package ug.dataplus_systems.vcsmobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectInfoFragment extends Fragment {

    MyApplicationClass helper = MyApplicationClass.getInstance();

    public static final String KEY_PROJECT_ID = "project_id";

    String URL_GET_PROJECT_DETAILS = "http://192.168.0.10/VcsMobileLoginAuth/getProjectDetails.php";

    TextView tvProjectName, tvProjectCode, tvProjectClient, tvProjectObjective, tvProjectStartDate, tvProjectEndDate, tvProjectManager;

    View rv;

    public ProjectInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rv = inflater.inflate(R.layout.fragment_project_info, container, false);

        tvProjectName = (TextView) rv.findViewById(R.id.tv_project_name);
        tvProjectCode = (TextView) rv.findViewById(R.id.tv_project_code);
        tvProjectClient = (TextView) rv.findViewById(R.id.tv_project_client);
        tvProjectObjective = (TextView) rv.findViewById(R.id.tv_project_objective);
        tvProjectStartDate = (TextView) rv.findViewById(R.id.tv_project_start_date);
        tvProjectEndDate = (TextView) rv.findViewById(R.id.tv_project_end_date);
        tvProjectManager = (TextView) rv.findViewById(R.id.tv_project_manager);

        return rv;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_PROJECT_DETAILS,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            populateFields(jsonResponse);
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
                map.put(KEY_PROJECT_ID, "1");

                return map;
            }
        };

        helper.add(stringRequest);

    }

    private void populateFields(JSONObject response) {


        try {
            tvProjectName.setText(response.getString("project_name"));
            tvProjectCode.setText(response.getString("project_code"));
            tvProjectClient.setText(response.getString("project_client"));
            tvProjectObjective.setText(response.getString("project_objective"));
            tvProjectStartDate.setText(response.getString("project_start_date"));
            tvProjectEndDate.setText(response.getString("project_end_date"));
            tvProjectManager.setText(response.getString("project_manager"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
