package com.antony.helpdesk.repositories;

import com.antony.helpdesk.model.Call;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRepository extends JpaRepository<Call,Integer> {

}
