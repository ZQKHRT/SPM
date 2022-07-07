package com.vrms.app.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel
public class Users implements Serializable {
    /**
     * 用户ID
     */
    @ApiModelProperty(hidden = true)
    private Integer uid;
    /**
     * 用户名称1
     */
    @ApiModelProperty(name = "username",value = "用户名称",
    dataType = "string",required = true,example = "user")
    private String username;
    /**
     * 密码2
     */
    @ApiModelProperty(name = "password",value = "用户密码",
    dataType = "string",required = true,example = "111111")
    private String password;
    /**
     * 性别3
     */
    @ApiModelProperty(name = "sex",value = "用户性别",
    dataType = "string",required = true,example = "男")
    private String sex;
    /**
     * 年龄4
     */
    @ApiModelProperty(name = "age",value = "用户年龄",
    dataType = "integer",required = true,example = "20")
    private Integer age;
    /**
     * 身份证5
     */
    @ApiModelProperty(name = "idcard",value = "身份证",
    dataType = "string",required = true,example = "10101010")
    private String idcard;
    /**
     * 电话6
     */
    @ApiModelProperty(name = "tel",value = "联系方式",
    dataType = "string",required = true,example = "11111111111")
    private String tel;
    /**
     * 住址7
     */
    @ApiModelProperty(name = "address",value = "住址",
    dataType = "string",required = true,example = "西安")
    private String address;
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
     * 0未删除 , 1已删除
     */
    @ApiModelProperty(hidden = true)
    private Integer deleted;
    /**
     * 0未禁用,1已经用
     */
    @ApiModelProperty(hidden = true)
    private Integer stats;
}
