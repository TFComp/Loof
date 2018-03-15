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


public class TVShowsActivity extends AppCompatActivity {
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    static String[] items;
    static ArrayList<TVShows> tvShowsList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshows);
        this.setTitle("TV Shows");
        if (tvShowsList == null) {
            tvShowsList = new ArrayList<TVShows>();
            items = new String[10];
            for(int i=0; i<10; i++){
                items[i] = "TV Shows "+i;
                tvShowsList.add(new TVShows("TV Shows "+i,"Lorem "+i, i));
            }
        }
        listView = (ListView) findViewById(R.id.tvShowsList);
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
                EditText page = (EditText) findViewById(R.id.episode);
                name.setText(o.toString());
                for(TVShows tvShows:tvShowsList) {
                    if (tvShows.getName().contentEquals(o.toString())) {
                        description.setText(tvShows.getDescription());
                        page.setText(""+tvShows.getEpisode());
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
        tvShowsList.add(new TVShows(name, description, Integer.parseInt(episode)));
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
                    TVShows tvshows = new TVShows(name, description, Integer.parseInt(episode));
                    tvShowsList.set(checkedItems.keyAt(i), tvshows);
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
                    tvShowsList.remove(checkedItems.keyAt(i));
                }
                adapter.notifyDataSetChanged();
            }
        }
    }
}