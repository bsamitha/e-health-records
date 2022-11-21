package com.healthcare.error;

public class UnAuthorize  extends RuntimeException {

    public UnAuthorize() {
        super("Un Authorize user");
    }

}
