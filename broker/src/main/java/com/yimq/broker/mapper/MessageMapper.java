package com.yimq.broker.mapper;

import com.yimq.broker.model.MessagePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {

    List<MessagePO> selectByStatus(int status);

    List<MessagePO> selectByStatusList(List<Integer> list);

    int save(MessagePO messagePO);

    int updateStatusByStatus(@Param("id") long id, @Param("toStatus") int toStatus, @Param("fromStatus") int fromStatus,
                             @Param("updateTimeLock") int updateTimeLock);

    int lockMessageToIng(@Param("id") long id, @Param("toStatus") int toStatus,
                                 @Param("updateTimeLock") int updateTimeLock, @Param("fromStatusList") List<Integer> fromStatusList);

    int updateFailConsumers(@Param("id") long id, @Param("toStatus") int toStatus, @Param("fromStatus") int fromStatus,
                            @Param("failConsumers") String failConsumers, @Param("delayTime") int delayTime);
}
