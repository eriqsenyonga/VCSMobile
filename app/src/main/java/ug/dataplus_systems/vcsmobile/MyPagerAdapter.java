package ug.dataplus_systems.vcsmobile;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Eriq on 1/6/2016.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    public static int PROJECT_DETAILS_VIEWPAGER = 1;
    public static int PAP_LIST_VIEWPAGER = 2;
    public static int PAP_VIEW_LIVE_VIEWPAGER = 3;

    Bundle b = null;
    Context context;
    int whichViewPager;
    int numberOfTabs;

    public MyPagerAdapter(FragmentManager fm, Context context, int numberOfTabs, int whichViewPager) {
        super(fm);
        this.context = context;

        this.whichViewPager = whichViewPager;
        this.numberOfTabs = numberOfTabs;

    }

    public MyPagerAdapter(FragmentManager fm, Context context, int numberOfTabs, int whichViewPager, Bundle bundle)
    {
        super(fm);
        this.context = context;
        this.whichViewPager = whichViewPager;
        this.numberOfTabs = numberOfTabs;
        this.b = bundle;
    }

    @Override
    public Fragment getItem(int position) {

        if (whichViewPager == PROJECT_DETAILS_VIEWPAGER) {
            Fragment projectInfoFragment = new ProjectInfoFragment();

            Fragment projectStakeHoldersFragment = new ProjectStakeHoldersFragment();

            Fragment projectDisputesFragment = new ProjectDisputesFragment();



            if (position == 0) {

                return projectInfoFragment;
            }

            if (position == 1) {

                return projectStakeHoldersFragment;
            }

            if (position == 2) {

                return projectDisputesFragment;
            }
        }

        if (whichViewPager == PAP_LIST_VIEWPAGER) {
            Fragment papListLive = new PapListLive();

            Fragment papListPending = new PapListPending();

            if (position == 0) {

                return papListLive;
            }

            if (position == 1) {

                return papListPending;
            }

        }

        if (whichViewPager == PAP_VIEW_LIVE_VIEWPAGER) {
            Fragment bioDataPapViewLiveFragment = new BioDataPapViewLiveFragment();

            if (this.b != null) {
                bioDataPapViewLiveFragment.setArguments(this.b);
            }

            Fragment valuationPapViewLiveFragment = new ValuationPapViewLiveFragment();

            if (position == 0) {

                return  bioDataPapViewLiveFragment;
            }

            if (position == 1) {

                return valuationPapViewLiveFragment;
            }
        }

        return null;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // TODO Auto-generated method stub

        if (whichViewPager == PROJECT_DETAILS_VIEWPAGER) {
            if (position == 0) {
                String pendingTabTitle = "Info";
                return pendingTabTitle;
            }

            if (position == 1) {

                //   String finishedTabTitle = context.getResources().getString(
                //         R.string.finished);
                return "Stakeholders";
            }

            if (position == 2) {

                //   String finishedTabTitle = context.getResources().getString(
                //         R.string.finished);
                return "Disputes";
            }
        }

        if (whichViewPager == PAP_LIST_VIEWPAGER) {
            if (position == 0) {
                String liveListTabTitle = "Live";
                return liveListTabTitle;
            }

            if (position == 1) {

                //   String finishedTabTitle = context.getResources().getString(
                //         R.string.finished);
                return "Pending";
            }
        }

        if (whichViewPager == PAP_VIEW_LIVE_VIEWPAGER) {
            if (position == 0) {

                return "Bio Data";
            }

            if (position == 1) {

                //   String finishedTabTitle = context.getResources().getString(
                //         R.string.finished);
                return "Valuation";
            }
        }

        return super.getPageTitle(position);
    }
}
