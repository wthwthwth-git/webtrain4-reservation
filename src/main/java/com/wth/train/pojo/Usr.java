package com.wth.train.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


/*
ユーザーメッセージの実現クラス、
table：usr
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Usr {
    private String userId;
    private String firstName;
    private String lastName;
    private String password;
    private String roleName;
}
