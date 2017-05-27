package com.router.android.routers;

import com.router.android.annotation.FullUri;
import com.router.android.annotation.Params;
import com.router.android.model.RouterResult;

import java.util.Map;

/**
 * Created by WeiqiangWang on 17/5/11.
 */

public interface MethodRouter {
    RouterResult<String> getModuleMethodData(@FullUri String url, @Params Map<String, String> params);
}
