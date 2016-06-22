package ug.dataplus_systems.vcsmobile;

import android.app.AlertDialog;
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
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * Created by senyer on 6/22/2016.
 */
public class NewPapAddressesFrag extends Fragment implements View.OnClickListener {

    RecyclerView rvAddressList;
    TextView tvEmptyList;
    View v;
    NewPapActivity newPapActivity;
    PapLocal papLocal;
    List<Address> addressesHere;
    AddressListAdapter adapter;

    public NewPapAddressesFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_new_pap_with_recyclerview, container, false);

        rvAddressList = (RecyclerView) v.findViewById(R.id.recycler_view);
        tvEmptyList = (TextView) v.findViewById(R.id.tv_empty_list);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        rvAddressList.hasFixedSize();
        rvAddressList.setLayoutManager(new LinearLayoutManager(getActivity()));

        newPapActivity = (NewPapActivity) getActivity();
        papLocal = newPapActivity.getPapLocalItem();

        addressesHere = papLocal.getPapAddresses();


        newPapActivity.fab.show();

        newPapActivity.fab.setOnClickListener(this);

        showAddresses();


    }

    private void showAddresses() {

        adapter = new AddressListAdapter(getActivity(), this.addressesHere);
        rvAddressList.setAdapter(this.adapter);

    }

    @Override
    public void onClick(View v) {

        if (v == newPapActivity.fab) {
            //if fab is clicked then show the dialog to add new crop

            LayoutInflater inflater = LayoutInflater.from(getActivity());

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


            dialogToolbar.setTitle("New PAP Address");


            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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

                        Address address = new Address();

                        address.setPlotNoRoad(tilPlotNoRoad.getEditText().getText().toString());

                        address.setDistrict(spnDistrict.getSelectedItem().toString());
                        address.setCounty(spnCounty.getSelectedItem().toString());
                        address.setSubCounty(spnSubCounty.getSelectedItem().toString());
                        address.setVillage(spnVillage.getSelectedItem().toString());

                        if (cbIsMainResidence.isChecked()) {
                            address.setIsMainAddress(true);
                        } else {
                            address.setIsMainAddress(false);
                        }


                        addressesHere.add(address);
                        papLocal.setPapAddresses(addressesHere);

                        newPapActivity.updatePapLocalItem(papLocal);

                        showAddresses();

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