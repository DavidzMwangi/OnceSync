package ke.co.davidwanjohi.oncesync.models;



import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "authorization_table")
public class Authorization {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "token_type")
    @SerializedName("token_type")
    public String token_type;

    @ColumnInfo(name = "expires_in")
    @SerializedName("expires_in")
    public String expires_in;

    @ColumnInfo(name = "access_token")
    @SerializedName("access_token")
    public String access_token;


    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }
}
