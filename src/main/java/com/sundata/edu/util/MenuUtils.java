package com.sundata.edu.util;

import com.sundata.edu.domain.SysMenu;
import com.sundata.edu.vo.SysMenuVo;

import java.util.*;

/**
 * @Description TODO
 * @Author whj
 * @Date 2018-12-22 14:09
 * @Version 1.0
 */
public class MenuUtils {
    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public static List<SysMenuVo> getChildPerms(List<SysMenuVo> list, Integer parentId) {
        List<SysMenuVo> returnList = new ArrayList<SysMenuVo>();
        for (Iterator<SysMenuVo> iterator = list.iterator(); iterator.hasNext(); ) {
            SysMenuVo t = (SysMenuVo) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (parentId.intValue() == t.getParentId().intValue()) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }


    /**
     * 对象转菜单树
     *
     * @param menuList     菜单列表
     * @param isCheck      是否需要选中
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag    是否需要显示权限标识
     * @return
     */
    public static List<Map<String, Object>> getTrees(List<SysMenu> menuList, boolean isCheck, List<SysMenu> roleMenuList, boolean permsFlag) {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        Set<Integer> roleMenuIds = new TreeSet<>();
        if (roleMenuList != null) {
            for (SysMenu sysMenu : roleMenuList) {
                if (!roleMenuIds.contains(sysMenu.getMenuId())) {
                    roleMenuIds.add(sysMenu.getMenuId());
                }
            }
        }
        for (SysMenu menu : menuList) {
            Map<String, Object> deptMap = new HashMap<String, Object>();
            deptMap.put("id", menu.getMenuId());
            deptMap.put("pId", menu.getParentId());
            deptMap.put("name", transMenuName(menu, permsFlag));
            deptMap.put("title", menu.getMenuName());
            if (isCheck) {
                deptMap.put("checked", roleMenuIds.contains(menu.getMenuId()));
            } else {
                deptMap.put("checked", false);
            }
            trees.add(deptMap);
        }
        return trees;
    }

    private static String transMenuName(SysMenu menu, boolean permsFlag) {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (permsFlag) {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }



    public static List<Map<String, Object>> getGradeInfosTrees(List<Map<String, Object>> gradeInfos, boolean isCheck, List<Map<String, String>> userClassInfo, boolean permsFlag, List<Map<String, Object>> trees) {

        Set<String> roleClassIds = new TreeSet<>();
        if (userClassInfo != null) {
            for (Map<String, String> map : userClassInfo) {
                if (!roleClassIds.contains(map.get("gradeId"))) {
                    roleClassIds.add(map.get("gradeId"));
                }
            }
        }
        for (Map<String, Object> menu : gradeInfos) {
            Map<String, Object> deptMap = new HashMap<String, Object>();
            deptMap.put("id", menu.get("id"));
            deptMap.put("pId", menu.get("pid") == null? 0 : menu.get("pid"));
            deptMap.put("name", menu.get("name"));
            deptMap.put("title", menu.get("name"));
            if (isCheck) {
                deptMap.put("checked", roleClassIds.contains(menu.get("id")));
            } else {
                deptMap.put("checked", false);
            }
            trees.add(deptMap);
        }
        return trees;
    }


    public static List<Map<String, Object>> getClassInfosTrees(List<Map<String, Object>> gradeInfos, boolean isCheck, List<Map<String, String>> userClassInfo, boolean permsFlag, List<Map<String, Object>> trees) {

        Set<String> roleClassIds = new TreeSet<>();
        if (userClassInfo != null) {
            for (Map<String, String> map : userClassInfo) {
                if (!roleClassIds.contains(map.get("classId"))) {
                    roleClassIds.add(map.get("classId"));
                }
            }
        }
        for (Map<String, Object> menu : gradeInfos) {
            Map<String, Object> deptMap = new HashMap<String, Object>();
            deptMap.put("id", menu.get("id"));
            deptMap.put("pId", menu.get("pid") == null? 0 : menu.get("pid"));
            deptMap.put("name", menu.get("name"));
            deptMap.put("title", menu.get("name"));
            if (isCheck) {
                deptMap.put("checked", roleClassIds.contains(menu.get("id")));
            } else {
                deptMap.put("checked", false);
            }
            trees.add(deptMap);
        }
        return trees;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param sysMenu
     */
    private static void recursionFn(List<SysMenuVo> list, SysMenuVo sysMenu) {
        // 得到子节点列表
        List<SysMenuVo> childList = getChildList(list, sysMenu);
        sysMenu.setChildren(childList);
        for (SysMenuVo tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                Iterator<SysMenuVo> it = childList.iterator();
                while (it.hasNext()) {
                    SysMenuVo n = (SysMenuVo) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private static List<SysMenuVo> getChildList(List<SysMenuVo> list, SysMenuVo t) {

        List<SysMenuVo> tlist = new ArrayList<>();
        Iterator<SysMenuVo> it = list.iterator();
        while (it.hasNext()) {
            SysMenuVo n = it.next();
            if (n.getParentId().intValue() == t.getMenuId().intValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    List<SysMenuVo> returnList = new ArrayList<SysMenuVo>();

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @param prefix   子节点前缀
     */
    public List<SysMenuVo> getChildPerms(List<SysMenuVo> list, Long parentId, String prefix) {
        if (list == null) {
            return null;
        }
        for (Iterator<SysMenuVo> iterator = list.iterator(); iterator.hasNext(); ) {
            SysMenuVo node = iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (parentId.equals(node.getParentId())) {
                recursionFn(list, node, prefix);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*
             * if (node.getParentId()==0) { recursionFn(list, node); }
             */
        }
        return returnList;
    }

    private void recursionFn(List<SysMenuVo> list, SysMenuVo node, String p) {
        // 得到子节点列表
        List<SysMenuVo> childList = getChildList(list, node);
        if (hasChild(list, node)) {
            // 判断是否有子节点
            returnList.add(node);
            Iterator<SysMenuVo> it = childList.iterator();
            while (it.hasNext()) {
                SysMenuVo n = (SysMenuVo) it.next();
                n.setMenuName(p + n.getMenuName());
                recursionFn(list, n, p + p);
            }
        } else {
            returnList.add(node);
        }
    }

    /**
     * 判断是否有子节点
     */
    private static boolean hasChild(List<SysMenuVo> list, SysMenuVo t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
