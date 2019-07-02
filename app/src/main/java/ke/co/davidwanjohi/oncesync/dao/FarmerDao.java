package ke.co.davidwanjohi.oncesync.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ke.co.davidwanjohi.oncesync.models.Farmer;

@Dao
public interface FarmerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Farmer farmer);

    @Query("DELETE FROM farmers_table")
    void deleteAll();

    @Query("SELECT * from users_table ORDER BY id desc ")
    LiveData<List<Farmer>> getAllFarmers();
}
