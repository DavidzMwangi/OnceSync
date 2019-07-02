package ke.co.davidwanjohi.oncesync.service;

import ke.co.davidwanjohi.oncesync.models.User;

import retrofit2.Call;

import retrofit2.http.GET;

public interface UserService {

        @GET("user")
        Call<User> getAuthUser();



}
