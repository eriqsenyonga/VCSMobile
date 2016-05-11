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
public class ProjectsDetailsFragment extends Fragment {

    TabLayout tabs;
    ViewPager viewPager;
    View rootView;



    public ProjectsDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_projects_details, container, false);
        tabs = (TabLayout) rootView.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getChildFragmentManager(), getActivity(), 3, MyPagerAdapter.PROJECT_DETAILS_VIEWPAGER);
        viewPager.setAdapter(myPagerAdapter);

        tabs.setupWithViewPager(viewPager);
/*
        tabs.getTabAt(0).setIcon(R.drawable.ic_action_accept);
        tabs.getTabAt(1).setIcon(R.drawable.ic_add_white);
        tabs.getTabAt(2).setIcon(R.drawable.ic_close_white);
*/
    }
}
