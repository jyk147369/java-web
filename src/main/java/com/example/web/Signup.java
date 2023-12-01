package com.example.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/signup")      // insert 활용 -> JDBCTest2.java 참고
public class Signup extends HttpServlet {
    // 웹에 주소를 치는 방식 -> get
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 클라이언트의 요청이 모두 request 변수에 들어있다(객체 HttpServelet)

        // request.getParameter uid, upw는 클라이언트가 주소창에 적은 값
        String uid = request.getParameter("uid");       // getParameter() 메소드로 클라이언트가 보내준 데이터를 받아온다.
        String upw = request.getParameter("upw");       // getParameter() 메소드로 클라이언트가 보내준 데이터를 받아온다.


        Connection conn = null; // DB 서버와 연결하는 객체
        Statement stmt = null; // SQL을 실행하는 객체
        // ResultSet rs = null; // 실행 결과를 받아오는 객체
        try {
            String url = "jdbc:mysql://10.10.10.111:3306/test";
            String id = "kjy";
            String pw = "qwer1234";
            conn = DriverManager.getConnection(url, id, pw);    // DB서버와 연결
            stmt = conn.createStatement();      // SQL 실행


            String sql = "INSERT INTO test.user (uid, upw) VALUES ('"+ uid +"','"+ upw +"')";
            // SQL에서 만든 명령문 그대로 가져오면 됨
            // UPDATE test.student SET sage = 25 WHERE sname = '이름';
            Integer result = stmt.executeUpdate(sql);   // 실행 결과를  result에 저장
                                                        // insert의 결과는 0 or 1
                                                        // 0이면 실패, 1이면 성공

            PrintWriter out = response.getWriter();     // PrintWriter : 웹 브라우저에 출력하기 위한 객체

            if (result > 0) {
                System.out.println("정상적으로 insert 됐다");
                out.println("sign-up success!!!");
            } else {
                System.out.println("insert 안됨");
                out.println("sign-up failed!!!");
            }


        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
}
