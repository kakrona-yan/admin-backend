package com.hatthabank.adminbackend.service;

import com.hatthabank.adminbackend.model.request.BaseEntityRequest;
import com.hatthabank.adminbackend.model.response.BaseEntityResponse;

public interface InterfaceEntityService {

	BaseEntityResponse create(BaseEntityRequest entity);
	
	BaseEntityResponse update(BaseEntityRequest entity);

	BaseEntityResponse delete(BaseEntityRequest entity);

	Iterable findAll();
}
