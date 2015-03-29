package com.parse.entity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;

/**
 * Created by LMR on 29/03/15.
 */
public class EvenementQuery {
    public void createEvenement()
    {

        Evenement e = new Evenement();
        e.setNom("MyEvent");
        e.setDateEvenement(new Date());
        try {
            e.save();
        } catch (ParseException ep)
        {
            //System.out.println(e.getMessage());
        }
    }

    public String retrieveEventByCriterion(Date dateEvenement, String organisateur){
        ParseQuery query=new ParseQuery("Evenement");
        query.whereEqualTo("DateEvenement", dateEvenement);
        query.whereEqualTo("Organisateur", organisateur);
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
