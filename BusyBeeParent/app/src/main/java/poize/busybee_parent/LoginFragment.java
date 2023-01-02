package poize.busybee_parent;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private EditText et_email;
    private EditText et_pass;
    private Button buttn_login;
    private String email;
    private String pass;
    private TextView tv_loginToSignup;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        et_email = view.findViewById(R.id.et_loginEmail);
        et_pass = view.findViewById(R.id.et_loginPass);
        buttn_login = view.findViewById(R.id.button_login);
        tv_loginToSignup = view.findViewById(R.id.tv_login_to_signup);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String Email = sharedPreferences.getString("email",null);
        String Pass = sharedPreferences.getString("pass",null);

        if(Email == null || Pass ==null){

            tv_loginToSignup.setOnClickListener(view1 -> {
                    fragmentSwitch(new SignupFragment());
                    Toast.makeText(getActivity(),"SignUp",Toast.LENGTH_SHORT).show();
            });

            firebaseAuth = FirebaseAuth.getInstance();

            buttn_login.setOnClickListener(view12 -> {
                email = et_email.getText().toString().trim();
                pass = et_pass.getText().toString().trim();
                if(!email.isEmpty() && !pass.isEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(email,pass)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    editor.putString("email", email);
                                    editor.putString("pass", pass);
                                    editor.apply();
                                    Toast.makeText(getActivity(),"Login Successful",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else {
                    Toast.makeText(getActivity(),"Empty Email or Password",Toast.LENGTH_SHORT).show();
                }
            });

        }
        else{
            //TODO: Directly send to the home fragment
        }


        return view;
    }

    private void fragmentSwitch(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_navigation, fragment).commit();
    }
}
