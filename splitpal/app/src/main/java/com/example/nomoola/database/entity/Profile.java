package com.example.nomoola.database.entity;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "T_PROFILE")
public class Profile {

    /**
     * ATTRIBUTE
     */
    public enum userLanguage {
        FRENCH, ENGLISH;

        @Override
        public String toString() {
            if (this == FRENCH) {
                return "Fr";
            } else if (this == ENGLISH) {
                return "";
            } else {
                return super.toString();
            }
        }
    }

    public enum userCurrency {
        EURO, USDOLLAR;

        @Override
        public String toString() {
            if (this == EURO) {
                return "Euro";
            } else if (this == USDOLLAR) {
                return "USdollar";
            } else {
                return super.toString();
            }
        }
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "PROFILE_ID")
    private int m_PROFILE_ID;

    @NonNull
    @ColumnInfo(name = "PROFILE_NAME")
    private String m_USERNAME;

    @NonNull
    @ColumnInfo(name = "PROFILE_LANGUAGE")
    private userLanguage m_LANGUAGE;


    @NonNull
    @ColumnInfo(name = "PROFILE_CURRENCY")
    private userCurrency m_CURRENCY;


    /**
     * CONSTRUCTOR
     */

    public Profile(){
    }

    public Profile(@NonNull String userName, @NonNull userLanguage language, @NonNull userCurrency currency){
        Log.d("CREATION", "Instantiation of Profile = " + " "+userName);
        this.m_USERNAME = userName;
        this.m_LANGUAGE = language;
        this.m_CURRENCY = currency;
    }



    /**
     * GETTER / SETTER
     */

    public int getM_PROFILE_ID() {
        return m_PROFILE_ID;
    }

    public void setM_PROFILE_ID(int m_PROFILE_ID) {
        this.m_PROFILE_ID = m_PROFILE_ID;
    }

    @NonNull
    public String getM_USERNAME() {
        return m_USERNAME;
    }

    public void setM_USERNAME(@NonNull String m_USERNAME) {
        this.m_USERNAME = m_USERNAME;
    }

    @NonNull
    public userLanguage getM_LANGUAGE() {
        return m_LANGUAGE;
    }

    public void setM_LANGUAGE(@NonNull userLanguage m_LANGUAGE) {
        this.m_LANGUAGE = m_LANGUAGE;
    }

    @NonNull
    public userCurrency getM_CURRENCY() {
        return m_CURRENCY;
    }

    public void setM_CURRENCY(@NonNull userCurrency m_CURRENCY) {
        this.m_CURRENCY = m_CURRENCY;
    }
}