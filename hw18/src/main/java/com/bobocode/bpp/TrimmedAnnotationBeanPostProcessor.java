package com.bobocode.bpp;

import com.bobocode.annotation.Trimmed;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class TrimmedAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        var type = bean.getClass();
        if (type.isAnnotationPresent(Trimmed.class)) {
            return createProxy(bean);
        }
        return bean;
    }

    private Object createProxy(Object bean) {
        var enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallback((MethodInterceptor) (instance, method, args, proxy) -> {
            trimParams(args);
            var result = proxy.invokeSuper(instance, args);
            if (result instanceof String) {
                return ((String) result).trim();
            }
            return result;
        });

        return enhancer.create();
    }

    private void trimParams(Object[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String) {
                args[i] = ((String) args[i]).trim();
            }
        }
    }
}
