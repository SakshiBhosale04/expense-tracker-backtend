package com.financetracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financetracker.entity.Dashboard;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Long> {

}
