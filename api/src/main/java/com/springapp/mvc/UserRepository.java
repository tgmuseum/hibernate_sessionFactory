package com.springapp.mvc;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13. 7. 22
 * Time: 오후 1:19
 * To change this template use File | Settings | File Templates.
 */
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
