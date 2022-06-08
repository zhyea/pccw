--
--
-- 用户表
create table if not exists user
(
    id          int                not null auto_increment primary key,

    username    varchar(32) unique not null,
    password    varchar(64),
    email       varchar(64) unique not null,
    name        varchar(32),

    deleted     int                         default 0,
    op_time     timestamp          not null default current_timestamp on update current_timestamp,
    create_time datetime                    default now()
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4;