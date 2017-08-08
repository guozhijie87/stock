package com.sxht.controller;

import com.sxht.service.RoleFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lmm on 2017/1/19.
 */
@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("api/roleFeature")
public class RoleFeatureController {
    @Autowired
    RoleFeatureService roleFeatureService;

//    @Post
}
