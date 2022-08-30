package SpringBoot.model;

import SpringBoot.appuser.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "todolist")
public class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    Long listId;

    @Column(name = "listname")
    String listName;

    @Column(name = "created_at")
    Timestamp created;

    @Column(name = "updated_at")
    Timestamp updated;
/*
    @ManyToOne
    @JoinColumn(name = "userid",nullable=false)
    private UserTable guser;

 */
    @ManyToOne
    @JoinColumn(name = "userid",nullable=false)
    private AppUser appUser;

    //reference to Task table
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "toDoList", fetch = FetchType.LAZY)
    private Set<Task> tasks;


    public ToDoList(Long listId, Long userId, String listName, Timestamp created, Timestamp updated) {
        this.listId = listId;
        this.listName = listName;
        this.created = created;
        this.updated = updated;
    }

    public ToDoList() {

    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
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
/*
    @JsonBackReference
    public UserTable getGuser() {
        return guser;
    }

    public void setGuser(UserTable guser) {
        this.guser = guser;
    }
 */

    @JsonBackReference
    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @JsonManagedReference
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}