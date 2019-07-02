package ke.co.davidwanjohi.oncesync.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.davidwanjohi.oncesync.R;
import ke.co.davidwanjohi.oncesync.models.Authorization;
import ke.co.davidwanjohi.oncesync.views.FarmerViewModel;

public class NewFarmerFragment extends Fragment {

    @BindView(R.id.name) AppCompatEditText name;
    @BindView(R.id.telephone_no) AppCompatEditText telephoneNo;
    @BindView(R.id.account_number) AppCompatEditText accountNo;
    @BindView(R.id.location) AppCompatEditText location;
    @BindView(R.id.save_btn) AppCompatButton saveBtn;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    static int gender=0; //0-male 1-female
    FarmerViewModel farmerViewModel;
    Authorization authorization;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup login_view = (ViewGroup) inflater.inflate(
                R.layout.new_farmer_fragment, container, false);
        ButterKnife.bind(this, login_view);
        return  login_view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        farmerViewModel= ViewModelProviders.of(this).get(FarmerViewModel.class);

        farmerViewModel.mAuth.observe(this, new Observer<Authorization>() {
            @Override
            public void onChanged(Authorization auth) {
                if (auth !=null){

                    authorization=auth;
                }
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                    switch(checkedId){
                        case R.id.male:
                            // do operations specific to this selection
                            gender=0;
                            break;
                        case R.id.female:
                            // do operations specific to this selection
                            gender=1;
                            break;

                    }
                });

                //save the data online
                farmerViewModel.saveFarmerOnline(authorization.access_token,name.getText().toString(),gender,telephoneNo.getText().toString(),accountNo.getText().toString(),location.getText().toString());
            }
        });





    }
}
