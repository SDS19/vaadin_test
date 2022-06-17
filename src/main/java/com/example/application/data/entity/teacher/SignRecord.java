package com.example.application.data.entity.teacher;

import java.util.Date;

public class SignRecord {
    private Integer id;
    private Integer stuId;
    private Integer groupId;
    private Date time;
    private Integer result;
    /** 0: 未签到
     *  1: 正常
     *  2：迟到
     *  3：事假
     *  4：病假
     *  5：早退
     *  6：迟到+早退
     * */
}
