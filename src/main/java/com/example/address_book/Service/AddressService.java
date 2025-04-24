package com.example.address_book.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.address_book.Model.Address;

@Service
public class AddressService {
  private final List<Address> addresses = new ArrayList<>() ;

  public ResponseEntity<Address> addAddress(@RequestBody Address address){
    addresses.add(address) ; 
    return new ResponseEntity<>(address,HttpStatus.OK) ; 
  }

  public ResponseEntity<List<Address>> getAllAddresses() {
    return new ResponseEntity<>(addresses,HttpStatus.OK); 
  }

  public ResponseEntity<Address> getAddressById(Long id){
     for (Address address : addresses) {
      if(address.getId().equals(id)){
        return new ResponseEntity<>(address , HttpStatus.OK) ; 
      }
     }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND) ; 
  }

  public ResponseEntity<Address> updateAddress(Long id, Address updated_address){
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
  
  public ResponseEntity<Address> deleteAddress(Long id) {   
    for (Address address : addresses) {
      if(address.getId().equals(id)){
        addresses.remove(address); 
        return new ResponseEntity<>(HttpStatus.OK) ; 
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
   }
}