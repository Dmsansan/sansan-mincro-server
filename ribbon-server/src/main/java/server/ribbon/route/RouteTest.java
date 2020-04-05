package server.ribbon.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/route")
public class RouteTest {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserInfo(){
        String url = "http://produce-server/user/getUserInfo";
        String userInfo = restTemplate.getForObject(url, String.class);
        return userInfo;
    }
}
