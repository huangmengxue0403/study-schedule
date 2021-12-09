package com.snow.schedule.demo02.controller;

import com.snow.schedule.demo02.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/7 17:24
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/stop")
    @ResponseBody
    public String stop(Integer id) {
        taskService.stop(id);
        return "暂停成功";
    }

    @PostMapping("/start")
    @ResponseBody
    public String start(Integer id) {
        taskService.start(id);
        return "启动成功";
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
