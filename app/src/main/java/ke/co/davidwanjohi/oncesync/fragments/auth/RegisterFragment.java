package ke.co.davidwanjohi.oncesync.fragments.auth;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

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
import ke.co.davidwanjohi.oncesync.utils.NetworkResponse;
import ke.co.davidwanjohi.oncesync.views.AuthorizationViewModel;

public class RegisterFragment extends Fragment {

    @BindView(R.id.name_edit_text) EditText name;
    @BindView(R.id.user_email) EditText email;
    @BindView(R.id.password_edit_text) EditText password;
    @BindView(R.id.password_confirm_edit_text) EditText passwordConfirm;
    @BindView(R.id.sign_up_btn)
    Button signUp;
    @BindView(R.id.register_pr)
    ProgressBar progressBar;
    AuthorizationViewModel authorizationViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup login_view = (ViewGroup) inflater.inflate(
                R.layout.auth_register_fragment, container, false
        );
        ButterKnife.bind(this, login_view);
        return  login_view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        signUp.setOnClickListener(v -> authorizationViewModel.registerOnline(name.getText().toString(),email.getText().toString(),1,password.getText().toString(),passwordConfirm.getText().toString()));

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
    }
}
