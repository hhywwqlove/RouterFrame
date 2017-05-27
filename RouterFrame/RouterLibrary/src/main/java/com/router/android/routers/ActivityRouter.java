package com.router.android.routers;

import android.content.Context;
import android.os.Bundle;

import com.router.android.Constants;
import com.router.android.annotation.FullUri;
import com.router.android.annotation.IntentParams;
import com.router.android.annotation.Params;
import com.router.android.annotation.RequestCode;
import com.router.android.annotation.RouterContext;
import com.router.android.annotation.Uri;

import java.util.Map;

/**
 * Created by WeiqiangWang on 17/5/10.
 */

public interface ActivityRouter {

    @RequestCode(code = Constants.REQUEST_CODE)
    void startActivityForResultFromRouter(@RouterContext Context context, @FullUri String url);

    @RequestCode(code = Constants.REQUEST_CODE)
    void startActivityForResultFromRouter(@RouterContext Context context, @FullUri String url, @IntentParams Bundle bundle);

    @RequestCode(code = Constants.REQUEST_CODE)
    void startActivityForResultFromRouter(@RouterContext Context context, @Uri String url, @Params Map<String, String> params);

    @RequestCode(code = Constants.REQUEST_CODE)
    void startActivityForResultFromRouter(@RouterContext Context context, @Uri String url, @IntentParams Bundle bundle, @Params Map<String, String> params);

    void startActivityFromRouter(@RouterContext Context context, @FullUri String url);

    void startActivityFromRouter(@RouterContext Context context, @FullUri String url, @IntentParams Bundle bundle);

    void startActivityFromRouter(@RouterContext Context context, @Uri String url, @Params Map<String, String> params);

    void startActivityFromRouter(@RouterContext Context context, @Uri String url, @IntentParams Bundle bundle, @Params Map<String, String> params);

}
