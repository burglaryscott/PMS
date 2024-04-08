package com.pms.repositories;

import com.pms.models.ProjectBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PmsRepo extends JpaRepository<ProjectBean, Long> {
    List<ProjectBean> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    List<ProjectBean> findAllByOrderByStartDateAsc();

    List<ProjectBean> findAllByOrderByStartDateDesc();

    List<ProjectBean> findAllByOrderByEndDateAsc();

    List<ProjectBean> findAllByOrderByEndDateDesc();

}
