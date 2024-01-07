package com.tbert31.banking.repositories;

import com.tbert31.banking.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Integer> {

}
