package com.antony.helpdesk.repositories;

import com.antony.helpdesk.model.Technical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalRepository extends JpaRepository<Technical,Integer> {
}
