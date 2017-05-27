package com.router.android.parsers;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.router.android.BaseAction;
import com.router.android.BaseParser;
import com.router.android.BaseProvider;
import com.router.android.Constants;
import com.router.android.RouterService;
import com.router.android.model.RouterResult;

import java.lang.reflect.Method;

/**
 * Created by WeiqiangWang on 17/5/10.
 * <p>
 * modulename://actionName/fragmentName
 */

public class FragmentParser extends BaseParser {

    @Override
    protected Object invocationHandler(Object o, Method method, Object[] objects) {
        String fullUri = getFullUriFromMethod(method, objects);
        Bundle bundle = getIntentDataFromMethod(method, objects);

        if (TextUtils.isEmpty(fullUri)) {
            return respErrorResult(fullUri, Constants.Code.ERROR_NO_URI, Constants.Code.NO_URL_TEXT);
        }

        Uri uri = Uri.parse(fullUri);

        String schema = uri.getScheme();

        String host = uri.getHost();

        if (TextUtils.isEmpty(schema)) {
            return respErrorResult(fullUri, Constants.Code.ERROR_NO_SCHEMA, Constants.Code.NO_SCHEMA_TEXT);
        }

        if (TextUtils.isEmpty(host)) {
            return respErrorResult(fullUri, Constants.Code.ERROR_NO_HOST, Constants.Code.NO_HOST_TEXT);
        }

        BaseProvider provider = RouterService.getInstance().getProvider(schema);

        if (provider == null) {
            return respErrorResult(fullUri, Constants.Code.ERROR_RES_NOFOUND, Constants.Code.NOFOUND_PROVIDER);
        }

        BaseAction action = provider.getAction(host);

        if (action == null) {
            return respErrorResult(fullUri, Constants.Code.ERROR_RES_NOFOUND, Constants.Code.NOFOUND_ACTION);
        }

        Fragment fragment = action.invokeFragment(fullUri, bundle);

        if (fragment == null) {
            return respErrorResult(fullUri, Constants.Code.ERROR_RES_NOFOUND, Constants.Code.RES_NOFOUND);
        }

        return respSuccResult(fullUri, fragment);
    }

    /**
     * 返回失败或错误数据
     *
     * @return
     */
    private RouterResult respErrorResult(String fullUrl, int errorCode, String errorMsg) {

        return new RouterResult(fullUrl, errorCode, errorMsg);

    }

    /**
     * 返回成功数据
     */
    private <T> RouterResult<T> respSuccResult(String fullUri, T data) {
        return new RouterResult<T>(fullUri, Constants.Code.SUCCESS_CODE, "", data);
    }


}
