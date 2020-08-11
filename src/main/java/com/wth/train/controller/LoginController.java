package com.wth.train.controller;

import com.wth.train.pojo.LoginUser;
import com.wth.train.pojo.Usr;
import com.wth.train.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;


/*
LoginController　クラス
view取得したデータLoginServiceに転送
LoginService取得した結果処理し、viewに転送
画面遷移機能
*/
@Controller
public class LoginController {
    @Autowired
    LoginService loginService;
    @RequestMapping("/login")
    public String login(LoginUser loginUser, Model model, HttpSession session){
        //ユーザーメッセージを取得する
        Usr usr = loginService.queryUsr(loginUser.getUserId(), loginUser.getPassword());
        if(usr==null||usr.equals("")){
            model.addAttribute("error","ユーザー名、又はパスワードが間違いました！");
            return "login";
        }else {
            //登録メッセージをsessionに保存する
            session.setAttribute("usr",usr);
            return "redirect:/guide";
        }

    }


    /*
    loginに遷移
    sessionの登録メッセージをクリーン
    */
    @RequestMapping("/logout")
    public String tologin(HttpSession session){
        session.removeAttribute("usr");
        return "login";
    }
}
