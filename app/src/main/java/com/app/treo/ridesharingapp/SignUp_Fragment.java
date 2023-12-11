package com.app.treo.ridesharingapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SignUp_Fragment extends Fragment implements OnClickListener {
    private View view;
    private EditText fullName, emailId, mobileNumber, location,
            password, age, enrollno;
    private TextView login;
    private Button signUpButton;
    private CheckBox terms_conditions;
    private RadioButton rb1, rb2;
    RequestQueue requestQueue;
    StringRequest stringRequest;

    private String DataParseUrl = "https://quizknights.co.uk/register.php";

    public SignUp_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.signup_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    // Initialize all views
    private void initViews() {
        fullName = (EditText) view.findViewById(R.id.fullName);
        emailId = (EditText) view.findViewById(R.id.userEmailId);
        mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
        location = (EditText) view.findViewById(R.id.location);
        password = (EditText) view.findViewById(R.id.password);
        age = (EditText) view.findViewById(R.id.age);
        signUpButton = (Button) view.findViewById(R.id.signUpBtn);
        login = (TextView) view.findViewById(R.id.already_user);
        terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);
        rb1 = (RadioButton) view.findViewById(R.id.feRB1);
        rb2 = (RadioButton) view.findViewById(R.id.maRB2);
        enrollno = (EditText) view.findViewById(R.id.enrollNo);
        // Setting text selector over textviews
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);
            login.setTextColor(csl);
            terms_conditions.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        signUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.signUpBtn) {
            checkValidation();
        } else if (v.getId() == R.id.already_user) {
            new LoginBaseActivity().replaceLoginFragment();
        }

    }

    // Check Validation Method
    private void checkValidation() {

        // Get all edittext texts
        String getFullName = fullName.getText().toString();
        String getEmailId = emailId.getText().toString();
        String getMobileNumber = mobileNumber.getText().toString();
        String getLocation = location.getText().toString();
        String getPassword = password.getText().toString();
        String getAge = age.getText().toString();
        String getEnrollNo = enrollno.getText().toString();
        String getSex = "M";
        if (rb1.isChecked()) getSex = "F";


        if (getFullName.equals("") || getEmailId.equals("") || getMobileNumber.equals("")
                || getLocation.equals("") || getPassword.equals("") ||
                getAge.equals("") || getEnrollNo.equals(""))
            new CustomToast().Show_Toast(getActivity(), view, "All fields are required.");
        else if (!rb1.isChecked() && !rb2.isChecked())
            new CustomToast().Show_Toast(getActivity(), view, "You missed out the (fe)male part!");
        else if (Integer.parseInt(getAge) < 16)
            new CustomToast().Show_Toast(getActivity(), view,
                    "You sure you in college? You dont seem old enough!");

            // Make sure user should check Terms and Conditions checkbox
        else if (!terms_conditions.isChecked())
            new CustomToast().Show_Toast(getActivity(), view,
                    "Please select Terms and Conditions.");

            // Else do signup or do your stuff
        else {
            registerUser(getFullName, getEmailId, getMobileNumber, getLocation,
                    getPassword, getSex, getEnrollNo, getAge);
        }
    }

    public void registerUser(String name, final String email, String mob, String college,
                             String passwrd, String Sex, String Enrol, String Age) {
        final String Name = name;
        final String Email = email;
        final String Mob = mob;
        final String College = college;
        final String Pword = passwrd;
        final String sex = Sex;
        final String enroll = Enrol;
        final String age = Age;
        stringRequest = new StringRequest(Request.Method.POST, DataParseUrl, response -> {
            if (response != null && response.length() > 0)
                Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getContext(), "Something went terribly wrong! ", Toast.LENGTH_LONG).show();
        },
                error -> {
                    if (error != null && error.toString().length() > 0) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getContext(), "Something went terribly wrong! ", Toast.LENGTH_LONG).show();

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", Name);  //u_name
                params.put("mob_no", Mob);   //u_id
                params.put("college", College);   //u_college
                params.put("email", Email);     //u_email
                params.put("dob", age);     //u_dob
                params.put("sex", sex);     //u_sex
                params.put("pword", Pword);     //pass
                params.put("enroll", enroll);      //ignore
                params.put("fb_link", " ");      //u_fb

                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(40000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue = Volley.newRequestQueue(requireActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }
}