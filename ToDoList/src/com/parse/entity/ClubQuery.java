package com.parse.entity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;



public class ClubQuery {
    public void createClub()
    {
        Club myClub = new Club();
        myClub.setNom("Test");
        myClub.setIdentifiant("1234A");
        try {
            myClub.save();
        } catch (ParseException e)
        {
            //System.out.println(e.getMessage());
        }
    }

    public String retrieveClubByIdentifiant(String identifiant)
    {
        ParseQuery query=new ParseQuery("Club");
        query.whereEqualTo("Identifiant", identifiant);
        query.setLimit(1);

        try
        {
            ParseObject po = query.getFirst();
            return po.getObjectId();
        }
        catch (ParseException e)
        {

        }
        return "NoResult";
    }
}
