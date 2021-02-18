package com.lingyejun.dating.chap6.practice;

import com.lingyejun.dating.chap5.Worker;
import com.lingyejun.dating.chap5.WorkingWithStreams;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 学习使用流进行数据收集
 *
 * @author yiyh
 * @date 2021-02-17 19:11
 */
public class CollectDataWithStreamsTest {

    private static List<Worker> workerList = WorkingWithStreams.initWorkerList();

    /**
     * 复习 stream().collect(Collectors.toList())
     */
    @Test
    public void reviewCollect() {
        //需求：取出所有的员工姓名
        List<String> nameList = workerList.stream()
                .map(Worker::getName)
                .collect(Collectors.toList());
        System.out.println("所有的员工姓名：" + nameList);
    }

}
