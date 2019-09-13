package com.vokovalenko16.framework.param;

import com.vokovalenko16.framework.annotation.NotNullField;
import com.vokovalenko16.framework.enums.OperationType;

/**
 * Param bean for {@link com.vokovalenko16.framework.domain.RoleDomain}
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 10/16/15
 * @since JDK1.8
 */
public class RoleParam extends BaseParam {

  @NotNullField(value = {OperationType.UPDATE, OperationType.DELETE}, message = "id cannot be null.")
  private Long id; // role's ID.

  @NotNullField(value = OperationType.CREATE, message = "name cannot be null.")
  private String name; // role's name

  private String description;

  private String resourceIds; // group ids string

  public RoleParam() {}

  public RoleParam(Long id) {
    this.id = id;
  }

  @Override public String toString() {
    final StringBuffer sb = new StringBuffer(super.toString());
    sb.append("RoleParam{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", description='").append(description).append('\'');
    sb.append(", resourceIds='").append(resourceIds).append('\'');
    sb.append('}');
    return sb.toString();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getResourceIds() {
    return resourceIds;
  }

  public void setResourceIds(String resourceIds) {
    this.resourceIds = resourceIds;
  }
}
