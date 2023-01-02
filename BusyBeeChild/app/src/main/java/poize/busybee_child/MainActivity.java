package poize.busybee_child;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout frame_homeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame_homeScreen = findViewById(R.id.frame_homeScreen);
        SharedPreferences sharedPreferences = getSharedPreferences("userPref", Context.MODE_PRIVATE);
        String Email = sharedPreferences.getString("email",null);
        String Pass = sharedPreferences.getString("pass",null);

        if(Email == null || Pass == null){
            switchFragment(new LoginFragment());
        }
        else{
            switchFragment(new HomeFragment());
        }
    }

    private void switchFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_homeScreen , fragment)
                .commit();
    }
}