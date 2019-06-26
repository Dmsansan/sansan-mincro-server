package server.ribbon.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/route")
public class RouteTest {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET, produces = "application/json")
    public String getUserInfo(){
        String url = "http://PRODUCE-SERVER/user/getUserInfo";
        String userInfo = restTemplate.getForObject(url, String.class);
        return userInfo;
    }
}