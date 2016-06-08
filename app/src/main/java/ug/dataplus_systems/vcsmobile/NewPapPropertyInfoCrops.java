package ug.dataplus_systems.vcsmobile;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewPapPropertyInfoCrops extends Fragment implements View.OnClickListener {

    RecyclerView rvCropsList;
    TextView tvNoCropsMessage;
    View v;
    NewPapActivity newPapActivity;
    PapLocal papLocal;
    List<Crop> cropsHere;
    CropsListAdapter adapter;

    public NewPapPropertyInfoCrops() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_new_pap_property_info_crops, container, false);

        rvCropsList = (RecyclerView) v.findViewById(R.id.recycler_view);
        tvNoCropsMessage = (TextView) v.findViewById(R.id.tv_no_crops_added);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        rvCropsList.hasFixedSize();
        rvCropsList.setLayoutManager(new LinearLayoutManager(getActivity()));

        newPapActivity = (NewPapActivity) getActivity();
        papLocal = newPapActivity.getPapLocalItem();

        cropsHere = papLocal.getCrops();


        newPapActivity.fab.show();

        newPapActivity.fab.setOnClickListener(this);

        showCrops();


    }

    private void showCrops() {

        this.adapter = new CropsListAdapter(getActivity(), this.cropsHere);
        rvCropsList.setAdapter(this.adapter);

    }

    @Override
    public void onClick(View v) {

        if (v == newPapActivity.fab) {
            //if fab is clicked then show the dialog to add new crop

            LayoutInflater inflater = LayoutInflater.from(getActivity());

            View dialogView = inflater.inflate(R.layout.dialog_new_crop, null);

            Toolbar dialogToolbar = (Toolbar) dialogView.findViewById(R.id.toolbar_dialog);
            final TextInputLayout tilCropName = (TextInputLayout) dialogView.findViewById(R.id.til_crop_name);
            final TextInputLayout tilCropDescription = (TextInputLayout) dialogView.findViewById(R.id.til_crop_description);
            final TextInputLayout tilQuantity = (TextInputLayout) dialogView.findViewById(R.id.til_crop_quantity);
            final Spinner units = (Spinner) dialogView.findViewById(R.id.spinner_crop_units);
            final Spinner spnCropType = (Spinner) dialogView.findViewById(R.id.spinner_crop_type);
            Button save = (Button) dialogView.findViewById(R.id.b_save_crop);
            Button cancel = (Button) dialogView.findViewById(R.id.b_cancel);


            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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

                    if (tilCropName.getEditText().getText().toString().isEmpty() || tilQuantity.getEditText().getText().toString().isEmpty()) {

                        if (tilCropName.getEditText().getText().toString().isEmpty()) {
                            tilCropName.getEditText().setError("Enter crop name");
                        }

                        if (tilQuantity.getEditText().getText().toString().isEmpty()) {

                            tilQuantity.getEditText().setError("Enter quantity");
                        }

                    } else {

                        Crop crop = new Crop();

                        crop.setCropName(tilCropName.getEditText().getText().toString());
                        crop.setCropDescription(tilCropDescription.getEditText().getText().toString());
                        crop.setCropType(spnCropType.getSelectedItem().toString());
                        crop.setQuantity(tilQuantity.getEditText().getText().toString());
                        crop.setUnit(units.getSelectedItem().toString());

                        cropsHere.add(crop);

                        papLocal.setCrops(cropsHere);

                        newPapActivity.updatePapLocalItem(papLocal);

                        showCrops();

                        d.dismiss();


                    }


                }
            });


            d.show();

        }


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && isResumed()) {
            onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!getUserVisibleHint()) {
            return;
        }

        newPapActivity.fab.setOnClickListener(this);


    }
}
