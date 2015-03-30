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

        //TestCreation
        //aq.createArcher();
        //arcq.createArc();
        //cq.createClub();
        //eq.createEvenement();
        //bq.createBlason();
        //pq.inscription();
        //rq.sauvegarder();

        String licenceArcher = "Toto";
        String identifiantClub = "67203001";

        Date today = Calendar.getInstance().getTime();
        String dateEvent = Utils.getDateInFormat(today);

        String distance = "1234A";

        aArcher(cq, identifiantClub);
        bArc(aq, licenceArcher);
        cClub(aq, licenceArcher);
        dEvenement(cq, identifiantClub);
        eBlason(eq, dateEvent, identifiantClub);
        fParticipants(aq, eq, licenceArcher, dateEvent, identifiantClub);
        gResultats(aq, eq, bq, licenceArcher, dateEvent, identifiantClub, distance);

        hAuthenticate(aq, licenceArcher, "test");
        hAuthenticate(aq, licenceArcher, "test2");







    }

    public void aArcher(ClubQuery cq, String identifiantClub)
    {
        Archer a = new Archer();
        a.setLicence("Toto");
        a.setNom("Dodo");
        a.setPrenom("LaSaumure");
        a.setAnnee(2015);
        a.setDateDeNaissance(new Date());
        a.setNiveau("1");
        a.setMotDePasse(Utils.MD5("test"));
        a.setClubObjectId(cq.retrieveClubByIdentifiant(identifiantClub));

        save(a);
    }

    public void bArc(ArcherQuery aq, String licenceArcher)
    {
        Arc myArc = new Arc();
        myArc.setPuissance("1234");
        myArc.setTaille("1m50");
        myArc.setType("RobinDesBois");

        myArc.setProprietaireObjectId(aq.retrieveArcherByLicence(licenceArcher));
        save(myArc);
    }

    public void cClub(ArcherQuery aq, String licenceArcher)
    {
        Club myClub = new Club();
        myClub.setNom("LMRTestt");
        myClub.setIdentifiant("1234A");
        myClub.setLieu("Tot");
        myClub.setPresidentObjectId(aq.retrieveArcherByLicence(licenceArcher));

        save(myClub);
    }

    public void dEvenement(ClubQuery cq, String identifiantClub)
    {
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
        Date today = Calendar.getInstance().getTime();
        String dateEvent = Utils.getDateInFormat(today);

        Evenement myEvent = new Evenement();
        myEvent.setNom("MyEvent");
        myEvent.setDateEvenement(sysdate_as_date);
        myEvent.setDateEvent(dateEvent);
        myEvent.setClubObjectId(cq.retrieveClubByIdentifiant(identifiantClub));

        save(myEvent);
    }

    public void eBlason(EvenementQuery eq, String dateEvent, String identifiantClub)
    {
        Blason myBlason = new Blason();
        myBlason.setDiametre("Test");
        myBlason.setDistance("1234A");
        myBlason.setEvenementObjectId(eq.retrieveEventByCriterion(dateEvent, identifiantClub));

        save(myBlason);
    }

    public void fParticipants(ArcherQuery aq, EvenementQuery eq, String licenceArcher, String dateEvent, String identifiantClub)
    {
        Participants myInscription = new Participants();
        myInscription.setParticipantObjectId(aq.retrieveArcherByLicence(licenceArcher));
        myInscription.setEvenementObjectId(eq.retrieveEventByCriterion(dateEvent, identifiantClub));

        save(myInscription);
    }

    public void gResultats(ArcherQuery aq, EvenementQuery eq, BlasonQuery bq,
                           String licenceArcher, String dateEvent, String identifiantClub, String distance)
    {
        Resultats resultats = new Resultats();
        resultats.setEvenementObjectId(eq.retrieveEventByCriterion(dateEvent, identifiantClub));
        resultats.setArcherObjectId(aq.retrieveArcherByLicence(licenceArcher));
        resultats.setBlasonObjectId(bq.retrieveBlasonByCriterion(dateEvent, identifiantClub, distance));
        resultats.setVolee("1");
        resultats.setFleche1(9);
        resultats.setFleche2(10);
        resultats.setFleche3(3);
        resultats.setStatutF1("OK");
        resultats.setStatutF2("M");
        resultats.setStatutF3("P");

        save(resultats);
    }

    public void hAuthenticate(ArcherQuery aq, String licence, String password)
    {
        System.out.println(aq.authenticate(licence, password));
    }


    public void save(ParseObject item)
    {
        try
        {
            item.save();
        }
        catch (Exception e){
            Log.v("add", e.getMessage());
        }
    }
}

