package com.mrqinzh.webapp.controller;

import com.mrqinzh.common.constant.MessageConstant;
import com.mrqinzh.common.domain.vo.comment.CommentVo;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.framework.utils.RedisUtil;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RequestMapping("test")
@RestController
public class TestController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping
    public Resp testRedisKeyExpire() {
        RedisUtil.set("test_expire", 1, 5);
        return Resp.success();
    }

    @GetMapping("mq")
    public Resp testMq() {
        CommentVo commentVo = new CommentVo();
        commentVo.setCommentIp("123123");
        commentVo.setNickname("name");
        rocketMQTemplate.syncSend(MessageConstant.Comment.COMMENT_REPLY_TOPIC, new GenericMessage<>(commentVo));
        return Resp.success();
    }


}
