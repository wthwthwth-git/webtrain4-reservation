package com.wth.train.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


/*
合わせ情報の実現クラス
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SumBean {
    private Usr usr;
    private ReservableRoom reservableRoom;
    private Reservation reservation;
    private MeetingRoom meetingRoom;
}
