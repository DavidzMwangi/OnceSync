package ke.co.davidwanjohi.oncesync.fragments.auth;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.davidwanjohi.oncesync.R;
import ke.co.davidwanjohi.oncesync.models.Authorization;
import ke.co.davidwanjohi.oncesync.utils.NetworkResponse;
import ke.co.davidwanjohi.oncesync.views.AuthorizationViewModel;

public class LoginFragment extends Fragment {

    @BindView(R.id.user_name_edit_text) EditText emailText;
    @BindView(R.id.password_edit_text) EditText passwordText;
    @BindView(R.id.sign_btn) Button signBtn;
    @BindView(R.id.login_pr)
    ProgressBar progressBar;
    AuthorizationViewModel authorizationViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup login_view = (ViewGroup) inflater.inflate(
                R.layout.login_fragment, container, false
        );
        ButterKnife.bind(this, login_view);
        return  login_view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authorizationViewModel= ViewModelProviders.of(this).get(AuthorizationViewModel.class);

        authorizationViewModel.monitor.observe(this, new Observer<NetworkResponse>() {
            @Override
            public void onChanged(@Nullable NetworkResponse networkResponse) {
                if(networkResponse==null)
                    return;
                if(networkResponse.is_loading){
                    progressBar.setVisibility(View.VISIBLE);
                }else{
                    progressBar.setVisibility(View.GONE);
                }

                if(networkResponse.message!=null && !networkResponse.message.equals(""))
                    Snackbar.make(progressBar, networkResponse.message , Snackbar.LENGTH_LONG).show();
            }
        });

        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (emailText.getText().toString().equals("")|| emailText.getText().toString()==""){
                    emailText.setError("This field cannot be empty");


                }else if (passwordText.getText().toString().equals("") || passwordText.getText().toString()==""){
                    passwordText.setError("This field cannot be empty");

                }else{
                    //attempt to login
                    authorizationViewModel.loginOnline(emailText.getText().toString(),passwordText.getText().toString());
                }

            }
        });
    }
}
