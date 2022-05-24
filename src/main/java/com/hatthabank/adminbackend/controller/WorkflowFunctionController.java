package com.hatthabank.adminbackend.controller;

import com.hatthabank.adminbackend.model.request.WorkflowFunctionPageSearchRequest;
import com.hatthabank.adminbackend.model.request.WorkflowFunctionRequest;
import com.hatthabank.adminbackend.service.impl.WorkflowFunctionServiceImpl;
import com.hatthabank.sdk.web.model.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/{$version:1.0}/workflow-functions/")
public class WorkflowFunctionController {
    @Autowired
    WorkflowFunctionServiceImpl workflowFunctionService;

    @PostMapping("list")
    public APIResponse list(@RequestBody WorkflowFunctionPageSearchRequest request) {
        return APIResponse.success(workflowFunctionService.searchAllWorkflowFunction(request));
    }

    @PostMapping("create")
    public APIResponse create(@RequestBody WorkflowFunctionRequest request) {
        return APIResponse.success(workflowFunctionService.create(request));
    }

    @PutMapping("/{id}/update")
    public APIResponse update(@PathVariable("id") Long id, @RequestBody @Valid WorkflowFunctionRequest request) {
        request.setId(id);
        return APIResponse.success(workflowFunctionService.update(request));
    }

    @DeleteMapping("/{id}/delete")
    public APIResponse delete(@PathVariable("id") Long id) {
        WorkflowFunctionRequest request = WorkflowFunctionRequest.builder().build();
        request.setId(id);
        return APIResponse.success(workflowFunctionService.delete(request));
    }
}

