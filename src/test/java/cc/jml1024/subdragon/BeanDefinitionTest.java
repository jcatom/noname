package cc.jml1024.subdragon;

import cc.jml1024.subdragon.entity.SysUser;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class BeanDefinitionTest {

    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(SysUser.class);
        factory.registerBeanDefinition("sysUser", rootBeanDefinition);
        SysUser sysUser = factory.getBean("sysUser", SysUser.class);
        System.out.println(sysUser);
    }
}
