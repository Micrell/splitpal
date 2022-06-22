package com.example.nomoola.database.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "T_SUBCATEGORYPROFILE")
public class SubCategoryProfile {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "SUBCATEGORYPROFILE_ID")
    private int m_SUBCATPROFILE_ID;

    @NonNull
    @ColumnInfo(name = "SUBCAT_ID")
    private int m_SUBCAT_ID;

    @NonNull
    @ColumnInfo(name = "PROFILE_ID")
    private int m_PROFILE_ID;


    public SubCategoryProfile(){

    }

    public SubCategoryProfile(@NonNull int subcatID, @NonNull int profileID){
        this.m_SUBCAT_ID = subcatID;
        this.m_PROFILE_ID = profileID;
    }

    public int getM_SUBCATPROFILE_ID() {
        return m_SUBCATPROFILE_ID;
    }

    public void setM_SUBCATPROFILE_ID(int m_SUBCATPROFILE_ID) {
        this.m_SUBCATPROFILE_ID = m_SUBCATPROFILE_ID;
    }

    public int getM_SUBCAT_ID() {
        return m_SUBCAT_ID;
    }

    public void setM_SUBCAT_ID(int m_SUBCAT_ID) {
        this.m_SUBCAT_ID = m_SUBCAT_ID;
    }

    public int getM_PROFILE_ID() {
        return m_PROFILE_ID;
    }

    public void setM_PROFILE_ID(int m_PROFILE_ID) {
        this.m_PROFILE_ID = m_PROFILE_ID;
    }
}
