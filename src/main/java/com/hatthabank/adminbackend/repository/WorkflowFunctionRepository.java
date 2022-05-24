package com.hatthabank.adminbackend.repository;

import com.hatthabank.adminbackend.model.entity.WorkflowFunction;
import com.hatthabank.sdk.common.enumeration.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkflowFunctionRepository extends JpaRepository<WorkflowFunction, Long>, JpaSpecificationExecutor<WorkflowFunction> {
    Optional<WorkflowFunction> findById(Integer id);
    Optional<WorkflowFunction> findByCodeAndStatus(String code, Status status);
    List<WorkflowFunction> findAllById(Integer id);
    List<WorkflowFunction> findAllByCode(String code);
    List<WorkflowFunction> findAllByStatus(Status status);
    List<WorkflowFunction> findAllByStatusNot(Status status);
}
