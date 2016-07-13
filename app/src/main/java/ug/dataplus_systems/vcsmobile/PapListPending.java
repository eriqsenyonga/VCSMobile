package ug.dataplus_systems.vcsmobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


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



    }


}
