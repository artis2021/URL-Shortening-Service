package com.example.URLShorteningService.repository;

import com.example.URLShorteningService.entity.ShortUrl;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortURLRepository extends JpaRepository<ShortUrl, Long> {
    public Optional<ShortUrl> findByShortCode(String shortCode);
    @Transactional
    public void deleteByShortCode(String shortCode);
}
