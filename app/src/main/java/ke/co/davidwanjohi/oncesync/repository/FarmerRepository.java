package ke.co.davidwanjohi.oncesync.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ke.co.davidwanjohi.oncesync.dao.FarmerDao;
import ke.co.davidwanjohi.oncesync.models.Farmer;
import ke.co.davidwanjohi.oncesync.service.FarmerService;
import ke.co.davidwanjohi.oncesync.utils.CoreUtils;
import ke.co.davidwanjohi.oncesync.utils.NetworkResponse;
import ke.co.davidwanjohi.oncesync.utils.OnceSyncDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class FarmerRepository {
     public MutableLiveData<NetworkResponse> monitor;
    FarmerDao farmerDao;
    public LiveData<List<Farmer>> allFarmers;
    public FarmerRepository(Application application){
        OnceSyncDatabase onceSyncDatabase=OnceSyncDatabase.getDatabase(application);

        farmerDao=onceSyncDatabase.farmerDao();
        allFarmers=farmerDao.getAllFarmers();
        monitor=new MutableLiveData<>();

    }

    public void getFarmersDataOnline(String token){

        Call<List<Farmer>> call= CoreUtils.getAuthRetrofitClient(token).create(FarmerService.class).getAllFarmers();
        call.enqueue(new Callback<List<Farmer>>() {
            @Override
            public void onResponse(Call<List<Farmer>> call, Response<List<Farmer>> response) {

                monitor.postValue(new NetworkResponse(false));
                if (response.body()!=null){
                    for (Farmer farmer:response.body()){

                        farmerDao.insert(farmer);
                    }


                }
            }

            @Override
            public void onFailure(Call<List<Farmer>> call, Throwable t) {
                try{
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",((HttpException) t).code()));
                }catch (Exception e){
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",0));
                }
            }
        });
    }
}
