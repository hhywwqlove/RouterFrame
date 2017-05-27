package com.router.android;

import android.content.Context;
import android.os.Bundle;

import com.router.android.annotation.FullUri;
import com.router.android.annotation.IntentParams;
import com.router.android.annotation.Params;
import com.router.android.annotation.RouterContext;
import com.router.android.annotation.Uri;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by WeiqiangWang on 17/5/10.
 */

public abstract class BaseParser {

    public <T> T create(Class<T> router) {
        return (T) Proxy.newProxyInstance(router.getClassLoader(), new Class<?>[]{router}, new ObjHandler());
    }

    protected abstract Object invocationHandler(Object o, Method method, Object[] objects);


    private class ObjHandler implements InvocationHandler {
        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            return invocationHandler(o, method, objects);
        }
    }

    /**
     * 获取intentParam参数
     *
     * @param method
     * @param objects
     * @return
     */
    protected Bundle getIntentDataFromMethod(Method method, Object[] objects) {

        Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotationsArray.length; i++) {
            Annotation[] annotations = parameterAnnotationsArray[i];
            if (annotations != null && annotations.length != 0) {
                if (annotations[0] instanceof IntentParams) {
                    return (Bundle) objects[i];
                }
            }

        }
        return null;
    }

    /**
     * 获取FullUri参数
     *
     * @param method
     * @param objects
     * @return
     */
    protected String getFullUriFromMethod(Method method, Object[] objects) {
        Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotationsArray.length; i++) {
            Annotation[] annotations = parameterAnnotationsArray[i];
            if (annotations != null && annotations.length != 0) {
                if (annotations[0] instanceof FullUri) {
                    return (String) objects[i];
                }
            }

        }
        return null;
    }

    /**
     * 获取Uri参数
     *
     * @param method
     * @param objects
     * @return
     */
    protected String getUriFromMethod(Method method, Object[] objects) {
        Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotationsArray.length; i++) {
            Annotation[] annotations = parameterAnnotationsArray[i];
            if (annotations != null && annotations.length != 0) {
                if (annotations[0] instanceof Uri) {
                    return (String) objects[i];
                }
            }
        }
        return null;
    }

    /**
     * 获取params参数
     *
     * @param method
     * @param objects
     * @return
     */
    protected String getUriParamsFromMethod(Method method, Object[] objects) {
        Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotationsArray.length; i++) {
            Annotation[] annotations = parameterAnnotationsArray[i];
            if (annotations != null && annotations.length != 0) {
                if (annotations[0] instanceof Params) {
                    Map<String, String> params = (Map<String, String>) objects[i];
                    return mapToString(params);
                }
            }

        }
        return null;
    }

    protected Map<String, String> getParamsFromMethod(Method method, Object[] objects) {
        Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotationsArray.length; i++) {
            Annotation[] annotations = parameterAnnotationsArray[i];
            if (annotations != null && annotations.length != 0) {
                if (annotations[0] instanceof Params) {
                    Map<String, String> params = (Map<String, String>) objects[i];
                    return params;
                }
            }

        }
        return null;
    }

    /**
     * 获取context
     *
     * @param method
     * @param objects
     * @return
     */
    protected Context getContextFromMethod(Method method, Object[] objects) {
        Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotationsArray.length; i++) {
            Annotation[] annotations = parameterAnnotationsArray[i];
            if (annotations != null && annotations.length != 0) {
                if (annotations[0] instanceof RouterContext) {
                    return (Context) objects[0];
                }
            }

        }
        return null;
    }

    /**
     * map转成字符串并以&分隔
     *
     * @param params
     * @return
     */
    private String mapToString(Map<String, String> params) {
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
