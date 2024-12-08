package com.klef.sdp.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ADAO {
	@Autowired
	
	AdminRepository arepo;
	public List<Admin> findAdmin(){
		return arepo.findAll();
		}
	
	public Optional<Admin> findUser(int admin_id) {
		return arepo.findById(admin_id);
	}

}
