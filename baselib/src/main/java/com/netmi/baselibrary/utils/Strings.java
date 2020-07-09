package com.netmi.baselibrary.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * 类描述：字符串操作工具包
 * 创建人：Simple
 * 创建时间：2017/9/5 14:51
 * 修改备注：
 */
public class Strings {
    private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

//    private final static Pattern emailer = Pattern.compile("^([a-z0-9A-Z]+[-|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$");

    private final static Pattern phone = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$");

    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(CharSequence input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断给定列表是否为null或者size为0，返回true
     */
    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    // 判断是否符合身份证号码的规范
    public static boolean isIDCard(String IDCard) {
        if (!isEmpty(IDCard)) {
            String IDCardRegex = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x|Y|y)$)";
            return IDCard.matches(IDCardRegex);
        }
        return false;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     */
    public static boolean isEmail(CharSequence email) {
        if (isEmpty(email))
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 判断是不是一个合法的手机号码
     */
    public static boolean isPhone(CharSequence phoneNum) {
        if (isEmpty(phoneNum))
            return false;
        return phone.matcher(phoneNum).matches();
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * String转long
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * String转double
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception e) {
        }
        return 0D;
    }

    /**
     * String转float
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static float toFloat(String obj) {
        try {
            return Float.parseFloat(obj);
        } catch (Exception e) {
        }
        return 0f;
    }


    /**
     * String转Money
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static String twoDecimal(String obj) {
        try {
            return twoDecimal(Double.parseDouble(obj));
        } catch (Exception e) {
        }
        return twoDecimal(0D);
    }

    /**
     * Double 数据保留两位小数
     *
     * @param source
     * @return
     */
    public static String twoDecimal(double source) {
        try {
            if (source - 0 == 0) {
                return "0";
            }
            String p = String.format("%.2f", source);
            return p.replace(".00", "");
        } catch (Exception e) {
            return "0";
        }
    }


    /**
     * 字符串转布尔
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 判断一个字符串是不是数字
     */
    public static boolean isNumber(CharSequence str) {
        try {
            Double.parseDouble(str.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * byte[]数组转换为16进制的字符串。
     *
     * @param data 要转换的字节数组。
     * @return 转换后的结果。
     */
    public static final String byteArrayToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.getDefault());
    }

    /**
     * 16进制表示的字符串转换为字节数组。
     *
     * @param s 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] d = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
            d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return d;
    }

    /**
     * is url
     *
     * @param url
     * @return
     */
    public static boolean isUrl(String url) {
        if (Strings.isEmpty(url))
            return false;
        Pattern pattern = Pattern.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
        return pattern.matcher(url).matches();
    }

    /**
     * 判断密码是否由6位数数字和字母组成
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        boolean isSixNumber = false;
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        if (str.length() >= 6) {
            isSixNumber = true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isSixNumber && isDigit && isLetter && str.matches(regex);
        return isRight;
    }

    public static String formatStr(String format, Object str) {
        if (TextUtils.isEmpty(format)) {
            return "";
        }
        if (str instanceof String) {
            if (TextUtils.isEmpty((String) str)) {
                return String.format(format, "");
            } else {
                return String.format(format, (String) str);
            }
        } else if (str instanceof Integer) {
            return String.format(format, (Integer) str);
        }
        return "";
    }

    /**
     * 仅限此项目使用，根据类型type获取单位
     */
    public static String getUnit(int itemType) {
        if (itemType == 1) {
            return "  /卷";
        } else {
            return "  /米";
        }
    }
    /**
     * 保留2小数
     */
    public static double roundTwoDecimals(double number) {
        BigDecimal bg = new BigDecimal(number);
        double result = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
//        double result;
//        int buff = (int) (number * 1000);
//        int lastNumber = buff % 10;
//        int intResult = buff / 10;
//        if (lastNumber >= 5) {
//            intResult++;
//        }
//        result = ((double) intResult) / 100;
        return result;
    }
}
