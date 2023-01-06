package poize.busybee_child;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class GTBT_ViewPageAdapter extends PagerAdapter {

    Context mContext;
    List<ScreenItem> mListScreen;

    public GTBT_ViewPageAdapter (Context mContext, List<ScreenItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen= mListScreen;
    }
    @NonNull
    @Override
    public Object instantiateItem (@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen, null);
        ImageView imgslide = layoutScreen.findViewById(R.id.intro_img);
        TextView title = layoutScreen.findViewById(R.id.intro_title);
        TextView description = layoutScreen.findViewById(R.id.intro_description);
        title.setText(mListScreen.get(position).getTitle());
        description.setText(mListScreen.get(position).getDescription());
        imgslide.setImageResource(mListScreen.get(position).getScreenImg());
        container.addView(layoutScreen);
        return layoutScreen;
    }
    public int getCount() {
        return mListScreen.size();
    }


    public boolean isViewFromObject(View view, Object object) {
        return view == object ;
    }

    public void destroyItem(@NonNull ViewGroup container , int psoition , @NonNull Object object){
        container.removeView((View)object);
    }
}
