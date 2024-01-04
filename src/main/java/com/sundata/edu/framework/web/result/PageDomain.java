package com.sundata.edu.framework.web.result;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

/**
 * 分页数据
 */
public class PageDomain {
    /**
     * 当前记录起始索引
     */
    private Integer pageNum;
    /**
     * 每页显示记录数
     */
    private Integer pageSize = 10;
    /**
     * 排序列
     */
    private String orderByColumn;
    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    private String isAsc;


    public String getOrderBy() {
        if (StringUtils.isEmpty(orderByColumn)) {
            return "";
        }
        //驼峰转下划线
        //LOWER_HYPHEN	              连字符的变量命名规范如lower-hyphen
        //LOWER_UNDERSCORE	          c++变量命名规范如lower_underscore
        //LOWER_CAMEL	              java变量命名规范如lowerCamel
        //UPPER_CAMEL	              java和c++类的命名规范如UpperCamel
        //UPPER_UNDERSCORE	          java和c++常量的命名规范如UPPER_UNDERSCORE
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderByColumn) + " " + isAsc;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }

}
