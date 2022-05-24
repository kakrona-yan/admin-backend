package com.hatthabank.adminbackend.service.impl;

import com.hatthabank.adminbackend.model.entity.WorkflowFunction;
import com.hatthabank.adminbackend.model.request.*;
import com.hatthabank.adminbackend.model.response.BaseEntityResponse;
import com.hatthabank.adminbackend.model.response.PageResponse;
import com.hatthabank.adminbackend.model.response.WorkflowFunctionResponse;
import com.hatthabank.adminbackend.repository.WorkflowFunctionRepository;
import com.hatthabank.adminbackend.search.GenericSpecification;
import com.hatthabank.adminbackend.search.SearchCriteria;
import com.hatthabank.adminbackend.service.AbstractWorkflowFunctionService;
import com.hatthabank.sdk.common.enumeration.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkflowFunctionServiceImpl extends AbstractWorkflowFunctionService {
    @Autowired
    WorkflowFunctionRepository repo;

    @Value("${page.size:20}")
    private int defaultPageSize;

    @Override
    public Iterable findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<WorkflowFunction> findById(BaseEntityRequest entity) {
        WorkflowFunctionRequest request = (WorkflowFunctionRequest) entity;
        if (entity.getId() == null) {
            return Optional.empty();
        } else {
            return repo.findById(entity.getId());
        }
    }

    @Override
    public PageResponse<WorkflowFunction, WorkflowFunctionResponse> searchAllWorkflowFunction(WorkflowFunctionPageSearchRequest request) {
        GenericSpecification genericSpecification = new GenericSpecification<WorkflowFunction>();
        request.getParams().forEach(searchRequest -> {
            if ("status".equals(searchRequest.getKey()))
                searchRequest.setValue(Status.valueOf(searchRequest.getValue().toString()));
            genericSpecification.add(new SearchCriteria(searchRequest.getKey(), searchRequest.getValue(),searchRequest.getSearchOperation()));
        });

        int pageSize = request.getPageSize() == 0 ? defaultPageSize : request.getPageSize();
        PageRequest pageRequest = PageRequest.of(request.getPageNumber(), pageSize, Sort.by("createdAt").descending());

        Page<WorkflowFunction> queryPage = repo.findAll(genericSpecification, pageRequest);
        return new PageResponse<>(queryPage, WorkflowFunction::toResponse);
    }

    @Override
    protected BaseEntityResponse createRecord(BaseEntityRequest entity) {
        WorkflowFunction response = repo.save(mapRequest2Entity(entity));
        return WorkflowFunctionResponse.builder().id(response.getId()).build();
    }

    @Override
    protected BaseEntityResponse updateRecord(BaseEntityRequest entity) {
        WorkflowFunction request = mapRequest2Entity(entity);
        repo.save(request);
        return WorkflowFunctionResponse.builder().id(request.getId()).build();
    }

    @Override
    public void deleteRecord(WorkflowFunction entity) {
        entity.setStatus(Status.DELETED);
        repo.save(entity);
    }
}
