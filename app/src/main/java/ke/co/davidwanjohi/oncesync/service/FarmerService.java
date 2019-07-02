package ke.co.davidwanjohi.oncesync.service;

import java.util.List;

import ke.co.davidwanjohi.oncesync.models.Farmer;
import ke.co.davidwanjohi.oncesync.models.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FarmerService {
    @GET("all_farmers")
    Call<List<Farmer>> getAllFarmers();



    @POST("new_farmer")
    @FormUrlEncoded
    Call<Farmer> saveNewFarmerOnline(@Field("name") String name,@Field("gender") int gender, @Field("telephone_no") String telephoneNo,@Field("account_number") String accountNo, @Field("location") String location);
}
