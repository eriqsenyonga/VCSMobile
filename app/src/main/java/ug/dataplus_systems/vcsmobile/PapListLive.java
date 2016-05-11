package ug.dataplus_systems.vcsmobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    String URL_GET_PAP_LIST = "http://192.168.0.2/VcsMobile/getPapList.php";
    PapListLiveAdapter adapter;
    MyApplicationClass helper = MyApplicationClass.getInstance();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView livePapList;
    List<PapLive> papsToShow;
    View rootView;
    SwipeRefreshLayout swipeRefreshLayout;

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

        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        this.livePapList.hasFixedSize();
        this.livePapList.setLayoutManager(new LinearLayoutManager(getActivity()));
        //    this.livePapList.setItemAnimator(new SlideInLeftAnimator());
        this.papsToShow = new ArrayList();
        grabPapsToShow();

        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_PAP_LIST,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonResponse = new JSONArray(response);
                            populateList(jsonResponse);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.d("PROJECT INFO", "PROJECT INFO ERROR");


                    }
                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("project_id", "1");

                return map;
            }
        };

        helper.add(stringRequest);
    }

    public void populateList(JSONArray paramJSONArray) {
        Log.d("populateList", "1");

        for (int i = 0; i < paramJSONArray.length(); i++) {

            try {
                JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
                PapLive localPapLive = new PapLive(getActivity());
                localPapLive.setHhid(localJSONObject.getString("HHID"));
                localPapLive.setName(localJSONObject.getString("NAME"));
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


                this.papsToShow.add(localPapLive);


                Log.d("populateList", "2");
            } catch (JSONException localJSONException) {
                localJSONException.printStackTrace();
            }
        }
        Log.d("papSize", "" + this.papsToShow.size());
        this.adapter = new PapListLiveAdapter(getActivity(), this.papsToShow);
        this.livePapList.setAdapter(this.adapter);


    }

}
