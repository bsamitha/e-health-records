package com.healthcare.error;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Id not found : " + id);
    }

}
