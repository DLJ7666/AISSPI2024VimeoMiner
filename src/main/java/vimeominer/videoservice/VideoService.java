package vimeominer.videoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import imports.model.Video;

import java.util.ArrayList;

@Service
public class VideoService {

    @Autowired
    RestTemplate restTemplate;

    public Video creaCanal(String channelId, String nombre, String descripcion, String subidoEn) {
        Video video = null;
        String uri = String.format("http://localhost:42000/api/videominer/channels/%s/videos",
                channelId);
        Video datos = new Video(nombre, descripcion, subidoEn);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Video> request = new HttpEntity<>(datos, headers);
        ResponseEntity<Video> response = restTemplate.exchange(uri, HttpMethod.POST, request,
                Video.class);
        if (response.getStatusCode().value()==201) {
            video = response.getBody();
        }
        return video;
    }

}
