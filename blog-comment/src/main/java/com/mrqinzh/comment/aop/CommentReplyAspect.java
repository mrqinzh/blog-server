package com.mrqinzh.comment.aop;

import com.mrqinzh.comment.domain.dto.CommentReqDTO;
import com.mrqinzh.framework.common.constant.MessageConstant;
import com.mrqinzh.framework.mq.producer.MessageProducer;
import jakarta.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommentReplyAspect {

    @Resource
    private MessageProducer messageProducer;

    @Pointcut("execution(* com.mrqinzh.comment.service.CommentService.add(..))")
    public void pointCut() {
    }

    @AfterReturning(pointcut = "pointCut()")
    public void after(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof CommentReqDTO reqDTO) {
                sendCommentMessage(reqDTO);
            }
        }
    }

    private void sendCommentMessage(CommentReqDTO reqDTO) {
        messageProducer.syncSend(MessageConstant.Comment.COMMENT_REPLY_TOPIC, reqDTO);
    }

}
