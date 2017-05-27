package com.router.android.creater;

import java.util.Map;

/**
 * Created by WeiqiangWang on 17/5/11.
 * <p>
 * Activity的生成器
 */

public class ActivityUrlCreator extends BaseCreator {

    private static final String DEFAULT_ACT_SCHEMA = "router://";

    public ActivityUrlCreator hostAndPath(String host) {
        String url = DEFAULT_ACT_SCHEMA + host;
        if (sb.length() > 0) {
            sb.append(url, 0, url.length());
        } else {
            sb.append(url);
        }

        return this;
    }

    public ActivityUrlCreator queryParams(Map<String, String> params) {
        if (params == null || params.size() == 0) {
            return this;
        }
        sb.append("?");
        sb.append(mapToString(params));
        return this;
    }

    @Override
    public String fullUrl() {
        return sb.toString();
    }

    @Override
    public String singleUrl() {
        return sb.toString().split("\\?")[0];
    }

    public ActivityUrlCreator newCreater() {
        return new ActivityUrlCreator();
    }

    private ActivityUrlCreator() {
    }
}
