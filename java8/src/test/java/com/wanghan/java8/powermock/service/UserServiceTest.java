package com.wanghan.java8.powermock.service;

import com.wanghan.java8.powermock.common.User;
import com.wanghan.java8.powermock.dao.LocalUserDao;
import com.wanghan.java8.powermock.dao.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
/**
 * 需要改动这个service的字节码，已完成局部变量的导入
 */
@PrepareForTest(UserService.class)
public class UserServiceTest {

    private UserService userService;

    /**
     * 老式。。。。。
     */
    @Before
    public void setup(){
        userService = new UserService(new UserDao());
    }

    @Test
    public void testGetUserWithJunit() {
        int count = userService.getUser();
        assertEquals(0, count);
    }

    @Test
    public void testSaveUserWithJunit() {
        userService.saveUser(new User());
    }

    /**
     * Mockito
     */

    @Mock
    private UserDao userDao;

    @Test
    public void testGetUserWithMockito() {
        MockitoAnnotations.initMocks(this);

        //录制
        Mockito.when(userDao.get()).thenReturn(10);

        userService = new UserService(userDao);
        int count = userService.getUser();
        assertEquals(10, count);
    }

    @Test
    public void testSaveUserWithMockito() {
        userService.saveUser(new User());
    }

    /**
     * power mock
     */
    @Test
    public void testGetUserWithPowerMock() {
        UserDao userDao = PowerMockito.mock(UserDao.class);

        //录制
        PowerMockito.doReturn(10).when(userDao).get();
//        PowerMockito.when(userDao.get()).thenReturn(10);

        //测试
        userService = new UserService(userDao);
        int count = userService.getUser();

        //断言
        assertEquals(10, count);
    }

    @Test
    public void testSaveUserWithPowerMock() {
        User user = new User();

        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.doNothing().when(userDao).save(user);

        UserService userService = new UserService(userDao);

        userService.saveUser(user);

        Mockito.verify(userDao).save(user);
    }

    @Test
    public void getLocalUser() {
        try {
            UserService userService = new UserService();
            LocalUserDao userDao = PowerMockito.mock(LocalUserDao.class);
            PowerMockito.whenNew(LocalUserDao.class).withNoArguments().thenReturn(userDao);
            PowerMockito.doReturn(10).when(userDao).get();

            int result = userService.getLocalUser();
            assertEquals(10, result);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void saveLocalUser() {
    }
}