package com.router.android;

/**
 * Created by WeiqiangWang on 17/5/10.
 */

public class Constants {

    public static final int REQUEST_CODE = 5477;
    public static final String INTENT_BUNDLE_KEY = "data";

    public static class Code {

        public static final int SUCCESS_CODE = 1;
        public static final int ERROR_NO_URI = -10;
        public static final int ERROR_NO_HOST = -11;
        public static final int ERROR_NO_SCHEMA = -12;

        public static final int ERROR_RES_NOFOUND = 13;

        public static final String NO_URL_TEXT = "协议格式错误，未找到对应的uri值";
        public static final String NO_HOST_TEXT = "协议格式错误，未找到对应的host值";
        public static final String NO_SCHEMA_TEXT = "协议格式错误,未找到对应的module";

        public static final String RES_NOFOUND = "未找到对应的数据";

        public static final String NOFOUND_PROVIDER = "未找到对应的provider";
        public static final String NOFOUND_ACTION = "未找到对应的action";
    }


}
