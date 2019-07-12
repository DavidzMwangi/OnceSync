package ke.co.davidwanjohi.oncesync;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.Menu;

import butterknife.ButterKnife;
import ke.co.davidwanjohi.oncesync.fragments.home.AllFarmersFragment;
import ke.co.davidwanjohi.oncesync.fragments.home.DashboardFragment;
import ke.co.davidwanjohi.oncesync.fragments.home.NewFarmerFragment;
import ke.co.davidwanjohi.oncesync.fragments.home.NewPickupFragment;
import ke.co.davidwanjohi.oncesync.models.Authorization;
import ke.co.davidwanjohi.oncesync.models.User;
import ke.co.davidwanjohi.oncesync.views.FarmerViewModel;
import ke.co.davidwanjohi.oncesync.views.UserViewModel;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    UserViewModel userViewModel;
    FarmerViewModel farmerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        ButterKnife.bind(this);
        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);
        farmerViewModel=ViewModelProviders.of(this).get(FarmerViewModel.class);
        userViewModel.getAuthUserInfo().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                //get user data here
            }
        });

        userViewModel.mAuth.observe(this, new Observer<Authorization>() {
            @Override
            public void onChanged(Authorization authorization) {
                if (authorization!=null){

                    userViewModel.getAuthInfoOnline(authorization.access_token);
                    farmerViewModel.getFarmersOnline(authorization.access_token);
                }
            }
        });

        changeFragment(4);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_new_farmer) {
            changeFragment(2);
            // Handle the camera action
        } else if (id == R.id.nav_new_pickup) {
            changeFragment(3);
        } else if (id == R.id.nav_all_farmers) {
            changeFragment(1);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeFragment(int page){

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        switch (page){


            case 1:
                transaction.replace(R.id.main_frame,new AllFarmersFragment(),"All Farmers").commit();

                break;
            case 2:

                transaction.replace(R.id.main_frame,new NewFarmerFragment(),"New Fragment").commit();
                break;
            case 3:
                transaction.replace(R.id.main_frame,new NewPickupFragment(),"New Pickup").commit();
                break;

            case 4:

                transaction.replace(R.id.main_frame,new DashboardFragment(),"Dashboard Fragment").commit();
                break;
        }
    }
}
