package SpringBoot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Reminder")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reminder_id")
    Long reminderId;

    @Column(name = "remindertime")
    Timestamp reminderTime;

    @Column(name = "created_at")
    Timestamp created;

    @Column(name = "updated_at")
    Timestamp updated;

    @ManyToOne
    @JoinColumn(name = "taskId",nullable=false)
    private Task rtask;

    public Long getReminderId() {
        return reminderId;
    }

    public void setReminderId(Long reminderId) {
        this.reminderId = reminderId;
    }

    public Timestamp getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(Timestamp reminderTime) {
        this.reminderTime = reminderTime;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @JsonBackReference
    public Task getRtask() {
        return rtask;
    }

    public void setRtask(Task rtask) {
        this.rtask = rtask;
    }
}
