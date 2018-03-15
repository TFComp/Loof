package com.example.tinaf.loof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import android.util.SparseBooleanArray;


public class MangaActivity extends AppCompatActivity {
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    static String[] items;
    static ArrayList<Manga> mangaList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga);
        this.setTitle("Manga");
        if (mangaList == null) {
            mangaList = new ArrayList<Manga>();
            items = new String[10];
            for(int i=0; i<10; i++){
                items[i] = "Manga "+i;
                mangaList.add(new Manga("Manga "+i,"Lorem "+i, i));
            }
        }
        listView = (ListView) findViewById(R.id.mangaList);
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
                EditText page = (EditText) findViewById(R.id.chapter);
                name.setText(o.toString());
                for(Manga manga:mangaList) {
                    if (manga.getName().contentEquals(o.toString())) {
                        description.setText(manga.getDescription());
                        page.setText(""+manga.getChapter());
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
        EditText episidetextview = (EditText) findViewById(R.id.chapter);
        String chapter = (String) episidetextview.getText().toString();
        arrayList.add(name);
        mangaList.add(new Manga(name, description, Integer.parseInt(chapter)));
        adapter.notifyDataSetChanged();
    }

    public void onUpdateClick(View view) {
        EditText textview = (EditText) findViewById(R.id.name);
        String name = (String) textview.getText().toString();
        EditText descriptiontextview = (EditText) findViewById(R.id.description);
        String description = (String) descriptiontextview.getText().toString();
        EditText episidetextview = (EditText) findViewById(R.id.chapter);
        String chapter = (String) episidetextview.getText().toString();

        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        if (checkedItems != null) {
            for (int i = 0; i < checkedItems.size(); ++i) {
                if (checkedItems.valueAt(i)) {
                    arrayList.set(checkedItems.keyAt(i), name);
                    Manga manga = new Manga(name, description, Integer.parseInt(chapter));
                    mangaList.set(checkedItems.keyAt(i), manga);
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
                    mangaList.remove(checkedItems.keyAt(i));
                }
                adapter.notifyDataSetChanged();
            }
        }
    }
}