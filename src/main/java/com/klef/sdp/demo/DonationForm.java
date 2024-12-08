package com.klef.sdp.demo;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="donations_table")
public class DonationForm {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Auto-generate the ID
    Long id;
	String email;
	String name,donationFor;
	double amount;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDonationFor() {
		return donationFor;
	}
	public void setDonationFor(String donationFor) {
		this.donationFor = donationFor;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
