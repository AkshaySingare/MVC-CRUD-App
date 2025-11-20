package Spring_MVC_CRUD.MVC_CRUD.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Spring_MVC_CRUD.MVC_CRUD.model.Employee;
import Spring_MVC_CRUD.MVC_CRUD.service.EmpService;


@Controller
public class HomeController {
	
	@Autowired
	EmpService service;

	@RequestMapping(value="/")  //RootApi Home Page
	public String test(Model model) throws IOException{
		return "home";
	}
	
	@RequestMapping(value="/create",method = RequestMethod.GET) //Show Employee Form 
	public String showForm(Model model) {
		return "SaveEmpForm";
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST) //Save Employee
	public String showFormData(@ModelAttribute Employee e, Model model) {
		
		if(service.saveEmp(e)>0) {
			model.addAttribute("msg", "Employee Saved...");
		}
		return "SaveEmpForm";
	}
	@RequestMapping(value = "/show",method = RequestMethod.GET) //show All Employee from Emp Table
	public String showEMp(Model model) {
		List<Employee> list = service.showEmployee();
		 
			model.addAttribute("employees",list);
			
			  if(list.isEmpty()) {
			        model.addAttribute("msg", "No Employees Found!");
			    }
	
		return "Show";
	}
		
	
	@RequestMapping(value = "/search", method = RequestMethod.GET) // Search Emp 
	public String searchEmp(@RequestParam("name") String name, Model model) {
		
//		System.out.println(name);
		List<Employee> list = service.searchEmployees(name);
	    

	    model.addAttribute("employees", list);

	    if (list.isEmpty()) {
	        model.addAttribute("msg", "No Employees Found!");
	    }

	    return "Show";  // This will load Show.jsp
	}
	
	
	
	@RequestMapping(value = "/update/{id}",method = RequestMethod.GET) //Fetch Employee For Update
	public String fetchforUpdateEmp(@PathVariable("id") int id,Model model) {

		Employee e= service.fetchById(id);
		model.addAttribute("employee", e);
		return "UpdateForm";
	}
	@RequestMapping(value = "/updateEmp",method = RequestMethod.POST) // Update Employee 
	public String updateEmp(@ModelAttribute Employee emp, Model model) {
		service.updateEmp(emp);
		return "redirect:/show";
	}
	
	@RequestMapping(value = "/delete/{id}",method = RequestMethod.GET) // Emp Delete By id
	public String deleteEmp(@PathVariable("id") int id ,Model model) {
		service.delete(id);
		return "redirect:/show";
	}

}
