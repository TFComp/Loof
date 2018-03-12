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


public class AnimeActivity extends AppCompatActivity {
    ArrayList<Anime> arrayList;
    ArrayAdapter<Anime> adapter;
    static Anime[] items;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);
        this.setTitle("Anime");
        if (items == null) {
            items = new Anime[10];
            for(int i=0; i<10; i++){
                items[i]=new Anime("Anime "+i,"Lorem "+i, i);
            }
        }
        listView = (ListView) findViewById(R.id.animeList);
        arrayList = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<Anime>(this, R.layout.list_item, R.id.txtitem, arrayList);
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
                description.setText(o.toString());
                episodNum.setText(o.toString());
            }
        });
    }


    public void onAddClick(View view) {
        EditText textview = (EditText) findViewById(R.id.name);
        String editText = (String) textview.getText().toString();
        //arrayList.add(editText);
        adapter.notifyDataSetChanged();
    }

    public void onUpdateClick(View view) {
        EditText textview = (EditText) findViewById(R.id.name);
        String name = (String) textview.getText().toString();

        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        if (checkedItems != null) {
            for (int i = 0; i < checkedItems.size(); ++i) {
                if (checkedItems.valueAt(i)) {
                    //arrayList.set(checkedItems.keyAt(i), name);
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
                }
                adapter.notifyDataSetChanged();
            }
        }
    }
}
