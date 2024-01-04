package com.sundata.edu.vo;

/**
 * @author TAOTAO
 * @date 2020/10/14 19:42
 */
public class SelectVo {

    private Integer attrId;

    private String name;

    private String value;

    public SelectVo(Integer attrId, String name, String value) {
        this.attrId = attrId;
        this.name = name;
        this.value = value;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
