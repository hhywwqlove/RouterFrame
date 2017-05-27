package com.router.android.parsers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.router.android.BaseParser;
import com.router.android.Constants;
import com.router.android.annotation.RequestCode;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by WeiqiangWang on 17/5/10.
 */

public class ActivityParser extends BaseParser {


    @Override
    protected Object invocationHandler(Object o, Method method, Object[] objects) {

        String fullUri = getFullUriFromMethod(method, objects);
        Bundle bundle = getIntentDataFromMethod(method, objects);
        String params = getUriParamsFromMethod(method, objects);
        int requestCode = getRequestCodeFromMethod(method);
        Context context = getContextFromMethod(method, objects);
        String uri = getUriFromMethod(method, objects);

        String intentUri;
        if (TextUtils.isEmpty(fullUri)) {
            if (TextUtils.isEmpty(uri)) {
                return null;
            }
            intentUri = TextUtils.isEmpty(params) ? uri : uri + "?" + params;
        } else {
            intentUri = fullUri;
        }

        openRouterUri(context, intentUri, bundle, requestCode);

        return null;
    }

    /**
     * 通过uri跳转指定页面
     *
     * @param url
     */
    private void openRouterUri(Context context, String url, Bundle bundle, int requestCode) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        boolean isValid = activities.size() > 0;
        if (!isValid) {
            return;
        }
        if (bundle != null) {
            intent.putExtra(Constants.INTENT_BUNDLE_KEY, bundle);
        }

        if (requestCode == -1) {
            context.startActivity(intent);
        } else {
            ((Activity) context).startActivityForResult(intent, requestCode);
        }

    }


    /**
     * 获取requestcode注解的值
     *
     * @param method
     * @return
     */
    private int getRequestCodeFromMethod(Method method) {
        if (!method.isAnnotationPresent(RequestCode.class)) {
            return -1;
        }
        RequestCode code = method.getAnnotation(RequestCode.class);
        return code.code();
    }


}
