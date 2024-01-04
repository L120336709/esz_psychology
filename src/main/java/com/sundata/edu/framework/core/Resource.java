package com.sundata.edu.framework.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Resource {

    @Value("${parent.root.orgid}")
    public String PARENT_ROOT_ORGID;

    @Value("${userkey}")
    public String USERKEY;


    @Value("${web.upload-path}")
    public String WEB_UPLOAD_PATH;

    @Value("${web.download-path}")
    public String WEB_DOWNLOAD_PATH;



    @Value("${app.key}")
    public String APP_KEY;
}

