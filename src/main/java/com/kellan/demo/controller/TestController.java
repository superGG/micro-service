package com.kellan.demo.controller;

import com.kellan.demo.model.User;
import com.kellan.demo.utils.entitys.ExceptionEntity;
import com.kellan.demo.utils.entitys.ResultJson;
import com.kellan.demo.utils.enums.ExceptionEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Kellan_Song
 * @date: 2019-07-16 13:25
 **/
@Slf4j
@Controller
@RequestMapping("/api/demo")
@Api(value = "testController",description = "controller样例",tags="contrller模块命名")
public class TestController {

    @ApiOperation(value="demo接口", notes = "接口的描述", httpMethod = "POST")
    @ApiImplicitParam(name="name", value="参数描述", required=true, paramType="query", dataType="string")
    @RequestMapping(value = "/demo")
    @ResponseBody
    public ResultJson<User> demo(String name) {
        log.info("进入demo接口");
        ResultJson<User> resultJson = new ResultJson<>();
//        User user = new User(name,true).setId(1l);
        User user = new User().setId(1l).setName(name).setSex(true);
        resultJson.setResult(user).setMessage("请求成功" + user.toString());
        if (!resultJson.getState()) throw new ExceptionEntity(ExceptionEnum.DATA_ERROR);
        return resultJson;
    }
}