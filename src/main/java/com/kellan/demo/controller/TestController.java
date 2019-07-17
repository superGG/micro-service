package com.kellan.demo.controller;

import com.kellan.demo.utils.entitys.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Kellan_Song
 * @date: 2019-07-16 13:25
 **/
@Controller
@RequestMapping("/api/demo")
@Api(value = "DemoController",description="swagger接口文档")
public class TestController {

    @ApiOperation(value="demo接口", notes = "接口的描述", response = ResultJson.class, httpMethod = "POST")
    @ApiImplicitParam(name="name", value="参数描述", required=true, paramType="query", dataType="string")
    @RequestMapping(value = "/demo")
    @ResponseBody
    public ResultJson demo(String name) {
        ResultJson resultJson = new ResultJson();
        resultJson.getResultMap().put("result", "Hello world! " + name);
        return resultJson;
    }
}