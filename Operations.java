package com.jpa.main.entity;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.ScrollPosition.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;

@Component
public class Operations {

	@Autowired
	DeptRepo deptnoRepo;

	@Autowired
	Repo repo;

	@Autowired
	ParkingRepo parkingRepo;

//EmployeeDetails(long employeeId, String name, int age, float salary, String city, String gender,
//String country)
	public void addMoreEmployees() {
		List<EmployeeDetails> employees = new ArrayList<>();
		employees.add(new EmployeeDetails(1, "James Smith", 30, 55000.0f, "New York", "Male", "USA"));
		employees.add(new EmployeeDetails(2, "Mary Johnson", 27, 48000.0f, "Los Angeles", "Female", "USA"));
		employees.add(new EmployeeDetails(3, "Robert Williams", 35, 62000.0f, "Chicago", "Male", "USA"));
		employees.add(new EmployeeDetails(4, "Patricia Brown", 29, 51000.0f, "Houston", "Female", "USA"));
		employees.add(new EmployeeDetails(5, "John Jones", 32, 58000.0f, "Phoenix", "Male", "USA"));
		employees.add(new EmployeeDetails(6, "Jennifer Garcia", 26, 47000.0f, "Philadelphia", "Female", "USA"));
		employees.add(new EmployeeDetails(7, "Michael Miller", 34, 60000.0f, "San Antonio", "Male", "USA"));
		employees.add(new EmployeeDetails(8, "Linda Davis", 28, 49000.0f, "San Diego", "Female", "USA"));
		employees.add(new EmployeeDetails(9, "William Rodriguez", 36, 65000.0f, "Dallas", "Male", "USA"));
		employees.add(new EmployeeDetails(10, "Elizabeth Martinez", 31, 54000.0f, "San Jose", "Female", "USA"));
		employees.add(new EmployeeDetails(11, "David Hernandez", 33, 57000.0f, "Austin", "Male", "USA"));
		employees.add(new EmployeeDetails(12, "Barbara Lopez", 25, 46000.0f, "Jacksonville", "Female", "USA"));
		employees.add(new EmployeeDetails(13, "Richard Gonzalez", 37, 67000.0f, "Fort Worth", "Male", "USA"));
		employees.add(new EmployeeDetails(14, "Susan Wilson", 29, 52000.0f, "Columbus", "Female", "USA"));
		employees.add(new EmployeeDetails(15, "Joseph Anderson", 38, 69000.0f, "Charlotte", "Male", "USA"));
		employees.add(new EmployeeDetails(16, "Jessica Thomas", 27, 48000.0f, "San Francisco", "Female", "USA"));
		employees.add(new EmployeeDetails(17, "Thomas Taylor", 35, 61000.0f, "Indianapolis", "Male", "USA"));
		employees.add(new EmployeeDetails(18, "Sarah Moore", 26, 47000.0f, "Seattle", "Female", "USA"));
		employees.add(new EmployeeDetails(19, "Charles Jackson", 40, 72000.0f, "Los vagas", "Male", "USA"));
		employees.add(new EmployeeDetails(20, "Karen Martin", 30, 53000.0f, "Washington", "Female", "USA"));
		repo.saveAll(employees);
		System.out.println("INSERTED SUCCESSFULLY");
	}

	public void allEmployee() {
		List<EmployeeDetails> empList = (List<EmployeeDetails>) repo.findAll();
		System.out.println(empList);
	}

	public void searchEmployee() {
		Optional<EmployeeDetails> empObj = repo.findById(1L);

		EmployeeDetails emp = null;
		if (empObj.isPresent())
			emp = empObj.get();
		else {
			System.out.println("No row selected");

		}

		System.out.println(emp);
	}

	public void getSalary() {
		List<EmployeeDetails> list = repo.findBySalary(53000);
		ListIterator<EmployeeDetails> i = list.listIterator();
		while (i.hasNext()) {
			EmployeeDetails emp = i.next();
			System.out.println(emp);
		}
	}

	public void getGender() {
		List<EmployeeDetails> list = repo.findByCity("Male");
		ListIterator<EmployeeDetails> i = list.listIterator();
		while (i.hasNext()) {
			EmployeeDetails emp = i.next();
			System.out.println(emp);
		}
	}

	public void getCity() {
		List<EmployeeDetails> list = repo.findByCity("New York");
		ListIterator<EmployeeDetails> i = list.listIterator();
		while (i.hasNext()) {
			EmployeeDetails emp = i.next();
			System.out.println(emp);
		}
	}

	public void getGenderAndCountry() {
		List<EmployeeDetails> list = repo.findByGenderAndCountry("Male", "USA");
		ListIterator<EmployeeDetails> i = list.listIterator();
		while (i.hasNext()) {
			EmployeeDetails emp = i.next();
			System.out.println(emp);
		}
	}

	@Modifying
	@Transactional
	public void deleteEmpinfo() {
		repo.deleteById(0L);
	}

	@Transactional
	public void deleteByCity() {
		int count = repo.deleteByCity("Chicago");
		System.out.println(count + " rows are deleted");
	}
	
	@Modifying
	@Transactional
	public void deleteByName() {
		repo.deleteByName("Madhu");
	}
	

	public void updateById(Long i) {
		Optional<EmployeeDetails> emp = repo.findById(i);
		if (emp.isPresent()) {
			EmployeeDetails e1 = emp.get();
			e1.setName("Gopal");
			e1.setAge(21);
			e1.setCountry("USA");
			e1.setGender("male");
			e1.setSalary(100000);
			repo.save(e1);
		} else {
			System.out.println("Not present");
		}
	}

	public void sortBySalary() {
		List<EmployeeDetails> list = repo.findAll(Sort.by("salary"));
		ListIterator<EmployeeDetails> i = list.listIterator();
		while (i.hasNext()) {
			EmployeeDetails emp = i.next();
			System.out.println(emp);
		}
	}

	public void sortBySalaryDesc() {
		List<EmployeeDetails> list = repo.findAll(Sort.by(Sort.Direction.DESC, "salary"));
		ListIterator<EmployeeDetails> i = list.listIterator();
		while (i.hasNext()) {
			EmployeeDetails emp = i.next();
			System.out.println(emp);
		}
	}

	public void doPagination() {
		List<EmployeeDetails> list = repo
				.findAll(PageRequest.of(5, 3, Sort.by("city").descending().and(Sort.by("salary").ascending())))
				.getContent();
		ListIterator<EmployeeDetails> i = list.listIterator();
		while (i.hasNext()) {
			EmployeeDetails emp = i.next();
			System.out.println(emp);
		}
	}

	public void getAllEmployee() {
		List<EmployeeDetails> list = repo.getAllEmployee();
		ListIterator<EmployeeDetails> i = list.listIterator();
		while (i.hasNext()) {
			EmployeeDetails emp = i.next();
			System.out.println(emp);
		}

	}

	public void getEmpByGender() {
		List<EmployeeDetails> list = repo.getEmployeeByGender("Male");
		ListIterator<EmployeeDetails> i = list.listIterator();
		while (i.hasNext()) {
			EmployeeDetails emp = i.next();
			System.out.println(emp);
		}
	}

	public void getEmpByGenderAge() {
		List<EmployeeDetails> list = repo.getEmpByGenderAndAge("male",21);
		ListIterator<EmployeeDetails> i = list.listIterator();
		while (i.hasNext()) {
			EmployeeDetails emp = i.next();
			System.out.println(emp);
		}
	}

	public void getEmpBySalConditionAndGender() {
		List<EmployeeDetails> list = repo.getEmpByGenderAndSalCondition("Male");
		ListIterator<EmployeeDetails> i = list.listIterator();
		while (i.hasNext()) {
			EmployeeDetails emp = i.next();
			System.out.println(emp);
		}
	}

	public void getEmpByCountryAndAge() {
		List<EmployeeDetails> list = repo.getEmpByCountryAndAge("USA", 35);
		ListIterator<EmployeeDetails> i = list.listIterator();
		while (i.hasNext()) {
			EmployeeDetails emp = i.next();
			System.out.println(emp);
		}
	}

	public void getAllRecords() {
		List<EmployeeDetails> list = repo.getAllRecords();
		ListIterator<EmployeeDetails> i = list.listIterator();
		while (i.hasNext()) {
			EmployeeDetails emp = i.next();
			System.out.println(emp);
		}
	}

	public void AddEmployeeWithAddr() {
		AddrDetails a1 = new AddrDetails("Madhavaram", "Chennai", "India");
		EmployeeDetails e1 = new EmployeeDetails(21, "Madan", 21, 100000, "Chennai", "Male", "India");
		e1.setAddr(a1);
		repo.save(e1);
	}

	public void FindByID() {
		Optional<EmployeeDetails> op = repo.findById(21L);
		if (op.isPresent()) {
			EmployeeDetails list = op.get();
			System.out.println(list);
		} else {
			System.out.println("No records");
		}
	}

	public void AddUserWithMultipleParking() {
		EmployeeDetails emp1 = new EmployeeDetails();
		emp1.setAge(20);
		emp1.setCity("Chennai");
		emp1.setSalary(20000);
		emp1.setCountry("India");
		emp1.setName("sneha");
		emp1.setGender("Female");
		emp1.setEmployeeId(22);

		AddrDetails a1 = new AddrDetails("Kolathur", "Chennai", "India");

		Parking p1 = new Parking(true, 2, 1000);
		Parking p2 = new Parking(true, 1, 1000);
		Parking p3 = new Parking(true, 10, 1000);
		
		p1.setEmployee(emp1);
		p2.setEmployee(emp1);
		p3.setEmployee(emp1);

		emp1.setParking(List.of(p1, p2, p3));
		Parking p4 = new Parking(100, true, 3, 1000);
		Parking p5 = new Parking(101, true, 10, 1000);
		Parking p6 = new Parking(102, true, 20, 1000);
		
	

		EmployeeDetails emp2 = new EmployeeDetails();
		emp2.setAge(20);
		emp2.setCity("Chennai");
		emp2.setSalary(30000);
		emp2.setCountry("India");
		emp2.setName("Kishore");
		emp2.setGender("Male");
		emp2.setEmployeeId(23);
		
		p4.setEmployee(emp2);
		p5.setEmployee(emp2);
		p6.setEmployee(emp2);

		emp2.setParking(List.of(p4, p5, p6));

		repo.save(emp1);
		repo.save(emp2);
	}

	public void dropParkingId() {
		parkingRepo.deleteById(112);
	}

	public void addEmployeeWithFullDetail() {
		Department d1 = new Department();
		d1.setDeptId(1);
		d1.setDeptName("HR");
		
		deptnoRepo.save(d1);
		
		Parking p1 = new Parking(true, 10, 1000);
		Parking p2 = new Parking(true, 12, 1000);
		Parking p3 = new Parking(true, 13, 1000);


		AddrDetails a1 = new AddrDetails("RkNagar", "Chennai", "India");
		EmployeeDetails emp1 = new EmployeeDetails();
		emp1.setAge(24);
		emp1.setEmployeeId(24);
		emp1.setCity("Chennai");
		emp1.setName("Madhu");
		emp1.setSalary(30000);
		emp1.setCountry("India");
		emp1.setGender("Female");

		emp1.setParking(List.of(p1, p2, p3));
		emp1.setAddr(a1);

		emp1.setDepartment(d1);
		repo.save(emp1);
		
		
		
		Department d2 = new Department();
		d1.setDeptId(2);
		d1.setDeptName("MANAGER");
		
		deptnoRepo.save(d1);
		
		Parking p4 = new Parking(true, 19, 1000);
		Parking p5 = new Parking(true, 21, 1000);
		Parking p6 = new Parking(true, 31, 1000);


		AddrDetails a2 = new AddrDetails("KkNagar", "Chennai", "India");
		EmployeeDetails emp2 = new EmployeeDetails();
		emp1.setAge(21);
		emp1.setEmployeeId(25);
		emp1.setCity("Chennai");
		emp1.setName("Vijay");
		emp1.setSalary(20000);
		emp1.setCountry("India");
		emp1.setGender("Male");

		emp1.setParking(List.of(p1, p2, p3));
		emp1.setAddr(a1);

		emp1.setDepartment(d1);
		repo.save(emp1);
		

	}

}
