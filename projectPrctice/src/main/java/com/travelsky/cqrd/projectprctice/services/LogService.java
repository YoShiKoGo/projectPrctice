package com.travelsky.cqrd.projectprctice.services;

import com.travelsky.cqrd.projectprctice.entity.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LogService {
    /**
     * 查询所有log
     * @return
     */
    List<Log> findAllLog();

    /**
     * 根据log查询log
     * @param username
     * @return
     */
    List<Log> findLogByUsername(String username);
    /**
     * 记录日志
     */
    void addLog(String option, HttpServletRequest request);

}
