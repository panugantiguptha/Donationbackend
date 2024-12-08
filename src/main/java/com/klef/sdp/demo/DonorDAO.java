package com.klef.sdp.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DonorDAO {
	@Autowired
	 DonorRepository drepo2;
	
	public void insert(Donor d1) {
		drepo2.save(d1);
	}
	public Donor findUser(String email) {
		return drepo2.findByEmail(email);
	}
	public List<Donor> findDonors(){
		return drepo2.findAll();
	}

}
