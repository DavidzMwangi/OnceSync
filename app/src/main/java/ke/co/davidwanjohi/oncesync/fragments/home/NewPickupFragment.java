package ke.co.davidwanjohi.oncesync.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.davidwanjohi.oncesync.MainActivity;
import ke.co.davidwanjohi.oncesync.R;
import ke.co.davidwanjohi.oncesync.models.Authorization;
import ke.co.davidwanjohi.oncesync.models.Farmer;
import ke.co.davidwanjohi.oncesync.models.Pickup;
import ke.co.davidwanjohi.oncesync.utils.NetworkResponse;
import ke.co.davidwanjohi.oncesync.views.FarmerViewModel;
import ke.co.davidwanjohi.oncesync.views.PickUpViewModel;

public class NewPickupFragment extends Fragment {

    @BindView(R.id.no_of_litres) AppCompatEditText litres;
    @BindView(R.id.account_number) AppCompatEditText accountNo;
    @BindView(R.id.farmer_spinner) Spinner farmersSpinner;
    @BindView(R.id.date_picker) DatePicker datePicker;
    @BindView(R.id.save_btn) AppCompatButton saveBtn;
    @BindView(R.id.progress) ProgressBar progressBar;

    ArrayAdapter<Farmer> farmerArrayAdapter;
    Farmer selectedFarmer;
    Authorization authorization;
    FarmerViewModel farmerViewModel;
    PickUpViewModel pickUpViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        ViewGroup login_view = (ViewGroup) inflater.inflate(
                R.layout.new_pickup_fragment, container, false);
        ButterKnife.bind(this, login_view);
        return  login_view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        farmerViewModel= ViewModelProviders.of(this).get(FarmerViewModel.class);
        pickUpViewModel=ViewModelProviders.of(this).get(PickUpViewModel.class);
        farmerViewModel.mAuth.observe(this, new Observer<Authorization>() {
            @Override
            public void onChanged(Authorization auth) {
                if (auth!=null){
                    authorization=auth;
                    farmerViewModel.getFarmersOnline(auth.access_token);
                }
            }
        });


        farmerViewModel.allFarmers.observe(this, new Observer<List<Farmer>>() {
            @Override
            public void onChanged(List<Farmer> farmers) {
                if (farmers!=null){

                    ArrayAdapter<Farmer> adapter=new ArrayAdapter<Farmer>(getActivity(),android.R.layout.simple_spinner_item,farmers);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    farmersSpinner.setAdapter(adapter);
                    farmerArrayAdapter=adapter;
                }
            }
        });


        pickUpViewModel.monitor.observe(this, new Observer<NetworkResponse>() {
            @Override
            public void onChanged(NetworkResponse networkResponse) {
                if (networkResponse!=null){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(),"PickUp Saved Successfully",Toast.LENGTH_SHORT).show();
                    FragmentTransaction transaction=((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.main_frame,new AllFarmersFragment(),"all").commit();
                }

            }
        });
        farmersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedFarmer=farmerArrayAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
//MM-dd-yyyy

                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date d = new Date(year, month, day);
                String strDate = dateFormatter.format(d);


                pickUpViewModel.savePickupOnline(authorization.access_token,strDate,Double.parseDouble(litres.getText().toString()),accountNo.getText().toString(),selectedFarmer.getId());

            }
        });
    }
}
