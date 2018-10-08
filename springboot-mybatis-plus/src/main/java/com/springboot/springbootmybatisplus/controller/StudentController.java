package com.springboot.springbootmybatisplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.springbootmybatisplus.entity.Student;
import com.springboot.springbootmybatisplus.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxj
 * @since 2018-09-29
 */
@RestController
@RequestMapping("/student")
public class StudentController {


    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;

    @GetMapping("selectAll")
    public List<Student> selectAll(){
        LOGGER.debug("warn enter selectAll........");
        LOGGER.info("info enter selectAll........");
        LOGGER.warn("warn enter selectAll........");
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age");
        return studentService.list(queryWrapper);
    }

    @GetMapping("selectByCondition")
    public List<Student> selectByCondition(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","王杨帅");
        map.put("age",25);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(map);
//        queryWrapper.eq("name","王杨帅");
        return studentService.list(queryWrapper);
    }

    @GetMapping("insert")
    public Boolean insert(){
        Student student = new Student();
        student.setAddress("重庆");
        student.setAge(20);
        student.setName("林四");
        System.out.println(student);
        return studentService.save(student);
    }

    @GetMapping("updateMy")
    public int updateBy(@RequestParam("id") int id) {
        return studentService.updateMy(id);
    }

    @GetMapping("selectPageVo")
    public IPage<Student> selectPageVo(@RequestParam("name") String name) {
        Page<Student> studentPage = new Page<Student>();
        return studentService.selectPageVo(studentPage, name);
    }

    @GetMapping("selectPage")
    public IPage<Student> selectPage(@RequestParam("name") String name, @RequestParam("current") int current,@RequestParam("size") int size) {
        /**
         * <p>
         * 分页构造函数
         * </p>
         *
         * @param current 当前页
         * @param size    每页显示条数
         */
        Page<Student> studentPage = new Page<Student>(current,size);
//        studentPage.setSize(2);
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<Student>();
        System.out.println("---测试热部署1111224445556666---");
        studentQueryWrapper.likeRight("name",name);
        return studentService.page(studentPage,studentQueryWrapper);
    }

}
