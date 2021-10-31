package com.zw.controller;

import cn.hutool.core.io.resource.ResourceUtil;
import com.zw.pojo.ToEmail;
import com.zw.pojo.result.Result;
import com.zw.service.ToEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.net.URL;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.controller
 * @descripation TODO
 * @date 2021-06-03
 */
@RestController
public class EmailController {
    @Autowired
    private ToEmailService service;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${toemail.name}")
    private String toEmailAddress;

    @PostMapping("/sendMail")
    public Result sendSimpleMail(@RequestBody ToEmail toEmail){
        return service.sendSimpleMail(toEmail);
    }

    @PostMapping("/sendHtmlMail")
    public Result sendHtmlMail(){
        Context context = new Context();
        context.setVariable("project", "Spring boot sendmail test");
        context.setVariable("author", "weizhang");
        context.setVariable("url", "https://github.com/xkcoding/spring-boot-demo");
        String emailTemplate = templateEngine.process("welcome", context);

        return service.sendHtmlMail(new ToEmail(new String[]{toEmailAddress},"测试一下发送HtmlEmail",emailTemplate));
    }

    @PostMapping("/sendAttach")
    public Result sendAttachmentsMail(ToEmail toEmail){
        URL resource =ResourceUtil.getResource("static/tuzi.jpeg");
        return service.sendAttachmentsMail(toEmail,resource.getPath());
    }

    @PostMapping("/sendAttachByFile")
    public Result sendAttachmentsMail(ToEmail toEmail, MultipartFile file){
        return service.sendAttachmentsMail(toEmail,file);
    }

    @PostMapping("/sendResource")
    public Result sendResourceMail(ToEmail toEmail,String rscId){
        URL resource =ResourceUtil.getResource("static/tuzi.jpeg");
        toEmail.setContent("<html><body>这是带静态资源的邮件<br/><img src=\'cid:" + rscId + "\' ></body></html>");
        return service.sendResourceMail(toEmail,resource.getPath(),rscId);
    }

    @PostMapping("/sendResourceByFile")
    public Result sendResourceMail(ToEmail toEmail, MultipartFile file,String rscId){
        return service.sendResourceMail(toEmail,file,rscId);
    }
}
