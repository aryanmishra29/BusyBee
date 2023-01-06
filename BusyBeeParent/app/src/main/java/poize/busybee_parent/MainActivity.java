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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        fragmentSwitch(new DashboardFragment());

        bottomNav_home = findViewById(R.id.bottomNav_homeScreen);

        bottomNav_home.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_dashboard:{
                        fragmentSwitch(new DashboardFragment());
                        break;
                    }
                    case R.id.nav_feed:{
                        fragmentSwitch(new FeedFragment());
                        break;
                    }
                    case R.id.nav_profile:{
                        fragmentSwitch(new ProfileFragment());
                        break;
                    }
                }
                return true;
            }
        });


    }



    private void fragmentSwitch(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_navigation, fragment).commit();
    }


}