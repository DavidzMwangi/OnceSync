package ke.co.davidwanjohi.oncesync.fragments.auth;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.davidwanjohi.oncesync.AuthActivity;
import ke.co.davidwanjohi.oncesync.R;

public class WelcomeFragment extends Fragment {

    @BindView(R.id.login_btn) Button loginBtn;
    @BindView(R.id.register_btn) Button registerBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup login_view = (ViewGroup) inflater.inflate(
                R.layout.auth_welcome_fragment, container, false
        );
        ButterKnife.bind(this, login_view);
        return  login_view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=((AuthActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.auth_frame,new LoginFragment(),"Login").commit();
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=((AuthActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.auth_frame,new RegisterFragment(),"Register").commit();
            }
        });
    }
}
