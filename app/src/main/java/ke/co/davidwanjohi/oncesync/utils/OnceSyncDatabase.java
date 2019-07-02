package ke.co.davidwanjohi.oncesync.utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ke.co.davidwanjohi.oncesync.dao.AuthorizationDao;
import ke.co.davidwanjohi.oncesync.dao.FarmerDao;
import ke.co.davidwanjohi.oncesync.dao.PickupDao;
import ke.co.davidwanjohi.oncesync.dao.UserDao;
import ke.co.davidwanjohi.oncesync.models.Authorization;
import ke.co.davidwanjohi.oncesync.models.Farmer;
import ke.co.davidwanjohi.oncesync.models.Pickup;
import ke.co.davidwanjohi.oncesync.models.User;

@Database(entities = {Authorization.class, User.class, Farmer.class, Pickup.class},version = 1)
public abstract class OnceSyncDatabase extends RoomDatabase {

    public abstract AuthorizationDao authorizationDao();
    public abstract UserDao userDao();
    public abstract FarmerDao farmerDao();
    public abstract PickupDao pickupDao();
    private static OnceSyncDatabase INSTANCE;
    public static OnceSyncDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (OnceSyncDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            OnceSyncDatabase.class,"oncesync_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
