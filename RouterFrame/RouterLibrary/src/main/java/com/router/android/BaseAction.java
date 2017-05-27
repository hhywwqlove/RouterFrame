package com.router.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.router.android.annotation.ActionName;

import java.util.Map;

/**
 * Created by WeiqiangWang on 17/5/10.
 */

public abstract class BaseAction {

    private String actionName;

    public BaseAction() {
        this.actionName = getAnnotationName();
    }

    public abstract String invokeMethod(String url, Map<String, String> params);

    public abstract Fragment invokeFragment(String url, Bundle bundle);

    public String getActionName() {
        return this.actionName;
    }

    /**
     * 通过注解得到ActionName的名称
     *
     * @return
     */
    private String getAnnotationName() {

        Class clazz = this.getClass();
        if (!clazz.isAnnotationPresent(ActionName.class)) {
            return null;
        }
        ActionName name = (ActionName) clazz.getAnnotation(ActionName.class);
        return name.name();
    }

}
