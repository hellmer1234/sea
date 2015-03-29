package com.parse.entity;


import com.parse.ParseException;

public class ParticipantsQuery {
    public void inscription()
    {
        Participants inscription = new Participants();
        //inscription.setEvenementObjectId();
        //inscription.setParticipantObjectId();
        try {
            inscription.save();
        } catch (ParseException e)
        {
            //System.out.println(e.getMessage());
        }

    }
}
