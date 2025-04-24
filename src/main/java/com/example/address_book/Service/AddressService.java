package com.example.address_book.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.address_book.Model.Address;
import com.example.address_book.Repo.AddressRepo;

@Service
public class AddressService {
  @Autowired
  public AddressRepo addressRepo ; 

  public Address addAddress(@RequestBody Address address){
    return addressRepo.save(address); 
  }

  public List<Address> getAllAddresses() {
    return addressRepo.findAll();
  }
  
  public void deleteAddress(Long id) {
    addressRepo.deleteById(id);  
  }
}