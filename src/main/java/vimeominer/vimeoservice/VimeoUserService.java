package vimeominer.vimeoservice;


import org.springframework.stereotype.Service;
import vimeominer.model.VimeoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VimeoUserService {

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "87879038305545edc0a789f4d4733f6b";

    public VimeoUser getVimeoUser(String id) {
        VimeoUser res = null;
        String uri = String.format("https://api.vimeo.com/users/%s", id);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<VimeoUser> request = new HttpEntity<>(null, headers);

        ResponseEntity<VimeoUser> response = restTemplate.exchange(uri, HttpMethod.GET, request, VimeoUser.class);

        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<VimeoUser> getVimeoUsers() {
        List<VimeoUser> res = new ArrayList<>();
        String uri = "https://api.vimeo.com/users";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<VimeoUser> request = new HttpEntity<>(null, headers);
        ResponseEntity<VimeoUser> response = restTemplate.exchange(uri, HttpMethod.GET, request, VimeoUser.class);
        if (response.getBody() != null) {
            res.add(response.getBody());
        }
        return res;
    }
}
