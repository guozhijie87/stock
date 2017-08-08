package com.sxht.service;

/**
 * Created by lmm on 2017/3/15.
 */
public class PasswordDoNotMuchException extends Exception {
    public PasswordDoNotMuchException() {
        super("Password is not much!");
    }
}
