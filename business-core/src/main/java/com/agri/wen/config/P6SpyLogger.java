package com.agri.wen.config;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * p6spy打印日志输出格式修改
 * 1.只打印最终执行的sql.
 * 2.sql换到下一行
 * 3.结尾处增加分号,以标示sql结尾
 * Created by odelia on 2016/1/4.
 */
public class P6SpyLogger implements MessageFormattingStrategy {
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public P6SpyLogger() {
    }

    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        StringBuffer buffer = new StringBuffer();
        return !"".equals(sql.trim()) ? buffer.append("/* ")
                .append(this.format.format(new Date()))
                .append(" | took ").append(elapsed)
                .append("ms | ").append(category)
                .append(" | connection ")
                .append(connectionId)
                .append(" */ \n ")
                .append(sql)
                .append(";")
                .toString() : "";
    }
}

