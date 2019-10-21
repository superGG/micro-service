package com.kellan.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kellan.demo.model.TUser;
import com.kellan.demo.service.TUserService;
import com.kellan.demo.utils.entitys.ExceptionEntity;
import com.kellan.demo.utils.entitys.PageInfos;
import com.kellan.demo.utils.entitys.ResultJson;
import com.kellan.demo.utils.enums.ExceptionEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: Kellan_Song
 * @date: 2019-07-16 13:25
 **/
@Slf4j
@Controller
@RequestMapping("/api/demo")
@Api(value = "testController",description = "controller样例",tags="contrller模块命名")
public class TestController {

    @Autowired
    TUserService tUserService;

    @ApiOperation(value="demo接口", notes = "接口的描述", httpMethod = "POST")
    @ApiImplicitParam(name="name", value="参数描述", required=true, paramType="query", dataType="string")
    @RequestMapping(value = "/demo")
    @ResponseBody
    public ResultJson<TUser> demo(String name) {
        log.info("进入demo接口");
        ResultJson<TUser> resultJson = new ResultJson<>();
        TUser user = new TUser().setName(name).setSex(true).setCreateTime(new Date()).setLastTime(new Date());
        tUserService.insert(user);
        resultJson.setResult(user).setMessage("请求成功" + user.toString());
        if (!resultJson.getState()) throw new ExceptionEntity(ExceptionEnum.DATA_ERROR);
        return resultJson;
    }

    @ApiOperation(value="getAll接口", notes = "获取所有数据", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNumber", value="页数", required=true, paramType="query", dataType="Integer"),
            @ApiImplicitParam(name="pageSize", value="每页数量", required=true, paramType="query", dataType="Integer")
    })
    @RequestMapping(value = "/getAll")
    @ResponseBody
    public ResultJson<Map<String, Object>> getAll(Integer pageNumber, Integer pageSize) {
        log.info("进入getAll接口");
        ResultJson<Map<String, Object>> resultJson = new ResultJson<>();
        PageHelper.startPage(pageNumber, pageSize);
        List<TUser> list = tUserService.getAll();
        PageInfo<TUser> page = new PageInfo<>(list);
        PageInfos pageInfo = new PageInfos(pageNumber, pageSize, (int) page.getTotal());
        resultJson.getResultMap().put("list", list);
        resultJson.getResultMap().put("pageInfo", pageInfo);
        if (!resultJson.getState()) throw new ExceptionEntity(ExceptionEnum.DATA_ERROR);
        return resultJson;
    }

}