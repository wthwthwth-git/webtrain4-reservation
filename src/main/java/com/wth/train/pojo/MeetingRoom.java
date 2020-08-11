package com.wth.train.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/*
会議室の実現クラス、
table：meeting_room
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MeetingRoom {
    private Integer roomId;
    private String roomName;
}
