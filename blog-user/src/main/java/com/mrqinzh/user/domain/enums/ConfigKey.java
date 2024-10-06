package com.mrqinzh.user.domain.enums;

public enum ConfigKey {

    MY_PRIMARY_DOMAIN("主域名"),
    IMG_DOMAIN("图片域名"),
    PROJECT_START_TIME("项目开始时间"),
    PROJECT_FIRST_USE_TIME("项目首次使用时间"),
    RECORD_CODE("备案号"),
    ICP("ICP备案号"),
    DEFAULT_AVATAR_LINK("默认头像链接"),

    ;


    private String desc;
    ConfigKey(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }

    public static boolean validConfigKey(String key) {
        ConfigKey[] keys = ConfigKey.values();
        for (ConfigKey configKey : keys) {
            if (configKey.name().equals(key)) {
                return true;
            }
        }
        return false;
    }

}
