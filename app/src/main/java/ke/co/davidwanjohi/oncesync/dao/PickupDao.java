package ke.co.davidwanjohi.oncesync.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ke.co.davidwanjohi.oncesync.models.Pickup;

@Dao
public interface PickupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Pickup pickup);

    @Query("DELETE FROM pickup_table")
    void deleteAll();

    @Query("SELECT * from pickup_table ORDER BY id desc ")
    LiveData<List<Pickup>> getAllPickups();

    @Query("SELECT * from pickup_table WHERE farmer_id=:farmerId")
    LiveData<List<Pickup>> getFarmerPickups(int farmerId);
}
