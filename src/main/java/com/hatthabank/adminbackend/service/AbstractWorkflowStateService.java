package com.hatthabank.adminbackend.service;

import org.springframework.stereotype.Service;

import com.hatthabank.adminbackend.model.entity.WorkflowState;
import com.hatthabank.adminbackend.model.request.BaseEntityRequest;
import com.hatthabank.adminbackend.model.request.WorkflowStatePageSearchRequest;
import com.hatthabank.adminbackend.model.request.WorkflowStateRequest;
import com.hatthabank.adminbackend.model.response.PageResponse;
import com.hatthabank.adminbackend.model.response.WorkflowStateResponse;
import com.hatthabank.adminbackend.service.impl.AbstractEntityServiceImpl;

@Service
public abstract class AbstractWorkflowStateService extends AbstractEntityServiceImpl<WorkflowState> {
    protected abstract PageResponse<WorkflowState, WorkflowStateResponse> searchAllWorkflowState(WorkflowStatePageSearchRequest request);

	@Override
    protected BaseEntityRequest setUpdateValue(BaseEntityRequest updateEntity, WorkflowState existEntity) {
        updateEntity.setId(existEntity.getId());
        if (updateEntity.getStatus() == null) {
            updateEntity.setStatus(existEntity.getStatus());
        }
        return updateEntity;
    }

	@Override
    protected WorkflowState mapRequest2Entity(BaseEntityRequest entity) {
        WorkflowStateRequest request = (WorkflowStateRequest) entity;
        WorkflowState workflowState = WorkflowState.builder()
                .id(request.getId())
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .build();

        workflowState.setStatus(entity.getStatus());
        return workflowState;
    }

}
