package com.vokovalenko16.framework.param;

import com.vokovalenko16.framework.annotation.NotNullField;
import com.vokovalenko16.framework.enums.OperationType;

/**
 * Param bean for {@link com.vokovalenko16.framework.domain.ResourceDomain}
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 10/16/15
 * @since JDK1.8
 */
public class ResourceParam extends BaseParam {

  private static final long serialVersionUID = 8542867394907970893L;

  @NotNullField(value = {OperationType.UPDATE, OperationType.DELETE}, message = "id cannot be null.")
  private Long id; // role's ID.

  @NotNullField(value = OperationType.CREATE, message = "name cannot be null.")
  private String name; // role's name

  private String description;

  public ResourceParam() {}

  public ResourceParam(Long id) {
    this.id = id;
  }

  @Override public String toString() {
    final StringBuffer sb = new StringBuffer(super.toString());
    sb.append("ResourceParam{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", description='").append(description).append('\'');
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

}
