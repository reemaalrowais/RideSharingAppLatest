package com.app.treo.ridesharingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RequestCapoAdapter extends ArrayAdapter<Ride> {

    public RequestCapoAdapter(Context context, ArrayList<Ride> ridesArrayList){
        super(context, 0, ridesArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        //getting the view into a variable listItemView
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.ride_item_found, parent, false);
        }
        //This is to ensure the reusablity of a view

        Ride currentRide = getItem(position);

        TextView fromTo = (TextView) listItemView.findViewById(R.id.textViewDirections);
        fromTo.setText("pickuppoint ID: "+currentRide.getPp_id());

        TextView price = (TextView) listItemView.findViewById(R.id.textClock);
        price.setText("RideId@ " + (currentRide.getR_id()));

        TextView pricetag = (TextView) listItemView.findViewById(R.id.pricetag);
        pricetag.setText("RiderId: " + currentRide.getRider_id());

        return listItemView;
    }
}

