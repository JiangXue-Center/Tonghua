package com.hf.auth.service;

public interface IMailService {
    /**
     * 发送文本邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String to, String subject, String content);

    public void sendSimpleMail(String to, String subject, String content, String... cc);

    /**
     * 发送HTML邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    public void sendHtmlMail(String to, String subject, String content);

    public void sendHtmlMail(String to, String subject, String content, String... cc);

    /**
     * 发送带附件的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);

    public void sendAttachmentsMail(String to, String subject, String content, String filePath, String... cc);

    /**
     * 发送正文中有静态资源的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    public void sendResourceMail(String to, String subject, String content, String rscPath, String rscId);

    public void sendResourceMail(String to, String subject, String content, String rscPath, String rscId, String... cc);

} 