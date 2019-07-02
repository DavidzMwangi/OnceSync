package ke.co.davidwanjohi.oncesync.service;

import java.util.List;

import ke.co.davidwanjohi.oncesync.models.Farmer;
import ke.co.davidwanjohi.oncesync.models.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FarmerService {
    @GET("all_farmers")
    Call<List<Farmer>> getAllFarmers();
}
