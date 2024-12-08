package com.klef.sdp.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DFDAO {
	@Autowired
	DFRepository dfrepo;
	
	public void insert(DonationForm dr1) {
		dfrepo.save(dr1);
	}

	
}
