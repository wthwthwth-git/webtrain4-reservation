package com.wth.train.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.sql.Date;


/*
日付とルーム対応の実現クラス、
table：reservable_room
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ReservableRoom {
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date reservedDate;
    private Integer roomId;
}
