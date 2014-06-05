package com.j1987.coffeeroo.web.interceptors;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.j1987.coffeeroo.domain.JFirm;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.domain.JUser;
import com.j1987.coffeeroo.framework.JDataImporter;
import com.j1987.coffeeroo.framework.JUtils;
import com.j1987.coffeeroo.framework.properties.PropertiesService;
import com.j1987.coffeeroo.services.dao.FirmService;
import com.j1987.coffeeroo.services.dao.UserService;

public class CommonInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * Logging system
	 * */
	private static Logger logger = Logger.getLogger(CommonInterceptor.class); 
	
	@Autowired
	private PropertiesService propertiesService;
	
	@Autowired
	private FirmService firmService;
	
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
//		JDataImporter.getInstance(
//				propertiesService.getSqlScript(), 
//				propertiesService.getDbDriverClassName(), 
//				propertiesService.getDbURL(), 
//				propertiesService.getDbUserName(), 
//				propertiesService.getDbPassword());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) {
			String authUser = authentication.getName();
			
			if ( !JUtils.SECURITY_UNAUTHENTICATED_USER.equals(authUser) ) {
				
				JUser user = null;
				Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();
				for (GrantedAuthority authority : authorities) {
					
					// if the current user is a MANAGER
					if (JUtils.DB_ROLE_FACTORY_MANAGER.equals(authority.getAuthority()) || JUtils.DB_ROLE_FACTORY_AGENT.equals(authority.getAuthority())) {
						
						HttpSession session = request.getSession();
						if (session != null && (session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE) == null)) {
							
							logger.debug("preHandle() - setting 'userfactorycode' attribute to the session");
							
							if (user == null) {
								List<JUser> users = userService.findUserByUserNameEquals(authUser);
								user = users.get(0);
							}
							
							session.setAttribute(JUtils.HTTP_SESSION_USER_ROLE, user.getRoleName());
							
							//Retrieves the factory of current MANAGER
							List<JFactory> factories = user.getFactories();
							
							if( !factories.isEmpty() && factories.size() <= 1){
								JFactory factory = factories.get(0);
								
								if (session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE) == null) {
									
									session.setAttribute(JUtils.HTTP_SESSION_FACTORY_CODE, factory.getCode());
									session.setAttribute(JUtils.HTTP_SESSION_FACTORY_NAME, factory.getName());
									
									logger.debug("preHandle() - actvity's session variables has been set");
								}
								
								if (session.getAttribute(JUtils.HTTP_SESSION_COMPANY_CODE) == null) {
									
									JFirm firm = firmService.findFirm(Long.valueOf("1"));
									
									session.setAttribute(JUtils.HTTP_SESSION_COMPANY_CODE, firm.getId());
									session.setAttribute(JUtils.HTTP_SESSION_COMPANY_NAME, firm.getName());
									
									logger.debug("preHandle() - firm's session variables has been set");
								}
							}
							
							
						}
						
					}else if(JUtils.DB_ROLE_ADMIN.equals(authority.getAuthority()) || JUtils.DB_ROLE_SUPERVISOR.equals(authority.getAuthority()) ){
						// if the current user is a SUPERVISOR
						
						HttpSession session = request.getSession();
						if (session != null && (session.getAttribute(JUtils.HTTP_SESSION_COMPANY_CODE) == null)) {
							
							
							logger.debug("preHandle() - setting 'userCompanyCode' attribute to the session");
							if (user == null) {
								List<JUser> users = userService.findUserByUserNameEquals(authUser);
								user = users.get(0);
							}

							session.setAttribute(JUtils.HTTP_SESSION_USER_ROLE, user.getRoleName());
							
							//Retrieves the firms of current SUPERVISOR
							List<JFirm> firms = user.getFirms();
							
							if(!firms.isEmpty() && firms.size() <=1){
								
								if (session.getAttribute(JUtils.HTTP_SESSION_COMPANY_CODE) == null) {
									
									JFirm firm = firms.get(0);
									
									session.setAttribute(JUtils.HTTP_SESSION_COMPANY_CODE, firm.getId());
									session.setAttribute(JUtils.HTTP_SESSION_COMPANY_NAME, firm.getName());
									
									logger.debug("preHandle() - firm's session variables has been set");
								}
								
							}
							
						}
						
						
						
					}
					
				}
				
			}
			
		}
		
		
		return super.preHandle(request, response, handler);
	}

}
