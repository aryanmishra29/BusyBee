package poize.busybee_parent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class SignUpActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_parentName = findViewById(R.id.et_parentName);
        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);
        et_childName = findViewById(R.id.et_childName);
        et_childDOB = findViewById(R.id.et_DOB);
        buttn_signup = findViewById(R.id.button_signup);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);


        DatePickerDialog.OnDateSetListener date = (view12, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        };
        et_childDOB.setOnClickListener(view1 -> new DatePickerDialog(this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        buttn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentName = et_parentName.getText().toString();
                email = et_email.getText().toString();
                pass = et_pass.getText().toString();
                childName = et_childName.getText().toString();
                childDOB = myCalendar.getTime();
                if (!parentName.isEmpty() && !email.isEmpty() && !pass.isEmpty() && !childName.isEmpty() && !childDOB.toString().isEmpty()) {

                    progressDialog.setMessage("Authenticating entered details");
                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    progressDialog.cancel();
                                    firebaseFirestore.collection("User")
                                            .document(FirebaseAuth.getInstance().getUid())
                                            .set(new UserModel(parentName, email, pass, childName, childDOB));
                                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                                      @Override
                                                      public void onFailure(@NonNull Exception e) {
                                                          Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                          progressDialog.cancel();
                                                      }
                                                  }
                            );
                } else {
                    Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        et_childDOB.setText(dateFormat.format(myCalendar.getTime()));
    }
}