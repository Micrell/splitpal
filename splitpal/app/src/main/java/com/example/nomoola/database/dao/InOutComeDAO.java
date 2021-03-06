package com.example.nomoola.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.nomoola.database.entity.InOutCome;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface InOutComeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInOutCome(InOutCome...inOutComes);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateInOutCome(InOutCome...inOutComes);

    @Delete
    void deleteInOutCome(InOutCome inOutCome);

    @Query("SELECT * " +
            "FROM T_INOUTCOME")
    LiveData<List<InOutCome>> getALlInOutComes();

    @Query("SELECT * " +
            "FROM T_INOUTCOME " +
            "WHERE SUBCAT_ID=:subCategoryID")
    LiveData<List<InOutCome>> getInOutComesOf(int subCategoryID);

    @Query("SELECT SUM(INOUTCOME_AMOUNT) " +
            "FROM T_INOUTCOME " +
            "WHERE SUBCAT_ID=:subCategoryID AND INOUTCOME_OWNER_ID=:ownerID ")
    LiveData<Double> getInOutComesOfSubCatOfOwner(int subCategoryID, int ownerID);

    @Query("SELECT SUM(INOUTCOME_AMOUNT) " +
            "FROM T_INOUTCOME " +
            "WHERE SUBCAT_ID=:m_subcat_id")
    LiveData<Double> getAmountUsedBySubcategory(int m_subcat_id);

    @Query("SELECT SUM(INOUTCOME_AMOUNT) " +
            "FROM T_INOUTCOME INNER JOIN T_SUBCATEGORY " +
            "ON T_INOUTCOME.SUBCAT_ID = T_SUBCATEGORY.SUBCAT_ID " +
            "WHERE INOUTCOME_OWNER_ID=:ownerID AND T_INOUTCOME.SUBCAT_ID=:subCatID")
    LiveData<Double> getTotalExpense(int ownerID, int subCatID);
}
