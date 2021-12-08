create database my_schedule;

use my_schedule;
create table schedule_config
(
    id          int          NOT NULL AUTO_INCREMENT COMMENT '主键id',
    cron        varchar(32)  not null default '' comment 'cron表达式',
    description varchar(128) not null default '' comment '任务描述',
    class_name  varchar(128) not null default '' comment '任务类全限定名',
    status      tinyint      not null default '0' comment '状态',
    last_time   bigint       not null default '0' comment '上次执行时间',
    next_time   bigint       not null default '0' comment '下次执行时间',
    create_time datetime     not null default CURRENT_TIMESTAMP comment '创建时间',
    update_time datetime     not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1;

insert into schedule_config (cron, description, class_name, status)
values ('0/5 * * * * ?', 'first', 'com.snow.schedule.dynamic.task.impl.MyTask1', 0);

insert into schedule_config (cron, description, class_name, status)
values ('0/15 * * * * ?', 'second', 'com.snow.schedule.dynamic.task.impl.MyTask2', 0);

insert into schedule_config (cron, description, class_name, status)
values ('0/15 * * * * ?', 'third', 'com.snow.schedule.dynamic.task.impl.MyTask3', 0);

alter table schedule_config
    add column
        next_time bigint not null default '0' comment '下次执行时间';