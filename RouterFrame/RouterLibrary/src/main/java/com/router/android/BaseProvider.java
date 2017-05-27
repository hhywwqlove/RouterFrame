package com.router.android;

import android.text.TextUtils;

import com.router.android.annotation.ProviderName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WeiqiangWang on 17/5/10.
 */

public abstract class BaseProvider {

    protected String name;

    private Map<String, BaseAction> actions = new HashMap<>();

    public BaseProvider() {
        this.name = getAnnotationName();
        initActions();
    }

    /**
     * 通过注解得到provider的名称
     *
     * @return
     */
    private String getAnnotationName() {

        Class clazz = this.getClass();
        if (!clazz.isAnnotationPresent(ProviderName.class)) {
            return null;
        }
        ProviderName name = (ProviderName) clazz.getAnnotation(ProviderName.class);
        return name.name();
    }

    public String getProviderName() {
        return name;
    }

    protected abstract void initActions();

    public BaseAction getAction(String name) {
        return actions.containsKey(name) ? actions.get(name) : null;
    }

    public void registerAction(BaseAction action) {
        String name = action.getActionName();
        if (TextUtils.isEmpty(name)) {
            return;
        }
        actions.put(name, action);
    }

}
