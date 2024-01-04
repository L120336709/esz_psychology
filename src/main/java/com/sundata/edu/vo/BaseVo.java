package com.sundata.edu.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BaseVo implements Serializable
{
    /** 搜索值 */
    private String searchValue;

    /** 请求参数 */
    private Map<String, Object> params;

    /**
     * 班级集合
     */
    private List<String> classIdList;

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<String> getClassIdList() {
        return classIdList;
    }

    public void setClassIdList(List<String> classIdList) {
        this.classIdList = classIdList;
    }
}
