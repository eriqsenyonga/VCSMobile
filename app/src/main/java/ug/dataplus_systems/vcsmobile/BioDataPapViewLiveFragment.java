package ug.dataplus_systems.vcsmobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BioDataPapViewLiveFragment extends Fragment {


    Bundle papBundle;
    TextView tvBirthPlace;
    TextView tvDesignation;
    TextView tvDob;
    TextView tvHhid;
    TextView tvIsMarried;
    TextView tvIsResident;
    TextView tvOccupation;

    TextView tvPlotRef;
    TextView tvRefNo;
    TextView tvReligion;
    TextView tvSex;
    TextView tvTribe;
    View v;


    public BioDataPapViewLiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_bio_data_pap_view_live, container, false);
        tvHhid = ((TextView)this.v.findViewById(R.id.tv_papID));
        tvDob = ((TextView)this.v.findViewById(R.id.tv_entry_papDOB));
        tvBirthPlace = ((TextView)this.v.findViewById(R.id.tv_entry_birthPlace));
        tvIsMarried = ((TextView)this.v.findViewById(R.id.tv_entry_isMarried));

        tvPlotRef = ((TextView)this.v.findViewById(R.id.tv_entry_plotRef));
        tvRefNo = ((TextView)this.v.findViewById(R.id.tv_entry_refNo));
        tvDesignation = ((TextView)this.v.findViewById(R.id.tv_entry_designation));
        tvIsResident = ((TextView)this.v.findViewById(R.id.tv_entry_isResident));
        tvTribe = ((TextView) this.v.findViewById(R.id.tv_entry_tribe));
        tvOccupation = ((TextView)this.v.findViewById(R.id.tv_entry_occupation));
        tvReligion = ((TextView)this.v.findViewById(R.id.tv_entry_religion));
        tvSex = ((TextView)this.v.findViewById(R.id.tv_entry_sex));


        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        papBundle = getArguments();
        tvHhid.setText(papBundle.getString("hhid"));
        tvDob.setText(papBundle.getString("dateOfBirth"));
        tvSex.setText(papBundle.getString("sex"));
        tvBirthPlace.setText(papBundle.getString("birthPlace"));
        tvIsMarried.setText(papBundle.getString("isMarried"));
        tvPlotRef.setText(papBundle.getString("plotReference"));
    }
}
