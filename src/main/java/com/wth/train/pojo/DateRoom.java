package com.wth.train.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

/*
* 予約日付とルームリストの実現クラス
* 日付を変わる機能ためのstatic　changDate保存する
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class DateRoom {
    private String roomDate;
    private List<MeetingRoom> roomList;
    public static int changDate;
}
