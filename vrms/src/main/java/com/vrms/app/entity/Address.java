package com.vrms.app.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel
public class Address implements Serializable {
    /**
     * 地址ID
     */
    @ApiModelProperty(hidden = true)
    private Integer addid;
    /**
     * 地址名称
     */
    @ApiModelProperty(name = "address",value = "疫苗预约地址",
    dataType = "string",required = true,example = "中心医院")
    private String address;
    /**
     * 地址详情
     */
    @ApiModelProperty(name = "detail",value = "疫苗预约详细地址",
    example = "门诊部",required = true,dataType = "string")
    private String detail;
    /**
     * 电话
     */
    @ApiModelProperty(name = "tel",value = "预约地址联系方式",
    example = "11111",dataType = "string",required = true)
    private String tel;
    /**
     * 添加时间
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
