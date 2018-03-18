package com.example.tinaf.loof;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import android.util.SparseBooleanArray;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class MoviesActivity extends AppCompatActivity {
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    static ArrayList<String> items;
    static ArrayList<Movies> moviesList;
    ListView listView;
    String fileName = "movies.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        this.setTitle("Movies");
        this.setTitle("Manga");

        moviesList = new ArrayList<Movies>();
        items = new ArrayList<String>();

        this.ReadFile();
        listView = (ListView) findViewById(R.id.moviesList);
        arrayList = new ArrayList<>(items);
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, arrayList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
                Object o = listView.getItemAtPosition(position);
                EditText name = (EditText) findViewById(R.id.name);
                EditText description = (EditText) findViewById(R.id.description);
                EditText page = (EditText) findViewById(R.id.time);
                name.setText(o.toString());
                for(Movies movies:moviesList) {
                    if (movies.getName().contentEquals(o.toString())) {
                        description.setText(movies.getDescription());
                        page.setText(""+movies.getTime());
                    }
                }
            }
        });
    }


    public void onAddClick(View view) {
        EditText nametextview = (EditText) findViewById(R.id.name);
        String name = (String) nametextview.getText().toString();
        EditText descriptiontextview = (EditText) findViewById(R.id.description);
        String description = (String) descriptiontextview.getText().toString();
        EditText episidetextview = (EditText) findViewById(R.id.time);
        String time = (String) episidetextview.getText().toString();
        arrayList.add(name);
        moviesList.add(new Movies(name, description, time));
        adapter.notifyDataSetChanged();
        SaveFile();
    }

    public void onUpdateClick(View view) {
        EditText textview = (EditText) findViewById(R.id.name);
        String name = (String) textview.getText().toString();
        EditText descriptiontextview = (EditText) findViewById(R.id.description);
        String description = (String) descriptiontextview.getText().toString();
        EditText episidetextview = (EditText) findViewById(R.id.time);
        String time = (String) episidetextview.getText().toString();

        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        if (checkedItems != null) {
            for (int i = 0; i < checkedItems.size(); ++i) {
                if (checkedItems.valueAt(i)) {
                    arrayList.set(checkedItems.keyAt(i), name);
                    Movies movies = new Movies(name, description, time);
                    moviesList.set(checkedItems.keyAt(i), movies);
                }
                adapter.notifyDataSetChanged();
            }
        }
        SaveFile();
    }

    public void onDeleteClick(View view) {
        EditText textview = (EditText) findViewById(R.id.name);
        String name = (String) textview.getText().toString();

        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        if (checkedItems != null) {
            for (int i = 0; i < checkedItems.size(); ++i) {
                if (checkedItems.valueAt(i)) {
                    arrayList.remove(checkedItems.keyAt(i));
                    moviesList.remove(checkedItems.keyAt(i));
                }
                adapter.notifyDataSetChanged();
            }
        }
        SaveFile();
    }

    public void SaveFile(){
        String fileContents = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        fileContents += "<movie>";
        for (int i = 0; i < moviesList.size(); i++){
            fileContents += moviesList.get(i).toXml();
        }
        fileContents += "</movie>";
        FileOutputStream outputStream;

        try{
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void ReadFile(){
        try{
            InputStream inputStream = openFileInput(fileName);
            DocumentBuilderFactory docbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docbFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputStream);

            NodeList nodeList = doc.getElementsByTagName("manga");

            for(int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element =(Element) node;
                    String name = getValue("name",element);
                    moviesList.add(new Movies(name,
                            getValue("description", element),
                            getValue("time", element)));
                    items.add(name);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getValue(String tag, Element element){
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
