package com.hatthabank.adminbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hatthabank.adminbackend.model.entity.WorkflowState;
import com.hatthabank.sdk.common.enumeration.Status;

@Repository
public interface WorkflowStateRepository extends JpaRepository<WorkflowState, Long>, JpaSpecificationExecutor<WorkflowState>{
	Optional<WorkflowState> findById(Integer id);
	Optional<WorkflowState> findByCodeAndStatus(String code, Status status);
	List<WorkflowState> findAllById(Integer id);
	List<WorkflowState> findAllByCode(String code);
	List<WorkflowState> findAllByStatus(Status status);
	List<WorkflowState> findAllByStatusNot(Status status);
}

