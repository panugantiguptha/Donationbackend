package com.klef.sdp.demo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


@RestController
@CrossOrigin

public class Service {
	@Autowired
	RDAO rdao;
	@Autowired
	 DonorDAO ddao;
	@Autowired
	DFDAO dfrepo;
	@Autowired 
	ADAO adao;
	@Autowired
	DRRepository2 repo;
	
	@GetMapping("/")
	public String initial()
	{
		return "This is Home Page";
	}
	//Recipient Registration
	@PostMapping("/user")
	public String insertUser(@RequestBody Recipient rr) {
		rdao.insert(rr);
		return "Recipient Inserted";
	}
	
	//Donor Registration
	@PostMapping("/donor")
	public String insertUser(@RequestBody Donor dr) {
		ddao.insert(dr);
		return "Donor Inserted";
	}
	
//	//RecipientLogin
//	@PostMapping("/rlogin")
//    public ResponseEntity<?> fun9(@RequestBody Recipient user) {
//        Recipient userFromDb = rdao.findUser(user.getName());
//
//        if (userFromDb == null) {
//            // User not found in the database
//            System.out.println("User not found");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }
//
//        if (userFromDb.getPassword().equals(user.getPassword())) {
//            // Password is correct
//            System.out.println("Password is correct");
//            return ResponseEntity.ok(userFromDb); // Returns user data only if login is successful
//        } else {
//            // Password is incorrect
//            System.out.println("Password is wrong");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }
    
	@PostMapping("/rlogin")
	public ResponseEntity<?> login(@RequestBody Recipient user, HttpSession session) {
	    Recipient userFromDb = rdao.findUser(user.getName());

	    if (userFromDb == null) {
	        System.out.println("User not found");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	    }

	    if (userFromDb.getPassword().equals(user.getPassword())) {
	        System.out.println("Password is correct");

	        // Store user data in session
	        session.setAttribute("recipient", userFromDb.getName());

	        // Log session details
	        System.out.println("Session ID: " + session.getId());
	        System.out.println("User added to session: " + session.getAttribute("recipient"));

	        return ResponseEntity.ok(userFromDb);
	    } else {
	        System.out.println("Password is wrong");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	    }
	}

	@GetMapping("/dashboard")
	public ResponseEntity<?> dashboard(HttpSession session) {
	    // Log session details
	    System.out.println("Session ID: " + session.getId());
	    System.out.println("Session Creation Time: " + session.getCreationTime());
	    System.out.println("Last Accessed Time: " + session.getLastAccessedTime());

	    String recipientName = (String) session.getAttribute("recipient");

	    if (recipientName == null) {
	        System.out.println("Access denied. No user in session.");
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. Please log in.");
	    }

	    System.out.println("Recipient in session: " + recipientName);

	    return ResponseEntity.ok("Welcome to the dashboard, " + recipientName);
	}

	@GetMapping("/rlogout")
	public ResponseEntity<?> logout(HttpSession session) {
	    // Log session invalidation
	    System.out.println("Invalidating session with ID: " + session.getId());
	    session.invalidate();
	    return ResponseEntity.ok("Session invalidated and logged out");
	}
	 //Donor login
	@PostMapping("/dlogin")
    public ResponseEntity<?> fun9(@RequestBody Donor user) {
        Donor userFromDb = ddao.findUser(user.getEmail());

        if (userFromDb == null) {
            // User not found in the database
            System.out.println("User not found");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        if (userFromDb.getPassword().equals(user.getPassword())) {
            // Password is correct
            System.out.println("Password is correct");
            return ResponseEntity.ok(userFromDb); // Returns user data only if login is successful
        } else {
            // Password is incorrect
            System.out.println("Password is wrong");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
	/*
	 @PostMapping("/user")
	public String insertUser(@RequestBody Recipient rr) {
		rdao.insert(rr);
		return "Recipient Inserted";
	}
	 */
//	//DonationRequest data insertion
//	@PostMapping("/donationrequest")
//	public String inserDonation(@RequestBody DonationRequest dr) {
//		drdao.insert(dr);
//		return "Donation Request is Registered ";
//	}
//	
//	@GetMapping("/alldonations")
//	public List<DonationRequest> displayAll(){
//		return drdao.findDonationR();
//	}
	
	//@PostMapping("/donationform")
	/*public String insertDonation(@RequestBody DonationForm df) {
		dfrepo.insert(df);
		return "Doation Form Inserted";
	}*/
	public ResponseEntity<String> createDonation(@RequestBody DonationForm donationForm) {
	    
	    System.out.println(donationForm);

	    // Save donationForm to the database
	    dfrepo.insert(donationForm);
	    return ResponseEntity.ok("Donation successfully submitted!");
	}
	@GetMapping("/allreciepients")
    public ResponseEntity<List<Recipient>> getAllRecipients() {
        List<Recipient> recipients = rdao.findRecepients();
        return ResponseEntity.ok(recipients);
    }
	@GetMapping("/alldonors")
	public List<Donor> getAllDonors(){
		return ddao.findDonors();
	}
	//Admin Login
	@PostMapping("/alogin")
	public ResponseEntity<?> fun9(@RequestBody Admin user) {
	    Optional<Admin> userFromDb = adao.findUser(user.getAdmin_id());

	    if (userFromDb.isEmpty()) { // Check if user is not present
	        // User not found in the database
	        System.out.println("User not found");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	    }

	    Admin admin = userFromDb.get(); // Retrieve the actual Admin object

	    if (admin.getPassword().equals(user.getPassword())) {
	        // Password is correct
	        System.out.println("Password is correct");
	        return ResponseEntity.ok(admin); // Returns user data only if login is successful
	    } else {
	        // Password is incorrect
	        System.out.println("Password is wrong");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	    }
	}
	
	@GetMapping("/alladmins")
	public List<Admin> Admins(){
		return adao.findAdmin();
	}
	
	@PostMapping("/requst-donation")
    public String saveDonationRequest(
    		
            @RequestParam("name") String name,
            @RequestParam("contact") String contact,
            @RequestParam("email") String email,
            @RequestParam("donationType") String donationType,
            @RequestParam("amount") double amount,
            @RequestParam("photo") MultipartFile photo) {
        try {
            DonationRequest2 donation = new DonationRequest2();
            donation.setName(name);
            donation.setContact(contact);
            donation.setEmail(email);
            donation.setDonationType(donationType);
            donation.setAmount(amount);
            donation.setPhoto(photo.getBytes());

            repo.save(donation);
            return "Donation request submitted successfully.";
        } catch (IOException e) {
            return "Failed to submit donation request.";
        }
    }
	@GetMapping("/donations/{id}/photo")
	public ResponseEntity<byte[]> getDonationPhoto(@PathVariable Long id) {
	    Optional<DonationRequest2> donationOpt = repo.findById(id);
	    if (donationOpt.isPresent()) {
	        DonationRequest2 donation = donationOpt.get();
	        byte[] photo = donation.getPhoto();

	        
	        return ResponseEntity.ok()
	                .contentType(MediaType.IMAGE_JPEG) // Adjust content type as per your image format
	                .body(photo);
	    } else {
	        return ResponseEntity.notFound().build(); // Handle case if the donation is not found
	    }
	}
	@GetMapping("/allrequests")
    public List<DonationRequest2> getAllDonationRequests() {
        return repo.findAll();
    }

}
