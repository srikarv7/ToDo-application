package SpringBoot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    Long commentId;

    // relation to task table. this is the foreign key
    @ManyToOne
    @JoinColumn(name = "taskId",nullable=false)
    private Task utask;

    @Column(name = "comment")
    String comment;

    @Column(name = "created_at")
    Timestamp created;

    @Column(name = "updated_at")
    Timestamp updated;

    public Comment() {
    }

    public Comment(Long commentId, String comment, Timestamp created, Timestamp updated) {
        this.commentId = commentId;
        this.comment = comment;
        this.created = created;
        this.updated = updated;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    @JsonBackReference
    public Task getUtask() {
        return utask;
    }

    public void setUtask(Task utask) {
        this.utask = utask;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
}
