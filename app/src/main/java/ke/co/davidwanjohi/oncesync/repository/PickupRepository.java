package ke.co.davidwanjohi.oncesync.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ke.co.davidwanjohi.oncesync.dao.PickupDao;
import ke.co.davidwanjohi.oncesync.models.Pickup;
import ke.co.davidwanjohi.oncesync.service.PickupService;
import ke.co.davidwanjohi.oncesync.utils.CoreUtils;
import ke.co.davidwanjohi.oncesync.utils.NetworkResponse;
import ke.co.davidwanjohi.oncesync.utils.OnceSyncDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class PickupRepository {
    PickupDao pickupDao;
    public MutableLiveData<NetworkResponse> monitor;
    public LiveData<List<Pickup>> allPickups;
    public PickupRepository(Application application){
        OnceSyncDatabase onceSyncDatabase=OnceSyncDatabase.getDatabase(application);
        pickupDao=onceSyncDatabase.pickupDao();
        monitor=new MutableLiveData<>();
        allPickups=pickupDao.getAllPickups();

    }

    public void getFarmerPickups(String token,int farmerId){

        Call<List<Pickup>> call= CoreUtils.getAuthRetrofitClient(token).create(PickupService.class).getFarmerPickups(farmerId);
        call.enqueue(new Callback<List<Pickup>>() {
            @Override
            public void onResponse(Call<List<Pickup>> call, Response<List<Pickup>> response) {
              monitor.postValue(new NetworkResponse(false));
                if (response.body()!=null){

                    for (Pickup pickup:response.body()){

                        pickupDao.insert(pickup);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Pickup>> call, Throwable t) {
                try{
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",((HttpException) t).code()));
                }catch (Exception e){
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",0));
                }
            }
        });
    }

    public LiveData<List<Pickup>> getFarmerPickUps(int farmerId){

        return pickupDao.getFarmerPickups(farmerId);
    }

    public void savePickupOnline(String token, String date, Double litres, String accountNumber, int farmerId) {
        Call<Pickup> call=CoreUtils.getAuthRetrofitClient(token).create(PickupService.class).saveFarmerPickup(date,litres,accountNumber,farmerId);
        call.enqueue(new Callback<Pickup>() {
            @Override
            public void onResponse(Call<Pickup> call, Response<Pickup> response) {

                monitor.postValue(new NetworkResponse(false));
                if (response.body()!=null){

                    pickupDao.insert(response.body());

                }
            }

            @Override
            public void onFailure(Call<Pickup> call, Throwable t) {
                try{
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",((HttpException) t).code()));
                }catch (Exception e){
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",0));
                }
            }
        });

    }
}
