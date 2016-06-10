package ug.dataplus_systems.vcsmobile;

import android.app.AlertDialog;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by senyer on 5/13/2016.
 */
public class ImprovementsListAdapter extends RecyclerView.Adapter<ImprovementsListAdapter.ViewHolder> {

    List<Improvement> improvementsListToShow;
    Context context;
    NewPapActivity newPapActivity;
    PapLocal papLocal;


    public ImprovementsListAdapter(Context c, List<Improvement> improvementListToShow) {

        this.context = c;
        this.improvementsListToShow = improvementListToShow;

        newPapActivity = (NewPapActivity) context;
        papLocal = newPapActivity.getPapLocalItem();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.improvement_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Improvement improvement = improvementsListToShow.get(position);
        holder.tvCategory.setText(improvement.getCategory());
        holder.tvSubCategory.setText(improvement.getSubCategory());
        holder.tvArea.setText(improvement.getArea() + " " + improvement.getUnit());

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //show menu with options View, Edit, and Delete for the crop

                showImprovementItemClickOptionsDialog(position);
            }
        });

    }

    private void showImprovementItemClickOptionsDialog(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(new String[]{"View", "Edit", "Delete"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0 || which == 1) {
                    //if the item clicked is View or Edit

                    openImprovementDialog(position);

                } else if (which == 2) {
                    //if delete is clicked


                    openConfirmDeleteDialog(position);
                }
            }
        });

        Dialog d = builder.create();
        d.show();
    }

    private void openImprovementDialog(final int position) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View dialogView = inflater.inflate(R.layout.dialog_new_improvement, null);

        Toolbar dialogToolbar = (Toolbar) dialogView.findViewById(R.id.toolbar_dialog);
        final TextInputLayout tilCategory = (TextInputLayout) dialogView.findViewById(R.id.til_improvement_name);
        final Spinner spinnerSubCategory = (Spinner) dialogView.findViewById(R.id.spinner_improvement_sub_category);
        final TextInputLayout tilArea = (TextInputLayout) dialogView.findViewById(R.id.til_improvement_area);
        final Spinner spinnerUnits = (Spinner) dialogView.findViewById(R.id.spinner_improvement_area_units);

        final TextInputLayout tilRoofType = (TextInputLayout) dialogView.findViewById(R.id.til_roof_type);
        final TextInputLayout tilWallsType = (TextInputLayout) dialogView.findViewById(R.id.til_walls_type);
        final TextInputLayout tilWindowsType = (TextInputLayout) dialogView.findViewById(R.id.til_windows_type);
        final TextInputLayout tilDoorsType = (TextInputLayout) dialogView.findViewById(R.id.til_doors_type);
        final TextInputLayout tilFloorType = (TextInputLayout) dialogView.findViewById(R.id.til_floor_type);
        Button save = (Button) dialogView.findViewById(R.id.b_save_improvement);
        Button cancel = (Button) dialogView.findViewById(R.id.b_cancel);


        final Improvement selectedImprovement = improvementsListToShow.get(position);

        dialogToolbar.setTitle("Edit Improvement");

        tilCategory.getEditText().setText(selectedImprovement.getCategory());
        tilArea.getEditText().setText(selectedImprovement.getArea());
        tilRoofType.getEditText().setText(selectedImprovement.getRoof());
        tilWallsType.getEditText().setText(selectedImprovement.getWalls());
        tilWindowsType.getEditText().setText(selectedImprovement.getWindows());
        tilDoorsType.getEditText().setText(selectedImprovement.getDoors());
        tilFloorType.getEditText().setText(selectedImprovement.getFloor());

        String selectedUnit = selectedImprovement.getUnit();

        String[] unitsInSpinner = context.getResources().getStringArray(R.array.land_size_units);

        int numberOfUnits = unitsInSpinner.length;

        for (int savedUnitSpinnerPosition = 0; savedUnitSpinnerPosition < numberOfUnits; savedUnitSpinnerPosition++) {

            if (selectedUnit.equals(unitsInSpinner[savedUnitSpinnerPosition])) {

                spinnerUnits.setSelection(savedUnitSpinnerPosition);

                break;
            }


        }

        String selectedSubCategory = selectedImprovement.getSubCategory();

        String[] subCategoriesInSpinner = context.getResources().getStringArray(R.array.structure_types);

        int numberOfSubCategories = subCategoriesInSpinner.length;

        for (int savedSubCategorySpinnerPosition = 0; savedSubCategorySpinnerPosition < numberOfSubCategories; savedSubCategorySpinnerPosition++) {

            if (selectedSubCategory.equals(subCategoriesInSpinner[savedSubCategorySpinnerPosition])) {

                spinnerSubCategory.setSelection(savedSubCategorySpinnerPosition);

                break;
            }


        }


        AlertDialog.Builder builder = new AlertDialog.Builder(context);

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



                    selectedImprovement.setCategory(tilCategory.getEditText().getText().toString());
                    selectedImprovement.setSubCategory(spinnerSubCategory.getSelectedItem().toString());
                    selectedImprovement.setArea(tilArea.getEditText().getText().toString());
                    selectedImprovement.setUnit(spinnerUnits.getSelectedItem().toString());
                    selectedImprovement.setRoof(tilRoofType.getEditText().getText().toString());
                    selectedImprovement.setFloor(tilFloorType.getEditText().getText().toString());
                    selectedImprovement.setWalls(tilWallsType.getEditText().getText().toString());
                    selectedImprovement.setWindows(tilWindowsType.getEditText().getText().toString());
                    selectedImprovement.setDoors(tilDoorsType.getEditText().getText().toString());

                    improvementsListToShow.set(position, selectedImprovement);


                    papLocal.setImprovements(improvementsListToShow);

                    newPapActivity.updatePapLocalItem(papLocal);

                    notifyDataSetChanged();

                    d.dismiss();

                }


            }
        });

        d.show();


    }

    private void openConfirmDeleteDialog(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to delete this crop?");
        builder.setTitle("Delete Crop");

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                improvementsListToShow.remove(position);

                papLocal.setImprovements(improvementsListToShow);

                newPapActivity.updatePapLocalItem(papLocal);

                notifyDataSetChanged();


            }
        });

        Dialog d = builder.create();
        d.show();

    }

    @Override
    public int getItemCount() {
        if (this.improvementsListToShow != null) {
            return this.improvementsListToShow.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvCategory, tvSubCategory, tvArea;
        ItemClickListener clickListener;

        public ViewHolder(View itemView) {

            super(itemView);

            tvCategory = (TextView) itemView.findViewById(R.id.tv_category);
            tvSubCategory = (TextView) itemView.findViewById(R.id.tv_sub_category);
            tvArea = (TextView) itemView.findViewById(R.id.tv_area);


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
