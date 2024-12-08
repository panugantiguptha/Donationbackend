package com.klef.sdp.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RDAO {
	@Autowired
	RepRepository repo;
	public void insert(Recipient r1) {
		repo.save(r1);
	}
	public Recipient findUser(String name) {
		return repo.findByName(name);
	}
	public List<Recipient> findRecepients(){
		return repo.findAll();
	}

}
