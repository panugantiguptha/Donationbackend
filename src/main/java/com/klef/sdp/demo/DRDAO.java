package com.klef.sdp.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DRDAO {
	@Autowired
	DRRepository2 dr2repo;

	public List<DonationRequest2> getAllDonationRequests() {
        return dr2repo.findAll();
    }

	public Optional<DonationRequest2> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(DonationRequest2 donation) {
		// TODO Auto-generated method stub
		
	}

}
