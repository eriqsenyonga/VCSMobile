package ug.dataplus_systems.vcsmobile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by senyer on 6/23/2016.
 */
public class FamilyMembersListAdapter extends RecyclerView.Adapter<FamilyMembersListAdapter.ViewHolder> {

    List<FamilyMember> familyMembersListToShow;
    Context context;
    NewPapActivity newPapActivity;
    PapLocal papLocal;
    ConversionClass mCC;
    Calendar c;

    public FamilyMembersListAdapter(Context c, List<FamilyMember> familyMembersList) {

        this.context = c;
        familyMembersListToShow = new ArrayList();
        familyMembersListToShow = familyMembersList;

        newPapActivity = (NewPapActivity) context;
        papLocal = newPapActivity.getPapLocalItem();

        mCC = new ConversionClass(context);
        this.c = Calendar.getInstance();

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.family_member_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        FamilyMember familyMember = familyMembersListToShow.get(position);
        holder.tvFamilyMemberName.setText(familyMember.getName());
        holder.tvRelationType.setText(familyMember.getRelationType());


        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //show menu with options View, Edit, and Delete for the crop

                showFamilyMemberItemClickOptionsDialog(position);


            }
        });

    }

    private void showFamilyMemberItemClickOptionsDialog(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(new String[]{"View", "Edit", "Delete"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0 || which == 1) {
                    //if the item clicked is View or Edit

                    openFamilyMemberDialog(position);

                } else if (which == 2) {
                    //if delete is clicked


                    openConfirmDeleteDialog(position);
                }
            }
        });

        Dialog d = builder.create();
        d.show();

    }

    private void openConfirmDeleteDialog(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to delete this family member?");
        builder.setTitle("Delete Family Member");

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                familyMembersListToShow.remove(position);

                papLocal.setPapFamilyMembers(familyMembersListToShow);

                newPapActivity.updatePapLocalItem(papLocal);

                notifyDataSetChanged();


            }
        });

        Dialog d = builder.create();
        d.show();

    }

    private void openFamilyMemberDialog(final int position) {

        LayoutInflater inflater = LayoutInflater.from(context);

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


        dialogToolbar.setTitle("Edit Family Member");

        final FamilyMember selectedFamilyMember = familyMembersListToShow.get(position);

        //saved family member name
        tilFamilyMemberName.getEditText().setText(selectedFamilyMember.getName());

        //saved sex
        String selectedSex = selectedFamilyMember.getSex();

        String[] sexArray = context.getResources().getStringArray(R.array.sex);

        int numberOfSex = sexArray.length;


        for (int savedSexSpinnerPosition = 0; savedSexSpinnerPosition < numberOfSex; savedSexSpinnerPosition++) {


            if (selectedSex.equals(sexArray[savedSexSpinnerPosition])) {


                spnSex.setSelection(savedSexSpinnerPosition);

                break;
            }


        }

        //saved relation type
        tilRelationType.getEditText().setText(selectedFamilyMember.getRelationType());

        //saved tribe
        tilTribe.getEditText().setText(selectedFamilyMember.getTribe());

        //saved religion
        tilReligion.getEditText().setText(selectedFamilyMember.getReligion());

        //saved family member name
        tilPlaceOfBirth.getEditText().setText(selectedFamilyMember.getPlaceOfBirth());


        //saved date of birth
        dobButton.setText(selectedFamilyMember.getDateOfBirth());

        c.setTime(mCC.returnDateObjectFromDisplayDateString(dobButton.getText().toString()));


        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(dialogView);

        final Dialog d = builder.create();

        dobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateTitle = "Date of Birth";
                DatePickerDialog datePicker = new DatePickerDialog(
                        context,
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


                    selectedFamilyMember.setName(tilFamilyMemberName.getEditText().getText().toString());
                    selectedFamilyMember.setPlaceOfBirth(tilPlaceOfBirth.getEditText().getText().toString());
                    selectedFamilyMember.setSex(spnSex.getSelectedItem().toString());
                    selectedFamilyMember.setRelationType(tilRelationType.getEditText().getText().toString());
                    selectedFamilyMember.setTribe(tilTribe.getEditText().getText().toString());
                    selectedFamilyMember.setReligion(tilReligion.getEditText().getText().toString());
                    selectedFamilyMember.setDateOfBirth(dobButton.getText().toString());

                    familyMembersListToShow.set(position, selectedFamilyMember);
                    papLocal.setPapFamilyMembers(familyMembersListToShow);

                    newPapActivity.updatePapLocalItem(papLocal);

                    notifyDataSetChanged();

                    d.dismiss();


                }


            }
        });


        d.show();

    }


    @Override
    public int getItemCount() {

        if (this.familyMembersListToShow != null) {
            return this.familyMembersListToShow.size();
        }
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvFamilyMemberName;
        TextView tvRelationType;
        ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            tvFamilyMemberName = (TextView) itemView.findViewById(R.id.tv_family_member_name);
            tvRelationType = (TextView) itemView.findViewById(R.id.tv_relation_type);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            this.clickListener.onClick(v, getAdapterPosition(), false);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }
    }

}
