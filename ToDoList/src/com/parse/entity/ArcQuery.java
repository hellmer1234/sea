package com.parse.entity;


import com.parse.ParseException;
import com.parse.entity.Arc;


public class ArcQuery {
    public void createArc()
    {
        Arc myArc = new Arc();
        myArc.setTaille("Test");
        myArc.setType("1234A");
        myArc.setPuissance("9999");
        try {
            myArc.save();
        } catch (ParseException e)
        {
            //System.out.println(e.getMessage());
        }
    }
}
