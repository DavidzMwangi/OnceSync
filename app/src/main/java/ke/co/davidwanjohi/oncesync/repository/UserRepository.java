package ke.co.davidwanjohi.oncesync.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ke.co.davidwanjohi.oncesync.dao.UserDao;
import ke.co.davidwanjohi.oncesync.models.Authorization;
import ke.co.davidwanjohi.oncesync.models.User;
import ke.co.davidwanjohi.oncesync.service.UserService;
import ke.co.davidwanjohi.oncesync.utils.CoreUtils;
import ke.co.davidwanjohi.oncesync.utils.NetworkResponse;
import ke.co.davidwanjohi.oncesync.utils.OnceSyncDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class UserRepository {


    public MutableLiveData<NetworkResponse> monitor;
    public LiveData<Authorization> authorization;
    public UserDao userDao;
    public LiveData<User> authUser;
    public UserRepository(Application application){
        OnceSyncDatabase onceSyncDatabase=OnceSyncDatabase.getDatabase(application);
        monitor=new MutableLiveData<>();
       userDao=onceSyncDatabase.userDao();
       authUser=userDao.getAuthUser();
    }


    public void getAuthUserOnline(String token){
        Call<User> call= CoreUtils.getAuthRetrofitClient(token).create(UserService.class).getAuthUser();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                monitor.postValue(new NetworkResponse(false));
                if (response.body()!=null){

                    userDao.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                try{
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",((HttpException) t).code()));
                }catch (Exception e){
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",0));
                }
            }
        });
    }
}
