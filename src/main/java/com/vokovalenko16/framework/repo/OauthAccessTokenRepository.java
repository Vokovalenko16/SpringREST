package com.vokovalenko16.framework.repo;

import com.vokovalenko16.framework.po.OauthAccessToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for {@link OauthAccessToken}
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 26/10/2016
 * @since JDK1.8
 */
public interface OauthAccessTokenRepository extends JpaRepository<OauthAccessToken, String> {

  Optional<OauthAccessToken> findByUserName(String userName);
}
