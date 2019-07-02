package ke.co.davidwanjohi.oncesync.utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ke.co.davidwanjohi.oncesync.dao.AuthorizationDao;
import ke.co.davidwanjohi.oncesync.models.Authorization;

@Database(entities = {Authorization.class},version = 1)
public abstract class OnceSyncDatabase extends RoomDatabase {

    public abstract AuthorizationDao authorizationDao();

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
