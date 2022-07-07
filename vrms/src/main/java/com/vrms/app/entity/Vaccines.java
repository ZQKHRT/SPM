package com.vrms.app.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel
public class Vaccines implements Serializable {
    /**
     * 疫苗ID
     */
    @ApiModelProperty(hidden = true)
    private Integer vacid;
    /**
     * 疫苗名称
     */
    @ApiModelProperty(name = "avcname",value = "疫苗名称",
    required = true,example = "X疫苗",dataType = "string")
    private String vacname;
    /**
     * 生成厂家
     */
    @ApiModelProperty(name = "manufactor",value = "生产厂家",
    required = true,example = "XX公司",dataType = "string" )
    private String manufactor;
    /**
     * 生产日期
     */
    @ApiModelProperty(name = "mdate",value = "生产日期",
    required = true,example = "2020.10.10",dataType = "string")
    private String mdate;
    /**
     * 单价
     */
    @ApiModelProperty(name = "price",value = "单价",
    required = true,example = "100",dataType = "string")
    private String price;
    /**
     * 说明
     */
    @ApiModelProperty(name = "instructions",value = "说明",
    required = true,example = "使用说明",dataType = "string")
    private String instructions;
    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private String ctime;
    /**
     * 修改时间
     */
    @ApiModelProperty(hidden = true)
    private String mtime;
    /**
     * 0未删除 1已删除
     */
    @ApiModelProperty(hidden = true)
    private Integer deleted;
}
