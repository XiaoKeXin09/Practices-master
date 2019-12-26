package com.shmj.mouzhai.downloaddemo.db;

import com.shmj.mouzhai.downloaddemo.entities.ThreadInfo;

import java.util.List;

/**
 * 数据访问接口
 * <p>
 * Created by Mouzhai on 2016/11/28.
 */

public interface ThreadPort {

    //插入线程信息
    void insertThread(ThreadInfo threadInfo);

    //删除线程
    void deleteThread(String url);

    //更新线程下载进度
    void updateThread(String url, int thread_id, int finished);

    //查询文件线程信息
    List<ThreadInfo> getThreads(String url);

    //线程信息是否存在
    boolean isExists(String url, int thread_id);
}
