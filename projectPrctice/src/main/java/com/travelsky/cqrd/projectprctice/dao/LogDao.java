package com.travelsky.cqrd.projectprctice.dao;

import com.travelsky.cqrd.projectprctice.entity.Log;

import java.util.List;

public interface LogDao {
    /**
     * 查询所有log
     * @return
     */
    List<Log> findAllLog();
    /**
     * 根据用户查询log
     */
    List<Log> findLogByUserName(String username);
    /**
     * 新增log
     */
    void addLog(String option, String username);
}
