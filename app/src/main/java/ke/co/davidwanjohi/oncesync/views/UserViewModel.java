package ke.co.davidwanjohi.oncesync.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ke.co.davidwanjohi.oncesync.models.Authorization;
import ke.co.davidwanjohi.oncesync.models.User;
import ke.co.davidwanjohi.oncesync.repository.AuthorizationRepository;
import ke.co.davidwanjohi.oncesync.repository.UserRepository;
import ke.co.davidwanjohi.oncesync.utils.NetworkResponse;


public class UserViewModel extends AndroidViewModel {

    public LiveData<Authorization> mAuth;
    public MutableLiveData<NetworkResponse> monitor;
    UserRepository userRepository;
    AuthorizationRepository authorizationRepository;
    public UserViewModel(@NonNull Application application) {
        super(application);

        //init them here
        userRepository=new UserRepository(application);
        authorizationRepository=new AuthorizationRepository(application);
        mAuth=authorizationRepository.authorization;
        monitor=userRepository.monitor;
    }

    public void getAuthInfoOnline(String token){

        userRepository.getAuthUserOnline(token);
    }

    public LiveData<User> getAuthUserInfo(){
        return  userRepository.authUser;
    }


}
