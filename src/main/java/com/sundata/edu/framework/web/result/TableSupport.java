package com.sundata.edu.framework.web.result;

import com.sundata.edu.framework.util.ServletUtils;

/**
 * 表格数据处理
 */
public class TableSupport {
    /**
     * 封装分页对象
     */
    private static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt("pageNum"));
        pageDomain.setPageSize(ServletUtils.getParameterToInt("pageSize"));
        pageDomain.setOrderByColumn(ServletUtils.getParameter("orderByColumn"));
        pageDomain.setIsAsc(ServletUtils.getParameter("isAsc"));
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }

}
