package com.snow.schedule.dynamic.controller;

import com.snow.schedule.dynamic.task.ScheduledTaskService;
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
    private ScheduledTaskService scheduledTaskService;

    @PostMapping("/stop")
    @ResponseBody
    public String stop(Integer id) {
        scheduledTaskService.stop(id);
        return "暂停成功";
    }

    @PostMapping("/start")
    @ResponseBody
    public String start(Integer id) {
        scheduledTaskService.start(id);
        return "启动成功";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("cronList", scheduledTaskService.list());
        return "task-list";
    }

    @RequestMapping("/updateById")
    @ResponseBody
    public String updateById(Integer id, String cron) {
        scheduledTaskService.updateById(id, cron);
        return "修改成功";
    }
}
