package com.hatthabank.adminbackend.service.impl;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.hatthabank.adminbackend.message.MessageCode;
import com.hatthabank.adminbackend.model.request.BaseEntityRequest;
import com.hatthabank.adminbackend.model.response.BaseEntityResponse;
import com.hatthabank.adminbackend.service.InterfaceEntityService;
import com.hatthabank.sdk.web.exception.ApplicationException;

@Service
public abstract class AbstractEntityServiceImpl<T> implements InterfaceEntityService {
	protected Optional<T> findById(BaseEntityRequest entity){
        return Optional.empty();
    }

    protected abstract BaseEntityResponse createRecord(BaseEntityRequest entity);

    protected abstract BaseEntityResponse updateRecord(BaseEntityRequest entity);

    protected void deleteRecord(T entity) {}

    protected abstract BaseEntityRequest setUpdateValue(BaseEntityRequest updateEntity, T existEntity);

    protected abstract T mapRequest2Entity(BaseEntityRequest entity);

    @Override
    public BaseEntityResponse create(BaseEntityRequest entity) {
        Optional<T> existEntity = findById(entity);
        if (existEntity.isEmpty()) {
            try {
                return createRecord(entity);
            } catch (DataIntegrityViolationException ex) {
                throw new ApplicationException(MessageCode.BAD_REQUEST);
            }
        } else {
            throw new ApplicationException(MessageCode.ALREADY_EXIST);
        }
    }

    @Override
    public BaseEntityResponse update(BaseEntityRequest entity) {
        Optional<T> existEntity = findById(entity);
        if (existEntity.isPresent()) {
            try {
                return updateRecord(setUpdateValue(entity, existEntity.get()));
            } catch (DataIntegrityViolationException ex) {
                throw new ApplicationException(MessageCode.BAD_REQUEST);
            }
        } else {
            throw new ApplicationException(MessageCode.NOT_FOUND);
        }
    }

    @Override
    public BaseEntityResponse delete(BaseEntityRequest entity) {
        Optional<T> existEntity = findById(entity);
        if (existEntity.isPresent()) {
            deleteRecord(existEntity.get());
        } else {
            throw new ApplicationException(MessageCode.NOT_FOUND);
        }
        return null;
    }
}