package com.org.event.book.service;

import com.org.event.book.entity.Offer;
import com.org.event.book.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;
    public List<Offer> getOffers(Long eventId) {
        return offerRepository.findByEventId(eventId);
    }
}
