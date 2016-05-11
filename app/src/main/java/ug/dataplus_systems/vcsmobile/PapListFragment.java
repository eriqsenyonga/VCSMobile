package ug.dataplus_systems.vcsmobile;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class PapListFragment extends Fragment {

    /*Has tabs with Live PapList and Local PapList in ViewPager*/

    TabLayout tabs;
    ViewPager viewPager;
    View rootView;

    public PapListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_pap_list, container, false);
        tabs = (TabLayout) rootView.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getChildFragmentManager(), getActivity(), 2, MyPagerAdapter.PAP_LIST_VIEWPAGER);
        viewPager.setAdapter(myPagerAdapter);

        tabs.setupWithViewPager(viewPager);

    }

}
