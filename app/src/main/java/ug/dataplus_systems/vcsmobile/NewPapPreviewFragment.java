package ug.dataplus_systems.vcsmobile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewPapPreviewFragment extends Fragment {

    NewPapActivity newPapActivity;

    TextView tvBirthPlace;
    TextView tvDesignation;
    TextView tvDob;
    TextView tvHhid;
    TextView tvIsMarried;
    TextView tvIsResident;
    TextView tvOccupation;
    TextView tvPhysicalAddress;
    TextView tvPlotRef;
    TextView tvRefNo;
    TextView tvReligion;
    TextView tvSex;
    TextView tvTribe;
    PapLocal papLocal;
    CardView idCard;
    View v;


    public NewPapPreviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_bio_data_pap_view_live, container, false);
        tvHhid = ((TextView) this.v.findViewById(R.id.tv_papID));
        tvDob = ((TextView) this.v.findViewById(R.id.tv_entry_papDOB));
        tvBirthPlace = ((TextView) this.v.findViewById(R.id.tv_entry_birthPlace));
        tvIsMarried = ((TextView) this.v.findViewById(R.id.tv_entry_isMarried));
        tvPhysicalAddress = ((TextView) this.v.findViewById(R.id.tv_entry_physicalAddress));
        tvPlotRef = ((TextView) this.v.findViewById(R.id.tv_entry_plotRef));
        tvRefNo = ((TextView) this.v.findViewById(R.id.tv_entry_refNo));
        tvDesignation = ((TextView) this.v.findViewById(R.id.tv_entry_designation));
        tvIsResident = ((TextView) this.v.findViewById(R.id.tv_entry_isResident));
        tvTribe = ((TextView) this.v.findViewById(R.id.tv_entry_trib));
        tvOccupation = ((TextView) this.v.findViewById(R.id.tv_entry_occupation));
        tvReligion = ((TextView) this.v.findViewById(R.id.tv_entry_religion));
        tvSex = ((TextView) this.v.findViewById(R.id.tv_entry_sex));
        idCard =  (CardView)v.findViewById(R.id.card_id);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        newPapActivity = (NewPapActivity) getActivity();
        newPapActivity.fab.hide();

        papLocal = newPapActivity.getPapLocalItem();

        idCard.setVisibility(View.GONE);

        populateFields();


    }

    private void populateFields() {

        tvDob.setText(papLocal.getDateOfBirth());
        tvSex.setText(papLocal.getSex());
        tvReligion.setText(papLocal.getReligion());
        tvDesignation.setText(papLocal.getDesignation());
        tvPlotRef.setText(papLocal.getPlotReference());

        if (papLocal.isMarried() == true) {

            tvIsMarried.setText("Yes");
        } else {

            tvIsMarried.setText("No");
        }

        if (papLocal.isResident() == true) {
            tvIsResident.setText("Yes");
        } else {
            tvIsResident.setText("No");
        }

        tvRefNo.setText(papLocal.getReferenceNumber());
        tvTribe.setText(papLocal.getTribe());
        tvPhysicalAddress.setText(papLocal.getPhysicalAddress());
        tvOccupation.setText(papLocal.getOccupation());

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
        populateFields();


    }

}
