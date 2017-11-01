package cn.zsy.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


public class MyInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(MyInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true;
        String ip = request.getRemoteAddr();
        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", startTime);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        logger.info("用户:" + ip + ",访问目标:" + method.getDeclaringClass().getName() + "." + method.getName());

//        User user = (User) request.getSession().getAttribute("user");
//        if (null == user) {
//            response.sendRedirect("toLogin");
//            flag = false;
//        } else {
//            flag = true;
//        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        long startTime = (Long) request.getAttribute("requestStartTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        // 打印方法执行时间
        if (executeTime > 1000) {
            logger.info("[" + method.getDeclaringClass().getName() + "." + method.getName() + "] 执行耗时 : "
                + executeTime + "ms");
        } else {
            logger.info("[" + method.getDeclaringClass().getSimpleName() + "." + method.getName() + "] 执行耗时 : "
                + executeTime + "ms");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
