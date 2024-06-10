package com.unknown.deliveryserver.domain.order.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderUtil {
    public static class date {
        public static String getDate() {
            LocalDateTime now = LocalDateTime.now();

            // yyyyMMdd 형식의 DateTimeFormatter 생성
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            return now.format(formatter).substring(2);
        }

        public static String getTime() {
            LocalDateTime now = LocalDateTime.now();

            //  "kkmmss" 형식을 사용하여 시간을 24시간 형식으로 표시
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("kkmmss");
            return now.format(formatter);
        }

        public static String getTimeMillis(LocalDateTime createdAt) {
            return String.valueOf(createdAt).split("\\.")[1].substring(0, 6);
        }
    }
}
