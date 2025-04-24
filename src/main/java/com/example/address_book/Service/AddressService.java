package com.example.address_book.Service;

import java.util.*;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.address_book.Model.Address;
import com.example.address_book.Repo.AddressRepo;

@Service
public class AddressService {
  @Autowired
  public AddressRepo addressRepo ; 

  public ResponseEntity<Address> addAddress(@RequestBody Address address){
    addressRepo.save(address); 
    return new ResponseEntity<>(address,HttpStatus.OK) ; 
  }

  public List<Address> getAllAddresses() {
    return addressRepo.findAll();
  }

  public ResponseEntity<Address> getAddressById(Long id){
    Optional<Address> address = addressRepo.findById(id) ; 

    if(address.isPresent()){
      return new ResponseEntity<>(address.get() , HttpStatus.OK) ; 
    }return new ResponseEntity<>(HttpStatus.NOT_FOUND) ; 
  }

  public ResponseEntity<Address> updateAddress(Long id, Address updated_address){
    Optional<Address> address = addressRepo.findById(id); 
    
    if(address.isPresent()){
      address.get().setCity(updated_address.getCity());
      address.get().setEmail(updated_address.getEmail());
      address.get().setFullName(updated_address.getFullName());
      address.get().setPhoneNumber(updated_address.getPhoneNumber());
      address.get().setId(updated_address.getId());

      return new ResponseEntity<>(address.get(),HttpStatus.OK) ; 
    }return new ResponseEntity<>(HttpStatus.NOT_FOUND) ; 
  }
  
  public ResponseEntity<Address> deleteAddress(Long id) { 
    List<Address> data = addressRepo.findAll() ; 
    Address address = addressRepo.findById(id).get() ; 
    if(data.contains(address)){
      addressRepo.deleteById(id); 
      return new ResponseEntity<>(HttpStatus.OK) ; 
    }return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
   }
}