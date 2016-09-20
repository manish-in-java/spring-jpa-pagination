package org.example.service;

import com.mysema.query.types.expr.BooleanExpression;
import org.example.data.EmployeeRepository;
import org.example.domain.Employee;
import org.example.domain.QEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Business logic operations for {@link Employee}.
 */
@Service
@Transactional
public class EmployeeService
{
  @Autowired
  private EmployeeRepository repository;

  /**
   * Gets a page of employees matching a .
   *
   * @param code The
   * @return A {@link List} of {@link Employee}s.
   */
  public Page<Employee> page(final String code, final String name, final int page, final int rows)
  {
    final QEmployee root = QEmployee.employee;

    BooleanExpression query = root.isNotNull();
    if (code != null)
    {
      query = query.and(root.code.trim().upper().like("%" + code.trim().toUpperCase() + "%"));
    }

    if (name != null)
    {
      query = query.and(root.name.trim().upper().like("%" + name.trim().toUpperCase() + "%"));
    }

    return repository.findAll(query, new PageRequest(page - 1, rows, new Sort("name")));
  }

  /**
   * Saves an employee.
   *
   * @param employee The employee to save.
   * @return The saved employee.
   */
  @Transactional(propagation = Propagation.REQUIRED)
  public Employee save(final Employee employee)
  {
    return repository.saveAndFlush(employee);
  }
}
