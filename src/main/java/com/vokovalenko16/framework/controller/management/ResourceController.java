package com.vokovalenko16.framework.controller.management;

import com.vokovalenko16.framework.annotation.CurrentUser;
import com.vokovalenko16.framework.component.ResultHelper;
import com.vokovalenko16.framework.component.ValidateHelper;
import com.vokovalenko16.framework.constant.CommonsConstant;
import com.vokovalenko16.framework.constant.ResourceURL;
import com.vokovalenko16.framework.constant.VersionConstant;
import com.vokovalenko16.framework.domain.ResourceDomain;
import com.vokovalenko16.framework.enums.ErrorType;
import com.vokovalenko16.framework.enums.OperationType;
import com.vokovalenko16.framework.exception.CommonsException;
import com.vokovalenko16.framework.param.ResourceParam;
import com.vokovalenko16.framework.po.User;
import com.vokovalenko16.framework.tools.Assert;
import com.vokovalenko16.framework.vo.ResourceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Controller of resource.
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 10/17/15
 * @since JDK1.8
 */
@Api("Resource") @RestController @RequestMapping(ResourceURL.RESOURCES + VersionConstant.V1 + ResourceURL.MANAGEMENT + ResourceURL.RESOURCES) public class ResourceController {

  @RequestMapping(method = RequestMethod.POST)
  @ApiOperation(value = "Create", httpMethod = "POST", response = ResourceVO.class)
  @ApiImplicitParam(name = "Authorization", paramType = "header", dataType = "string", required = true)
  public ResponseEntity create(@ApiIgnore @CurrentUser User currentUser, @RequestBody ResourceParam param) {
    try {
      // Validate current user, param and sign.
      ResponseEntity responseEntity = validateHelper.validate(param, currentUser, logger, OperationType.CREATE);
      if (!responseEntity.getStatusCode().is2xxSuccessful()) {
        return responseEntity;
      }
      // Return result and message.
      return new ResponseEntity<>(resourceDomain.create(param, currentUser), HttpStatus.CREATED);
    } catch (CommonsException e) {
      // Return error information and log the exception.
      return resultHelper.infoResp(logger, e.getErrorType(), e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    } catch (Exception e) {
      // Return unknown error and log the exception.
      return resultHelper.errorResp(logger, e, ErrorType.UNKNOWN, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(method = RequestMethod.GET)
  @ApiOperation(value = "List", httpMethod = "GET", response = ResourceVO.class)
  @ApiImplicitParam(name = "Authorization", value = "token", paramType = "header", dataType = "string", required = true)
  public ResponseEntity all() {
    try {
      return new ResponseEntity<>(resourceDomain.all(), HttpStatus.OK);
    } catch (Exception e) {
      // Return unknown error and log the exception.
      return resultHelper.errorResp(logger, e, ErrorType.UNKNOWN, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ApiOperation(value = "Detail", httpMethod = "GET", response = ResourceVO.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "Authorization", paramType = "header", dataType = "string", required = true),
      @ApiImplicitParam(name = "id", paramType = "path", dataType = "string", required = true)
  })
  public ResponseEntity detail(@ApiIgnore @PathVariable String id) {
    try {
      if (StringUtils.isBlank(id)) {
        return resultHelper.infoResp(ErrorType.SYS0002, CommonsConstant.ID_BLANK, HttpStatus.UNPROCESSABLE_ENTITY);
      }
      return new ResponseEntity<>(resourceDomain.getById(Long.valueOf(id), ResourceVO.class), HttpStatus.OK);
    } catch (Exception e) {
      // Return unknown error and log the exception.
      return resultHelper.errorResp(logger, e, ErrorType.UNKNOWN, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ApiOperation(value = "Update", httpMethod = "PUT", response = ResourceVO.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "Authorization", paramType = "header", dataType = "string", required = true),
      @ApiImplicitParam(name = "id", paramType = "path", dataType = "string", required = true)
  })
  public ResponseEntity update(@ApiIgnore @CurrentUser User currentUser, @RequestBody ResourceParam param) {
    try {
      // Validate current user, param and sign.
      ResponseEntity responseEntity = validateHelper.validate(param, currentUser, logger, OperationType.UPDATE);
      if (!responseEntity.getStatusCode().is2xxSuccessful()) {
        return responseEntity;
      }
      // Update resource.
      return new ResponseEntity<>(resourceDomain.update(param, currentUser), HttpStatus.OK);
    } catch (CommonsException e) {
      // Return error information and log the exception.
      return resultHelper.infoResp(logger, e.getErrorType(), e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    } catch (Exception e) {
      // Return unknown error and log the exception.
      return resultHelper.errorResp(logger, e, ErrorType.UNKNOWN, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ApiOperation(value = "Delete", httpMethod = "DELETE", response = ResponseEntity.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "Authorization", paramType = "header", dataType = "string", required = true),
      @ApiImplicitParam(name = "id", paramType = "path", dataType = "string", required = true)
  })
  public ResponseEntity delete(@ApiIgnore @CurrentUser User currentUser, @ApiIgnore @PathVariable Long id) {
    try {
      ResourceParam param = new ResourceParam(id);
      // Validate current user and param.
      ResponseEntity responseEntity = validateHelper.validate(param, currentUser, logger, OperationType.DELETE);
      if (!responseEntity.getStatusCode().is2xxSuccessful()) {
        return responseEntity;
      }
      // Delete resource.
      resourceDomain.deepDelete(param.getId(), currentUser);
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    } catch (CommonsException e) {
      // Return error information and log the exception.
      return resultHelper.infoResp(logger, e.getErrorType(), e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    } catch (Exception e) {
      // Return unknown error and log the exception.
      return resultHelper.errorResp(logger, e, ErrorType.UNKNOWN, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

  private final ResultHelper resultHelper;

  private final ValidateHelper validateHelper;

  private final ResourceDomain resourceDomain;

  @Autowired public ResourceController(ResultHelper resultHelper, ValidateHelper validateHelper, ResourceDomain resourceDomain) {
    Assert.defaultNotNull(resultHelper);
    Assert.defaultNotNull(validateHelper);
    Assert.defaultNotNull(resourceDomain);
    this.resultHelper = resultHelper;
    this.validateHelper = validateHelper;
    this.resourceDomain = resourceDomain;
  }

}
