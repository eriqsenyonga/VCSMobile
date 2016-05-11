package ug.dataplus_systems.vcsmobile;

/**
 * Created by Eriq on 1/14/2016.
 */
public class HomeGridItem  {

    private String itemName;
    private int itemIcon;

    public String getName() {
        return itemName;
    }

    public void setName(String name) {
        this.itemName = name;
    }

    public int getThumbnail() {
        return itemIcon;
    }

    public void setThumbnail(int thumbnail) {
        this.itemIcon = thumbnail;
    }
}
