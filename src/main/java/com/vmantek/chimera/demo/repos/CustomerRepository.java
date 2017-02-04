package com.vmantek.chimera.demo.repos;

import com.vmantek.chimera.demo.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>
{
    Page<Customer> findByName(@Param("name") String name, Pageable p);
}
