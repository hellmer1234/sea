package com.parse.entity;

import com.parse.ParseException;
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

    public void main()
    {
        createArcher();
    }
}
