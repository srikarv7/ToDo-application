package SpringBoot.model;

import java.sql.Timestamp;

public class AddReminderRequest {

    Long taskId;
    Timestamp reminderTime;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Timestamp getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(Timestamp reminderTime) {
        this.reminderTime = reminderTime;
    }
}
