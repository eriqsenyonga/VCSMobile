package ug.dataplus_systems.vcsmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class PapViewLive extends AppCompatActivity {

    CoordinatorLayout mCoordinatorLayout;
    AppBarLayout mAppBarLayout;
    int heightPx;
    Intent receivedIntent;
    Bundle papBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pap_view_live);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        receivedIntent = getIntent();
        papBundle = receivedIntent.getBundleExtra("papBundle");

        getSupportActionBar().setTitle(papBundle.getString("name"));

        setupImagesViewPager();

        setupViewPager();

        setupCollapsingToolbar();
    }

    private void setupImagesViewPager() {

        int[] papImages = {
                R.drawable.uuu,
                R.drawable.house,
                R.drawable.plantation,
                R.drawable.agric,
                R.drawable.prof_pic

        };

        ViewPager papImagesViewPager = (ViewPager) findViewById(R.id.vp_pap_images);

        PapImagesPagerAdapter papImagesPagerAdapter = new PapImagesPagerAdapter(this, papImages);

        papImagesViewPager.setAdapter(papImagesPagerAdapter);

    }

    private void setupCollapsingToolbar() {

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);


        mAppBarLayout.post(new Runnable() {
            @Override
            public void run() {
                heightPx = findViewById(R.id.app_bar_layout).getHeight();

                Log.d("heightPx", "" + heightPx);

                setAppBarOffset((heightPx * 3) / 5);


            }
        });


        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(
                R.id.collapse_toolbar);

        collapsingToolbar.setTitleEnabled(false);
    }

    private void setupViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), this, 2, MyPagerAdapter.PAP_VIEW_LIVE_VIEWPAGER, papBundle);
        viewPager.setAdapter(adapter);
    }

    private void setAppBarOffset(int offsetPx) {

        Log.d("offsetPx", "" + offsetPx);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        behavior.onNestedPreScroll(mCoordinatorLayout, mAppBarLayout, null, 0, heightPx - offsetPx, new int[]{0, 0});

    }
}
