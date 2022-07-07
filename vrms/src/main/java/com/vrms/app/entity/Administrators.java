package com.vrms.app.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel
public class Administrators implements Serializable {
    @ApiModelProperty(hidden = true) //模型中的属性使用该标签注释，也是swagger提供
    private Integer aid;
    @ApiModelProperty(name = "username",value = "管理员名称", required = true,dataType ="string",example = "admin")
    private String username;
    @ApiModelProperty(name = "password",value = "管理员密码", required = true,dataType = "string",example = "000000")
    private String password;
    @ApiModelProperty(name = "images",value = "管理员头像", required = true,dataType = "string",example = "000")
    private String images; //用户头像
    @ApiModelProperty(name = "stats",value = "管理员状态",
            required = true,dataType = "integer",example = "0")
    private Integer stats; //0表示启用 1禁用
//    添加时间
    @ApiModelProperty(hidden = true)
    private String ctime;
//    修改时间
    @ApiModelProperty(hidden = true)
    private String mtime;
    @ApiModelProperty(hidden = true)
    private Integer deleted; //删除标记

}
