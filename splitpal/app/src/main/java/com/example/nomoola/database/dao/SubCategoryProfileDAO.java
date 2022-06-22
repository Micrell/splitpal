package com.example.nomoola.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nomoola.database.entity.Profile;
import com.example.nomoola.database.entity.SubCategoryProfile;

import java.util.List;

@Dao
public interface SubCategoryProfileDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubCatProfile(SubCategoryProfile...subCategoryProfiles);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSubCatProfile(SubCategoryProfile...subCategoryProfiles);

    @Delete
    void deleteSubCatProfile(SubCategoryProfile...subCategoryProfiles);

    @Query("SELECT * " +
            "FROM T_SUBCATEGORYPROFILE")
    LiveData<List<SubCategoryProfile>> getAllSubCatProfile();

    @Query("SELECT T_PROFILE.* " +
            "FROM T_PROFILE INNER JOIN T_SUBCATEGORYPROFILE " +
            "ON T_PROFILE.PROFILE_ID = T_SUBCATEGORYPROFILE.PROFILE_ID " +
            "WHERE T_SUBCATEGORYPROFILE.SUBCAT_ID=:subCatID")
    LiveData<List<Profile>> getProfileFromSubCat(int subCatID);

    @Query("SELECT COUNT(SUBCATEGORYPROFILE_ID)" +
            "FROM T_SUBCATEGORYPROFILE " +
            "WHERE SUBCAT_ID=:subCatID ")
    LiveData<Integer> getNumberOfMembersInSubCategory(int subCatID);

}
