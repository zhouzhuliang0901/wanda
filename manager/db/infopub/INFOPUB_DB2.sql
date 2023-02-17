--**************************************************************
--************* 请将“语句终止符”设置为字符“/” **************
--**************************************************************

--**************************************************************
--*************************** Drops ****************************
--**************************************************************

--==============================================================
-- Procedure Code: TW_QUIET_DROP_PROC                           
-- 工具：用于弥补DB2没有类似 DROP IF EXISTS 的功能。            
--==============================================================
create or replace procedure TW_QUIET_DROP_PROC ( dropSql varchar(1000) )
language sql
begin
   execute immediate dropSql;
end
/

--==============================================================
-- Table Code: INFOPUB_DEVICE_INFO                              
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_DEVICE_INFO' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_DEVICE_INFO"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_DEVICE_TYPE                              
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_DEVICE_TYPE' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_DEVICE_TYPE"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_AREA                                     
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_AREA' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_AREA"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_ATTACHMENT                               
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_ATTACHMENT' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_ATTACHMENT"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_ODEVICE_RESULT                           
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_ODEVICE_RESULT' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_ODEVICE_RESULT"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_DEVICE_RESULT                            
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_DEVICE_RESULT' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_DEVICE_RESULT"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_DEVICE_LOG                               
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_DEVICE_LOG' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_DEVICE_LOG"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_IRESOURCE                                
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_IRESOURCE' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_IRESOURCE"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_GROUP                                    
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_GROUP' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_GROUP"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_GROUP_DEVICE                             
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_GROUP_DEVICE' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_GROUP_DEVICE"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_PUBLISH                                  
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_PUBLISH' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_PUBLISH"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_PSOURCE                                  
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_PSOURCE' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_PSOURCE"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_ONOFF                                    
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_ONOFF' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_ONOFF"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_ODEVICE_STATUS                           
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_ODEVICE_STATUS' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_ODEVICE_STATUS"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_DEVICE_RESULT_HIS                        
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_DEVICE_RESULT_HIS' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_DEVICE_RESULT_HIS"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_PIC_TYPE                                 
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_PIC_TYPE' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_PIC_TYPE"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_PIC                                      
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_PIC' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_PIC"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_FUNC                                     
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_FUNC' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_FUNC"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_FUNC_TYPE                                
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_FUNC_TYPE' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_FUNC_TYPE"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_APP                                      
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_APP' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_APP"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_APP_PIC                                  
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_APP_PIC' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_APP_PIC"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_APP_FUNC                                 
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_APP_FUNC' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_APP_FUNC"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_COMPANY                                  
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_COMPANY' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_COMPANY"');
   end if;
end
/

--==============================================================
-- Table Code: INFOPUB_ADDRESS                                  
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'INFOPUB_ADDRESS' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "INFOPUB_ADDRESS"');
   end if;
end
/

--==============================================================
-- Procedure Code: TW_QUIET_DROP_PROC                           
-- 工具：用于弥补DB2没有类似 DROP IF EXISTS 的功能。            
--==============================================================
drop procedure TW_QUIET_DROP_PROC
/

--**************************************************************
--*********************** Create Tables ************************
--**************************************************************

--==============================================================
-- Table Name: 设备信息                                         
-- Table Code: INFOPUB_DEVICE_INFO                              
--==============================================================
create table "INFOPUB_DEVICE_INFO" (
   "ST_DEVICE_ID"      varchar(50) not null,   -- 设备ID
   "ST_DEVICE_NAME"    varchar(100),   -- 设备名称
   "ST_DEVICE_CODE"    varchar(50),   -- 设备编号
   "ST_DEVICE_IP"      varchar(50),   -- 设备IP
   "ST_DEVICE_MAC"     varchar(50),   -- 设备MAC
   "ST_DEVICE_ADDRESS" varchar(100),   -- 设备详细地址
   "ST_TYPE_ID"        varchar(50) not null,   -- 类型ID
   "NM_IS_HOST"        decimal(1),   -- 是否是主机
   "NM_ORDER"          decimal(8),   -- 排序
   "NM_INTERVAL"       decimal(10),   -- 监控间隔
   "NM_RECOVER"        decimal(1),   -- 是否恢复
   "NM_DOWN_TRY"       decimal(4),   -- 故障尝试次数
   "NM_NOTIFICATION"   decimal(1),   -- 是否通知
   "NM_LNG"            decimal(18,15),   -- 经度
   "NM_LAT"            decimal(18,15),   -- 纬度
   "NM_ONLINE"         decimal(1),   -- 是否在线
   "ST_CHANNEL"        varchar(50),   -- 消息通道
   "ST_CONFIG_ID"      varchar(50),   -- 终端配置
   "NM_SDTYPE"         decimal(1),   -- 设备类型（子类型）
   "ST_USER_ID"        varchar(50),   -- 用户ID
   "ST_AREA_ID"        varchar(50),   -- 区域ID
   "ST_ADDRESS_ID"     varchar(50),   -- 地址ID
   "ST_ORGAN_ID"       varchar(50),   -- 组织机构ID
   "ST_CERT_KEY"       varchar(50),   -- 证书唯一标识
   "DT_CREATE"         timestamp,   -- 创建时间
   "DT_UPDATE"         timestamp,   -- 更新时间
   "ST_DESC"           varchar(100),   -- 备注
   primary key ("ST_DEVICE_ID")
)
/

comment on column "INFOPUB_DEVICE_INFO"."ST_DEVICE_ID" is
'设备ID'
/

comment on column "INFOPUB_DEVICE_INFO"."ST_DEVICE_NAME" is
'设备名称'
/

comment on column "INFOPUB_DEVICE_INFO"."ST_DEVICE_CODE" is
'设备编号'
/

comment on column "INFOPUB_DEVICE_INFO"."ST_DEVICE_IP" is
'设备IP'
/

comment on column "INFOPUB_DEVICE_INFO"."ST_DEVICE_MAC" is
'设备MAC'
/

comment on column "INFOPUB_DEVICE_INFO"."ST_DEVICE_ADDRESS" is
'设备详细地址'
/

comment on column "INFOPUB_DEVICE_INFO"."ST_TYPE_ID" is
'类型ID'
/

comment on column "INFOPUB_DEVICE_INFO"."NM_IS_HOST" is
'是否是主机'
/

comment on column "INFOPUB_DEVICE_INFO"."NM_ORDER" is
'排序'
/

comment on column "INFOPUB_DEVICE_INFO"."NM_INTERVAL" is
'单位为秒'
/

comment on column "INFOPUB_DEVICE_INFO"."NM_RECOVER" is
'是否恢复，0：未恢复（异常），1：已恢复（正常）'
/

comment on column "INFOPUB_DEVICE_INFO"."NM_DOWN_TRY" is
'故障几次算异常'
/

comment on column "INFOPUB_DEVICE_INFO"."NM_NOTIFICATION" is
'是否通知'
/

comment on column "INFOPUB_DEVICE_INFO"."NM_LNG" is
'经度'
/

comment on column "INFOPUB_DEVICE_INFO"."NM_LAT" is
'纬度'
/

comment on column "INFOPUB_DEVICE_INFO"."NM_ONLINE" is
'是否在线，0：不在线；1：在线；'
/

comment on column "INFOPUB_DEVICE_INFO"."ST_CHANNEL" is
'消息通道'
/

comment on column "INFOPUB_DEVICE_INFO"."ST_CONFIG_ID" is
'终端配置，关联附件表'
/

comment on column "INFOPUB_DEVICE_INFO"."NM_SDTYPE" is
'设备类型，0：中心；1：延伸'
/

comment on column "INFOPUB_DEVICE_INFO"."ST_USER_ID" is
'用户ID'
/

comment on column "INFOPUB_DEVICE_INFO"."ST_AREA_ID" is
'区域ID'
/

comment on column "INFOPUB_DEVICE_INFO"."ST_ADDRESS_ID" is
'地址ID'
/

comment on column "INFOPUB_DEVICE_INFO"."ST_ORGAN_ID" is
'组织机构ID'
/

comment on column "INFOPUB_DEVICE_INFO"."ST_CERT_KEY" is
'证书唯一标识'
/

comment on column "INFOPUB_DEVICE_INFO"."DT_CREATE" is
'创建时间'
/

comment on column "INFOPUB_DEVICE_INFO"."DT_UPDATE" is
'更新时间'
/

comment on column "INFOPUB_DEVICE_INFO"."ST_DESC" is
'备注'
/

--==============================================================
-- Table Name: 设备（外设）分类                                 
-- Table Code: INFOPUB_DEVICE_TYPE                              
--==============================================================
create table "INFOPUB_DEVICE_TYPE" (
   "ST_TYPE_ID"        varchar(50) not null,   -- 类型ID
   "ST_TYPE_NAME"      varchar(50),   -- 分类名称
   "ST_TYPE_CODE"      varchar(50),   -- 分类代码
   "ST_ICON"           varchar(50),   -- 设备图标
   "ST_CLASS"          varchar(50),   -- 设备样式
   "ST_COMPANY_ID"     varchar(50),   -- 厂商ID
   "NM_DTYPE"          decimal(1),   -- 设备类型
   "ST_PARENT_TYPE_ID" varchar(50),   -- 父类型
   "NM_ORDER"          decimal(8),   -- 排序
   "DT_CREATE"         timestamp,   -- 创建时间
   "DT_UPDATE"         timestamp,   -- 更新时间
   "ST_DESC"           varchar(100),   -- 备注
   primary key ("ST_TYPE_ID")
)
/

comment on column "INFOPUB_DEVICE_TYPE"."ST_TYPE_ID" is
'类型ID'
/

comment on column "INFOPUB_DEVICE_TYPE"."ST_TYPE_NAME" is
'分类名称'
/

comment on column "INFOPUB_DEVICE_TYPE"."ST_TYPE_CODE" is
'分类代码'
/

comment on column "INFOPUB_DEVICE_TYPE"."ST_ICON" is
'设备图标'
/

comment on column "INFOPUB_DEVICE_TYPE"."ST_CLASS" is
'设备样式'
/

comment on column "INFOPUB_DEVICE_TYPE"."ST_COMPANY_ID" is
'厂商ID'
/

comment on column "INFOPUB_DEVICE_TYPE"."NM_DTYPE" is
'设备类型，0：政务终端；1：社会化终端'
/

comment on column "INFOPUB_DEVICE_TYPE"."ST_PARENT_TYPE_ID" is
'父类型，子类型均为外设'
/

comment on column "INFOPUB_DEVICE_TYPE"."NM_ORDER" is
'排序'
/

comment on column "INFOPUB_DEVICE_TYPE"."DT_CREATE" is
'创建时间'
/

comment on column "INFOPUB_DEVICE_TYPE"."DT_UPDATE" is
'更新时间'
/

comment on column "INFOPUB_DEVICE_TYPE"."ST_DESC" is
'备注'
/

--==============================================================
-- Table Name: 区域                                             
-- Table Code: INFOPUB_AREA                                     
--==============================================================
create table "INFOPUB_AREA" (
   "ST_AREA_ID"        varchar(50) not null,   -- 区域ID
   "ST_AREA_NAME"      varchar(50),   -- 区域名称
   "ST_AREA_CODE"      varchar(50),   -- 区域代码
   "ST_USER_ID"        varchar(50),   -- 管理员
   "ST_USER_NAME"      varchar(50),   -- 管理员姓名
   "ST_PHONE"          varchar(50),   -- 联系方式
   "NM_ORDER"          decimal(8),   -- 排序
   "DT_CREATE"         timestamp,   -- 创建时间
   "DT_UPDATE"         timestamp,   -- 更新时间
   "ST_PARENT_AREA_ID" varchar(50),   -- 父ID
   "ST_DESC"           varchar(100),   -- 备注
   primary key ("ST_AREA_ID")
)
/

comment on column "INFOPUB_AREA"."ST_AREA_ID" is
'区域ID'
/

comment on column "INFOPUB_AREA"."ST_AREA_NAME" is
'区域名称'
/

comment on column "INFOPUB_AREA"."ST_AREA_CODE" is
'区域代码'
/

comment on column "INFOPUB_AREA"."ST_USER_ID" is
'管理员'
/

comment on column "INFOPUB_AREA"."ST_USER_NAME" is
'管理员姓名'
/

comment on column "INFOPUB_AREA"."ST_PHONE" is
'联系方式'
/

comment on column "INFOPUB_AREA"."NM_ORDER" is
'排序'
/

comment on column "INFOPUB_AREA"."DT_CREATE" is
'创建时间'
/

comment on column "INFOPUB_AREA"."DT_UPDATE" is
'更新时间'
/

comment on column "INFOPUB_AREA"."ST_PARENT_AREA_ID" is
'父ID'
/

comment on column "INFOPUB_AREA"."ST_DESC" is
'备注'
/

--==============================================================
-- Table Name: 附件表                                           
-- Table Code: INFOPUB_ATTACHMENT                               
--==============================================================
create table "INFOPUB_ATTACHMENT" (
   "ST_ATTACH_ID"     varchar(50) not null,   -- 主键
   "ST_LINK_TABLE"    varchar(50),   -- 关联表名称
   "ST_LINK_ID"       varchar(50),   -- 关联主键值
   "ST_ATTACH_TYPE"   varchar(50),   -- 附件类型
   "ST_FILENAME"      varchar(100),   -- 文件名
   "ST_FILE_SIZE"     varchar(50),   -- 文件大小
   "BL_CONTENT"       blob(10M) not compact not logged,   -- 文件内容
   "CL_CONTENT"       clob(10M) not compact not logged,   -- 文本内容
   "BL_SMALL_CONTENT" blob(10M) not compact not logged,   -- 图片缩略图
   "ST_FILE_TYPE"     varchar(10),   -- 文件类型
   "DT_CREATE"        timestamp,   -- 创建时间
   "DT_UPDATE"        timestamp,   -- 修改时间
   primary key ("ST_ATTACH_ID")
)
/

comment on column "INFOPUB_ATTACHMENT"."ST_ATTACH_ID" is
'主键'
/

comment on column "INFOPUB_ATTACHMENT"."ST_LINK_TABLE" is
'关联表名称'
/

comment on column "INFOPUB_ATTACHMENT"."ST_LINK_ID" is
'关联主键值'
/

comment on column "INFOPUB_ATTACHMENT"."ST_ATTACH_TYPE" is
'附件类型'
/

comment on column "INFOPUB_ATTACHMENT"."ST_FILENAME" is
'文件名'
/

comment on column "INFOPUB_ATTACHMENT"."ST_FILE_SIZE" is
'文件大小'
/

comment on column "INFOPUB_ATTACHMENT"."BL_CONTENT" is
'文件内容'
/

comment on column "INFOPUB_ATTACHMENT"."CL_CONTENT" is
'文本内容'
/

comment on column "INFOPUB_ATTACHMENT"."BL_SMALL_CONTENT" is
'图片缩略图'
/

comment on column "INFOPUB_ATTACHMENT"."ST_FILE_TYPE" is
'文件类型'
/

comment on column "INFOPUB_ATTACHMENT"."DT_CREATE" is
'创建时间'
/

comment on column "INFOPUB_ATTACHMENT"."DT_UPDATE" is
'修改时间'
/

--==============================================================
-- Table Name: 外设状态结果信息                                 
-- Table Code: INFOPUB_ODEVICE_RESULT                           
--==============================================================
create table "INFOPUB_ODEVICE_RESULT" (
   "ST_OUT_DEVICE_RESULT_ID" varchar(50) not null,   -- 外设状态结果ID
   "ST_DEVICE_ID"            varchar(50),   -- 设备ID
   "ST_OUT_DEVICE_CODE"      varchar(50),   -- 外设标识
   "NM_EXCEPTION"            decimal(1),   -- 是否异常
   "ST_CAUSE"                varchar(200),   -- 异常原因
   "NM_NOTICE"               decimal(1),   -- 是否已经通知
   "NM_TOTAL"                decimal(8),   -- 总量
   "NM_REMAIN"               decimal(8),   -- 剩余量
   "DT_UPDATE"               timestamp,   -- 更新日期
   "ST_EXT1"                 varchar(50),   -- 扩展字段1
   "ST_EXT2"                 varchar(50),   -- 扩展字段2
   primary key ("ST_OUT_DEVICE_RESULT_ID")
)
/

comment on column "INFOPUB_ODEVICE_RESULT"."ST_OUT_DEVICE_RESULT_ID" is
'外设状态结果ID'
/

comment on column "INFOPUB_ODEVICE_RESULT"."ST_DEVICE_ID" is
'设备ID'
/

comment on column "INFOPUB_ODEVICE_RESULT"."ST_OUT_DEVICE_CODE" is
'外设标识'
/

comment on column "INFOPUB_ODEVICE_RESULT"."NM_EXCEPTION" is
'是否异常，0：正常；1：异常'
/

comment on column "INFOPUB_ODEVICE_RESULT"."ST_CAUSE" is
'异常原因'
/

comment on column "INFOPUB_ODEVICE_RESULT"."NM_NOTICE" is
'是否已经通知，0：未通知；1：已通知'
/

comment on column "INFOPUB_ODEVICE_RESULT"."NM_TOTAL" is
'总量'
/

comment on column "INFOPUB_ODEVICE_RESULT"."NM_REMAIN" is
'剩余量'
/

comment on column "INFOPUB_ODEVICE_RESULT"."DT_UPDATE" is
'更新日期'
/

comment on column "INFOPUB_ODEVICE_RESULT"."ST_EXT1" is
'扩展字段1'
/

comment on column "INFOPUB_ODEVICE_RESULT"."ST_EXT2" is
'扩展字段2'
/

--==============================================================
-- Table Name: 设备状态结果信息                                 
-- Table Code: INFOPUB_DEVICE_RESULT                            
--==============================================================
create table "INFOPUB_DEVICE_RESULT" (
   "ST_DEVICE_RESULT_ID" varchar(50) not null,   -- 设备状态结果ID
   "ST_DEVICE_ID"        varchar(50),   -- 设备ID
   "NM_MEM_USED"         decimal(5,2),   -- 内存使用率
   "NM_CPU_USED"         decimal(5,2),   -- CPU使用率
   "CL_HD_USED"          clob(10M) not compact not logged,   -- 磁盘使用情况
   "CL_NET_USED"         clob(10M) not compact not logged,   -- 网络使用情况
   "CL_SERVICE_USED"     clob(10M) not compact not logged,   -- 服务使用情况
   "DT_CREATE"           timestamp,   -- 创建日期
   "ST_EXT1"             varchar(50),   -- 扩展字段1
   "ST_EXT2"             varchar(50),   -- 扩展字段2
   primary key ("ST_DEVICE_RESULT_ID")
)
/

comment on column "INFOPUB_DEVICE_RESULT"."ST_DEVICE_RESULT_ID" is
'设备状态结果ID'
/

comment on column "INFOPUB_DEVICE_RESULT"."ST_DEVICE_ID" is
'设备ID'
/

comment on column "INFOPUB_DEVICE_RESULT"."NM_MEM_USED" is
'内存使用率'
/

comment on column "INFOPUB_DEVICE_RESULT"."NM_CPU_USED" is
'CPU使用率'
/

comment on column "INFOPUB_DEVICE_RESULT"."CL_HD_USED" is
'磁盘使用情况'
/

comment on column "INFOPUB_DEVICE_RESULT"."CL_NET_USED" is
'网络使用情况'
/

comment on column "INFOPUB_DEVICE_RESULT"."CL_SERVICE_USED" is
'服务使用情况'
/

comment on column "INFOPUB_DEVICE_RESULT"."DT_CREATE" is
'创建日期'
/

comment on column "INFOPUB_DEVICE_RESULT"."ST_EXT1" is
'扩展字段1'
/

comment on column "INFOPUB_DEVICE_RESULT"."ST_EXT2" is
'扩展字段2'
/

--==============================================================
-- Table Name: 设备日志记录                                     
-- Table Code: INFOPUB_DEVICE_LOG                               
--==============================================================
create table "INFOPUB_DEVICE_LOG" (
   "ST_DEVICE_LOG_ID" varchar(50) not null,   -- 设备日志ID
   "ST_DEVICE_ID"     varchar(50),   -- 设备ID
   "ST_THREAD"        varchar(50),   -- 线程号
   "ST_LEVEL"         varchar(50),   -- 日志级别
   "ST_LOGGER"        varchar(100),   -- 日志记录类名称
   "ST_OPERATOR"      varchar(50),   -- 操作者
   "ST_OPERAND"       varchar(50),   -- 操作对象
   "ST_ACTION"        varchar(50),   -- 动作类型
   "ST_LOCATION"      varchar(100),   -- 记录位置
   "ST_LINE"          varchar(50),   -- 行号
   "ST_METHOD"        varchar(50),   -- 方法
   "ST_MSG"           varchar(1000),   -- 日志消息
   "ST_EXCEPTION"     varchar(1000),   -- 异常信息
   "DT_CREATE"        timestamp,   -- 创建日期
   "ST_EXT1"          varchar(50),   -- 扩展字段1
   "ST_EXT2"          varchar(50),   -- 扩展字段2
   primary key ("ST_DEVICE_LOG_ID")
)
/

comment on column "INFOPUB_DEVICE_LOG"."ST_DEVICE_LOG_ID" is
'设备日志ID'
/

comment on column "INFOPUB_DEVICE_LOG"."ST_DEVICE_ID" is
'设备ID'
/

comment on column "INFOPUB_DEVICE_LOG"."ST_THREAD" is
'线程号'
/

comment on column "INFOPUB_DEVICE_LOG"."ST_LEVEL" is
'日志级别'
/

comment on column "INFOPUB_DEVICE_LOG"."ST_LOGGER" is
'日志记录类名称'
/

comment on column "INFOPUB_DEVICE_LOG"."ST_OPERATOR" is
'操作者'
/

comment on column "INFOPUB_DEVICE_LOG"."ST_OPERAND" is
'操作对象'
/

comment on column "INFOPUB_DEVICE_LOG"."ST_ACTION" is
'动作类型'
/

comment on column "INFOPUB_DEVICE_LOG"."ST_LOCATION" is
'记录位置'
/

comment on column "INFOPUB_DEVICE_LOG"."ST_LINE" is
'行号'
/

comment on column "INFOPUB_DEVICE_LOG"."ST_METHOD" is
'方法'
/

comment on column "INFOPUB_DEVICE_LOG"."ST_MSG" is
'日志消息'
/

comment on column "INFOPUB_DEVICE_LOG"."ST_EXCEPTION" is
'异常信息'
/

comment on column "INFOPUB_DEVICE_LOG"."DT_CREATE" is
'创建日期'
/

comment on column "INFOPUB_DEVICE_LOG"."ST_EXT1" is
'扩展字段1'
/

comment on column "INFOPUB_DEVICE_LOG"."ST_EXT2" is
'扩展字段2'
/

--==============================================================
-- Table Name: 信息资源                                         
-- Table Code: INFOPUB_IRESOURCE                                
--==============================================================
create table "INFOPUB_IRESOURCE" (
   "ST_IRESOURCE_ID"   varchar(50) not null,   -- 资源ID
   "ST_IRESOURCE_NAME" varchar(100),   -- 资源名称
   "ST_IRESOURCE_TYPE" varchar(50),   -- 资源类型
   "ST_USER_ID"        varchar(50),   -- 用户ID
   "DT_CREATE"         timestamp,   -- 创建时间
   "DT_UPDATE"         timestamp,   -- 更新时间
   "ST_DESC"           varchar(100),   -- 备注
   primary key ("ST_IRESOURCE_ID")
)
/

comment on column "INFOPUB_IRESOURCE"."ST_IRESOURCE_ID" is
'资源ID'
/

comment on column "INFOPUB_IRESOURCE"."ST_IRESOURCE_NAME" is
'资源名称'
/

comment on column "INFOPUB_IRESOURCE"."ST_IRESOURCE_TYPE" is
'资源类型'
/

comment on column "INFOPUB_IRESOURCE"."ST_USER_ID" is
'用户ID'
/

comment on column "INFOPUB_IRESOURCE"."DT_CREATE" is
'创建时间'
/

comment on column "INFOPUB_IRESOURCE"."DT_UPDATE" is
'更新时间'
/

comment on column "INFOPUB_IRESOURCE"."ST_DESC" is
'备注'
/

--==============================================================
-- Table Name: 设备分组                                         
-- Table Code: INFOPUB_GROUP                                    
--==============================================================
create table "INFOPUB_GROUP" (
   "ST_GROUP_ID"   varchar(50) not null,   -- 分组ID
   "ST_GROUP_NAME" varchar(50),   -- 设备组名称
   "ST_USER_ID"    varchar(50),   -- 用户ID
   "DT_CREATE"     timestamp,   -- 创建时间
   "DT_UPDATE"     timestamp,   -- 更新时间
   "ST_DESC"       varchar(100),   -- 备注
   "ST_AREA_ID"    varchar(50),   -- 区域ID
   primary key ("ST_GROUP_ID")
)
/

comment on column "INFOPUB_GROUP"."ST_GROUP_ID" is
'分组ID'
/

comment on column "INFOPUB_GROUP"."ST_GROUP_NAME" is
'设备组名称'
/

comment on column "INFOPUB_GROUP"."ST_USER_ID" is
'用户ID'
/

comment on column "INFOPUB_GROUP"."DT_CREATE" is
'创建时间'
/

comment on column "INFOPUB_GROUP"."DT_UPDATE" is
'更新时间'
/

comment on column "INFOPUB_GROUP"."ST_DESC" is
'备注'
/

comment on column "INFOPUB_GROUP"."ST_AREA_ID" is
'区域ID'
/

--==============================================================
-- Table Name: 设备组关联                                       
-- Table Code: INFOPUB_GROUP_DEVICE                             
--==============================================================
create table "INFOPUB_GROUP_DEVICE" (
   "ST_GROUP_ID"  varchar(50) not null,   -- 分组ID
   "ST_DEVICE_ID" varchar(50) not null,   -- 设备ID
   "NM_ORDER"     decimal(8),   -- 排序
   primary key ("ST_GROUP_ID", "ST_DEVICE_ID")
)
/

comment on column "INFOPUB_GROUP_DEVICE"."ST_GROUP_ID" is
'分组ID'
/

comment on column "INFOPUB_GROUP_DEVICE"."ST_DEVICE_ID" is
'设备ID'
/

comment on column "INFOPUB_GROUP_DEVICE"."NM_ORDER" is
'排序'
/

--==============================================================
-- Table Name: 设备发布                                         
-- Table Code: INFOPUB_PUBLISH                                  
--==============================================================
create table "INFOPUB_PUBLISH" (
   "ST_PUBLISH_ID"   varchar(50) not null,   -- 发布ID
   "ST_DEVICE_ID"    varchar(50),   -- 设备ID
   "ST_PSOURCE_ID"   varchar(50),   -- 发布源ID
   "ST_PUBLISH_NAME" varchar(50),   -- 发布名称
   "NM_PRIORITY"     decimal(4),   -- 优先级
   "ST_PTYPE"        varchar(50),   -- 类型
   "DT_PSTART"       timestamp,   -- 发布开始时间
   "DT_PEND"         timestamp,   -- 发布结束时间
   "ST_PERIOD"       varchar(50),   -- 发布周期
   "DT_PUBLISH"      timestamp,   -- 发布日期
   "DT_CREATE"       timestamp,   -- 创建时间
   "DT_UPDATE"       timestamp,   -- 更新时间
   "ST_DESC"         varchar(100),   -- 备注
   primary key ("ST_PUBLISH_ID")
)
/

comment on column "INFOPUB_PUBLISH"."ST_PUBLISH_ID" is
'发布ID'
/

comment on column "INFOPUB_PUBLISH"."ST_DEVICE_ID" is
'设备ID'
/

comment on column "INFOPUB_PUBLISH"."ST_PSOURCE_ID" is
'发布源ID'
/

comment on column "INFOPUB_PUBLISH"."ST_PUBLISH_NAME" is
'发布名称'
/

comment on column "INFOPUB_PUBLISH"."NM_PRIORITY" is
'优先级'
/

comment on column "INFOPUB_PUBLISH"."ST_PTYPE" is
'类型，WEEK：按周；DAY：按天'
/

comment on column "INFOPUB_PUBLISH"."DT_PSTART" is
'发布开始时间'
/

comment on column "INFOPUB_PUBLISH"."DT_PEND" is
'发布结束时间'
/

comment on column "INFOPUB_PUBLISH"."ST_PERIOD" is
'发布周期，固定7个数字，0代表不发布，1代表发布'
/

comment on column "INFOPUB_PUBLISH"."DT_PUBLISH" is
'发布日期'
/

comment on column "INFOPUB_PUBLISH"."DT_CREATE" is
'创建时间'
/

comment on column "INFOPUB_PUBLISH"."DT_UPDATE" is
'更新时间'
/

comment on column "INFOPUB_PUBLISH"."ST_DESC" is
'备注'
/

--==============================================================
-- Table Name: 发布源                                           
-- Table Code: INFOPUB_PSOURCE                                  
--==============================================================
create table "INFOPUB_PSOURCE" (
   "ST_PSOURCE_ID"   varchar(50) not null,   -- 发布源ID
   "ST_PSOURCE_NAME" varchar(50),   -- 发布源名称
   "NM_OFFLINE"      decimal(1),   -- 是否离线
   "ST_USER_ID"      varchar(50),   -- 用户ID
   "DT_CREATE"       timestamp,   -- 创建时间
   "DT_UPDATE"       timestamp,   -- 更新时间
   "ST_DESC"         varchar(100),   -- 备注
   primary key ("ST_PSOURCE_ID")
)
/

comment on column "INFOPUB_PSOURCE"."ST_PSOURCE_ID" is
'发布源ID'
/

comment on column "INFOPUB_PSOURCE"."ST_PSOURCE_NAME" is
'发布源名称'
/

comment on column "INFOPUB_PSOURCE"."NM_OFFLINE" is
'是否离线，0：否；1：是'
/

comment on column "INFOPUB_PSOURCE"."ST_USER_ID" is
'用户ID'
/

comment on column "INFOPUB_PSOURCE"."DT_CREATE" is
'创建时间'
/

comment on column "INFOPUB_PSOURCE"."DT_UPDATE" is
'更新时间'
/

comment on column "INFOPUB_PSOURCE"."ST_DESC" is
'备注'
/

--==============================================================
-- Table Name: 设备开关机                                       
-- Table Code: INFOPUB_ONOFF                                    
--==============================================================
create table "INFOPUB_ONOFF" (
   "ST_ONOFF_ID"  varchar(50) not null,   -- 设备开关机ID
   "ST_DEVICE_ID" varchar(50),   -- 设备ID
   "ST_PTYPE"     varchar(50),   -- 类型
   "ST_ON_TIME"   varchar(50),   -- 开机时间
   "ST_OFF_TIME"  varchar(50),   -- 关机时间
   "ST_PERIOD"    varchar(50),   -- 发布周期
   "DT_ONOFF"     timestamp,   -- 定时日期
   "DT_CREATE"    timestamp,   -- 创建时间
   "DT_UPDATE"    timestamp,   -- 更新时间
   "ST_DESC"      varchar(100),   -- 备注
   primary key ("ST_ONOFF_ID")
)
/

comment on column "INFOPUB_ONOFF"."ST_ONOFF_ID" is
'设备开关机ID'
/

comment on column "INFOPUB_ONOFF"."ST_DEVICE_ID" is
'设备ID'
/

comment on column "INFOPUB_ONOFF"."ST_PTYPE" is
'类型，WEEK：按周；DAY：按天'
/

comment on column "INFOPUB_ONOFF"."ST_ON_TIME" is
'开机时间'
/

comment on column "INFOPUB_ONOFF"."ST_OFF_TIME" is
'关机时间'
/

comment on column "INFOPUB_ONOFF"."ST_PERIOD" is
'发布周期，固定7个数字，0代表不发布，1代表发布'
/

comment on column "INFOPUB_ONOFF"."DT_ONOFF" is
'定时日期'
/

comment on column "INFOPUB_ONOFF"."DT_CREATE" is
'创建时间'
/

comment on column "INFOPUB_ONOFF"."DT_UPDATE" is
'更新时间'
/

comment on column "INFOPUB_ONOFF"."ST_DESC" is
'备注'
/

--==============================================================
-- Table Name: 外设状态                                         
-- Table Code: INFOPUB_ODEVICE_STATUS                           
--==============================================================
create table "INFOPUB_ODEVICE_STATUS" (
   "ST_OUT_DEVICE_RESULT_ID" varchar(50) not null,   -- 外设状态结果ID
   "ST_DEVICE_ID"            varchar(50),   -- 设备ID
   "ST_OUT_DEVICE_CODE"      varchar(50),   -- 外设标识
   "NM_EXCEPTION"            decimal(1),   -- 是否异常
   "ST_CAUSE"                varchar(200),   -- 异常原因
   "NM_NOTICE"               decimal(1),   -- 是否已经通知
   "NM_TOTAL"                decimal(8),   -- 总量
   "NM_REMAIN"               decimal(8),   -- 剩余量
   "NM_HIS_TOTAL"            decimal(12),   -- 历史总量
   "NM_HIS_STOTAL"           decimal(12),   -- 成功总次数
   "NM_HIS_FTOTAL"           decimal(12),   -- 失败总次数
   "DT_UPDATE"               timestamp,   -- 更新日期
   "ST_EXT1"                 varchar(50),   -- 扩展字段1
   "ST_EXT2"                 varchar(50),   -- 扩展字段2
   primary key ("ST_OUT_DEVICE_RESULT_ID")
)
/

comment on column "INFOPUB_ODEVICE_STATUS"."ST_OUT_DEVICE_RESULT_ID" is
'外设状态结果ID'
/

comment on column "INFOPUB_ODEVICE_STATUS"."ST_DEVICE_ID" is
'设备ID'
/

comment on column "INFOPUB_ODEVICE_STATUS"."ST_OUT_DEVICE_CODE" is
'外设标识'
/

comment on column "INFOPUB_ODEVICE_STATUS"."NM_EXCEPTION" is
'是否异常，0：正常；1：异常'
/

comment on column "INFOPUB_ODEVICE_STATUS"."ST_CAUSE" is
'异常原因'
/

comment on column "INFOPUB_ODEVICE_STATUS"."NM_NOTICE" is
'是否已经通知，0：未通知；1：已通知'
/

comment on column "INFOPUB_ODEVICE_STATUS"."NM_TOTAL" is
'总量'
/

comment on column "INFOPUB_ODEVICE_STATUS"."NM_REMAIN" is
'剩余量'
/

comment on column "INFOPUB_ODEVICE_STATUS"."NM_HIS_TOTAL" is
'历史总量'
/

comment on column "INFOPUB_ODEVICE_STATUS"."NM_HIS_STOTAL" is
'成功总次数'
/

comment on column "INFOPUB_ODEVICE_STATUS"."NM_HIS_FTOTAL" is
'失败总次数'
/

comment on column "INFOPUB_ODEVICE_STATUS"."DT_UPDATE" is
'更新日期'
/

comment on column "INFOPUB_ODEVICE_STATUS"."ST_EXT1" is
'扩展字段1'
/

comment on column "INFOPUB_ODEVICE_STATUS"."ST_EXT2" is
'扩展字段2'
/

--==============================================================
-- Table Name: 设备状态结果历史                                 
-- Table Code: INFOPUB_DEVICE_RESULT_HIS                        
--==============================================================
create table "INFOPUB_DEVICE_RESULT_HIS" (
   "ST_DEVICE_RESULT_ID" varchar(50) not null,   -- 设备状态结果ID
   "ST_DEVICE_ID"        varchar(50),   -- 设备ID
   "NM_MEM_USED"         decimal(5,2),   -- 内存使用率
   "NM_CPU_USED"         decimal(5,2),   -- CPU使用率
   "CL_HD_USED"          clob(10M) not compact not logged,   -- 磁盘使用情况
   "CL_NET_USED"         clob(10M) not compact not logged,   -- 网络使用情况
   "CL_SERVICE_USED"     clob(10M) not compact not logged,   -- 服务使用情况
   "DT_CREATE"           timestamp,   -- 创建日期
   "ST_EXT1"             varchar(50),   -- 扩展字段1
   "ST_EXT2"             varchar(50),   -- 扩展字段2
   primary key ("ST_DEVICE_RESULT_ID")
)
/

comment on column "INFOPUB_DEVICE_RESULT_HIS"."ST_DEVICE_RESULT_ID" is
'设备状态结果ID'
/

comment on column "INFOPUB_DEVICE_RESULT_HIS"."ST_DEVICE_ID" is
'设备ID'
/

comment on column "INFOPUB_DEVICE_RESULT_HIS"."NM_MEM_USED" is
'内存使用率'
/

comment on column "INFOPUB_DEVICE_RESULT_HIS"."NM_CPU_USED" is
'CPU使用率'
/

comment on column "INFOPUB_DEVICE_RESULT_HIS"."CL_HD_USED" is
'磁盘使用情况'
/

comment on column "INFOPUB_DEVICE_RESULT_HIS"."CL_NET_USED" is
'网络使用情况'
/

comment on column "INFOPUB_DEVICE_RESULT_HIS"."CL_SERVICE_USED" is
'服务使用情况'
/

comment on column "INFOPUB_DEVICE_RESULT_HIS"."DT_CREATE" is
'创建日期'
/

comment on column "INFOPUB_DEVICE_RESULT_HIS"."ST_EXT1" is
'扩展字段1'
/

comment on column "INFOPUB_DEVICE_RESULT_HIS"."ST_EXT2" is
'扩展字段2'
/

--==============================================================
-- Table Name: 图片集类型                                       
-- Table Code: INFOPUB_PIC_TYPE                                 
--==============================================================
create table "INFOPUB_PIC_TYPE" (
   "ST_PIC_TYPE_ID" varchar(50) not null,   -- 图片集类型ID
   "ST_TYPE_CODE"   varchar(50),   -- 类型代码
   "ST_TYPE_NAME"   varchar(50),   -- 类型名称
   "DT_CREATE"      timestamp,   -- 创建时间
   "DT_UPDATE"      timestamp,   -- 更新时间
   "ST_DESC"        varchar(50),   -- 备注
   primary key ("ST_PIC_TYPE_ID")
)
/

comment on column "INFOPUB_PIC_TYPE"."ST_PIC_TYPE_ID" is
'图片集类型ID'
/

comment on column "INFOPUB_PIC_TYPE"."ST_TYPE_CODE" is
'类型代码'
/

comment on column "INFOPUB_PIC_TYPE"."ST_TYPE_NAME" is
'类型名称'
/

comment on column "INFOPUB_PIC_TYPE"."DT_CREATE" is
'创建时间'
/

comment on column "INFOPUB_PIC_TYPE"."DT_UPDATE" is
'更新时间'
/

comment on column "INFOPUB_PIC_TYPE"."ST_DESC" is
'备注'
/

--==============================================================
-- Table Name: 图片集                                           
-- Table Code: INFOPUB_PIC                                      
--==============================================================
create table "INFOPUB_PIC" (
   "ST_PIC_ID"      varchar(50) not null,   -- 图片集ID
   "ST_PIC_TYPE_ID" varchar(50),   -- 图片集类型ID
   "ST_ATTACH_ID"   varchar(50),   -- 附件ID
   "ST_PIC_NAME"    varchar(50),   -- 图片名称
   "ST_PIC_URL"     varchar(200),   -- 图片链接
   "ST_SKIP_URL"    varchar(200),   -- 跳转链接
   "ST_SKIP_MODULE" varchar(50),   -- 跳转模块
   "NM_ORDER"       decimal(4),   -- 图片排序
   "ST_DESC"        varchar(200),   -- 图片描述
   "DT_CREATE"      timestamp,   -- 创建时间
   "NM_STATUS"      decimal(1),   -- 状态
   "ST_CREATOR"     varchar(50),   -- 创建人
   "DT_UPDATE"      timestamp,   -- 更新时间
   "ST_AUDITOR"     varchar(50),   -- 审核人
   "DT_AUDIT"       timestamp,   -- 审核时间
   primary key ("ST_PIC_ID")
)
/

comment on column "INFOPUB_PIC"."ST_PIC_ID" is
'图片集ID'
/

comment on column "INFOPUB_PIC"."ST_PIC_TYPE_ID" is
'图片集类型ID'
/

comment on column "INFOPUB_PIC"."ST_ATTACH_ID" is
'附件ID'
/

comment on column "INFOPUB_PIC"."ST_PIC_NAME" is
'图片名称'
/

comment on column "INFOPUB_PIC"."ST_PIC_URL" is
'图片链接，可能该图片链接其他系统'
/

comment on column "INFOPUB_PIC"."ST_SKIP_URL" is
'跳转链接'
/

comment on column "INFOPUB_PIC"."ST_SKIP_MODULE" is
'跳转模块'
/

comment on column "INFOPUB_PIC"."NM_ORDER" is
'图片排序'
/

comment on column "INFOPUB_PIC"."ST_DESC" is
'图片描述'
/

comment on column "INFOPUB_PIC"."DT_CREATE" is
'创建时间'
/

comment on column "INFOPUB_PIC"."NM_STATUS" is
'状态，0：未审核；1：审核通过；2：已发布；3：已禁用；9：已删除'
/

comment on column "INFOPUB_PIC"."ST_CREATOR" is
'创建人'
/

comment on column "INFOPUB_PIC"."DT_UPDATE" is
'更新时间'
/

comment on column "INFOPUB_PIC"."ST_AUDITOR" is
'审核人'
/

comment on column "INFOPUB_PIC"."DT_AUDIT" is
'审核时间'
/

--==============================================================
-- Table Name: 功能模块                                         
-- Table Code: INFOPUB_FUNC                                     
--==============================================================
create table "INFOPUB_FUNC" (
   "ST_FUNC_ID"      varchar(50) not null,   -- 功能模块ID
   "ST_FUNC_TYPE_ID" varchar(50),   -- 功能模块类型ID
   "ST_FUNC_CODE"    varchar(50),   -- 模块代码
   "ST_FUNC_NAME"    varchar(50),   -- 模块名称
   "ST_TITLE_ONE"    varchar(50),   -- 一级标题
   "ST_TITLE_TWO"    varchar(50),   -- 二级标题
   "ST_SKIP_URL"     varchar(200),   -- 跳转链接
   "ST_SKIP_MODULE"  varchar(50),   -- 跳转模块
   "ST_ICON"         varchar(50),   -- 小图标
   "NM_EURL"         decimal(1),   -- 是否是第三方url
   "ST_PARENT_ID"    varchar(50),   -- 父级ID
   "NM_ORDER"        decimal(4),   -- 排序
   "DT_CREATE"       timestamp,   -- 创建时间
   "NM_STATUS"       decimal(1),   -- 状态
   "ST_CREATOR"      varchar(50),   -- 创建人
   "DT_UPDATE"       timestamp,   -- 更新时间
   "ST_AUDITOR"      varchar(50),   -- 审核人
   "DT_AUDIT"        timestamp,   -- 审核时间
   "ST_DESC"         varchar(200),   -- 功能简介
   "ST_PIC_PATH"     varchar(50),   -- 图片路径
   primary key ("ST_FUNC_ID")
)
/

comment on column "INFOPUB_FUNC"."ST_FUNC_ID" is
'功能模块ID'
/

comment on column "INFOPUB_FUNC"."ST_FUNC_TYPE_ID" is
'功能模块类型ID'
/

comment on column "INFOPUB_FUNC"."ST_FUNC_CODE" is
'模块代码'
/

comment on column "INFOPUB_FUNC"."ST_FUNC_NAME" is
'模块名称'
/

comment on column "INFOPUB_FUNC"."ST_TITLE_ONE" is
'一级标题'
/

comment on column "INFOPUB_FUNC"."ST_TITLE_TWO" is
'二级标题'
/

comment on column "INFOPUB_FUNC"."ST_SKIP_URL" is
'跳转链接'
/

comment on column "INFOPUB_FUNC"."ST_SKIP_MODULE" is
'跳转模块'
/

comment on column "INFOPUB_FUNC"."ST_ICON" is
'小图标，可以是url或者css样式等'
/

comment on column "INFOPUB_FUNC"."NM_EURL" is
'是否是第三方url，0：否；1：是'
/

comment on column "INFOPUB_FUNC"."ST_PARENT_ID" is
'父级ID'
/

comment on column "INFOPUB_FUNC"."NM_ORDER" is
'排序'
/

comment on column "INFOPUB_FUNC"."DT_CREATE" is
'创建时间'
/

comment on column "INFOPUB_FUNC"."NM_STATUS" is
'状态，0：未审核；1：审核通过；2：已发布；3：已禁用；9：已删除'
/

comment on column "INFOPUB_FUNC"."ST_CREATOR" is
'创建人'
/

comment on column "INFOPUB_FUNC"."DT_UPDATE" is
'更新时间'
/

comment on column "INFOPUB_FUNC"."ST_AUDITOR" is
'审核人'
/

comment on column "INFOPUB_FUNC"."DT_AUDIT" is
'审核时间'
/

comment on column "INFOPUB_FUNC"."ST_DESC" is
'功能简介'
/

comment on column "INFOPUB_FUNC"."ST_PIC_PATH" is
'图片路径'
/

--==============================================================
-- Table Name: 功能模块类型                                     
-- Table Code: INFOPUB_FUNC_TYPE                                
--==============================================================
create table "INFOPUB_FUNC_TYPE" (
   "ST_FUNC_TYPE_ID" varchar(50) not null,   -- 功能模块类型ID
   "ST_TYPE_CODE"    varchar(50),   -- 类型代码
   "ST_TYPE_NAME"    varchar(50),   -- 类型名称
   "DT_CREATE"       timestamp,   -- 创建时间
   "DT_UPDATE"       timestamp,   -- 更新时间
   "ST_DESC"         varchar(50),   -- 备注
   primary key ("ST_FUNC_TYPE_ID")
)
/

comment on column "INFOPUB_FUNC_TYPE"."ST_FUNC_TYPE_ID" is
'功能模块类型ID'
/

comment on column "INFOPUB_FUNC_TYPE"."ST_TYPE_CODE" is
'类型代码'
/

comment on column "INFOPUB_FUNC_TYPE"."ST_TYPE_NAME" is
'类型名称'
/

comment on column "INFOPUB_FUNC_TYPE"."DT_CREATE" is
'创建时间'
/

comment on column "INFOPUB_FUNC_TYPE"."DT_UPDATE" is
'更新时间'
/

comment on column "INFOPUB_FUNC_TYPE"."ST_DESC" is
'备注'
/

--==============================================================
-- Table Name: 应用                                             
-- Table Code: INFOPUB_APP                                      
--==============================================================
create table "INFOPUB_APP" (
   "ST_APP_ID"   varchar(50) not null,   -- 应用ID
   "ST_APP_NAME" varchar(100),   -- 应用名称
   "ST_APP_CODE" varchar(50),   -- 应用代码
   "ST_APP_URL"  varchar(50),   -- 应用URL
   "DT_CREATE"   timestamp,   -- 创建时间
   "NM_STATUS"   decimal(1),   -- 状态
   "ST_CREATOR"  varchar(50),   -- 创建人
   "DT_UPDATE"   timestamp,   -- 更新时间
   "ST_AUDITOR"  varchar(50),   -- 审核人
   "DT_AUDIT"    timestamp,   -- 审核时间
   "ST_DESC"     varchar(200),   -- 功能简介
   "ST_PIC_PATH" varchar(50),   -- 图片路径
   primary key ("ST_APP_ID")
)
/

comment on column "INFOPUB_APP"."ST_APP_ID" is
'应用ID'
/

comment on column "INFOPUB_APP"."ST_APP_NAME" is
'应用名称'
/

comment on column "INFOPUB_APP"."ST_APP_CODE" is
'应用代码'
/

comment on column "INFOPUB_APP"."ST_APP_URL" is
'应用URL'
/

comment on column "INFOPUB_APP"."DT_CREATE" is
'创建时间'
/

comment on column "INFOPUB_APP"."NM_STATUS" is
'状态，0：未审核；1：审核通过；2：已发布；3：已禁用；9：已删除'
/

comment on column "INFOPUB_APP"."ST_CREATOR" is
'创建人'
/

comment on column "INFOPUB_APP"."DT_UPDATE" is
'更新时间'
/

comment on column "INFOPUB_APP"."ST_AUDITOR" is
'审核人'
/

comment on column "INFOPUB_APP"."DT_AUDIT" is
'审核时间'
/

comment on column "INFOPUB_APP"."ST_DESC" is
'功能简介'
/

comment on column "INFOPUB_APP"."ST_PIC_PATH" is
'图片路径'
/

--==============================================================
-- Table Name: 应用关联图片                                     
-- Table Code: INFOPUB_APP_PIC                                  
--==============================================================
create table "INFOPUB_APP_PIC" (
   "ST_APP_ID" varchar(50) not null,   -- 应用ID
   "ST_PIC_ID" varchar(50) not null,   -- 图片集ID
   "NM_ORDER"  decimal(4),   -- 排序
   primary key ("ST_APP_ID", "ST_PIC_ID")
)
/

comment on column "INFOPUB_APP_PIC"."ST_APP_ID" is
'应用ID'
/

comment on column "INFOPUB_APP_PIC"."ST_PIC_ID" is
'图片集ID'
/

comment on column "INFOPUB_APP_PIC"."NM_ORDER" is
'排序'
/

--==============================================================
-- Table Name: 应用关联模块                                     
-- Table Code: INFOPUB_APP_FUNC                                 
--==============================================================
create table "INFOPUB_APP_FUNC" (
   "ST_APP_ID"  varchar(50) not null,   -- 应用ID
   "ST_FUNC_ID" varchar(50) not null,   -- 功能模块ID
   "NM_ORDER"   decimal(4),   -- 排序
   primary key ("ST_APP_ID", "ST_FUNC_ID")
)
/

comment on column "INFOPUB_APP_FUNC"."ST_APP_ID" is
'应用ID'
/

comment on column "INFOPUB_APP_FUNC"."ST_FUNC_ID" is
'功能模块ID'
/

comment on column "INFOPUB_APP_FUNC"."NM_ORDER" is
'排序'
/

--==============================================================
-- Table Name: 设备厂商                                         
-- Table Code: INFOPUB_COMPANY                                  
--==============================================================
create table "INFOPUB_COMPANY" (
   "ST_COMPANY_ID"   varchar(50) not null,   -- 厂商ID
   "ST_COMPANY_CODE" varchar(50),   -- 厂商编码
   "ST_COMPANY_NAME" varchar(50),   -- 厂商名称
   "ST_CONTACT_NAME" varchar(50),   -- 厂商负责人姓名
   "ST_CONTACT_TEL"  varchar(50),   -- 厂商负责人联系方式
   "NM_ORDER"        decimal(1),   -- 排序
   "DT_CREATE"       timestamp,   -- 创建时间
   "DT_UPDATE"       timestamp,   -- 更新时间
   primary key ("ST_COMPANY_ID")
)
/

comment on column "INFOPUB_COMPANY"."ST_COMPANY_ID" is
'厂商ID'
/

comment on column "INFOPUB_COMPANY"."ST_COMPANY_CODE" is
'厂商编码'
/

comment on column "INFOPUB_COMPANY"."ST_COMPANY_NAME" is
'厂商名称'
/

comment on column "INFOPUB_COMPANY"."ST_CONTACT_NAME" is
'厂商负责人姓名'
/

comment on column "INFOPUB_COMPANY"."ST_CONTACT_TEL" is
'厂商负责人联系方式'
/

comment on column "INFOPUB_COMPANY"."NM_ORDER" is
'排序'
/

comment on column "INFOPUB_COMPANY"."DT_CREATE" is
'创建时间'
/

comment on column "INFOPUB_COMPANY"."DT_UPDATE" is
'更新时间'
/

--==============================================================
-- Table Name: 地址表（办理点）                                 
-- Table Code: INFOPUB_ADDRESS                                  
--==============================================================
create table "INFOPUB_ADDRESS" (
   "ST_ADDRESS_ID" varchar(50) not null,   -- 地址ID
   "ST_ADDRESS"    varchar(200),   -- 地址名称
   "ST_LABEL"      varchar(200),   -- 地址别名
   "NM_LNG"        decimal(18,15),   -- 经度
   "NM_LAT"        decimal(18,15),   -- 纬度
   "ST_CITY"       varchar(50),   -- 市
   "ST_DISTRICT"   varchar(50),   -- 区
   "ST_STREET"     varchar(50),   -- 街道
   "DT_CREATE"     timestamp,   -- 创建时间
   "DT_UPDATE"     timestamp,   -- 更新时间
   primary key ("ST_ADDRESS_ID")
)
/

comment on column "INFOPUB_ADDRESS"."ST_ADDRESS_ID" is
'地址ID'
/

comment on column "INFOPUB_ADDRESS"."ST_ADDRESS" is
'地址名称'
/

comment on column "INFOPUB_ADDRESS"."ST_LABEL" is
'地址别名'
/

comment on column "INFOPUB_ADDRESS"."NM_LNG" is
'经度'
/

comment on column "INFOPUB_ADDRESS"."NM_LAT" is
'纬度'
/

comment on column "INFOPUB_ADDRESS"."ST_CITY" is
'市'
/

comment on column "INFOPUB_ADDRESS"."ST_DISTRICT" is
'区'
/

comment on column "INFOPUB_ADDRESS"."ST_STREET" is
'街道'
/

comment on column "INFOPUB_ADDRESS"."DT_CREATE" is
'创建时间'
/

comment on column "INFOPUB_ADDRESS"."DT_UPDATE" is
'更新时间'
/

