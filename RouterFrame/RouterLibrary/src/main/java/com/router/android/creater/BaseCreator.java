package com.router.android.creater;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by WeiqiangWang on 17/5/11.
 */

public abstract class BaseCreator {

    protected StringBuffer sb = new StringBuffer();

    public abstract String fullUrl();

    public abstract String singleUrl();

    /**
     * map转成字符串并以&分隔
     *
     * @param params
     * @return
     */
    protected String mapToString(Map<String, String> params) {
        StringBuffer sb = new StringBuffer();
        Set<String> set = params.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String key = it.next();
            String value = params.get(key);
            sb.append(key).append("=").append(value).append("&");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : null;
    }
}
