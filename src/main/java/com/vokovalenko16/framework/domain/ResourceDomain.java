package com.vokovalenko16.framework.domain;

import com.vokovalenko16.framework.component.LogHelper;
import com.vokovalenko16.framework.component.Transformer;
import com.vokovalenko16.framework.constant.CommonsConstant;
import com.vokovalenko16.framework.enums.ErrorType;
import com.vokovalenko16.framework.enums.OperationType;
import com.vokovalenko16.framework.enums.ValidFlag;
import com.vokovalenko16.framework.exception.CommonsException;
import com.vokovalenko16.framework.param.ResourceParam;
import com.vokovalenko16.framework.po.Resource;
import com.vokovalenko16.framework.po.User;
import com.vokovalenko16.framework.repo.CustomRepository;
import com.vokovalenko16.framework.repo.ResourceRepository;
import com.vokovalenko16.framework.tools.Assert;
import com.vokovalenko16.framework.tools.ErrorMsgHelper;
import com.vokovalenko16.framework.vo.ResourceVO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Domain of {@link Resource}
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 10/17/15
 * @since JDK1.8
 */
@Service @Transactional(readOnly = true) public class ResourceDomain extends BaseDomain<Resource, Long> {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  public ResourceDomain(CustomRepository<Resource, Long> repository, LogHelper logHelper, Transformer transformer, ResourceRepository resourceRepository) {
    super(repository, logHelper, transformer);
    Assert.defaultNotNull(resourceRepository);
    this.resourceRepository = resourceRepository;
  }

  @Transactional public ResourceVO create(ResourceParam param, User currentUser) throws Exception {
    nameExists(param.getName());
    return super.createByPO(ResourceVO.class, resourceParam2PO(param, new Resource(), currentUser), currentUser);
  }

  public List<ResourceVO> all() {
    return resourceRepository.findAll().stream()
        .map(resource -> {
          try {
            return transformer.po2VO(ResourceVO.class, resource);
          } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
          }
        }).collect(Collectors.toList());
  }

  @Transactional public ResourceVO update(ResourceParam param, User currentUser) throws Exception {
    Resource resource = findById(param.getId());
    if (!param.getName().equals(resource.getName())) {
      nameExists(param.getName());
    }
    return super.updateByPO(ResourceVO.class, resourceParam2PO(param, resource, currentUser), currentUser);
  }

  public Resource findById(Long id) {
    return resourceRepository.findById(id).orElse(null);
  }

  @Transactional @Override public void deepDelete(Long id, User currentUser) throws Exception {
    logHelper.logUsersOperations(OperationType.DELETE, getClassT().getName(), currentUser);
    Resource resource = findById(id);
    if (resource == null) {
      throw new CommonsException(ErrorType.SYS0122, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0122, getClassT().getSimpleName(), CommonsConstant.ID));
    }
    resourceRepository.delete(resource);
  }

  // --------------------------
  // PRIVATE FIELDS AND METHODS
  // --------------------------

  private final ResourceRepository resourceRepository;

  private Resource resourceParam2PO(ResourceParam param, Resource resource, User currentUser) throws Exception {
    return transformer.param2PO(getClassT(), param, resource, currentUser);
  }

  private void nameExists(String name) throws Exception {
    if (resourceRepository.findByNameAndValidFlag(name, ValidFlag.VALID).isPresent()) {
      // Throw group already existing exception, name taken.
      throw new CommonsException(ErrorType.SYS0111, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0111, getClassT().getSimpleName(), CommonsConstant.NAME));
    }
  }

}
