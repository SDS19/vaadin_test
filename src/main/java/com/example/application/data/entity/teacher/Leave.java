package com.example.application.data.entity.teacher;

import java.util.Date;

//@Entity
//@Table(name = "t_leave")
public class Leave {
    private Integer id;
    private Student student;
    private Date start;
    private Date end;
    private Integer type;
    private Teacher teacher;
    private Date verifyTime;
    private Integer status;
    /** 0: 未审核
     *  1：通过
     *  2：驳回
     * */
}
