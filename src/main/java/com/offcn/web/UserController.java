
package com.offcn.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offcn.common.PhoneFormatCheckUtils;
import com.offcn.common.Result;
import com.offcn.pojo.TbUser;
import com.offcn.user.service.UserService;


/**
 * @author Administrator
 * TODO 用户注册、登录
 * @date  2019年5月15日
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	public Result register(@RequestBody TbUser tbUser){
		try {
			//校验后台获取的验证码和手机输入的验证码
			userService.userRegister(tbUser);
			return new Result(true,"注册成功");
		} catch (Exception e) {
			return new Result(false,"注册失败");
		}
	}
	//获取验证码
	@RequestMapping("/code")
	public Result getCode(String phone){
		try {
			//判断手机号格式
			if(!PhoneFormatCheckUtils.isPhoneLegal(phone)) {
				return new Result(false,"手机号格式不正确");
			}
			//校验后台获取的验证码和手机输入的验证码
			String code = userService.createSmsCode(phone);
			return new Result(true,code);
		} catch (Exception e) {
			return new Result(false,"获取验证码错误");
		}
	}
	//获取登录用户名
	@RequestMapping("/findLoginUser")
	public Map findLoginUser(){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		Map map = new HashMap<>();
		map.put("username", name);
		return map;
	}
}
