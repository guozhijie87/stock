package com.sxht.service;

import com.sxht.model.User;

import java.util.Map;

/**
 * Created by yanjinxing on 2017-01-20.
 */
public interface PassportService {

    User signIn(String username, String password) throws UserNotExistsException, PasswordDoNotMuchException;

    /**
     * 修改密码
     *
     * @param userid
     * @param oldpassword
     * @param newPassword
     * @return
     */
    Map<String, Object> modifyPassword(String userid, String oldpassword, String newPassword);
}
