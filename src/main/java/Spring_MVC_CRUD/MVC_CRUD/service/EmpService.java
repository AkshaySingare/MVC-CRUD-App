package Spring_MVC_CRUD.MVC_CRUD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Spring_MVC_CRUD.MVC_CRUD.model.Employee;
import Spring_MVC_CRUD.MVC_CRUD.repository.EmpRepository;

@Service
public class EmpService {
	
	@Autowired
	EmpRepository repo;
	
	public int saveEmp(Employee e) {
		return repo.saveEmployee(e);
	}
	
	public List<Employee> showEmployee(){
		return repo.showEmp();
	}
	public Employee fetchById(int id) {
		return repo.fetchEmp(id);
	}
	public int delete(int id) {
		return repo.deleteEmp(id);
	}
	public int updateEmp(Employee e) {
		return repo.updateEmployee(e);
	}

}
