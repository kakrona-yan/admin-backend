package com.hatthabank.adminbackend.service;

import com.hatthabank.adminbackend.model.entity.WorkflowTransition;
import com.hatthabank.adminbackend.model.request.BaseEntityRequest;
import com.hatthabank.adminbackend.model.request.WorkflowTransitionPageSearchRequest;
import com.hatthabank.adminbackend.model.request.WorkflowTransitionRequest;
import com.hatthabank.adminbackend.model.response.PageResponse;
import com.hatthabank.adminbackend.model.response.WorkflowTransitionResponse;
import com.hatthabank.adminbackend.service.impl.AbstractEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractWorkflowTransitionService extends AbstractEntityServiceImpl<WorkflowTransition> {
    protected abstract PageResponse<WorkflowTransition, WorkflowTransitionResponse> searchAllWorkflowTransition(WorkflowTransitionPageSearchRequest request);

    @Override
    protected BaseEntityRequest setUpdateValue(BaseEntityRequest updateEntity, WorkflowTransition existEntity) {
        updateEntity.setId(existEntity.getId());
        if (updateEntity.getStatus() == null) {
            updateEntity.setStatus(existEntity.getStatus());
        }
        return updateEntity;
    }

    @Override
    protected WorkflowTransition mapRequest2Entity(BaseEntityRequest entity) {
        WorkflowTransitionRequest request = (WorkflowTransitionRequest) entity;
        WorkflowTransition workflowTransition = WorkflowTransition.builder()
                .id(request.getId())
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .build();

        workflowTransition.setStatus(entity.getStatus());
        return workflowTransition;
    }

}
