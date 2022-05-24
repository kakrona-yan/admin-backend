package com.hatthabank.adminbackend.model.entity;

import com.hatthabank.adminbackend.model.response.WorkflowStateResponse;
import com.hatthabank.sdk.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name="workflow_states")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowState extends BaseEntity {
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
    
    public static WorkflowStateResponse toResponse(WorkflowState workflowState) {
    	WorkflowStateResponse response =  WorkflowStateResponse.builder()
				.id(workflowState.getId())
				.code(workflowState.getCode())
				.name(workflowState.getName())
				.description(workflowState.getDescription())
				.build();
				response.setStatus(workflowState.getStatus());
				response.setCreatedAt(workflowState.getCreatedAt());
				response.setCreatedBy(workflowState.getCreatedBy());
				response.setModifiedAt(workflowState.getModifiedAt());
				response.setModifiedBy(workflowState.getModifiedBy());
		return response;
	}
}

