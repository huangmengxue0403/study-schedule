package com.snow.schedule.demo03.controller;

import com.snow.schedule.demo03.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/9 11:20
 */
@Controller
@RequestMapping("/schedule")
public class MyController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/start")
    @ResponseBody
    public String start(Integer id) {
        taskService.start(id);
        return "success";
    }

    @PostMapping("/stop")
    @ResponseBody
    public String stop(Integer id) {
        taskService.stop(id);
        return "success";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("cronList", taskService.list());
        return "task-list";
    }

    @RequestMapping("/updateById")
    @ResponseBody
    public String updateById(Integer id, String cron) {
        taskService.updateById(id, cron);
        return "修改成功";
    }
}
