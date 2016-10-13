package ug.dataplus_systems.vcsmobile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class BackupDatabase {

    SharedPreferences sharedPrefs;
 //   String sharedPreferenceName = "mySharedPrefs";
 //   String KEY_TRANSFER_NUMBER = "transfer number";
 //   String KEY_ALARM_ID = "alarm id int";

    Context context;

    public BackupDatabase(Context ctx) {

        context = ctx;

     //   sharedPrefs = context.getSharedPreferences(sharedPreferenceName,
     //           Context.MODE_PRIVATE);

    }

    public void callThem(int i) {

        if (i == 0) {

            Log.d("callThem", "restore");
          //  importDB();
        }

        if (i == 1) {
            Log.d("callThem", "backup");
            exportDB();
        }

    }

  /*  private void importDB() {
        try {
            Log.d("import", "import");
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            if (sd.canWrite()) {
                String currentDBPath = "//data//" + "com.sentayzo.app"
                        + "//databases//" + "sentayzoDb.db";
                String backupDBPath = "MoneyCradleBackup"; // From SD directory.
                File backupDB = new File(data, currentDBPath);
                File currentDB = new File(sd, backupDBPath);

                FileInputStream srcFileInputStream = new FileInputStream(
                        currentDB);
                FileOutputStream dstFileOutputStream = new FileOutputStream(
                        backupDB);

                FileChannel src = srcFileInputStream.getChannel();

                FileChannel dst = dstFileOutputStream.getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                srcFileInputStream.close();
                dstFileOutputStream.close();

                Toast.makeText(context, "Import Successful!",
                        Toast.LENGTH_SHORT).show();

                updateScheduledAlarmIdAndTransferId();

            }
        } catch (Exception e) {

            Toast.makeText(context, "Import Failed!", Toast.LENGTH_SHORT)
                    .show();

        }
    }

  */

    private void exportDB() {
        try {
            Log.d("export", "export");
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            Log.d("2", "2");
            if (sd.canWrite()) {
                Log.d("3", "3");
                String currentDBPath = "//data//" + "ug.dataplus_systems.vcsmobile"
                        + "//databases//" + "vcs_db_and_ten.db";

                String backupDBPath = "VCSBackup";

                Log.d("4", "4");
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);
                Log.d("5", "5");

                FileInputStream srcFileInputStream = new FileInputStream(
                        currentDB);
                FileOutputStream dstFileOutputStream = new FileOutputStream(
                        backupDB);

                FileChannel src = srcFileInputStream.getChannel();

                FileChannel dst = dstFileOutputStream.getChannel();
                Log.d("6", "6");
                dst.transferFrom(src, 0, src.size());

                Log.d("7", "7");
                src.close();
                dst.close();

                srcFileInputStream.close();
                dstFileOutputStream.close();
                Toast.makeText(context, "Backup Successful!",
                        Toast.LENGTH_SHORT).show();

                Log.d("export2", "export2");

            }
        } catch (Exception e) {

            Toast.makeText(context, "Backup Failed!", Toast.LENGTH_SHORT)
                    .show();

        }
    }
}
