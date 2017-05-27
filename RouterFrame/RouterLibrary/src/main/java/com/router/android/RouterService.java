package com.router.android;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WeiqiangWang on 17/5/11.
 */

public class RouterService {

    private static class SingleTon {
        private static RouterService INSTANCE = new RouterService();
    }

    public synchronized static RouterService getInstance() {
        return SingleTon.INSTANCE;
    }


    private Map<String, BaseProvider> providers = new HashMap<>();

    public RouterService registerProvider(BaseProvider provider) {

        String name = provider.getProviderName();
        if (TextUtils.isEmpty(name)) {
            return SingleTon.INSTANCE;
        }
        providers.put(name, provider);
        return SingleTon.INSTANCE;
    }

    public BaseProvider getProvider(String name) {

        if (providers.containsKey(name)) {
            return providers.get(name);
        }
        return null;
    }

    public void unregisterProvider(String name) {
        providers.remove(name);
    }

    public void unregisterAllProvider() {
        providers.clear();
    }


}
