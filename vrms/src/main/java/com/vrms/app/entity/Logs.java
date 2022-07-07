package com.vrms.app.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel
public class Logs implements Serializable {
    /**
     * 日志ID
     */
    @ApiModelProperty(hidden = true)
    private Integer opid;
    /**
     * 操作时间
     */
    @ApiModelProperty(name = "optime",value = "操作时间",
    example = "2020.10.10",dataType = "string",required = true)
    private String optime;
    /**
     * 操作ip
     */
    @ApiModelProperty(name = "ip",value = "操作ip",
    example = "192.168.1.1",dataType = "string",required = true)
    private String ip;
    /**
     * 操作方法
     */
    @ApiModelProperty(name = "methods",value = "操作方法",
    example = "save",dataType = "string",required = true)
    private String methods;
    /**
     * 操作方法描述
     */
    @ApiModelProperty(name = "ddesc",value = "操作方法描述",
    example = "saveXX",dataType = "string",required = true)
    private String ddesc;
}
