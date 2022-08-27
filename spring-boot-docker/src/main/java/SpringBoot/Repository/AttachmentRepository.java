package SpringBoot.Repository;

import SpringBoot.model.Attachment;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
