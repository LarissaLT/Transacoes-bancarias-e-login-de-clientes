package com.desafio.banco.repository;

import com.desafio.banco.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente,Long> {

    UserDetails findByCpfNumber(String cpfNumber);
}
