package demo.common.service.impl;

import demo.common.repository.po.User;
import demo.common.repository.mapper.UserMapper;
import demo.common.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户 服务实现类
 *
 * @author 水张哲
 * @date 2021/10/19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
