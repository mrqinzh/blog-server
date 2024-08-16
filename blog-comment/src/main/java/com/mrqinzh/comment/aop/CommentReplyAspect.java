package com.mrqinzh.comment.aop;

import com.mrqinzh.common.entity.Comment;
import com.mrqinzh.common.message.CommentReplyMessage;
import com.mrqinzh.common.vo.comment.CommentVo;
import com.mrqinzh.framework.message.MessageProducer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
            if (arg instanceof CommentVo) {
                CommentVo commentVo = (CommentVo) arg;
                sendCommentMessage(commentVo);
            }
        }
    }

    private void sendCommentMessage(CommentVo commentVo) {
        CommentReplyMessage message = new CommentReplyMessage();
        message.setArticleId(commentVo.getArticleId());
        messageProducer.sendMessage(message);
    }

}
