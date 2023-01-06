package poize.busybee_parent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    long honey;

    private ArrayList<TaskModel> taskList;

    public TaskAdapter(ArrayList<TaskModel> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View taskView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_task,parent,false);
        return new TaskViewHolder(taskView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.tv_taskName.setText(taskList.get(position).getTask());
        holder.tv_taskReward.setText(taskList.get(position).getReward()+" Honey");
        holder.cb_task.setChecked(taskList.get(position).isCompleted());

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView tv_taskName, tv_taskReward;
        CheckBox cb_task;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_taskName = itemView.findViewById(R.id.tv_taskName);
            tv_taskReward = itemView.findViewById(R.id.tv_taskReward);
            cb_task = itemView.findViewById(R.id.cb_task);


        }
    }
}
