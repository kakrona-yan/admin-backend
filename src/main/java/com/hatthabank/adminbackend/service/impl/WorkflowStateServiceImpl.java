package com.hatthabank.adminbackend.service.impl;

import com.hatthabank.adminbackend.model.request.WorkflowStateRequest;
import com.hatthabank.sdk.common.enumeration.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.hatthabank.adminbackend.model.entity.WorkflowState;
import com.hatthabank.adminbackend.model.request.BaseEntityRequest;
import com.hatthabank.adminbackend.model.request.WorkflowStatePageSearchRequest;
import com.hatthabank.adminbackend.model.response.BaseEntityResponse;
import com.hatthabank.adminbackend.model.response.PageResponse;
import com.hatthabank.adminbackend.model.response.WorkflowStateResponse;
import com.hatthabank.adminbackend.repository.WorkflowStateRepository;
import com.hatthabank.adminbackend.search.GenericSpecification;
import com.hatthabank.adminbackend.search.SearchCriteria;
import com.hatthabank.adminbackend.service.AbstractWorkflowStateService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkflowStateServiceImpl extends AbstractWorkflowStateService {
	
	@Autowired
	WorkflowStateRepository repo;
	 
	@Value("${page.size:20}")
    private int defaultPageSize;

	@Override
    public Iterable findAll() {
        return repo.findAll();
    }

	@Override
	public Optional<WorkflowState> findById(BaseEntityRequest entity) {
		WorkflowStateRequest request = (WorkflowStateRequest) entity;
		if (entity.getId() == null) {
			return Optional.empty();
		} else {
			return repo.findById(entity.getId());
		}
	}

	@Override
    public PageResponse<WorkflowState, WorkflowStateResponse> searchAllWorkflowState(WorkflowStatePageSearchRequest request) {
		 GenericSpecification genericSpecification = new GenericSpecification<WorkflowState>();
	        request.getParams().forEach(searchRequest -> {
				if ("status".equals(searchRequest.getKey()))
					searchRequest.setValue(Status.valueOf(searchRequest.getValue().toString()));
	            	genericSpecification.add(new SearchCriteria(searchRequest.getKey(), searchRequest.getValue(),searchRequest.getSearchOperation()));
	        });

	        int pageSize = request.getPageSize() == 0 ? defaultPageSize : request.getPageSize();
	        PageRequest pageRequest = PageRequest.of(request.getPageNumber(), pageSize, Sort.by("createdAt").descending());

	        Page<WorkflowState> queryPage = repo.findAll(genericSpecification, pageRequest);
	        return new PageResponse<>(queryPage, WorkflowState::toResponse);
    }

	@Override
	protected BaseEntityResponse createRecord(BaseEntityRequest entity) {
		WorkflowState response = repo.save(mapRequest2Entity(entity));
		return WorkflowStateResponse.builder().id(response.getId()).build();
	}

	@Override
	protected BaseEntityResponse updateRecord(BaseEntityRequest entity) {
		WorkflowState request = mapRequest2Entity(entity);
		repo.save(request);
		return WorkflowStateResponse.builder().id(request.getId()).build();
	}

	@Override
	public void deleteRecord(WorkflowState entity) {
		entity.setStatus(Status.DELETED);
		repo.save(entity);
	}
}

