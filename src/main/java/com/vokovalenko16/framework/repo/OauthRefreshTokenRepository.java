package com.vokovalenko16.framework.repo;

import com.vokovalenko16.framework.po.OauthRefreshToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 26/10/2016
 * @since JDK1.8
 */
public interface OauthRefreshTokenRepository extends JpaRepository<OauthRefreshToken, String> {

  Optional<OauthRefreshToken> findByTokenId(String tokenId);
}
