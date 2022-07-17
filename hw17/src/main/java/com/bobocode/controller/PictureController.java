package com.bobocode.controller;

import com.bobocode.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pictures")
public class PictureController {
    private final PictureService pictureService;

    @GetMapping("/{sol}/largest")
    public ResponseEntity getLargestPicture(@PathVariable("sol") Integer sol) {
        String largestPictureURI = pictureService.getLargestPictureURI(sol);
        return ResponseEntity
                .status(HttpStatus.PERMANENT_REDIRECT)
                .location(URI.create(largestPictureURI))
                .build();
    }
}
