package com.app.treo.ridesharingapp;

import static org.junit.Assert.*;

import junit.framework.TestCase;

import org.junit.Test;

public class RideTest extends TestCase {

    @Test
    public void testRide() {
        Ride ride = new Ride();
        assertNotNull(ride);
    }

    @Test
    public void testRide2() {
        Ride ride = new Ride("PP", "PP_time", "PP_price", "PP_id", "college", "rid");
        assertEquals("PP", ride.getPp());
        assertEquals("PP_time", ride.getPp_time());
        assertEquals("PP_price", ride.getPp_price());
        assertEquals("PP_id", ride.getPp_id());
        assertEquals("college", ride.getDesti_loc());
        assertEquals("rid", ride.getR_id());

    }

    @Test
    public void testRide3() {
        Ride ride = new Ride("R_ID", "RIDER_ID", "PP_ID", "col");
        assertEquals("R_ID", ride.getR_id());
        assertEquals("RIDER_ID", ride.getRider_id());
        assertEquals("PP_ID", ride.getPp_id());
        assertEquals("col", ride.getDesti_loc());
    }

    @Test
    public void testRide4() {
        Ride ride = new Ride("startLoc", "DestiLoc", "seats", "stTime", "DestiTime", "DateOride", "R_id");
        assertEquals("startLoc", ride.getStart_loc());
        assertEquals("DestiLoc", ride.getDesti_loc());
        assertEquals("seats", ride.getNo_seats());
        assertEquals("stTime", ride.getStart_time());
        assertEquals("DestiTime", ride.getExp_desti_time());
        assertEquals("DateOride", ride.getDateORide());
        assertEquals("R_id", ride.getR_id());
    }

    @Test
    public void testGetR_id() {
        Ride ride = new Ride();
        assertEquals(null, ride.getR_id("mobNo"));
    }


}