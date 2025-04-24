package com.example.address_book.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.address_book.Model.Address;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final List<Address> addresses = new ArrayList<>() ;

   @PostMapping
  public ResponseEntity<Address> addAddress(@RequestBody Address address){
    addresses.add(address) ; 
    return new ResponseEntity<>(address,HttpStatus.OK) ; 
  }

  @GetMapping
  public ResponseEntity<List<Address>> getAllAddresses() {
    return new ResponseEntity<>(addresses,HttpStatus.OK); 
  }

  @GetMapping("/{id}")
  public ResponseEntity<Address> getAddressById(@PathVariable Long id){
     for (Address address : addresses) {
      if(address.getId().equals(id)){
        return new ResponseEntity<>(address , HttpStatus.OK) ; 
      }
     }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND) ; 
  }

  @PutMapping
  public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address updated_address){
    for (Address address : addresses) {
      if(address.getId().equals(id)){
        address.setCity(updated_address.getCity());
        address.setEmail(updated_address.getEmail());
        address.setFullName(updated_address.getFullName());
        address.setPhoneNumber(updated_address.getPhoneNumber());
        address.setId(updated_address.getId());
  
        return new ResponseEntity<>(address,HttpStatus.OK) ; 
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND) ; 
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Address> deleteAddress(@PathVariable Long id) {   
    for (Address address : addresses) {
      if(address.getId().equals(id)){
        addresses.remove(address); 
        return new ResponseEntity<>(HttpStatus.OK) ; 
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
   }
}
