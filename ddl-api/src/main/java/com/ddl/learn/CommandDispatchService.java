package com.ddl.learn;

/**
 * description: 事件发布
 * @author dongdongliu
 * @version 1.0
 * @date 2018-10-23 13:34:50
 */
public interface CommandDispatchService {
    /**
     * description:<发布>
     * @param command
     * @author dongdongliu
     * @date 2018/10/23 13:40
     */
    void dispatch(Command command);
}
