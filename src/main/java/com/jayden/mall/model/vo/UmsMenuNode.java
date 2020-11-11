package com.jayden.mall.model.vo;

import com.jayden.mall.model.pojo.UmsMenu;

import java.util.List;

/**
 * 后台菜单节点封装
 * Created jayden macro on 2020/11/4.
 */
public class UmsMenuNode extends UmsMenu {
    private List<UmsMenuNode> children;

    public List<UmsMenuNode> getChildren() {
        return children;
    }

    public void setChildren(List<UmsMenuNode> children) {
        this.children = children;
    }
}