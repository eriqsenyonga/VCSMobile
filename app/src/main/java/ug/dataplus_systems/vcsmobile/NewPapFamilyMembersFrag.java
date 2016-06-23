package ug.dataplus_systems.vcsmobile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by senyer on 6/23/2016.
 */
public class NewPapFamilyMembersFrag extends Fragment implements View.OnClickListener {

    RecyclerView rvFamilyList;
    TextView tvEmptyList;
    View v;
    NewPapActivity newPapActivity;
    PapLocal papLocal;
    List<FamilyMember> familyMembersHere;
    FamilyMembersListAdapter adapter;
    ConversionClass mCC;
    Calendar c;

    public NewPapFamilyMembersFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_new_pap_with_recyclerview, container, false);

        rvFamilyList = (RecyclerView) v.findViewById(R.id.recycler_view);
        tvEmptyList = (TextView) v.findViewById(R.id.tv_empty_list);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        rvFamilyList.hasFixedSize();
        rvFamilyList.setLayoutManager(new LinearLayoutManager(getActivity()));

        newPapActivity = (NewPapActivity) getActivity();
        papLocal = newPapActivity.getPapLocalItem();

        familyMembersHere = papLocal.getPapFamilyMembers();

        mCC = new ConversionClass(getActivity());
        c = Calendar.getInstance();

        newPapActivity.fab.show();

        newPapActivity.fab.setOnClickListener(this);

        showFamilyMembers();


    }

    private void showFamilyMembers() {

        adapter = new FamilyMembersListAdapter(getActivity(), this.familyMembersHere);
        rvFamilyList.setAdapter(this.adapter);
    }

    @Override
    public void onClick(View v) {

        if (v == newPapActivity.fab) {
            //if fab is clicked then show the dialog to add new family member

            LayoutInflater inflater = LayoutInflater.from(getActivity());

            View dialogView = inflater.inflate(R.layout.dialog_new_family_member, null);

            Toolbar dialogToolbar = (Toolbar) dialogView.findViewById(R.id.toolbar_dialog);

            final TextInputLayout tilFamilyMemberName = (TextInputLayout) dialogView.findViewById(R.id.til_family_member_name);
            final Spinner spnSex = (Spinner) dialogView.findViewById(R.id.spinner_sex);
            final TextInputLayout tilRelationType = (TextInputLayout) dialogView.findViewById(R.id.til_relation_type);
            final TextInputLayout tilPlaceOfBirth = (TextInputLayout) dialogView.findViewById(R.id.til_place_of_birth);
            final TextInputLayout tilTribe = (TextInputLayout) dialogView.findViewById(R.id.til_tribe);
            final TextInputLayout tilReligion = (TextInputLayout) dialogView.findViewById(R.id.til_religion);
            final Button dobButton = (Button) dialogView.findViewById(R.id.b_dob);

            Button save = (Button) dialogView.findViewById(R.id.b_save);
            Button cancel = (Button) dialogView.findViewById(R.id.b_cancel);


            dialogToolbar.setTitle("Add Family Member");

            //default date is today
            dobButton.setText(mCC.dateForDisplayFromCalendarInstance(c.getTime()));


            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setView(dialogView);

            final Dialog d = builder.create();

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


                                }
                            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                            c.get(Calendar.DAY_OF_MONTH));

                    datePicker.setTitle(dateTitle);
                    datePicker.setCancelable(false);
                    datePicker.show();
                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d.dismiss();
                }
            });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (tilFamilyMemberName.getEditText().getText().toString().isEmpty() ||
                            tilRelationType.getEditText().getText().toString().isEmpty()) {

                        if (tilFamilyMemberName.getEditText().getText().toString().isEmpty()) {

                            tilFamilyMemberName.getEditText().setError("Enter Name");

                        }

                        if (tilRelationType.getEditText().getText().toString().isEmpty()) {

                            tilRelationType.getEditText().setError("Enter Relation Type");
                        }


                    } else {

                        FamilyMember familyMember = new FamilyMember();

                        familyMember.setName(tilFamilyMemberName.getEditText().getText().toString());
                        familyMember.setPlaceOfBirth(tilPlaceOfBirth.getEditText().getText().toString());
                        familyMember.setSex(spnSex.getSelectedItem().toString());
                        familyMember.setRelationType(tilRelationType.getEditText().getText().toString());
                        familyMember.setTribe(tilTribe.getEditText().getText().toString());
                        familyMember.setReligion(tilReligion.getEditText().getText().toString());
                        familyMember.setDateOfBirth(dobButton.getText().toString());

                        familyMembersHere.add(familyMember);
                        papLocal.setPapFamilyMembers(familyMembersHere);

                        newPapActivity.updatePapLocalItem(papLocal);

                        showFamilyMembers();

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