package ke.co.davidwanjohi.oncesync.service;

import java.util.List;

import ke.co.davidwanjohi.oncesync.models.Pickup;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PickupService {
    @GET("farmer_pick_ups/{farmer}")
    Call<List<Pickup>> getFarmerPickups(@Path("farmer") int farmerId);



    @POST("new_farmer_pickup")
    @FormUrlEncoded
    Call<Pickup> saveFarmerPickup(@Field("date") String date, @Field("no_of_litres") Double litres,@Field("account_number") String accountNo,@Field("farmer_id") int farmerId);

}
