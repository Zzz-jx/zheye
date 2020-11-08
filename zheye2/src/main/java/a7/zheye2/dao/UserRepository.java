package a7.zheye2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import a7.zheye2.pojo.User;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
