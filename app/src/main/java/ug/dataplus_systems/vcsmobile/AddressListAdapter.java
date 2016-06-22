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
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by senyer on 6/22/2016.
 */
public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ViewHolder> {

    List<Address> addressListToShow;
    Context context;
    NewPapActivity newPapActivity;
    PapLocal papLocal;

    public AddressListAdapter(Context c, List<Address> addressList) {

        this.context = c;
        addressListToShow = new ArrayList();
        addressListToShow = addressList;

        newPapActivity = (NewPapActivity) context;
        papLocal = newPapActivity.getPapLocalItem();

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Address address = addressListToShow.get(position);
        holder.tvPlotNoRoad.setText(address.getPlotNoRoad());
        holder.tvVillage.setText(address.getVillage());
        holder.tvDistrict.setText(address.getDistrict());

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //show menu with options View, Edit, and Delete for the crop

                showAddressItemClickOptionsDialog(position);


            }
        });

    }

    private void showAddressItemClickOptionsDialog(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(new String[]{"View", "Edit", "Delete"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0 || which == 1) {
                    //if the item clicked is View or Edit

                    openAddressDialog(position);

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
        builder.setMessage("Are you sure you want to delete this address?");
        builder.setTitle("Delete Address");

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                addressListToShow.remove(position);

                papLocal.setPapAddresses(addressListToShow);

                newPapActivity.updatePapLocalItem(papLocal);

                notifyDataSetChanged();


            }
        });

        Dialog d = builder.create();
        d.show();

    }

    private void openAddressDialog(final int position) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View dialogView = inflater.inflate(R.layout.dialog_new_address, null);

        Toolbar dialogToolbar = (Toolbar) dialogView.findViewById(R.id.toolbar_dialog);

        final TextInputLayout tilPlotNoRoad = (TextInputLayout) dialogView.findViewById(R.id.til_plot_no_road);
        final Spinner spnDistrict = (Spinner) dialogView.findViewById(R.id.spinner_district);
        final Spinner spnCounty = (Spinner) dialogView.findViewById(R.id.spinner_county);
        final Spinner spnSubCounty = (Spinner) dialogView.findViewById(R.id.spinner_sub_county);
        final Spinner spnVillage = (Spinner) dialogView.findViewById(R.id.spinner_village);
        final CheckBox cbIsMainResidence = (CheckBox) dialogView.findViewById(R.id.cb_is_main_residence);

        Button save = (Button) dialogView.findViewById(R.id.b_save);
        Button cancel = (Button) dialogView.findViewById(R.id.b_cancel);

        final Address selectedAddress = addressListToShow.get(position);

        dialogToolbar.setTitle("Edit PAP Address");

        tilPlotNoRoad.getEditText().setText(selectedAddress.getPlotNoRoad());


        String selectedDistrict = selectedAddress.getDistrict();

        String[] districtsArray = context.getResources().getStringArray(R.array.districts);

        int numberOfDistricts = districtsArray.length;


        for (int savedDistrictSpinnerPosition = 0; savedDistrictSpinnerPosition < numberOfDistricts; savedDistrictSpinnerPosition++) {


            if (selectedDistrict.equals(districtsArray[savedDistrictSpinnerPosition])) {


                spnDistrict.setSelection(savedDistrictSpinnerPosition);

                break;
            }


        }


        String selectedCounty = selectedAddress.getCounty();

        String[] countiesArray = context.getResources().getStringArray(R.array.counties);

        int numberOfCounties = countiesArray.length;


        for (int savedCountySpinnerPosition = 0; savedCountySpinnerPosition < numberOfCounties; savedCountySpinnerPosition++) {


            if (selectedCounty.equals(countiesArray[savedCountySpinnerPosition])) {


                spnCounty.setSelection(savedCountySpinnerPosition);

                break;
            }


        }

        String selectedSubCounty = selectedAddress.getSubCounty();

        String[] subCountiesArray = context.getResources().getStringArray(R.array.sub_counties);

        int numberOfSubCounties = subCountiesArray.length;


        for (int savedSubCountySpinnerPosition = 0; savedSubCountySpinnerPosition < numberOfSubCounties; savedSubCountySpinnerPosition++) {


            if (selectedSubCounty.equals(subCountiesArray[savedSubCountySpinnerPosition])) {


                spnSubCounty.setSelection(savedSubCountySpinnerPosition);

                break;
            }


        }

        String selectedVillage = selectedAddress.getVillage();

        String[] villagesArray = context.getResources().getStringArray(R.array.villages);

        int numberOfVillages = villagesArray.length;


        for (int savedVillagesSpinnerPosition = 0; savedVillagesSpinnerPosition < numberOfVillages; savedVillagesSpinnerPosition++) {


            if (selectedVillage.equals(villagesArray[savedVillagesSpinnerPosition])) {


                spnVillage.setSelection(savedVillagesSpinnerPosition);

                break;
            }


        }

        if (selectedAddress.isMainAddress()) {
            cbIsMainResidence.setChecked(true);
        } else {
            cbIsMainResidence.setChecked(false);
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

                if (tilPlotNoRoad.getEditText().getText().toString().isEmpty()) {


                    tilPlotNoRoad.getEditText().setError("Field can't be empty");


                } else {


                    selectedAddress.setPlotNoRoad(tilPlotNoRoad.getEditText().getText().toString());

                    selectedAddress.setDistrict(spnDistrict.getSelectedItem().toString());
                    selectedAddress.setCounty(spnCounty.getSelectedItem().toString());
                    selectedAddress.setSubCounty(spnSubCounty.getSelectedItem().toString());
                    selectedAddress.setVillage(spnVillage.getSelectedItem().toString());

                    if (cbIsMainResidence.isChecked()) {
                        selectedAddress.setIsMainAddress(true);
                    } else {
                        selectedAddress.setIsMainAddress(false);
                    }


                    addressListToShow.set(position, selectedAddress);


                    papLocal.setPapAddresses(addressListToShow);

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

        if (this.addressListToShow != null) {
            return this.addressListToShow.size();
        }
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvPlotNoRoad, tvVillage, tvDistrict;

        ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            tvPlotNoRoad = (TextView) itemView.findViewById(R.id.tv_plot_no_road);
            tvVillage = (TextView) itemView.findViewById(R.id.tv_village);
            tvDistrict = (TextView) itemView.findViewById(R.id.tv_district);

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
