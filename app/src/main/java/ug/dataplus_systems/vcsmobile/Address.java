package ug.dataplus_systems.vcsmobile;

/**
 * Created by senyer on 6/17/2016.
 */
public class Address {


    String district;
    String county;
    String subCounty;
    String village;
    String plotNoRoad;
    boolean isMainAddress = false;

    public String getPlotNoRoad() {
        return plotNoRoad;
    }

    public void setPlotNoRoad(String plotNoRoad) {
        this.plotNoRoad = plotNoRoad;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(String subCounty) {
        this.subCounty = subCounty;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public boolean isMainAddress() {
        return isMainAddress;
    }

    public void setIsMainAddress(boolean mainAddress) {
        isMainAddress = mainAddress;
    }
}
