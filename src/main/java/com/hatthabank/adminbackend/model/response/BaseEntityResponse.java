package com.hatthabank.adminbackend.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hatthabank.sdk.common.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntityResponse{
	Long id;
	Status status;
	Date createdAt;
	Date modifiedAt;
	String createdBy;
	String modifiedBy;	
}
