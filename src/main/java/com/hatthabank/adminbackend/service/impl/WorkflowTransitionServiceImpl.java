package com.hatthabank.adminbackend.service.impl;

import com.hatthabank.adminbackend.model.entity.WorkflowTransition;
import com.hatthabank.adminbackend.model.request.*;
import com.hatthabank.adminbackend.model.response.WorkflowTransitionResponse;
import com.hatthabank.adminbackend.repository.WorkflowTransitionRepository;
import com.hatthabank.adminbackend.service.AbstractWorkflowTransitionService;
import com.hatthabank.sdk.common.enumeration.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.hatthabank.adminbackend.model.response.BaseEntityResponse;
import com.hatthabank.adminbackend.model.response.PageResponse;
import com.hatthabank.adminbackend.search.GenericSpecification;
import com.hatthabank.adminbackend.search.SearchCriteria;;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkflowTransitionServiceImpl extends AbstractWorkflowTransitionService {
    @Autowired
    WorkflowTransitionRepository repo;

    @Value("${page.size:20}")
    private int defaultPageSize;

    @Override
    public Iterable findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<WorkflowTransition> findById(BaseEntityRequest entity) {
        WorkflowTransitionRequest request = (WorkflowTransitionRequest) entity;
        if (entity.getId() == null) {
            return Optional.empty();
        } else {
            return repo.findById(entity.getId());
        }
    }

    @Override
    public PageResponse<WorkflowTransition, WorkflowTransitionResponse> searchAllWorkflowTransition(WorkflowTransitionPageSearchRequest request) {
        GenericSpecification genericSpecification = new GenericSpecification<WorkflowTransition>();
        request.getParams().forEach(searchRequest -> {
            if ("status".equals(searchRequest.getKey()))
                searchRequest.setValue(Status.valueOf(searchRequest.getValue().toString()));
            genericSpecification.add(new SearchCriteria(searchRequest.getKey(), searchRequest.getValue(),searchRequest.getSearchOperation()));
        });

        int pageSize = request.getPageSize() == 0 ? defaultPageSize : request.getPageSize();
        PageRequest pageRequest = PageRequest.of(request.getPageNumber(), pageSize, Sort.by("createdAt").descending());

        Page<WorkflowTransition> queryPage = repo.findAll(genericSpecification, pageRequest);
        return new PageResponse<>(queryPage, WorkflowTransition::toResponse);
    }

    @Override
    protected BaseEntityResponse createRecord(BaseEntityRequest entity) {
        WorkflowTransition response = repo.save(mapRequest2Entity(entity));
        return WorkflowTransitionResponse.builder().id(response.getId()).build();
    }

    @Override
    protected BaseEntityResponse updateRecord(BaseEntityRequest entity) {
        WorkflowTransition request = mapRequest2Entity(entity);
        repo.save(request);
        return WorkflowTransitionResponse.builder().id(request.getId()).build();
    }

    @Override
    public void deleteRecord(WorkflowTransition entity) {
        entity.setStatus(Status.DELETED);
        repo.save(entity);
    }
}
