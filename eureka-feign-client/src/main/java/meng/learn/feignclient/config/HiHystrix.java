package meng.learn.feignclient.config;

import org.springframework.stereotype.Component;

@Component
public class HiHystrix implements EurekaClientFeign{
    @Override
    public String sayHiFromClientEureka(String name) {
        return "Sorry "+ name+", service Error!";
    }
}
