package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailCreatorService {
    @Autowired
    private final AdminConfig adminConfig;
    @Autowired
    private final CompanyConfig companyConfig;

    @Qualifier("templateEngine")
    private final TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        Context context= new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://MajaBrog.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Best regards");
        context.setVariable("company_details", companyConfig);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

}