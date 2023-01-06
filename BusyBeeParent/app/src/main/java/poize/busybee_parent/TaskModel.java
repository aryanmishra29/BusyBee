package poize.busybee_parent;

public class TaskModel {
    private String task;
    private int reward;
    private boolean isCompleted;

    public TaskModel() {
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public TaskModel(String task, int reward, boolean isCompleted) {
        this.task = task;
        this.reward = reward;
        this.isCompleted = isCompleted;
    }
}
