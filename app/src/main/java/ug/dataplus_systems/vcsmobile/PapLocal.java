package ug.dataplus_systems.vcsmobile;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class PapLocal {
    String birthPlace;
    Context context;
    List<Crop> crops;
    String dateOfBirth;
    String papStatus;
    boolean hasCrops;
    boolean hasImprovements;
    String id;
    List<Improvement> improvements;
    boolean isComplete = false;
    boolean isMarried = false;
    boolean isResident;
    String name;
    String occupation;
    List<String> otherPhotosUrlList;
    String papPhotoUrl;
    String papType = "Individual";
    String physicalAddress;
    String plotReference;
    String referenceNumber;
    String religion;
    long rightOfWaySize;
    String sex = "Male";
    String tribe;
    long wayLeaveSize;

    public String getLandUnits() {
        return landUnits;
    }

    public void setLandUnits(String landUnits) {
        this.landUnits = landUnits;
    }

    String landUnits;


    public PapLocal(Context paramContext) {

        crops = new ArrayList();
        improvements = new ArrayList();
        this.context = paramContext;
    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    public List<Crop> getCrops() {
        return this.crops;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getPapStatus() {
        return this.papStatus;
    }

    public String getId() {
        return this.id;
    }

    public List<Improvement> getImprovements() {
        return this.improvements;
    }

    public String getName() {
        return this.name;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public List<String> getOtherPhotosUrlList() {
        return this.otherPhotosUrlList;
    }

    public String getPapPhotoUrl() {
        return this.papPhotoUrl;
    }

    public String getPapType() {
        return this.papType;
    }

    public String getPhysicalAddress() {
        return this.physicalAddress;
    }

    public String getPlotReference() {
        return this.plotReference;
    }

    public String getReferenceNumber() {
        return this.referenceNumber;
    }

    public String getReligion() {
        return this.religion;
    }

    public long getRightOfWaySize() {
        return this.rightOfWaySize;
    }

    public String getSex() {
        return this.sex;
    }

    public String getTribe() {
        return this.tribe;
    }

    public long getWayLeaveSize() {
        return this.wayLeaveSize;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public boolean isHasCrops() {
        return this.hasCrops;
    }

    public boolean isHasImprovements() {
        return this.hasImprovements;
    }

    public boolean isMarried() {
        return this.isMarried;
    }

    public boolean isResident() {
        return this.isResident;
    }

    public void setBirthPlace(String paramString) {
        this.birthPlace = paramString;
    }

    public void setCrops(List<Crop> paramList) {
        this.crops = paramList;
    }

    public void setDateOfBirth(String paramString) {
        this.dateOfBirth = paramString;
    }

    public void setPapStatus(String paramString) {
        this.papStatus = paramString;
    }

    public void setHasCrops(boolean paramBoolean) {
        this.hasCrops = paramBoolean;
    }

    public void setHasImprovements(boolean paramBoolean) {
        this.hasImprovements = paramBoolean;
    }

    public void setId(String paramString) {
        this.id = paramString;
    }

    public void setImprovements(List<Improvement> paramList) {
        this.improvements = paramList;
    }

    public void setIsComplete(boolean paramBoolean) {
        this.isComplete = paramBoolean;
    }

    public void setIsMarried(boolean paramBoolean) {
        this.isMarried = paramBoolean;
    }

    public void setIsResident(boolean paramBoolean) {
        this.isResident = paramBoolean;
    }

    public void setName(String paramString) {
        this.name = paramString;
    }

    public void setOccupation(String paramString) {
        this.occupation = paramString;
    }

    public void setOtherPhotosUrlList(List<String> paramList) {
        this.otherPhotosUrlList = paramList;
    }

    public void setPapPhotoUrl(String paramString) {
        this.papPhotoUrl = paramString;
    }

    public void setPapType(String paramString) {
        this.papType = paramString;
    }

    public void setPhysicalAddress(String paramString) {
        this.physicalAddress = paramString;
    }

    public void setPlotReference(String paramString) {
        this.plotReference = paramString;
    }

    public void setReferenceNumber(String paramString) {
        this.referenceNumber = paramString;
    }

    public void setReligion(String paramString) {
        this.religion = paramString;
    }

    public void setRightOfWaySize(long paramLong) {
        this.rightOfWaySize = paramLong;
    }

    public void setSex(String paramString) {
        this.sex = paramString;
    }

    public void setTribe(String paramString) {
        this.tribe = paramString;
    }

    public void setWayLeaveSize(long paramLong) {
        this.wayLeaveSize = paramLong;
    }
}
