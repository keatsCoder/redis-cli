package cn.keats.rediscli.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: keats_coder
 * @Date: 2020/04/05
 * @Version 1.0
 */
@Configuration
public class SmsDefaultConfig {
    // 两条短信的间隔时间/s
    private static final Integer DEFAULT_SMS_INTERVALS = 60;

    // 短信有效时间/s
    private static final Integer DEFAULT_SMS_EFFECTIVE_TIME = 300;

    // 每天每个手机号的上限/个
    private static final Integer DEFAULT_SMS_MAX_ONE_DAY = 10;

    // 两条短信的间隔时间/s
    private static Integer SMS_INTERVALS;

    // 短信有效时间/s
    private static Integer SMS_EFFECTIVE_TIME;

    // 每天每个手机号的上限/个
    private static Integer SMS_MAX_ONE_DAY;

    public static Integer getSmsIntervals() {
        return SMS_INTERVALS;
    }

    @Value("${sms.intervals}")
    public static void setSmsIntervals(Integer smsIntervals) {
        SMS_INTERVALS = smsIntervals;
    }

    public static Integer getSmsEffectiveTime() {
        return SMS_EFFECTIVE_TIME;
    }

    @Value("${sms.effective_time}")
    public static void setSmsEffectiveTime(Integer smsEffectiveTime) {
        SMS_EFFECTIVE_TIME = smsEffectiveTime;
    }

    public static Integer getMaxSmsOneDay() {
        return SMS_MAX_ONE_DAY;
    }

    @Value("${sms.max_one_day}")
    public static void setMaxSmsOneDay(Integer maxSmsOneDay) {
        SMS_MAX_ONE_DAY = maxSmsOneDay;
    }

    public SmsDefaultConfig() {
        if (SMS_INTERVALS == null) {
            SMS_INTERVALS = DEFAULT_SMS_INTERVALS;
        }
        if (SMS_EFFECTIVE_TIME == null) {
            SMS_EFFECTIVE_TIME = DEFAULT_SMS_EFFECTIVE_TIME;
        }
        if (SMS_MAX_ONE_DAY == null) {
            SMS_MAX_ONE_DAY = DEFAULT_SMS_MAX_ONE_DAY;
        }
    }
}
