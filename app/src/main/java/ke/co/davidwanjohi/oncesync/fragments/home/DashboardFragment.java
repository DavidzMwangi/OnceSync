package ke.co.davidwanjohi.oncesync.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.davidwanjohi.oncesync.MainActivity;
import ke.co.davidwanjohi.oncesync.R;

public class DashboardFragment extends Fragment {

    @BindView(R.id.add_farmer) LinearLayout addFarmer;
    @BindView(R.id.add_collection) LinearLayout addCollection;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup login_view = (ViewGroup) inflater.inflate(
                R.layout.dashboard_fragment, container, false);
        ButterKnife.bind(this, login_view);
        return  login_view;    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_frame,new NewFarmerFragment(),"New Farmer").commit();
            }
        });

        addCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    FragmentTransaction transaction=((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.main_frame,new NewPickupFragment(),"New Pickup").commit();
            }
        });
    }
}
