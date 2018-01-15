package com.example.service;

import java.util.HashSet;
import java.util.Set;

import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.model.Employee;



@Controller
@RequestMapping("/employee")
public class EmployeeService {
	static Set Employees;
	static {
		Employees = new HashSet();
		Employee emp =null;
		for(int i=0;i<10;i++) {
			emp = new Employee(i,"Employee"+i);
			Employees.add(emp);
		}
	}
	
	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = {"application/json"})
	@ResponseBody
	public Employee getEmployee(@PathVariable int employeeId) {
		Iterator it = Employees.iterator();
		while(it.hasNext()) {
			Employee emp =  (Employee) it.next();
			if(employeeId == emp.getId()) {
				return emp;
			}
		}
		return null;
	}
	
	@RequestMapping(value="/employeeList", method = RequestMethod.GET, produces = {"application/json"})
	@ResponseBody
	public Set getEmployeeList() {
		System.out.println("get");
		return Employees;
	}

	@RequestMapping(value="/{employeeId}", method = RequestMethod.PUT, headers = "Accept=application/json", produces = {"application/json"}, consumes = {"application/json"})
	@ResponseBody
	public Employee editEmployee(@RequestBody Employee employee, @PathVariable int employeeId) {
		Iterator it = Employees.iterator();
		while(it.hasNext()) {
			Employee emp =  (Employee) it.next();
			if(employeeId == emp.getId()) {
				emp.setName(employee.getName());
				emp.setId(employee.getId());
				return emp;
			}
		}
		return null;
	}
	
	@RequestMapping(value="/{employeeId}", method = RequestMethod.DELETE, headers = "Accept=application/json", produces = {"application/json"})
	@ResponseBody
	public boolean deleteEmployee(@PathVariable int employeeId) {
		Iterator it = Employees.iterator();
		while(it.hasNext()) {
			Employee emp =  (Employee) it.next();
			if(employeeId == emp.getId()) {
				Employees.remove(emp);
				return true;
			}
		}
		return false;
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST, consumes={"application/json"},produces={"application/json"})
	public @ResponseBody boolean addEmployee(@RequestBody Employee employee) {
		System.out.println("sjhfkushrfijsgrhiu");
		return Employees.add(employee);
	}
	
//	@RequestMapping(method = RequestMethod.GET,produces={"application/json"})
//	public @ResponseBody Employee addEmployee1(HttpServletResponse resp) {
//		System.out.println("sjhfkushrfijsgrhiu");
//		resp.addHeader("Content-Type", "application/json");
//		 Employee employee = new Employee("1","2");
//		 System.out.println("sjhfkushrfijsgrhiu1");
//		//return Employees.add(employee);
//		return employee;
//	}
}

