package ug.dataplus_systems.vcsmobile;

import android.app.AlertDialog;
import android.app.Dialog;
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


        adapter = new PagerAdapterNewPap(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        viewPager.setOffscreenPageLimit(6);

        b_right.setOnClickListener(this);

        b_left.setOnClickListener(this);

        showOrHideFab();

        fab.setOnClickListener(this);

    }

    private void showOrHideFab() {

        if (viewPager.getCurrentItem() == PagerAdapterNewPap.NEW_PAP_STEP_PROPERTY_INFO_CROPS ||
                viewPager.getCurrentItem() == PagerAdapterNewPap.NEW_PAP_STEP_PROPERTY_INFO_IMPROVEMENTS) {

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

            int currentPageIndex = viewPager.getCurrentItem();
            int newPageIndex = currentPageIndex + 1;


            setCurrentItem(newPageIndex, true);
            tv_step_number.setText("Step " + (newPageIndex + 1) + " of 5");
            b_left.setVisibility(View.VISIBLE);

            setStepTitle(newPageIndex);


        }

        if (v == b_left) {

            goToPreviousStep();

        }

        if (v == fab) {

            if (viewPager.getCurrentItem() == PagerAdapterNewPap.NEW_PAP_STEP_PROPERTY_INFO_CROPS) {
                //if crops page is being shown, show new crop dialog

                showDialogFor(PagerAdapterNewPap.NEW_PAP_STEP_PROPERTY_INFO_CROPS);

            } else if (viewPager.getCurrentItem() == PagerAdapterNewPap.NEW_PAP_STEP_PROPERTY_INFO_IMPROVEMENTS) {
                //if improvements page is being shown, show new improvement dialog

                showDialogFor(PagerAdapterNewPap.NEW_PAP_STEP_PROPERTY_INFO_IMPROVEMENTS);

            }


        }

    }

    private void showDialogFor(int which) {

        LayoutInflater inflater = LayoutInflater.from(this);

        if (which == PagerAdapterNewPap.NEW_PAP_STEP_PROPERTY_INFO_CROPS) {

            View dialogView = inflater.inflate(R.layout.dialog_new_crop, null);

            Toolbar dialogToolbar = (Toolbar) dialogView.findViewById(R.id.toolbar_dialog);
            final TextInputLayout tilCropName = (TextInputLayout) dialogView.findViewById(R.id.til_crop_name);
            final TextInputLayout tilQuantity = (TextInputLayout) dialogView.findViewById(R.id.til_crop_quantity);
            Spinner units = (Spinner) dialogView.findViewById(R.id.spinner_crop_units);
            Button save = (Button) dialogView.findViewById(R.id.b_save_crop);
            Button cancel = (Button) dialogView.findViewById(R.id.b_cancel);


            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setView(dialogView);

            final Dialog d = builder.create();

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d.dismiss();
                }
            });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (tilCropName.getEditText().getText().toString().isEmpty()) {

                        tilCropName.getEditText().setError("Enter crop name");
                    }

                    if (tilQuantity.getEditText().getText().toString().isEmpty()) {

                        tilQuantity.getEditText().setError("Enter quantity");
                    }

                }
            });


            d.show();

        } else if (which == PagerAdapterNewPap.NEW_PAP_STEP_PROPERTY_INFO_IMPROVEMENTS) {

            View dialogView = inflater.inflate(R.layout.dialog_new_improvement, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setView(dialogView);

            final Dialog d = builder.create();

            d.show();
        }


    }

    private void setStepTitle(int pageIndex) {

        if (pageIndex == 0) {
            tv_step_title.setText("Basic Info");
        }
        if (pageIndex == 1) {
            tv_step_title.setText("Property Info");
        }
        if (pageIndex == 2) {
            tv_step_title.setText("Crops");
        }
        if (pageIndex == 3) {
            tv_step_title.setText("Improvements");
        }
        if (pageIndex == 4) {
            tv_step_title.setText("Other Details");
        }
        if (pageIndex == 5) {
            tv_step_title.setText("Photos");
        }
        if (pageIndex == 6) {
            tv_step_title.setText("Preview");
        }

    }

    private void goToPreviousStep() {

        int currentPageIndex = viewPager.getCurrentItem();
        int newPageIndex = currentPageIndex - 1;
        setCurrentItem(newPageIndex, true);
        tv_step_number.setText("Step " + (newPageIndex + 1) + " of 5");

        setStepTitle(newPageIndex);
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
