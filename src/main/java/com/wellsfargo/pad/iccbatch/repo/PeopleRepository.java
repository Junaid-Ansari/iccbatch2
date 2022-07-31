package com.wellsfargo.pad.iccbatch.repo;

import com.wellsfargo.pad.iccbatch.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

}