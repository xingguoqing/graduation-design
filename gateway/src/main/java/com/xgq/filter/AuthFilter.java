//package com.xgq.filter;
//
//import com.google.common.base.Strings;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
//import org.springframework.http.HttpStatus;
//import responsecode.enums.CommonRespCodeEnum;
//import responsecode.response.CommonResponse;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author xingguoqing
// * @date 2018/2/7 下午3:07
// */
//@Slf4j
//public class AuthFilter extends ZuulFilter{
//    private final static String HEADER_TOKEN = "token";
//
//    private final static String OAUTH_SERVICE_ID = "authorization-server";
//
//    @Override
//    public String filterType() {
//        return "route";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() {
//
//        RequestContext ctx = RequestContext.getCurrentContext();
//
//        HttpServletRequest request = ctx.getRequest();
//        String serviceId = ctx.get(FilterConstants.SERVICE_ID_KEY).toString();
//        if (OAUTH_SERVICE_ID.equals(serviceId)) {
//            log.info("请求的serviceId为:{}", serviceId);
//            ctx.setSendZuulResponse(true);
//            ctx.setResponseStatusCode(HttpStatus.OK.value());
//            return null;
//        }
//        log.info("serviceID:{}" + serviceId);
//        String token = request.getHeader(HEADER_TOKEN);
//        if (Strings.isNullOrEmpty(token)) {
//            log.info("http header contains no token");
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//            ctx.setResponseBody(CommonRespCodeEnum.AUTH_ENABLE.toString());
//        } else {
//            log.info("http header contains token");
//            ctx.setSendZuulResponse(true);
//            ctx.setResponseStatusCode(HttpStatus.OK.value());
//        }
//        return null;
//    }
//}
