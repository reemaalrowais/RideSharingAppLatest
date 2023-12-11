package com.app.treo.ridesharingapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Login_Fragment extends Fragment implements OnClickListener {

    private static View view;
    private static EditText emailid, password;
    private static Button loginButton;
    private static TextView forgotPassword, signUp;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    String final_response;

    private static String DataParseUrl = "https://quizknights.co.uk/login.php";

    public Login_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    // Initiate Views
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();

        emailid = (EditText) view.findViewById(R.id.login_emailid);
        password = (EditText) view.findViewById(R.id.login_password);
        loginButton = (Button) view.findViewById(R.id.loginBtn);
        forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
        signUp = (TextView) view.findViewById(R.id.createAccount);
        show_hide_password = (CheckBox) view
                .findViewById(R.id.show_hide_password);
        loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);

        // Setting text selector over textviews
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            forgotPassword.setTextColor(csl);
            show_hide_password.setTextColor(csl);
            signUp.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);

        // Set check listener over checkbox for showing and hiding password
        show_hide_password
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checkec then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText(R.string.show_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginBtn) {
            checkValidation();
        } else if (v.getId() == R.id.forgot_password) {
            fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                    .replace(R.id.frameContainer,
                            new ForgotPassword_Fragment(),
                            BaseActivity.ForgotPassword_Fragment).commit();
        } else if (v.getId() == R.id.createAccount) {
            fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                    .replace(R.id.frameContainer, new SignUp_Fragment(),
                            BaseActivity.SignUp_Fragment).commit();
        }

    }


    // Check Validation before login
    private void checkValidation() {
        // Get email id and password
        String getEmailId = emailid.getText().toString().trim();
        String getPassword = password.getText().toString().trim();

        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(), view,
                    "Enter both credentials.");

        } else {
            checkAuthenticity(getEmailId, getPassword);
        }
    }

    private void checkAuthenticity(String getEmailId, String getPassword) {
        final String mobile = getEmailId;
        final String pword = getPassword;

        stringRequest = new StringRequest(Request.Method.POST, DataParseUrl, response -> {
            final_response = response;
            Toast.makeText(getActivity().getApplicationContext(), "  " + response, Toast.LENGTH_SHORT).show();
            Log.e("response: ", "  " + response);
            if (!response.equals("NO_PROF")) {
                verified();

            } else {
                Toast.makeText(getActivity().getApplicationContext(), "wrong username or password", Toast.LENGTH_LONG).show();
                Log.e("in login frag", "" + response);
            }

        },
                error -> {
                    if (error != null && error.toString().length() > 0)
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getContext(), "Something went terribly wrong! ", Toast.LENGTH_LONG).show();

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mob_no", mobile);
                params.put("password", pword);

                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(40000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue = Volley.newRequestQueue(requireActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void verified() {
        Toast.makeText(getActivity(), "Successfully Loged IN!", Toast.LENGTH_SHORT).show();
        SharedPreferences preferences = this.getActivity().getSharedPreferences(BaseActivity.MyPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String[] tokens = final_response.split(">");
        //Toast.makeText(getActivity().getApplicationContext(), " 0Name :"+tokens[0]+ " 1Phone :"+ tokens[1]+ " 2College :"+ tokens[2], Toast.LENGTH_SHORT).show();
        Log.e("!!!!!!", " Name :" + tokens[0] + " Phone :" + tokens[1] + " College :" +
                tokens[2] + " email :" + tokens[3] + " sex :>" + tokens[4] + "< enroll :" + tokens[5] + " fb_link :" + tokens[6]);
        editor.putString(BaseActivity.Name, tokens[0]);
        editor.putString(BaseActivity.IS_LOGIN, "true");
        editor.putString(BaseActivity.Phone, tokens[1]); //1
        editor.putString(BaseActivity.displaypic, tokens[4]);  //4
        editor.putString(BaseActivity.College, tokens[2]); //2
        editor.putString(BaseActivity.Email, tokens[3]);  //3
        editor.putString(BaseActivity.Enroll, tokens[5]);  //5
        editor.putString(BaseActivity.fb_link, tokens[6]);  //6

        editor.putString(BaseActivity.Extras, "Female, 19 years old, does not have license :O ");
        editor.commit();
        Toast.makeText(getActivity(), "Shared Pref added!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);


    }

}