package ug.dataplus_systems.vcsmobile;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewPapBasicInfoFrag extends Fragment {


    Calendar c;
    CheckBox cbIsMarried;
    Button dobButton;
    String firstName = "", physicalAddress = "", birthPlace = "";
    ConversionClass mCC;
    NewPapActivity newPapActivity;
    String otherNames = "";
    PapLocal papLocal;
    View rootView;
    Spinner spnPapType;
    Spinner spnSex;
    String surName = "";
    TextInputLayout tilFirstName;
    TextInputLayout tilOtherNames;

    TextInputLayout tilSurname;
    TextInputLayout tilBirthPlace;
    TextInputLayout tilTribe, tilOccupation, tilReligion, tilPhoneNumber, tilOtherPhoneNumber, tilEmail;
    String tribe = "", occupation = "", religion = "", email = "", phoneNumber = "", otherPhoneNumber = "";

    public NewPapBasicInfoFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_new_pap_basic_info, container, false);
        dobButton = (Button) rootView.findViewById(R.id.b_dob);

        tilFirstName = (TextInputLayout) rootView.findViewById(R.id.til_firstname);
        tilSurname = (TextInputLayout) rootView.findViewById(R.id.til_surname);
        tilOtherNames = (TextInputLayout) rootView.findViewById(R.id.til_othernames);
        tilBirthPlace = (TextInputLayout) rootView.findViewById(R.id.til_birth_place);

        tilPhoneNumber = (TextInputLayout) rootView.findViewById(R.id.til_phone_number);
        tilOtherPhoneNumber = (TextInputLayout) rootView.findViewById(R.id.til_other_phone_number);
        tilEmail = (TextInputLayout) rootView.findViewById(R.id.til_email);

        spnSex = (Spinner) rootView.findViewById(R.id.spinner_sex);
        spnPapType = (Spinner) rootView.findViewById(R.id.spinner_pap_type);
        tilTribe = (TextInputLayout) rootView.findViewById(R.id.til_tribe);
        tilOccupation = (TextInputLayout) rootView.findViewById(R.id.til_occupation);
        tilReligion = (TextInputLayout) rootView.findViewById(R.id.til_religion);
        cbIsMarried = (CheckBox) rootView.findViewById(R.id.cb_is_married);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mCC = new ConversionClass(getActivity());
        newPapActivity = ((NewPapActivity) getActivity());
        papLocal = newPapActivity.getPapLocalItem();

        c = Calendar.getInstance();

        tilFirstName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!tilFirstName.getEditText().getText().toString().equals("")) {
                    firstName = tilFirstName.getEditText().getText().toString();
                } else {
                    firstName = "";
                }
                papLocal.setName(getFullName());
                newPapActivity.updatePapLocalItem(papLocal);

            }
        });

        tilSurname.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!tilSurname.getEditText().getText().toString().equals("")) {
                    surName = tilSurname.getEditText().getText().toString();
                } else {
                    surName = "";
                }
                papLocal.setName(getFullName());
                newPapActivity.updatePapLocalItem(papLocal);

            }
        });

        tilOtherNames.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!tilOtherNames.getEditText().getText().toString().equals("")) {
                    otherNames = tilOtherNames.getEditText().getText().toString();
                } else {
                    otherNames = "";
                }
                papLocal.setName(getFullName());
                newPapActivity.updatePapLocalItem(papLocal);

            }
        });


        tilBirthPlace.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!tilBirthPlace.getEditText().getText().toString().equals("")) {
                    birthPlace = tilBirthPlace.getEditText().getText().toString();
                } else {
                    birthPlace = "";
                }
                papLocal.setBirthPlace(birthPlace);
                newPapActivity.updatePapLocalItem(papLocal);

            }
        });

        dobButton.setText(mCC.dateForDisplayFromCalendarInstance(c.getTime()));

        //   dateButton.setText(buttonTextDate);

       papLocal.setDateOfBirth(dobButton.getText().toString()); //to set the date just incase it is not clicked

        dobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateTitle = "Date of Birth";
                DatePickerDialog datePicker = new DatePickerDialog(
                        getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view,
                                                  int selectedYear, int selectedMonth,
                                                  int selectedDay) {


                                String dateSetString = selectedYear + "-"
                                        + (selectedMonth + 1) + "-" + selectedDay;

                                String displayDate = mCC
                                        .dateForDisplay(dateSetString);

                                dobButton.setText(displayDate);

                                papLocal.setDateOfBirth(displayDate);

                                newPapActivity.updatePapLocalItem(papLocal);

                            }
                        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH));

                datePicker.setTitle(dateTitle);
                datePicker.setCancelable(false);
                datePicker.show();
            }
        });


        spnSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                papLocal.setSex(spnSex.getSelectedItem().toString());


                newPapActivity.updatePapLocalItem(papLocal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnPapType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView tvPapType = (TextView) view;
                papLocal.setPapType(tvPapType.getText().toString());
                newPapActivity.updatePapLocalItem(papLocal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

        tilPhoneNumber.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!tilPhoneNumber.getEditText().getText().toString().equals("")) {
                    phoneNumber = tilPhoneNumber.getEditText().getText().toString();
                } else {
                    phoneNumber = "";
                }
                papLocal.setPhoneNumber(phoneNumber);
                newPapActivity.updatePapLocalItem(papLocal);

            }
        });

        tilOtherPhoneNumber.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!tilOtherPhoneNumber.getEditText().getText().toString().equals("")) {
                    otherPhoneNumber = tilOtherPhoneNumber.getEditText().getText().toString();
                } else {
                    otherPhoneNumber = "";
                }
                papLocal.setOtherPhoneNumber(otherPhoneNumber);
                newPapActivity.updatePapLocalItem(papLocal);

            }
        });

        tilEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!tilEmail.getEditText().getText().toString().equals("")) {
                    email = tilEmail.getEditText().getText().toString();
                } else {
                    email = "";
                }
                papLocal.setEmail(email);
                newPapActivity.updatePapLocalItem(papLocal);

            }
        });

        cbIsMarried.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (cbIsMarried.isChecked()) {
                    papLocal.setIsMarried(true);
                } else {
                    papLocal.setIsMarried(false);
                }

                newPapActivity.updatePapLocalItem(papLocal);
            }
        });

        newPapActivity.updatePapLocalItem(papLocal);

    }

    private String getFullName() {
        return (this.firstName + " " + this.surName + " " + this.otherNames).trim();
    }
}
