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

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewPapPropertyInfoStructures extends Fragment implements View.OnClickListener {

    RecyclerView rvImprovementsList;
    TextView tvNoImprovementsMessage;
    View v;
    NewPapActivity newPapActivity;
    PapLocal papLocal;
    List<Structure> improvementsHere;
    StructuresListAdapter adapter;


    public NewPapPropertyInfoStructures() {
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

        improvementsHere = papLocal.getStructures();


        newPapActivity.fab.show();

        newPapActivity.fab.setOnClickListener(this);

        showImprovements();

    }

    private void showImprovements() {

        adapter = new StructuresListAdapter(getActivity(), improvementsHere);
        rvImprovementsList.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {
        if (v == newPapActivity.fab) {


            LayoutInflater inflater = LayoutInflater.from(getActivity());

            View dialogView = inflater.inflate(R.layout.dialog_new_structure, null);

            final TextInputLayout tilStrName = (TextInputLayout) dialogView.findViewById(R.id.til_str_name);
            final Spinner spnStrType = (Spinner) dialogView.findViewById(R.id.spinner_str_type);
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

                    if (tilStrName.getEditText().getText().toString().isEmpty()
                            || tilArea.getEditText().getText().toString().isEmpty()) {

                        if (tilStrName.getEditText().getText().toString().isEmpty()) {

                            tilStrName.getEditText().setError("Enter name");
                        }

                        if (tilArea.getEditText().getText().toString().isEmpty()) {

                            tilArea.getEditText().setError("Enter area");
                        }

                    } else {

                        Structure structure = new Structure();

                        structure.setStructureName(tilStrName.getEditText().getText().toString());
                        structure.setStructureType(spnStrType.getSelectedItem().toString());
                        structure.setArea(tilArea.getEditText().getText().toString());
                        structure.setUnit(spinnerUnits.getSelectedItem().toString());
                        structure.setRoof(tilRoofType.getEditText().getText().toString());
                        structure.setFloor(tilFloorType.getEditText().getText().toString());
                        structure.setWalls(tilWallsType.getEditText().getText().toString());
                        structure.setWindows(tilWindowsType.getEditText().getText().toString());
                        structure.setDoors(tilDoorsType.getEditText().getText().toString());
                        structure.setValue(tilValue.getEditText().getText().toString());

                        improvementsHere.add(structure);

                        papLocal.setStructures(improvementsHere);

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
