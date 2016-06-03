package ug.dataplus_systems.vcsmobile;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewPapPropertyInfoFrag extends Fragment {

    CheckBox cbHasCrops;
    CheckBox cbHasImprovements;
    CheckBox cbIsResident;
    NewPapActivity newPapActivity;
    PapLocal papLocal;
    View rv;
    Spinner spnDesignation;
    Spinner spnUnits;
    TextInputLayout tilPlotRef;
    TextInputLayout tilROW;
    TextInputLayout tilRefNo;
    TextInputLayout tilWayleave;
    String plotRef = "", refNo="", ROW="", wayleave="";
    ConversionClass mCC;


    public NewPapPropertyInfoFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rv = inflater.inflate(R.layout.fragment_new_pap_property_info, container, false);

        tilPlotRef = (TextInputLayout) rv.findViewById(R.id.til_plot_ref);
        tilRefNo = (TextInputLayout) rv.findViewById(R.id.til_ref_no);
        tilROW = (TextInputLayout) rv.findViewById(R.id.til_right_of_way_size);
        tilWayleave = (TextInputLayout) rv.findViewById(R.id.til_wayleave_size);
        spnDesignation = (Spinner) rv.findViewById(R.id.spinner_pap_designation);
        spnUnits = (Spinner) rv.findViewById(R.id.spinner_land_units);
        cbIsResident = (CheckBox) rv.findViewById(R.id.cb_is_resident);
        cbHasCrops = (CheckBox) rv.findViewById(R.id.cb_has_crops);
        cbHasImprovements = (CheckBox) rv.findViewById(R.id.cb_has_improvements);


        return rv;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mCC = new ConversionClass(getActivity());
        newPapActivity = (NewPapActivity) getActivity();
        papLocal = newPapActivity.getPapLocalItem();

        tilPlotRef.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!tilPlotRef.getEditText().getText().toString().equals("")) {
                    plotRef = tilPlotRef.getEditText().getText().toString();
                } else {
                    plotRef = "";
                }
                papLocal.setPlotReference(plotRef);
                newPapActivity.updatePapLocalItem(papLocal);

            }
        });

        tilRefNo.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!tilRefNo.getEditText().getText().toString().equals("")) {
                    refNo = tilRefNo.getEditText().getText().toString();
                } else {
                    refNo = "";
                }
                papLocal.setReferenceNumber(refNo);
                newPapActivity.updatePapLocalItem(papLocal);

            }
        });

        tilROW.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!tilROW.getEditText().getText().toString().equals("")) {
                    ROW = tilROW.getEditText().getText().toString();
                } else {
                    ROW = "0";
                }
                papLocal.setRightOfWaySize(mCC.valueConverter(ROW)); //turns it into db value with no decimals
                newPapActivity.updatePapLocalItem(papLocal);

            }
        });

        tilWayleave.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!tilWayleave.getEditText().getText().toString().equals("")) {
                    wayleave = tilWayleave.getEditText().getText().toString();
                } else {
                    wayleave = "0";
                }
                papLocal.setRightOfWaySize(mCC.valueConverter(wayleave)); //turns it into db value with no decimals
                newPapActivity.updatePapLocalItem(papLocal);

            }
        });

        spnDesignation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView tvPapDesignation = (TextView) view;
                papLocal.setPapStatus(tvPapDesignation.getText().toString());
                newPapActivity.updatePapLocalItem(papLocal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnUnits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView tvLandUnits = (TextView) view;
                papLocal.setLandUnits(tvLandUnits.getText().toString());
                newPapActivity.updatePapLocalItem(papLocal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cbIsResident.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (cbIsResident.isChecked()) {
                    papLocal.setIsResident(true);
                } else {
                    papLocal.setIsResident(false);
                }

                newPapActivity.updatePapLocalItem(papLocal);
            }
        });

        cbHasCrops.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (cbHasCrops.isChecked()) {
                    papLocal.setHasCrops(true);
                } else {
                    papLocal.setHasCrops(false);
                }

                newPapActivity.updatePapLocalItem(papLocal);
            }
        });

        cbHasImprovements.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (cbHasImprovements.isChecked()) {
                    papLocal.setHasImprovements(true);
                } else {
                    papLocal.setHasImprovements(false);
                }

                newPapActivity.updatePapLocalItem(papLocal);
            }
        });


    }
}
