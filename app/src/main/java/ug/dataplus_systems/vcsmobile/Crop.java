package ug.dataplus_systems.vcsmobile;

/**
 * Created by senyer on 5/12/2016.
 */
public class Crop {

    String cropName = "";
    String quantity = "";
    String unit = "";
    String otherDetails = "";
    String cropType ="";
    String cropDescription = "";
    String cropRate = "";

    public String getCropDescription() {
        return cropDescription;
    }

    public void setCropDescription(String cropDescription) {
        this.cropDescription = cropDescription;
    }





    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public String getCropRate() {
        return cropRate;
    }

    public void setCropRate(String cropRate) {
        this.cropRate = cropRate;
    }
}
