package com.aigleinfo.siksepounoutout.rest.controllers;

import com.aigleinfo.siksepounoutout.model.Video;
import com.aigleinfo.siksepounoutout.repository.VideoRepository;
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
@Api
public class VideoController {
  @Autowired VideoRepository videoRepository;

  @Value("${facebook.token}")
  String facebookToken;

  @RequestMapping(method = RequestMethod.GET, path = "")
  public List<Video> getVideos() {
    return videoRepository.findAll();
  }

  @RequestMapping(method = RequestMethod.POST, path = "")
  public Video createVideo(@RequestBody Video video) {
    return videoRepository.save(video);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/{videoID}")
  public Video getVideoById(@PathVariable String videoID) {
    return videoRepository.findOne(videoID);
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/{videoID}")
  public Video updateVideo(@PathVariable String videoID, @RequestBody Video video) {
    video.id = videoID;
    return videoRepository.save(video);
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/{videoID}")
  public void deleteVideoById(@PathVariable String videoID) {
    videoRepository.delete(videoID);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/facebook")
  public List<VideoList> getFacebookVideos() {
    FacebookClient facebookClient = new DefaultFacebookClient(facebookToken, Version.VERSION_2_9);
    Connection<VideoList> fbVideos =
        facebookClient.fetchConnection(
            "Aigleinfo/videos", VideoList.class, Parameter.with("limit", 100));
    return fbVideos.getData();
  }
}
