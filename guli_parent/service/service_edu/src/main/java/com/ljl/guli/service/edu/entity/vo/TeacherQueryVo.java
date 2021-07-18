/**
 * FileName: TeacherQueryVo
 * Author: ljl
 * Date: 2021/3/23 20:58
 * Description: teacher类查询对象条件
 * History:
 */


package com.ljl.guli.service.edu.entity.vo;

import com.ljl.guli.service.edu.entity.Teacher;
import lombok.Data;

import java.util.Date;

@Data
public class TeacherQueryVo extends Teacher {

    private String joinDateBegin;

    private String joinDateEnd;
}
