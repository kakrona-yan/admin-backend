package com.hatthabank.adminbackend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.hatthabank.sdk.common.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="workflow_function_transition")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowFunctionTransition extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "function_id", referencedColumnName = "id", nullable = false)
	private WorkflowFunction  workflowFunction;
	
	@Column(name = "target_id")
	private  Long targetId;
	
	@Column(name = "source", length = 255)
	String source;
	
	@Column(name = "target", length = 255)
	String target;
	
	@Column(name = "event", length = 255)
	String event;
	
	@Column(name = "action", length = 255)
	String action;
	
	@Column(columnDefinition = "boolean default false")
    private Boolean isEnd;
}
