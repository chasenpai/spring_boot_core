package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.Set;

@HandlesTypes(AppInit.class) //애플리케이션 초기화 인터페이스를 지정
public class MyContainerInitV2 implements ServletContainerInitializer {

    /**
     * ServletContainerInitializer
     * - 서블릿 컨테이너를 초기화 하는 기능 제공
     * - 서블릿 컨테이너는 실행 시점에 초기화 메서드인 onStartup 을 호출한다
     * - 애플리케이션에 필요한 기능들을 초기화 하거나 등록할 수 있다
     *
     * Set<Class<>>
     * - 애플리케이션 초기화 인터페이스의 구현체들을 모두 찾아서 클래스 메타 정보를 넘김
     *
     * ServletContext
     * - 서블릿 컨테이너 자체의 기능을 제공
     *
     * 초기화 순서
     * - 서블릿 컨테이너 초기화 resources/META-INF/services/jakarta.servlet.ServletContainerInitializer
     * - 애플리케이션 초기화 @HandlesTypes(AppInit.class)
     *
     * 애플리케이션 초기화
     * - 애플리케이션 초기화는 서블릿 컨테이너에 상관없이 원하는 모양으로 인터페이스를 만들 수 있다
     * - 애플리케이션 초기화 코드가 서블릿 컨테이너에 대한 의존을 줄이고
     * - ServletContext 가 필요 없는 코드라면 의존을 완전히 제거할 수 있다
     */
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("MyContainerInitV2.onStartup");
        System.out.println("MyContainerInitV2 c = " + c);
        System.out.println("MyContainerInitV2 ctx = " + ctx);

        for (Class<?> appInitClass : c) {
            try {
                AppInit appInit = (AppInit) appInitClass.getDeclaredConstructor().newInstance();
                appInit.onStartup(ctx);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

}
