package com.hatthabank.adminbackend.model.entity;

import com.hatthabank.sdk.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;

@Entity
@Table(name="workflow_function_transition_users")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor

public class WorkflowFunctionTransitionUser extends BaseEntity{
	
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
	
	@Column(name = "user_id")
	private  Long UserId;
	
	@Column(name = "user_type", length = 255)
	String userType;
	
	@Column(name = "user_group", length = 255)
	String userGroup;
	
	@Column(name = "proxy_user", length = 255)
	String proxyUser;
	
	@Column(name = "proxy_level", nullable = false)
	private  Long proxyLevel;
}
