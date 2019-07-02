package ke.co.davidwanjohi.oncesync.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import ke.co.davidwanjohi.oncesync.models.User;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);



    @Query("DELETE FROM users_table")
    void deleteAll();


    @Query("SELECT * from users_table ORDER BY id desc LIMIT 1")
    LiveData<User> getAuthUser();

}
