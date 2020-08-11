package com.wth.train.service;

import com.wth.train.dao.ReservationMapper;
import com.wth.train.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
ReservationService実現クラス
*/
@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    private ReservationMapper reservationMapper;
    /*
    * 日付である会議室のメッセージを取得
    * */
    @Override
    public DateRoom getMeetingRoom(int dateChange) {
        List<MeetingRoom> meetingRoom;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date1 = new java.util.Date();
        //Calendarオブジェクト取得
        Calendar calendar = Calendar.getInstance();
        //今の日付を設定
        calendar.setTime(date1);
        if (dateChange == 0) {
            //sql　Dateに転換
            Date date2 = new Date(date1.getTime());
            //ある会議室のメッセージを取得
            meetingRoom = reservationMapper.getMeetingRoom(date2);
            //取得したメッセージをDateroomで転送
            DateRoom dateRoom = new DateRoom(sdf.format(date1),meetingRoom);
            return dateRoom;
        }else {
            DateRoom dateRoom = new DateRoom();
            //staticのchangDateを日付が記録する
            DateRoom.changDate=dateChange+DateRoom.changDate;
            //changDateで注入 日付変化
            calendar.add(Calendar.DATE, DateRoom.changDate);
            //calendarから日付を取得
            java.util.Date newDate = calendar.getTime();
            //sql　Dateに転換
            Date date3 = new Date(newDate.getTime());
            //ある会議室のメッセージを取得
            meetingRoom = reservationMapper.getMeetingRoom(date3);
            //取得したメッセージをDateroomで転送
            dateRoom.setRoomDate(sdf.format(newDate));
            dateRoom.setRoomList(meetingRoom);
            return dateRoom;
        }
    }

    /*
    * 会議室のidと日付で全部の予約メッセージを取得する
    * 重複排除した可予約時間帯のlistを転送する
    * */
    @Override
    public TimeSumBean queryByReservableRoom(String reservedDate,int roomId) throws ParseException {
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");
        //StringのパラメータがDateに転換
        java.util.Date parse = df1.parse(reservedDate);
        ReservableRoom reservableRoom = new ReservableRoom(new Date(parse.getTime()),roomId);
        //保存したreservableRoomをパラメータとして転送、listのメッセージを取得
        List<SumBean> sumBeanList = reservationMapper.queryByReservableRoom(reservableRoom);
        //今日の日付を取得する
        java.util.Date todayDate = new java.util.Date();
        Time nowTime = new Time(todayDate.getTime());
        String today= df1.format(todayDate);
        //roomnameを取得
        String roomName = reservationMapper.queryRoomName(roomId);
        List<Time> startTimeList= new ArrayList<>();
        List<Time> endTimeList= new ArrayList<>();
        List<String> start = new ArrayList<>();
        List<String> end = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        //はじめ時間が設定
        java.util.Date startTime =df.parse("09:00");
        //はじめ時間が保存
        startTimeList.add(new Time(startTime.getTime()));
        //Calendarオブジェクト取得
        Calendar calendar = Calendar.getInstance();
        //はじめ時間が注入
        calendar.setTime(startTime);
        //30分分け時間listが取得
        while (true) {
            calendar.add(Calendar.MINUTE, 30);
            java.util.Date time = calendar.getTime();
            Time time1 = new Time(time.getTime());
            startTimeList.add(time1);
            if (df.format(time).equals("20:00")) {
                break;
            }
        }
        String formatNow = df.format(nowTime);
        for (int i = startTimeList.size()-1; i >= 0; i--) {
            if (today.equals(reservedDate) && formatNow.compareTo(df.format(startTimeList.get(i))) >= 0) {
                startTimeList.remove(startTimeList.get(i));
                if(startTimeList.size() == 1){
                    startTimeList.add(new Time(df.parse("00:00").getTime()));
                    startTimeList.remove(startTimeList.get(0));
                }
                if (startTimeList.size() == 0) {
                    startTimeList.add(new Time(df.parse("00:00").getTime()));
                }
            }
        }
        endTimeList.addAll(startTimeList);

        //予約がある時間帯を判断
        if(sumBeanList.size()!=0) {
            for (int i = startTimeList.size()-1; i >= 0; i--) {
                for (SumBean sumBean : sumBeanList) {
                    if (startTimeList.get(i).after(sumBean.getReservation().getStartTime()) && startTimeList.get(i).before(sumBean.getReservation().getEndTime())||startTimeList.get(i).equals(sumBean.getReservation().getStartTime())) {
                        //ある時間帯削除
                        startTimeList.remove(startTimeList.get(i));
                        break;
                    }
                }
            }

            for (int i = endTimeList.size()-1; i >= 0; i--) {
                for (SumBean sumBean : sumBeanList) {
                    if (endTimeList.get(i).after(sumBean.getReservation().getStartTime()) && endTimeList.get(i).before(sumBean.getReservation().getEndTime())||endTimeList.get(i).before(startTimeList.get(0))||endTimeList.get(i).equals(sumBean.getReservation().getEndTime())) {
                        //ある時間帯削除
                        endTimeList.remove(endTimeList.get(i));
                        break;
                    }
                }
            }
            if(endTimeList.size()==0){
                endTimeList.add(new Time(df.parse("00:00").getTime()));
            }
            if(startTimeList.size()==1){
                startTimeList.add(new Time(df.parse("00:00").getTime()));
                startTimeList.remove(startTimeList.get(0));
            }

        }
        if (startTimeList.size()>1){
            startTimeList.remove(startTimeList.get(startTimeList.size()-1));
        }
        if (startTimeList.size()!=0){
            //Stringに転換、保存する
            for (Time time:startTimeList){
                String timeString = df.format(time);
                start.add(timeString);
            }
        }
        if(endTimeList.size()!=0){
            for (Time time:endTimeList){
                String timeString = df.format(time);
                end.add(timeString);
            }
        }
        //TimeSumBeanに保存して、リターンする
        return new TimeSumBean(sumBeanList,start,end,roomName,reservedDate,roomId);
    }

    //reservationIdで予約のデータを削除する
    @Override
    public int deleteById(int reservationId) {
        int i = reservationMapper.deleteById(reservationId);
        return i;
    }

    //予約メッセージを追加する
    @Override
    public int addReservation(Reservation reservation) {
        int i = reservationMapper.addReservation(reservation);
        return i;
    }
}
