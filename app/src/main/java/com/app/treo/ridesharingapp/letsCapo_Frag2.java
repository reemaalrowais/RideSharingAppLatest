package com.app.treo.ridesharingapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class letsCapo_Frag2 extends Fragment implements View.OnClickListener {

    private static View view;
    private static Button button;
    private static EditText to, from, fromTime;
    private static FragmentManager fragmentManager;

    public letsCapo_Frag2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lets_capo__frag2, container, false);
        initViews();
        setListeners();
        return view;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        button = (Button) view.findViewById(R.id.b1);
        to = (EditText) view.findViewById(R.id.to);
        from = (EditText) view.findViewById(R.id.from);
        fromTime = (EditText) view.findViewById(R.id.fromTime);
    }

    private void setListeners() {
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.b1) {
            if (to.getText().toString().equals("") || fromTime.getText().toString().equals("") || from.getText().toString().equals("")) {
                Log.e("in lets capo frag1", " equals null");
                new CustomToast().Show_Toast(getActivity(), view, "Please enter All details in order to proceed.");
            } else {

                new CustomToast().Show_Toast(getActivity(), view, " Awesome! We got your data! Will let you know when we get something!");

                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);

            }
        } else if (v.getId() == R.id.to) {
            Log.e("ablah ", " ablhad ");
        }
    }
}


