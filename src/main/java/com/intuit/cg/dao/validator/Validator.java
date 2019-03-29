package com.intuit.cg.dao.validator;

/**
 *
 * @author sakhim
 */
public interface Validator<T> {

    void validate(T t);

}
