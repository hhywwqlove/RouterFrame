package com.router.android.routers;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.router.android.annotation.FullUri;
import com.router.android.annotation.IntentParams;
import com.router.android.model.RouterResult;

/**
 * Created by WeiqiangWang on 17/5/10.
 */

public interface FragmentRouter {

    RouterResult<Fragment> getFragmentFromRouter(@FullUri String url, @IntentParams Bundle data);

    RouterResult<Fragment> getFragmentFromRouter(@FullUri String url);

}
