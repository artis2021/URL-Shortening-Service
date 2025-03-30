package com.example.URLShorteningService.service;

import com.example.URLShorteningService.entity.ShortUrl;
import com.example.URLShorteningService.repository.ShortURLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ShortURLService {
    @Autowired
    private ShortURLRepository shortURLRepository;

    public ShortUrl createShortUrl(String url){
        ShortUrl shortUrl = new ShortUrl(url);
        return shortURLRepository.save(shortUrl);
    }

    public Optional<ShortUrl> getOriginalUrl(String shortCode) {
        return shortURLRepository.findByShortCode(shortCode).map(url -> {
            url.setAccessCount(url.getAccessCount() + 1);
            shortURLRepository.save(url);
            return url;
        });
    }

    public Optional<ShortUrl> updateShortUrl(String shortCode, String newURl){
        return shortURLRepository.findByShortCode(shortCode).map(url -> {
            url.setUrl(newURl);
            url.setUpdatedAt(Instant.now());
            return shortURLRepository.save(url);
        });
    }

    public boolean deleteShortUrl(String shortUrl){
        if(shortURLRepository.findByShortCode(shortUrl).isPresent()){
            shortURLRepository.deleteByShortCode(shortUrl);
            return true;
        } else{
            return false;
        }
    }

    public Optional<ShortUrl> getUrlStats(String shortUrl){
        return shortURLRepository.findByShortCode(shortUrl);
    }

    public List<ShortUrl> getAllUrls(){
        return shortURLRepository.findAll();
    }





}
