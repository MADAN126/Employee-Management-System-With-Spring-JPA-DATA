package com.jpa.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Parking")
public class Parking {

	@SequenceGenerator(name = "parking_id", sequenceName = "parking_id", initialValue = 100, allocationSize = 10)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parking_id")
	@Column(name = "id")
	private int id;

	public EmployeeDetails getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDetails employee) {
		this.employee = employee;
	}

	@ManyToOne
	@JoinColumn(name = "emp_id")
	private EmployeeDetails employee;
	@Column(name = "iscar")
	private boolean isCar;

	public Parking(boolean isCar, int floorNo, int monthlySub) {
		super();
		this.isCar = isCar;
		this.floorNo = floorNo;
		this.monthlySub = monthlySub;
	}

	@Column(name = "floorno")
	private int floorNo;

	@Column(name = "monthlySub")
	private int monthlySub;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isCar() {
		return isCar;
	}

	public void setCar(boolean isCar) {
		this.isCar = isCar;
	}

	public int getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public int getMonthlySub() {
		return monthlySub;
	}

	public void setMonthlySub(int monthlySub) {
		this.monthlySub = monthlySub;
	}

	@Override
	public String toString() {
		return "Parking [id=" + id + ", isCar=" + isCar + ", floorNo=" + floorNo + ", monthlySub=" + monthlySub + "]";
	}

	public Parking(int id, boolean isCar, int floorNo, int monthlySub) {
		super();
		this.id = id;
		this.isCar = isCar;
		this.floorNo = floorNo;
		this.monthlySub = monthlySub;
	}

	public Parking() {
		super();
		// TODO Auto-generated constructor stub
	}

}
