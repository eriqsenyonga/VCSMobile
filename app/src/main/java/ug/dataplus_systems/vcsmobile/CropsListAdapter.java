package ug.dataplus_systems.vcsmobile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by senyer on 5/12/2016.
 */
public class CropsListAdapter extends RecyclerView.Adapter<CropsListAdapter.ViewHolder> {

    List<Crop> cropsListToShow;
    Context context;
    NewPapActivity newPapActivity;
    PapLocal papLocal;

    public CropsListAdapter(Context c, List<Crop> cropsList) {

        this.context = c;
        cropsListToShow = new ArrayList();
        cropsListToShow = cropsList;

        newPapActivity = (NewPapActivity) context;
        papLocal = newPapActivity.getPapLocalItem();

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.crops_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Crop crop = cropsListToShow.get(position);
        holder.tvCropName.setText(crop.getCropName());
        holder.tvQuantityUnits.setText(crop.getQuantity() + " " + crop.getUnit());

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //show menu with options View, Edit, and Delete for the crop

                showCropItemClickOptionsDialog(position);


            }
        });

    }

    private void showCropItemClickOptionsDialog(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(new String[]{"View", "Edit", "Delete"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0 || which == 1) {
                    //if the item clicked is View or Edit

                    openCropDialog(position);

                } else if (which == 2) {
                    //if delete is clicked

                    Toast.makeText(context, "Delete Crop Coming soon", Toast.LENGTH_LONG).show();

                    openConfirmDeleteDialog(position);
                }
            }
        });

        Dialog d = builder.create();
        d.show();

    }

    private void openConfirmDeleteDialog(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to delete this crop?");
        builder.setTitle("Delete Crop");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

                cropsListToShow.remove(position);

                papLocal.setCrops(cropsListToShow);

                newPapActivity.updatePapLocalItem(papLocal);

                notifyDataSetChanged();


            }
        });

        Dialog d = builder.create();
        d.show();

    }

    private void openCropDialog(final int position) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View dialogView = inflater.inflate(R.layout.dialog_new_crop, null);

        Toolbar dialogToolbar = (Toolbar) dialogView.findViewById(R.id.toolbar_dialog);
        final TextInputLayout tilCropName = (TextInputLayout) dialogView.findViewById(R.id.til_crop_name);
        final TextInputLayout tilCropDescription = (TextInputLayout) dialogView.findViewById(R.id.til_crop_description);
        final TextInputLayout tilQuantity = (TextInputLayout) dialogView.findViewById(R.id.til_crop_quantity);
        final Spinner spnUnits = (Spinner) dialogView.findViewById(R.id.spinner_crop_units);
        final Spinner spnCropType = (Spinner) dialogView.findViewById(R.id.spinner_crop_type);
        Button save = (Button) dialogView.findViewById(R.id.b_save_crop);
        Button cancel = (Button) dialogView.findViewById(R.id.b_cancel);

        final Crop selectedCrop = cropsListToShow.get(position);

        dialogToolbar.setTitle("Edit Crop");

        tilCropName.getEditText().setText(selectedCrop.getCropName());
        tilCropDescription.getEditText().setText(selectedCrop.getCropDescription());
        tilQuantity.getEditText().setText(selectedCrop.getQuantity());

        String selectedCropType = selectedCrop.getCropType();

        String[] cropTypesArray = context.getResources().getStringArray(R.array.crop_types);

        int numberCropTypes = cropTypesArray.length;


        for (int savedCropSpinnerPosition = 0; savedCropSpinnerPosition < numberCropTypes; savedCropSpinnerPosition++) {


            if (selectedCropType.equals(cropTypesArray[savedCropSpinnerPosition])) {


                spnCropType.setSelection(savedCropSpinnerPosition);

                break;
            }


        }

        String selectedUnit = selectedCrop.getUnit();

        String[] unitsInSpinner = context.getResources().getStringArray(R.array.land_size_units);

        int numberOfUnits = unitsInSpinner.length;

        for (int savedUnitSpinnerPosition = 0; savedUnitSpinnerPosition < numberOfUnits; savedUnitSpinnerPosition++) {

            if (selectedUnit.equals(unitsInSpinner[savedUnitSpinnerPosition])) {

                spnUnits.setSelection(savedUnitSpinnerPosition);

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

                if (tilCropName.getEditText().getText().toString().isEmpty() || tilQuantity.getEditText().getText().toString().isEmpty()) {

                    if (tilCropName.getEditText().getText().toString().isEmpty()) {
                        tilCropName.getEditText().setError("Enter crop name");
                    }

                    if (tilQuantity.getEditText().getText().toString().isEmpty()) {

                        tilQuantity.getEditText().setError("Enter quantity");
                    }

                } else {


                    selectedCrop.setCropName(tilCropName.getEditText().getText().toString());
                    selectedCrop.setCropDescription(tilCropDescription.getEditText().getText().toString());
                    selectedCrop.setCropType(spnCropType.getSelectedItem().toString());
                    selectedCrop.setQuantity(tilQuantity.getEditText().getText().toString());
                    selectedCrop.setUnit(spnUnits.getSelectedItem().toString());

                    cropsListToShow.set(position, selectedCrop);


                    papLocal.setCrops(cropsListToShow);

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

        if (this.cropsListToShow != null) {
            return this.cropsListToShow.size();
        }
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvCropName;
        TextView tvQuantityUnits;
        ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            tvCropName = (TextView) itemView.findViewById(R.id.tv_crop_name);
            tvQuantityUnits = (TextView) itemView.findViewById(R.id.tv_crop_quantity_units);


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
