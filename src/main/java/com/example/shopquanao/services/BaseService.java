/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.services;

/**
 *
 * @author haidu
 */
import com.example.shopquanao.exceptions.CommonException;
import java.lang.reflect.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

public abstract class BaseService<T, ID, DTO, RDTO> {

    protected abstract JpaRepository<T, ID> getRepository();

    protected abstract Function<T, DTO> getMapper();

    protected abstract Class<T> getEntityClass();

    public Page<DTO> getAll(String searchText, Pageable pageable) {
        return getRepository().findAll(pageable).map(getMapper());
    }

    public Optional<DTO> getById(ID id) {
        T entity = getRepository().findById(id).orElseThrow(() 
                -> new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: " + id));
        return Optional.ofNullable(getMapper().apply(entity));
    }

    public Optional<DTO> create(RDTO data) {
        try {
            T entity = getEntityClass().getDeclaredConstructor().newInstance();

            for (Field field : data.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(data);
                if (value != null) {
                    Field entityField = entity.getClass().getDeclaredField(field.getName());
                    entityField.setAccessible(true);
                    entityField.set(entity, value);
                }
            }
            T savedEntity = getRepository().save(entity);
            return Optional.ofNullable(getMapper().apply(savedEntity));

        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "Failed to create entity: " + e.getMessage());
        }
    }

    public Optional<DTO> update(ID id, RDTO data) {
        T entityFind = getRepository().findById(id).orElseThrow(()
                -> new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: " + id));

        try {
            for (Field field : data.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(data);
                if (value != null) {
                    Field entityField = entityFind.getClass().getDeclaredField(field.getName());
                    entityField.setAccessible(true);
                    entityField.set(entityFind, value);
                }
            }

            T savedEntity = getRepository().save(entityFind);
            return Optional.ofNullable(getMapper().apply(savedEntity));

        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "Failed to update entity: " + e.getMessage());
        }
    }

    public void deleteById(ID id) {
        if (!getRepository().existsById(id)) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "Entity not found with id: " + id);
        }
        getRepository().deleteById(id);
    }
}
