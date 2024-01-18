package Misc.repository;

import Misc.model.oldUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<oldUser, Long> {
}
