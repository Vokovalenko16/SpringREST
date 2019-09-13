package com.vokovalenko16.framework.domain;

import com.vokovalenko16.framework.component.Transformer;
import com.vokovalenko16.framework.param.LogParam;
import com.vokovalenko16.framework.po.Log;
import com.vokovalenko16.framework.po.User;
import com.vokovalenko16.framework.repo.LogRepository;
import com.vokovalenko16.framework.tools.Assert;
import com.vokovalenko16.framework.vo.LogVO;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Domain of {@link Log}
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 10/28/15
 * @since JDK1.8
 */
@Service @Transactional(readOnly = true) public class LogDomain {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------


  @Autowired public LogDomain(Transformer transformer, LogRepository logRepository) {
    Assert.defaultNotNull(transformer);
    Assert.defaultNotNull(logRepository);
    this.transformer = transformer;
    this.logRepository = logRepository;
  }

  @Transactional public LogVO create(LogParam param, User currentUser) throws Exception {
    return transformer.po2VO(LogVO.class, logRepository.save(logParam2PO(param, currentUser)));
  }

  @SuppressWarnings("unchecked")
  public List<LogVO> getAllLogs() throws Exception {
    List<Log> logs = logRepository.findAll();
    if (logs.isEmpty()) {
      return null;
    }
    return transformer.pos2VOs(LogVO.class, logs);
  }

  public Page getPage(Pageable pageable) throws Exception {
    Page<Log> logPage = logRepository.findAll(pageable);
    if (!logPage.hasContent()) {
      return null;
    }
    return transformer.poPage2VO(transformer.pos2VOs(LogVO.class, logPage.getContent()), pageable, logPage.getTotalElements());
  }

  // --------------------------
  // PRIVATE FIELDS AND METHODS
  // --------------------------

  private final Transformer transformer;

  private final LogRepository logRepository;

  private Log logParam2PO(LogParam param, User currentUser) {
    Log log = new Log();
    BeanUtils.copyProperties(param, log);
    log.setUserId(currentUser.getId());
    log.setUsername(currentUser.getUsr());
    return log;
  }
}
