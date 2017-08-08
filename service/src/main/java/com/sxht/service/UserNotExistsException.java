package com.sxht.service;

/**
 * Created by lmm on 2017/3/15.
 */
public class UserNotExistsException extends Exception {
    public UserNotExistsException() {
        super("User is not exists");
    }
}
