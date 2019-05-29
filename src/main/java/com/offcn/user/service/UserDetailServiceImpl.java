
package com.offcn.user.service;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.offcn.pojo.TbSeller;



/**
 * @author Administrator
 * TODO 商家认证登录
 * @date  2019年5月5日
 */
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//构建角色列表
			ArrayList<GrantedAuthority>  grantedAuthorities= new ArrayList();
			//设置权限（必须为“ROLE_XX”）
			SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
			//T添加权限
			grantedAuthorities.add(grantedAuthority);
			return new User(username,"",grantedAuthorities);
	}
}
