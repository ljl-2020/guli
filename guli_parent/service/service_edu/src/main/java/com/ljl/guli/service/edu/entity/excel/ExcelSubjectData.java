/**
 * FileName: ExcelSubjectData
 * Author: ljl
 * Date: 2021/6/3 9:30
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelSubjectData {

    @ExcelProperty(value = "一级分类")
    private String levelOneTitle;

    @ExcelProperty(value = "二级分类")
    private String levelTwoTitle;
}
