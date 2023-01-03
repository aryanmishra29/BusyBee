package poize.busybee_parent;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class SignupFragment extends Fragment {

    private EditText et_parentName;
    private EditText et_email;
    private EditText et_pass;
    private EditText et_childName;
    private EditText et_childDOB;
    private Calendar myCalendar = Calendar.getInstance();
    private Button buttn_signup;

    private String parentName;
    private String email;
    private String pass;
    private String childName;
    private Date childDOB;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private ProgressDialog progressDialog;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public SignupFragment() {
        // Required empty public constructor
    }


    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        et_parentName = view.findViewById(R.id.et_parentName);
        et_email = view.findViewById(R.id.et_email);
        et_pass = view.findViewById(R.id.et_pass);
        et_childName = view.findViewById(R.id.et_childName);
        et_childDOB = view.findViewById(R.id.et_DOB);
        buttn_signup = view.findViewById(R.id.button_signup);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(getContext());


        DatePickerDialog.OnDateSetListener date = (view12, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateLabel();
        };
        et_childDOB.setOnClickListener(view1 -> new DatePickerDialog(getContext(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        buttn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentName = et_parentName.getText().toString();
                email = et_email.getText().toString();
                pass = et_pass.getText().toString();
                childName = et_childName.getText().toString();
                childDOB = myCalendar.getTime();
                if(!parentName.isEmpty() && !email.isEmpty() && !pass.isEmpty() && !childName.isEmpty() && !childDOB.toString().isEmpty() ){

                progressDialog.setMessage("Authenticating entered details");
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                progressDialog.cancel();
                                fragmentSwitch(new LoginFragment());
                                firebaseFirestore.collection("User")
                                        .document(FirebaseAuth.getInstance().getUid())
                                        .set(new UserModel(parentName,email, pass, childName, childDOB));

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.cancel();
                            }
                        }
                        );}
                else{
                    Toast.makeText(getActivity(),"Please fill all fields",50).show();
                }
            }
        });

        return view;
    }

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        et_childDOB.setText(dateFormat.format(myCalendar.getTime()));
    }
    private void fragmentSwitch(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_navigation, fragment).commit();
    }

}