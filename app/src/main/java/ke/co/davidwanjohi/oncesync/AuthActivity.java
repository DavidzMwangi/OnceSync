package ke.co.davidwanjohi.oncesync;

import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import ke.co.davidwanjohi.oncesync.fragments.auth.LandingPageFragment;
import ke.co.davidwanjohi.oncesync.fragments.auth.LoginFragment;
import ke.co.davidwanjohi.oncesync.fragments.auth.RegisterFragment;
import ke.co.davidwanjohi.oncesync.fragments.auth.WelcomeFragment;
import ke.co.davidwanjohi.oncesync.models.Authorization;
import ke.co.davidwanjohi.oncesync.views.AuthorizationViewModel;

public class AuthActivity extends AppCompatActivity {

    AuthorizationViewModel authorizationViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        ButterKnife.bind(this);
        authorizationViewModel= ViewModelProviders.of(this).get(AuthorizationViewModel.class);

        authorizationViewModel.authorization.observe(this, new Observer<Authorization>() {
            @Override
            public void onChanged(Authorization authorization) {
                if (authorization!=null){
                    Intent intent=new Intent(AuthActivity.this,MainActivity.class);
                    startActivity(intent);

                }else{

                    changeFragment(1);

                }
            }
        });

    }

    public void changeFragment(int page){

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        switch (page){

            case 0:
                transaction.replace(R.id.auth_frame,new WelcomeFragment(),"Welcome Fragment").commit();
                break;
            case 1:

                transaction.replace(R.id.auth_frame,new LoginFragment(),"Login Fragment").commit();
                break;

            case 2:

                transaction.replace(R.id.auth_frame,new RegisterFragment(),"Register Fragment").commit();
                break;

            case 3:
                transaction.replace(R.id.auth_frame,new LandingPageFragment(),"Landing").commit();
                break;
        }
    }
}
