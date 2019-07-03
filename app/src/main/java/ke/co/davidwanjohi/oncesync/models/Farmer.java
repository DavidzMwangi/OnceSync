package ke.co.davidwanjohi.oncesync.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "farmers_table")
public class Farmer {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "agent_id")
    @SerializedName("agent_id")
    public int agentId;

    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    public int gender;


    @ColumnInfo(name = "name")
    @SerializedName("name")
    public String name;



    @ColumnInfo(name = "telephone_no")
    @SerializedName("telephone_no")
    public String telephoneNo;


    @ColumnInfo(name = "account_number")
    @SerializedName("account_number")
    public String accountNo;

    @ColumnInfo(name = "location")
    @SerializedName("location")
    public String location;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
