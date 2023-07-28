package cc.jml1024.noname;

import cc.jml1024.noname.entity.SysUser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class BeanDefinitionTest {

    @BeforeAll
    public static void init() {
        System.out.println("测试初始化");
    }

    @AfterAll
    public static void distory() {
        System.out.println("测试销毁");
    }

    @DisplayName("测试bean定义")
    @Test
    public void test_BeanDefinition() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(SysUser.class);
        factory.registerBeanDefinition("sysUser", rootBeanDefinition);
        SysUser sysUser = factory.getBean("sysUser", SysUser.class);
        System.out.println(sysUser);
    }
}
