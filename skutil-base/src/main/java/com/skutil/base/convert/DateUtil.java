package com.skutil.base.convert;

import com.skutil.base.enums.DateUtilEnum;
import com.skutil.common.exception.support.MismatchException;
import com.skutil.common.exception.support.ParamFormatException;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  通过传入的日期格式使用工厂/枚举的方法获取对应的ThreadLocal容器
 * 注意：如果在运行时设置了ThreadLocal，那么需要在退出时remove()
 * @author zhan yan
 * @date 2022/11/18
 */
public class DateUtil {

    private static final ThreadLocal<SimpleDateFormat> TL_S_Y2S;
    private static final ThreadLocal<SimpleDateFormat> TL_S_Y2D;
    private static final ThreadLocal<SimpleDateFormat> TL_S_M2D;

    /*
       this {@link ThreadLocal} is a container to load {@link SimpleDateFormat}
     */
    static {
        TL_S_Y2S = ThreadLocal.withInitial(() -> new SimpleDateFormat(DateUtilEnum.Y2S.get()));
        TL_S_Y2D = ThreadLocal.withInitial(() -> new SimpleDateFormat(DateUtilEnum.Y2D.get()));
        TL_S_M2D = ThreadLocal.withInitial(() -> new SimpleDateFormat(DateUtilEnum.M2D.get()));
    }
    private DateUtil() {
    }




    /**
     * {@link String} converted to {@link Date} according to dateType
     *
     * @param obj      target str
     * @param dateType expect dateType
     * @return {@link Date}
     */
    public static Date convert(String obj, @NotNull DateUtilEnum dateType) {
        SimpleDateFormat sdf;
        sdf = checkAndGet(dateType);
        return convert(obj, sdf);
    }



    /**
     * {@link Date} converted to {@link  String} according to dateType.
     *
     * @param date     target date
     * @param dateType expect dateType
     * @return {@link String}
     */
    public static String convert(Date date, @NotNull DateUtilEnum dateType) {
        SimpleDateFormat sdf;
        sdf = checkAndGet(dateType);
        return convert(date, sdf);
    }

    /**
     * Get the current time, and format to date type
     * <tr>Flow: get now time -> parse to String -> format -> to Date</tr>
     */
    public static Date getDateTime(@NotNull DateUtilEnum dateType) {
        return dateFormat(new Date(), dateType);
    }

    /**
     * this method use default{ {@link DateUtilEnum}：<b>DateUtilEnum.Y2S</b>}
     */
    public static Date getDateTime() {
        return dateFormat(new Date(), DateUtilEnum.Y2S);
    }

    /**
     * check source string have match target date type
     * @param date source string
     * @param dateType target date type {@link DateUtilEnum}
     * @return true:match/false:mismatch
     */
    public static Boolean hasMatchDefault(String date, DateUtilEnum dateType){
        try {
            convert(date, checkAndGet(dateType));
            return true;
        }catch (ParamFormatException e){
            return false;
        }
    }

    /**
     * 检查是否匹配（yyyy-MM-dd HH:mm:ss/yyyy-MM-dd/MM-dd）中的其中一项
     */
    public static Boolean hasMatchOne(String date){
        boolean a = hasMatchDefault(date, DateUtilEnum.Y2S);
        boolean b = hasMatchDefault(date, DateUtilEnum.Y2D);
        boolean c = hasMatchDefault(date, DateUtilEnum.M2D);

        return a || b || c;
    }

    /**
     * use default date type（yyyy-MM-dd） to check date type
     */
    public static Boolean hasMatchDefault(String date){
        try {
            convert(date, checkAndGet(DateUtilEnum.Y2D));
            return true;
        }catch (ParamFormatException e){
            return false;
        }
    }



    /**
     * {@link Date} to {@link String}, use specified {@link SimpleDateFormat}
     * @return {@link String}
     */
    static String convert(Date date, SimpleDateFormat sdf) {
        return sdf.format(date);
    }

    /**
     * {@link String} to {@link Date} , use specified {@link SimpleDateFormat}
     * @return {@link String}
     */
    static Date convert(String obj, SimpleDateFormat sdf) {
        try {
            return sdf.parse(obj);
        } catch (ParseException e) {
            throw new ParamFormatException(e);
        }
    }

    /**
     * {@link String} to {@link Date} , use specified {@link SimpleDateFormat}
     * @return {@link String}
     */
    static Date dateFormat(Date date, DateUtilEnum type) {
        SimpleDateFormat sdf = getSdf(type);
        return convert(convert(date, sdf), sdf);
    }


    /**
     * Get the current time according to the format of dateType
     *
     * @param dateType {@link DateUtilEnum}
     * @return {@link SimpleDateFormat}
     * @throws ParseException ex
     */
    static SimpleDateFormat getSdf(DateUtilEnum dateType) {
        SimpleDateFormat sdf;
        sdf = checkAndGet(dateType);
        return sdf;
    }




    /**
     * check input type, and search in Map
     *
     * @param dateType 格式类型
     * @return {@link SimpleDateFormat}
     */
    protected static SimpleDateFormat checkAndGet(DateUtilEnum dateType) {
        switch (dateType) {
            case Y2S:
                return TL_S_Y2S.get();

            case Y2D:
                return TL_S_Y2D.get();

            case M2D:
                return TL_S_M2D.get();

            default:
                throw new MismatchException("This date-type is not found");
        }
    }


    /**
     * provide an unuseful func that made ThreadLocalMap.Entry can be collect
     */
    @SuppressWarnings("unused")
    private static void reload() {
        TL_S_Y2S.remove();
        TL_S_Y2D.remove();
        TL_S_M2D.remove();
    }

}
