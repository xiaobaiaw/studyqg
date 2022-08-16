package com.guanghui.amen.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guanghui.amen.common.Constants;
import com.guanghui.amen.common.Result;
import com.guanghui.amen.controller.dto.UserDTO;
import com.guanghui.amen.controller.dto.UserPasswordDTO;
import com.guanghui.amen.entity.Course;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.guanghui.amen.service.IUserService;
import com.guanghui.amen.entity.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 林同学
 * @since 2022-06-23
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping
    public Result save(@RequestBody User user) {
        return Result.success(userService.saveOrUpdate(user));
    }

    @PostMapping("login")
    public Result login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);

    }

    @PostMapping("password")
    public Result password(@RequestBody UserPasswordDTO userPasswordDTO) {
        userPasswordDTO.getPassword();
        userPasswordDTO.setNewPassword(SecureUtil.md5(userPasswordDTO.getNewPassword()));
        userService.updatePassword(userPasswordDTO);
        return Result.success();

    }

    @PostMapping("register")
    public Result Register(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        User register = userService.register(userDTO);
        return Result.success(register);

    }

    @GetMapping
    public Result hhd() {
        return Result.success(userService.list());
    }

    @GetMapping("role/{role}")
    public Result findUsersByRole(@PathVariable String role) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role",role);
//        List<User> list = userService.list(queryWrapper);
        return Result.success(userService.list(queryWrapper));
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(userService.removeById(id));
    }

    @PostMapping("del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(userService.removeByIds(ids));
    }

    @GetMapping("{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }

    @GetMapping("username/{username}")
    public Result findOne(@PathVariable String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return Result.success(userService.getOne(queryWrapper));
    }


    @GetMapping("page")//MybatisPlus方式实现分页查询和模糊查询
    public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String username,
                               @RequestParam(defaultValue = "") String email,
                               @RequestParam(defaultValue = "") String address
    ) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
//        queryWrapper.like(!Strings.isEmpty(username), "username", username);
//        queryWrapper.like(!Strings.isEmpty(email), "email", email);
//        queryWrapper.like(!Strings.isEmpty(address), "address", address);
        return Result.success(userService.findPage(new Page<>(pageNum, pageSize), username, email, address));
    }
//    @GetMapping("course")
//    public Result findCourse(@RequestParam List<Course> courses,@RequestParam List<Course> stuCourses){
//        return Result.success(userService.findCourse(courses,stuCourses));
//    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<User> list = userService.list();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("username", "用户名");
        writer.addHeaderAlias("password", "密码");
        writer.addHeaderAlias("nickname", "昵称");
        writer.addHeaderAlias("email", "邮箱");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("createTime", "创建时间");
        writer.addHeaderAlias("avatarUrl", "头像");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }

    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
//        List<User> list = reader.readAll(User.class);

        // 方式2：忽略表头的中文，直接读取表的内容
        List<List<Object>> list = reader.read(1);
        List<User> users = CollUtil.newArrayList();
        for (List<Object> row : list) {
            User user = new User();
            user.setUsername(row.get(0).toString());
            user.setPassword(row.get(1).toString());
            user.setNickname(row.get(2).toString());
            user.setEmail(row.get(3).toString());
            user.setPhone(row.get(4).toString());
            user.setAddress(row.get(5).toString());
            user.setAvatarUrl(row.get(6).toString());
            users.add(user);
        }

        userService.saveBatch(users);
        return Result.success(true);
    }
}

