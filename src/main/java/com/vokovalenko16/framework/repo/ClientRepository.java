package com.vokovalenko16.framework.repo;

import com.vokovalenko16.framework.enums.ValidFlag;
import com.vokovalenko16.framework.po.Client;
import java.util.Optional;

/**
 * Client's repository.
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 10/23/15
 * @since JDK1.8
 */
public interface ClientRepository extends CustomRepository<Client, Long> {

  Optional<Client> findByClientIdAliasAndValidFlag(String clientIdAlias, ValidFlag validFlag);

}
