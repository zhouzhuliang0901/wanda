package reindeer.config;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reindeer.shiro.filter.AuthFilter;
import reindeer.shiro.realm.AccountRealm;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    /**
     * 安全管理器
     *
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(AccountRealm accountRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置自定义 realm.
        securityManager.setRealm(accountRealm);
        // 关闭shiro自带的session，详情见文档
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }


    /**
     * shiro过滤器
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 过滤器列表
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        //设置自定义过滤器
        filterMap.put("jwt", new AuthFilter());
        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);
        // 设置无权限时跳转的 url
        factoryBean.setUnauthorizedUrl("/unauthorized/401");
        Map<String, String> filterRuleMap = new LinkedHashMap<>();

        // 访问 /unauthorized/** 不通过JWTFilter
        filterRuleMap.put("/sms/login/**", "anon");
        // 所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/sms/frame/**", "jwt");
        filterRuleMap.put("/wdf/exam/**", "jwt");
        filterRuleMap.put("/exam/**", "jwt");
        filterRuleMap.put("/uacItemInfo/listBig.do", "jwt");
        filterRuleMap.put("/uacItemInfo/listSmall.do", "jwt");
        filterRuleMap.put("/wdf/uacUnionApply/recipienEdit", "jwt");
        filterRuleMap.put("/wdf/uacUnionApply/preTrialApply", "jwt");
        filterRuleMap.put("/wdf/uacApply/approvalApply", "jwt");
        filterRuleMap.put("/wdf/uacApply/certApply", "jwt");
        filterRuleMap.put("/wdf/uacUnionApply/addFlowTrack", "jwt");
        filterRuleMap.put("/wdf/uacUnionApply/upApplyUlstics", "jwt");

        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }


    /**
     * 添加注解支持
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


}
