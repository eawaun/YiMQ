package com.yimq.broker.mapper;

import com.yimq.common.model.MessageModel;

public interface MessageMapper {
    MessageModel select(long id);
}
