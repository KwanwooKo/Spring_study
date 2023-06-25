package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // ^ + o 누르면 자동완성 가능
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        // request, response 객체는 계속 추가생성 <-> servlet 컨테이너를 하나만 이용, 여기서 servlet 컨테이너는 HelloServlet
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // ? 가 query parameter 역할, localhost:8080/hello?username=ko
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // servlet이 애초에 logic 이외의 모든 것을 관리하니까
        response.setContentType("test/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);
    }
}
