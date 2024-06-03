package server;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.apache.commons.beanutils.BeanUtils;

@RestController
public class UnsafeBeanPopulation {

    static class SomeBean {
        String a;

        public SomeBean() {
            a = "";
        }

        String getA() { return a; }
        void setA(String a) { this.a = a; }
    }

    @GetMapping("/")
    void method(@RequestHeader("X-Api-Version") String apiVersion) { // <-- Taint source
        SomeBean s = new SomeBean();
        String versionToSet = apiVersion; // <-- Taint propagated from source
        BeanUtils.setPropertyValue(s, "a", versionToSet); // <-- Taint sink
    }
}
