package com.wth.train.congfig;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//登録状態検証のインターセプター
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws ServletException, IOException {
        //sessionを取得する
        Object loginUser = request.getSession().getAttribute("usr");
        if(loginUser==null||loginUser.equals("")){
            request.setAttribute("error","登録してください");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }else {
            return true;
        }
    }
}
