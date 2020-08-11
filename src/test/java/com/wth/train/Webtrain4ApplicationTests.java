package com.wth.train;


import com.wth.train.dao.ReservationMapper;
import com.wth.train.pojo.ReservableRoom;
import com.wth.train.pojo.SumBean;
import com.wth.train.pojo.TimeSumBean;
import com.wth.train.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

@SpringBootTest
class Webtrain4ApplicationTests {

    @Test
    void contextLoads() {

    }

//    @Autowired
//    private LoginMapper loginMapper;
//
//    @Test
//    public void myTest(){
//        Usr usr = loginMapper.queryUsr("1", "1");
//        System.out.println(usr);
//    }

//        @Autowired
//    private ReservationMapper reservationMapper;
//
//    @Test
//    public void myTest() throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        java.util.Date date1 = new java.util.Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date1);
//        Date sqlNow = new Date(date1.getTime());
//        System.out.println(sqlNow);
//        ReservableRoom reservableRoom = new ReservableRoom(sqlNow,1);
//        List<SumBean> sumBeans = reservationMapper.queryByReservableRoom(reservableRoom);
//        System.out.println(sumBeans);
//    }
//    @Test
//    public void  timeTest() throws ParseException {
//        List<String> list = new ArrayList<>();
//        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
//        Date startTime =df.parse("09:00");
//        String format = df.format(startTime);
//        list.add(format);
//        Date endTime =df.parse("20:00");
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(startTime);
//        while (true) {
//            calendar.add(Calendar.MINUTE, 30);
//            String format1 = df.format(calendar.getTime());
//            list.add(format1);
//            if (format1.equals("20:00")) {
//                break;
//            }
//        }
//        System.out.println(list);
//    }

//    @Test
//    public   void  aTest() throws ParseException {
//        List<Time> dateList= new ArrayList<>();
//        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
//        java.util.Date startTime =df.parse("09:00");
//        dateList.add(new Time(startTime.getTime()));
//        java.util.Date endTime =df.parse("20:00");
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(startTime);
//        while (true) {
//            calendar.add(Calendar.MINUTE, 30);
////            String format1 = df.format(calendar.getTime());
//            java.util.Date time = calendar.getTime();
//            Time time1 = new Time(time.getTime());
//            dateList.add(time1);
//            if (df.format(time).equals("20:00")) {
//                break;
//            }
//        }
//    }

//    @Autowired
//    private ReservationService reservationService;
//    @Test
//    public void  aTest() throws ParseException {
//        TimeSumBean timeSumBean = reservationService.queryByReservableRoom("2020/07/29", 1);
//        System.out.println(timeSumBean);
//    }
}

