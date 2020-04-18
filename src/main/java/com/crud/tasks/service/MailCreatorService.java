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
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tusks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context= new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://MajaBrog.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("show_button", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("is_friend", true);
        context.setVariable("goodbye_message", "Best regards");
        context.setVariable("application_functionality", functionality);
        context.setVariable("company_details", companyConfig);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildInformationEmail(String message) {

        Context context= new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://MajaBrog.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("show_button", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("is_friend", true);
        context.setVariable("goodbye_message", "Best regards");
        context.setVariable("company_details", companyConfig);
        return templateEngine.process("mail/information-mail", context);
    }

}