package com.skutil.base.convert;

import com.skutil.base.enums.DateUtilEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author zhan yan
 * @date 2022/12/30
 */
class DateUtilTest {

    private static final Logger LOG = LoggerFactory.getLogger(DateUtilTest.class);

    private ExecutorService pool;

    @BeforeEach
    public void init(){
        pool = Executors.newFixedThreadPool(5);
    }

    @Test
    void convert() {
        int i = 0;
        do{
            pool.execute(() -> {
                Assertions.assertDoesNotThrow(() -> {
                    DateUtil.convert(new Date(), DateUtilEnum.Y2S);
                    System.out.println(Thread.currentThread().getName());
                });
            });
            i++;
        }while (i < 30);

    }

    @Test
    void testGetTime(){

        Date nowTime = new Date();
        Date a = DateUtil.dateFormat(nowTime, DateUtilEnum.Y2S);
        String b = DateUtil.convert(nowTime, DateUtilEnum.Y2S);
        Assertions.assertEquals(b, DateUtil.convert(a, DateUtilEnum.Y2S));
        Assertions.assertEquals(DateUtil.convert(b, DateUtilEnum.Y2S), a);

        System.out.println("---->@DateUtil ^simple test^ is pass");
    }


    @Test
    void hasMatch() {
        final String date01 = "06月20日";
        final String date02 = "06-20";
        final String date03 = "2022年06月20日";
        final String date04 = "2022-06-20";
        final String date05 = "2022-06-20 01:19:05";
        assertFalse(DateUtil.hasMatchDefault(date01));
        assertFalse(DateUtil.hasMatchDefault(date03, DateUtilEnum.Y2D));
        assertTrue(DateUtil.hasMatchOne(date02));
        assertTrue(DateUtil.hasMatchOne(date05));
        assertTrue(DateUtil.hasMatchOne(date04));
        assertTrue(DateUtil.hasMatchDefault(date04, DateUtilEnum.Y2D));
    }
}