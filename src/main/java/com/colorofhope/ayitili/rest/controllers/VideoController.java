package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Sponsor;
import com.colorofhope.ayitili.model.Video;
import com.colorofhope.ayitili.repository.SponsorRepository;
import com.colorofhope.ayitili.repository.VideoRepository;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.VideoList;
import io.swagger.annotations.Api;
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

  @RequestMapping(method = RequestMethod.GET, path = "/facebook")
  public List<VideoList> getFacebookVideos() {
    FacebookClient facebookClient = new DefaultFacebookClient(facebookToken, Version.VERSION_2_9);
    Connection<VideoList> fbVideos =
        facebookClient.fetchConnection(
            "Aigleinfo/videos", VideoList.class, Parameter.with("limit", 100));
    return fbVideos.getData();
  }
}
