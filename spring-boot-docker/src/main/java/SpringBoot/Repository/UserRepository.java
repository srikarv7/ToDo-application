package SpringBoot.Repository;

import SpringBoot.model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long> {

    List<UserTable> findByEmail(String email);

}
