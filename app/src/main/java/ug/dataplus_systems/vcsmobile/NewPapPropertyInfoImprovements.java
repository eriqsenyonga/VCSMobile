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
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewPapPropertyInfoImprovements extends Fragment implements View.OnClickListener{

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
        v =inflater.inflate(R.layout.fragment_new_pap_property_info_improvements, container, false);
        rvImprovementsList = (RecyclerView) v.findViewById(R.id.recycler_view);
        tvNoImprovementsMessage = (TextView)v.findViewById(R.id.tv_no_improvements);


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

    @Override
    public void onClick(View v) {
        if(v == newPapActivity.fab){

            LayoutInflater inflater =  LayoutInflater.from(getActivity());

            View dialogView = inflater.inflate(R.layout.dialog_new_improvement, null);

            TextInputLayout tilCategory = (TextInputLayout) dialogView.findViewById(R.id.til_improvement_name);
            Spinner spinnerSubCategory = (Spinner) dialogView.findViewById(R.id.spinner_improvement_sub_category);
            TextInputLayout tilArea = (TextInputLayout) dialogView.findViewById(R.id.til_improvement_area);
            Spinner spinnerUnits = (Spinner) dialogView.findViewById(R.id.spinner_improvement_area_units);
            TextInputLayout tilTotal = (TextInputLayout) dialogView.findViewById(R.id.til_improvement_value);
            TextInputLayout tilRoofType = (TextInputLayout) dialogView.findViewById(R.id.til_roof_type);
            TextInputLayout tilWallsType = (TextInputLayout) dialogView.findViewById(R.id.til_walls_type);
            TextInputLayout tilWindowsType = (TextInputLayout) dialogView.findViewById(R.id.til_windows_type);
            TextInputLayout tilDoorsType = (TextInputLayout) dialogView.findViewById(R.id.til_doors_type);
            TextInputLayout tilFloorType = (TextInputLayout) dialogView.findViewById(R.id.til_floor_type);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setView(dialogView);

            final Dialog d = builder.create();

            d.show();

        }
    }
}
