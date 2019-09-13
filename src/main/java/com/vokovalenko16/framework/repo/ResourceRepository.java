package com.vokovalenko16.framework.repo;

import com.vokovalenko16.framework.enums.ValidFlag;
import com.vokovalenko16.framework.po.Resource;
import java.util.Optional;

/**
 * Resource's repository.
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 10/16/15
 * @since JDK1.8
 */
public interface ResourceRepository extends CustomRepository<Resource, Long> {

  Optional<Resource> findByNameAndValidFlag(String name, ValidFlag validFlag);

}
