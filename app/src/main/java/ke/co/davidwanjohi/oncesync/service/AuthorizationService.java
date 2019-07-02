package ke.co.davidwanjohi.oncesync.service;

import ke.co.davidwanjohi.oncesync.models.Authorization;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthorizationService {

    @POST("login")
    @FormUrlEncoded
    Call<Authorization> tryLogin(@Field("username") String title, @Field("password") String body);



    @POST("register")
    @FormUrlEncoded
    Call<Authorization> tryRegister(@Field("name") String name, @Field("email") String email,  @Field("password") String password, @Field("password_confirmation") String password_confirmation, @Field("user_type") int userType);

    @POST("recover_password")
    @FormUrlEncoded
    Call<Void> recoverPassword(@Field("email") String email);
}
