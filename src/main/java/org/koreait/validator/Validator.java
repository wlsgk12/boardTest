package org.koreait.validator;

public interface Validator<T> {
    void check(T t);

    default void requiredCheck(String str, RuntimeException e) {
        if (str == null || str.isBlank()) {
            throw e;
        }
    }
}
