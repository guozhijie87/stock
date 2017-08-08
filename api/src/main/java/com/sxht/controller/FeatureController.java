package com.sxht.controller;

import com.sxht.model.Feature;
import com.sxht.service.FeatureService;
import com.sxht.common.web.Result;
import com.sxht.common.web.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lmm on 2017/1/16.
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("api/feature")
public class FeatureController {
    private static Logger logger = LoggerFactory.getLogger(FeatureController.class);
    @Autowired
    FeatureService featureService;

    @PostMapping("add")
    public Result add(Feature f) {
        try {
            featureService.insert(f);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping("update")
    public Result update(Feature f) {
        try {
            featureService.update(f);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e);
        }
    }


    @PostMapping("delete")
    public Result delete(String id) {
        try {
            featureService.delete(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping("get")
    public Result get(String id) {
        try {
            Feature f = featureService.get(id);
            return Result.success(f);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

//    @PostMapping("all")
//    public Result all(){
//        try{
////            List<Feature> list = featureService.sel
//        }
//    }

    @PostMapping("getNotFeaturesByUserId")
    public Result getNotFeaturesByUserId(String userId) {
        try {
            List<Feature> list = featureService.getNotFeaturesByUserId(userId);
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping("getFeatures")
    public Result getFeatures(HttpServletRequest request, @RequestBody Map<String, Object> mapData) {
        try {
            System.out.print("执行功能查询操作！！");
            System.out.print(mapData.toString() + "--------------------");
            Map<String,Object> retult=new HashMap<String,Object>();
            int index=(int)mapData.get("index");
            int size=(int)mapData.get("size");
            int offset = index * size;
            mapData.put("offset",offset);
            List<Feature> roles= featureService.getFeatures(mapData);
            int count=featureService.getCountFeature(mapData);
            retult.put("list",roles);
            retult.put("count",count);
            return Result.success(retult);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping("tree")
    public Result tree(boolean checkIsMenu) {
        try {
            List<TreeNode> nodes = featureService.tree(checkIsMenu);
            return Result.success(nodes);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping("userTree")
    public Result userTree(String userId) {
        try {
            List<TreeNode> nodes = featureService.getTreeByUser(userId);
            return Result.success(nodes);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping("roleTree")
    public Result roleTree(String roleId) {
        try {
            List<TreeNode> nodes = featureService.getTreeByRole(roleId);
            return Result.success(nodes);
        } catch (Exception e) {
            return Result.error(e);
        }
    }
}
