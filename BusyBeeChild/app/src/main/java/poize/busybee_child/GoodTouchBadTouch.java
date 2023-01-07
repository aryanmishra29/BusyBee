package poize.busybee_child;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class GoodTouchBadTouch extends AppCompatActivity {

    private ViewPager screenPager;
    GTBT_ViewPageAdapter gtbt_viewPageAdapter;
    //TabLayout tabIndicator ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_touch_bad_touch);

        //tabIndicator = findViewById(R.id.tab_indicator);"Someone threatening or trying to hurt you"

        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem ( "Good Touch",  "Brother pulling your cheeks", R.drawable.gtbt1));
        mList.add(new ScreenItem ( "Bad Touch",  "Stranger touching you \n that is making you feel uneasy", R.drawable.gtbt2));
        mList.add(new ScreenItem ( "Good Touch",  "Dad giving bath to you \n and cleaning your private parts", R.drawable.gtbt6));
        mList.add(new ScreenItem ( "Bad Touch",  "Stranger touching your private parts \n and asking to keep it as a secret", R.drawable.gtbt3));
        mList.add(new ScreenItem ( "Bad Touch", "Someone threatening you \n or trying to hurt you" , R.drawable.gtbt4));
        mList.add(new ScreenItem ( "Good Touch",  "Teacher patting your back", R.drawable.gtbt5));

        // setup viewpager
        screenPager = findViewById(R.id.screen_viewpager) ;
        gtbt_viewPageAdapter = new GTBT_ViewPageAdapter ( this, mList);
        screenPager.setAdapter (gtbt_viewPageAdapter);
      //  tabIndicator.setupWithViewPager( screenPager);



    }
}