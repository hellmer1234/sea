package com.parse;

import com.parse.*;
import com.parse.entity.Archer;
import com.parse.entity.ArcherQuery;
import com.parse.entity.Arc;
import com.parse.entity.ArcQuery;
import com.parse.entity.Club;
import com.parse.entity.ClubQuery;
import com.parse.entity.Blason;
import com.parse.entity.BlasonQuery;
import com.parse.entity.Evenement;
import com.parse.entity.EvenementQuery;
import com.parse.entity.Participants;
import com.parse.entity.ParticipantsQuery;
import com.parse.entity.Resultats;
import com.parse.entity.ResultatsQuery;
import com.parse.util.Utils;

import android.util.Log;

import android.app.Application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


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
        ParseObject.registerSubclass(Blason.class);
        ParseObject.registerSubclass(Evenement.class);
        ParseObject.registerSubclass(Participants.class);
        ParseObject.registerSubclass(Resultats.class);

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
        BlasonQuery bq = new BlasonQuery();
        EvenementQuery eq = new EvenementQuery();
        ParticipantsQuery pq = new ParticipantsQuery();
        ResultatsQuery rq = new ResultatsQuery();

        //TestCreation d'un archer
        //aq.createArcher();


        Archer a = new Archer();
        a.setLicence("Toto");
        a.setNom("Dodo");
        a.setPrenom("LaSaumure");
        a.setAnnee(2015);
        a.setDateDeNaissance(new Date());
        a.setNiveau("1");
        a.setClubObjectId(cq.retrieveClubByIdentifiant("67203001"));
        Log.v("add", cq.retrieveClubByIdentifiant("67203001"));
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

     //   cq.createClub();

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

     //   eq.createEvenement();

        Calendar c = GregorianCalendar.getInstance();
        Calendar sysdate = GregorianCalendar.getInstance();
        TimeZone tz = TimeZone.getTimeZone("Europe/Paris");
        sysdate.setTimeZone(tz);
        //sysdate.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, 0,0,0);
        sysdate.set(Calendar.YEAR, c.get(Calendar.YEAR));
        sysdate.set(Calendar.MONTH, c.get(Calendar.MONTH));
        sysdate.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
        sysdate.set(Calendar.HOUR_OF_DAY, 0);
        sysdate.set(Calendar.MINUTE, 0);
        sysdate.set(Calendar.SECOND, 0);

        Date sysdate_as_date = sysdate.getTime();
        /*sysdate_as_date.setHours(0);
        sysdate_as_date.setMinutes(0);
        sysdate_as_date.setSeconds(0);*/

        Date today = Calendar.getInstance().getTime();
        String dateEvent = Utils.getDateInFormat(today);

        Evenement myEvent = new Evenement();
        myEvent.setNom("MyEvent");
        myEvent.setDateEvenement(sysdate_as_date);
        myEvent.setDateEvent(dateEvent);
        myEvent.setClubObjectId(cq.retrieveClubByIdentifiant("67203001"));
        try {
            myEvent.save();
        } catch (ParseException e)
        {
            //System.out.println(e.getMessage());
        }

     //   bq.createBlason();

        Blason myBlason = new Blason();
        myBlason.setDiametre("Test");
        myBlason.setDistance("1234A");
        myBlason.setEvenementObjectId(myEvent.getObjectId());
        try {
            myBlason.save();
        } catch (ParseException e)
        {
            //System.out.println(e.getMessage());
        }

     //   pq.inscription();

        String event = eq.retrieveEventByCriterion(dateEvent, "67203001");

        Participants myInscription = new Participants();
        myInscription.setParticipantObjectId(aq.retrieveArcherByLicence("Toto"));
        myInscription.setEvenementObjectId(event);
        try {
            myInscription.save();
        } catch (ParseException e)
        {
            //System.out.println(e.getMessage());
        }

     //   rq.sauvegarder();

        Resultats resultats = new Resultats();
        resultats.setEvenementObjectId(event);
        resultats.setArcherObjectId(aq.retrieveArcherByLicence("Toto"));
        resultats.setBlasonObjectId(myBlason.getObjectId());
        resultats.setVolee("1");
        resultats.setFleche1(9);
        resultats.setFleche2(10);
        resultats.setFleche3(3);
        resultats.setStatutF1("OK");
        resultats.setStatutF2("M");
        resultats.setStatutF3("P");
        try {
            resultats.save();
        } catch (ParseException e)
        {
            //System.out.println(e.getMessage());
        }
    }
}

