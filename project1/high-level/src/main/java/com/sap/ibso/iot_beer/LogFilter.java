package com.sap.ibso.iot_beer;

import org.apache.catalina.connector.RequestFacade;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
class LogFilter implements Filter
{
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String ipAddress = request.getRemoteHost();

        RequestFacade req = (RequestFacade) request;

        String userAgent = req.getHeader("user-agent");
        String url = req.getRequestURL().toString();

        System.out.println(url + " - " + ipAddress + " - " + userAgent);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}