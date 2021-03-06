package ug.dataplus_systems.vcsmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DbClass {

    //all tables have these fields
    public static final String KEY_ID = "_id";
    public static final String KEY_IS_DELETED = "IS_DELETED";
    public static final String KEY_UPDATED_BY = "UPDATED_BY";
    public static final String KEY_UPDATED_DATE = "UPDATED_DATE";
    public static final String KEY_CREATED_BY = "CREATED_BY";
    public static final String KEY_CREATED_DATE = "CREATED_DATE";
    public static final String KEY_OTHER_DETAILS = "OTHER_DTL";
    public static final String KEY_PAP_ID = "PAP_ID";
    public static final String KEY_EMAIL = "EMAIL";
    public static final String KEY_PHONE = "PHONE_NO";
    public static final String KEY_DISTRICT_ID = "DIST_ID";
    public static final String KEY_PROJECT_ID = "PROJ_ID";
    public static final String KEY_CATEGORY = "CATG";
    public static final String KEY_SUB_CATEGORY = "SUB_CATG";
    public static final String KEY_UNIT_OF_MEASURE = "UNIT_MSR";
    public static final String KEY_COUNTY_ID = "CTY_ID";


    //column names for table trn_bio_pap_info
    public static final String DATABASE_TABLE_TRN_BIO_PAP_INFO = "trn_bio_pap_info";
    public static final String KEY_NAME = "PAP_NAME";
    public static final String KEY_DATE_OF_BIRTH = "DOB";
    public static final String KEY_SEX = "SEX";
    public static final String KEY_PLOT_REFERENCE = "PLOT_REF";
    public static final String KEY_REFERENCE_NO = "REF_NO";
    public static final String KEY_IS_RESIDENT = "IS_RESIDENT";
    public static final String KEY_BIRTH_PLACE = "BIRTH_PLACE";
    public static final String KEY_IS_MARRIED = "IS_MARRIED";
    public static final String KEY_TRIBE_ID = "TRIBE_ID";
    public static final String KEY_RELIGION_ID = "RELGN_ID";
    public static final String KEY_OCCUPATION_ID = "OCCUPN_ID";
    public static final String KEY_PAP_STATUS_ID = "PAP_STATUS_ID";
    public static final String KEY_DESIGNATION = "DESIGN";
    public static final String KEY_PHOTO = "PHOTO";
    public static final String KEY_PAP_TYPE = "PAP_TYPE";
    public static final String KEY_COMPLETE = "complete";
    public static final String KEY_SYNCED = "synced";
    public static final String KEY_OTHER_PHONE_NO = "OTHR_PHONE_NO";


    //column names for table trn_bio_pap_family
    public static final String DATABASE_TABLE_TRN_BIO_PAP_FAMILY = "trn_bio_pap_family";
    public static final String KEY_FAMILY_MEMBER_NAME = "MBR_NAME";
    public static final String KEY_FAMILY_RELATION_ID = "RLTN_ID";


    //column names for table trn_val_crop
    public static final String DATABASE_TABLE_TRN_VAL_CROP = "trn_val_crop";
    public static final String KEY_CROP_ID = "CROP_ID";
    public static final String KEY_CROP_TYPE_ID = "CROP_TYPE";
    public static final String KEY_CROP_DESCRIPTION = "CROP_DESC";
    public static final String KEY_CROP_RATE = "RATE";
    public static final String KEY_QUANTITY = "CROP_UNITS";
    public static final String KEY_TOTAL = "TOTAL";


    //column names for table trn_val_str
    public static final String DATABASE_TABLE_TRN_VAL_STR = "trn_val_str";
    public static final String KEY_STRUCTURE_NAME = "STR_NAME";
    public static final String KEY_STRUCTURE_TYPE_ID = "STR_TYPE";
    public static final String KEY_STRUCTURE_UNITS = "STR_UNITS";
    public static final String KEY_STRUCTURE_VALUE = "STR_VALUE";
    public static final String KEY_STRUCTURE_RATE = "RATE";
    public static final String KEY_ROOF = "STR_ROOF";
    public static final String KEY_WALLS = "STR_WALL";
    public static final String KEY_WINDOWS = "STR_WINDW";
    public static final String KEY_DOORS = "STR_DOOR";
    public static final String KEY_FLOOR = "STR_FLOOR";

    //column names for table trn_val_land
    public static final String DATABASE_TABLE_TRN_VAL_LAND = "trn_val_land";
    public static final String KEY_RIGHT_OF_WAY_UNITS = "ROW_UNITS";
    public static final String KEY_WAYLEAVE_UNITS = "WL_UNITS";
    public static final String KEY_SHARE_OF_LAND = "SHARE_OF_LND";
    public static final String KEY_DIMINUTION = "DIMINUTION";
    public static final String KEY_LAND_RATE = "RATE";
    public static final String KEY_LAND_TYPE_ID = "LND_TYPE";
    public static final String KEY_IS_TITLED = "IS_TITLED";

    //column names for table trn_val_fix
    public static final String DATABASE_TABLE_TRN_VAL_FIX = "trn_val_fix";
    public static final String KEY_FIXTURE_TYPE_ID = "FIX_TYPE";
    public static final String KEY_FIXTURE_DESCRIPTION = "FIX_DESC";
    public static final String KEY_FIXTURE_UNITS = "FIX_UNITS";
    public static final String KEY_FIXTURE_RATE = "RATE";

    //column names for table trn_val_totals
    public static final String DATABASE_TABLE_TRN_VAL_TOTALS = "trn_val_totals";
    public static final String KEY_LAND_VAL = "LAND_VAL";
    public static final String KEY_LAND_DA = "LAND_DA";
    public static final String KEY_CROP_VAL = "CROP_VAL";
    public static final String KEY_CROP_DA = "CROP_DA";
    public static final String KEY_STR_VAL = "STR_VAL";
    public static final String KEY_STR_DA= "STR_DA";
    public static final String KEY_FIX_VAL = "STR_VAL";
    public static final String KEY_FIX_DA= "STR_DA";
    public static final String KEY_ALLOW_VAL = "ALLOW_VAL";


    //column names for table mst_val_crop
    public static final String DATABASE_TABLE_MST_VAL_CROP = "mst_val_crop";
    public static final String KEY_CROP = "CROP";

    //column names for table mst_val_fix_type
    public static final String DATABASE_TABLE_MST_VAL_FIX_TYPE = "mst_val_fix_type";
    public static final String KEY_FIXTURE_TYPE = "FIX_TYPE";


    //column names for table mst_val_crop_type
    public static final String DATABASE_TABLE_MST_VAL_CROP_TYPE = "mst_val_crop_type";
    public static final String KEY_CROP_TYPE = "CROP_TYPE";

    //column names for table mst_val_land_type
    public static final String DATABASE_TABLE_MST_VAL_LAND_TYPE = "mst_val_lnd_type";
    public static final String KEY_LAND_TYPE = "LND_TYPE";

    //column names for table mst_val_str_type
    public static final String DATABASE_TABLE_MST_VAL_STR_TYPE = "mst_val_str_type";
    public static final String KEY_STRUCTURE_TYPE = "STR_TYPE";

    //column names for table mst_val_str_catg
    public static final String DATABASE_TABLE_MST_VAL_STR_CATG = "mst_val_str_catg";
    public static final String KEY_STRUCTURE_CATEGORY = "STR_CATG";

    //column names for table trn_bio_pap_addr
    public static final String DATABASE_TABLE_TRN_BIO_PAP_ADDR = "trn_bio_pap_addr";
    public static final String KEY_ROAD = "ROAD";

    public static final String KEY_VILLAGE_ID = "VILL_ID";


    //column names for table mst_val_unit_msr
    public static final String DATABASE_TABLE_MST_VAL_UNIT_MSR = "MST_VAL_UNIT_MSR";


    //column names for table mst_sys_user
    public static final String DATABASE_TABLE_MST_SYS_USER = "mst_sys_user";
    public static final String KEY_USER_NAME = "USER_NAME";
    public static final String KEY_PASSWORD = "PWD";
    public static final String KEY_DISPLAY_NAME = "DISP_NAME";
    public static final String KEY_ROLE_ID = "ROLE_ID";

    //column names for table mst_sys_user_role
    public static final String DATABASE_TABLE_MST_SYS_USER_ROLE = "mst_sys_user_role";
    public static final String KEY_ROLE = "ROLE";

    //column names for table mst_bio_county
    public static final String DATABASE_TABLE_MST_BIO_COUNTY = "mst_bio_county";
    public static final String KEY_COUNTY = "COUNTY";


    //column names for table mst_bio_district
    public static final String DATABASE_TABLE_MST_BIO_DISTRICT = "mst_bio_district";
    public static final String KEY_DISTRICT = "DISTRICT";

    //column names for table mst_bio_occupation
    public static final String DATABASE_TABLE_MST_BIO_OCCUPATION = "mst_bio_occupation";
    public static final String KEY_OCCUPATION_NAME = "OCCUPN_NAME";

    //column names for table mst_bio_papstatus
    public static final String DATABASE_TABLE_MST_BIO_PAPSTATUS = "mst_bio_papstatus";
    public static final String KEY_PAP_STATUS = "PAP_STATUS";

    //column names for table mst_bio_tribe
    public static final String DATABASE_TABLE_MST_BIO_TRIBE = "mst_bio_tribe";
    public static final String KEY_TRIBE = "TRIBE";

    //column names for table mst_bio_religion
    public static final String DATABASE_TABLE_MST_BIO_RELIGION = "mst_bio_religion";
    public static final String KEY_RELIGION = "RELIGION";

    //column names for table mst_bio_relation
    public static final String DATABASE_TABLE_MST_BIO_RELATION = "mst_bio_relation";
    public static final String KEY_RELATION = "RELATION";

    //column names for trn_proj_details
    public static final String DATABASE_TABLE_TRN_PROJ_DETAILS = "trn_proj_details";
    public static final String KEY_PROJECT_NAME = "PROJ_NAME";
    public static final String KEY_PROJECT_CODE = "PROJ_CODE";
    public static final String KEY_PROJECT_OBJECTIVE = "PROJ_OBJ";
    public static final String KEY_START_DATE = "START_DATE";
    public static final String KEY_END_DATE = "END_DATE";
    public static final String KEY_PROJECT_DESCRIPTION = "PROJ_DESC";
    public static final String KEY_PROJECT_MANAGER = "PROJ_MGR";

    //column names for trn_proj_dispute
    public static final String DATABASE_TABLE_TRN_PROJ_DISPUTE = "trn_proj_dispute";
    public static final String KEY_DATE_LOG = "DATE_LOG";
    public static final String KEY_LOG_BY = "LOG_BY";
    public static final String KEY_STATUS = "STATUS";

    //column names for trn_proj_expense
    public static final String DATABASE_TABLE_PROJ_EXPENSE = "trn_proj_expense";
    public static final String KEY_EXPENSE_AMOUNT = "EXP_AMT";
    public static final String KEY_EXPENSE_NAME = "EXP_NAME";
    public static final String KEY_EXPENSE_SUB_CATEGORY_ID = "SUB_CATG_ID";
    public static final String KEY_EXPENSE_DATE = "EXP_DATE";

    //column names for mst_bio_village
    public static final String DATABASE_TABLE_MST_BIO_VILLAGE = "mst_bio_village";
    public static final String KEY_VILLAGE_NAME = "VILLAGE";
    public static final String KEY_SUBCOUNTY_ID = "SUBCTY_ID";

    //column names for mst_bio_subcounty
    public static final String DATABASE_TABLE_MST_BIO_SUBCOUNTY = "mst_bio_subcounty";
    public static final String KEY_SUBCOUNTY_NAME = "SUBCOUNTY";



    // details of the database that is, database name and version
    private static final String DATABASE_NAME = "vcs_db_and_ten.db";
    private static final int DATABASE_VERSION = 14;

    private DbHelper ourHelper; // instance of the DbHelper class
    private Context ourContext;
    static SQLiteDatabase ourDatabase; // instance of the SQLitedatabase class

    public DbClass(Context c) {
        // this is constructor for the DbClass
        ourContext = c; // here we want to have the context passed into the
        // class to be usable
        // within the class
    }


    public DbClass open() {

        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();

        return this;
    }

    public void close() {

        ourHelper.close();

    }

    public void insertPap(PapLocal papLocal) {
        Log.d("checking this nigga", "1");

        ConversionClass mCC = new ConversionClass(ourContext);



        ContentValues cv = new ContentValues();


        //these are direct text entries captured as Strings and saved as Text
        cv.put(KEY_NAME, papLocal.getName());
        cv.put(KEY_DATE_OF_BIRTH, mCC.dateForDb(papLocal.getDateOfBirth()));
        cv.put(KEY_SEX, papLocal.getSex());
        cv.put(KEY_PLOT_REFERENCE, papLocal.getPlotReference());
        cv.put(KEY_BIRTH_PLACE, papLocal.getBirthPlace());
        cv.put(KEY_REFERENCE_NO, papLocal.getReferenceNumber());
        cv.put(KEY_PAP_TYPE, papLocal.getPapType());
        cv.put(KEY_PHOTO, papLocal.getPapPhotoUriString());
        cv.put(KEY_EMAIL, papLocal.getEmail());
        cv.put(KEY_PHONE, papLocal.getPhoneNumber());
        cv.put(KEY_OTHER_PHONE_NO, papLocal.getOtherPhoneNumber());
        cv.put(KEY_IS_DELETED, "false");
        cv.put(KEY_SYNCED, "false");
        cv.put(KEY_COMPLETE, "true");

        //since these are captured as Booleans and yet they are saved as TEXT in the database
        if (papLocal.isResident()) {
            cv.put(KEY_IS_RESIDENT, "true");
        } else {

            cv.put(KEY_IS_RESIDENT, "false");
        }

        if (papLocal.isMarried()) {
            cv.put(KEY_IS_MARRIED, "true");
        } else {

            cv.put(KEY_IS_MARRIED, "false");
        }


        //this method checks for the item in the table and returns its id

        cv.put(KEY_PAP_STATUS_ID, checkForEntryAndReturnId(DATABASE_TABLE_MST_BIO_PAPSTATUS, KEY_PAP_STATUS, papLocal.getPapStatus()));

        cv.put(KEY_TRIBE_ID, checkForEntryAndReturnId(DATABASE_TABLE_MST_BIO_TRIBE, KEY_TRIBE, papLocal.getTribe()));

        cv.put(KEY_RELIGION_ID, checkForEntryAndReturnId(DATABASE_TABLE_MST_BIO_RELIGION, KEY_RELIGION, papLocal.getReligion()));

        cv.put(KEY_OCCUPATION_ID, checkForEntryAndReturnId(DATABASE_TABLE_MST_BIO_OCCUPATION, KEY_OCCUPATION_NAME, papLocal.getOccupation()));

        // cv.put(KEY_PAP_STATUS_ID, checkForEntryAndReturnId(DATABASE_TABLE_MST_BIO_PAPSTATUS, KEY_PAP_STATUS, papLocal.getPapStatus()));


        //TODO add insert for UPDATEDBY, UPDATEDDATE, CREATEDBY, CREATEDDATE


        /*insert the values into database and get the id for the pap so as to be able to
        insert crops and structures in their respective tables
        */
        Log.d("checking this nigga", "7");
        Long papId = ourDatabase.insert(DATABASE_TABLE_TRN_BIO_PAP_INFO, null, cv);

        Log.d("checking this nigga", "8");
        addPapLandInfoToDatabase(papId, papLocal);


        if (!papLocal.getPapAddresses().isEmpty()) {
            //if pap has addresses

            addAddressesToDatabase(papId, papLocal.getPapAddresses());


        }


        if (!papLocal.getPapFamilyMembers().isEmpty()) {
            //if pap has family members

            addFamilyMembersToDatabase(papId, papLocal.getPapFamilyMembers());

        }


        if (!papLocal.getCrops().isEmpty()) {
            //if pap has crops


            addCropsToDatabase(papId, papLocal.getCrops());

        }

        if (!papLocal.getStructures().isEmpty()) {
            //if pap has structures


            addStructuresToDatabase(papId, papLocal.getStructures());

        }


    }

    private void addFamilyMembersToDatabase(Long papId, List<FamilyMember> papFamilyMembers) {

        ConversionClass mCC = new ConversionClass(ourContext);

        for (FamilyMember familyMember : papFamilyMembers) {

            ContentValues cv = new ContentValues();

            cv.put(KEY_PAP_ID, papId);
            cv.put(KEY_FAMILY_MEMBER_NAME, familyMember.getName());
            cv.put(KEY_DATE_OF_BIRTH, mCC.dateForDb(familyMember.getDateOfBirth()));
            cv.put(KEY_BIRTH_PLACE, familyMember.getPlaceOfBirth());
            cv.put(KEY_SEX, familyMember.getSex());
            cv.put(KEY_FAMILY_RELATION_ID, checkForEntryAndReturnId(DATABASE_TABLE_MST_BIO_RELATION, KEY_RELATION, familyMember.getRelationType()));
            cv.put(KEY_TRIBE_ID, checkForEntryAndReturnId(DATABASE_TABLE_MST_BIO_TRIBE, KEY_TRIBE, familyMember.getTribe()));
            cv.put(KEY_RELIGION_ID, checkForEntryAndReturnId(DATABASE_TABLE_MST_BIO_RELIGION, KEY_RELIGION, familyMember.getReligion()));

            cv.put(KEY_IS_DELETED, "false");

            //TODO add insert for UPDATEDBY, UPDATEDDATE, CREATEDBY, CREATEDDATE

            ourDatabase.insert(DATABASE_TABLE_TRN_BIO_PAP_FAMILY, null, cv);

        }
    }

    private void addAddressesToDatabase(Long papId, List<Address> papAddresses) {


        for (Address address : papAddresses) {

            ContentValues cv = new ContentValues();

            cv.put(KEY_PAP_ID, papId);
            cv.put(KEY_ROAD, address.getPlotNoRoad());
            cv.put(KEY_VILLAGE_ID, checkForVillageAndReturnId(DATABASE_TABLE_MST_BIO_VILLAGE, KEY_VILLAGE_NAME, address.getVillage(), address));

            if (address.isMainAddress()) {

                cv.put(KEY_IS_RESIDENT, "true");

            } else {
                cv.put(KEY_IS_RESIDENT, "false");

            }

            cv.put(KEY_IS_DELETED, "false");

            //TODO add insert for UPDATEDBY, UPDATEDDATE, CREATEDBY, CREATEDDATE


            ourDatabase.insert(DATABASE_TABLE_TRN_BIO_PAP_ADDR, null, cv);

        }

    }

    private void addPapLandInfoToDatabase(Long papId, PapLocal papLocal) {

        ContentValues cv = new ContentValues();

        cv.put(KEY_PAP_ID, papId);
        cv.put(KEY_RIGHT_OF_WAY_UNITS, papLocal.getRightOfWaySize());
        cv.put(KEY_WAYLEAVE_UNITS, papLocal.getWayLeaveSize());
        cv.put(KEY_LAND_TYPE, papLocal.getLandType());
        cv.put(KEY_SHARE_OF_LAND, papLocal.getShareOfLand());
        cv.put(KEY_DIMINUTION, papLocal.getDiminution());
        cv.put(KEY_LAND_RATE, papLocal.getLandRate());
        cv.put(KEY_IS_DELETED, "false");
        cv.put(KEY_LAND_TYPE_ID, checkForEntryAndReturnId(DATABASE_TABLE_MST_VAL_LAND_TYPE, KEY_LAND_TYPE, papLocal.getLandType()));
        cv.put(KEY_UNIT_OF_MEASURE, checkForEntryAndReturnId(DATABASE_TABLE_MST_VAL_UNIT_MSR, KEY_UNIT_OF_MEASURE, papLocal.getLandUnits()));

        if (papLocal.isTitledLand()) {
            cv.put(KEY_IS_TITLED, "true");
        } else {

            cv.put(KEY_IS_TITLED, "false");
        }

        //TODO add insert for UPDATEDBY, UPDATEDDATE, CREATEDBY, CREATEDDATE

        Long landId = ourDatabase.insert(DATABASE_TABLE_TRN_VAL_LAND, null, cv);

    }

    private void addCropsToDatabase(Long papId, List<Crop> crops) {


        for (Crop crop : crops) {

            ContentValues cv = new ContentValues();

            cv.put(KEY_PAP_ID, papId);
            cv.put(KEY_CROP_ID, checkForEntryAndReturnId(DATABASE_TABLE_MST_VAL_CROP, KEY_CROP, crop.getCropName()));
            cv.put(KEY_CROP_TYPE_ID, checkForEntryAndReturnId(DATABASE_TABLE_MST_VAL_CROP_TYPE, KEY_CROP_TYPE, crop.getCropType()));
            cv.put(KEY_CROP_DESCRIPTION, crop.getCropDescription());
            cv.put(KEY_QUANTITY, crop.getQuantity());
            cv.put(KEY_UNIT_OF_MEASURE, checkForEntryAndReturnId(DATABASE_TABLE_MST_VAL_UNIT_MSR, KEY_UNIT_OF_MEASURE, crop.getUnit()));
            cv.put(KEY_IS_DELETED, "false");

            //TODO add insert for UPDATEDBY, UPDATEDDATE, CREATEDBY, CREATEDDATE


            ourDatabase.insert(DATABASE_TABLE_TRN_VAL_CROP, null, cv);


        }
    }

    private void addStructuresToDatabase(Long papId, List<Structure> structures) {


        //TODO save structures


        for (Structure structure : structures) {

            ContentValues cv = new ContentValues();

            cv.put(KEY_PAP_ID, papId);
            cv.put(KEY_STRUCTURE_NAME, structure.getStructureName());
            cv.put(KEY_STRUCTURE_TYPE_ID, checkForEntryAndReturnId(DATABASE_TABLE_MST_VAL_STR_TYPE, KEY_STRUCTURE_TYPE, structure.getStructureType()));
            cv.put(KEY_STRUCTURE_UNITS, structure.getArea());
            cv.put(KEY_UNIT_OF_MEASURE, checkForEntryAndReturnId(DATABASE_TABLE_MST_VAL_UNIT_MSR, KEY_UNIT_OF_MEASURE, structure.getUnit()));
            cv.put(KEY_ROOF, structure.getRoof());
            cv.put(KEY_WALLS, structure.getWalls());
            cv.put(KEY_WINDOWS, structure.getWindows());
            cv.put(KEY_DOORS, structure.getDoors());
            cv.put(KEY_FLOOR, structure.getFloor());
            cv.put(KEY_STRUCTURE_VALUE, structure.getValue());
            cv.put(KEY_IS_DELETED, "false");
            cv.put(KEY_OTHER_DETAILS, structure.getOtherDetails());

            //TODO add insert for UPDATEDBY, UPDATEDDATE, CREATEDBY, CREATEDDATE


            Long improvementId = ourDatabase.insert(DATABASE_TABLE_TRN_VAL_STR, null, cv);

            Log.d("IMPROVEMENT ID", "" + improvementId);


        }


    }

    private long checkForEntryAndReturnId(String tableNameToBeSearched, String columnNameWhereClause, String parameter) {


        Log.d("checccc", "1");
        String sql = "SELECT " + KEY_ID
                + " FROM " + tableNameToBeSearched
                + " WHERE " + columnNameWhereClause + " = '" + parameter + "'";

        Cursor c = ourDatabase.rawQuery(sql, null);

        Log.d("checccc", "2");

        if (c.getCount() > 0) {
            Log.d("checccc", "3");
            //if there is a row with that entry already, get the _id and return it to caller
            c.moveToFirst();

            Log.d("checccc", "4");
            long id = c.getLong(c.getColumnIndex(KEY_ID));

            Log.d("checccc", "5");
            c.close();
            return id;
        } else {
            //if the row is not there, insert the row and then get its id and return it
            c.close();
            return insertParameterIntoTable(tableNameToBeSearched, columnNameWhereClause, parameter);
            //  ContentValues cv = new ContentValues();


        }


    }

    private long checkForVillageAndReturnId(String tableNameToBeSearched, String columnNameWhereClause, String parameter, Address address) {



        String sql = "SELECT " + KEY_ID
                + " FROM " + tableNameToBeSearched
                + " WHERE " + columnNameWhereClause + " = '" + parameter + "'";

        Cursor c = ourDatabase.rawQuery(sql, null);



        if (c.getCount() > 0) {

            //if there is a row with that village already, get the _id and return it to caller

            c.moveToFirst();

            long id = c.getLong(c.getColumnIndex(KEY_ID));

            c.close();
            return id;
        } else {
            //if the village is not there, insert the village in 3 steps and then get its id and return it
            c.close();



           return insertVillageIntoDatabase(address);

        }


    }

    private long insertVillageIntoDatabase(Address address){

        //Step 1: Check for the district and get its Id
        long districtId = checkForEntryAndReturnId(DATABASE_TABLE_MST_BIO_DISTRICT, KEY_DISTRICT, address.getDistrict());
        long countyId;
        long subCountyId;
        long villageId;

        //Step 2: Check for county and get its id
        String checkCountySql = "SELECT " + KEY_ID
                + " FROM " + DATABASE_TABLE_MST_BIO_COUNTY
                + " WHERE " + KEY_COUNTY + " = '" + address.getCounty() + "'"
                + " AND " + KEY_DISTRICT_ID + " = " + districtId;

        Cursor c = ourDatabase.rawQuery(checkCountySql, null);

        if(c.getCount() > 0){

            c.moveToFirst();

            countyId = c.getLong(c.getColumnIndex(KEY_ID));

            c.close();
        }else{
            c.close();

            ContentValues cv = new ContentValues();
            cv.put(KEY_COUNTY, address.getCounty());
            cv.put(KEY_DISTRICT_ID, districtId);
            cv.put(KEY_IS_DELETED, "false");

             countyId = ourDatabase.insert(DATABASE_TABLE_MST_BIO_COUNTY, null, cv);


        }

        //Step 3: Check for subcounty and get its id
        String checkSubCountySql = "SELECT " + KEY_ID
                + " FROM " + DATABASE_TABLE_MST_BIO_SUBCOUNTY
                + " WHERE " + KEY_SUBCOUNTY_NAME + " = '" + address.getSubCounty() + "'"
                + " AND " + KEY_COUNTY_ID + " = " + countyId;

        Cursor cu = ourDatabase.rawQuery(checkSubCountySql, null);

        if(cu.getCount() > 0){

            cu.moveToFirst();

           subCountyId = cu.getLong(c.getColumnIndex(KEY_ID));

            cu.close();
        }else{

            cu.close();

            ContentValues cvs = new ContentValues();
            cvs.put(KEY_SUBCOUNTY_NAME, address.getSubCounty());
            cvs.put(KEY_COUNTY_ID, countyId);
            cvs.put(KEY_IS_DELETED, "false");

            subCountyId = ourDatabase.insert(DATABASE_TABLE_MST_BIO_SUBCOUNTY, null, cvs);


        }

        //STEP 4: insert village with its subcounty id and return the id of new village

        ContentValues cvv  = new ContentValues();
        cvv.put(KEY_VILLAGE_NAME, address.getVillage());
        cvv.put(KEY_SUBCOUNTY_ID, subCountyId);
        cvv.put(KEY_IS_DELETED, "false");

        villageId = ourDatabase.insert(DATABASE_TABLE_MST_BIO_VILLAGE, null, cvv);

        return villageId;




    }


    private long insertParameterIntoTable(String tableNameToBeInserted, String columnNameToInsert, String parameter) {

        ContentValues cv = new ContentValues();
        cv.put(columnNameToInsert, parameter);
        cv.put(KEY_IS_DELETED, "False");

        //TODO add insert for UPDATEDBY, UPDATEDDATE, CREATEDBY, CREATEDDATE

        return ourDatabase.insert(tableNameToBeInserted, null, cv);


    }

    public Cursor getLocalPapCursor() {

        String sql = "SELECT " + KEY_ID + ", " + KEY_NAME + ", " + KEY_COMPLETE + ", " + KEY_PHOTO + " FROM " + DATABASE_TABLE_TRN_BIO_PAP_INFO;

        open();
        Cursor c = ourDatabase.rawQuery(sql, null);

        if (c.getCount() > 0) {
            return c;
        } else {
            return null;
        }

    }

    public JSONObject getPapsForUploadAndConvertToJson(Context ctx) {


        String selectID = "SELECT \n" +
                "\ttrn_bio_pap_info._id, \n" +
                "\ttrn_bio_pap_info.DOB, \n" +
                "\ttrn_bio_pap_info.SEX,\n" +
                "\ttrn_bio_pap_info.PLOT_REF, \n" +
                "\ttrn_bio_pap_info.REF_NO, \n" +
                "\ttrn_bio_pap_info.IS_RESIDENT, \n" +
                "\ttrn_bio_pap_info.BIRTH_PLACE, \n" +
                "\ttrn_bio_pap_info.IS_MARRIED, \n" +
                "\ttrn_bio_pap_info.PHONE_NO, \n" +
                "\ttrn_bio_pap_info.OTHR_PHONE_NO, \n" +
                "\ttrn_bio_pap_info.EMAIL, \n" +
                "\ttrn_bio_pap_info.DESIGN \n" +
                "\t\n" +
                "FROM " +
                "\ttrn_bio_pap_info \n" +
                "\t\n" +
                "\t\t\t\n" +
                "WHERE \n" +
                "\ttrn_bio_pap_info.COMPLETE = 'true' \n" +
                "\t\n" +
                "\tAND trn_bio_pap_info.SYNCED = 'false' ";

        String selectIds = "SELECT "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_ID + ", "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_NAME + ","
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_DATE_OF_BIRTH + ", "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_SEX + ", "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_PLOT_REFERENCE + ", "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_REFERENCE_NO + ", "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_IS_RESIDENT + ", "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_BIRTH_PLACE + ", "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_IS_MARRIED + ", "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_PHONE + ", "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_OTHER_PHONE_NO + ", "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_EMAIL + ", "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_DESIGNATION + ", "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_PAP_TYPE + ", "
                + DATABASE_TABLE_MST_BIO_TRIBE + "." + KEY_TRIBE + ", "
                + DATABASE_TABLE_MST_BIO_RELIGION + "." + KEY_RELIGION + ", "
                + DATABASE_TABLE_MST_BIO_OCCUPATION + "." + KEY_OCCUPATION_NAME + ", "
                + DATABASE_TABLE_MST_BIO_PAPSTATUS + "." + KEY_PAP_STATUS + ", "
                + DATABASE_TABLE_TRN_PROJ_DETAILS + "." + KEY_PROJECT_NAME
                + " FROM "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO
                + " LEFT JOIN "
                + DATABASE_TABLE_MST_BIO_TRIBE + " ON "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_TRIBE_ID + " = " + DATABASE_TABLE_MST_BIO_TRIBE + "." + KEY_ID
                + " LEFT JOIN "
                + DATABASE_TABLE_MST_BIO_RELIGION + " ON "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_RELIGION_ID + " = " + DATABASE_TABLE_MST_BIO_RELIGION + "." + KEY_ID
                + " LEFT JOIN "
                + DATABASE_TABLE_MST_BIO_OCCUPATION + " ON "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_OCCUPATION_ID + " = " + DATABASE_TABLE_MST_BIO_OCCUPATION + "." + KEY_ID
                + " LEFT JOIN "
                + DATABASE_TABLE_MST_BIO_PAPSTATUS + " ON "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_PAP_STATUS_ID + " = " + DATABASE_TABLE_MST_BIO_PAPSTATUS + "." + KEY_ID
                + " LEFT JOIN "
                + DATABASE_TABLE_TRN_PROJ_DETAILS + " ON "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_PROJECT_ID + " = " + DATABASE_TABLE_TRN_PROJ_DETAILS + "." + KEY_ID

                + " WHERE " + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_COMPLETE + " = 'true' AND "
                + DATABASE_TABLE_TRN_BIO_PAP_INFO + "." + KEY_SYNCED + " = 'false'";

        //   Log.d("query", selectIds);

        final List<PapLocal> localPapList = new ArrayList<>();//List that will be converted to JSON

        open();

        Cursor c = ourDatabase.rawQuery(selectIds, null);

        Log.d("selectIdsCount", "" + c.getCount());

        if (c.getCount() > 0) {
            //put all results into a papLocal object to be added to localPapList for conversion to JSON
            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

                PapLocal papLocal = new PapLocal(ctx);

                Long papId = c.getLong(c.getColumnIndex(KEY_ID));

//Section for add Id and basic details to papLocal
                papLocal.setId("" + papId);
                papLocal.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                papLocal.setBirthPlace(c.getString(c.getColumnIndex(KEY_BIRTH_PLACE)));
                papLocal.setSex(c.getString(c.getColumnIndex(KEY_SEX)));
                papLocal.setDateOfBirth(c.getString(c.getColumnIndex(KEY_DATE_OF_BIRTH)));
                papLocal.setPhoneNumber(c.getString(c.getColumnIndex(KEY_PHONE)));
                papLocal.setOtherPhoneNumber(c.getString(c.getColumnIndex(KEY_OTHER_PHONE_NO)));
                papLocal.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                papLocal.setPapType(c.getString(c.getColumnIndex(KEY_PAP_TYPE)));
                papLocal.setTribe(c.getString(c.getColumnIndex(KEY_TRIBE)));
                papLocal.setReligion(c.getString(c.getColumnIndex(KEY_RELIGION)));
                papLocal.setOccupation(c.getString(c.getColumnIndex(KEY_OCCUPATION_NAME)));

                if (c.getString(c.getColumnIndex(KEY_IS_MARRIED)) == "true") {
                    papLocal.setIsMarried(true);
                } else {
                    papLocal.setIsMarried(false);
                }

                if (c.getString(c.getColumnIndex(KEY_IS_RESIDENT)) == "true") {
                    papLocal.setIsResident(true);
                } else {
                    papLocal.setIsResident(false);
                }

                papLocal.setPlotReference(c.getString(c.getColumnIndex(KEY_PLOT_REFERENCE)));
                papLocal.setReferenceNumber(c.getString(c.getColumnIndex(KEY_REFERENCE_NO)));
                papLocal.setPapStatus(c.getString(c.getColumnIndex(KEY_PAP_STATUS)));

//section for land details being added to papLocal
                String sqlLandDetails = "SELECT "
                        + DATABASE_TABLE_TRN_VAL_LAND + "." + KEY_RIGHT_OF_WAY_UNITS + ","
                        + DATABASE_TABLE_TRN_VAL_LAND + "." + KEY_WAYLEAVE_UNITS + ","
                        + DATABASE_TABLE_MST_VAL_UNIT_MSR + "." + KEY_UNIT_OF_MEASURE + ","
                        + DATABASE_TABLE_TRN_VAL_LAND + "." + KEY_SHARE_OF_LAND + ","
                        + DATABASE_TABLE_TRN_VAL_LAND + "." + KEY_DIMINUTION + ","
                        + DATABASE_TABLE_TRN_VAL_LAND + "." + KEY_LAND_RATE + ","
                        + DATABASE_TABLE_MST_VAL_LAND_TYPE + "." + KEY_LAND_TYPE + ","
                        + DATABASE_TABLE_TRN_VAL_LAND + "." + KEY_IS_TITLED
                        + " FROM " + DATABASE_TABLE_TRN_VAL_LAND
                        + " LEFT JOIN " + DATABASE_TABLE_MST_VAL_LAND_TYPE
                        + " ON "
                        + DATABASE_TABLE_TRN_VAL_LAND + "." + KEY_LAND_TYPE_ID + " = " + DATABASE_TABLE_MST_VAL_LAND_TYPE + "." + KEY_ID
                        + " LEFT JOIN " + DATABASE_TABLE_MST_VAL_UNIT_MSR
                        + " ON "
                        + DATABASE_TABLE_TRN_VAL_LAND + "." + KEY_UNIT_OF_MEASURE + " = " + DATABASE_TABLE_MST_VAL_UNIT_MSR + "." + KEY_ID
                        + " WHERE " + DATABASE_TABLE_TRN_VAL_LAND + "." + KEY_PAP_ID + " = " + papId;
                    //    + " AND " + DATABASE_TABLE_TRN_VAL_LAND + "." + KEY_IS_DELETED + " = 'false'";


                Cursor landCursor = ourDatabase.rawQuery(sqlLandDetails, null);

                Log.d("lAND CURSOR COUNT", "" +landCursor.getCount());

                if (landCursor.getCount() > 0) {

                    Log.d("in Land Cursor", "in Land Cursor");

                    for (landCursor.moveToFirst(); !landCursor.isAfterLast(); landCursor.moveToNext()) {

                        papLocal.setRightOfWaySize("" + landCursor.getDouble(landCursor.getColumnIndex(KEY_RIGHT_OF_WAY_UNITS)));
                        papLocal.setWayLeaveSize("" + landCursor.getDouble(landCursor.getColumnIndex(KEY_WAYLEAVE_UNITS)));
                        papLocal.setLandUnits(landCursor.getString(landCursor.getColumnIndex(KEY_UNIT_OF_MEASURE)));
                        papLocal.setDiminution(""+landCursor.getDouble(landCursor.getColumnIndex(KEY_DIMINUTION)));
                        papLocal.setLandRate(""+landCursor.getDouble(landCursor.getColumnIndex(KEY_LAND_RATE)));
                        papLocal.setShareOfLand(""+landCursor.getDouble(landCursor.getColumnIndex(KEY_SHARE_OF_LAND)));
                        papLocal.setLandType(landCursor.getString(landCursor.getColumnIndex(KEY_LAND_TYPE)));


                        Log.d("ROW SIZE","" + landCursor.getDouble(landCursor.getColumnIndex(KEY_RIGHT_OF_WAY_UNITS)) );

                        if (landCursor.getString(landCursor.getColumnIndex(KEY_IS_TITLED)) == "true") {
                            papLocal.setIsTitled(true);
                        } else {
                            papLocal.setIsTitled(false);
                        }
                    }


                }

                landCursor.close();


//section for adding PAP addresses

                String sqlAdresses = "SELECT "
                        + DATABASE_TABLE_TRN_BIO_PAP_ADDR + "." + KEY_ROAD + ", "
                        + DATABASE_TABLE_TRN_BIO_PAP_ADDR + "." + KEY_IS_RESIDENT + ", "
                        + DATABASE_TABLE_MST_BIO_VILLAGE + "." + KEY_VILLAGE_NAME
                        + " FROM " + DATABASE_TABLE_TRN_BIO_PAP_ADDR
                        + " INNER JOIN " + DATABASE_TABLE_MST_BIO_VILLAGE
                        + " ON "
                        + DATABASE_TABLE_TRN_BIO_PAP_ADDR + "." + KEY_VILLAGE_ID + " = " + DATABASE_TABLE_MST_BIO_VILLAGE + "." + KEY_ID
                        + " WHERE " + DATABASE_TABLE_TRN_BIO_PAP_ADDR + "." + KEY_PAP_ID + " = " + papId
                        + " AND " + DATABASE_TABLE_TRN_BIO_PAP_ADDR + "." + KEY_IS_DELETED + " = 'false'";



                String sqlDAdresses = "SELECT "
                        + DATABASE_TABLE_TRN_BIO_PAP_ADDR + "." + KEY_ROAD + ", "
                        + DATABASE_TABLE_TRN_BIO_PAP_ADDR + "." + KEY_IS_RESIDENT + ", "
                        + DATABASE_TABLE_MST_BIO_VILLAGE + "." + KEY_VILLAGE_NAME + ", "
                        + DATABASE_TABLE_MST_BIO_SUBCOUNTY + "." + KEY_SUBCOUNTY_NAME + ", "
                        + DATABASE_TABLE_MST_BIO_COUNTY + "." + KEY_COUNTY + ", "
                        + DATABASE_TABLE_MST_BIO_DISTRICT + "." + KEY_DISTRICT
                        + " FROM " + DATABASE_TABLE_TRN_BIO_PAP_ADDR
                        + " INNER JOIN " + DATABASE_TABLE_MST_BIO_VILLAGE
                        + " ON "
                        + DATABASE_TABLE_TRN_BIO_PAP_ADDR + "." + KEY_VILLAGE_ID + " = " + DATABASE_TABLE_MST_BIO_VILLAGE + "." + KEY_ID
                        + " INNER JOIN " + DATABASE_TABLE_MST_BIO_SUBCOUNTY
                        + " ON "
                        + DATABASE_TABLE_MST_BIO_VILLAGE + "." + KEY_SUBCOUNTY_ID + " = " + DATABASE_TABLE_MST_BIO_SUBCOUNTY + "." + KEY_ID
                        + " INNER JOIN " + DATABASE_TABLE_MST_BIO_COUNTY
                        + " ON "
                        + DATABASE_TABLE_MST_BIO_SUBCOUNTY + "." + KEY_COUNTY_ID + " = " + DATABASE_TABLE_MST_BIO_COUNTY + "." + KEY_ID
                        + " INNER JOIN " + DATABASE_TABLE_MST_BIO_DISTRICT
                        + " ON "
                        + DATABASE_TABLE_MST_BIO_COUNTY + "." + KEY_DISTRICT_ID + " = " + DATABASE_TABLE_MST_BIO_DISTRICT + "." + KEY_ID

                        + " WHERE " + DATABASE_TABLE_TRN_BIO_PAP_ADDR + "." + KEY_PAP_ID + " = " + papId
                        + " AND " + DATABASE_TABLE_TRN_BIO_PAP_ADDR + "." + KEY_IS_DELETED + " = 'false'";





                Cursor addressCursor = ourDatabase.rawQuery(sqlDAdresses, null);

                if (addressCursor.getCount() > 0) {

                    List<Address> addresses = new ArrayList<>();

                    for (addressCursor.moveToFirst(); !addressCursor.isAfterLast(); addressCursor.moveToNext()) {

                        Address address = new Address();
                        address.setVillage(addressCursor.getString(addressCursor.getColumnIndex(KEY_VILLAGE_NAME)));
                        address.setSubCounty(addressCursor.getString(addressCursor.getColumnIndex(KEY_SUBCOUNTY_NAME)));
                        address.setCounty(addressCursor.getString(addressCursor.getColumnIndex(KEY_COUNTY)));
                        address.setDistrict(addressCursor.getString(addressCursor.getColumnIndex(KEY_DISTRICT)));
                        address.setPlotNoRoad(addressCursor.getString(addressCursor.getColumnIndex(KEY_ROAD)));

                        if (addressCursor.getString(addressCursor.getColumnIndex(KEY_IS_RESIDENT)) == "true") {
                            address.setIsMainAddress(true);
                        } else {
                            address.setIsMainAddress(false);
                        }


                        addresses.add(address);

                    }

                    papLocal.setPapAddresses(addresses);


                }

                addressCursor.close();

//section for adding family members

                String sqlFamily = "SELECT "
                        + DATABASE_TABLE_TRN_BIO_PAP_FAMILY + "." + KEY_FAMILY_MEMBER_NAME + " , "
                        + DATABASE_TABLE_TRN_BIO_PAP_FAMILY + "." + KEY_SEX + " , "
                        + DATABASE_TABLE_TRN_BIO_PAP_FAMILY + "." + KEY_DATE_OF_BIRTH + " , "
                        + DATABASE_TABLE_TRN_BIO_PAP_FAMILY + "." + KEY_BIRTH_PLACE + " , "
                        + DATABASE_TABLE_MST_BIO_TRIBE + "." + KEY_TRIBE + " , "
                        + DATABASE_TABLE_MST_BIO_RELIGION + "." + KEY_RELIGION + " , "
                        + DATABASE_TABLE_MST_BIO_RELATION + "." + KEY_RELATION
                        + " FROM " + DATABASE_TABLE_TRN_BIO_PAP_FAMILY
                        + " INNER JOIN " + DATABASE_TABLE_MST_BIO_TRIBE
                        + " ON "
                        + DATABASE_TABLE_TRN_BIO_PAP_FAMILY + "." + KEY_TRIBE_ID + " = " + DATABASE_TABLE_MST_BIO_TRIBE + "." + KEY_ID
                        + " INNER JOIN " + DATABASE_TABLE_MST_BIO_RELIGION
                        + " ON "
                        + DATABASE_TABLE_TRN_BIO_PAP_FAMILY + "." + KEY_RELIGION_ID + " = " + DATABASE_TABLE_MST_BIO_RELIGION + "." + KEY_ID
                        + " INNER JOIN " + DATABASE_TABLE_MST_BIO_RELATION
                        + " ON "
                        + DATABASE_TABLE_TRN_BIO_PAP_FAMILY + "." + KEY_FAMILY_RELATION_ID + " = " + DATABASE_TABLE_MST_BIO_RELATION + "." + KEY_ID
                        + " WHERE " + DATABASE_TABLE_TRN_BIO_PAP_FAMILY + "." + KEY_PAP_ID + " = " + papId
                        + " AND " + DATABASE_TABLE_TRN_BIO_PAP_FAMILY + "." + KEY_IS_DELETED + " = 'false'";

                Cursor familyCursor = ourDatabase.rawQuery(sqlFamily, null);

                if (familyCursor.getCount() > 0) {

                    List<FamilyMember> famList = new ArrayList<>();

                    for (familyCursor.moveToFirst(); !familyCursor.isAfterLast(); familyCursor.moveToNext()) {

                        FamilyMember familyMember = new FamilyMember();

                        familyMember.setName(familyCursor.getString(familyCursor.getColumnIndex(KEY_FAMILY_MEMBER_NAME)));
                        familyMember.setSex(familyCursor.getString(familyCursor.getColumnIndex(KEY_SEX)));
                        familyMember.setDateOfBirth(familyCursor.getString(familyCursor.getColumnIndex(KEY_DATE_OF_BIRTH)));
                        familyMember.setPlaceOfBirth(familyCursor.getString(familyCursor.getColumnIndex(KEY_BIRTH_PLACE)));
                        familyMember.setTribe(familyCursor.getString(familyCursor.getColumnIndex(KEY_TRIBE)));
                        familyMember.setReligion(familyCursor.getString(familyCursor.getColumnIndex(KEY_RELIGION)));
                        familyMember.setRelationType(familyCursor.getString(familyCursor.getColumnIndex(KEY_RELATION)));

                        famList.add(familyMember);


                    }

                    papLocal.setPapFamilyMembers(famList);

                }

                familyCursor.close();

//Section for adding Crops

                //String sqlCr = " SELECT * FROM " + DATABASE_TABLE_TRN_VAL_CROP;

                String sqlCrops = "SELECT "
                        + DATABASE_TABLE_MST_VAL_CROP + "." + KEY_CROP + ","
                        + DATABASE_TABLE_MST_VAL_CROP_TYPE + "." + KEY_CROP_TYPE + ","
                        + DATABASE_TABLE_TRN_VAL_CROP + "." + KEY_CROP_DESCRIPTION + ","
                        + DATABASE_TABLE_MST_VAL_UNIT_MSR + "." + KEY_UNIT_OF_MEASURE + ","
                        + DATABASE_TABLE_TRN_VAL_CROP + "." + KEY_QUANTITY + ","
                        + DATABASE_TABLE_TRN_VAL_CROP + "." + KEY_CROP_RATE + ","
                        + DATABASE_TABLE_TRN_VAL_CROP + "." + KEY_OTHER_DETAILS
                        + " FROM " + DATABASE_TABLE_TRN_VAL_CROP
                        + " LEFT JOIN " + DATABASE_TABLE_MST_VAL_CROP
                        + " ON "
                        + DATABASE_TABLE_TRN_VAL_CROP + "." + KEY_CROP_ID + " = " + DATABASE_TABLE_MST_VAL_CROP + "." + KEY_ID
                        + " LEFT JOIN " + DATABASE_TABLE_MST_VAL_UNIT_MSR
                        + " ON "
                        + DATABASE_TABLE_TRN_VAL_CROP + "." + KEY_UNIT_OF_MEASURE + " = " + DATABASE_TABLE_MST_VAL_UNIT_MSR + "." + KEY_ID
                        + " LEFT JOIN " + DATABASE_TABLE_MST_VAL_CROP_TYPE
                        + " ON "
                        + DATABASE_TABLE_TRN_VAL_CROP + "." + KEY_CROP_TYPE_ID + " = " + DATABASE_TABLE_MST_VAL_CROP_TYPE + "." + KEY_ID
                        + " WHERE " + DATABASE_TABLE_TRN_VAL_CROP + "." + KEY_PAP_ID + " = " + papId
                        + " AND " + DATABASE_TABLE_TRN_VAL_CROP + "." + KEY_IS_DELETED + " = 'false'";


                Cursor cropCursor = ourDatabase.rawQuery(sqlCrops, null);

                Log.d("cropCusros", "" + cropCursor.getCount());

                if (cropCursor.getCount() > 0) {

                    List<Crop> cropsList = new ArrayList<>();

                    for (cropCursor.moveToFirst(); !cropCursor.isAfterLast(); cropCursor.moveToNext()) {

                        Crop crop = new Crop();

                        crop.setCropName(cropCursor.getString(cropCursor.getColumnIndex(KEY_CROP)));

//                        Log.d("cropNameCur",cropCursor.getString(cropCursor.getColumnIndex(KEY_CROP)));

                        crop.setCropDescription(cropCursor.getString(cropCursor.getColumnIndex(KEY_CROP_DESCRIPTION)));
                        crop.setUnit(cropCursor.getString(cropCursor.getColumnIndex(KEY_UNIT_OF_MEASURE)));
                        crop.setQuantity(cropCursor.getString(cropCursor.getColumnIndex(KEY_QUANTITY)));
                        crop.setCropRate(cropCursor.getString(cropCursor.getColumnIndex(KEY_CROP_RATE)));
                        crop.setOtherDetails(cropCursor.getString(cropCursor.getColumnIndex(KEY_OTHER_DETAILS)));
                        crop.setCropType(cropCursor.getString(cropCursor.getColumnIndex(KEY_CROP_TYPE)));

                        cropsList.add(crop);

                    }

                    papLocal.setCrops(cropsList);

                }

                cropCursor.close();

//SECTION FOR ADDING IMPROVEMENTS

                String sqlImprov = "SELECT "
                        + DATABASE_TABLE_TRN_VAL_STR + "." + KEY_STRUCTURE_NAME + ","
                        + DATABASE_TABLE_MST_VAL_STR_TYPE + "." + KEY_STRUCTURE_TYPE + ","
                        + DATABASE_TABLE_TRN_VAL_STR + "." + KEY_STRUCTURE_UNITS + ","
                        + DATABASE_TABLE_MST_VAL_UNIT_MSR + "." + KEY_UNIT_OF_MEASURE + ","
                        + DATABASE_TABLE_TRN_VAL_STR + "." + KEY_STRUCTURE_VALUE + ","
                        + DATABASE_TABLE_TRN_VAL_STR + "." + KEY_ROOF + ","
                        + DATABASE_TABLE_TRN_VAL_STR + "." + KEY_WALLS + ","
                        + DATABASE_TABLE_TRN_VAL_STR + "." + KEY_WINDOWS + ","
                        + DATABASE_TABLE_TRN_VAL_STR + "." + KEY_DOORS + ","
                        + DATABASE_TABLE_TRN_VAL_STR + "." + KEY_FLOOR
                        + " FROM " + DATABASE_TABLE_TRN_VAL_STR
                        + " INNER JOIN " + DATABASE_TABLE_MST_VAL_STR_TYPE
                        + " ON "
                        + DATABASE_TABLE_TRN_VAL_STR + "." + KEY_STRUCTURE_TYPE_ID + " = " + DATABASE_TABLE_MST_VAL_STR_TYPE + "." + KEY_ID
                        + " INNER JOIN " + DATABASE_TABLE_MST_VAL_UNIT_MSR
                        + " ON "
                        + DATABASE_TABLE_TRN_VAL_STR + "." + KEY_UNIT_OF_MEASURE + " = " + DATABASE_TABLE_MST_VAL_UNIT_MSR + "." + KEY_ID
                        + " WHERE " + DATABASE_TABLE_TRN_VAL_STR + "." + KEY_PAP_ID + " = " + papId
                        + " AND " + DATABASE_TABLE_TRN_VAL_STR + "." + KEY_IS_DELETED + " = 'false'";


                Cursor imprCursor = ourDatabase.rawQuery(sqlImprov, null);

                if (imprCursor.getCount() > 0) {

                    List<Structure> structures = new ArrayList<>();

                    for (imprCursor.moveToFirst(); !imprCursor.isAfterLast(); imprCursor.moveToNext()) {

                        Structure structure = new Structure();
                        structure.setStructureName(imprCursor.getString(imprCursor.getColumnIndex(KEY_STRUCTURE_NAME)));
                        structure.setCategory(imprCursor.getString(imprCursor.getColumnIndex(KEY_STRUCTURE_TYPE)));
                        structure.setUnit(imprCursor.getString(imprCursor.getColumnIndex(KEY_UNIT_OF_MEASURE)));
                        structure.setValue(imprCursor.getString(imprCursor.getColumnIndex(KEY_STRUCTURE_VALUE)));
                        structure.setRoof(imprCursor.getString(imprCursor.getColumnIndex(KEY_ROOF)));
                        structure.setWalls(imprCursor.getString(imprCursor.getColumnIndex(KEY_WALLS)));
                        structure.setWindows(imprCursor.getString(imprCursor.getColumnIndex(KEY_WINDOWS)));
                        structure.setDoors(imprCursor.getString(imprCursor.getColumnIndex(KEY_DOORS)));
                        structure.setFloor(imprCursor.getString(imprCursor.getColumnIndex(KEY_FLOOR)));
                        structure.setArea(imprCursor.getString(imprCursor.getColumnIndex(KEY_STRUCTURE_UNITS)));

                        structures.add(structure);


                    }

                    papLocal.setStructures(structures);
                }

                imprCursor.close();

//SECTION FOR ADDING PHOTOS
                //TODO add photos i.e sqlquerry for photo, cursor to get all photos of pap, maybe also changing the uri string to base64 encoded image


                localPapList.add(papLocal);


            }


        }

        c.close();

//SECTION TO TURN THE localPapList to a JSON object for upload

        JSONObject paps = new JSONObject();

        JSONArray jsonArray = new JSONArray();

        try {

            Log.d("one", "one");

            for (PapLocal p : localPapList) {

                Log.d("two", "two");

                JSONObject obj = new JSONObject();
                obj.put("id", p.getId());

                //CONVERT BASICDETAILS TO JSON
                JSONObject basicDetails = new JSONObject();

                basicDetails.put("name", p.getName());
                basicDetails.put("dob", p.getDateOfBirth());
                basicDetails.put("sex", p.getSex());
                basicDetails.put("birth_place", p.getBirthPlace());
                basicDetails.put("phone_number", p.getPhoneNumber());
                basicDetails.put("other_phone_number", p.getOtherPhoneNumber());
                basicDetails.put("email", p.getEmail());
                basicDetails.put("pap_type", p.getPapType());
                basicDetails.put("tribe", p.getTribe());
                basicDetails.put("religion", p.getReligion());
                basicDetails.put("occupation", p.getOccupation());


                if (p.isMarried()) {
                    basicDetails.put("is_married", "true");
                } else {
                    basicDetails.put("is_married", "false");
                }


                //add basicdetails json to papsJson
                obj.put("basic_details", basicDetails);


                //CONVERT PROPERTYINFO TO JSON
                JSONObject propertyInfo = new JSONObject();

                propertyInfo.put("plot_ref", p.getPlotReference());
                propertyInfo.put("pap_status", p.getPapStatus());
                propertyInfo.put("ref_no", p.getReferenceNumber());

                propertyInfo.put("right_of_way_size", p.getRightOfWaySize());

//                Log.d("right_of_way_size", p.getRightOfWaySize());

                propertyInfo.put("wayleave_size", p.getWayLeaveSize());
                propertyInfo.put("land_units", p.getLandUnits());
                propertyInfo.put("land_rate", p.getLandRate());
                propertyInfo.put("diminution", p.getDiminution());
                propertyInfo.put("share_of_land", p.getShareOfLand());
                propertyInfo.put("land_type", p.getLandType());


                if (p.isResident()) {
                    propertyInfo.put("is_resident", "true");
                } else {
                    propertyInfo.put("is_resident", "false");
                }
                if (p.isTitledLand()) {
                    propertyInfo.put("is_titled", "true");
                } else {
                    propertyInfo.put("is_titled", "false");
                }

                //add propertyInfo json to papsJson
                obj.put("property_info", propertyInfo);

                //CONVERT ADDRESSES TO JSON
                JSONArray addressesJsonArray = new JSONArray();

                List<Address> addressesList = p.getPapAddresses();

                for (Address address : addressesList) {

                    JSONObject addObject = new JSONObject();
                    addObject.put("plot_no_road", address.getPlotNoRoad());
                    addObject.put("village", address.getVillage());
                    addObject.put("subcounty", address.getSubCounty());
                    addObject.put("county", address.getCounty());
                    addObject.put("district", address.getDistrict());

                    Log.d("village" , address.getVillage());
                    Log.d("subcounty", address.getSubCounty());

                    if (address.isMainAddress()) {
                        addObject.put("is_main_residence", "true");
                    } else {
                        addObject.put("is_main_residence", "false");
                    }

                    addressesJsonArray.put(addObject);


                }

                //add addressArray to obj
                obj.put("addresses", addressesJsonArray);

                //CONVERT FAMILY MEMBERS TO JSON
                JSONArray famJsonArray = new JSONArray();

                List<FamilyMember> famList = p.getPapFamilyMembers();

                for (FamilyMember fam : famList) {

                    JSONObject famObject = new JSONObject();
                    famObject.put("family_member_name", fam.getName());
                    famObject.put("family_member_place_of_birth", fam.getPlaceOfBirth());
                    famObject.put("family_member_date_of_birth", fam.getDateOfBirth());
                    famObject.put("family_member_relation_type", fam.getRelationType());
                    famObject.put("family_member_religion", fam.getReligion());
                    famObject.put("family_member_sex", fam.getSex());
                    famObject.put("family_member_tribe", fam.getTribe());

                    famJsonArray.put(famObject);


                }

                //add addressArray to obj
                obj.put("family_members", famJsonArray);



                //CONVERT CROPS TO JSON
                JSONArray cropJsonArray = new JSONArray();

                List<Crop> cropList = p.getCrops();

                for (Crop crop : cropList) {

                    JSONObject cropObject = new JSONObject();

                    cropObject.put("crop_name", crop.getCropName());

//                    Log.d("cropName",crop.getCropName());
//                    Log.d("cropDesc",crop.getCropDescription());

                    cropObject.put("crop_description", crop.getCropDescription());
                    cropObject.put("crop_type", crop.getCropType());
                    cropObject.put("crop_quantity", crop.getQuantity());
                    cropObject.put("crop_units", crop.getUnit());
                    cropObject.put("crop_rate", crop.getCropRate());

                    cropJsonArray.put(cropObject);


                }

                //add cropsArray to obj
                obj.put("crops", cropJsonArray);


                //CONVERT IMPROVEMENTS TO JSON
                JSONArray strJsonArray = new JSONArray();

                List<Structure> strList = p.getStructures();

                for (Structure str : strList) {

                    JSONObject strObject = new JSONObject();

                    strObject.put("structure_name", str.getStructureName());
                    strObject.put("structure_units", str.getUnit());
                    strObject.put("structure_value", str.getValue());
                    strObject.put("structure_area", str.getArea());
                    strObject.put("structure_type", str.getCategory());
                    strObject.put("doors", str.getDoors());
                    strObject.put("windows", str.getWindows());
                    strObject.put("floor", str.getFloor());
                    strObject.put("walls", str.getWalls());
                    strObject.put("roof", str.getRoof());

                    strJsonArray.put(strObject);


                }

                //add imprJSONArray to obj
                obj.put("structures", strJsonArray);




                Log.d("three", "three");

                jsonArray.put(obj);
            }

            Log.d("four", "four");
            paps.put("paps", jsonArray);


        } catch (JSONException e) {

            Log.d("eeeerer", "errrrrrrrrrrrrr");
            e.printStackTrace();
        }


        Log.d("end", "end");

        return paps;
    }

    public List<Project> getAllProjects(Context ctx) {

        List<Project> projects = new ArrayList<>();

        String sql = "SELECT * FROM " + DATABASE_TABLE_TRN_PROJ_DETAILS + " WHERE " + KEY_IS_DELETED + " = 'false' ORDER BY " + KEY_PROJECT_NAME;

        open();
        Cursor c = ourDatabase.rawQuery(sql, null);

        if (c.getCount() > 0) {

            c.moveToFirst();

            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

                Project project = new Project(ctx);

                project.setProjectName(c.getString(c.getColumnIndex(KEY_PROJECT_NAME)));
                project.setProjectId(c.getLong(c.getColumnIndex(KEY_ID)));
                project.setProjectCode(c.getString(c.getColumnIndex(KEY_PROJECT_CODE)));

                projects.add(project);


            }

            c.close();

        }

        c.close();

        return projects;

    }


    public class DbHelper extends SQLiteAssetHelper {
        //this Class gets the Db from assets/databases folder that is named appropriately ie vcs_db_android_nine.db

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

            setForcedUpgrade();
        }


    }


}
