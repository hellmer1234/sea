package com.parse.entity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;



public class ClubQuery {
    public String retrieveClubByLicence(String licence)
    {
        ParseQuery query=new ParseQuery("Club");
        query.whereEqualTo("Identifiant", licence);
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
