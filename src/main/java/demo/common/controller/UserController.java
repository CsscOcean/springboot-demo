package demo.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 用户 前端控制器
 *
 * @author 水张哲
 * @date 2021/10/19
 */
@Tag(name = "用户 前端控制器")
@RestController
@RequestMapping("/user")
public class UserController {

}
