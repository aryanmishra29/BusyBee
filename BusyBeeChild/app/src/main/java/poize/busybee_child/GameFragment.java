package poize.busybee_child;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GameFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LinearLayout ll_solarSystem;


    private String mParam1;
    private String mParam2;

    public GameFragment() {
        // Required empty public constructor
    }

    public static GameFragment newInstance(String param1, String param2) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game, container, false);
        ll_solarSystem = view.findViewById(R.id.ll_solarSystem);

        ll_solarSystem.setOnClickListener(view1 -> {
            Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.PlaneDetect.BusyBeeAR");
            if(intent != null){
                startActivity(intent);
            }
            else{
                Toast.makeText(getActivity(),"The app you are looking for is not available",Toast.LENGTH_SHORT).show();
            }
        });

        CardView test = view.findViewById(R.id.cv_02);
        test.setOnClickListener(view12 -> {
            Toast.makeText(getActivity(),"Clicked",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(),GoodTouchBadTouch.class);
            startActivity(intent);
        });



        return view;




    }
}