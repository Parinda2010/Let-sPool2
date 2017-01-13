package com.example.parinda.letspool;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {


    private DrawerLayout drawerlayout;
    private ListView listview;
    private ActionBarDrawerToggle drawerlistener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerlayout=(DrawerLayout) findViewById(R.id.drawerLayout);

        listview=(ListView) findViewById(R.id.drawerList);

        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.pages,android.R.layout.simple_list_item_1);
        listview.setAdapter(adapter);

        drawerlistener=new ActionBarDrawerToggle(this,drawerlayout,R.drawable.drawer_icon1,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerlayout.setDrawerListener(drawerlistener);

        // getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.drawer_icon1);
     //   ActionBar actionBar=getActionBar();
     //   actionBar.setDisplayHomeAsUpEnabled(true);

      //  actionBar.setHomeAsUpIndicator(R.drawable.drawer_icon1);
        drawerlayout.post(new Runnable() {
            @Override
            public void run() {
                drawerlistener.syncState();
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = null;
                switch (position)
                {

                    case 0:
                        fragment=new Register();
                        break;
                    case 1:
                        fragment=new Login();
                        break;
                    default:
                        fragment=new Register();
                        break;
                }
                FragmentManager fragmentManager= getSupportFragmentManager();
                fragmentManager.beginTransaction().add(fragment,"fragment").addToBackStack("fragment").replace(R.id.mainContent,fragment).commit();
                drawerlayout.closeDrawers();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(drawerlistener.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerlistener.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerlistener.syncState();
    }
}
