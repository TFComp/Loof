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
import java.util.ArrayList;
import java.util.Arrays;
import android.util.SparseBooleanArray;


public class AnimeActivity extends AppCompatActivity {
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    static String[] items;
    static ArrayList<Anime> animeList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);
        this.setTitle("Anime");
        if (animeList == null) {
            animeList = new ArrayList<Anime>();
            items = new String[10];
            for(int i=0; i<10; i++){
                items[i] = "Anime "+i;
                animeList.add(new Anime("Anime "+i,"Lorem "+i, i));
            }
        }
        listView = (ListView) findViewById(R.id.animeList);
        arrayList = new ArrayList<>(Arrays.asList(items));
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
        String fileName = "anime.xml";
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
}
