package com.demo.cloud.user.controller;

import com.demo.cloud.user.model.UserInfo;
import com.demo.cloud.user.service.IUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "用户服务Controller", description = "用户管理")
@RestController
@RequestMapping(value = "/api/v1/user/")
public class UserServiceController {

    private final IUserService userService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public UserServiceController(IUserService userService) {
        this.userService = userService;
    }


    @ApiOperation(value = "查询所有用户信息", response = UserInfo.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserInfo> queryAllUserInfo(HttpServletRequest request) {

        return userService.queryAllUserInfo();
    }


    @ApiOperation(value = "根据UserInfo查询用户信息列表", response = UserInfo.class)
    @GetMapping(value = "page", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserInfo> queryUserInfoByPage(UserInfo userInfo) {

        List<UserInfo> userInfos = userService.queryUserInfoByPage(userInfo);
        return userInfos;
    }


    @ApiOperation(value = "使用Mapper框架自动分页根据UserInfo分页查询用户信息", response = UserInfo.class)
    @GetMapping(value = "page-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<UserInfo> queryUserInfoByPageInfo(UserInfo userInfo) {
        List<UserInfo> userInfos = userService.queryUserInfoByPage(userInfo);
        return new PageInfo<>(userInfos);
    }

    @ApiOperation(value = "自定义SQL查询语句根据UserInfo分页查询用户信息", response = UserInfo.class)
    @GetMapping(value = "{pageNum}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<UserInfo> queryUserInfoByPageInfo(UserInfo userInfo,
                                                      @PathVariable(value = "pageNum", name = "pageNum") Integer pageNum,
                                                      @PathVariable(value = "pageSize", name = "pageSize") Integer pageSize) {
        List<UserInfo> userInfos = userService.queryUserInfoByPageSize(userInfo, pageNum, pageSize);
        return new PageInfo<>(userInfos);
    }


}
