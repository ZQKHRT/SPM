package com.vrms.app.controller;


import com.vrms.app.aop.SystemControllerLog;
import com.vrms.app.entity.Address;
import com.vrms.app.service.AddressService;
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
@RequestMapping("address")
@Api(tags = "预约地址控制器模块")
@CrossOrigin//解决跨域访问问题
public class AddressController {

    @Resource //注入
    private AddressService addressService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "分页起始数据",example = "1",
                    required = true,dataType = "integer",dataTypeClass = Long.class),
            @ApiImplicitParam(name = "size",value = "每页最大记录数",example = "10",
                    required = true,dataType = "integer",dataTypeClass = Long.class),
            @ApiImplicitParam(name = "address",value = "根据预约地址查询",example = "西安",
                    required = true,dataType = "string",dataTypeClass = Long.class),
    }

    )
    @GetMapping("/listPage")
    @ApiOperation("分页查询预约地址信息")
    @SystemControllerLog(description = "分页查询预约地址信息")
    public CommonResult listPage(int page, int size, String address){
        Map<String,Object> map=new HashMap();
        map.put("count", addressService.count(address));
        map.put("data", addressService.listPage(page,size,address));
        return CommonResult.success(map);
    }

    @PostMapping("/save")
    @ApiOperation("添加预约地址信息")
    @SystemControllerLog(description = "添加预约地址信息")
    public CommonResult save(@RequestBody Address address){
        return CommonResult.success(addressService.save(address));
    }

    @PostMapping("/update")
    @ApiOperation("修改预约地址信息")
    @SystemControllerLog(description = "修改预约地址信息")
    public CommonResult update(@RequestBody Address address){
        return CommonResult.success(addressService.update(address));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "addid",value = "根据ID删除预约地址信息",
                    example = "1",dataType = "integer",required = true,dataTypeClass = Long.class)
    })
    @PostMapping("/remove")
    @ApiOperation("删除预约地址信息")
    @SystemControllerLog(description = "删除预约地址信息")

    public CommonResult remove(Integer addid){
        return CommonResult.success(addressService.remove(addid));

    }
    @GetMapping("/list")
    @ApiOperation("查询所有预约地址信息")
    @SystemControllerLog(description = "查询所有预约地址信息")
    public CommonResult list(){
        return CommonResult.success(addressService.list());
    }
}
