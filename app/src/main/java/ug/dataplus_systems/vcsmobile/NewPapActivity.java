package ug.dataplus_systems.vcsmobile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NewPapActivity extends AppCompatActivity implements View.OnClickListener {

    NonSwipeableViewPager viewPager;
    Button b_right, b_left;
    TextView tv_step_number, tv_step_title;
    PagerAdapterNewPap adapter;
    public FloatingActionButton fab;
    PapLocal papLocal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pap);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        papLocal = new PapLocal(this);

        viewPager = (NonSwipeableViewPager) findViewById(R.id.vp_new_pap_steps);
        b_right = (Button) findViewById(R.id.b_right);
        b_left = (Button) findViewById(R.id.b_left);
        tv_step_number = (TextView) findViewById(R.id.tv_label);
        tv_step_title = (TextView) findViewById(R.id.tv_step_title);
        fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.hide();
        adapter = new PagerAdapterNewPap(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        viewPager.setOffscreenPageLimit(7);

        b_right.setOnClickListener(this);

        b_left.setOnClickListener(this);

        showOrHideFab();


        //      fab.setOnClickListener(this);

    }

    private void showOrHideFab() {

        if (viewPager.getCurrentItem() == PagerAdapterNewPap.NEW_PAP_STEP_PROPERTY_INFO_CROPS ||
                viewPager.getCurrentItem() == PagerAdapterNewPap.NEW_PAP_STEP_PROPERTY_INFO_IMPROVEMENTS ||
                viewPager.getCurrentItem() == PagerAdapterNewPap.NEW_PAP_STEP_ADDRESSES ||
                viewPager.getCurrentItem() == PagerAdapterNewPap.NEW_PAP_STEP_FAMILY_MEMBERS) {

            fab.show();

        } else {

            fab.hide();
        }
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        viewPager.setCurrentItem(item, smoothScroll);
        showOrHideFab();

    }

    @Override
    public void onClick(View v) {

        if (v == b_right) {

            if (!b_right.getText().equals("Finish")) {

                int currentPageIndex = viewPager.getCurrentItem();

                int newPageIndex = currentPageIndex + 1;


                setCurrentItem(newPageIndex, true);
                tv_step_number.setText("Step " + (newPageIndex + 1) + " of 8");
                b_left.setVisibility(View.VISIBLE);

                setStepTitle(newPageIndex);

                if (whichScreenNowActive() == 7) {
                    tv_step_number.setText("Step 8 of 8");

                    b_right.setText("Finish");
                } else {
                    b_right.setText("Next");
                }
                if (whichScreenNowActive() != 0) {
                    b_left.setText("Back");
                }

            } else {

                savePapLocally();

            }
        }

        if (v == b_left) {

            goToPreviousStep();

        }


    }

    private void savePapLocally() {

        /*preferabbly show progress bar as the pap is saved into the local sqlite db
         * get the PAPLOCAL item and save it to SQLite Database
         *  redirect to Main Activity
         */

        if ((papLocal.getName() == "") || (papLocal.getReferenceNumber() == "")) {
            //if pap has no name, dont save or reference number

            if (papLocal.getName() == "") {
                Toast.makeText(this, "Please Enter Name", Toast.LENGTH_LONG).show();
            }

            if (papLocal.getReferenceNumber() == "") {
                Toast.makeText(this, "Please Enter Reference Number", Toast.LENGTH_LONG).show();
            }
        } else {
            DbClass mDbClass = new DbClass(NewPapActivity.this);
            mDbClass.open();
            mDbClass.insertPap(papLocal);
            mDbClass.close();

            Toast.makeText(this, "Pap created", Toast.LENGTH_LONG).show();



            Intent i = new Intent(NewPapActivity.this, MainActivity.class);
            startActivity(i);
        }

    }


    public int whichScreenNowActive() {
        return viewPager.getCurrentItem();
    }

    private void setStepTitle(int pageIndex) {

        if (pageIndex == 0) {
            tv_step_title.setText("Basic Info");
        }
        if (pageIndex == 1) {
            tv_step_title.setText("Property Info");
        }

        if (pageIndex == 2) {
            tv_step_title.setText("Addresses");
        }

        if (pageIndex == 3) {
            tv_step_title.setText("Family Members");
        }


        if (pageIndex == 4) {
            tv_step_title.setText("Crops");
        }
        if (pageIndex == 5) {
            tv_step_title.setText("Improvements");
        }

        if (pageIndex == 6) {
            tv_step_title.setText("Photos");
        }
        if (pageIndex == 7) {
            tv_step_title.setText("Preview");
        }

    }

    private void goToPreviousStep() {

        int currentPageIndex = viewPager.getCurrentItem();
        int newPageIndex = currentPageIndex - 1;
        setCurrentItem(newPageIndex, true);
        tv_step_number.setText("Step " + (newPageIndex + 1) + " of 8");

        setStepTitle(newPageIndex);


        if (whichScreenNowActive() == 0) {
            tv_step_number.setText("Step 1 of 8");
            b_left.setText("");
        } else {
            b_left.setText("Back");
        }

        if (!(whichScreenNowActive() == 7)) {
            b_right.setText("Next");
        }
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            goToPreviousStep();
        }
    }

    public PapLocal getPapLocalItem() {
        return this.papLocal;
    }

    public void updatePapLocalItem(PapLocal paramPapLocal) {
        papLocal = paramPapLocal;
    }


}



