package poize.busybee_child;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HoneypotFragment extends Fragment {

    private TextView tv_honey_honeypot;
    private TextView tv_childName_honeypot;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public HoneypotFragment() {
        // Required empty public constructor
    }

    public static HoneypotFragment newInstance(String param1, String param2) {
        HoneypotFragment fragment = new HoneypotFragment();
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
        View view = inflater.inflate(R.layout.fragment_honeypot, container, false);
        tv_honey_honeypot = view.findViewById(R.id.tv_honey_honeypot);
        tv_childName_honeypot = view.findViewById(R.id.tv_childName_honeypot);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        db.collection("User")
                .document(auth.getUid())
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    String childName = documentSnapshot.get("childName").toString();
                    long honey =(long) documentSnapshot.get("Honey");

                    tv_honey_honeypot.setText("You have "+honey+" Honey.");
                    tv_childName_honeypot.setText("Hey "+childName+"!");
                });

        return view;
    }
}