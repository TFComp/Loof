package com.example.tinaf.loof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
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
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshows);
        this.setTitle("TV Shows");
        if (items == null) {
            items = new String[]{"one", "two", "three"};
        }
        listView = (ListView) findViewById(R.id.tvShowsList);
        arrayList = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, arrayList);
        listView.setAdapter(adapter);
    }


    public void onClick(View view) {
        EditText textview = (EditText) findViewById(R.id.name);
        String editText = (String) textview.getText().toString();
        arrayList.add(editText);
        adapter.notifyDataSetChanged();
    }

    public void onDeleteClick(View view) {
        EditText textview = (EditText) findViewById(R.id.name);
        String editText = (String) textview.getText().toString();
        //arrayList.add(editText);
        //adapter.notifyDataSetChanged();

        //Log.i(TAG, "deleteSelectedEntries");

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
