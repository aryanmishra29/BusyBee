package poize.busybee_parent;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        fragmentSwitch(new FeedFragment());

        bottomNav_home = findViewById(R.id.bottomNav_homeScreen);
        bottomNav_home.getMenu().getItem(1).setChecked(true);

        bottomNav_home.setOnItemSelectedListener(item -> {
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
        });


    }



    private void fragmentSwitch(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_navigation, fragment).commit();
    }


}