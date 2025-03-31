package com.labdev.labdev_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.labdev.labdev_spring.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{


 
} 
