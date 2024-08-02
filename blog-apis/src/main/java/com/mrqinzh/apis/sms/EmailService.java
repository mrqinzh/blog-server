package com.mrqinzh.apis.sms;


import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.vo.email.EmailVO;

public interface EmailService {

    Resp sendSimpleMail(EmailVO emailVO);

    Resp sendFileMail(EmailVO emailVO);
}
