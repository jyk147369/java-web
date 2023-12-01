package com.example.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;

@WebServlet("/login")      // select 활용 -> JDBCTest.java 참고
public class Login extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // HttpServletRequest : 클라이언트가 요청한 모든 것을 저장

        String uid = request.getParameter("uid");       // getParameter() 메소드로 클라이언트가 보내준 데이터를 받아온다.
        String upw = request.getParameter("upw");

        Connection conn = null; // DB 서버와 연결하는 객체
        Statement stmt = null; // SQL을 실행하는 객체
        ResultSet rs = null; // 실행 결과를 받아오는 객체
        try {
            String url = "jdbc:mysql://10.10.10.111:3306/test";
            String id = "kjy";
            String pw = "qwer1234";
            conn = DriverManager.getConnection(url, id, pw);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM test.user WHERE uid='"+uid+"'AND upw='"+upw+"'";
            rs = stmt.executeQuery(sql);        // ResultSet 객체를 반환. sql문을 실행해서 표형태의 데이터를 받는다

            // response 변수에 클라이언트에게 HTTP 프로토콜로 응답할 모든게 저장되어 있음
            // response 변수에 저장된 HttpServletResponse 객체에
            // getWriter() 메소드를 통해서 outputstream을 받아오고
            // 해당 스트림을 통해서 데이터를 출력하면
            // 클라이언트 웹 브라우저에 내용이 출력된다.

            PrintWriter out = response.getWriter();

            /*while(rs.next())
            {
                out.println(rs.getString("uid"));
                out.println("login success!!!");
            }*/

            if(rs.next()) {
                out.println(rs.getString("uid"));
                out.println("login success!!!");
            } else {
                out.println("login failed!!!");
            };


        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }



        System.out.println("클라이언트가 웹 브라우저로 클래스 파일을 실행");
    }
}
