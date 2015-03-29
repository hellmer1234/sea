package com.parse.entity;

import com.parse.ParseException;


public class BlasonQuery {

    public void createBlason()
    {
        Blason myBlason = new Blason();
        myBlason.setDiametre("Test");
        myBlason.setDistance("1234A");
        try {
            myBlason.save();
        } catch (ParseException e)
        {
            //System.out.println(e.getMessage());
        }
    }
}
