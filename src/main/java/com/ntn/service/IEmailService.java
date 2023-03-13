package com.ntn.service;

import com.ntn.entity.EmailDetails;

public interface IEmailService {

    String sendSimpleMail(EmailDetails emailDetails);

    String sendMailWithAttachment(EmailDetails emailDetails);

}
