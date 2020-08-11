package com.wth.train.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import java.util.Date;


/*
予約メッセージの実現クラス、
table：reservation
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Reservation {
    private Integer reservationId;
    @DateTimeFormat(pattern = "HH:mm")
    private Date startTime;
    @DateTimeFormat(pattern = "HH:mm")
    private Date endTime;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date reservedDate;
    private Integer roomId;
    private String userId;
}