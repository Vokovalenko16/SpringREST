package com.vokovalenko16.framework.domain;

import com.google.common.collect.Sets;
import com.vokovalenko16.framework.component.LogHelper;
import com.vokovalenko16.framework.component.Transformer;
import com.vokovalenko16.framework.constant.CommonsConstant;
import com.vokovalenko16.framework.enums.ErrorType;
import com.vokovalenko16.framework.enums.OperationType;
import com.vokovalenko16.framework.enums.ValidFlag;
import com.vokovalenko16.framework.exception.CommonsException;
import com.vokovalenko16.framework.param.RoleParam;
import com.vokovalenko16.framework.po.Resource;
import com.vokovalenko16.framework.po.Role;
import com.vokovalenko16.framework.po.User;
import com.vokovalenko16.framework.repo.CustomRepository;
import com.vokovalenko16.framework.repo.RoleRepository;
import com.vokovalenko16.framework.tools.Assert;
import com.vokovalenko16.framework.tools.ErrorMsgHelper;
import com.vokovalenko16.framework.vo.ResourceVO;
import com.vokovalenko16.framework.vo.RoleVO;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Domain of {@link Role}
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 10/17/15
 * @since JDK1.8
 */
@Service @Transactional(readOnly = true) public class RoleDomain extends BaseDomain<Role, Long> {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------


  @Autowired public RoleDomain(CustomRepository<Role, Long> repository, LogHelper logHelper, Transformer transformer, RoleRepository roleRepository, ResourceDomain resourceDomain) {
    super(repository, logHelper, transformer);
    Assert.defaultNotNull(roleRepository);
    Assert.defaultNotNull(resourceDomain);
    this.roleRepository = roleRepository;
    this.resourceDomain = resourceDomain;
  }

  @Transactional public RoleVO create(RoleParam param, User currentUser) throws Exception {
    nameExists(param.getName());
    return po2Vo(super.createByPO(param2Po(param, new Role(), currentUser), currentUser));
  }

  public List<RoleVO> all() {
    return roleRepository.findAll().stream()
        .map(role -> {
          try {
            return po2Vo(role);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }).collect(Collectors.toList());
  }

  @Transactional public RoleVO update(RoleParam param, User currentUser) throws Exception {
    Role role = findById(param.getId());
    if (role == null) {
      throw new CommonsException(ErrorType.SYS0122, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0122, getClassT().getSimpleName(), CommonsConstant.ID));
    }
    if (StringUtils.isNotBlank(param.getName()) && !param.getName().equals(role.getName())) {
      nameExists(param.getName());
    }
    return po2Vo(super.updateByPO(param2Po(param, role, currentUser), currentUser));
  }

  public RoleVO getById(Long id) throws Exception {
    return po2Vo(roleRepository.findById(id).orElse(null));
  }

  public Role findById(Long id) {
    return roleRepository.findById(id).orElse(null);
  }

  @Transactional @Override public void deepDelete(Long id, User currentUser) throws Exception {
    logHelper.logUsersOperations(OperationType.DELETE, getClassT().getName(), currentUser);
    Role role = findById(id);
    if (role == null) {
      throw new CommonsException(ErrorType.SYS0122, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0122, getClassT().getSimpleName(), CommonsConstant.ID));
    }
    roleRepository.delete(role);
  }

  public RoleVO po2Vo(Role role) throws Exception {
    if (role == null) {
      return null;
    }
    RoleVO vo = transformer.po2VO(RoleVO.class, role);
    vo.setResources(role.getResources().stream()
        .map(resource -> {
          try {
            return transformer.po2VO(ResourceVO.class, resource);
          } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
          }
        }).collect(Collectors.toSet()));
    return vo;
  }

  // --------------------------
  // PRIVATE FIELDS AND METHODS
  // --------------------------

  private final RoleRepository roleRepository;

  private final ResourceDomain resourceDomain;

  private Role param2Po(RoleParam param, Role role, User currentUser) throws Exception {
    transformer.param2PO(getClassT(), param, role, currentUser);
    if (StringUtils.isNotBlank(param.getResourceIds())) {
      Set<Resource> resources = Sets.newHashSet(resourceDomain.getAllByIds(transformer.idsStr2List(param.getResourceIds())));
      if (role.getResources() != null) {
        resources.addAll(role.getResources());
      }
      role.setResources(resources);
    }
    return role;
  }

  private void nameExists(String name) throws Exception {
    if (roleRepository.findByNameAndValidFlag(name, ValidFlag.VALID).isPresent()) {
      // Throw role already existing exception, name taken.
      throw new CommonsException(ErrorType.SYS0111, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0111, getClassT().getSimpleName(), CommonsConstant.NAME));
    }
  }

}
