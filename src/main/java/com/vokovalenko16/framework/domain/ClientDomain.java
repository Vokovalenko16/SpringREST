package com.vokovalenko16.framework.domain;

import com.vokovalenko16.framework.component.LogHelper;
import com.vokovalenko16.framework.component.Transformer;
import com.vokovalenko16.framework.constant.CommonsConstant;
import com.vokovalenko16.framework.enums.ErrorType;
import com.vokovalenko16.framework.enums.ValidFlag;
import com.vokovalenko16.framework.exception.CommonsException;
import com.vokovalenko16.framework.param.ClientParam;
import com.vokovalenko16.framework.po.Client;
import com.vokovalenko16.framework.po.User;
import com.vokovalenko16.framework.repo.ClientRepository;
import com.vokovalenko16.framework.repo.CustomRepository;
import com.vokovalenko16.framework.tools.Assert;
import com.vokovalenko16.framework.tools.ErrorMsgHelper;
import com.vokovalenko16.framework.vo.ClientVO;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Domain of {@link Client}.
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 10/25/15
 * @since JDK1.8
 */
@Service @Transactional(readOnly = true) public class ClientDomain extends BaseDomain<Client, Long> {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------


  @Autowired public ClientDomain(CustomRepository<Client, Long> repository, LogHelper logHelper, Transformer transformer, ClientRepository clientRepository) {
    super(repository, logHelper, transformer);
    Assert.defaultNotNull(clientRepository);
    this.clientRepository = clientRepository;
  }

  @Transactional public ClientVO create(ClientParam param, User currentUser) throws Exception {
    clientIdExists(param.getClientIdAlias());
    return po2Vo(super.createByPO(transformer.param2PO(getClassT(), param, new Client(), currentUser), currentUser));
  }

  public List<ClientVO> all() {
    return clientRepository.findAll().stream().map(this::po2Vo).collect(Collectors.toList());
  }

  public Client findClientByClientId(String clientId) {
    return clientRepository.findByClientIdAliasAndValidFlag(clientId, ValidFlag.VALID).orElse(null);
  }

  public Client findById(Long id) {
    return clientRepository.findById(id).orElse(null);
  }

  @Transactional public ClientVO update(ClientParam param, User currentUser) throws Exception {
    Client client = findById(param.getId());
    if (client == null) {
      throw new CommonsException(ErrorType.SYS0122, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0122, getClassT().getSimpleName(), CommonsConstant.ID));
    }
    if (StringUtils.isNotBlank(param.getClientIdAlias())) {
      param.setClientIdAlias(null);
    }
    return po2Vo(super.updateByPO(transformer.param2PO(getClassT(), param, client, currentUser), currentUser));
  }

  // --------------------------
  // PRIVATE FIELDS AND METHODS
  // --------------------------

  private final ClientRepository clientRepository;

  private final static String CLIENT_ID = "clientId";

  private void clientIdExists(String clientId) throws Exception {
    if (clientRepository.findByClientIdAliasAndValidFlag(clientId, ValidFlag.VALID).isPresent()) {
      // Throw client already existing exception, clientId taken.
      throw new CommonsException(ErrorType.SYS0111, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0111, getClassT().getSimpleName(), CLIENT_ID));
    }
  }

  private ClientVO po2Vo(Client client) {
    if (client == null) {
      return null;
    }
    ClientVO vo = new ClientVO();
    vo.setId(client.getId());
    vo.setClientId(client.getClientIdAlias());
    vo.setClientSecret(client.getClientSecretAlias());
    vo.setResourceIds(transformer.str2Set(client.getResourceIdStr()));
    vo.setScope(transformer.str2Set(client.getScopeStr()));
    vo.setAuthorizedGrantTypes(transformer.str2Set(client.getAuthorizedGrantTypeStr()));
    vo.setRegisteredRedirectUri(transformer.str2Set(client.getRegisteredRedirectUriStr()));
    vo.setGrantedAuthorities(Arrays.stream(client.getAuthoritiesStr().split(CommonsConstant.COMMA))
        .map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    vo.setAccessTokenValiditySeconds(client.getAccessTokenValiditySecondsAlias());
    vo.setRefreshTokenValiditySeconds(client.getRefreshTokenValiditySecondsAlias());
    vo.setPublicKey(client.getPublicKey());
    return vo;
  }

}
