package com.houbo.test;

import com.houbo.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {

  /**
   * 测试方法：传统方式
   */
  @Test
  public void test1() throws IOException {

    //1.通过类加载器对配置文件进行加载，加载成了字节输入流，存到内存中 注意：配置文件并没有被解析。
    InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

    //2. （1）解析了配置文件，封装configuration对象，（2）创建了DefaultSqlSessionFactory工厂对象
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

    //3.问题：openSession()执行逻辑是什么？
    //3。（1）创建事务对象  （2）创建执行器对象cachingExecutor （3）创建了DefaultSqlSession对象
    SqlSession sqlSession = sqlSessionFactory.openSession();


    User user = sqlSession.selectOne("user.findUserById", 1);

    System.out.println(user);
    System.out.println("Mybatis源码环境搭建成功");

    sqlSession.close();

  }

}
