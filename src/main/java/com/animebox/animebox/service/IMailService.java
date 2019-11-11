package com.animebox.animebox.service;

import java.io.File;
import java.util.List;

public interface IMailService {
    public void sendSimpleMail(String to,String title,String content);
    public void sendAttachmentsMail(String to, String title, String cotent, List<File> fileList);
}
