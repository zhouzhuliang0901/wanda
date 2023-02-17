create table WWD_WORKDAY (
   ST_WORKDAY_ID varchar(50) not null,   -- 编号
   DT_DAY        datetime,   -- 特殊日
   ST_IS_WORKDAY varchar(1),   -- 是否为工作日
   primary key (ST_WORKDAY_ID)
);