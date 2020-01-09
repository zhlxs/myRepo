package com.data.masterandslave.datasource;

public class DBContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void set(String dbType) {
        contextHolder.set(dbType);
    }

    public static String get() {
        return contextHolder.get();
    }

    public static void clearDbType() {
        contextHolder.remove();
    }

}