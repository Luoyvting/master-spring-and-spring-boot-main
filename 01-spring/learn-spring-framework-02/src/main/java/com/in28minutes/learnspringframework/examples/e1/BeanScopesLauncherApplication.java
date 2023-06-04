package com.in28minutes.learnspringframework.examples.e1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
class NormalClass {

}


@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class PrototypeClass {

}


@Configuration
@ComponentScan
public class BeanScopesLauncherApplication {

    public static void main(String[] args) {

        try (var context =
                     new AnnotationConfigApplicationContext
                             (BeanScopesLauncherApplication.class)) {

            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));

            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));

            ((MyService) context.getBean("myService")).doSomething();
        }
    }
}

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class MyPrototypeBean {
    // 这里可以定义Prototype类型bean的属性和方法
}

@Service
class MyService {
    @Autowired
    private MyPrototypeBean myPrototypeBean;

    public void doSomething() {
        // 在需要使用MyPrototypeBean的地方直接使用即可
//		MyPrototypeBean prototypeInstance1 = myPrototypeBean;
//		MyPrototypeBean prototypeInstance2 = myPrototypeBean;
        try (var context =
                     new AnnotationConfigApplicationContext
                             (BeanScopesLauncherApplication.class)) {
            MyPrototypeBean prototypeInstance1 = ((MyPrototypeBean) context.getBean("myPrototypeBean"));
            MyPrototypeBean prototypeInstance2 = ((MyPrototypeBean) context.getBean("myPrototypeBean"));
            System.out.println(prototypeInstance1);
            System.out.println(prototypeInstance2);
        }
    }
}
