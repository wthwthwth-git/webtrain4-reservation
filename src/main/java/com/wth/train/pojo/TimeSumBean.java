package com.wth.train.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


/*
予約可能時間帯と合わせ予約メッセージの実現クラス
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TimeSumBean {
    private List<SumBean> sumBeanList;
    private List<String> startTimeList;
    private List<String> endTimeList;
    private String roomName;
    private String reservedDate;
    private Integer roomId;
}
