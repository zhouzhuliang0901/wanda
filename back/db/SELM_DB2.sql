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
-- Table Code: SELM_MACHINE                                     
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'SELM_MACHINE' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "SELM_MACHINE"');
   end if;
end
/

--==============================================================
-- Table Code: SELM_DELIVERY                                    
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'SELM_DELIVERY' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "SELM_DELIVERY"');
   end if;
end
/

--==============================================================
-- Table Code: SELM_OPINION                                     
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'SELM_OPINION' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "SELM_OPINION"');
   end if;
end
/

--==============================================================
-- Table Code: SELM_DELIVERY_HISTORY                            
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'SELM_DELIVERY_HISTORY' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "SELM_DELIVERY_HISTORY"');
   end if;
end
/

--==============================================================
-- Table Code: SELM_ITEM                                        
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'SELM_ITEM' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "SELM_ITEM"');
   end if;
end
/

--==============================================================
-- Table Code: SELM_ATTACH                                      
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'SELM_ATTACH' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "SELM_ATTACH"');
   end if;
end
/

--==============================================================
-- Table Code: SELM_ITEM_TYPE                                   
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'SELM_ITEM_TYPE' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "SELM_ITEM_TYPE"');
   end if;
end
/

--==============================================================
-- Table Code: SELM_ITEM_LINK                                   
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'SELM_ITEM_LINK' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "SELM_ITEM_LINK"');
   end if;
end
/

--==============================================================
-- Table Code: SELM_PERSONAL_DOCUMENT                           
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'SELM_PERSONAL_DOCUMENT' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "SELM_PERSONAL_DOCUMENT"');
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
-- Table Name: 自助设备                                         
-- Table Code: SELM_MACHINE                                     
--==============================================================
create table "SELM_MACHINE" (
   "ST_MACHINE_ID"      varchar(50) not null,   -- 设备ID
   "ST_MACHINE_NAME"    varchar(50),   -- 设备名称
   "ST_MACHINE_UNIQUE"  varchar(50),   -- 设备唯一标识
   "ST_MACHINE_ADDRESS" varchar(100),   -- 设备位置
   "ST_DESC"            varchar(100),   -- 设备描述
   "NM_TYPE"            decimal(1),   -- 设备类型
   "ST_VERSION"         varchar(50),   -- 版本
   "ST_FORM_CAT_ID"     varchar(50),   -- 取表目录ID
   "NM_LNG"             decimal(18,15),   -- 经度
   "NM_LAT"             decimal(18,15),   -- 纬度
   "NM_ONLINE"          decimal(1),   -- 是否在线
   "ST_EXT1"            varchar(50),   -- 扩展字段1
   "ST_EXT2"            varchar(50),   -- 扩展字段2
   primary key ("ST_MACHINE_ID")
)
/

comment on column "SELM_MACHINE"."ST_MACHINE_ID" is
'设备ID'
/

comment on column "SELM_MACHINE"."ST_MACHINE_NAME" is
'设备名称'
/

comment on column "SELM_MACHINE"."ST_MACHINE_UNIQUE" is
'设备唯一标识'
/

comment on column "SELM_MACHINE"."ST_MACHINE_ADDRESS" is
'设备位置'
/

comment on column "SELM_MACHINE"."ST_DESC" is
'设备描述'
/

comment on column "SELM_MACHINE"."NM_TYPE" is
'设备类型，0：自助机；2：工作台'
/

comment on column "SELM_MACHINE"."ST_VERSION" is
'版本，V1.0'
/

comment on column "SELM_MACHINE"."ST_FORM_CAT_ID" is
'取表目录ID'
/

comment on column "SELM_MACHINE"."NM_LNG" is
'经度'
/

comment on column "SELM_MACHINE"."NM_LAT" is
'纬度'
/

comment on column "SELM_MACHINE"."NM_ONLINE" is
'是否在线，0：不在线；1：在线'
/

comment on column "SELM_MACHINE"."ST_EXT1" is
'扩展字段1'
/

comment on column "SELM_MACHINE"."ST_EXT2" is
'扩展字段2'
/

--==============================================================
-- Table Name: 快递柜                                           
-- Table Code: SELM_DELIVERY                                    
--==============================================================
create table "SELM_DELIVERY" (
   "ST_DELIVERY_ID"     varchar(50) not null,   -- 快递柜ID
   "ST_MACHINE_ID"      varchar(50),   -- 设备ID
   "ST_CABINET_NO"      varchar(50),   -- 设备柜号
   "ST_CERT_FLOW_NO"    varchar(50),   -- 取证号
   "ST_RECEIVER_PHONE"  varchar(50),   -- 收件人手机号
   "ST_RECEIVER_NAME"   varchar(50),   -- 收件人姓名
   "ST_RECEIVER_IDCARD" varchar(50),   -- 收件人身份证号
   "ST_SENDER_ID"       varchar(50),   -- 投件人（用户ID）
   "ST_SENDER_NAME"     varchar(50),   -- 投件人姓名
   "ST_CERT_NAME"       varchar(50),   -- 证照名称
   "NM_TYPE"            decimal(1),   -- 类型
   "ST_APPLY_ID"        varchar(50),   -- 关联办件
   "ST_NAME"            varchar(100),   -- 企业/个人 名称
   "NM_STATUS"          decimal(1),   -- 状态
   "ST_RECEIVE_NUM"     varchar(50),   -- 取件码
   "ST_DESC"            varchar(200),   -- 描述
   "DT_CREATE"          timestamp,   -- 创建时间
   "DT_STORE"           timestamp,   -- 投放时间
   "DT_TAKE"            timestamp,   -- 取走时间
   "ST_EXT1"            varchar(50),   -- 扩展字段1
   "ST_EXT2"            varchar(50),   -- 扩展字段2
   primary key ("ST_DELIVERY_ID")
)
/

comment on column "SELM_DELIVERY"."ST_DELIVERY_ID" is
'快递柜ID'
/

comment on column "SELM_DELIVERY"."ST_MACHINE_ID" is
'设备ID'
/

comment on column "SELM_DELIVERY"."ST_CABINET_NO" is
'设备柜号'
/

comment on column "SELM_DELIVERY"."ST_CERT_FLOW_NO" is
'取证号'
/

comment on column "SELM_DELIVERY"."ST_RECEIVER_PHONE" is
'收件人手机号'
/

comment on column "SELM_DELIVERY"."ST_RECEIVER_NAME" is
'收件人姓名'
/

comment on column "SELM_DELIVERY"."ST_RECEIVER_IDCARD" is
'收件人身份证号'
/

comment on column "SELM_DELIVERY"."ST_SENDER_ID" is
'投件人（用户ID）'
/

comment on column "SELM_DELIVERY"."ST_SENDER_NAME" is
'投件人姓名'
/

comment on column "SELM_DELIVERY"."ST_CERT_NAME" is
'证照名称'
/

comment on column "SELM_DELIVERY"."NM_TYPE" is
'类型，0：企业；1：个人'
/

comment on column "SELM_DELIVERY"."ST_APPLY_ID" is
'关联办件'
/

comment on column "SELM_DELIVERY"."ST_NAME" is
'企业/个人 名称'
/

comment on column "SELM_DELIVERY"."NM_STATUS" is
'状态；0：待存；1：待取；2：已取'
/

comment on column "SELM_DELIVERY"."ST_RECEIVE_NUM" is
'取件码'
/

comment on column "SELM_DELIVERY"."ST_DESC" is
'描述'
/

comment on column "SELM_DELIVERY"."DT_CREATE" is
'创建时间'
/

comment on column "SELM_DELIVERY"."DT_STORE" is
'投放时间'
/

comment on column "SELM_DELIVERY"."DT_TAKE" is
'取走时间'
/

comment on column "SELM_DELIVERY"."ST_EXT1" is
'扩展字段1'
/

comment on column "SELM_DELIVERY"."ST_EXT2" is
'扩展字段2'
/

--==============================================================
-- Table Name: 意见反馈表                                       
-- Table Code: SELM_OPINION                                     
--==============================================================
create table "SELM_OPINION" (
   "ST_OPINION_ID"  varchar(50) not null,   -- 意见表ID
   "ST_MACHINE_ID"  varchar(50),   -- 设备ID
   "ST_UNAME"       varchar(50),   -- 姓名
   "ST_PHONE"       varchar(50),   -- 手机号
   "ST_UNIT"        varchar(100),   -- 单位名称
   "ST_CONTENT"     varchar(500),   -- 内容
   "NM_SATISFATION" decimal(1),   -- 满意度评价
   "DT_CREATE"      timestamp,   -- 创建时间
   "DT_UPDATE"      timestamp,   -- 修改时间
   "ST_EXT1"        varchar(50),   -- 扩展字段1
   "ST_EXT2"        varchar(50),   -- 扩展字段2
   primary key ("ST_OPINION_ID")
)
/

comment on column "SELM_OPINION"."ST_OPINION_ID" is
'意见表ID'
/

comment on column "SELM_OPINION"."ST_MACHINE_ID" is
'设备ID'
/

comment on column "SELM_OPINION"."ST_UNAME" is
'姓名'
/

comment on column "SELM_OPINION"."ST_PHONE" is
'手机号'
/

comment on column "SELM_OPINION"."ST_UNIT" is
'单位名称'
/

comment on column "SELM_OPINION"."ST_CONTENT" is
'内容'
/

comment on column "SELM_OPINION"."NM_SATISFATION" is
'满意度评价，1：非常满意；2：满意；3：一般；4：不满意'
/

comment on column "SELM_OPINION"."DT_CREATE" is
'创建时间'
/

comment on column "SELM_OPINION"."DT_UPDATE" is
'修改时间'
/

comment on column "SELM_OPINION"."ST_EXT1" is
'扩展字段1'
/

comment on column "SELM_OPINION"."ST_EXT2" is
'扩展字段2'
/

--==============================================================
-- Table Name: 快递柜历史                                       
-- Table Code: SELM_DELIVERY_HISTORY                            
--==============================================================
create table "SELM_DELIVERY_HISTORY" (
   "ST_DELIVERY_ID"     varchar(50) not null,   -- 快递柜ID
   "ST_MACHINE_ID"      varchar(50),   -- 设备ID
   "ST_CABINET_NO"      varchar(50),   -- 设备柜号
   "ST_CERT_FLOW_NO"    varchar(50),   -- 取证号
   "ST_RECEIVER_PHONE"  varchar(50),   -- 收件人手机号
   "ST_RECEIVER_NAME"   varchar(50),   -- 收件人姓名
   "ST_RECEIVER_IDCARD" varchar(50),   -- 收件人身份证号
   "ST_SENDER_ID"       varchar(50),   -- 投件人（用户ID）
   "ST_SENDER_NAME"     varchar(50),   -- 投件人姓名
   "ST_CERT_NAME"       varchar(50),   -- 证照名称
   "NM_TYPE"            decimal(1),   -- 类型
   "ST_APPLY_ID"        varchar(50),   -- 关联办件
   "ST_NAME"            varchar(100),   -- 企业/个人 名称
   "NM_STATUS"          decimal(1),   -- 状态
   "ST_RECEIVE_NUM"     varchar(50),   -- 取件码
   "ST_DESC"            varchar(200),   -- 描述
   "DT_CREATE"          timestamp,   -- 创建时间
   "DT_STORE"           timestamp,   -- 投放时间
   "DT_TAKE"            timestamp,   -- 取走时间
   "ST_EXT1"            varchar(50),   -- 扩展字段1
   "ST_EXT2"            varchar(50),   -- 扩展字段2
   primary key ("ST_DELIVERY_ID")
)
/

comment on column "SELM_DELIVERY_HISTORY"."ST_DELIVERY_ID" is
'快递柜ID'
/

comment on column "SELM_DELIVERY_HISTORY"."ST_MACHINE_ID" is
'设备ID'
/

comment on column "SELM_DELIVERY_HISTORY"."ST_CABINET_NO" is
'设备柜号'
/

comment on column "SELM_DELIVERY_HISTORY"."ST_CERT_FLOW_NO" is
'取证号'
/

comment on column "SELM_DELIVERY_HISTORY"."ST_RECEIVER_PHONE" is
'收件人手机号'
/

comment on column "SELM_DELIVERY_HISTORY"."ST_RECEIVER_NAME" is
'收件人姓名'
/

comment on column "SELM_DELIVERY_HISTORY"."ST_RECEIVER_IDCARD" is
'收件人身份证号'
/

comment on column "SELM_DELIVERY_HISTORY"."ST_SENDER_ID" is
'投件人（用户ID）'
/

comment on column "SELM_DELIVERY_HISTORY"."ST_SENDER_NAME" is
'投件人姓名'
/

comment on column "SELM_DELIVERY_HISTORY"."ST_CERT_NAME" is
'证照名称'
/

comment on column "SELM_DELIVERY_HISTORY"."NM_TYPE" is
'类型，0：企业；1：个人'
/

comment on column "SELM_DELIVERY_HISTORY"."ST_APPLY_ID" is
'关联办件'
/

comment on column "SELM_DELIVERY_HISTORY"."ST_NAME" is
'企业/个人 名称'
/

comment on column "SELM_DELIVERY_HISTORY"."NM_STATUS" is
'状态；0：待存；1：待取；2：已取'
/

comment on column "SELM_DELIVERY_HISTORY"."ST_RECEIVE_NUM" is
'取件码'
/

comment on column "SELM_DELIVERY_HISTORY"."ST_DESC" is
'描述'
/

comment on column "SELM_DELIVERY_HISTORY"."DT_CREATE" is
'创建时间'
/

comment on column "SELM_DELIVERY_HISTORY"."DT_STORE" is
'投放时间'
/

comment on column "SELM_DELIVERY_HISTORY"."DT_TAKE" is
'取走时间'
/

comment on column "SELM_DELIVERY_HISTORY"."ST_EXT1" is
'扩展字段1'
/

comment on column "SELM_DELIVERY_HISTORY"."ST_EXT2" is
'扩展字段2'
/

--==============================================================
-- Table Name: 事项表                                           
-- Table Code: SELM_ITEM                                        
--==============================================================
create table "SELM_ITEM" (
   "ST_ITEM_ID"       varchar(50) not null,   -- 事项ID
   "ST_ITEM_NO"       varchar(50),   -- 事项编码
   "ST_TEN_CODE"      varchar(50),   -- 其他编码
   "ST_MAIN_NAME"     varchar(200),   -- 主名称（主事项）
   "ST_ITEM_NAME"     varchar(200),   -- 事项名称
   "NM_BELONG"        decimal(4),   -- 事项所属
   "ST_ITEM_TYPE"     varchar(50),   -- 事项类型
   "ST_LEGAL_TIME"    varchar(50),   -- 法定时限
   "ST_PROMISE_TIME"  varchar(50),   -- 承诺时限
   "NM_SORT"          decimal(4),   -- 排序
   "ST_ITEM_GUIDE_ID" varchar(50),   -- 事项办事指南
   "NM_TYPE"          decimal(1),   -- 类型
   "ST_PARENT_ID"     varchar(50),   -- 父事项ID
   "NM_SHOW_TYPE"     decimal(1),   -- 显示类别
   "ST_WORK_URL"      varchar(200),   -- 办理跳转链接
   "DT_CREATE"        timestamp,   -- 创建时间
   "DT_UPDATE"        timestamp,   -- 更新时间
   "ST_EXT1"          varchar(50),   -- 扩展字段1
   "ST_EXT2"          varchar(50),   -- 扩展字段2
   "ST_EXT3"          varchar(100),   -- 扩展字段3
   "ST_EXT4"          varchar(200),   -- 扩展字段4
   primary key ("ST_ITEM_ID")
)
/

comment on column "SELM_ITEM"."ST_ITEM_ID" is
'事项ID'
/

comment on column "SELM_ITEM"."ST_ITEM_NO" is
'事项编码'
/

comment on column "SELM_ITEM"."ST_TEN_CODE" is
'其他编码'
/

comment on column "SELM_ITEM"."ST_MAIN_NAME" is
'主名称（主事项）'
/

comment on column "SELM_ITEM"."ST_ITEM_NAME" is
'事项名称'
/

comment on column "SELM_ITEM"."NM_BELONG" is
'法人或者个人'
/

comment on column "SELM_ITEM"."ST_ITEM_TYPE" is
'审批还是服务事项'
/

comment on column "SELM_ITEM"."ST_LEGAL_TIME" is
'法定时限'
/

comment on column "SELM_ITEM"."ST_PROMISE_TIME" is
'承诺时限'
/

comment on column "SELM_ITEM"."NM_SORT" is
'排序'
/

comment on column "SELM_ITEM"."ST_ITEM_GUIDE_ID" is
'事项办事指南，关联附件'
/

comment on column "SELM_ITEM"."NM_TYPE" is
'类型，0：主事项；2：子事项；3：情形'
/

comment on column "SELM_ITEM"."ST_PARENT_ID" is
'父事项ID'
/

comment on column "SELM_ITEM"."NM_SHOW_TYPE" is
'显示类别，0：不展示；1：只做线上；2：只做线下；3：线上线下'
/

comment on column "SELM_ITEM"."ST_WORK_URL" is
'办理跳转链接'
/

comment on column "SELM_ITEM"."DT_CREATE" is
'创建时间'
/

comment on column "SELM_ITEM"."DT_UPDATE" is
'更新时间'
/

comment on column "SELM_ITEM"."ST_EXT1" is
'扩展字段1'
/

comment on column "SELM_ITEM"."ST_EXT2" is
'扩展字段2'
/

comment on column "SELM_ITEM"."ST_EXT3" is
'扩展字段2'
/

comment on column "SELM_ITEM"."ST_EXT4" is
'扩展字段2'
/

--==============================================================
-- Table Name: 附件表                                           
-- Table Code: SELM_ATTACH                                      
--==============================================================
create table "SELM_ATTACH" (
   "ST_ATTACH_ID"     varchar(50) not null,   -- 附件ID
   "ST_LINK_TABLE"    varchar(50),   -- 关联表名称
   "ST_LINK_ID"       varchar(50),   -- 关联主键值
   "ST_ATTACH_TYPE"   varchar(50),   -- 附件类型
   "ST_FILENAME"      varchar(100),   -- 文件名
   "ST_FILE_SIZE"     varchar(50),   -- 文件大小
   "CL_CONTENT"       clob(10M) not compact not logged,   -- 文本内容
   "BL_CONTENT"       blob(10M) not compact not logged,   -- 文件内容
   "BL_SMALL_CONTENT" blob(10M) not compact not logged,   -- 图片缩略图
   "ST_FILE_TYPE"     varchar(10),   -- 文件类型
   "NM_ORDER"         decimal(4),   -- 排序
   "DT_CREATE"        timestamp,   -- 创建时间
   "DT_UPDATE"        timestamp,   -- 修改时间
   "ST_EXT1"          varchar(50),   -- 扩展字段1
   "ST_EXT2"          varchar(50),   -- 扩展字段2
   primary key ("ST_ATTACH_ID")
)
/

comment on column "SELM_ATTACH"."ST_ATTACH_ID" is
'附件ID'
/

comment on column "SELM_ATTACH"."ST_LINK_TABLE" is
'关联表名称'
/

comment on column "SELM_ATTACH"."ST_LINK_ID" is
'关联主键值'
/

comment on column "SELM_ATTACH"."ST_ATTACH_TYPE" is
'附件类型'
/

comment on column "SELM_ATTACH"."ST_FILENAME" is
'文件名'
/

comment on column "SELM_ATTACH"."ST_FILE_SIZE" is
'文件大小'
/

comment on column "SELM_ATTACH"."CL_CONTENT" is
'文本内容'
/

comment on column "SELM_ATTACH"."BL_CONTENT" is
'文件内容'
/

comment on column "SELM_ATTACH"."BL_SMALL_CONTENT" is
'图片缩略图'
/

comment on column "SELM_ATTACH"."ST_FILE_TYPE" is
'文件类型'
/

comment on column "SELM_ATTACH"."NM_ORDER" is
'排序'
/

comment on column "SELM_ATTACH"."DT_CREATE" is
'创建时间'
/

comment on column "SELM_ATTACH"."DT_UPDATE" is
'修改时间'
/

comment on column "SELM_ATTACH"."ST_EXT1" is
'扩展字段1'
/

comment on column "SELM_ATTACH"."ST_EXT2" is
'扩展字段2'
/

--==============================================================
-- Table Name: 事项类别                                         
-- Table Code: SELM_ITEM_TYPE                                   
--==============================================================
create table "SELM_ITEM_TYPE" (
   "ST_ITEM_TYPE_ID"   varchar(50) not null,   -- 事项类别ID
   "ST_ITEM_TYPE_NAME" varchar(50),   -- 事项类别名称
   "NM_SORT"           decimal(4),   -- 排序
   "ST_PARENT_ID"      varchar(50),   -- 父事项ID
   "DT_CREATE"         timestamp,   -- 创建时间
   "DT_UPDATE"         timestamp,   -- 更新时间
   "ST_EXT1"           varchar(50),   -- 扩展字段1
   "ST_EXT2"           varchar(50),   -- 扩展字段2
   primary key ("ST_ITEM_TYPE_ID")
)
/

comment on column "SELM_ITEM_TYPE"."ST_ITEM_TYPE_ID" is
'事项类别ID'
/

comment on column "SELM_ITEM_TYPE"."ST_ITEM_TYPE_NAME" is
'事项类别名称'
/

comment on column "SELM_ITEM_TYPE"."NM_SORT" is
'排序'
/

comment on column "SELM_ITEM_TYPE"."ST_PARENT_ID" is
'父事项ID'
/

comment on column "SELM_ITEM_TYPE"."DT_CREATE" is
'创建时间'
/

comment on column "SELM_ITEM_TYPE"."DT_UPDATE" is
'更新时间'
/

comment on column "SELM_ITEM_TYPE"."ST_EXT1" is
'扩展字段1'
/

comment on column "SELM_ITEM_TYPE"."ST_EXT2" is
'扩展字段2'
/

--==============================================================
-- Table Name: 类别关联事项                                     
-- Table Code: SELM_ITEM_LINK                                   
--==============================================================
create table "SELM_ITEM_LINK" (
   "ST_ITEM_ID"      varchar(50) not null,   -- 事项ID
   "ST_ITEM_TYPE_ID" varchar(50) not null,   -- 事项类别ID
   "NM_SORT"         decimal(4),   -- 排序
   primary key ("ST_ITEM_ID", "ST_ITEM_TYPE_ID")
)
/

comment on column "SELM_ITEM_LINK"."ST_ITEM_ID" is
'事项ID'
/

comment on column "SELM_ITEM_LINK"."ST_ITEM_TYPE_ID" is
'事项类别ID'
/

comment on column "SELM_ITEM_LINK"."NM_SORT" is
'排序'
/

--==============================================================
-- Table Name: 个人档案表                                       
-- Table Code: SELM_PERSONAL_DOCUMENT                           
--==============================================================
create table "SELM_PERSONAL_DOCUMENT" (
   "ST_PERSONAL_DOCUMENT" varchar(50) not null,   -- 档案ID
   "ST_NAME"              varchar(50),   -- 姓名
   "ST_SEX"               varchar(50),   -- 性别
   "ST_NATION"            varchar(50),   -- 民族
   "ST_BIRTH"             varchar(50),   -- 出生年月
   "ST_HOME_ADDRESS"      varchar(100),   -- 住址
   "ST_IDENTITY_NO"       varchar(50),   -- 身份证号
   "ST_MOBILE"            varchar(50),   -- 手机号
   "ST_HEAD_IMAGE_ID"     varchar(50),   -- 身份证头像ID
   "ST_FRONT_IMAGE_ID"    varchar(50),   -- 身份证正面ID
   "ST_BACK_IMAGE_ID"     varchar(50),   -- 身份证反面ID
   "DT_CREATE"            timestamp,   -- 创建时间
   "DT_UPDATE"            timestamp,   -- 更新时间
   "ST_EXT1"              varchar(50),   -- 扩展字段1
   "ST_EXT2"              varchar(50),   -- 扩展字段2
   primary key ("ST_PERSONAL_DOCUMENT")
)
/

comment on column "SELM_PERSONAL_DOCUMENT"."ST_PERSONAL_DOCUMENT" is
'档案ID'
/

comment on column "SELM_PERSONAL_DOCUMENT"."ST_NAME" is
'姓名'
/

comment on column "SELM_PERSONAL_DOCUMENT"."ST_SEX" is
'性别'
/

comment on column "SELM_PERSONAL_DOCUMENT"."ST_NATION" is
'民族'
/

comment on column "SELM_PERSONAL_DOCUMENT"."ST_BIRTH" is
'出生年月'
/

comment on column "SELM_PERSONAL_DOCUMENT"."ST_HOME_ADDRESS" is
'住址'
/

comment on column "SELM_PERSONAL_DOCUMENT"."ST_IDENTITY_NO" is
'证件号'
/

comment on column "SELM_PERSONAL_DOCUMENT"."ST_MOBILE" is
'手机号'
/

comment on column "SELM_PERSONAL_DOCUMENT"."ST_HEAD_IMAGE_ID" is
'身份证头像ID，关联附件表'
/

comment on column "SELM_PERSONAL_DOCUMENT"."ST_FRONT_IMAGE_ID" is
'身份证正面ID，关联附件表'
/

comment on column "SELM_PERSONAL_DOCUMENT"."ST_BACK_IMAGE_ID" is
'身份证反面ID，关联附件表'
/

comment on column "SELM_PERSONAL_DOCUMENT"."DT_CREATE" is
'创建时间'
/

comment on column "SELM_PERSONAL_DOCUMENT"."DT_UPDATE" is
'更新时间'
/

comment on column "SELM_PERSONAL_DOCUMENT"."ST_EXT1" is
'扩展字段1'
/

comment on column "SELM_PERSONAL_DOCUMENT"."ST_EXT2" is
'扩展字段2'
/

