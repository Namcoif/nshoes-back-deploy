package com.ntn.controller;

import com.ntn.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailController {
    @Autowired
    private IEmailService emailService;
}
