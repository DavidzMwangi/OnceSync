package ke.co.davidwanjohi.oncesync.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "pickup_table")
public class Pickup {
//


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "agent_id")
    @SerializedName("agent_id")
    public int agentId;

    @ColumnInfo(name = "farmer_id")
    @SerializedName("farmer_id")
    public int farmerId;


    @ColumnInfo(name = "date")
    @SerializedName("date")
    public String date;


    @ColumnInfo(name = "account_number")
    @SerializedName("account_number")
    public String accountNumber;



    @ColumnInfo(name = "no_of_litres")
    @SerializedName("no_of_litres")
    public Double noOfLitres;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
