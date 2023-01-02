package poize.busybee_child;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    FrameLayout frame_homeScreen;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame_homeScreen = findViewById(R.id.frame_homeScreen);
        bottomNav = findViewById(R.id.bottomNav);
        switchFragment(new TaskFragment());
        bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottomNav_Tasks:{
                    switchFragment(new TaskFragment());
                    break;
                }
                case R.id.bottomNav_game:{

                    break;
                }
                case R.id.bottomNav_store:{

                    break;
                }
            }
            return true;
        });

    }

    private void switchFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_homeScreen, fragment)
                .commit();
    }

}