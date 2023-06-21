package com.vn.tpbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.tpbank.entity.SchedulePlan;

@Repository
public interface SchedulePlanRepository extends JpaRepository<SchedulePlan, Long> {

}
