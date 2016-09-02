package ug.dataplus_systems.vcsmobile;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewPapPropertyInfoImprovements extends Fragment implements View.OnClickListener {

    RecyclerView rvImprovementsList;
    TextView tvNoImprovementsMessage;
    View v;
    NewPapActivity newPapActivity;
    PapLocal papLocal;
    List<Improvement> improvementsHere;
    ImprovementsListAdapter adapter;


    public NewPapPropertyInfoImprovements() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_new_pap_property_info_improvements, container, false);
        rvImprovementsList = (RecyclerView) v.findViewById(R.id.recycler_view);
        tvNoImprovementsMessage = (TextView) v.findViewById(R.id.tv_no_improvements);


        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        rvImprovementsList.hasFixedSize();
        rvImprovementsList.setLayoutManager(new LinearLayoutManager(getActivity()));

        newPapActivity = (NewPapActivity) getActivity();
        papLocal = newPapActivity.getPapLocalItem();

        improvementsHere = papLocal.getImprovements();


        newPapActivity.fab.show();

        newPapActivity.fab.setOnClickListener(this);

        showImprovements();

    }

    private void showImprovements() {

        adapter = new ImprovementsListAdapter(getActivity(), improvementsHere);
        rvImprovementsList.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {
        if (v == newPapActivity.fab) {


            LayoutInflater inflater = LayoutInflater.from(getActivity());

            View dialogView = inflater.inflate(R.layout.dialog_new_improvement, null);

            final TextInputLayout tilCategory = (TextInputLayout) dialogView.findViewById(R.id.til_improvement_name);
            final Spinner spinnerSubCategory = (Spinner) dialogView.findViewById(R.id.spinner_improvement_sub_category);
            final TextInputLayout tilArea = (TextInputLayout) dialogView.findViewById(R.id.til_improvement_area);
            final Spinner spinnerUnits = (Spinner) dialogView.findViewById(R.id.spinner_improvement_area_units);
            final TextInputLayout tilValue = (TextInputLayout) dialogView.findViewById(R.id.til_improvement_value);

            final TextInputLayout tilRoofType = (TextInputLayout) dialogView.findViewById(R.id.til_roof_type);
            final TextInputLayout tilWallsType = (TextInputLayout) dialogView.findViewById(R.id.til_walls_type);
            final TextInputLayout tilWindowsType = (TextInputLayout) dialogView.findViewById(R.id.til_windows_type);
            final TextInputLayout tilDoorsType = (TextInputLayout) dialogView.findViewById(R.id.til_doors_type);
            final TextInputLayout tilFloorType = (TextInputLayout) dialogView.findViewById(R.id.til_floor_type);
            Button save = (Button) dialogView.findViewById(R.id.b_save_improvement);
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

                    if (tilCategory.getEditText().getText().toString().isEmpty()
                            || tilArea.getEditText().getText().toString().isEmpty()) {

                        if (tilCategory.getEditText().getText().toString().isEmpty()) {

                            tilCategory.getEditText().setError("Enter category");
                        }

                        if (tilArea.getEditText().getText().toString().isEmpty()) {

                            tilArea.getEditText().setError("Enter area");
                        }

                    } else {

                        Improvement improvement = new Improvement();

                        improvement.setCategory(tilCategory.getEditText().getText().toString());
                        improvement.setSubCategory(spinnerSubCategory.getSelectedItem().toString());
                        improvement.setArea(tilArea.getEditText().getText().toString());
                        improvement.setUnit(spinnerUnits.getSelectedItem().toString());
                        improvement.setRoof(tilRoofType.getEditText().getText().toString());
                        improvement.setFloor(tilFloorType.getEditText().getText().toString());
                        improvement.setWalls(tilWallsType.getEditText().getText().toString());
                        improvement.setWindows(tilWindowsType.getEditText().getText().toString());
                        improvement.setDoors(tilDoorsType.getEditText().getText().toString());
                        improvement.setValue(tilValue.getEditText().getText().toString());

                        improvementsHere.add(improvement);

                        papLocal.setImprovements(improvementsHere);

                        showImprovements();

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
