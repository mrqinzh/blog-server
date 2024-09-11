package com.mrqinzh.comment.aop;

import com.mrqinzh.comment.domain.vo.CommentReqVO;
import com.mrqinzh.framework.common.constant.MessageConstant;
import jakarta.annotation.Resource;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

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
            if (arg instanceof CommentReqVO) {
                CommentReqVO commentReqVo = (CommentReqVO) arg;
                sendCommentMessage(commentReqVo);
            }
        }
    }

    private void sendCommentMessage(CommentReqVO commentReqVo) {
        rocketMQTemplate.syncSend(MessageConstant.Comment.COMMENT_REPLY_TOPIC, new GenericMessage<>(commentReqVo));
    }

}
