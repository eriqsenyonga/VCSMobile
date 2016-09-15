package ug.dataplus_systems.vcsmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.tr4android.support.extension.internal.Account;
import com.tr4android.support.extension.widget.AccountHeaderView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fm;
    public FloatingActionButton fab;
    int i = 1;
    DbClass dbClass;
    SharedPreferences mPositionSavedPrefs;
    SharedPreferences.Editor posSavedEditor;


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

        mPositionSavedPrefs = getSharedPreferences("mPositionSaved",
                Context.MODE_PRIVATE);
        posSavedEditor = mPositionSavedPrefs.edit();


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        setUpNavigationViewHeaderProjects();


        Intent callerIntent = getIntent();
        if (callerIntent.hasExtra("zero")) {
            drawer.openDrawer(GravityCompat.START);
            fm.beginTransaction().replace(R.id.contentMain, new HomeFragment()).commit();
            getSupportActionBar().setTitle("Dashboard");
            posSavedEditor.putInt("last_main_position", R.id.nav_home).apply();

        } else {

            Fragment fragment = null;
            CharSequence title = null;

            int id = mPositionSavedPrefs.getInt(
                    "last_main_position", 1);


            if (id == R.id.nav_home) {

                fragment = new HomeFragment();
                title = "Dashboard";
            }

            if (id == R.id.nav_pap_list) {

                fragment = new PapListFragment();
                title = "PAP";
            }

            if (id == R.id.nav_project_details) {

                fragment = new ProjectsDetailsFragment();
                title = "Project";
            }

            if (id == R.id.nav_chats) {

                fragment = new PapEntry();
                title = "Chats";
            }

            if (id == R.id.nav_valuation) {

                fragment = new ValuationFragment();
                title = "Valuation";
            }


            if (fragment != null) {

                getSupportActionBar().setTitle(title);
                fm.beginTransaction().replace(R.id.contentMain, fragment).commit();

                drawer.closeDrawer(GravityCompat.START);


            }


        }


    }

    private void setUpNavigationViewHeaderProjects() {

        dbClass = new DbClass(MainActivity.this);
        List<Project> myProjects = new ArrayList<>();
        myProjects = dbClass.getAllProjects(MainActivity.this);

        View header = navigationView.getHeaderView(0);

        // Get a reference to the `AccountHeaderView`
        AccountHeaderView accountHeaderView = (AccountHeaderView) header.findViewById(R.id.account_header);

        // Add your accounts
        for (Project project : myProjects) {


            accountHeaderView.addAccount(new Account().setName("admin").setEmail(project.getProjectName()));

        }

/*
        // Add your accounts
        accountHeaderView.addAccounts(new Account().setName("Eric Senyonga").setEmail("Karuma Interconnection Project"),
                new Account().setName("Standard Gauge Railway").setEmail("Standard Gauge Railway"),
                new Account().setName("Total Oil Expansion").setEmail("Total Oil Expansion")
        );

        accountHeaderView.addAccount(new Account().setName("Kyambadde").setEmail("Pingu all the way"));
*/

        // Attach a listener to the `AccountHeaderView` to respond to a selected account
        accountHeaderView.setAccountSelectedListener(new AccountHeaderView.OnAccountSelectedListener() {
            @Override
            public boolean onAccountSelected(Account account) {
                drawer.closeDrawers();
                return false;
            }

            @Override
            public void onAccountChecked(Account account, boolean b) {
                drawer.closeDrawers();
            }

            @Override
            public void onAccountAddSelected() {
                drawer.closeDrawers();
            }

            @Override
            public void onAccountManageSelected() {
                drawer.closeDrawers();
            }
        });
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

        if (id == R.id.nav_valuation) {

            fragment = new ValuationFragment();
        }


        if (fragment != null) {

            fm.beginTransaction().replace(R.id.contentMain, fragment).commit();
            getSupportActionBar().setTitle(item.getTitle());
            posSavedEditor.putInt("last_main_position", id).apply();
            drawer.closeDrawer(GravityCompat.START);
            item.setChecked(true);


        }


        return true;
    }
}
