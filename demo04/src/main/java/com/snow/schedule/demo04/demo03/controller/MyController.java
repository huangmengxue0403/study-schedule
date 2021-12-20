package com.snow.schedule.demo04.demo03.controller;

import com.snow.schedule.demo04.demo03.service.JobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/18 14:33
 */
@Controller()
@RequestMapping("/my")
public class MyController {

    @Autowired
    private JobService jobService;

    @PostMapping("/start")
    @ResponseBody
    public String start(@RequestParam Integer id) throws Exception {
        jobService.start(id);
        return "start success";
    }


    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("jobDetailList", jobService.list());
        model.addAttribute("triggerList", jobService.triggerList());
        return "task-list";
    }


    @RequestMapping("/job/updateById")
    @ResponseBody
    public String updateJobById(Integer id, Integer triggerId) {
        jobService.updateJobById(id, triggerId);
        return "success";
    }

    @RequestMapping("/trigger/updateById")
    @ResponseBody
    public String updateTriggerById(Integer id, String cron) throws Exception {
        jobService.updateTriggerById(id, cron);
        return "success";
    }

    @RequestMapping("/stop")
    @ResponseBody
    public String stop(Integer id) throws SchedulerException {
        jobService.stop(id);
        return "success";
    }
}
