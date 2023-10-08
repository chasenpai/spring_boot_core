package hello.container;

import jakarta.servlet.ServletContext;

public interface AppInit {

    //애플리케이션 초기화를 진행하려면 인터페이스가 꼭 필요(내용과 형식은 상관x)
    void onStartup(ServletContext servletContext);

}
