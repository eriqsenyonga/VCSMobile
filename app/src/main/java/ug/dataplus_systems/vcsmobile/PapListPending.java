package ug.dataplus_systems.vcsmobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
        v = inflater.inflate(R.layout.fragment_pap_list_live, container, false);
        pendingPapList = (RecyclerView) v.findViewById(R.id.recycler_view);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

     //   pendingPapList.hasFixedSize();
        mDb = new DbClass(getActivity());

        pendingPapList.setLayoutManager(new LinearLayoutManager(getActivity()));



        adapter = new PapListPendingAdapter(getActivity(),mDb.getLocalPapCursor() );

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
}
