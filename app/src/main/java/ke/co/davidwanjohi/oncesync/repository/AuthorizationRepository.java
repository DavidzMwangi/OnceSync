package ke.co.davidwanjohi.oncesync.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ke.co.davidwanjohi.oncesync.dao.AuthorizationDao;
import ke.co.davidwanjohi.oncesync.models.Authorization;
import ke.co.davidwanjohi.oncesync.service.AuthorizationService;
import ke.co.davidwanjohi.oncesync.utils.CoreUtils;
import ke.co.davidwanjohi.oncesync.utils.NetworkResponse;
import ke.co.davidwanjohi.oncesync.utils.OnceSyncDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class AuthorizationRepository {

    AuthorizationDao authorizationDao;
    public MutableLiveData<NetworkResponse> monitor;
    public LiveData<Authorization> authorization;
    public MutableLiveData<Boolean> recoverSent;

    public AuthorizationRepository(Application application){
        OnceSyncDatabase agrihubDatabase=OnceSyncDatabase.getDatabase(application);
        authorizationDao=agrihubDatabase.authorizationDao();
        monitor=new MutableLiveData<>();
        authorization=authorizationDao.getAuthorization();
        recoverSent=new MutableLiveData<>();

    }

    public void loginOnline(String email,String password){
        Call<Authorization> call= CoreUtils.getRetrofitClient().create(AuthorizationService.class).tryLogin(email,password);
        call.enqueue(new Callback<Authorization>() {
            @Override
            public void onResponse(Call<Authorization> call, Response<Authorization> response) {
                if(response.code()==401){
                    monitor.postValue(new NetworkResponse(false,"Your credentials did not match our records",response.code()));
                }else if(response.code()==422 || response.code()==500){
                    monitor.postValue(new NetworkResponse(false,"Invalid credentials",response.code()));
                }else{
                    monitor.postValue(new NetworkResponse(false,"",1));
                }
                if (response.body()!=null){

                    authorizationDao.deleteAll();
                    authorizationDao.insert(response.body());
                }else{
                    monitor.postValue(new NetworkResponse(false,"Cannot connect to the server",response.code()));

                }
            }

            @Override
            public void onFailure(Call<Authorization> call, Throwable t) {

                try{
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",((HttpException) t).code()));
                }catch (Exception e){
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",0));
                }
            }
        });
    }

    //email,userName,password,passwordConfirmation,userType
    public void registerOnline(String email, String userName,String password,String passwordConfirmation,int user_type){
        Call<Authorization> call=CoreUtils.getRetrofitClient().create(AuthorizationService.class).tryRegister(userName,email,password,passwordConfirmation,user_type);
        call.enqueue(new Callback<Authorization>() {
            @Override
            public void onResponse(Call<Authorization> call, Response<Authorization> response) {
                if(response.code()==422 || response.code()==500){
                    monitor.postValue(new NetworkResponse(false,"Invalid user credentials",response.code()));
                }else{
                    monitor.postValue(new NetworkResponse(false,"successful",1));
                }
                if (response.body()!=null){
                    authorizationDao.insert(response.body());
                }else{
                    monitor.postValue(new NetworkResponse(false,"Cannot connect to the server",response.code()));

                }
            }

            @Override
            public void onFailure(Call<Authorization> call, Throwable t) {
                try{
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",((HttpException) t).code()));
                }catch (Exception e){
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",0));
                }
            }
        });
    }

    public void sendRecoveryPassword(String email) {

        Call<Void> call=CoreUtils.getRetrofitClient().create(AuthorizationService.class).recoverPassword(email);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()){
                    recoverSent.postValue(true);
                }else{
                    recoverSent.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                try{
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",((HttpException) t).code()));
                }catch (Exception e){
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",0));
                }
            }
        });
    }

}
