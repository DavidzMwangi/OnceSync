package ke.co.davidwanjohi.oncesync.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import ke.co.davidwanjohi.oncesync.models.Authorization;

@Dao
public interface AuthorizationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Authorization authorization);

    @Query("DELETE FROM authorization_table")
    void deleteAll();

    @Query("SELECT * from authorization_table ORDER BY id desc LIMIT 1")
    LiveData<Authorization> getAuthorization();

    @Query("SELECT * from authorization_table ORDER BY id desc LIMIT 1")
    Authorization getSavedAuthorization();
}
