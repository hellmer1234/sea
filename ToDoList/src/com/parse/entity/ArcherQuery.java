package com.parse.entity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;
import android.util.Log;
import com.parse.FindCallback;
import com.parse.entity.Archer;


public class ArcherQuery {
    public void createArcher()
    {
        Archer myArcher = new Archer();
        myArcher.setNom("Test");
        myArcher.setLicence("1234A");
        try {
            myArcher.save();
        } catch (ParseException e)
        {
            //System.out.println(e.getMessage());
        }
    }

    public String retrieveArcherByLicence(String licence)
    {
        ParseQuery query=new ParseQuery("Archer");
        query.whereEqualTo("Licence", licence);
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
