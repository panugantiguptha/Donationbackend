package com.klef.sdp.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, Integer>{
	public Donor findByEmail(String email);
}
