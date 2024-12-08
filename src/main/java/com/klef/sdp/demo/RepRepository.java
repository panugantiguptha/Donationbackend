package com.klef.sdp.demo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepRepository extends JpaRepository<Recipient, Integer> {
	public Recipient findByName(String name);
}
