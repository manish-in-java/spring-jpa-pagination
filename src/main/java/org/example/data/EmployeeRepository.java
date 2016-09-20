package org.example.data;

import org.example.domain.Employee;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Contract for data access operations for {@link Employee}.
 */
public interface EmployeeRepository extends ModelRepository<Employee>, QueryDslPredicateExecutor<Employee>
{
}
