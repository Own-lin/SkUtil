package com.lynn.skutil.convert;

import com.lynn.skutil.enums.DateUtilEnum;
import org.springframework.lang.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * <p3>日期格式转换需求</p3>
 * <ul>
 *     <li>str -> date</li>
 *     <li>date -> str</li>
 *     <li>获取当前时间</li>
 * </ul>
 * 注意：如果在运行时设置了ThreadLocal，那么需要在退出时remove()
 * @author zhan yan
 * @date 2022/11/18
 */
public class DateUtil {



    private DateUtil() {
    }

    /**
     *  this {@link ThreadLocal} is a container to load {@link SimpleDateFormat}
     */


    static {
        TL_Y2S = ThreadLocal.withInitial(() -> new SimpleDateFormat(DateUtilEnum.Y2S.get()));
        TL_Y2D = ThreadLocal.withInitial(() -> new SimpleDateFormat(DateUtilEnum.Y2D.get()));
        TL_M2D = ThreadLocal.withInitial(() -> new SimpleDateFormat(DateUtilEnum.M2D.get()));
    }

    private static final ThreadLocal<SimpleDateFormat> TL_Y2S;
    private static final ThreadLocal<SimpleDateFormat> TL_Y2D;
    private static final ThreadLocal<SimpleDateFormat> TL_M2D;


    /**
     * {@link String} converted to {@link Date} according to dateType
     * @param obj target str
     * @param dateType expect dateType
     * @return {@link Date}
     * @throws ParseException ex
     */
    @NonNull
    public static Date convert(String obj, DateUtilEnum dateType) throws ParseException{
        SimpleDateFormat sdf;
        sdf = checkAndGet(dateType).get();
        return sdf.parse(obj);
    }


    /**
     * {@link Date} converted to {@link  String} according to dateType.
     * @param date target date
     * @param dateType expect dateType
     * @return {@link String}
     */
    @NonNull
    public static String convert(Date date, DateUtilEnum dateType) throws ParseException {
        SimpleDateFormat sdf;
        sdf = checkAndGet(dateType).get();
        return sdf.format(date);
    }

    public static Date convert(SimpleDateFormat sdf){
        return sdf.getCalendar().getTime();
    }

    /**
     * Get the current time according to the format of dateType
     * @param dateType {@link DateUtilEnum}
     * @return {@link SimpleDateFormat}
     * @throws ParseException ex
     */
    public static SimpleDateFormat getTime(DateUtilEnum dateType) throws ParseException {
        SimpleDateFormat sdf;
        sdf = checkAndGet(dateType).get();
        return sdf;
    }



    /**
     * check input type, and search in Map
     * @param dateType 格式类型
     * @return {@link SimpleDateFormat}
     */
    protected static ThreadLocal<SimpleDateFormat> checkAndGet(DateUtilEnum dateType) throws ParseException {
        switch (dateType){

            case Y2S : return TL_Y2S;

            case Y2D : return TL_Y2D;

            case M2D : return TL_M2D;

            default : throw new ParseException("This date-type is not found", -1);
        }
    }


    /**
     * provide an unuseful func that made ThreadLocalMap.Entry can be collect
     */
    @SuppressWarnings("unused")
    private static void reload(){
        TL_Y2S.remove();
        TL_Y2D.remove();
        TL_M2D.remove();
    }







}
