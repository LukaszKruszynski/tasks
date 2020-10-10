package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    private final String CRUD_URL = "http://localhost:8888/tasks_frontend";

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message",message);
        context.setVariable("tasks_url",CRUD_URL);
        context.setVariable("button","Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview_message","New Trello card detected.");
        context.setVariable("app_name",adminConfig.getAppName());
        context.setVariable("app_version",adminConfig.getAppVersion());
        context.setVariable("app_rights",adminConfig.getAppRights());
        return templateEngine.process("mail/created-trello-card-mail",context);
    }
}
