package ke.co.davidwanjohi.oncesync;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.davidwanjohi.oncesync.models.Farmer;
import ke.co.davidwanjohi.oncesync.views.FarmerViewModel;

public class FarmerActivity extends AppCompatActivity {

    @BindView(R.id.name) TextView name;
    @BindView(R.id.gender) TextView gender;
    @BindView(R.id.telephone_no) TextView telephoneNo;
    @BindView(R.id.account_number) TextView accountNo;
    @BindView(R.id.location) TextView location;

    FarmerViewModel farmerViewModel;
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
    }
}
