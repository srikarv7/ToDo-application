package SpringBoot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "Task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    Long taskId;

    @ManyToOne
    @JoinColumn(name = "listId",nullable=false)
    private ToDoList toDoList;

    @Column(name = "taskname")
    String taskName;

    @Column(name = "summary")
    String summary;

    @Column(name = "duedate")
    Timestamp dueDate;

    @Column(name="state")
    String state;

    @Column(name = "priority")
    int priority;

    //list of reminders we are the primary key
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rtask", fetch = FetchType.LAZY)
    private Set<Reminder> reminders;

    @Column(name = "created_at")
    Timestamp created;

    @Column(name = "updated_at")
    Timestamp updated;

    //list of attachments
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gtask", fetch = FetchType.LAZY)
    private Set<Attachment> attachments;

    //list of tags

    //list of comments
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utask", fetch = FetchType.LAZY)
    private Set<Comment> comments;

    /*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utask", fetch = FetchType.LAZY)
    private Set<Tag> tags;
    */

    public Task() {
    }

    public Task(Long taskId, String taskName, String summary, Timestamp dueDate, int priority, Timestamp created, Timestamp updated,String state) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.summary = summary;
        this.dueDate = dueDate;
        this.priority = priority;
        this.created = created;
        this.updated = updated;
        this.state = state;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @JsonBackReference
    public ToDoList getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    @JsonManagedReference
    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    @JsonManagedReference
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @JsonManagedReference
    public Set<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(Set<Reminder> reminders) {
        this.reminders = reminders;
    }
}
