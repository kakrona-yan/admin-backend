package com.hatthabank.adminbackend.controller;

import com.hatthabank.adminbackend.model.request.WorkflowStateRequest;
import com.hatthabank.adminbackend.model.request.WorkflowTransitionPageSearchRequest;
import com.hatthabank.adminbackend.model.request.WorkflowTransitionRequest;
import com.hatthabank.adminbackend.service.impl.WorkflowTransitionServiceImpl;
import com.hatthabank.sdk.web.model.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/{$version:1.0}/workflow-transitions/")
public class WorkflowTransitionController {
    @Autowired
    WorkflowTransitionServiceImpl workflowTransitionService;

    @PostMapping("list")
    public APIResponse list(@RequestBody WorkflowTransitionPageSearchRequest request) {
        return APIResponse.success(workflowTransitionService.searchAllWorkflowTransition(request));
    }

    @PostMapping("create")
    public APIResponse create(@RequestBody WorkflowTransitionRequest request) {
        return APIResponse.success(workflowTransitionService.create(request));
    }

    @PutMapping("/{id}/update")
    public APIResponse update(@PathVariable("id") Long id, @RequestBody @Valid WorkflowStateRequest request) {
        request.setId(id);
        return APIResponse.success(workflowTransitionService.update(request));
    }

    @DeleteMapping("/{id}/delete")
    public APIResponse delete(@PathVariable("id") Long id) {
        WorkflowStateRequest request = WorkflowStateRequest.builder().build();
        request.setId(id);
        return APIResponse.success(workflowTransitionService.delete(request));
    }
}
