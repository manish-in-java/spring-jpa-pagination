package org.example.web;

import org.example.domain.Employee;
import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Home page controller.
 */
@Controller
@RequestMapping("/")
public class HomeController
{
  @Autowired
  private EmployeeService service;

  /**
   * Gets a search page of employees.
   */
  @RequestMapping("page")
  public MappingJackson2JsonView page(@RequestParam(defaultValue = "0") final int page
      , @RequestParam(defaultValue = "100") final int rows
      , @RequestParam(required = false) final String code
      , @RequestParam(required = false) final String name
      , final Model model)
  {
    final Page<Employee> employees = service.page(code, name, page, rows);

    model.addAttribute("page", page);
    model.addAttribute("pages", employees.getTotalPages());
    model.addAttribute("rows", employees.getContent());
    model.addAttribute("total", employees.getTotalElements());

    return new MappingJackson2JsonView();
  }

  /**
   * Saves an employee and displays the home page.
   */
  @RequestMapping(method = RequestMethod.POST)
  public String save(final Employee employee, final Model model)
  {
    service.save(employee);

    return show();
  }

  /**
   * Displays the home page.
   */
  @RequestMapping(method = RequestMethod.GET)
  public String show()
  {
    return "home";
  }
}
