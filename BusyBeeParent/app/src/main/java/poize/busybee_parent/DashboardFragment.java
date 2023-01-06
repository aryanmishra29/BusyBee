package poize.busybee_parent;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private String task;
    private int reward;
    private boolean isCompleted;
    private EditText et_taskName;
    private EditText et_taskReward;
    private Button button_addTask;
    private RecyclerView rv_tasks;
    private ArrayList<TaskModel> taskList;
    private TaskAdapter taskAdapter;
    private String user;
    private TextView tv_welcome;

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
        tv_welcome = view.findViewById(R.id.tv_welcome);
        rv_tasks = view.findViewById(R.id.rv_tasks);
        rv_tasks.setLayoutManager(new LinearLayoutManager(getActivity()));
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        db.collection("User")
                .document(auth.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        user = documentSnapshot.getString("parentName");
                        if(user != null) tv_welcome.setText("Hello "+user+"!");
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });





        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList);
        rv_tasks.setAdapter(taskAdapter);

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

        db.collection("Task")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){
                            TaskModel model = d.toObject(TaskModel.class);
                            taskList.add(model);
                        }
                        taskAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });



        return view;
    }
}