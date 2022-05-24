package com.hatthabank.adminbackend.model.entity;

import com.hatthabank.sdk.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name="workflow_events")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor

public class WorkflowEvent extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "workflow_function_transition_id", referencedColumnName = "id", nullable = false)
	private WorkflowFunctionTransition  workflowFunctionTransition;
	
	@Column(name = "comment")
    @Type(type = "org.hibernate.type.TextType")
    @Lob
    String comment;
	
	@Column(name = "data")
    @Type(type = "org.hibernate.type.TextType")
    @Lob
    String data;
	
	@OneToOne
	@JoinColumn(name = "parent_id")
	private WorkflowEvent parentId;
}
