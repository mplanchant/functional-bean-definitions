package uk.co.loggiccache;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

import java.util.function.Function;

@SpringBootConfiguration
public class FunctionalBeanDefinitionsApplication
        implements ApplicationContextInitializer<GenericApplicationContext> {

    public static void main(String[] args) {
        FunctionalSpringApplication.run(FunctionalBeanDefinitionsApplication.class, args);
    }

    public Function<String, String> uppercase() {
        return String::toUpperCase;
    }

    @Override
    public void initialize(GenericApplicationContext context) {
        context.registerBean("upper", FunctionRegistration.class,
                () -> new FunctionRegistration<>(uppercase())
                        .type(FunctionType.from(String.class).to(String.class))
        );
    }

}