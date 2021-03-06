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
    List<Structure> structures;
    boolean isComplete = false;
    boolean isMarried = false;
    boolean isResident;
    String name = "";
    String occupation;
    List<String> otherPhotosUrlList;
    String papPhotoUriString;
    String papType = "Individual";
    String physicalAddress = "";
    String plotReference;
    String referenceNumber;
    String religion;
    String rightOfWaySize;
    String sex = "Male";
    String tribe;
    String wayLeaveSize;
    String landType = "";
    String landRate = "";
    String diminution = "";
    String shareOfLand = "";
    boolean isTitledLand = false;
    List<Address> papAddresses;
    List<FamilyMember> papFamilyMembers;
    String phoneNumber;
    String otherPhoneNumber;
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOtherPhoneNumber() {
        return otherPhoneNumber;
    }

    public void setOtherPhoneNumber(String otherPhoneNumber) {
        this.otherPhoneNumber = otherPhoneNumber;
    }

    public String getLandUnits() {
        return landUnits;
    }

    public void setLandUnits(String landUnits) {
        this.landUnits = landUnits;
    }

    String landUnits;


    public PapLocal(Context paramContext) {

        crops = new ArrayList();
        structures = new ArrayList();
        papAddresses = new ArrayList();
        papFamilyMembers = new ArrayList();

        this.context = paramContext;
    }

    public List<FamilyMember> getPapFamilyMembers() {
        return papFamilyMembers;
    }

    public void setPapFamilyMembers(List<FamilyMember> papFamilyMembers) {
        this.papFamilyMembers = papFamilyMembers;
    }

    public List<Address> getPapAddresses() {
        return papAddresses;
    }

    public void setPapAddresses(List<Address> papAddresses) {
        this.papAddresses = papAddresses;
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

    public List<Structure> getStructures() {
        return this.structures;
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

    public String getPapPhotoUriString() {
        return this.papPhotoUriString;
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

    public String getRightOfWaySize() {
        return this.rightOfWaySize;
    }

    public String getSex() {
        return this.sex;
    }

    public String getTribe() {
        return this.tribe;
    }

    public String getWayLeaveSize() {
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

    public boolean isTitledLand() {
        return this.isTitledLand;
    }

    public String getShareOfLand() {
        return shareOfLand;
    }

    public void setShareOfLand(String shareOfLand) {
        this.shareOfLand = shareOfLand;
    }

    public String getDiminution() {
        return diminution;
    }

    public void setDiminution(String diminution) {
        this.diminution = diminution;
    }

    public String getLandRate() {
        return landRate;
    }

    public void setLandRate(String landRate) {
        this.landRate = landRate;
    }

    public String getLandType() {
        return landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }

    public void setIsTitled(boolean paramBoolean) {

        this.isTitledLand = paramBoolean;

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

    public void setStructures(List<Structure> paramList) {
        this.structures = paramList;
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

    public void setPapPhotoUriString(String paramString) {
        this.papPhotoUriString = paramString;
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

    public void setRightOfWaySize(String paramLong) {
        this.rightOfWaySize = paramLong;
    }

    public void setSex(String paramString) {
        this.sex = paramString;
    }

    public void setTribe(String paramString) {
        this.tribe = paramString;
    }

    public void setWayLeaveSize(String paramString) {
        this.wayLeaveSize = paramString;
    }
}
