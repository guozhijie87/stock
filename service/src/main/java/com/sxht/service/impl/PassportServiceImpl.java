package com.sxht.service.impl;

import com.sxht.dao.UserMapper;
import com.sxht.model.User;
import com.sxht.service.PassportService;
import com.sxht.service.PasswordDoNotMuchException;
import com.sxht.service.UserNotExistsException;
import com.sxht.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanjinxing on 2017-01-20.
 */
@Service
public class PassportServiceImpl implements PassportService {
    @Autowired
    UserMapper userMapper;

    public User signIn(String username, String password) throws UserNotExistsException, PasswordDoNotMuchException {
        User user = userMapper.selectByPrimaryKey(username);

        if (user == null) {
            throw new UserNotExistsException();
        } else if (!user.getPassword().equals(password)) {
            throw new PasswordDoNotMuchException();
        }

        return user;
    }


    @Override
    public Map<String, Object> modifyPassword(String userid, String oldpassword, String newPassword) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userMapper.queryByUserId(userid);
        if (!user.getPassword().equals(EncryptionUtil.MD5(oldpassword))) {
            map.put("state", "error");
            map.put("msg", "用户原密码不正确！！");
            return map;
        }
        User u = new User();
        u.setUserid(userid);
        u.setPassword(EncryptionUtil.MD5(newPassword));
        userMapper.updateByPrimaryKeySelective(u);
        map.put("state", "success");
        return map;
    }
}
