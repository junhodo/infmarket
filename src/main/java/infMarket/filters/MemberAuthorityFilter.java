package infMarket.filters;

import infMarket.models.mybatis.dto.Member;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

public class MemberAuthorityFilter implements Filter {
    private FilterConfig filterConfig;
    private static String MEMBER_SESSION_ID = "auth";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        Member member = (Member) httpServletRequest.getSession().getAttribute(MEMBER_SESSION_ID);
        if (member == null) {
            out.println("<script>alert('먼저 로그인해주세요!'); location.href='/login';</script>");
        }else{
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
