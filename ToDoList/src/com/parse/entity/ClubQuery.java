package com.parse.entity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


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

    public String retrieveClubIdByIdentifiant(String identifiant)
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

    public List<String> retrieveArchersIdList(String identifiant)
    {
        ParseQuery query=new ParseQuery("Archer");
        query.whereEqualTo("AppartientAClub", ParseObject.createWithoutData("Club", retrieveClubIdByIdentifiant(identifiant)));
        //query.setLimit(1);

        try
        {
            List<ParseObject> resultset = query.find();
            List<String> resultat = new ArrayList<String>();;

            for(int i=0; i<resultset.size(); i++)
                resultat.add(i, resultset.get(i).getObjectId());

            return resultat;
        }
        catch (ParseException e)
        {

        }
        return null;
    }

    public List<Archer> retrieveArchersList(String identifiant)
    {
        ParseQuery query=new ParseQuery("Archer");
        query.whereEqualTo("AppartientAClub", ParseObject.createWithoutData("Club", retrieveClubIdByIdentifiant(identifiant)));
        //query.setLimit(1);

        try
        {
            List<ParseObject> resultset = query.find();
            List<Archer> resultat = new ArrayList<Archer>();;

            for(int i=0; i<resultset.size(); i++)
                resultat.add(i, (Archer)resultset.get(i));

            return resultat;
        }
        catch (ParseException e)
        {

        }
        return null;
    }
}
