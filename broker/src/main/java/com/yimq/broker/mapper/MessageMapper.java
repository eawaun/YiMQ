package com.yimq.broker.mapper;

import com.yimq.broker.model.MessagePO;

import java.util.List;

public interface MessageMapper {

    List<MessagePO> selectByStatus(int status);

    List<MessagePO> selectByStatusList(List<Integer> list);

    int save(MessagePO messagePO);

    int updateStatusByStatus(long id, int toStatus, int fromStatus, int updateTimeLock);

    int updateStatusByStatusList(long id, int toStatus, List<Integer> list, int updateTimeLock);

    int updateFailConsumers(long id, int toStatus, int fromStatus, String failConsumers, int delayTime);
}
