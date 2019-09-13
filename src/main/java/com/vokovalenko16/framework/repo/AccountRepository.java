package com.vokovalenko16.framework.repo;

import com.vokovalenko16.framework.po.Account;
import java.util.Optional;

/**
 * Repository for {@link Account}
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 08/02/2017
 * @since JDK1.8
 */
public interface AccountRepository extends CustomRepository<Account, Long> {

  Optional<Account> findByAccount(String account);
}
