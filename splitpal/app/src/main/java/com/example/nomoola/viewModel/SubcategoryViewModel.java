package com.example.nomoola.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.database.repository.DataRepository;

import java.util.List;

public class SubcategoryViewModel extends AndroidViewModel {

    private final DataRepository mRepository;
    private final LiveData<List<SubCategory>> mAllSubCategories;

    public SubcategoryViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new DataRepository(application);
        this.mAllSubCategories = mRepository.getAllSubCategories();
    }

    public LiveData<List<SubCategory>> getmAllSubCategories(){
        return mAllSubCategories;
    }

    public void insert(SubCategory subCategory){
        mRepository.insert(subCategory);
    }

    public void delete(SubCategory subCategory){
        mRepository.delete(subCategory);
    }

    public LiveData<Double> getAmountUsedBySubcategory(SubCategory subCategory) {
        return this.mRepository.getAmountUsedBySubcategory(subCategory.getM_SUBCAT_ID());
    }

    public void update(SubCategory subCategory) {
        this.mRepository.updateSubCat(subCategory);
    }

    public LiveData<Integer> getNumberOfMembersInSubCategory(int subCatID){
        return this.mRepository.getNumberOfMembersInSubCategory(subCatID);
    }

    public LiveData<String> getNameOfFirstProfile(){
        return this.mRepository.getUserName(1);
    }
}
