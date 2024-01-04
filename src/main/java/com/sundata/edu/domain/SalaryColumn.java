/******************************************************************************
 * Copyright (C) 2016 - 2021 Yunxin network technology (Shenzhen) Co.,Ltd
 * All Rights Reserved.
 * 本软件为云信网络科技（深圳）有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.sundata.edu.domain;

/**
 * @author dudejin
 * @version V1.0
 * @Title: SalaryColumn.java
 * @Package com.yunxinlink.salary.bean
 * @Description 工资每一项
 * @date 2021 05-22 00:29.
 */
public class SalaryColumn {

    private String category1;
    private String category2;
    private String category3;
    private String title;
    private String money;

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SalaryColumn{");
        sb.append("category1='").append(category1).append('\'');
        sb.append(", category2='").append(category2).append('\'');
        sb.append(", category3='").append(category3).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", money='").append(money).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
