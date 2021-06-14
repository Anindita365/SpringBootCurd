package com.spring.data.jpa.repository;

import com.spring.data.jpa.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer,Long> {

    //SELECT * FROM CUSTOMER WHERE NAME =?

    Customer findByName(String name);

    //SELECT * FROM CUSTOMER WHERE NAME =? AND STATUS =?

    List<Customer> findByNameAndStatus(String name,String Status);

    //SELECT * FROM CUSTOMER WHERE NAME IN (?)

    List<Customer> findByStoreIdIn(Long ...storeId);

    //SELECT * FROM CUSTOMER WHERE UPPER(NAME) = UPPER(?)

    List<Customer> findByNameIgnoreCase(String name);


    Page<Customer> findByName(String name, Pageable pageRequest);

    @Override
    Page<Customer> findAll(Pageable pageable);
}
