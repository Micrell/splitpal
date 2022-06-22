package com.example.nomoola.viewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.nomoola.database.entity.InOutCome;
import com.example.nomoola.database.entity.Profile;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.database.entity.SubCategoryProfile;
import com.example.nomoola.database.repository.DataRepository;
import com.example.nomoola.database.roomDataBase.NomoolaRoomDataBase;

import java.time.LocalDate;
import java.util.List;

public class InOutComeViewModel extends AndroidViewModel {

    private final DataRepository mRepository;
    private LiveData<List<InOutCome>> mInOutComes;

    public InOutComeViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new DataRepository(application);
        this.mInOutComes = mRepository.getmAllInOutCome();
    }

    public LiveData<List<InOutCome>> getmInOutComes(){
        return this.mInOutComes;
    }

    public LiveData<List<InOutCome>> getInOutComeOf(int subCategoryID){
        return this.mRepository.getInOutComeOf(subCategoryID);
    }

    public void insert(InOutCome inOutCome){
        this.mRepository.insert(inOutCome);
    }

    public void delete(InOutCome inOutCome){
        this.mRepository.delete(inOutCome);
    }

    public void update(InOutCome inOutCome){
        mRepository.update(inOutCome);
    }

    public LiveData<List<String>> getAllSubCategoriesNames(){
        return this.mRepository.getAllSubCategoriesNames();
    }

    public SubCategory getSubCategoriesNamed(String name){
        return this.mRepository.getSubCategoriesNamed(name);
    }

    public void insertSubCatProfile(SubCategoryProfile...subCategoryProfiles){
            this.mRepository.insertSubCatProfile(subCategoryProfiles);

    }

    public void updateSubCatProfile(SubCategoryProfile...subCategoryProfiles){
            this.mRepository.updateSubCatProfile(subCategoryProfiles);
    }

    public void deleteSubCatProfile(SubCategoryProfile subCategoryProfile){
            this.mRepository.deleteSubCatProfile(subCategoryProfile);
    }

    public LiveData<List<SubCategoryProfile>> getAllSubCatProfile(){
        return this.mRepository.getAllSubCatProfile();
    }

    public LiveData<List<Profile>> getProfileFromSubCat(int subCatID){
        return this.mRepository.getProfileFromSubCat(subCatID);
    }

    public LiveData<Integer> getNumberOfMembersInSubCategory(int subCatID) {
        return this.mRepository.getNumberOfMembersInSubCategory(subCatID);
    }
    public LiveData<List<String>> getAllUsernames() {
        return this.mRepository.getAllUsernames();
    }

    public LiveData<Double> getToTtalExpense(int ownerID, int subcatID){
        return this.mRepository.getToTtalExpense(ownerID, subcatID);

    }

}
