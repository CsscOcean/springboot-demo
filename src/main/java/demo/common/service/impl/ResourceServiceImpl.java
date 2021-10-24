package demo.common.service.impl;

import demo.common.repository.po.Resource;
import demo.common.repository.mapper.ResourceMapper;
import demo.common.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 资源 服务实现类
 *
 * @author 水张哲
 * @date 2021/10/23
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

}
