package com.hatthabank.adminbackend.service;

import com.hatthabank.adminbackend.model.entity.WorkflowFunction;
import com.hatthabank.adminbackend.model.request.BaseEntityRequest;
import com.hatthabank.adminbackend.model.request.WorkflowFunctionPageSearchRequest;
import com.hatthabank.adminbackend.model.request.WorkflowFunctionRequest;
import com.hatthabank.adminbackend.model.response.PageResponse;
import com.hatthabank.adminbackend.model.response.WorkflowFunctionResponse;
import com.hatthabank.adminbackend.service.impl.AbstractEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractWorkflowFunctionService extends AbstractEntityServiceImpl<WorkflowFunction> {
    protected  abstract PageResponse<WorkflowFunction, WorkflowFunctionResponse> searchAllWorkflowFunction(WorkflowFunctionPageSearchRequest request);

    @Override
    protected BaseEntityRequest setUpdateValue(BaseEntityRequest updateEntity, WorkflowFunction existEntity) {
        updateEntity.setId(existEntity.getId());
        if (updateEntity.getStatus() == null) {
            updateEntity.setStatus(existEntity.getStatus());
        }
        return updateEntity;
    }

    @Override
    protected WorkflowFunction mapRequest2Entity(BaseEntityRequest entity) {
        WorkflowFunctionRequest request = (WorkflowFunctionRequest) entity;
        WorkflowFunction workflowFunction = WorkflowFunction.builder()
                .id(request.getId())
                .code(request.getCode())
                .name(request.getName())
                .action(request.getAction())
                .endpoint(request.getEndpoint())
                .inputData(request.getInputData())
                .build();

        workflowFunction.setStatus(entity.getStatus());
        return workflowFunction;
    }
}
