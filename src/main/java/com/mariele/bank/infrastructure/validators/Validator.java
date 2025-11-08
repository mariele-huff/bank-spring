package com.mariele.bank.infrastructure.validators;

public interface Validator<Object> {
    boolean canCreate(Object obj) throws Exception;
    boolean canUpdate(Object obj) throws Exception;
    boolean canDelete(long obj) throws Exception;
}
