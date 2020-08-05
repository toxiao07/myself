package com.gcxygrp.actg.common.util.tree;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xiao
 * @Date: 2019-10-26 09:32
 * @Description: 递归
 */
@Data
public class TreeUtil {

    //节点id
    private String id;
    //父节点id
    private String parentId;
    //节点名称
    private String name;
    //父节点名称
    private String parenName;
    //其他参数
    private String other;

    //节点名称
    private String title;

    private String accountCode;

    private Boolean ifclick;

    //子节点集合
    private List<TreeUtil> children;

    private List<T> list;

    public TreeUtil() {

    }


    /**
     * 递归（先得到父节点）再递归
     *
     * @param list
     * @return
     */
    public static List<TreeUtil> getTreeResult(List<TreeUtil> list) {
        List<TreeUtil> listResult = new ArrayList<>();
        for (TreeUtil t : list) {
            if (t.getParentId() == null || "".equals(t.getParentId()) || t.getParentId().equals("0")) {
                listResult.add(t);// 得到父类
            }
        }
        secondTree(list, listResult);
        return listResult;
    }

    /**
     * 父节点获取子节点
     *
     * @param list
     * @param listResult
     * @return
     */

    public static void secondTree(List<TreeUtil> list, List<TreeUtil> listResult) {
        //StringBuffer buffer = new StringBuffer();
        for (TreeUtil tResult : listResult) {
            List<TreeUtil> childrens = new ArrayList<>();
            for (TreeUtil t : list) {
               // buffer .append(t.getName()+"-") ;
                if (t.getParentId() == null || t.getParentId() == "" ||t.getParentId().equals("0") ) {
                   // t.setParenName(t.getName());
                    continue;
                }
                if (tResult.getId().equals(t.getParentId())) {
                   // t.setParenName(buffer.toString().substring(0, buffer.toString().length() - 1));
                    childrens.add(t);
                }
            }
            tResult.setChildren(childrens);
            if (!tResult.getChildren().isEmpty()) {
                secondTree(list, tResult.getChildren());
            }
        }


    }
    //使用方法参考
  /*  List< TreeUtil> tree = new ArrayList<>();
        for (EventType e:list //原来的数据进行转换，list是原来的数据
                ) {        
        tree.add(new TreeUtil(e.getName(), e.getEventTypeId(), e.getParentId()));
    }
    List<TreeUtil> result =  TreeUtil.getTreeResult(tree);     //递归得到的结果
    */
}
