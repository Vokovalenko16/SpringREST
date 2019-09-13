package com.vokovalenko16.framework.repo;

import com.vokovalenko16.framework.enums.ValidFlag;
import com.vokovalenko16.framework.po.User;
import java.util.Optional;


/**
 * User's repository.
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 6/25/15
 * @since JDK1.8
 */
public interface UserRepository extends CustomRepository<User, Long> {

  Optional<User> findByIdAndValidFlag(Long id, ValidFlag validFlag);

  Optional<User> findByUsrAndValidFlag(String usr, ValidFlag validFlag);

}
