package com.klef.sdp.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	//public Admin findByEmail(String admin_id);

}
