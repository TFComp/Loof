package com.example.tinaf.loof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.tinaf.loof.AnimeActivity.items;

public class BooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        this.setTitle("Books");
        if (items == null) {
            items = new String[] {"one", "two", "three"};
        }
    }
}
