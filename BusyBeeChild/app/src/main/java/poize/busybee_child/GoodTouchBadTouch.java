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

        //tabIndicator = findViewById(R.id.tab_indicator);

        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem ( "Fresh Food",  "Description of FF", R.drawable.img_1));
        mList.add(new ScreenItem ( "Fast Delivery", "Description of FD", R.drawable.img_2));
        mList.add(new ScreenItem ( "Easy Payment", "Description of EP", R.drawable.img_3));
        // setup viewpager
        screenPager = findViewById(R.id.screen_viewpager) ;
        gtbt_viewPageAdapter = new GTBT_ViewPageAdapter ( this, mList);
        screenPager.setAdapter (gtbt_viewPageAdapter);
      //  tabIndicator.setupWithViewPager( screenPager);



    }
}