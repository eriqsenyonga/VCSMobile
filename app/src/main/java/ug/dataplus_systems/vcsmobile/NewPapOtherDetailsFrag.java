package ug.dataplus_systems.vcsmobile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewPapOtherDetailsFrag extends Fragment {

    TextInputLayout tilTribe, tilOccupation, tilReligion;
    String tribe = "", occupation = "", religion = "";
    View v;
    NewPapActivity newPapActivity;
    PapLocal papLocal;


    public NewPapOtherDetailsFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_new_pap_other_details, container, false);

        tilTribe = (TextInputLayout) v.findViewById(R.id.til_tribe);
        tilOccupation = (TextInputLayout) v.findViewById(R.id.til_occupation);
        tilReligion = (TextInputLayout) v.findViewById(R.id.til_religion);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        newPapActivity = (NewPapActivity) getActivity();
        papLocal = newPapActivity.getPapLocalItem();


        tilTribe.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!tilTribe.getEditText().getText().toString().equals("")) {
                    tribe = tilTribe.getEditText().getText().toString();
                } else {
                    tribe = "";
                }
                papLocal.setTribe(tribe);
                newPapActivity.updatePapLocalItem(papLocal);

            }
        });

        tilOccupation.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!tilOccupation.getEditText().getText().toString().equals("")) {
                    occupation = tilOccupation.getEditText().getText().toString();
                } else {
                    occupation = "";
                }
                papLocal.setOccupation(occupation);
                newPapActivity.updatePapLocalItem(papLocal);

            }
        });

        tilReligion.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!tilReligion.getEditText().getText().toString().equals("")) {
                    religion = tilReligion.getEditText().getText().toString();
                } else {
                    religion = "";
                }
                papLocal.setReligion(religion);
                newPapActivity.updatePapLocalItem(papLocal);

            }
        });
    }
}
