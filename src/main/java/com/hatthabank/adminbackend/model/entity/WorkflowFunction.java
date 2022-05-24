package com.hatthabank.adminbackend.model.entity;

import com.hatthabank.adminbackend.model.response.WorkflowFunctionResponse;
import com.hatthabank.sdk.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name="workflow_functions")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowFunction extends BaseEntity {
	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "code", length = 100)
    String code;

    @Column(name = "name", length = 255)
    String name;
    
    @Column(name = "action", length = 255)
    String action;
    
    @Column(name = "endpoint", length = 255)
    String endpoint;

    @Column(name = "input_data")
    @Type(type = "org.hibernate.type.TextType")
    @Lob
    String inputData;

    public static WorkflowFunctionResponse toResponse(WorkflowFunction workflowFunction) {
        WorkflowFunctionResponse response =  WorkflowFunctionResponse.builder()
                .id(workflowFunction.getId())
                .code(workflowFunction.getCode())
                .name(workflowFunction.getName())
                .action(workflowFunction.getAction())
                .endpoint(workflowFunction.getEndpoint())
                .inputData(workflowFunction.getInputData())
                .build();
        response.setStatus(workflowFunction.getStatus());
        response.setCreatedAt(workflowFunction.getCreatedAt());
        response.setCreatedBy(workflowFunction.getCreatedBy());
        response.setModifiedAt(workflowFunction.getModifiedAt());
        response.setModifiedBy(workflowFunction.getModifiedBy());
        return response;
    }
}
