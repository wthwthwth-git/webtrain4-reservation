package com.wth.train.controller;

import com.wth.train.pojo.DateRoom;
import com.wth.train.pojo.Reservation;
import com.wth.train.pojo.TimeSumBean;
import com.wth.train.pojo.Usr;
import com.wth.train.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*
ReservationController　クラス
view取得したデータReservationServiceに転送
ReservationService取得した結果処理し、viewに転送
画面遷移機能
*/
@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    /*
    * guide画面に遷移
    * 日付を取得して保存する
    * 予約可能の会議室を保存する
    */
    @RequestMapping("/guide")
    public String getMeetingRoom(HttpSession session,Model model, @RequestParam(value = "dateChange", defaultValue = "0", required = false) int dateChange){
        DateRoom dateRoom = reservationService.getMeetingRoom(dateChange);
        model.addAttribute("roomDate",dateRoom.getRoomDate());
        model.addAttribute("rooms",dateRoom.getRoomList());

        if(dateChange==0){ DateRoom.changDate=0; }
        session.setAttribute("changeDate",DateRoom.changDate);
        return "guide";
    }
    /*
    * reservable画面に遷移
    * 予約可能の時間帯を取得して保存する
    * 予約したメッセージを取得して保存する
    * 選択した会議室の名前と日付を保存する
    */
    @RequestMapping("/toreservable")
    public String toreservable(String date,int roomId,Model model) throws ParseException {

        TimeSumBean timeSumBean = reservationService.queryByReservableRoom(date, roomId);
        model.addAttribute("startTimeList",timeSumBean.getStartTimeList());
        model.addAttribute("endTimeList",timeSumBean.getEndTimeList());
        model.addAttribute("sumBeanList",timeSumBean.getSumBeanList());
        model.addAttribute("roomName",timeSumBean.getRoomName());
        model.addAttribute("reservedDate",timeSumBean.getReservedDate());
        model.addAttribute("roomId",timeSumBean.getRoomId());
        return "reservable";
    }
    /*　取消業務
     * reservable画面に遷移
     * 取得したreservationIdが転送する
     */
    @RequestMapping("/cancel")
    public String deleteReservation(int reservationId,String date,int roomId,Model model,HttpSession session){
        int i = reservationService.deleteById(reservationId);
        if(i==1){//成功メッセージを保存する
            model.addAttribute("msg","取消成功");
        }else{//失敗メッセージを保存する
            model.addAttribute("error","取消失敗");
            //error画面遷移
            return "error";
        }
        //reservable画面に遷移
        return "redirect:/toreservable?date="+date+"&roomId="+roomId;
    }


    /*予約業務
     * reservable画面に遷移
     * 取得したreservationがパラメータで転送する
     */
    @RequestMapping("/add")
    public String addReservation(Reservation reservation,Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String format = sdf.format(reservation.getReservedDate());
        int i = reservationService.addReservation(reservation);
        if (i==1){//成功メッセージを保存する
            model.addAttribute("msg","取消成功");
        }else{//失敗メッセージを保存する
            model.addAttribute("error","取消失敗");
            //error画面遷移
            return "error";
        }
        return "redirect:/toreservable?date="+format+"&roomId="+reservation.getRoomId();
    }

}


