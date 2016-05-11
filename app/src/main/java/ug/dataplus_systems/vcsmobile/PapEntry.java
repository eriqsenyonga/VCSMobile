package ug.dataplus_systems.vcsmobile;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class PapEntry extends Fragment {

    RecyclerView localPapList;
    View rootView;
    PapListLocalAdapter adapter;

    public PapEntry() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_pap_entry, container, false);

        localPapList = (RecyclerView) rootView.findViewById(R.id.rv_pap_list);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        adapter = new PapListLocalAdapter(getActivity());
        localPapList.setAdapter(adapter);
        localPapList.hasFixedSize();


        localPapList.setLayoutManager(new LinearLayoutManager(getActivity()));

        MainActivity mainActivity = (MainActivity)getActivity();

        mainActivity.fab.show();

        mainActivity.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PapViewLive.class);
                getActivity().startActivity(i);
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();

        inflater.inflate(R.menu.options_menu_pap_entry, menu);

        // Associate searchable configuration with the SearchView
  //      SearchManager searchManager =
    //            (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(getActivity(), query, Toast.LENGTH_LONG).show();

                ((PapListLocalAdapter) localPapList.getAdapter()).setFilter(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.isEmpty()) {
                    ((PapListLocalAdapter) localPapList.getAdapter()).flushFilter();
                } else {

                    ((PapListLocalAdapter) localPapList.getAdapter()).setFilter(newText);

                }

                return true;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                ((PapListLocalAdapter) localPapList.getAdapter()).flushFilter();

                return true;
            }
        });

    //    searchView.setSearchableInfo(
      //          searchManager.getSearchableInfo(getActivity().getComponentName()));


    }
}
