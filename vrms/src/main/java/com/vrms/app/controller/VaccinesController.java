package com.vrms.app.controller;

import com.vrms.app.aop.SystemControllerLog;
import com.vrms.app.entity.Vaccines;
import com.vrms.app.service.VaccinesService;
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
@RequestMapping("vaccines")
@Api(tags = "疫苗控制器模块") //跨域访问，就是跨服务器访问
@CrossOrigin //解决跨域访问问题
public class VaccinesController {
    @Resource
    private VaccinesService vaccinesService;
    //控制器注解
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "分页起始数据",example = "1",
                    required = true,dataType = "integer",dataTypeClass = Long.class),
            @ApiImplicitParam(name = "size",value = "每页最大记录数",example = "10",
                    required = true,dataType = "integer",dataTypeClass = Long.class),
            @ApiImplicitParam(name = "vacname",value = "根据疫苗名称查询",example = "好疫苗",
                    required = true,dataType = "string",dataTypeClass = Long.class),
    }
    )
    @GetMapping("/listPage")
    @ApiOperation("分页查询疫苗信息")
    @SystemControllerLog(description = "分页查询疫苗信息")

    public CommonResult listPage(int page, int size, String vacname){
        Map<String,Object> map=new HashMap();
        map.put("count",vaccinesService.count(vacname));
        map.put("data",vaccinesService.listPage(page,size,vacname));
        return CommonResult.success(map);
    }

    @PostMapping("/save")
    @ApiOperation("添加疫苗信息")
    @SystemControllerLog(description = "添加疫苗信息")

    public CommonResult save(@RequestBody Vaccines vaccines){
        return CommonResult.success(vaccinesService.save(vaccines));
    }

    @PostMapping("/update")
    @ApiOperation("修改疫苗信息")
    @SystemControllerLog(description = "修改疫苗信息")

    public CommonResult update(@RequestBody Vaccines vaccines){
        return CommonResult.success(vaccinesService.update(vaccines));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "vacid",value = "根据ID删除疫苗信息",
                    example = "1",dataType = "integer",required = true,dataTypeClass = Long.class)
    })
    @PostMapping("/remove")
    @ApiOperation("删除疫苗信息")
    @SystemControllerLog(description = "删除疫苗信息")

    public CommonResult remove(Integer vacid){
        return CommonResult.success(vaccinesService.remove(vacid));
    }
    @GetMapping("/list")
    @ApiOperation("查询所有疫苗信息")
    @SystemControllerLog(description = "查询所有疫苗信息")
    public CommonResult list(){
        return CommonResult.success(vaccinesService.list());
    }
}
