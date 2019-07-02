package ke.co.davidwanjohi.oncesync.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ke.co.davidwanjohi.oncesync.models.Authorization;
import ke.co.davidwanjohi.oncesync.models.Farmer;
import ke.co.davidwanjohi.oncesync.repository.AuthorizationRepository;
import ke.co.davidwanjohi.oncesync.repository.FarmerRepository;
import ke.co.davidwanjohi.oncesync.utils.NetworkResponse;

public class FarmerViewModel extends AndroidViewModel {

    public LiveData<Authorization> mAuth;
    public MutableLiveData<NetworkResponse> monitor;
    AuthorizationRepository authorizationRepository;
    FarmerRepository farmerRepository;
    public LiveData<List<Farmer>> allFarmers;
    public FarmerViewModel(@NonNull Application application) {
        super(application);

        authorizationRepository=new AuthorizationRepository(application);
        farmerRepository=new FarmerRepository(application);
        monitor=farmerRepository.monitor;
        mAuth=authorizationRepository.authorization;
        allFarmers=farmerRepository.allFarmers;
    }

    public void getFarmersOnline(String token){
        farmerRepository.getFarmersDataOnline(token);
    }


    public void saveFarmerOnline(String access_token, String name, int gender, String telephoneNo, String accountNo, String location) {

        farmerRepository.saveFarmerOnline(access_token,name,gender,telephoneNo,accountNo,location);
    }

    public LiveData<Farmer> getSingleFarmer(int farmerId){

        return  farmerRepository.getSingleFarmer(farmerId);
    }
}
