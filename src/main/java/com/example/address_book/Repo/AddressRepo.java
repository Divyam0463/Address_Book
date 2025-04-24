package com.example.address_book.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.address_book.Model.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{
}