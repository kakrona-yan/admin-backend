package com.hatthabank.adminbackend.controller;

import com.hatthabank.adminbackend.model.request.WorkflowStateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hatthabank.adminbackend.model.request.WorkflowStatePageSearchRequest;
import com.hatthabank.adminbackend.service.impl.WorkflowStateServiceImpl;
import com.hatthabank.sdk.web.model.response.APIResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/{$version:1.0}/workflow-states/")
public class WorkflowStateController {
	@Autowired
    WorkflowStateServiceImpl workflowSateService;

    @PostMapping("list")
    public APIResponse list(@RequestBody WorkflowStatePageSearchRequest request) {
    	 return APIResponse.success(workflowSateService.searchAllWorkflowState(request));
    }

    @PostMapping("create")
    public APIResponse create(@RequestBody WorkflowStateRequest request) {
        return APIResponse.success(workflowSateService.create(request));
    }

    @PutMapping("/{id}/update")
    public APIResponse update(@PathVariable("id") Long id, @RequestBody @Valid WorkflowStateRequest request) {
        request.setId(id);
        return APIResponse.success(workflowSateService.update(request));
    }

    @DeleteMapping("/{id}/delete")
    public APIResponse delete(@PathVariable("id") Long id) {
        WorkflowStateRequest request = WorkflowStateRequest.builder().build();
        request.setId(id);
        return APIResponse.success(workflowSateService.delete(request));
    }
}
