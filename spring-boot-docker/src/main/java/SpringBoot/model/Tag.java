package SpringBoot.model;

import SpringBoot.appuser.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    Long tagId;

    @Column(name = "name")
    String name;

    @Column(name = "created_at")
    Timestamp created;
/*
    @ManyToOne
    @JoinColumn(name = "userid",nullable=false)
    private UserTable tuser;
 */
    @ManyToOne
    @JoinColumn(name = "userid",nullable=false)
    private AppUser tappUser;

    public Tag() {
    }

    public Tag(Long tagId, String name, Timestamp created) {
        this.tagId = tagId;
        this.name = name;
        this.created = created;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
/*
    @JsonBackReference
    public UserTable getTuser() {
        return tuser;
    }

    public void setTuser(UserTable tuser) {
        this.tuser = tuser;
    }
 */

    @JsonBackReference
    public AppUser getTappUser() {
        return tappUser;
    }

    public void setTappUser(AppUser tappUser) {
        this.tappUser = tappUser;
    }
}
