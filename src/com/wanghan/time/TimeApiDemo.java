package com.wanghan.time;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Set;

/**
 * time API 演示
 */
public class TimeApiDemo {

    //1.LocalDate LocalTime LocalDateTime
    @Test
    public void test(){
        //获取系统时间
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
        //获取自定义时间
        LocalDateTime dateTime1 = LocalDateTime.of(2015, 10, 19, 13, 22, 33);
        System.out.println(dateTime1);
        //时间运算API
        LocalDateTime dateTime2 = dateTime.plusYears(2);
        System.out.println(dateTime2);
        LocalDateTime dateTime3 = dateTime.minusYears(2);
        System.out.println(dateTime3);
        //................

        //获取当月时间
        LocalDateTime monthDateStartTime = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN);
        LocalDateTime monthDateEndTime = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX);
        System.out.println(monthDateStartTime);
        System.out.println(monthDateEndTime);

        //获取当天的开始时间和结束时间
        LocalDateTime todayStartTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime todayEndTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        //获取当前小时的起始时间
        LocalDate now = LocalDate.now();
        LocalDateTime hoursStartTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), LocalTime.now().getHour(), 0, 0);
        LocalDateTime hoursEndTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), LocalTime.now().getHour(), 59, 59);
        Date hourStartTime = getHourStartTime(0);
        Date hourEndTime = getHourEndTime(0);
        System.out.println("获取当前小时的起始时间" + hoursStartTime);
        System.out.println(hoursEndTime);
        System.out.println(hourStartTime);
        System.out.println(hourEndTime);
        //获取月/日
        System.out.println(dateTime.getYear());
        System.out.println(dateTime.getMonth());
        System.out.println(dateTime.getDayOfMonth());
        System.out.println(dateTime.getHour());
        System.out.println(dateTime.getMinute());
        System.out.println(dateTime.getSecond());
        System.out.println(dateTime.getDayOfYear());
        System.out.println(dateTime.getDayOfWeek());
    }

    //2. Instant : 时间戳(以Unix元年 : 1970年1月1日 00:00:00 之间的毫秒值)
    @Test
    public void test2(){
        //获取的是UTC时间,8小时时差
        Instant ins = Instant.now();
        System.out.println(ins);
        //消除8小时时差
        OffsetDateTime offsetDateTime = ins.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        //只获取时间戳
        System.out.println(ins.toEpochMilli());

        //根据时间戳转换时间
        Instant ins2 = Instant.ofEpochMilli(1);
        System.out.println(ins2);
    }

    //3. Duration: 计算两个"时间"之间的间隔
    @Test
    public void test3(){
        Instant ins = Instant.now();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant ins1 = Instant.now();
        //使用LocalDateTime也行
        Duration duration = Duration.between(ins, ins1);
        System.out.println(duration.toMillis());
    }

    //4.Period : 计算两个"日期"之间的间隔
    @Test
    public void test4(){
        LocalDate localDate = LocalDate.of(2015, 1, 1);
        LocalDate localDate1 = LocalDate.now();
        Period period = Period.between(localDate, localDate1);
        System.out.println(period);//P3Y9M4D 3年9月4天
        System.out.println(period.getYears());//..获取具体的天数时间数
    }

    //5.时间校正器: TemporalAdjuster
    @Test
    public void test5(){
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);

        LocalDateTime dateTime1 = dateTime.withDayOfMonth(10);
        System.out.println(dateTime1);

        //时间校正器
        LocalDateTime dateTime2 = dateTime.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(dateTime2);
        //自定义:下个工作日(....)
        LocalDateTime dateTime4 = dateTime.with(temporal -> {
            LocalDateTime dateTime3 = (LocalDateTime) temporal;

            DayOfWeek dayOfWeek = dateTime3.getDayOfWeek();

            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return dateTime3.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return dateTime3.plusDays(2);
            } else {
                return dateTime3.plusDays(1);
            }
        });
        System.out.println(dateTime4);
    }

    /**
     * DateTimeFormatter : 时间格式
     */
    @Test
    public void test6(){
        //默认格式
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        LocalDateTime now = LocalDateTime.now();
        //格式化
        String strNow = now.format(formatter);
        System.out.println(strNow);

        //自定义
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String strNow1 = now.format(formatter1);
        System.out.println(strNow1);

        //解析
        LocalDateTime parse = now.parse(strNow1, formatter1);
        System.out.println(parse);
    }


    //获取指定小时的启示和结束时间
    public Date getHourStartTime(int hour){
        LocalDate now = LocalDate.now();
        LocalDateTime hourStartTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), hour, 0, 0);
        return covertLocalDateTime(hourStartTime);
    }

    public Date getHourEndTime(int hour){
        LocalDate now = LocalDate.now();
        LocalDateTime hourEndTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), hour, 59, 59);
        return covertLocalDateTime(hourEndTime);
    }

    public Date covertLocalDateTime(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);

        return Date.from(zdt.toInstant());
    }

    /**
     * 时区操作
     */
    @Test
    public void test7() throws ParseException {
        //查看支持的时区
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        zoneIds.forEach(System.out::println);
        System.out.println("--------------------------------------------");

        //指定时区
        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Fortaleza"));
        System.out.println(now);

        LocalDateTime now1 = LocalDateTime.now();
        ZonedDateTime zonedDateTime = now1.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime);
        Date date = Date.from(zonedDateTime.toInstant()); //ZoneDateTime 转换成Date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = simpleDateFormat.format(date);
        Date date1 = simpleDateFormat.parse(s);
        System.out.println(s);
    }

    @Test
    public void test8() throws ParseException {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX);
        System.out.println(startTime);
        System.out.println(endTime);

        long epochSecond = startTime.toEpochSecond(ZoneOffset.of("+8"));
        long now = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        System.out.println(now);
        System.out.println(epochSecond);
    }

    @Test
    public void makeTime() {
        long startTime = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN)
                .toEpochSecond(ZoneOffset.of("+8"));
        long nowTime = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        String result = String.valueOf(Long.toHexString(nowTime - startTime));
        for (int i = 0; i < (6 - result.length()); i++) {
            result = "0" + result;
        }

        System.out.println(result.toUpperCase());
    }

    @Test
    public void test09(){
        LocalDate now = LocalDate.now();
        LocalDateTime endTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), LocalTime.now().getHour(), LocalTime.now().getMinute() - 5, LocalTime.now().getSecond());
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = endTime.atZone(zoneId);
        Date endDate = Date.from(zdt.toInstant());
        System.out.println(endDate);

        Date date = new Date();
        System.out.println(date);
        System.out.println(date.compareTo(endDate));
    }
}
