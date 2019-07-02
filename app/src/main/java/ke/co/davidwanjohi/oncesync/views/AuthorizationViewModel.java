package ke.co.davidwanjohi.oncesync.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ke.co.davidwanjohi.oncesync.models.Authorization;
import ke.co.davidwanjohi.oncesync.repository.AuthorizationRepository;
import ke.co.davidwanjohi.oncesync.utils.NetworkResponse;


public class AuthorizationViewModel extends AndroidViewModel {

    AuthorizationRepository authorizationRepository;
    public LiveData<Authorization> authorization;
    public MutableLiveData<NetworkResponse> monitor;
    public MutableLiveData<Boolean> recoverSent;

    public AuthorizationViewModel(@NonNull Application application){
        super(application);
        authorizationRepository=new AuthorizationRepository(application);
        authorization=authorizationRepository.authorization;
        monitor=authorizationRepository.monitor;
        recoverSent=authorizationRepository.recoverSent;

    }

    public void loginOnline(String email,String password){
        authorizationRepository.loginOnline(email,password);
    }

    public void registerOnline(String userName,String email,int userType,String password,String passwordConfirmation){

        authorizationRepository.registerOnline(email,userName,password,passwordConfirmation,userType);
    }

    public void sendRecoveryPassword(String email) {

        authorizationRepository.sendRecoveryPassword(email);
    }
}
