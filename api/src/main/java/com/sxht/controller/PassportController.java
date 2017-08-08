package com.sxht.controller;

import com.sxht.service.PassportService;
import com.sxht.service.PasswordDoNotMuchException;
import com.sxht.service.UserNotExistsException;
import com.sxht.util.EncryptionUtil;
import com.sxht.util.OperateImageUtil;
import com.sxht.util.SessionVariable;
import com.sxht.common.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by yanjinxing on 2017-01-20.
 */
@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("api/passport")
public class PassportController {

    @Autowired
    PassportService passportService;

    @PostMapping(value = "signln")
    public Result signln(String username, String password, HttpServletRequest req, HttpServletResponse res) {
        try {
            String md5Password = EncryptionUtil.MD5(password);
            passportService.signIn(username, md5Password);

            return Result.success();
        } catch (UserNotExistsException e) {
            return Result.error(e);
        } catch (PasswordDoNotMuchException e) {
            return Result.error(e);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    /**
     * 用户修改密码
     *
     * @param request
     * @param resp
     * @return
     */
    @PostMapping(value = "modifyPassword")
    public Result modifyPassword(HttpServletRequest request, HttpServletResponse resp) {

        try {
            System.out.print("执行用户修改密码操作！！");
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            //String userid=(String)request.getSession().getAttribute(SessionVariable.USER_ID);
            String userid = request.getParameter("userid");
            //判断userid是否为空
            if (StringUtils.isEmpty(userid)) {
                return Result.error("用户未登陆！！");
            }
            //判断oldPassword是否为空
            if (StringUtils.isEmpty(oldPassword)) {
                return Result.error("oldPassword is null");
            }
            //判断newPassword是否为空
            if (StringUtils.isEmpty(newPassword)) {
                return Result.error("newPassword is null");
            }
            Map<String, Object> map = passportService.modifyPassword(userid, oldPassword, newPassword);
            if (!"success".equals(map.get("state"))) {
                return Result.error((String) map.get("msg"));
            }
            HttpSession session = request.getSession();
            session.removeAttribute(SessionVariable.USER_ID);
            session.removeAttribute(SessionVariable.USER_NAME);
            session.setMaxInactiveInterval(0);
            Cookie cookie = new Cookie("JSESSIONID", null);
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping(value = "upload")
    public Result uploadImage(HttpServletRequest request, HttpServletResponse resp,
                              @RequestParam(required = true) MultipartFile file,
                              @RequestParam(required = false)int x,
                              @RequestParam(required = false)int y,
                              @RequestParam(required = false)int width,
                              @RequestParam(required = false)int hight ) {
        try {
            OperateImageUtil.operate(file,20,20,100,100);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e);
        }
    }

}
