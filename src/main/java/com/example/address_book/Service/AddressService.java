package com.example.address_book.Service;

import java.util.*;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.address_book.Model.Address;
import com.example.address_book.Repo.AddressRepo;

@Slf4j
@Service
public class AddressService {
  @Autowired
  public AddressRepo addressRepo ;

  public ResponseEntity<Address> addAddress(@RequestBody Address address){
    try {
      Address savedAddress = addressRepo.save(address);
      log.debug("Address saved successfully: {}", savedAddress.toString());
      return new ResponseEntity<>(savedAddress, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Error occurred while saving address: {}", address, e);
      throw new RuntimeException(e);
    }
  }

  public List<Address> getAllAddresses() {
    log.info("Get HTTP Method called");
    try{
      log.debug("the list will be returned here.");
      return addressRepo.findAll();}
    catch (Exception e) {
      log.error("Error occurred ,exception handled");
      throw new RuntimeException(e);
    }
  }

  public ResponseEntity<Address> getAddressById(Long id) {
    log.info("GET /addresses/{} - Fetching address by ID", id);
    Optional<Address> address = addressRepo.findById(id);

    if (address.isPresent()) {
      log.debug("Address found with ID {}", id);
      return new ResponseEntity<>(address.get(), HttpStatus.OK);
    } else {
      log.warn("No address found with ID {}", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<Address> updateAddress(Long id, Address updated_address){
    log.info("Put /addresses/{} - Updating address by id",id);
    Optional<Address> address = addressRepo.findById(id);

    if(address.isPresent()){

      address.get().setCity(updated_address.getCity());
      address.get().setEmail(updated_address.getEmail());
      address.get().setFullName(updated_address.getFullName());
      address.get().setPhoneNumber(updated_address.getPhoneNumber());
      address.get().setId(updated_address.getId());

      log.debug("Address updated with ID {}", id);

      return new ResponseEntity<>(address.get(),HttpStatus.OK) ;
    }
    log.warn("Attempted to update non-existing address with ID {}", id) ;
    return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
  }

  public ResponseEntity<Address> deleteAddress(Long id) {
    log.info("Delete /addresses/{} - Deleting address by id",id);
    Optional<Address> address = addressRepo.findById(id) ;
    if(address.isPresent()){
      addressRepo.deleteById(id);

      log.debug("Address deleted with ID {}", id);
      return new ResponseEntity<>(HttpStatus.OK) ;
    }
    log.warn("Attempted to delete non-existing address with ID {}", id) ;
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}