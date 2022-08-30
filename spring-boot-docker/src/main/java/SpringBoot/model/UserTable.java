package SpringBoot.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;


//@Entity
//@Table(name="usertable")
public class UserTable {

/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long userId;

    @Column(name = "firstname")
    String firstName;

    @Column(name = "middlename")
    String middleName;

    @Column(name = "lastname")
    String lastName;

    @Column(name = "email")
    String email;

    @Column(name = "is_validated")
    boolean isValidated;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "guser", fetch = FetchType.LAZY)
    private Set<ToDoList> lists;

    //add reference to tag. We are primary key
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "tuser", fetch = FetchType.LAZY)
    private Set<Tag> tags;



    public UserTable() {
    }

    public UserTable(Long userId, String firstName, String middleName, String lastName, String email, boolean isValidated) {
        this.userId = userId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.isValidated = isValidated;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }


    @JsonManagedReference
    public Set<ToDoList> getLists() {
        return lists;
    }

    public void setLists(Set<ToDoList> lists) {
        this.lists = lists;
    }

    @JsonManagedReference
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

 */

}
