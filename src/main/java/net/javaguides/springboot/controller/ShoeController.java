package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Shoe;
import net.javaguides.springboot.repository.ShoeRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class ShoeController {
	
	@Autowired
	private ShoeRepository shoeRepository;
	
	@GetMapping("/shoes")
	@CrossOrigin("*")
	public List<Shoe> getAllShoes(){
		return shoeRepository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/shoes")
	public Shoe createShoe(@RequestBody Shoe shoe) {
		return shoeRepository.save(shoe);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/shoes/{id}")
	public ResponseEntity<Shoe> getShoeById(@PathVariable("id")Long id) {
		Shoe shoe = shoeRepository.findById(id).orElseThrow();
		
		return ResponseEntity.ok(shoe);
	}
	
	@CrossOrigin("*")
	@PutMapping("/shoes/{id}")
	public ResponseEntity<Shoe> updateShoe(@PathVariable("id")Long id, @RequestBody Shoe shoeDetails){
		Shoe shoe = shoeRepository.findById(id).orElseThrow();
		
		shoe.setName(shoeDetails.getName());
		shoe.setSeller(shoeDetails.getSeller());
		shoe.setBuyer(shoeDetails.getBuyer());
		
		Shoe updatedShoe = shoeRepository.save(shoe);
		
		return ResponseEntity.ok(updatedShoe);
	}
	@CrossOrigin("*")
	@DeleteMapping("/shoes/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteShoe(@PathVariable Long id){
		Shoe shoe = shoeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Shoe not found"));
		
		shoeRepository.delete(shoe);
		Map<String, Boolean> shoeDeleted = new HashMap();
		shoeDeleted.put("Deleted", Boolean.TRUE);
		return  ResponseEntity.ok(shoeDeleted);
	}
	
}
