package com.ernie.Practice.exception;

public class EntityNotFoundException extends javax.persistence.EntityNotFoundException {

    public EntityNotFoundException(Class<?> entity, String keyName, String keyValue) {
        super("Entity (type=" + entity.getSimpleName() + ", " + keyName + "=" + keyValue + ") not found.");
    }
}
