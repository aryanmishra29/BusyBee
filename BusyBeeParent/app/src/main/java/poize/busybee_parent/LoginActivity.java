package poize.busybee_parent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText et_email;
    private EditText et_pass;
    private Button buttn_login;
    private String email;
    private String pass;
    private TextView tv_loginToSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = findViewById(R.id.et_loginEmail);
        et_pass = findViewById(R.id.et_loginPass);
        buttn_login = findViewById(R.id.button_login);
        tv_loginToSignup = findViewById(R.id.tv_login_to_signup);

        SharedPreferences sharedPreferences = getSharedPreferences("userPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String Email = sharedPreferences.getString("email",null);
        String Pass = sharedPreferences.getString("pass",null);

        if(Email == null || Pass == null){

            tv_loginToSignup.setOnClickListener(view1 -> {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
//                    Toast.makeText(LoginActivity.this,"SignUp",Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else {
                    Toast.makeText(LoginActivity.this,"Empty Email or Password",Toast.LENGTH_SHORT).show();
                }
            });

        }
        else{
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }

    }
}