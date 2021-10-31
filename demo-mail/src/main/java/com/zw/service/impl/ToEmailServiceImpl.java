package com.zw.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import com.zw.pojo.ToEmail;
import com.zw.exception.SendMailException;
import com.zw.pojo.result.Result;
import com.zw.service.ToEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.service.impl
 * @descripation TODO
 * @date 2021-06-03
 */
@Service
@Slf4j
public class ToEmailServiceImpl implements ToEmailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public Result sendSimpleMail(ToEmail to, String... cc) {
        //创建简单邮件消息
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //谁发的
        simpleMailMessage.setFrom(from);
        //谁要接收
        simpleMailMessage.setTo(to.getTos());
        //邮件标题
        simpleMailMessage.setSubject(to.getSubject());
        //邮件内容
        simpleMailMessage.setText(to.getContent());
        if(ArrayUtil.isNotEmpty(cc)){
            //抄送
            simpleMailMessage.setCc(cc);
        }
        try {
            javaMailSender.send(simpleMailMessage);
           return new Result("[sendSimpleMail] Success!");
        }catch (MailException e){
            log.error("==================>[sendSimpleMail] error: "+ e.getMessage() );
            throw new SendMailException();
        }

    }

    @Override
    public Result sendHtmlMail(ToEmail to, String... cc) {

        try {
            //创建一个MINE消息
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true);
            //谁发的
            helper.setFrom(from);
            //谁要接收
            helper.setTo(to.getTos());
            //邮件标题
            helper.setSubject(to.getSubject());
            //邮件内容
            helper.setText(to.getContent());

            if (ArrayUtil.isNotEmpty(cc)) {
                helper.setCc(cc);
            }
            javaMailSender.send(mailMessage);
            return new Result("sendHtmlMail Success!");
        } catch (MailException | MessagingException e) {
            log.error("==================>[sendHtmlMail] error: "+ e.getMessage() );
            throw new SendMailException();
        }
    }

    @Override
    public Result sendAttachmentsMail(ToEmail to, String filePath, String... cc) {
        try {
            //创建一个MINE消息
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true);
            //谁发的
            helper.setFrom(from);
            //谁要接收
            helper.setTo(to.getTos());
            //邮件标题
            helper.setSubject(to.getSubject());
            //邮件内容
            helper.setText(to.getContent());

            if (ArrayUtil.isNotEmpty(cc)) {
                helper.setCc(cc);
            }
            //通过path获取
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            //添加附件
            helper.addAttachment(fileName, file);

            javaMailSender.send(mailMessage);
            return new Result("sendAttachmentsMail Success!");
        } catch (MailException | MessagingException e) {
            log.error("==================>[sendAttachmentsMail] error: "+ e.getMessage() );
            throw new SendMailException();
        }
    }

    @Override
    public Result sendAttachmentsMail(ToEmail to, MultipartFile multipartFile, String... cc) {
        File multipartFileToFile = MultipartFileToFile(multipartFile);
        return sendAttachmentsMail(to, FileUtil.getAbsolutePath(multipartFileToFile),cc);
    }

    @Override
    public Result sendResourceMail(ToEmail to, String rscPath, String rscId, String... cc) {
        try {
            //创建一个MINE消息
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
            //谁发的
            helper.setFrom(from);
            //谁要接收
            helper.setTo(to.getTos());
            //邮件标题
            helper.setSubject(to.getSubject());
            //邮件内容
            helper.setText(to.getContent());

            if (ArrayUtil.isNotEmpty(cc)) {
                helper.setCc(cc);
            }
            //通过path获取
            FileSystemResource file = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId,file);
            javaMailSender.send(mailMessage);
            return new Result("sendResourceMail Success");
        }catch (MailException | MessagingException e){
            log.error("==================>[sendResourceMail] error: "+ e.getMessage() );
            throw new SendMailException();
        }
    }

    @Override
    public Result sendResourceMail(ToEmail to, MultipartFile multipartFile, String rscId, String... cc) {
        File multipartFileToFile = MultipartFileToFile(multipartFile);
        return sendResourceMail(to,FileUtil.getAbsolutePath(multipartFileToFile),rscId,cc);
    }


    /**
     * 将 multpartfile 转为file
     *
     * @return file
     */
    private File MultipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若需要防止生成的临时文件重复,可以在文件名后添加随机码

        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
