package ke.co.davidwanjohi.oncesync;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.davidwanjohi.oncesync.adapters.PickupAdapter;
import ke.co.davidwanjohi.oncesync.models.Authorization;
import ke.co.davidwanjohi.oncesync.models.Farmer;
import ke.co.davidwanjohi.oncesync.models.Pickup;
import ke.co.davidwanjohi.oncesync.views.FarmerViewModel;
import ke.co.davidwanjohi.oncesync.views.PickUpViewModel;

public class FarmerActivity extends AppCompatActivity {

    @BindView(R.id.name) TextView name;
    @BindView(R.id.gender) TextView gender;
    @BindView(R.id.telephone_no) TextView telephoneNo;
    @BindView(R.id.account_number) TextView accountNo;
    @BindView(R.id.location) TextView location;
    @BindView(R.id.pickups_recycler) RecyclerView pickupsRecycler;

    FarmerViewModel farmerViewModel;
    PickUpViewModel pickUpViewModel;
    PickupAdapter pickupAdapter;
    int farmerId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer);
        ButterKnife.bind(this);

        farmerId=getIntent().getIntExtra("farmer_id",0);

        if (farmerId==0){
            Intent intent=new Intent(FarmerActivity.this,MainActivity.class);
            startActivity(intent);
        }

        farmerViewModel= ViewModelProviders.of(this).get(FarmerViewModel.class);
        pickUpViewModel=ViewModelProviders.of(this).get(PickUpViewModel.class);
        farmerViewModel.getSingleFarmer(farmerId).observe(this, new Observer<Farmer>() {
            @Override
            public void onChanged(Farmer farmer) {
                if (farmer!=null){
                    name.setText(farmer.name);
                    gender.setText(farmer.gender==0?"Male":"Female");
                    telephoneNo.setText(farmer.telephoneNo);
                    accountNo.setText(farmer.accountNo);
                    location.setText(farmer.location);

                }
            }
        });

        pickupsRecycler.setLayoutManager(new LinearLayoutManager(this));
        pickupAdapter=new PickupAdapter(this,new ArrayList<Pickup>());
        pickupsRecycler.setAdapter(pickupAdapter);


        //get user pickps
        pickUpViewModel.mAuth.observe(this, new Observer<Authorization>() {
            @Override
            public void onChanged(Authorization authorization) {
                if (authorization!=null){
                    pickUpViewModel.getFarmerPickups(authorization.access_token,farmerId);


                }
            }
        });


        pickUpViewModel.getFarmersPickups(farmerId).observe(this, new Observer<List<Pickup>>() {
            @Override
            public void onChanged(List<Pickup> pickupList) {
                if(pickupList!=null){

                    pickupAdapter.updateData(pickupList);
                }
            }
        });
    }
}
