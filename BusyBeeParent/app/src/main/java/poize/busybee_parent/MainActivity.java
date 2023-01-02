package poize.busybee_parent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Gravity;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navView_HomeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        to change the text color in action bar
//        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"black\">" + "BusyBee" + "</font>"));

        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("userPref", Context.MODE_PRIVATE);
        String Email = sharedPreferences.getString("email",null);
        String Pass = sharedPreferences.getString("pass",null);

        if(Email == null || Pass == null){
            fragmentSwitch(new LoginFragment());
        }
        else{
           fragmentSwitch(new DashboardFragment());
        }


        drawerLayout = findViewById(R.id.drawer_homeScreen);
        navView_HomeScreen = findViewById(R.id.navView_homeScreen);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navView_HomeScreen.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        switch(item.getItemId()){
            case R.id.nav_account:{
                //TODO: fragmentswitch to Account FRAGMENT
            }
            case R.id.nav_logout:{
                SharedPreferences sharedPreferences = getSharedPreferences("userPref", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();
                fragmentSwitch(new LoginFragment());
            }
        }
        return false;
    }

    private void fragmentSwitch(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_navigation, fragment).commit();
    }


}