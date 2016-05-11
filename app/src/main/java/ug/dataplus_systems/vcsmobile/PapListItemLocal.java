package ug.dataplus_systems.vcsmobile;

import android.graphics.Bitmap;

/**
 * Created by Eriq on 11/30/2015.
 */
public class PapListItemLocal {

   private String papName;
    private int papPhoto;

    public PapListItemLocal(String name, int photo){

        papName = name;
        papPhoto = photo;
    }

    public String getName(){
        return papName;
    }

    public int getPapPhoto(){
        return papPhoto;
    }


}
