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

import java.util.List;

/**
 * Created by senyer on 5/13/2016.
 */
public class StructuresListAdapter extends RecyclerView.Adapter<StructuresListAdapter.ViewHolder> {

    List<Structure> improvementsListToShow;
    Context context;
    NewPapActivity newPapActivity;
    PapLocal papLocal;


    public StructuresListAdapter(Context c, List<Structure> structureListToShow) {

        this.context = c;
        this.improvementsListToShow = structureListToShow;

        newPapActivity = (NewPapActivity) context;
        papLocal = newPapActivity.getPapLocalItem();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.structure_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Structure structure = improvementsListToShow.get(position);
        holder.tvStrName.setText(structure.getStructureName());
        holder.tvStrType.setText(structure.getStructureType());
        holder.tvArea.setText(structure.getArea() + " " + structure.getUnit());

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

        View dialogView = inflater.inflate(R.layout.dialog_new_structure, null);

        Toolbar dialogToolbar = (Toolbar) dialogView.findViewById(R.id.toolbar_dialog);
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


        final Structure selectedStructure = improvementsListToShow.get(position);

        dialogToolbar.setTitle("Edit Structure");

        tilStrName.getEditText().setText(selectedStructure.getStructureName());
        tilArea.getEditText().setText(selectedStructure.getArea());
        tilRoofType.getEditText().setText(selectedStructure.getRoof());
        tilWallsType.getEditText().setText(selectedStructure.getWalls());
        tilWindowsType.getEditText().setText(selectedStructure.getWindows());
        tilDoorsType.getEditText().setText(selectedStructure.getDoors());
        tilFloorType.getEditText().setText(selectedStructure.getFloor());
        tilValue.getEditText().setText(selectedStructure.getValue());

        String selectedUnit = selectedStructure.getUnit();

        String[] unitsInSpinner = context.getResources().getStringArray(R.array.land_size_units);

        int numberOfUnits = unitsInSpinner.length;

        for (int savedUnitSpinnerPosition = 0; savedUnitSpinnerPosition < numberOfUnits; savedUnitSpinnerPosition++) {

            if (selectedUnit.equals(unitsInSpinner[savedUnitSpinnerPosition])) {

                spinnerUnits.setSelection(savedUnitSpinnerPosition);

                break;
            }


        }

        String selectedStrType = selectedStructure.getStructureType();

        String[] strTypesInSpinner = context.getResources().getStringArray(R.array.structure_types);

        int numberOfStrTypes = strTypesInSpinner.length;

        for (int savedStrTypeSpinnerPos = 0; savedStrTypeSpinnerPos < numberOfStrTypes; savedStrTypeSpinnerPos++) {

            if (selectedStrType.equals(strTypesInSpinner[savedStrTypeSpinnerPos])) {

                spnStrType.setSelection(savedStrTypeSpinnerPos);

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

                if (tilStrName.getEditText().getText().toString().isEmpty()
                        || tilArea.getEditText().getText().toString().isEmpty()) {

                    if (tilStrName.getEditText().getText().toString().isEmpty()) {

                        tilStrName.getEditText().setError("Enter name");
                    }

                    if (tilArea.getEditText().getText().toString().isEmpty()) {

                        tilArea.getEditText().setError("Enter area");
                    }

                } else {



                    selectedStructure.setStructureName(tilStrName.getEditText().getText().toString());
                    selectedStructure.setStructureType(spnStrType.getSelectedItem().toString());
                    selectedStructure.setArea(tilArea.getEditText().getText().toString());
                    selectedStructure.setUnit(spinnerUnits.getSelectedItem().toString());
                    selectedStructure.setRoof(tilRoofType.getEditText().getText().toString());
                    selectedStructure.setFloor(tilFloorType.getEditText().getText().toString());
                    selectedStructure.setWalls(tilWallsType.getEditText().getText().toString());
                    selectedStructure.setWindows(tilWindowsType.getEditText().getText().toString());
                    selectedStructure.setDoors(tilDoorsType.getEditText().getText().toString());
                    selectedStructure.setValue(tilValue.getEditText().getText().toString());

                    improvementsListToShow.set(position, selectedStructure);


                    papLocal.setStructures(improvementsListToShow);

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

                papLocal.setStructures(improvementsListToShow);

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

        TextView tvStrName, tvStrType, tvArea;
        ItemClickListener clickListener;

        public ViewHolder(View itemView) {

            super(itemView);

            tvStrName = (TextView) itemView.findViewById(R.id.tv_category);
            tvStrType = (TextView) itemView.findViewById(R.id.tv_sub_category);
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
