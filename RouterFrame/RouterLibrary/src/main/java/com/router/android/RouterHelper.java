package com.router.android;

import com.router.android.parsers.ActivityParser;
import com.router.android.parsers.FragmentParser;
import com.router.android.parsers.MethodParser;
import com.router.android.routers.ActivityRouter;
import com.router.android.routers.FragmentRouter;
import com.router.android.routers.MethodRouter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WeiqiangWang on 17/5/10.
 * <p>
 * 路由框架的基本api
 */

public class RouterHelper {

    public static RouterParserFactory FACTORY = new RouterParserFactory();

    /**
     * 获取ActivityParser
     *
     * @return
     */
    public static ActivityRouter getActivityRouter() {
        return (FACTORY.getParser(RouterParserFactory.ACTIVITY_PARSER)).create(ActivityRouter.class);
    }

    /**
     * 获取FragmentParser
     *
     * @return
     */
    public static FragmentRouter getFragmentRouter() {
        return FACTORY.getParser(RouterParserFactory.FRAGMENT_PARSER).create(FragmentRouter.class);
    }

    /**
     * 获取方法结果的methodParser
     *
     * @return
     */
    public static MethodRouter getMethodRouter() {
        return FACTORY.getParser(RouterParserFactory.METHOD_PARSER).create(MethodRouter.class);
    }

    /**
     * 获取自定义的parser
     *
     * @param key
     * @return
     */
    public static BaseParser getParser(String key) {
        return FACTORY.getParser(key);
    }


    /**
     * 路由解析器工厂类
     */
    public static class RouterParserFactory {

        private static final String ACTIVITY_PARSER = "activity_parser";
        private static final String FRAGMENT_PARSER = "fragment_parser";
        private static final String METHOD_PARSER = "method_parser";

        private Map<String, BaseParser> parsers = new HashMap<>();

        private RouterParserFactory() {
            parsers.put(ACTIVITY_PARSER, new ActivityParser());
            parsers.put(FRAGMENT_PARSER, new FragmentParser());
            parsers.put(METHOD_PARSER, new MethodParser());
        }


        /**
         * 建议在application中添加自定义的解析器
         *
         * @param key
         * @param parser
         */
        public void addCustomParser(String key, BaseParser parser) {
            parsers.put(key, parser);
        }

        /**
         * 获取自定义的parser
         *
         * @param key
         * @return
         */
        public BaseParser getParser(String key) {
            return parsers.get(key);
        }

    }

}
