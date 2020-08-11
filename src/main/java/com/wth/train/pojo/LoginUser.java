package com.wth.train.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


/*
 * ユーザー登録メッセージの実現クラス
 * ログインのデータを取得する
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LoginUser {
    private String userId;
    private String password;
}
