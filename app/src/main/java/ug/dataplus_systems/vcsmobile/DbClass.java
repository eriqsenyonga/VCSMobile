package ug.dataplus_systems.vcsmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Eriq on 12/3/2015.
 */
public class DbClass {

    //all tables have these fields
    public static final String KEY_ID = "_id";
    public static final String KEY_IS_DELETED = "ISDELETED";
    public static final String KEY_UPDATED_BY = "UPDATEDBY";
    public static final String KEY_UPDATED = "UPDATED";
    public static final String KEY_CREATED_BY = "CREATEDBY";
    public static final String KEY_CREATED = "CREATED";
    public static final String KEY_OTHER_DETAILS = "OTHER_DTL";
    public static final String KEY_PAP_ID = "PAP_ID";
    public static final String KEY_EMAIL = "EMAIL";
    public static final String KEY_PHONE = "PHONE";
    public static final String KEY_DISTRICT_ID = "DIST_ID";
    public static final String KEY_PROJECT_ID = "PROJ_ID";
    public static final String KEY_CATEGORY = "CATG";
    public static final String KEY_SUB_CATEGORY = "SUB_CATG";


    //column names for table trn_bio_pap_info
    public static final String DATABASE_TABLE_TRN_BIO_PAP_INFO = "trn_bio_pap_info";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_DATE_OF_BIRTH = "DOB";
    public static final String KEY_SEX = "SEX";
    public static final String KEY_PLOT_REFERENCE = "PLOT_REF";
    public static final String KEY_REFERENCE_NO = "REF_NO";
    public static final String KEY_IS_RESIDENT = "IS_RESIDENT";
    public static final String KEY_BIRTH_PLACE = "BIRTH_PLACE";
    public static final String KEY_IS_MARRIED = "IS_MARRIED";
    public static final String KEY_ADDRESS_ID = "ADDR_ID";
    public static final String KEY_TRIBE_ID = "TRIBE_ID";
    public static final String KEY_RELIGION_ID = "RELGN_ID";
    public static final String KEY_OCCUPATION_ID = "OCCUPN_ID";
    public static final String KEY_PAP_STATUS_ID = "PAP_STATUS_ID";
    public static final String KEY_DESIGNATION = "DESIGN";
    public static final String KEY_PHOTO = "PHOTO";
    public static final String KEY_PAP_TYPE = "PAP_TYPE";
    public static final String KEY_COMPLETE = "complete";
    public static final String KEY_SYNCED = "synced";

    //column names for table trn_val_crop
    public static final String DATABASE_TABLE_TRN_VAL_CROP = "trn_val_crop";
    public static final String KEY_CROP_ID = "CROP_ID";
    public static final String KEY_TYPE_ID = "TYPE_ID";
    public static final String KEY_DESCRIPTION_ID = "DESC_ID";
    public static final String KEY_UNIT_OF_MEASURE = "UNIT_MSR";
    public static final String KEY_QUANTITY = "QUANTITY";
    public static final String KEY_TOTAL = "TOTAL";


    //column names for table trn_val_impr
    public static final String DATABASE_TABLE_TRN_VAL_IMPR = "trn_val_impr";
    public static final String KEY_STRUCTURE_CATEGORY_ID = "STR_CATG";
    public static final String KEY_STRUCTUTRE_SUB_CATEGORY = "STR_SUBCATG";
    public static final String KEY_AREA = "AREA";
    public static final String KEY_STRUCTURE_TOTAL = "STR_TOTAL";
    public static final String KEY_ROOF = "ROOF";
    public static final String KEY_WALLS = "WALLS";
    public static final String KEY_WINDOWS = "WINDOWS";
    public static final String KEY_DOORS = "DOORS";
    public static final String KEY_FLOOR = "FLOOR";

    //column names for table trn_val_land
    public static final String DATABASE_TABLE_TRN_VAL_LAND = "trn_val_land";
    public static final String KEY_RIGHT_OF_WAY_SIZE = "ROW_UNITS";
    public static final String KEY_WAYLEAVE_SIZE = "WL_UNITS";
    public static final String KEY_SHARE_OF_LAND = "SHARE_OF_LND";
    public static final String KEY_DIMINUTION = "DIMINUTION";
    public static final String KEY_LAND_RATE = "LAND_RATE";
    public static final String KEY_WAYLEAVE_VALUE = "WL_VALUE";
    public static final String KEY_RIGHT_OF_WAY_VALUE = "ROW_VALUE";
    public static final String KEY_LAND_TYPE_ID = "LND_TYPE";
    public static final String KEY_IS_TITLED = "synced";

    //column names for table mst_val_crop
    public static final String DATABASE_TABLE_MST_VAL_CROP = "mst_val_crop";
    public static final String KEY_CROP = "CROP";

    //column names for table mst_val_crop_desc
    public static final String DATABASE_TABLE_MST_VAL_CROP_DESC = "mst_val_crop_desc";
    public static final String KEY_CROP_DESCRIPTION = "CROP_DESCRIPTION";

    //column names for table mst_val_crop_type
    public static final String DATABASE_TABLE_MST_VAL_CROP_TYPE = "mst_val_crop_type";
    public static final String KEY_CROP_TYPE = "CROP_TYPE";

    //column names for table mst_val_land
    public static final String DATABASE_TABLE_MST_VAL_LAND = "mst_val_land";
    public static final String KEY_LAND_TYPE = "LND_TYPE";

    //column names for table mst_val_str
    public static final String DATABASE_TABLE_MST_VAL_STR = "mst_val_str";
    public static final String KEY_STRUCTURE_TYPE = "STR_TYPE";

    //column names for table mst_val_str_catg
    public static final String DATABASE_TABLE_MST_VAL_STR_CATG = "mst_val_str_catg";
    public static final String KEY_STRUCTURE_CATEGORY = "STR_CATG";

    //column names for table trn_bio_pap_addr
    public static final String DATABASE_TABLE_TRN_BIO_PAP_ADDR = "trn_bio_pap_addr";
    public static final String KEY_COUNTY_ID = "CTY_ID";
    public static final String KEY_SUBCOUNTY_ID = "SUBCTY_ID";
    public static final String KEY_VILLAGE_ID = "VILL_ID";


    //column names for table mst_val_unit_msr
    public static final String DATABASE_TABLE_MST_VAL_UNIT_MSR = "MST_VAL_UNIT_MSR";
    public static final String KEY_ACRE_RATE = "ACRE_RATE";

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
    public static final String KEY_OCCUPATION_NAME = "NAME";

    //column names for table mst_bio_papstatus
    public static final String DATABASE_TABLE_MST_BIO_PAPSTATUS = "mst_bio_papstatus";
    public static final String KEY_PAP_STATUS = "PAP_STATUS";

    //column names for table mst_bio_tribe
    public static final String DATABASE_TABLE_MST_BIO_TRIBE = "mst_bio_tribe";
    public static final String KEY_TRIBE = "TRIBE";

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
    public static final String KEY_EXPENSE_PERCENTAGE = "EXP_PCTG";
    public static final String KEY_EXPENSE_DATE = "EXP_DATE";

    // details of the database that is, database name and version
    private static final String DATABASE_NAME = "vcs_db_android.db";
    private static final int DATABASE_VERSION = 1;

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


        ContentValues cv = new ContentValues();


        //these are direct text entries captured as Strings and saved as Text
        cv.put(KEY_NAME, papLocal.getName());
        cv.put(KEY_DATE_OF_BIRTH, papLocal.getDateOfBirth());
        cv.put(KEY_SEX, papLocal.getSex());
        cv.put(KEY_PLOT_REFERENCE, papLocal.getPlotReference());
        cv.put(KEY_BIRTH_PLACE, papLocal.getBirthPlace());
        cv.put(KEY_REFERENCE_NO, papLocal.getPlotReference());


        //since these are captured as Booleans and yet they are saved as TEXT in the database
        if (papLocal.isResident()) {
            cv.put(KEY_IS_RESIDENT, "True");
        } else {

            cv.put(KEY_IS_RESIDENT, "False");
        }

        if (papLocal.isMarried()) {
            cv.put(KEY_IS_MARRIED, "True");
        } else {

            cv.put(KEY_IS_MARRIED, "False");
        }


        //this method checks for the item in the table and returns its id
        checkForEntryAndReturnId(papLocal.getTribe(), DATABASE_TABLE_MST_BIO_TRIBE, );






        long papId = ourDatabase.insert(DATABASE_TABLE_TRN_BIO_PAP_INFO, null, cv);


    }


    public class DbHelper extends SQLiteAssetHelper {
        //this Class gets the Db from assets/databases folder that is named appropriately ie vcs_db_android.db

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

            //setForcedUpgrade();
        }


    }


}
