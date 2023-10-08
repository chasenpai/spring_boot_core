package hello.container;

import hello.servlet.HelloServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;

public class AppInitV1Servlet implements AppInit {

    @Override
    public void onStartup(ServletContext servletContext) {

        System.out.println("AppInitV1Servlet.onStartup");

        //프로그래밍 방식으로 서블릿을 서블릿 컨테이너에 직접 등록 - 유연성 제공
        ServletRegistration.Dynamic helloServlet = servletContext
                .addServlet("helloServlet", new HelloServlet());

        helloServlet.addMapping("/hello-servlet");
    }

}
