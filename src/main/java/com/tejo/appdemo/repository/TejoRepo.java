package com.tejo.appdemo.repository;

import com.tejo.appdemo.entity.TejoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TejoRepo extends JpaRepository<TejoEntity, Integer> {
}
