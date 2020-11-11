package com.jayden.mall.filter;

import com.jayden.mall.common.Constant;
import com.jayden.mall.model.pojo.UmsAdmin;
import com.jayden.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 描述：     管理员校验过滤器
 */
public class AdminFilter implements Filter {

    @Autowired
    UmsAdminService umsAdminService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        UmsAdmin currentUser = (UmsAdmin) session.getAttribute(Constant.MALL_USER);
        if (currentUser == null) {
            PrintWriter out = new HttpServletResponseWrapper(
                    (HttpServletResponse) servletResponse).getWriter();
            out.write("{\n"
                    + "    \"status\": 10007,\n"
                    + "    \"msg\": \"NEED_LOGIN\",\n"
                    + "    \"data\": null\n"
                    + "}");
            out.flush();
            out.close();
            return;
        }
        //校验是否是管理员
//        boolean adminRole = userService.checkAdminRole(currentUser);
//        if (!adminRole) {
//            PrintWriter out = new HttpServletResponseWrapper(
//                    (HttpServletResponse) servletResponse).getWriter();
//            out.write("{\n"
//                    + "    \"status\": 10009,\n"
//                    + "    \"msg\": \"NEED_ADMIN\",\n"
//                    + "    \"data\": null\n"
//                    + "}");
//            out.flush();
//            out.close();
//           return;
//        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
