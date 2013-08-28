package net.ion.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13. 7. 22
 * Time: 오후 5:12
 * To change this template use File | Settings | File Templates.
 */
public class UserCharsetFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException, ServletException {
        if(request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
            System.out.println("Charset : null");
        } else
            System.out.println("Charset : "+request.getCharacterEncoding().toString());
        chain.doFilter(request,response);
    }

    public void destroy() {
    }
}
