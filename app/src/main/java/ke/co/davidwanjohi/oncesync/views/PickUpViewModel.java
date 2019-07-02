package ke.co.davidwanjohi.oncesync.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ke.co.davidwanjohi.oncesync.models.Authorization;
import ke.co.davidwanjohi.oncesync.models.Farmer;
import ke.co.davidwanjohi.oncesync.models.Pickup;
import ke.co.davidwanjohi.oncesync.repository.AuthorizationRepository;
import ke.co.davidwanjohi.oncesync.repository.PickupRepository;
import ke.co.davidwanjohi.oncesync.utils.NetworkResponse;

public class PickUpViewModel extends AndroidViewModel {
    PickupRepository pickupRepository;
    public LiveData<Authorization> mAuth;
    public MutableLiveData<NetworkResponse> monitor;
    AuthorizationRepository authorizationRepository;
    public PickUpViewModel(@NonNull Application application) {
        super(application);

        pickupRepository=new PickupRepository(application);
        authorizationRepository=new AuthorizationRepository(application);
        mAuth=authorizationRepository.authorization;
        monitor=pickupRepository.monitor;

    }

    public void getFarmerPickups(String token,int farmerId){

        pickupRepository.getFarmerPickups(token,farmerId);
    }

    public LiveData<List<Pickup>> getFarmersPickups(int farmerId){

        return  pickupRepository.getFarmerPickUps(farmerId);
    }


    public void savePickupOnline(String token,String date,Double litres,String accountNumber,int farmerId){

        pickupRepository.savePickupOnline(token,date,litres,accountNumber,farmerId);
    }
}
