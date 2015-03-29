package com.parse;

import com.parse.*;
import com.parse.entity.Archer;
import com.parse.entity.ArcherQuery;
import com.parse.entity.Arc;
import com.parse.entity.ArcQuery;
import com.parse.entity.Club;
import com.parse.entity.ClubQuery;

import android.util.Log;

import android.app.Application;

import java.util.Date;


public class ArcheryFollows extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Crash Reporting.
        ParseCrashReporting.enable(this);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        //Enregistrer les sous-classes
        ParseObject.registerSubclass(Archer.class);
        ParseObject.registerSubclass(Arc.class);
        ParseObject.registerSubclass(Club.class);

        // Add your initialization code here
        Parse.initialize(this, "PhyOxGjACcVSyOnIQUuwSjs2R9ORvdeWVciTHbty", "wgEpJtIAQYFz8yytcFICVv3u2fgpVmnDF5WjXeQk");


        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);


        ArcherQuery aq = new ArcherQuery();
        ArcQuery arcq = new ArcQuery();
        ClubQuery cq = new ClubQuery();


        //TestCreation d'un archer
        //aq.createArcher();


        Archer a = new Archer();
        a.setLicence("Toto");
        a.setNom("Dodo");
        a.setPrenom("LaSaumure");
        a.setAnnee(2015);
        a.setDateDeNaissance(new Date());
        a.setNiveau("1");
        a.setClubObjectId(cq.retrieveClubByLicence("67203001"));
        Log.v("add", cq.retrieveClubByLicence("67203001"));
        try {
            a.save();
        }
        catch (ParseException e){

        }

        //ArcQuery q = new ArcQuery();
        //q.createArc();

        Arc myArc = new Arc();
        myArc.setPuissance("1234");
        myArc.setTaille("1m50");
        myArc.setType("RobinDesBois");

        //attendu : eXPzjJDZ8P
        myArc.setProprietaireObjectId(aq.retrieveArcherByLicence("Toto"));
        try {
            myArc.save();
        }
        catch (Exception e){
            Log.v("add", e.getMessage());
        }

        cq.createClub();

        Club myClub = new Club();
        myClub.setNom("LMRTestt");
        myClub.setIdentifiant("1234A");
        myClub.setLieu("Tot");
        myClub.setPresidentObjectId(aq.retrieveArcherByLicence("Toto"));
        try {
            myClub.save();
        } catch (ParseException e)
        {
            //System.out.println(e.getMessage());
        }
    }
}

