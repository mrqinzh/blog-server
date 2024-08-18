package com.mrqinzh.comment.aop;

import com.mrqinzh.common.constant.MessageConstant;
import com.mrqinzh.common.domain.vo.comment.CommentVo;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class CommentReplyAspect {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

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
        rocketMQTemplate.syncSend(MessageConstant.CommentMessage.COMMENT_REPLY_TOPIC, new GenericMessage<>(commentVo));
    }

}
