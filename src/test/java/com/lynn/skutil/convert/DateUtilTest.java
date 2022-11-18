package com.lynn.skutil.convert;

import com.lynn.skutil.enums.DateUtilEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author zhan yan
 * @date 2022/11/18
 */
@DisplayName("DateUtilTest")
class DateUtilTest {

    private static final Logger log = LoggerFactory.getLogger(DateUtilTest.class);

    @Test
    void convert(){
        final String failMsg = "Fail test for DateUtilTest#convert(String, String)!";
        final String strOne = "2018-11-25 17:57:40";
        final String strTwo = "2022-06-20";
        final String strThree = "06-20";

        final Date dateOne;
        final Date dateTwo;
        final Date dateThree;


        try {
            assertEquals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strOne),
                    dateOne = DateUtil.convert(strOne, DateUtilEnum.Y2S));
            assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse(strTwo),
                    dateTwo = DateUtil.convert(strTwo, DateUtilEnum.Y2D));
            assertEquals(new SimpleDateFormat("MM-dd").parse(strThree),
                    dateThree = DateUtil.convert(strThree, DateUtilEnum.M2D));
            System.out.println("DateUtilTest#convert(String, DateUtilEnum) True-Test is passed");

            //  the next True-Test  is #convert(Date, DateUtilEnum)

            assertEquals(strOne,
                    DateUtil.convert(dateOne, DateUtilEnum.Y2S));
            assertEquals(strTwo,
                    DateUtil.convert(dateTwo, DateUtilEnum.Y2D));
            assertEquals(strThree,
                    DateUtil.convert(dateThree, DateUtilEnum.M2D));
            System.out.println("DateUtilTest#convert(Date, DateUtilEnum) True-Test is passed");
        } catch (Exception e) {
            log.error(e, e::toString);
        }

        //  Error test
        final String exDate = "111111";
        assertThrows(ParseException.class, () -> {
            Date date = DateUtil.convert(exDate, DateUtilEnum.Y2S);
            System.out.println("error format is : " + new SimpleDateFormat(DateUtilEnum.Y2S.get()).format(date));
        }, failMsg);
        assertThrows(ParseException.class, () -> {
            Date date = DateUtil.convert(exDate, DateUtilEnum.Y2D);
            System.out.println("error format is : " + new SimpleDateFormat(DateUtilEnum.Y2D.get()).format(date));
        }, failMsg);
        assertThrows(ParseException.class, () -> {
            Date date = DateUtil.convert(exDate, DateUtilEnum.M2D);
            System.out.println("error format is : " + new SimpleDateFormat(DateUtilEnum.M2D.get()).format(date));
        }, failMsg);
        System.out.println("DateUtilTest#convert(String, DateUtilEnum) Fail-Test is passed");

        //  the next test is #convert(Date, DateUtilEnum)
        assertThrows(ParseException.class, () -> {
            Date date = DateUtil.convert(exDate, DateUtilEnum.Y2S);
            System.out.println("error format is : " + new SimpleDateFormat(DateUtilEnum.Y2S.get()).format(date));
        }, failMsg);
        assertThrows(ParseException.class, () -> {
            Date date = DateUtil.convert(exDate, DateUtilEnum.Y2D);
            System.out.println("error format is : " + new SimpleDateFormat(DateUtilEnum.Y2D.get()).format(date));
        }, failMsg);
        assertThrows(ParseException.class, () -> {
            Date date = DateUtil.convert(exDate, DateUtilEnum.M2D);
            System.out.println("error format is : " + new SimpleDateFormat(DateUtilEnum.M2D.get()).format(date));
        }, failMsg);
        System.out.println("DateUtilTest#convert(Date, DateUtilEnum) Fail-Test is passed");
    }

    @Test
    void checkAndGet() {
        try {
            assertEquals(DateUtilEnum.Y2S.get(), DateUtil.checkAndGet(DateUtilEnum.Y2S).get().toPattern());
            assertEquals(DateUtilEnum.Y2D.get(), DateUtil.checkAndGet(DateUtilEnum.Y2D).get().toPattern());
            assertEquals(DateUtilEnum.M2D.get(), DateUtil.checkAndGet(DateUtilEnum.M2D).get().toPattern());
        } catch (Exception e) {
            log.error(e, e::toString);
        }
    }
}