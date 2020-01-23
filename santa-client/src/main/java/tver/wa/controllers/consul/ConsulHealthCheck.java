package tver.wa.controllers.consul;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuator/health")
public class ConsulHealthCheck {

    @GetMapping
    public String check() {
        return "Ok";
    }

}
