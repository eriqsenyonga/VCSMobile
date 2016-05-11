package ug.dataplus_systems.vcsmobile;

import android.content.Context;

import java.util.List;

/**
 * Created by Eriq on 3/4/2016.
 */
public class PapLive {

    String birthPlace;
    String hhid;
    String name;
    String sex;
    String dateOfBirth;
    String papType;
    String physicalAddress;
    boolean isMarried;
    String plotReference;
    String referenceNumber;
    String designation;
    long rightOfWaySize;
    long wayLeaveSize;
    boolean isResident;
    boolean hasCrops;
    boolean hasImprovements;
    String papPhotoUrl;
    List<String> otherPhotosUrlList;

    String occupation;
    String tribe;
    String religion;

    Context context;


    public PapLive(Context c) {

        context = c;
    }

    public String getBirthPlace()
    {
        return this.birthPlace;
    }

   
    public String getHhid() {
        return hhid;
    }

    public void setHhid(String hhid) {
        this.hhid = hhid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPapType() {
        return papType;
    }

    public void setPapType(String papType) {
        this.papType = papType;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setIsMarried(boolean isMarried) {
        this.isMarried = isMarried;
    }

    public String getPlotReference() {
        return plotReference;
    }

    public void setPlotReference(String plotReference) {
        this.plotReference = plotReference;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public long getRightOfWaySize() {
        return rightOfWaySize;
    }

    public void setRightOfWaySize(long rightOfWaySize) {
        this.rightOfWaySize = rightOfWaySize;
    }

    public long getWayLeaveSize() {
        return wayLeaveSize;
    }

    public void setWayLeaveSize(long wayLeaveSize) {
        this.wayLeaveSize = wayLeaveSize;
    }

    public boolean isResident() {
        return isResident;
    }

    public void setIsResident(boolean isResident) {
        this.isResident = isResident;
    }

    public boolean isHasCrops() {
        return hasCrops;
    }

    public void setHasCrops(boolean hasCrops) {
        this.hasCrops = hasCrops;
    }

    public boolean isHasImprovements() {
        return hasImprovements;
    }

    public void setHasImprovements(boolean hasImprovements) {
        this.hasImprovements = hasImprovements;
    }

    public String getPapPhotoUrl() {
        return papPhotoUrl;
    }

    public void setPapPhotoUrl(String papPhotoUrl) {
        this.papPhotoUrl = papPhotoUrl;
    }

    public List<String> getOtherPhotosUrlList() {
        return otherPhotosUrlList;
    }

    public void setOtherPhotosUrlList(List<String> otherPhotosUrlList) {
        this.otherPhotosUrlList = otherPhotosUrlList;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getTribe() {
        return tribe;
    }

    public void setTribe(String tribe) {
        this.tribe = tribe;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }
}
