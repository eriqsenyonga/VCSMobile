package ug.dataplus_systems.vcsmobile;


import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class PapListPending extends Fragment {

    RecyclerView pendingPapList;
    PapListPendingAdapter adapter;
    DbClass mDb;
    View v;


    public PapListPending() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_pap_list_pending, container, false);
        pendingPapList = (RecyclerView) v.findViewById(R.id.recycler_view);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);

        //   pendingPapList.hasFixedSize();
        mDb = new DbClass(getActivity());

        pendingPapList.setLayoutManager(new LinearLayoutManager(getActivity()));


        adapter = new PapListPendingAdapter(getActivity(), mDb.getLocalPapCursor());

        pendingPapList.setAdapter(adapter);


        MainActivity mainActivity = (MainActivity) getActivity();

        mainActivity.fab.show();

        mainActivity.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NewPapActivity.class);
                getActivity().startActivity(i);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();

        inflater.inflate(R.menu.pap_list_pending_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_upload) {

            uploadReadyPapsToServer();


        }

        return super.onOptionsItemSelected(item);
    }

    private void uploadReadyPapsToServer() {

        /*
        * This method should check the local database for all PAPs that are complete and ready for sync
        *
        * All details for each PAP will be put in JSON and then uploaded to the server
        *
        * Results will received on what was successful and what wasnt and the local db updated accordingly
        * */


       Toast.makeText(getActivity(), "Upload ready PAPs coming soon", Toast.LENGTH_LONG).show();

        DbClass dbClass = new DbClass(getActivity());
       JSONObject jobj =  dbClass.getPapsForUploadAndConvertToJson(getActivity());

        BackupDatabase bd = new BackupDatabase(getActivity());
        bd.callThem(1);

Log.d("jsn", jobj.toString());

//
//        NotificationManager mNotifyManager;
//        NotificationBuilder mBuilder;
//        mNotifyManager =
//                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//        mBuilder = new NotificationCompat.Builder(this);
//        mBuilder.setContentTitle("Picture Download")
//                .setContentText("Download in progress")
//                .setSmallIcon(R.drawable.ic_notification);
//// Start a lengthy operation in a background thread
//        new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        int incr;
//                        // Do the "lengthy" operation 20 times
//                        for (incr = 0; incr <= 100; incr+=5) {
//                            // Sets the progress indicator to a max value, the
//                            // current completion percentage, and "determinate"
//                            // state
//                            mBuilder.setProgress(100, incr, false);
//                            // Displays the progress bar for the first time.
//                            mNotifyManager.notify(id, mBuilder.build());
//                            // Sleeps the thread, simulating an operation
//                            // that takes time
//                            try {
//                                // Sleep for 5 seconds
//                                Thread.sleep(5*1000);
//                            } catch (InterruptedException e) {
//                                Log.d(TAG, "sleep failure");
//                            }
//                        }
//                        // When the loop is finished, updates the notification
//                        mBuilder.setContentText("Download complete")
//                                // Removes the progress bar
//                                .setProgress(0,0,false);
//                        mNotifyManager.notify(id, mBuilder.build());
//                    }
//                }
//// Starts the thread by calling the run() method in its Runnable
//        ).start();



    }


}
