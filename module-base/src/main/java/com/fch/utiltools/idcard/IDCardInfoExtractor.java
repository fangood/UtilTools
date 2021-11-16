package com.fch.utiltools.idcard;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description $todo<从身份证号中提取所需 ( 如 ： 出生日期 、 性别 、 年龄 、 省份 ) 工具类>
 */
public class IDCardInfoExtractor {

    private String province;  //省份
    private String city;      //城市
    private String region;    //区县
    private int year;         //年份
    private int month;        //月份
    private int day;          //日期
    private String gender;    //性别
    private Date birthday;    //出生日期
    private int age;          //年龄

    private Map<String, String> cityCodeMap = new HashMap<String, String>();


    private IDCardValidator validator = null;

    /**
     * 通过构造方法初始化各个成员属性
     */
    public IDCardInfoExtractor(String idCard) {
        setData();
        parse(idCard);
    }

    public void setIdCard(String idCard) {
        parse(idCard);
    }

    private void parse(String idCard) {
        try {
            validator = new IDCardValidator();
            if (validator.isValidatedAllIdCard(idCard)) {
                if (idCard.length() == 15) {
                    idCard = validator.convertIdCarBy15bit(idCard);
                }
                // 获取省份
                String provinceId = idCard.substring(0, 2);
                Set<String> key = this.cityCodeMap.keySet();
                for (String id : key) {
                    if (id.equals(provinceId)) {
                        this.province = this.cityCodeMap.get(id);
                        break;
                    }
                }

                // 获取性别
                String id17 = idCard.substring(16, 17);
                if (Integer.parseInt(id17) % 2 != 0) {
                    this.gender = "男";
                } else {
                    this.gender = "女";
                }

                // 获取出生日期
                String birthday = idCard.substring(6, 14);
                Date birthDay = new SimpleDateFormat("yyyyMMdd").parse(birthday);
                this.birthday = birthDay;
                GregorianCalendar currentDay = new GregorianCalendar();
                currentDay.setTime(birthDay);
                this.year = currentDay.get(Calendar.YEAR);
                this.month = currentDay.get(Calendar.MONTH) + 1;
                this.day = currentDay.get(Calendar.DAY_OF_MONTH);

                //获取年龄
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
                String year = simpleDateFormat.format(new Date());
                this.age = Integer.parseInt(year) - this.year;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "省份：" + this.province + ",性别：" + this.gender + ",出生日期：" + this.birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void setData() {
        cityCodeMap.put("11", "北京");
        cityCodeMap.put("12", "天津");
        cityCodeMap.put("13", "河北");
        cityCodeMap.put("14", "山西");
        cityCodeMap.put("15", "内蒙古");
        cityCodeMap.put("21", "辽宁");
        cityCodeMap.put("22", "吉林");
        cityCodeMap.put("23", "黑龙江");
        cityCodeMap.put("31", "上海");
        cityCodeMap.put("32", "江苏");
        cityCodeMap.put("33", "浙江");
        cityCodeMap.put("34", "安徽");
        cityCodeMap.put("35", "福建");
        cityCodeMap.put("36", "江西");
        cityCodeMap.put("37", "山东");
        cityCodeMap.put("41", "河南");
        cityCodeMap.put("42", "湖北");
        cityCodeMap.put("43", "湖南");
        cityCodeMap.put("44", "广东");
        cityCodeMap.put("45", "广西");
        cityCodeMap.put("46", "海南");
        cityCodeMap.put("50", "重庆");
        cityCodeMap.put("51", "四川");
        cityCodeMap.put("52", "贵州");
        cityCodeMap.put("53", "云南");
        cityCodeMap.put("54", "西藏");
        cityCodeMap.put("61", "陕西");
        cityCodeMap.put("62", "甘肃");
        cityCodeMap.put("63", "青海");
        cityCodeMap.put("64", "宁夏");
        cityCodeMap.put("65", "新疆");
        cityCodeMap.put("71", "台湾");
        cityCodeMap.put("81", "香港");
        cityCodeMap.put("82", "澳门");
        cityCodeMap.put("91", "国外");
    }
}