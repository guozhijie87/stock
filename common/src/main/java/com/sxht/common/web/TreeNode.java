package com.sxht.common.web;

import java.util.Set;

/**
 * Created by lmm on 2017/1/19.
 */
public class TreeNode {
    public TreeNode(String id, String name, String url, TreeNode parent, Set<TreeNode> childs) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.parent = parent;
        this.childs = childs;
    }

    String id;
    String name;
    String url;
    TreeNode parent;
    Set<TreeNode> childs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public Set<TreeNode> getChilds() {
        return childs;
    }

    public void setChilds(Set<TreeNode> childs) {
        this.childs = childs;
    }
}
