package com.vokovalenko16.framework.domain;

import com.google.common.collect.Sets;
import com.vokovalenko16.framework.component.LogHelper;
import com.vokovalenko16.framework.component.Transformer;
import com.vokovalenko16.framework.constant.CommonsConstant;
import com.vokovalenko16.framework.enums.ErrorType;
import com.vokovalenko16.framework.enums.ValidFlag;
import com.vokovalenko16.framework.exception.CommonsException;
import com.vokovalenko16.framework.param.UserParam;
import com.vokovalenko16.framework.po.Account;
import com.vokovalenko16.framework.po.Role;
import com.vokovalenko16.framework.po.User;
import com.vokovalenko16.framework.repo.AccountRepository;
import com.vokovalenko16.framework.repo.CustomRepository;
import com.vokovalenko16.framework.repo.UserRepository;
import com.vokovalenko16.framework.tools.Assert;
import com.vokovalenko16.framework.tools.ErrorMsgHelper;
import com.vokovalenko16.framework.vo.UserVO;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Domain of {@link User}
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 7/21/15
 * @since JDK1.8
 */
@Service @Transactional(readOnly = true) public class UserDomain extends BaseDomain<User, Long> {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  public UserDomain(CustomRepository<User, Long> repository, LogHelper logHelper, Transformer transformer, AccountRepository accountRepository, UserRepository userRepository, RoleDomain roleDomain) {
    super(repository, logHelper, transformer);
    Assert.defaultNotNull(accountRepository);
    Assert.defaultNotNull(userRepository);
    Assert.defaultNotNull(roleDomain);
    this.accountRepository = accountRepository;
    this.userRepository = userRepository;
    this.roleDomain = roleDomain;
  }

  @Transactional public UserVO create(UserParam param, User currentUser) throws Exception {
    return po2Vo(createReturnPo(param, currentUser));
  }

  @Transactional public User createReturnPo(UserParam param, User currentUser) throws Exception {
    if (usrUsed(param.getUsr())) {
      // Throw user already exists error, usr taken.
      final String ACCOUNT = "account";
      throw new CommonsException(ErrorType.SYS0111, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0111, ACCOUNT, ACCOUNT));
    }
    User user = param2Po(param, new User(), currentUser);
    Account account = param2Account(param, new Account(), user, currentUser);
    accountRepository.save(account);
    return super.createByPO(user, currentUser);
  }

  public List<UserVO> getAll(Specification<User> specification, Sort sort) {
    return userRepository.findAll(specification, sort).stream().map(
        po -> {
          try {
            return po2Vo(po);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }).collect(Collectors.toList());
  }

  public Page getPage(Specification<User> specification, Pageable pageable) throws Exception {
    return getPage(specification, pageable, UserVO.class);
  }

  public User findById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  public User findByAccount(String usr) {
    Account account = accountRepository.findByAccount(usr).orElse(null);
    return account == null ? null : userRepository.findByIdAndValidFlag(account.getUser().getId(), ValidFlag.VALID).orElse(null);
  }

  public boolean usrUsed(String usr) {
    return accountRepository.findByAccount(usr).isPresent();
  }

  @Transactional public UserVO update(UserParam param, User currentUser) throws Exception {
    return po2Vo(updateReturnPo(param, currentUser));
  }

  @Transactional public User updateReturnPo(UserParam param, User currentUser) throws Exception {
    User user = findById(param.getId());
    if (user == null) {
      throw new CommonsException(ErrorType.SYS0122, ErrorMsgHelper.getReturnMsg(ErrorType.SYS0122, getClassT().getSimpleName(), CommonsConstant.ID));
    }
    if (StringUtils.isNotBlank(param.getUsr())) {
      param.setUsr(null);
    }
    return super.updateByPO(param2Po(param, user, currentUser), currentUser);
  }

  public Account param2Account(UserParam param, Account account, User user, User currentUser) throws Exception {
    account = transformer.param2PO(Account.class, param, account, currentUser);
    account.setAccount(param.getUsr());
    account.setUser(user);
    return account;
  }

  // --------------------------
  // PRIVATE FIELDS AND METHODS
  // --------------------------

  private final AccountRepository accountRepository;

  private final UserRepository userRepository;

  private final RoleDomain roleDomain;

  private User param2Po(UserParam param, User user, User currentUser) throws Exception {
    user = transformer.param2PO(User.class, param, user, currentUser);
    if (StringUtils.isNotBlank(param.getRoleIds())) {
      Set<Role> roles = Sets.newHashSet(roleDomain.getAllByIds(transformer.idsStr2List(param.getRoleIds())));
      if (user.getRoles() != null) {
        roles.addAll(user.getRoles());
      }
      user.setRoles(roles);
    }
    return user;
  }

  public UserVO po2Vo(User user) throws Exception {
    return transformer.po2VO(UserVO.class, user);
  }

}
