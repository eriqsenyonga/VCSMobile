package ug.dataplus_systems.vcsmobile;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fm;
    public FloatingActionButton fab;
    int i = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();

        // Button  b = (Button) findViewById(R.id.b);

        fm = getSupportFragmentManager();


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


         View header = navigationView.getHeaderView(0);
        Spinner projectSpinner = (Spinner) header.findViewById(R.id.spinner_projects);

        ArrayAdapter<CharSequence> projectAdapter = ArrayAdapter.createFromResource(this,
                R.array.project_list, android.R.layout.simple_spinner_item);

        projectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        projectSpinner.setAdapter(projectAdapter);
/*
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inf = LayoutInflater.from(MainActivity.this);
                View vd = inf.inflate(R.layout.activity_my_profile,null,false);

                if (i == 1) {

                    navigationView.getMenu().clear();

                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.MATCH_PARENT
                    );

                    params.setMargins(0, header.getHeight(), 0, 0);

                    navigationView.addView(vd, params);

                    i = 2;
                } else {


                 //   vd.setVisibility(View.GONE);
                    navigationView.removeView(vd);

                    navigationView.inflateMenu(R.menu.activity_main_drawer);

                    i = 1;
                }

            }
        });
*/
        Intent callerIntent = getIntent();
        if (callerIntent.hasExtra("zero")) {
            drawer.openDrawer(GravityCompat.START);
            fm.beginTransaction().replace(R.id.contentMain, new HomeFragment()).commit();
            getSupportActionBar().setTitle("Home");
        }


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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        Fragment fragment = null;


        if (id == R.id.nav_home) {

            fragment = new HomeFragment();
        }

        if (id == R.id.nav_pap_list) {

            fragment = new PapListFragment();
        }

        if (id == R.id.nav_project_details) {

            fragment = new ProjectsDetailsFragment();
        }

        if (id == R.id.nav_chats) {

            fragment = new PapEntry();
        }


        if (fragment != null) {

            fm.beginTransaction().replace(R.id.contentMain, fragment).commit();
            getSupportActionBar().setTitle(item.getTitle());
            drawer.closeDrawer(GravityCompat.START);
            item.setChecked(true);


        }


        return true;
    }
}
