package com.example.nomoola.database.repository;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;

import com.example.nomoola.database.dao.InOutComeDAO;
import com.example.nomoola.database.dao.SubCategoryDAO;
import com.example.nomoola.database.dao.ProfileDAO;
import com.example.nomoola.database.dao.SubCategoryProfileDAO;
import com.example.nomoola.database.entity.InOutCome;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.database.entity.Profile;
import com.example.nomoola.database.entity.SubCategoryProfile;
import com.example.nomoola.database.roomDataBase.NomoolaRoomDataBase;

import java.util.List;

public class DataRepository {

    private SubCategoryDAO mSubCategoryDAO;
    private LiveData<List<SubCategory>> mAllSubCategory;

    private InOutComeDAO mInOutComeDAO;
    private LiveData<List<InOutCome>> mAllInOutCome;

    private ProfileDAO mProfileDAO;
    private LiveData<Profile> mProfile;

    private SubCategoryProfileDAO mSubCategoryProfileDAO;

    public DataRepository(Application application) {
        Log.d("CREATION", "Instantiation of CategoryRepository");
        NomoolaRoomDataBase db = NomoolaRoomDataBase.getDatabase(application);

        mSubCategoryDAO = db.subCategoryDAO();
        mAllSubCategory = mSubCategoryDAO.getAllSubCategories();

        mInOutComeDAO = db.inOutComeDAO();
        mAllInOutCome = mInOutComeDAO.getALlInOutComes();

        mProfileDAO = db.profileDAO();
        mProfile = mProfileDAO.getProfile();

        mSubCategoryProfileDAO = db.subCategoryProfileDAO();
    }


    /*
        SUBCATEGORY
     */

    public LiveData<List<SubCategory>> getAllSubCategories(){
        return mAllSubCategory;
    }

    public void insert(SubCategory subCategory) {
        NomoolaRoomDataBase.databaseWriteExecutor.execute(() -> {
            mSubCategoryDAO.insertSubCategory(subCategory);
        });
    }
    public void delete(SubCategory subCategory){
        NomoolaRoomDataBase.databaseWriteExecutor.execute(()->{
            mSubCategoryDAO.deleteSubCategory(subCategory);
        });
    }

    public LiveData<List<String>> getAllSubCategoriesNames() {
        return this.mSubCategoryDAO.getAllSubCategoriesNames();
    }

    public SubCategory getSubCategoriesNamed(String name) {
        return this.mSubCategoryDAO.getSubCategoryNamed(name);
    }

    public void updateSubCat(SubCategory subCategory) {
        NomoolaRoomDataBase.databaseWriteExecutor.execute(()->{
            mSubCategoryDAO.updateSubCategory(subCategory);
        });
    }


    /*
        INOUTCOME
     */

    public LiveData<List<InOutCome>> getmAllInOutCome(){
        return mAllInOutCome;
    }
    public void insert(InOutCome inOutCome){
        NomoolaRoomDataBase.databaseWriteExecutor.execute(()->{
            mInOutComeDAO.insertInOutCome(inOutCome);
        });
    }


    public LiveData<List<InOutCome>> getInOutComeOf(int subCategoryID) {
        return this.mInOutComeDAO.getInOutComesOf(subCategoryID);
    }

    public void delete(InOutCome inOutCome) {
        this.mInOutComeDAO.deleteInOutCome(inOutCome);
    }

    public void update(InOutCome inOutCome){
        NomoolaRoomDataBase.databaseWriteExecutor.execute(()->{
            mInOutComeDAO.updateInOutCome(inOutCome);
        });
    }

    /*
        PROFILE
     */

    public LiveData<Profile> getmProfile() {
        return mProfile;
    }

    public void insert(Profile profile) {
        NomoolaRoomDataBase.databaseWriteExecutor.execute(() -> {
            mProfileDAO.insertProfile(profile);
        });
    }
    public void delete(Profile profile) {
        NomoolaRoomDataBase.databaseWriteExecutor.execute(()->{
            mProfileDAO.deleteProfile(profile);
        });
    }
    public void update(int userID, String userName, Profile.userLanguage language, Profile.userCurrency currency) {
        NomoolaRoomDataBase.databaseWriteExecutor.execute(()->{
            mProfileDAO.updateProfile(userID, userName, language, currency);
        });
    }

    public void setLanguage(int userID, Profile.userLanguage language){
        NomoolaRoomDataBase.databaseWriteExecutor.execute(()->{
            mProfileDAO.setLanguage(userID, language);
        });
    }

    public void setCurrency(int userID, Profile.userCurrency currency){
        NomoolaRoomDataBase.databaseWriteExecutor.execute(()->{
            mProfileDAO.setCurrency(userID, currency);
        });
    }

    public void setUsername(int userID, String userName){
        NomoolaRoomDataBase.databaseWriteExecutor.execute(()->{
            mProfileDAO.setUserName(userID, userName);
        });
    }

    public LiveData<String> getUserName(int userID){
        return this.mProfileDAO.getUserName(userID);
    }

    public LiveData<Double> getAmountUsedBySubcategory(int m_subcat_id) {
        return this.mInOutComeDAO.getAmountUsedBySubcategory(m_subcat_id);
    }

    /*
        SUBCATEGORYPROFILE
     */

    public void insertSubCatProfile(SubCategoryProfile...subCategoryProfiles){
        NomoolaRoomDataBase.databaseWriteExecutor.execute(() -> {
            mSubCategoryProfileDAO.insertSubCatProfile(subCategoryProfiles);
        });
    }

    public void updateSubCatProfile(SubCategoryProfile...subCategoryProfiles){
        NomoolaRoomDataBase.databaseWriteExecutor.execute(() -> {
            mSubCategoryProfileDAO.updateSubCatProfile(subCategoryProfiles);
        });
    }

    public void deleteSubCatProfile(SubCategoryProfile subCategoryProfile){
        NomoolaRoomDataBase.databaseWriteExecutor.execute(() -> {
            mSubCategoryProfileDAO.deleteSubCatProfile(subCategoryProfile);
        });
    }

    public LiveData<List<SubCategoryProfile>> getAllSubCatProfile(){
        return this.mSubCategoryProfileDAO.getAllSubCatProfile();
    }

    public LiveData<List<Profile>> getProfileFromSubCat(int subCatID){
        return this.mSubCategoryProfileDAO.getProfileFromSubCat(subCatID);
    }

    public LiveData<Integer> getNumberOfMembersInSubCategory(int subCatID){
        return this.mSubCategoryProfileDAO.getNumberOfMembersInSubCategory(subCatID);
    }


    public LiveData<String> getProfileNameAccordingToID(int profileID){
        return this.mProfileDAO.getProfileNameAccordingToID(profileID);
    }

    public LiveData<Double> getToTtalExpense(int ownerID, int subCatID){
        return this.mInOutComeDAO.getTotalExpense(ownerID, subCatID);
    }
}
