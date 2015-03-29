package com.parse.entity;

import com.parse.*;
import java.util.Date;

@ParseClassName("Evenement")
public class Evenement extends ParseObject {
    public Evenement() {
        // A default constructor is required.
    }

    public String getNom(){
        return getString("Nom");
    }

    public Date getDateEvenement(){
        return getDate("DateEvenement");
    }

    public String getOrganisateurObjectId(){
        return getString("Organisateur");
    }

    public void setObjectId(String objectId){
        put("objectId", objectId);
    }

    public void setNom(String nom){
        put("Nom", nom);
    }

    public void setDateEvenement(Date dateEvenement){
        put("DateEvenement", dateEvenement);
    }

    public void setClubObjectId( String clubObjectId ){
        put("Organisateur", ParseObject.createWithoutData("Club", clubObjectId));
    }
}
