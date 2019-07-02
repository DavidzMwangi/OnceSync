package ke.co.davidwanjohi.oncesync.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.davidwanjohi.oncesync.R;
import ke.co.davidwanjohi.oncesync.adapters.FarmerAdapter;
import ke.co.davidwanjohi.oncesync.models.Farmer;
import ke.co.davidwanjohi.oncesync.views.FarmerViewModel;

public class AllFarmersFragment extends Fragment {

    @BindView(R.id.farmer_recycler) RecyclerView farmerRecycler;
    FarmerAdapter farmerAdapter;
    FarmerViewModel farmerViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup login_view = (ViewGroup) inflater.inflate(
                R.layout.all_farmers_fragment, container, false);
        ButterKnife.bind(this, login_view);
        return  login_view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        farmerViewModel= ViewModelProviders.of(this).get(FarmerViewModel.class);


        farmerRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        farmerAdapter=new FarmerAdapter(getActivity(),new ArrayList<Farmer>());
        farmerRecycler.setAdapter(farmerAdapter);


        farmerViewModel.allFarmers.observe(this, new Observer<List<Farmer>>() {
            @Override
            public void onChanged(List<Farmer> farmerList) {
                if (farmerList!=null){

                    farmerAdapter.updateData(farmerList);
                }
            }
        });
    }
}
