package com.jpa.main.entity;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface Repo extends JpaRepository<EmployeeDetails, Long> {
	
	@Query(value = "SELECT E FROM EmployeeDetails E",nativeQuery = false)
	List<EmployeeDetails> getAllRecords();
	
	@Query(value = "SELECT * FROM emp_details WHERE COUNTRY = :COUNTRY AND AGE < :AGE",nativeQuery = true)
	List<EmployeeDetails> getEmpByCountryAndAge(@Param("COUNTRY")String country,@Param("AGE") int age);
	
	@Query(value="SELECT * FROM emp_details WHERE GENDER =:GENDER AND SALARY > 60000",nativeQuery = true)
	List<EmployeeDetails> getEmpByGenderAndSalCondition(@Param("GENDER") String GENDER);
	
	@Query(value = "SELECT * FROM emp_details WHERE GENDER = ?1 AND AGE = ?2", nativeQuery = true)
	List<EmployeeDetails> getEmpByGenderAndAge(String gender, int age);

	@Query(value = "SELECT * FROM emp_details", nativeQuery = true)
	List<EmployeeDetails> getAllEmployee();
	
	@Query(value = "SELECT * FROM emp_details WHERE Gender = ?1",nativeQuery = true)
	List<EmployeeDetails> getEmployeeByGender(String Gender);

	List<EmployeeDetails> findByCity(String city);

	List<EmployeeDetails> findByGender(String gender);

	List<EmployeeDetails> findBySalary(int i);

	List<EmployeeDetails> findByGenderAndCountry(String gender, String country);

	int deleteByCity(String city);
	
	int deleteByName(String name);

	/*
	 * List<EmployeeDetails> findByCity(String city);
	 * 
	 * List<EmployeeDetails> getBySalary(int salary);
	 * 
	 * List<EmployeeDetails> readByName(String name);
	 * 
	 * long countByCity(String city);
	 * 
	 * boolean existsBySalary(int salary);
	 * 
	 * void deleteByCity(String city);
	 */
}
