package SpringBoot.Repository;

import SpringBoot.model.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<ToDoList, Long> {
}
