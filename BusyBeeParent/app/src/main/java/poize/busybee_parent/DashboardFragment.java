package poize.busybee_parent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DashboardFragment extends Fragment {

    private FirebaseFirestore db;
    private String task;
    private int reward;
    private boolean isCompleted;
    private EditText et_taskName;
    private EditText et_taskReward;
    private Button button_addTask;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        et_taskName = view.findViewById(R.id.et_taskName);
        et_taskReward = view.findViewById(R.id.et_taskReward);
        button_addTask = view.findViewById(R.id.button_addTask);
        db = FirebaseFirestore.getInstance();

        button_addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = et_taskName.getText().toString();
                reward = Integer.parseInt(et_taskReward.getText().toString());

                db.collection("Task")
                        .add(new TaskModel(task,reward,false))
                        .addOnSuccessListener(documentReference -> Toast.makeText(getActivity(),"Task Set Successfully",Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show());
            }
        });


        return view;
    }
}