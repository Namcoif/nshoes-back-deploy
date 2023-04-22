package com.ntn.service;

import com.ntn.entity.Rate;
import com.ntn.repository.IRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateService implements IRateService {

    @Autowired
    private IRateRepository rateRepository;

    @Override
    public void createRate(Rate rate) {
        rateRepository.save(rate);
    }
}
