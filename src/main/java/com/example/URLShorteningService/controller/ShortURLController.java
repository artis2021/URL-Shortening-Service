package com.example.URLShorteningService.controller;

import com.example.URLShorteningService.entity.ShortUrl;
import com.example.URLShorteningService.entity.ShortUrlRequest;
import com.example.URLShorteningService.service.ShortURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpLogging;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shortURL")
public class ShortURLController {
    @Autowired
    private ShortURLService shortURLService;

    @PostMapping("/create")
    public ResponseEntity<ShortUrl> createShortURL(@RequestBody ShortUrlRequest shortUrlRequest){
        ShortUrl shortUrl = shortURLService.createShortUrl(shortUrlRequest.getUrl());
        return ResponseEntity.status(HttpStatus.CREATED).body(shortUrl);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ShortUrl>> getAllUrls(){
        List<ShortUrl> shortUrls = shortURLService.getAllUrls();
        return ResponseEntity.status(HttpStatus.FOUND).body(shortUrls);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Optional<ShortUrl>> getOriginalURL(@PathVariable String shortCode){
        Optional<ShortUrl> shortUrl = shortURLService.getOriginalUrl(shortCode);
        if(shortUrl.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND).body(shortUrl);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<ShortUrl> updateShortUrl(@PathVariable String shortCode, @RequestBody ShortUrlRequest shortUrlRequest){
        return shortURLService.updateShortUrl(shortCode, shortUrlRequest.getUrl()).map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{shortUrl}")
    public ResponseEntity<Void> deleteShortUrl(@PathVariable String shortUrl){
        boolean flag = shortURLService.deleteShortUrl(shortUrl);
        if (flag){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{shortUrl}/stats")
    public ResponseEntity<Optional<ShortUrl>> getUrlStats(@PathVariable String shortUrl){
        Optional<ShortUrl> shortUrl1 = shortURLService.getUrlStats(shortUrl);
        if(shortUrl1.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND).body(shortUrl1);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
