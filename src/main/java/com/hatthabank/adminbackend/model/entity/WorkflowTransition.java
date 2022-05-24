package com.hatthabank.adminbackend.model.entity;

import com.hatthabank.adminbackend.model.response.WorkflowTransitionResponse;
import com.hatthabank.sdk.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name="workflow_transitions")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowTransition extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "code", length = 100)
    String code;

    @Column(name = "name", length = 255)
    String name;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    @Lob
    String description;

    public static WorkflowTransitionResponse toResponse(WorkflowTransition workflowTransition) {
        WorkflowTransitionResponse response =  WorkflowTransitionResponse.builder()
                .id(workflowTransition.getId())
                .code(workflowTransition.getCode())
                .name(workflowTransition.getName())
                .description(workflowTransition.getDescription())
                .build();
        response.setStatus(workflowTransition.getStatus());
        response.setCreatedAt(workflowTransition.getCreatedAt());
        response.setCreatedBy(workflowTransition.getCreatedBy());
        response.setModifiedAt(workflowTransition.getModifiedAt());
        response.setModifiedBy(workflowTransition.getModifiedBy());
        return response;
    }
}
