package ke.co.davidwanjohi.oncesync;

import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import ke.co.davidwanjohi.oncesync.fragments.auth.LoginFragment;
import ke.co.davidwanjohi.oncesync.fragments.auth.RegisterFragment;
import ke.co.davidwanjohi.oncesync.fragments.auth.WelcomeFragment;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        changeFragment(0);
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


        }
    }
}
