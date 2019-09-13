package com.vokovalenko16.framework.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;

/**
 * VO for {@link com.vokovalenko16.framework.po.Client}
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 10/25/15
 * @since JDK1.8
 */
public class ClientVO implements Serializable {

  private static final long serialVersionUID = -6088897333280284093L;

  private Long id;

  private String clientId;

  private Set<String> resourceIds;

  private String clientSecret;

  private Set<String> scope;

  private Set<String> authorizedGrantTypes;

  private Set<String> registeredRedirectUri;

  private Integer accessTokenValiditySeconds;

  private Integer refreshTokenValiditySeconds;

  private Collection<GrantedAuthority> grantedAuthorities;

  private String publicKey;

  @Override public String toString() {
    final StringBuffer sb = new StringBuffer("ClientVO{");
    sb.append("id=").append(id);
    sb.append(", clientId='").append(clientId).append('\'');
    sb.append(", resourceIds=").append(resourceIds);
    sb.append(", clientSecret='").append(clientSecret).append('\'');
    sb.append(", scope=").append(scope);
    sb.append(", authorizedGrantTypes=").append(authorizedGrantTypes);
    sb.append(", registeredRedirectUri=").append(registeredRedirectUri);
    sb.append(", accessTokenValiditySeconds=").append(accessTokenValiditySeconds);
    sb.append(", refreshTokenValiditySeconds=").append(refreshTokenValiditySeconds);
    sb.append(", publicKey='").append(publicKey).append('\'');
    sb.append('}');
    return sb.toString();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public Set<String> getResourceIds() {
    return resourceIds;
  }

  public void setResourceIds(Set<String> resourceIds) {
    this.resourceIds = resourceIds;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public Set<String> getScope() {
    return scope;
  }

  public void setScope(Set<String> scope) {
    this.scope = scope;
  }

  public Set<String> getAuthorizedGrantTypes() {
    return authorizedGrantTypes;
  }

  public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
    this.authorizedGrantTypes = authorizedGrantTypes;
  }

  public Set<String> getRegisteredRedirectUri() {
    return registeredRedirectUri;
  }

  public void setRegisteredRedirectUri(Set<String> registeredRedirectUri) {
    this.registeredRedirectUri = registeredRedirectUri;
  }

  public Integer getAccessTokenValiditySeconds() {
    return accessTokenValiditySeconds;
  }

  public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
    this.accessTokenValiditySeconds = accessTokenValiditySeconds;
  }

  public Integer getRefreshTokenValiditySeconds() {
    return refreshTokenValiditySeconds;
  }

  public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
    this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
  }

  public Collection<GrantedAuthority> getGrantedAuthorities() {
    return grantedAuthorities;
  }

  public void setGrantedAuthorities(Collection<GrantedAuthority> grantedAuthorities) {
    this.grantedAuthorities = grantedAuthorities;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }
}
