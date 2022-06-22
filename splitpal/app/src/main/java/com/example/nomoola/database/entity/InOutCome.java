package com.example.nomoola.database.entity;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "T_INOUTCOME")
public class InOutCome {

    /**
     * ATTRIBUTE
     */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "INOUTCOME_ID")
    private int m_INOUTCOME_ID;

    @NonNull
    @ColumnInfo(name = "INOUTCOME_NAME")
    private String m_INOUTCOME_NAME;

    @NonNull
    @ColumnInfo(name = "SUBCAT_ID")
    private int m_SUBCAT_ID;

    @ColumnInfo(name = "INOUTCOME_AMOUNT")
    private double m_INOUTCOME_AMOUNT;

    @NonNull
    @ColumnInfo(name = "INOUTCOME_DATE")
    private LocalDate m_INOUTCOME_DATE;

    @NonNull
    @ColumnInfo(name = "INOUTCOME_OWNER_ID")
    private int m_INOUTCOME_OWNER_ID;



    /**
     * Constructor
     */

    public InOutCome(){

    }

    public InOutCome(String inOutName, int subCatID, Double amount, LocalDate date, int ownerID){
        Log.d("CREATION", "Instantiation of Category = " + inOutName);
        this.m_INOUTCOME_NAME = inOutName;
        this.m_SUBCAT_ID = subCatID;
        this.m_INOUTCOME_AMOUNT = amount;
        this.m_INOUTCOME_DATE = date;
        this.m_INOUTCOME_OWNER_ID = ownerID;

    }

    /**
     *  GETTER / SETTER
     */

    @NonNull
    public String getM_INOUTCOME_NAME() {
        return m_INOUTCOME_NAME;
    }

    public void setM_INOUTCOME_NAME(@NonNull String m_INOUTCOME_NAME) {
        this.m_INOUTCOME_NAME = m_INOUTCOME_NAME;
    }

    @NonNull
    public int getM_INOUTCOME_ID() {
        return m_INOUTCOME_ID;
    }

    public void setM_INOUTCOME_ID(@NonNull int m_INOUTCOME_ID) {
        this.m_INOUTCOME_ID = m_INOUTCOME_ID;
    }

    public int getM_SUBCAT_ID() {
        return m_SUBCAT_ID;
    }

    public void setM_SUBCAT_ID(int m_SUBCAT_ID) {
        this.m_SUBCAT_ID = m_SUBCAT_ID;
    }

    public double getM_INOUTCOME_AMOUNT() {
        return m_INOUTCOME_AMOUNT;
    }

    public void setM_INOUTCOME_AMOUNT(double m_INOUTCOME_AMOUNT) {
        this.m_INOUTCOME_AMOUNT = m_INOUTCOME_AMOUNT;
    }

    @NonNull
    public LocalDate getM_INOUTCOME_DATE() {
        return m_INOUTCOME_DATE;
    }

    public void setM_INOUTCOME_DATE(@NonNull LocalDate m_INOUTCOME_DATE) {
        this.m_INOUTCOME_DATE = m_INOUTCOME_DATE;
    }

    public int getM_INOUTCOME_OWNER_ID() {
        return m_INOUTCOME_OWNER_ID;
    }

    public void setM_INOUTCOME_OWNER_ID(int m_INOUTCOME_OWNER_ID) {
        this.m_INOUTCOME_OWNER_ID = m_INOUTCOME_OWNER_ID;
    }
}
