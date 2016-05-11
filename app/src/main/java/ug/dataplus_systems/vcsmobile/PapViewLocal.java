package ug.dataplus_systems.vcsmobile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;

import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

public class PapViewLocal extends AppCompatActivity {

    ImageView papPhoto;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;
    SimpleRecyclerAdapter simpleRecyclerAdapter;
    RecyclerView recyclerView;
    int mutedColor = R.attr.colorPrimary;
    LinearLayout title, contents;
    private YoYo.YoYoString rope;
    ImageView iv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_pap_view);

        papPhoto = (ImageView) findViewById(R.id.header);
        iv = (ImageView) findViewById(R.id.drop_arrow);





      /* Glide.with(this).load("http://ea-agribusiness.co.ug/wp-content/uploads/2013/09/Agrinsurance-27-660x330.jpg").into(papPhoto);

        papPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(PapView.this).load("http://www.newvision.co.ug/newvision_cms/newsimages/file/lion-assurance-2-10.jpg").into(papPhoto);
            }
        });

        */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
               R.drawable.uuu);


        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {



                mutedColor = palette.getMutedColor(R.color.colorPrimary);
                collapsingToolbar.setContentScrimColor(mutedColor);
                collapsingToolbar.setStatusBarScrimColor(R.color.black_trans80);
            }
        });




        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
     //   collapsingToolbar.setTitle("Suleiman Ali Shakir");

    //    getSupportActionBar().setSubtitle();
        toolbar.setSubtitle("ID: KIP4753");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

 /*       recyclerView = (RecyclerView) findViewById(R.id.scrollableview);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<String> listData = new ArrayList<String>();
        int ct = 0;
        for (int i = 0; i < VersionModel.data.length * 2; i++) {
            listData.add(VersionModel.data[ct]);
            ct++;
            if (ct == VersionModel.data.length) {
                ct = 0;
            }
        }

        if (simpleRecyclerAdapter == null) {
            simpleRecyclerAdapter = new SimpleRecyclerAdapter(listData);
            recyclerView.setAdapter(simpleRecyclerAdapter);
        } */

        title = (LinearLayout) findViewById(R.id.title_d);
        contents = (LinearLayout) findViewById(R.id.content_d);




    }

    public void toggleContent(View v){

        if(iv.isActivated()){iv.setActivated(false);}else{

        iv.setActivated(true);}

       // YoYo.with(Techniques.ZoomInDown).duration(100).playOn(contents);

        contents.setVisibility(contents.isShown()
                ? View.GONE
                : View.VISIBLE);

    }

    public void GetBelow(View v){

        TextView tc = (TextView) findViewById(R.id.tv_entry_occupati);

        YoYo.with(Techniques.Landing).duration(700).playOn(tc);

        tc.setVisibility(tc.isShown()
                ? View.GONE
                : View.VISIBLE);

    }

}
