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

import java.io.FileInputStream;
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


public class AnimeActivity extends AppCompatActivity {
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    static ArrayList<String> items;
    static ArrayList<Anime> animeList;
    ListView listView;
    String fileName = "anime.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);
        this.setTitle("Anime");

        animeList = new ArrayList<Anime>();
        items = new ArrayList<String>();

        this.ReadFile();
        listView = (ListView) findViewById(R.id.animeList);
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
                EditText episodNum = (EditText) findViewById(R.id.episode);
                name.setText(o.toString());
                for(Anime anime:animeList) {
                    if (anime.getName().contentEquals(o.toString())) {
                        description.setText(anime.getDescription());
                        episodNum.setText(""+anime.getEpisode());
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
        EditText episidetextview = (EditText) findViewById(R.id.episode);
        String episode = (String) episidetextview.getText().toString();
        arrayList.add(name);
        animeList.add(new Anime(name, description, Integer.parseInt(episode)));
        adapter.notifyDataSetChanged();
        SaveFile();
    }

    public void onUpdateClick(View view) {
        EditText textview = (EditText) findViewById(R.id.name);
        String name = (String) textview.getText().toString();
        EditText descriptiontextview = (EditText) findViewById(R.id.description);
        String description = (String) descriptiontextview.getText().toString();
        EditText episidetextview = (EditText) findViewById(R.id.episode);
        String episode = (String) episidetextview.getText().toString();

        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        if (checkedItems != null) {
            for (int i = 0; i < checkedItems.size(); ++i) {
                if (checkedItems.valueAt(i)) {
                    arrayList.set(checkedItems.keyAt(i), name);
                    Anime anime = new Anime(name, description, Integer.parseInt(episode));
                    animeList.set(checkedItems.keyAt(i), anime);
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
                    animeList.remove(checkedItems.keyAt(i));
                }
                adapter.notifyDataSetChanged();
            }
        }
        SaveFile();
    }

    public void SaveFile(){
        String fileContents = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        fileContents += "<animes>";
        for (int i = 0; i < animeList.size(); i++){
            fileContents += animeList.get(i).toXml();
        }
        fileContents += "</animes>";
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

            NodeList nodeList = doc.getElementsByTagName("anime");

            for(int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element =(Element) node;
                    String name = getValue("name",element);
                    animeList.add(new Anime(name,
                            getValue("description", element),
                            Integer.parseInt(getValue("episode", element))));
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
