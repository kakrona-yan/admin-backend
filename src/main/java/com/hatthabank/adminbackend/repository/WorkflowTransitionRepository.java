package com.hatthabank.adminbackend.repository;

import com.hatthabank.adminbackend.model.entity.WorkflowState;
import com.hatthabank.adminbackend.model.entity.WorkflowTransition;
import com.hatthabank.sdk.common.enumeration.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkflowTransitionRepository extends JpaRepository<WorkflowTransition, Long>, JpaSpecificationExecutor<WorkflowTransition> {
    Optional<WorkflowTransition> findById(Integer id);
    Optional<WorkflowTransition> findByCodeAndStatus(String code, Status status);
    List<WorkflowTransition> findAllById(Integer id);
    List<WorkflowTransition> findAllByCode(String code);
    List<WorkflowTransition> findAllByStatus(Status status);
    List<WorkflowTransition> findAllByStatusNot(Status status);
}
