package com.parse;

import com.parse.*;
import com.parse.entity.Archer;
import com.parse.entity.ArcherQuery;

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

        // Add your initialization code here
        Parse.initialize(this, "PhyOxGjACcVSyOnIQUuwSjs2R9ORvdeWVciTHbty", "wgEpJtIAQYFz8yytcFICVv3u2fgpVmnDF5WjXeQk");


        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);


        //ArcherQuery q = new ArcherQuery();
        //TestCreation d'un archer
        //q.createArcher();

        Archer a = new Archer();
        a.setLicence("Toto");
        a.setNom("Dodo");
        a.setPrenom("LaSaumure");
        a.setAnnee(2015);
        a.setDateDeNaissance(new Date());
        a.setNiveau("1");
        try {
            a.save();
        }
        catch (ParseException e){

        }
    }
}

