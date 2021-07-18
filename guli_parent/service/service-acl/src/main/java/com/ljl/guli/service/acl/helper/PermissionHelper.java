package com.ljl.guli.service.acl.helper;




import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.ljl.guli.service.acl.entity.Permission;
import org.apache.commons.io.CopyUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 根据权限数据构建菜单数据
 * </p>
 *
 * @author qy
 * @since 2019-11-11
 */
public class PermissionHelper {

    /**
     * 使用递归方法建菜单
     * @param treeNodes
     * @return
     */
    public static List<Permission> bulid(List<Permission> treeNodes) {
        List<Permission> trees = new ArrayList<>();
        Permission root = new Permission();
        for (Permission treeNode : treeNodes) {
            if ("0".equals(treeNode.getPid())) {
                BeanUtils.copyProperties(treeNode,root);
                root.setLevel(1);
            }
        }
        if(null == root.getPid() || null == root.getId()){
            root.setId("1");
            root.setPid("0");
            root.setLevel(1);
        }
        trees.add(findChildren(root,treeNodes));
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static Permission findChildren(Permission treeNode,List<Permission> treeNodes) {
        treeNode.setChildren(new ArrayList<Permission>());
        for (Permission it : treeNodes) {
            if(treeNode.getId().equals(it.getPid())) {
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }
}
