package com.zw.service;

import com.zw.pojo.ToEmail;
import com.zw.pojo.result.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.service
 * @descripation TODO
 * @date 2021-06-03
 */
public interface ToEmailService {
    /**
     * 发送文本邮件
     *
     * @param to      收件人信息
     * @param cc      抄送地址
     */
    Result sendSimpleMail(ToEmail to, String... cc);

    /**
     * 发送html邮件
     *
     * @param to      收件人信息
     * @param cc      抄送地址
     */
    Result sendHtmlMail(ToEmail to, String... cc);

    /**
     * 发送带附件的邮件
     *
     * @param to        收件人信息
     * @param filePath  附件地址
     * @param cc        抄送地址
     */
    Result sendAttachmentsMail(ToEmail to,String filePath, String... cc);

    /**
     * 发送带附件的邮件(通过浏览器获取file)
     *
     * @param to             收件人信息
     * @param multipartFile  附件(获取的file)
     * @param cc             抄送地址
     */
    Result sendAttachmentsMail(ToEmail to, MultipartFile multipartFile, String... cc);
    /**
     * 发送带静态资源的邮件
     *
     * @param to        收件人信息
     * @param rscPath 静态资源地址
     * @param rscId   静态资源id
     * @param cc      抄送地址
     */
    Result sendResourceMail(ToEmail to,String rscPath, String rscId, String... cc);

    /**
     * 发送带静态资源的邮件(通过浏览器获取file)
     *
     * @param to            收件人信息
     * @param multipartFile 静态资源地址
     * @param rscId         静态资源id
     * @param cc            抄送地址
     */
    Result sendResourceMail(ToEmail to,MultipartFile multipartFile, String rscId, String... cc);
}
