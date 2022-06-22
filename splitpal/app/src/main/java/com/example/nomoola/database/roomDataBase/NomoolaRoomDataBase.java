package com.example.nomoola.database.roomDataBase;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.nomoola.database.converter.Converters;
import com.example.nomoola.database.dao.InOutComeDAO;
import com.example.nomoola.database.dao.SubCategoryDAO;
import com.example.nomoola.database.dao.ProfileDAO;
import com.example.nomoola.database.dao.SubCategoryProfileDAO;
import com.example.nomoola.database.entity.InOutCome;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.database.entity.Profile;
import com.example.nomoola.database.entity.SubCategoryProfile;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SubCategory.class, InOutCome.class, Profile.class, SubCategoryProfile.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class NomoolaRoomDataBase extends RoomDatabase {

    public abstract SubCategoryDAO subCategoryDAO();
    public abstract InOutComeDAO inOutComeDAO();
    public abstract ProfileDAO profileDAO();
    public abstract SubCategoryProfileDAO subCategoryProfileDAO();

    private static volatile NomoolaRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static NomoolaRoomDataBase getDatabase(final Context context) {
        Log.d("CREATION", "getting database");
        if (INSTANCE == null) {
            synchronized (NomoolaRoomDataBase.class) {
                if (INSTANCE == null) {
                    Log.d("CREATION", "building database");
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NomoolaRoomDataBase.class, "DB_CATEGORY")
                            .addCallback(sRoomDataBaseCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }

        //Dummy query to force the method onCreate() to be call
        INSTANCE.query("select 1", null);

        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDataBaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            Log.d("CREATION", "onCreate() from sRoomDataBaseCallBack is being execute");
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                populateSubCategory();
                populateInOutCome();
                populateProfile();
                populateSubCatProfile();
            });
        }

        private void populateSubCategory(){
            SubCategoryDAO dao = INSTANCE.subCategoryDAO();

            SubCategory undCat = new SubCategory("GROCERIES");
            dao.insertSubCategory(undCat);
            undCat = new SubCategory("RESTAURANTS");
            dao.insertSubCategory(undCat);

            undCat = new SubCategory("REPARATION");
            dao.insertSubCategory(undCat);
        }

        private void populateInOutCome(){
            InOutComeDAO dao = INSTANCE.inOutComeDAO();

            InOutCome come = new InOutCome("week grocery at SuperU",1, 56.89, LocalDate.now(), 1);
            dao.insertInOutCome(come);
            come = new InOutCome("BK with my friends", 1, 13.50, LocalDate.now(), 2);
            dao.insertInOutCome(come);
            come = new InOutCome("motor reparation", 1, 207.90, LocalDate.now(), 3);
            dao.insertInOutCome(come);


            come = new InOutCome("weekly fuel", 2, 60.00, LocalDate.now(), 1);
            dao.insertInOutCome(come);
            come = new InOutCome("week grocery at SuperU",2, 56.89, LocalDate.now(), 2);
            dao.insertInOutCome(come);
            come = new InOutCome("BK with my friends", 2, 13.50, LocalDate.now(), 3);
            dao.insertInOutCome(come);
            come = new InOutCome("motor reparation", 2, 207.90, LocalDate.now(), 4);
            dao.insertInOutCome(come);

            come = new InOutCome("weekly fuel", 3, 60.00, LocalDate.now(), 1);
            dao.insertInOutCome(come);
            come = new InOutCome("week grocery at SuperU",3, 56.89, LocalDate.now(), 2);
            dao.insertInOutCome(come);
            come = new InOutCome("BK with my friends", 3, 13.50, LocalDate.now(), 3);
            dao.insertInOutCome(come);

        }

        private void populateProfile(){
            ProfileDAO dao = INSTANCE.profileDAO();

            Profile profile = new Profile("Philippe", Profile.userLanguage.ENGLISH, Profile.userCurrency.USDOLLAR);
            dao.insertProfile(profile);
            profile = new Profile("John", Profile.userLanguage.ENGLISH, Profile.userCurrency.USDOLLAR);
            dao.insertProfile(profile);
            profile = new Profile("Ercan", Profile.userLanguage.ENGLISH, Profile.userCurrency.USDOLLAR);
            dao.insertProfile(profile);
            profile = new Profile("Jean-Paul", Profile.userLanguage.ENGLISH, Profile.userCurrency.USDOLLAR);
            dao.insertProfile(profile);
            profile = new Profile("Jules", Profile.userLanguage.ENGLISH, Profile.userCurrency.USDOLLAR);
            dao.insertProfile(profile);
        }

        private void populateSubCatProfile(){
            SubCategoryProfileDAO dao = INSTANCE.subCategoryProfileDAO();

            SubCategoryProfile subCategoryProfile = new SubCategoryProfile(1, 1);
            dao.insertSubCatProfile(subCategoryProfile);
            subCategoryProfile = new SubCategoryProfile(1, 2);
            dao.insertSubCatProfile(subCategoryProfile);
            subCategoryProfile = new SubCategoryProfile(1, 3);
            dao.insertSubCatProfile(subCategoryProfile);

            subCategoryProfile = new SubCategoryProfile(2, 1);
            dao.insertSubCatProfile(subCategoryProfile);
            subCategoryProfile = new SubCategoryProfile(2, 2);
            dao.insertSubCatProfile(subCategoryProfile);
            subCategoryProfile = new SubCategoryProfile(2, 3);
            dao.insertSubCatProfile(subCategoryProfile);
            subCategoryProfile = new SubCategoryProfile(2, 4);
            dao.insertSubCatProfile(subCategoryProfile);

            subCategoryProfile = new SubCategoryProfile(3, 1);
            dao.insertSubCatProfile(subCategoryProfile);
            subCategoryProfile = new SubCategoryProfile(3, 2);
            dao.insertSubCatProfile(subCategoryProfile);

        }
    };


}
