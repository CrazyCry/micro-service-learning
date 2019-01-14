package meng.learn.eurekazuulclient.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class MyFilter extends ZuulFilter {
    private static final Logger log= LoggerFactory.getLogger(MyFilter.class);
    @Override
    public String filterType() {
        return PRE_TYPE;//这种类型的过滤器是在请求转发之前进行逻辑判断，实际开发中，可以用此过滤器来进行安全验证。
    }

    @Override
    public int filterOrder() {
        return 0;//越小 越优先执行
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx=RequestContext.getCurrentContext();//Zuul采取动态读取、编译和运行这些过滤器，过滤器之间不能互相通信，而是通过RequestContext对象来共享数据
        HttpServletRequest requtst = ctx.getRequest();
        Object accessToken = requtst.getParameter("token");
        if (accessToken==null){
            log.warn("Token is empty!");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("Token is empty!");
            } catch (IOException e) {
                return  null;
            }
        }
        log.info("ok!");
        return null;
    }
}
