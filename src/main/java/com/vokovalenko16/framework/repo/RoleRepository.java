package com.vokovalenko16.framework.repo;

import com.vokovalenko16.framework.enums.ValidFlag;
import com.vokovalenko16.framework.po.Role;
import java.util.Optional;

/**
 * Role's repository.
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 10/16/15
 * @since JDK1.8
 */
public interface RoleRepository extends CustomRepository<Role, Long> {

  Optional<Role> findByNameAndValidFlag(String name, ValidFlag validFlag);

}
