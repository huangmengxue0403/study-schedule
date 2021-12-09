create database my_schedule;

use my_schedule;
create table schedule_config02
(
    id          int auto_increment comment '主键id'
        primary key,
    cron        varchar(32)  default ''                not null comment 'cron表达式',
    class_name  varchar(128) default ''                not null comment '任务类全限定名',
    status      tinyint      default 0                 not null comment '状态',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    last_time   bigint       default 0                 not null comment '上次执行时间',
    next_time   bigint       default 0                 not null comment '下次执行时间'
);

insert into schedule_config02 (cron, class_name, status)
values ('0/5 * * * * ?', 'com.snow.schedule.demo03.task.impl.MyTask1', 0);

insert into schedule_config02 (cron, class_name, status)
values ('0/15 * * * * ?', 'com.snow.schedule.demo03.task.impl.MyTask2', 0);

insert into schedule_config02 (cron, class_name, status)
values ('0/15 * * * * ?', 'com.snow.schedule.demo03.task.impl.MyTask3', 0);
