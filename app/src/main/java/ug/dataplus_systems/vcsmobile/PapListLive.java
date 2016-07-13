package ug.dataplus_systems.vcsmobile;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class PapListLive extends Fragment {


    public static final String KEY_PROJECT_ID = "project_id";
    String URL_GET_PAP_LIST = "VcsMobile/getPapListPaged.php";
    PapListLiveAdapter adapter;
    MyApplicationClass helper = MyApplicationClass.getInstance();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView livePapList;
    List<PapLive> papsToShow;
    View rootView;
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayoutManager linearLayoutManager;
    ProgressBar pbLoading;
    boolean isLoading = false;

    int page = 1;

    public PapListLive() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_pap_list_live, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        livePapList = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        pbLoading = (ProgressBar) rootView.findViewById(R.id.pb_loading);

        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pbLoading.setVisibility(View.GONE);
        livePapList.hasFixedSize();

        linearLayoutManager = new LinearLayoutManager(getActivity());

        livePapList.setLayoutManager(linearLayoutManager);
        //    this.livePapList.setItemAnimator(new SlideInLeftAnimator());


        papsToShow = new ArrayList();


        grabPapsToShow();


        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            livePapList.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    //Ifscrolled at last then


                    if (isLastItemDisplaying(recyclerView)) {
                        //Calling the method getdata again

                        if (isLoading == false) {
                            grabPapsToShow();
                        }
                    }
                }
            });
        } else {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                livePapList.setOnScrollChangeListener(new RecyclerView.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        //If scrolled at last then
                        if (isLastItemDisplaying(livePapList)) {
                            //Calling the method getdata again
                            if (isLoading == false) {
                                grabPapsToShow();
                            }
                        }
                    }
                });
            }
        }


        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                page = 1;
                PapListLive.this.adapter.clearData();
                PapListLive.this.grabPapsToShow();
                PapListLive.this.adapter.notifyDataSetChanged();
                PapListLive.this.swipeRefreshLayout.setRefreshing(false);
            }
        });

        // Configure the refreshing colors
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    private void grabPapsToShow() {

        Log.d("B4 page", "" + page);

        isLoading = true;
        pbLoading.setIndeterminate(true);
        pbLoading.setVisibility(View.VISIBLE);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, helper.getGeneralUrl() + URL_GET_PAP_LIST,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonResponse = new JSONArray(response);

                            if (page == 1) {

                                populateList(jsonResponse, true);
                                page = page + 1;
                            } else {
                                populateList(jsonResponse, false);
                                page = page + 1;
                            }

                            Log.d("After page", "" + page);
                            isLoading = false;
                            pbLoading.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        isLoading = false;
                        pbLoading.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.d("PROJECT INFO", "PROJECT INFO ERROR");


                    }
                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("project_id", "10");
                map.put("page", String.valueOf(page));

                return map;
            }
        };

        helper.add(stringRequest);


    }

    public void populateList(JSONArray paramJSONArray, boolean isNew) {


        if (isNew) {

            for (int i = 0; i < paramJSONArray.length(); i++) {

                try {
                    JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
                    PapLive localPapLive = new PapLive(getActivity());
                    localPapLive.setHhid(localJSONObject.getString("HHID"));
                    localPapLive.setName(localJSONObject.getString("PAP_NAME"));
                    localPapLive.setPapPhotoUrl(localJSONObject.getString("PHOTO"));
                    localPapLive.setSex(localJSONObject.getString("SEX"));
                    localPapLive.setPlotReference(localJSONObject.getString("PLOT_REF"));
                    localPapLive.setBirthPlace(localJSONObject.getString("BIRTH_PLACE"));
                    localPapLive.setDateOfBirth(localJSONObject.getString("DOB"));
                    if (localJSONObject.getString("IS_MARRIED").equals("false")) {
                        localPapLive.setIsMarried(false);
                    } else {
                        localPapLive.setIsMarried(true);
                    }


                    papsToShow.add(localPapLive);


                } catch (JSONException localJSONException) {
                    localJSONException.printStackTrace();
                }
            }


            this.adapter = new PapListLiveAdapter(getActivity(), this.papsToShow);
            this.livePapList.setAdapter(this.adapter);
        } else {


            List<PapLive> additionalPaps = new ArrayList<>();

            for (int i = 0; i < paramJSONArray.length(); i++) {

                try {
                    JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
                    PapLive localPapLive = new PapLive(getActivity());
                    localPapLive.setHhid(localJSONObject.getString("HHID"));
                    localPapLive.setName(localJSONObject.getString("PAP_NAME"));
                    localPapLive.setPapPhotoUrl(localJSONObject.getString("PHOTO"));
                    localPapLive.setSex(localJSONObject.getString("SEX"));
                    localPapLive.setPlotReference(localJSONObject.getString("PLOT_REF"));
                    localPapLive.setBirthPlace(localJSONObject.getString("BIRTH_PLACE"));
                    localPapLive.setDateOfBirth(localJSONObject.getString("DOB"));
                    if (localJSONObject.getString("IS_MARRIED").equals("false")) {
                        localPapLive.setIsMarried(false);
                    } else {
                        localPapLive.setIsMarried(true);
                    }


                    additionalPaps.add(localPapLive);


                } catch (JSONException localJSONException) {
                    localJSONException.printStackTrace();
                }
            }

            adapter.addToList(additionalPaps);
        }


    }

    //This method would check that the recyclerview scroll has reached the bottom or not
    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition = ((LinearLayoutManager)
                    recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();

            int lastAdapterItem = recyclerView.getAdapter().getItemCount();

            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition ==
                    lastAdapterItem - 1)
                return true;
        }
        return false;
    }

}
