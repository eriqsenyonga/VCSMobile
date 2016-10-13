package ug.dataplus_systems.vcsmobile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewPapPreviewFragment extends Fragment {

    NewPapActivity newPapActivity;

    TextView tvBirthPlace, tvEmail, tvPhoneNumber, tvOtherPhoneNumber;
    TextView tvDesignation;
    TextView tvDob;
    TextView tvHhid;
    TextView tvIsMarried;
    TextView tvIsResident;
    TextView tvOccupation;
    TextView tvName;
    TextView tvPlotRef;
    TextView tvRefNo;
    TextView tvReligion;
    TextView tvSex;
    TextView tvTribe;
    PapLocal papLocal;
    CardView idCard;
    CardView cardAddresses, cardFamilyMembers, cardCrops, cardImprovements, cardPhotos;
    RecyclerView rvAddresses, rvFamilyMembers, rvCrops, rvImprovements;
    ViewPager vpPhotos;
    PapImagesPagerAdapter papImagesPagerAdapter;
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

        cardAddresses = (CardView) v.findViewById(R.id.card_addresses);
        cardFamilyMembers = (CardView) v.findViewById(R.id.card_family_members);
        cardCrops = (CardView) v.findViewById(R.id.card_crops);
        cardImprovements = (CardView) v.findViewById(R.id.card_improvements);
        cardPhotos = (CardView) v.findViewById(R.id.card_photos);
        rvAddresses = (RecyclerView) v.findViewById(R.id.rv_addresses);
        rvFamilyMembers = (RecyclerView) v.findViewById(R.id.rv_family_members);
        rvCrops = (RecyclerView) v.findViewById(R.id.rv_crops);
        rvImprovements = (RecyclerView) v.findViewById(R.id.rv_improvements);
        vpPhotos = (ViewPager) v.findViewById(R.id.view_pager_photos);
        tvHhid = ((TextView) this.v.findViewById(R.id.tv_papID));
        tvDob = ((TextView) this.v.findViewById(R.id.tv_entry_papDOB));
        tvBirthPlace = ((TextView) this.v.findViewById(R.id.tv_entry_birthPlace));
        tvIsMarried = ((TextView) this.v.findViewById(R.id.tv_entry_isMarried));
        tvPlotRef = ((TextView) this.v.findViewById(R.id.tv_entry_plotRef));
        tvRefNo = ((TextView) this.v.findViewById(R.id.tv_entry_refNo));
        tvDesignation = ((TextView) this.v.findViewById(R.id.tv_entry_designation));
        tvIsResident = ((TextView) this.v.findViewById(R.id.tv_entry_isResident));
        tvTribe = ((TextView) this.v.findViewById(R.id.tv_entry_tribe));
        tvOccupation = ((TextView) this.v.findViewById(R.id.tv_entry_occupation));
        tvReligion = ((TextView) this.v.findViewById(R.id.tv_entry_religion));
        tvSex = ((TextView) this.v.findViewById(R.id.tv_entry_sex));
        idCard = (CardView) v.findViewById(R.id.card_id);
        tvName = (TextView) v.findViewById(R.id.tv_entry_papName);
        tvPhoneNumber = (TextView) v.findViewById(R.id.tv_entry_papPhone);
        tvOtherPhoneNumber = (TextView) v.findViewById(R.id.tv_entry_papOtherPhone);
        tvEmail = (TextView) v.findViewById(R.id.tv_entry_papEmail);


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
        tvName.setText(papLocal.getName());
        tvDob.setText(papLocal.getDateOfBirth());
        tvSex.setText(papLocal.getSex());
        tvReligion.setText(papLocal.getReligion());
        tvDesignation.setText(papLocal.getPapStatus());
        tvPlotRef.setText(papLocal.getPlotReference());
        tvPhoneNumber.setText(papLocal.getPhoneNumber());
        tvOtherPhoneNumber.setText(papLocal.getOtherPhoneNumber());
        tvEmail.setText(papLocal.getEmail());
        tvPhoneNumber.setText(papLocal.getPhoneNumber());
        tvOtherPhoneNumber.setText(papLocal.getOtherPhoneNumber());
        tvBirthPlace.setText(papLocal.getBirthPlace());
        tvTribe.setText(papLocal.getTribe());



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

        tvOccupation.setText(papLocal.getOccupation());

        if (papLocal.getPapAddresses().size() > 0) {
            cardAddresses.setVisibility(View.VISIBLE);
            setAddresses();
        } else {
            cardAddresses.setVisibility(View.GONE);
        }


        if (papLocal.getPapFamilyMembers().size() > 0) {
            cardFamilyMembers.setVisibility(View.VISIBLE);
            setFamilyMembers();
        } else {
            cardFamilyMembers.setVisibility(View.GONE);
        }


        if (papLocal.getCrops().size() > 0) {
            cardCrops.setVisibility(View.VISIBLE);
            setCrops();
        } else {
            cardCrops.setVisibility(View.GONE);
        }


        if (papLocal.getStructures().size() > 0) {
            cardImprovements.setVisibility(View.VISIBLE);
            setImprovements();
        } else {
            cardImprovements.setVisibility(View.GONE);
        }


        setPhotos();

    }

    private void setAddresses() {

        /* addresses are contained in a recyclerview
        * and therefore we shall set up the addresses recyclerview
        * and attach a simple adapter*/


        rvAddresses.hasFixedSize();
        rvAddresses.setLayoutManager(new LinearLayoutManager(getActivity()) {

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        OverviewRecyclerViewAdapter addressAdapter = new OverviewRecyclerViewAdapter(getActivity(),
                papLocal.getPapAddresses(),
                OverviewRecyclerViewAdapter.OVERVIEW_ADDRESSES_LIST);

        rvAddresses.setAdapter(addressAdapter);


    }

    private void setFamilyMembers() {

        rvFamilyMembers.hasFixedSize();
        rvFamilyMembers.setLayoutManager(new LinearLayoutManager(getActivity()) {

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        OverviewRecyclerViewAdapter familyAdapter = new OverviewRecyclerViewAdapter(getActivity(),
                papLocal.getPapFamilyMembers(),
                OverviewRecyclerViewAdapter.OVERVIEW_FAMILY_MEMBERS_LIST);

        rvFamilyMembers.setAdapter(familyAdapter);


    }

    private void setCrops() {

        rvCrops.hasFixedSize();
        rvCrops.setLayoutManager(new LinearLayoutManager(getActivity()) {

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        OverviewRecyclerViewAdapter cropsAdapter = new OverviewRecyclerViewAdapter(getActivity(),
                papLocal.getCrops(),
                OverviewRecyclerViewAdapter.OVERVIEW_CROPS_LIST);

        rvCrops.setAdapter(cropsAdapter);


    }

    private void setImprovements() {

        rvImprovements.hasFixedSize();
        rvImprovements.setLayoutManager(new LinearLayoutManager(getActivity()) {

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        OverviewRecyclerViewAdapter improvementsAdapter = new OverviewRecyclerViewAdapter(getActivity(),
                papLocal.getStructures(),
                OverviewRecyclerViewAdapter.OVERVIEW_IMPROVEMENTS_LIST);

        rvImprovements.setAdapter(improvementsAdapter);


    }

    private void setPhotos() {

        //TODO change this to retrieve images from papLocal object
        int[] papImages = {
                R.drawable.uuu,
                R.drawable.house,
                R.drawable.plantation,
                R.drawable.agric,
                R.drawable.prof_pic

        };


        PapImagesPagerAdapter papImagesPagerAdapter = new PapImagesPagerAdapter(getActivity(), papImages);

        vpPhotos.setAdapter(papImagesPagerAdapter);
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
