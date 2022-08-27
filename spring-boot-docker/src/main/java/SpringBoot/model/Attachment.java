package SpringBoot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Attachment")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    Long attachmentId;

    //reference to task. we are the foreign key
    @ManyToOne
    @JoinColumn(name = "taskId",nullable=false)
    private Task gtask;

    @Column(name = "created_at")
    Timestamp created;

    @Column(name = "attachmentname")
    String attachmentName;

    public Attachment() {
    }

    public Attachment(Long attachmentId, Timestamp created, String attachmentName) {
        this.attachmentId = attachmentId;
        this.created = created;
        this.attachmentName = attachmentName;
    }

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    @JsonBackReference
    public Task getGtask() {
        return gtask;
    }

    public void setGtask(Task gtask) {
        this.gtask = gtask;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
}
