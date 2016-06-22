package ug.dataplus_systems.vcsmobile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Eriq on 1/11/2016.
 */
public class PagerAdapterNewPap extends FragmentStatePagerAdapter {

    public static int NEW_PAP_STEP_BASIC_INFO = 0;
    public static int NEW_PAP_STEP_PROPERTY_INFO = 1;
    public static int NEW_PAP_STEP_ADDRESSES = 2;
    public static int NEW_PAP_STEP_PROPERTY_INFO_CROPS = 3;
    public static int NEW_PAP_STEP_PROPERTY_INFO_IMPROVEMENTS = 4;
    public static int NEW_PAP_STEP_OTHER_DETAILS = 5;
    public static int NEW_PAP_STEP_PAP_PHOTOS= 6;
    public static int NEW_PAP_STEP_PREVIEW = 7;



    public PagerAdapterNewPap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        if (position == NEW_PAP_STEP_BASIC_INFO) {

            return new NewPapBasicInfoFrag();

        }

        if (position == NEW_PAP_STEP_PROPERTY_INFO) {

            return new NewPapPropertyInfoFrag();

        }

        if (position == NEW_PAP_STEP_ADDRESSES) {

            return new NewPapAddressesFrag();

        }

        if (position == NEW_PAP_STEP_PROPERTY_INFO_CROPS) {

            return new NewPapPropertyInfoCrops();

        }

        if (position == NEW_PAP_STEP_PROPERTY_INFO_IMPROVEMENTS) {

            return new NewPapPropertyInfoImprovements();

        }

        if (position == NEW_PAP_STEP_OTHER_DETAILS) {

            return new NewPapOtherDetailsFrag();
        }

        if (position == NEW_PAP_STEP_PAP_PHOTOS) {

            return new NewPapPhotosFrag();

        }

        if (position == NEW_PAP_STEP_PREVIEW) {

            return new NewPapPreviewFragment();
        }


        return null;
    }

    @Override
    public int getCount() {

        return 8;
    }
}
