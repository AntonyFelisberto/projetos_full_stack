package com.antony.helpdesk.repositories;

import com.antony.helpdesk.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    
}
