package org.example.domain;

import com.mysema.query.annotations.QueryEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents an employee.
 */
@Entity
@QueryEntity
@Table(name = "employee")
public class Employee extends Model
{
  @Column(length = 50, name = "code", nullable = false, unique = true)
  @NotNull
  private String code;

  @Column(length = 50, name = "name", nullable = false, unique = true)
  @NotNull
  private String name;

  /**
   * Gets the employee code.
   *
   * @return The employee code.
   */
  public String getCode()
  {
    return code;
  }

  /**
   * Gets the employee name.
   *
   * @return The employee name.
   */
  public String getName()
  {
    return name;
  }

  /**
   * Sets the employee code.
   *
   * @param code The employee code.
   */
  public void setCode(final String code)
  {
    this.code = code;
  }

  /**
   * Sets the employee name.
   *
   * @param name The employee name.
   */
  public void setName(final String name)
  {
    this.name = name;
  }
}
