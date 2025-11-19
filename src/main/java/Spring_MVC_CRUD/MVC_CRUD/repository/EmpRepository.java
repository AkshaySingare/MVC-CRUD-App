package Spring_MVC_CRUD.MVC_CRUD.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

//import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Spring_MVC_CRUD.MVC_CRUD.model.Employee;

@Repository
public class EmpRepository {
	
	@Autowired
	JdbcTemplate jdbc;
	
	public int saveEmployee(Employee e) {
		return jdbc.update("insert into Emp(name,email,contact) values(?,?,?)",new Object[] {e.getName(),e.getEmail(),e.getContact()});
	}
	
	public List<Employee> showEmp(){
		List<Employee> list = jdbc.query("select *from Emp", new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Employee e = new Employee();
				
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setEmail(rs.getString("email"));
				e.setContact(rs.getString("contact"));
				
				return e;
			}
			
		});
		return list;
	}
	
	public Employee fetchEmp(int id) { //fetch By id For Update Employee
		Employee employee= jdbc.queryForObject("select *from Emp where id=?",new Object[] {id},new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Employee e = new Employee();
				
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setEmail(rs.getString("email"));
				e.setContact(rs.getString("contact"));
				return e;
			}			
		});
		return employee;
	}
	public int updateEmployee(Employee e) {
		return jdbc.update("update Emp set name=?,email=?,contact=? where id=?",new Object[] {e.getName(),e.getEmail(),e.getContact(),e.getId()});
	}
	public int deleteEmp(int id) { // delete Employee by id
		return jdbc.update("delete from Emp where id=?",new Object[] {id} );
	}

}
