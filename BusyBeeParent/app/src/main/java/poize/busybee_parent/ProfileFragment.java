package poize.busybee_parent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Date;


public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView tv_signout;
    private TextView tv_parentName;
    private TextView tv_email;
    private TextView tv_childName;
    private TextView tv_honey;
    private TextView tv_childDOB;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String parentName;
    private String childName;
    private String email;
    private Date childDOB;
    private long honey;


    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        tv_signout = view.findViewById(R.id.tv_signout);
        tv_childDOB = view.findViewById(R.id.tv_childDOB);
        tv_email = view.findViewById(R.id.tv_emailProfile);
        tv_childName = view.findViewById(R.id.tv_childName);
        tv_parentName = view.findViewById(R.id.tv_parentName);
        tv_honey = view.findViewById(R.id.tv_honey);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        tv_signout.setOnClickListener(view1 -> {
            editor.clear().commit();
            startActivity(new Intent(getActivity(),LoginActivity.class));
        });

        firebaseFirestore.collection("User")
                .document(firebaseAuth.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        parentName = documentSnapshot.get("parentName").toString();
                        childName = documentSnapshot.get("childName").toString();
                        Timestamp timestamp = (Timestamp) documentSnapshot.get("childDOB");
                        childDOB = timestamp.toDate();

                        email = documentSnapshot.get("email").toString();
                        honey = (long) documentSnapshot.get("Honey");

                        tv_parentName.setText(parentName);
                        tv_childName.setText(childName);
                        tv_email.setText(email);
                        tv_honey.setText(Long.toString(honey));
                        tv_childDOB.setText(DateFormat.getDateInstance().format(childDOB));
                    }
                });



        return view;
    }
}