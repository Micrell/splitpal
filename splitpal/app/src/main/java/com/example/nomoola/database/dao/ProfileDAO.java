package com.example.nomoola.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nomoola.database.entity.Profile;

import java.util.List;

@Dao
public interface ProfileDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProfile(Profile...profiles);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateProfile(Profile...profiles);

    @Delete
    void deleteProfile(Profile profile);

    @Query("SELECT * " +
            "FROM T_PROFILE")
    LiveData<Profile> getProfile();

    @Query("SELECT * " +
            "FROM T_PROFILE " +
            "WHERE PROFILE_ID=:userID")
    LiveData<Profile> getProfileOf(int userID);

    @Query("UPDATE T_PROFILE SET PROFILE_NAME=:userName, PROFILE_CURRENCY=:currency, PROFILE_LANGUAGE=:language WHERE PROFILE_ID=:userID ")
    void updateProfile(int userID, String userName, Profile.userLanguage language, Profile.userCurrency currency);

    @Query("UPDATE T_PROFILE SET PROFILE_NAME=:userName WHERE PROFILE_ID=:userID")
    void setUserName(int userID, String userName);

    @Query("UPDATE T_PROFILE SET PROFILE_LANGUAGE=:language WHERE PROFILE_ID=:userID")
    void setLanguage(int userID, Profile.userLanguage language);

    @Query("UPDATE T_PROFILE SET PROFILE_CURRENCY=:currency WHERE PROFILE_ID=:userID")
    void setCurrency(int userID, Profile.userCurrency currency);

    @Query("SELECT PROFILE_NAME " + "FROM T_PROFILE " + "WHERE PROFILE_ID=:userID")
    LiveData<String> getUserName(int userID);

    @Query("SELECT PROFILE_ID " +
            "FROM T_PROFILE")
    int getUserID();

    @Query("SELECT PROFILE_NAME " +
            "FROM T_PROFILE " +
            "WHERE PROFILE_ID=:profileID")
    LiveData<String> getProfileNameAccordingToID(int profileID);
}
