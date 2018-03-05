package com.example.tinaf.loof;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            // Handle the camera action

        } else if (id == R.id.nav_gallery) {
            clickAnime();

        } else if (id == R.id.nav_slideshow) {
            clickManga();

        } else if (id == R.id.nav_manage) {
            clickTVShows();

        } else if (id == R.id.nav_share) {
            clickMovies();

        } else if (id == R.id.nav_send) {
            clickBooks();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void clickAnime(){
        //case R.id.navigation_item_1:
        Intent i = new Intent(MainActivity.this, AnimeActivity.class);
        startActivity(i);
    }

    public void clickManga(){
        //case R.id.navigation_item_1:
        Intent i = new Intent(MainActivity.this, MangaActivity.class);
        startActivity(i);
    }

    public void clickTVShows(){
        //case R.id.navigation_item_1:
        Intent i = new Intent(MainActivity.this, TVShowsActivity.class);
        startActivity(i);
    }

    public void clickMovies(){
        //case R.id.navigation_item_1:
        Intent i = new Intent(MainActivity.this, MoviesActivity.class);
        startActivity(i);
    }

    public void clickBooks(){
        //case R.id.navigation_item_1:
        Intent i = new Intent(MainActivity.this, BooksActivity.class);
        startActivity(i);
    }
}
