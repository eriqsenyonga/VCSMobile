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

    public MyPagerAdapter(FragmentManager fm, Context context, int numberOfTabs, int whichViewPager, Bundle bundle) {
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

            Fragment projectClientsFragment = new ProjectClientsFragment();

            Fragment projectBudgetFragment = new ProjectBudgetFragment();

            Fragment projectExpensesFragment = new ProjectExpensesFragment();

            Fragment projectPersonnelFragment = new ProjectPersonnelFragment();

            Fragment projectSectionsFragment = new ProjectSectionsFragment();


            if (position == 0) {

                return projectInfoFragment;
            }

            if (position == 1) {

                return projectClientsFragment;
            }

            if (position == 2) {

                return projectBudgetFragment;
            }

            if (position == 3) {

                return projectExpensesFragment;
            }

            if (position == 4) {

                return projectPersonnelFragment;
            }

            if (position == 5) {

                return projectSectionsFragment;
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

                return bioDataPapViewLiveFragment;
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


        if (whichViewPager == PROJECT_DETAILS_VIEWPAGER) {
            if (position == 0) {
                String pendingTabTitle = "Info";
                return pendingTabTitle;
            }

            if (position == 1) {

                //   String finishedTabTitle = context.getResources().getString(
                //         R.string.finished);
                return "Clients";
            }

            if (position == 2) {

                //   String finishedTabTitle = context.getResources().getString(
                //         R.string.finished);
                return "Budget";
            }

            if (position == 3) {

                //   String finishedTabTitle = context.getResources().getString(
                //         R.string.finished);
                return "Expenses";
            }

            if (position == 4) {

                //   String finishedTabTitle = context.getResources().getString(
                //         R.string.finished);
                return "Personnel";
            }

            if (position == 5) {

                //   String finishedTabTitle = context.getResources().getString(
                //         R.string.finished);
                return "Sections";
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
