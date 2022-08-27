package SpringBoot.model;

import java.sql.Timestamp;

public class UpdateTaskDueRequest {

    Long taskId;
    Timestamp newDue;

    public UpdateTaskDueRequest() {
    }

    public UpdateTaskDueRequest(Long taskId, Timestamp newDue) {
        this.taskId = taskId;
        this.newDue = newDue;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Timestamp getNewDue() {
        return newDue;
    }

    public void setNewDue(Timestamp newDue) {
        this.newDue = newDue;
    }
}
