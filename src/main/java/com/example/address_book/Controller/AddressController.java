package com.example.address_book.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.address_book.Service.AddressService;


@RestController
@RequestMapping("/addresses")
public class AddressController {

  @Autowired
  private AddressService service;

  @PostMapping
  public ResponseEntity<Address> create(@RequestBody Address address) {
    return service.addAddress(address);
  }

  @GetMapping
  public ResponseEntity<List<Address>> list() {
    List<Address> addresses = service.getAllAddresses();
    return new ResponseEntity<>(addresses,HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody Address address) {
    return service.updateAddress(id, address) ;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Address> getById(@PathVariable Long id, @RequestBody Address address){
    return service.getAddressById(id) ;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Address> delete(@PathVariable Long id) {
    return service.deleteAddress(id) ;
  }
}