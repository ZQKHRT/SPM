package com.vrms.app.controller;

import com.vrms.app.aop.SystemControllerLog;
import com.vrms.app.entity.Appointment;
import com.vrms.app.service.AppointmentService;
import com.vrms.app.tools.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("appointment")
@Api(tags = "预约控制器模块")
@CrossOrigin//解决跨域访问问题
public class AppointmentController {
  @Resource
    private AppointmentService appointmentService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "分页起始数据",example = "1",
                    required = true,dataType = "integer",dataTypeClass = Long.class),
            @ApiImplicitParam(name = "size",value = "每页最大记录数",example = "10",
                    required = true,dataType = "integer",dataTypeClass = Long.class),
            @ApiImplicitParam(name = "username",value = "根据预约用户查询",example = "张三",
                    required = true,dataType = "string",dataTypeClass = Long.class),
    }

    )
    @GetMapping("/listPage")
    @ApiOperation("分页查询预约信息")
    @SystemControllerLog(description = "分页查询预约信息")
    public CommonResult listPage(int page, int size, String username){
        Map<String,Object> map=new HashMap();
        map.put("count", appointmentService.count(username));
        map.put("data", appointmentService.listPage(page,size,username));
        return CommonResult.success(map);
    }

    @PostMapping("/save")
    @ApiOperation("添加预约信息")
    @SystemControllerLog(description = "添加预约信息")
    public CommonResult save(@RequestBody Appointment appointment){
        return CommonResult.success(appointmentService.save(appointment));
    }

    @PostMapping("/update")
    @ApiOperation("修改预约信息")
    @SystemControllerLog(description = "修改预约信息")
    public CommonResult update(@RequestBody Appointment appointment){
        return CommonResult.success(appointmentService.update(appointment));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "appid",value = "根据ID删除预约信息",
                    example = "1",dataType = "integer",required = true,dataTypeClass = Long.class)
    })
    @PostMapping("/remove")
    @ApiOperation("删除预约信息")
    @SystemControllerLog(description = "删除预约信息")
    public CommonResult remove(Integer appid){
        return CommonResult.success(appointmentService.remove(appid));

    }

    @PostMapping("/changeStats")
    @ApiOperation("修改接种状态")
    @SystemControllerLog( description = "修改接种状态")
    public CommonResult changeStats(@RequestBody Appointment appointment){
        return CommonResult.success(appointmentService.changeStats(appointment));

    }
}
