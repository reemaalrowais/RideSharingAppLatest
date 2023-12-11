package com.app.treo.ridesharingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class MyProfile extends BaseActivity {

    TextView tvName, tvEmail, tvMobile, tvCardets, tvCollege, tvEnroll;
    ImageView dp;
    String id ;
    String name;
    String dets;
    String email;
    String college;
    String enroll;
    String dpres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//////////////////////////////////////////////////Code begins
        SharedPreferences spref = getSharedPreferences(BaseActivity.MyPref, Context.MODE_PRIVATE);
         name = spref.getString(BaseActivity.Name, null); // getting String
         id = spref.getString(BaseActivity.Phone, null);
         email = spref.getString(BaseActivity.Email, null);
        college = spref.getString(BaseActivity.College, null);
        enroll = spref.getString(BaseActivity.Enroll, null);
        if (spref.getString(BaseActivity.displaypic, null).trim().equals("F")) {
            dpres = "@drawable/fpp";
        } else {
            dpres = "@drawable/mpp";
        }
        dets = spref.getString(BaseActivity.Extras, null);



        tvName = (TextView)findViewById(R.id.tvName);
        tvEmail = (TextView)findViewById(R.id.tvEmail);
        tvMobile = (TextView)findViewById(R.id.tvMobile);
        tvCardets = (TextView)findViewById(R.id.tvCar);
        tvCollege = (TextView) findViewById(R.id.tvCollege);
        tvEnroll = (TextView) findViewById(R.id.tvEnroll);
        dp = (ImageView)findViewById(R.id.iv1);
        int id1 = getResources().getIdentifier(dpres, "drawable", getPackageName());
        dp.setImageResource(id1);
        tvName.setText(name);
        Log.e("Name : ","name "+name);
        tvEmail.setText(email);
        tvMobile.setText(id);
        tvCollege.setText(college);
        tvCardets.setText(dets);
        tvEnroll.setText(enroll);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            Intent i = new Intent(MyProfile.this, MainActivity.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_get_capo) {
            Intent i = new Intent(MyProfile.this, Track.class);
            startActivity(i);
            finish();


        } else if (id == R.id.nav_lets_capo) {
            Intent i = new Intent(MyProfile.this, LetsCapo.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_track) {
            Intent i = new Intent(MyProfile.this, Track.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_share) {
            String shareBody = "This is an invite by your dear friend to come and join this community where people care and are making a change!" +
                    "Install Capo today from ... ";
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Greeting from Capo");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));

        } else if (id == R.id.nav_logout) {
            SharedPreferences preferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(Name, "null" );
            editor.putString(IS_LOGIN, "false");
            editor.putString(Phone, "null" );
            editor.putString(College, "null" );
            editor.putString(Email, "null" );
            editor.putString(displaypic, "null" );

            editor.commit();

            Intent i = new Intent(MyProfile.this,LoginBaseActivity.class);
            startActivity(i);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
