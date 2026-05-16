package com.jpa.main.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Addr")
public class AddrDetails {

	@Override
	public String toString() {
		return "AddrDetails [id=" + id + ", location=" + location + ", city=" + city + ", country=" + country + "]";
	}

	public AddrDetails(String location, String city, String country) {
		super();
		this.location = location;
		this.city = city;
		this.country = country;
	}

	@SequenceGenerator(name = "addr_id", sequenceName = "addr_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addr_id")
	@Id
	private int id;

	private String location;

	private String city;

	private String country;

}
