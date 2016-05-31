package ug.dataplus_systems.vcsmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dd.processbutton.iml.ActionProcessButton;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    EditText etUsername, etPassword;
    TextView tvForgotPassword;
    TextInputLayout tilUsername, tilPassword;
    public static final String KEY_USERNAME = "userid";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_REQUESTER = "requester";
    public static final String VALUE_MOBILE = "Mobile";

    MyApplicationClass helper = MyApplicationClass.getInstance();

    String LOGIN_URL_BAGZ = "http://bagz-vostro/vcs_prodn/mobi_login.php";

    String LOGIN_URL_ERIC = "http://192.168.0.10/VcsMobileLoginAuth/loginAuth.php";

    public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";
    ActionProcessButton btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        tvForgotPassword = (TextView) findViewById(R.id.tv_forgot_password);
        tilPassword = (TextInputLayout) findViewById(R.id.til_password);
        tilUsername = (TextInputLayout) findViewById(R.id.til_username);
        btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);



        btnSignIn.setProgress(0);

        // set progress > 0 to start progress indicator animation

        etUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSignIn.setProgress(0);
            }
        });

        etPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSignIn.setProgress(0);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                disableWhileLoading();
                btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
                btnSignIn.setProgress(1);

                if (validateEntries()) {
                    verifyDetails(etUsername.getText().toString().trim(), etPassword.getText().toString().trim());
                    if (etUsername.getText().toString().trim().equalsIgnoreCase("admin")) {
                        openProfile();
                    }
                    // openProfile();
                } else {

                    enableForEditing();
                }
            }
        });




    }

    private boolean validateEntries() {
        //method to validate that the input fields are valid eg if email field then it user should have entered a valid email

        if (etUsername.getText().toString().trim().isEmpty()) {

            tilUsername.getEditText().setError("Please enter your user name");
            btnSignIn.setErrorText("Error");
            btnSignIn.setProgress(-1);
            return false;
        }

        if (etPassword.getText().toString().trim().isEmpty()) {

            tilPassword.getEditText().setError("Please enter your password");
            btnSignIn.setErrorText("Error");
            btnSignIn.setProgress(-1);
            return false;
        }


        return true;
    }

    private boolean verifyDetails(final String username, final String password) {
        //method to verify the input details with the server

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL_ERIC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("TRUE")) {
                            Log.d("LOGIN", "Login SUCCESS");
                            openProfile();
                        } else if (response.trim().equals("FALSE")) {
                            Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();

                            btnSignIn.setErrorText("Wrong Credentials");
                            btnSignIn.setProgress(-1);
                            Log.d("LOGIN", "Login WRONG CREDITS");
                            Log.d("LOGIN RESPONSE", response);
                            enableForEditing();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        Log.d("LOGIN", "Login ERROR");
                        btnSignIn.setErrorText("Error");
                        btnSignIn.setProgress(-1);
                        enableForEditing();


                    }
                }) {

            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_REQUESTER, VALUE_MOBILE);
                map.put(KEY_USERNAME, username);
                map.put(KEY_PASSWORD, password);
                return map;
            }
        };


        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        helper.add(stringRequest);


        return false;

    }

    private void forgotPassword() {
        //if forgot password text is

    }

    private void openProfile() {

        btnSignIn.setProgress(100);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("zero", 0);
        startActivity(intent);
        finish();
    }

    private void disableWhileLoading(){

        tilUsername.getEditText().setEnabled(false);
        tilPassword.getEditText().setEnabled(false);
        btnSignIn.setEnabled(false);

    }

    private void enableForEditing(){

        tilUsername.getEditText().setEnabled(true);
        tilPassword.getEditText().setEnabled(true);
        btnSignIn.setEnabled(true);

    }

}
