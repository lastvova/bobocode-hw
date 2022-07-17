package com.bobocode.service;

import com.bobocode.entity.Photo;
import com.bobocode.entity.Photos;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.AbstractMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PictureService {

    private final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=%s&api_key=DEMO_KEY";
    private final RestTemplate restTemplate;

    @Cacheable("largestPicture")
    public String getLargestPictureURI(int sol) {
        var url = URI.create(String.format(BASE_URL, sol));

        Photos photos = restTemplate.getForObject(url, Photos.class);
        return photos.photos().parallelStream()
                .map(Photo::img_src)
                .map(uri -> new AbstractMap.SimpleEntry<>(uri, getPictureLength(uri)))
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");

    }

    private Long getPictureLength(String uri) {
        URI location = restTemplate.headForHeaders(uri)
                .getLocation();
        return restTemplate.headForHeaders(location)
                .getContentLength();
    }

}
