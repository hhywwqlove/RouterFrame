package com.router.android;

import com.router.android.model.RouterResult;

/**
 * Created by WeiqiangWang on 17/5/10.
 */

public interface RouterRespCallback<T> {

    void callback(RouterResult<T> result);

}
