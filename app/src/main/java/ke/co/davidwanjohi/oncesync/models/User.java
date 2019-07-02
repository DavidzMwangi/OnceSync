package ke.co.davidwanjohi.oncesync.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "users_table")
public class User {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    public String name;


    @ColumnInfo(name = "email")
    @SerializedName("email")
    public String email;

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    public String created_at;



    @ColumnInfo(name = "user_type")
    @SerializedName("user_type")
    public int user_type;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
