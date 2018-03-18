package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Video;
import com.colorofhope.ayitili.repository.VideoRepository;
import com.colorofhope.ayitili.web.ModelAttributeControllerAdvice;
import com.restfb.*;
import com.restfb.types.VideoList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/video")
public class VideoController extends DefaultController<VideoRepository, Video> {
  public VideoController(VideoRepository videoRepository) {
    this.repository = videoRepository;
  }

  @Value("${facebook.token}")
  String facebookToken;

  @Autowired
  ModelAttributeControllerAdvice modelAttributeControllerAdvice;

  @RequestMapping(method = RequestMethod.GET, path = "/facebook")
  public List<VideoList> getFacebookVideos() {
    FacebookClient facebookClient = new DefaultFacebookClient(facebookToken, Version.VERSION_2_9);
        Connection<VideoList> fbVideos =
            facebookClient.fetchConnection(
                    modelAttributeControllerAdvice.pageVideoPath(), VideoList.class, Parameter.with("limit", 100));
        return fbVideos.getData();
  }
}
