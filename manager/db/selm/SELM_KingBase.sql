/****************************************************************/
/**************************** Drops *****************************/
/****************************************************************/

/*==============================================================*/
/* Table Code: SELM_DELIVERY                                    */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_DELIVERY';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_DELIVERY"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_OPINION                                     */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_OPINION';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_OPINION"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_DELIVERY_HISTORY                            */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_DELIVERY_HISTORY';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_DELIVERY_HISTORY"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_ITEM                                        */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_ITEM';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_ITEM"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_ATTACH                                      */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_ATTACH';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_ATTACH"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_ITEM_TYPE                                   */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_ITEM_TYPE';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_ITEM_TYPE"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_ITEM_LINK                                   */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_ITEM_LINK';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_ITEM_LINK"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_PERSONAL_DOCUMENT                           */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_PERSONAL_DOCUMENT';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_PERSONAL_DOCUMENT"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_QUERY_HIS                                   */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_QUERY_HIS';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_QUERY_HIS"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_STATISTICS                                  */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_STATISTICS';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_STATISTICS"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_STATISTICS_DAY                              */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_STATISTICS_DAY';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_STATISTICS_DAY"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_CLIENT_STAT_DAY                             */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_CLIENT_STAT_DAY';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_CLIENT_STAT_DAY"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: OAUTH2_CLIENT                                    */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'OAUTH2_CLIENT';
   if obj_count > 0 then
      execute immediate 'drop table "OAUTH2_CLIENT"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: OAUTH2_CLIENT_ITEM                               */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'OAUTH2_CLIENT_ITEM';
   if obj_count > 0 then
      execute immediate 'drop table "OAUTH2_CLIENT_ITEM"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_ITEM_LOG                                    */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_ITEM_LOG';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_ITEM_LOG"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: OAUTH2_CLIENT_DEVICE                             */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'OAUTH2_CLIENT_DEVICE';
   if obj_count > 0 then
      execute immediate 'drop table "OAUTH2_CLIENT_DEVICE"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_DEVICE_ITEM                                 */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_DEVICE_ITEM';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_DEVICE_ITEM"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_ACCESS_APPLY                                */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_ACCESS_APPLY';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_ACCESS_APPLY"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_AREA_QUERY_DAY                              */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_AREA_QUERY_DAY';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_AREA_QUERY_DAY"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_SERVER_APPLY                                */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_SERVER_APPLY';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_SERVER_APPLY"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_SERVER_ITEM                                 */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_SERVER_ITEM';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_SERVER_ITEM"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_BIGSCREEN_CACHE                             */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_BIGSCREEN_CACHE';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_BIGSCREEN_CACHE"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_ASSIST                                      */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_ASSIST';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_ASSIST"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_DEVICE_ASSIST                               */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_DEVICE_ASSIST';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_DEVICE_ASSIST"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_DEVICE_APPLY                                */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_DEVICE_APPLY';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_DEVICE_APPLY"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_DEVICE_ALINK                                */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_DEVICE_ALINK';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_DEVICE_ALINK"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_SERVER_DLINK                                */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_SERVER_DLINK';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_SERVER_DLINK"';
   end if;
end;
GO

/*==============================================================*/
/* Table Code: SELM_HC_ACCESS                                   */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_HC_ACCESS';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_HC_ACCESS"';
   end if;
end;
GO

/****************************************************************/
/************************ Create Tables *************************/
/****************************************************************/

/*==============================================================*/
/* Table Name: 快递柜                                           */
/* Table Code: SELM_DELIVERY                                    */
/*==============================================================*/
create table "SELM_DELIVERY" (
   "ST_DELIVERY_ID"     nvarchar2(50) not null,   -- 快递柜ID
   "ST_MACHINE_ID"      nvarchar2(50),   -- 设备ID
   "ST_CABINET_NO"      nvarchar2(50),   -- 设备柜号
   "ST_CERT_FLOW_NO"    nvarchar2(50),   -- 取证号
   "ST_RECEIVER_PHONE"  nvarchar2(50),   -- 收件人手机号
   "ST_RECEIVER_NAME"   nvarchar2(50),   -- 收件人姓名
   "ST_RECEIVER_IDCARD" nvarchar2(50),   -- 收件人身份证号
   "ST_SENDER_ID"       nvarchar2(50),   -- 投件人（用户ID）
   "ST_SENDER_NAME"     nvarchar2(50),   -- 投件人姓名
   "ST_CERT_NAME"       nvarchar2(50),   -- 证照名称
   "NM_TYPE"            decimal(1),   -- 类型
   "ST_APPLY_ID"        nvarchar2(50),   -- 关联办件
   "ST_NAME"            nvarchar2(100),   -- 企业/个人 名称
   "NM_STATUS"          decimal(1),   -- 状态
   "ST_RECEIVE_NUM"     nvarchar2(50),   -- 取件码
   "ST_DESC"            nvarchar2(200),   -- 描述
   "DT_CREATE"          timestamp,   -- 创建时间
   "DT_STORE"           timestamp,   -- 投放时间
   "DT_TAKE"            timestamp,   -- 取走时间
   "ST_EXT1"            nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"            nvarchar2(50),   -- 扩展字段2
   primary key ("ST_DELIVERY_ID")
)
GO

comment on column "SELM_DELIVERY"."ST_DELIVERY_ID" is
'快递柜ID'
GO

comment on column "SELM_DELIVERY"."ST_MACHINE_ID" is
'设备ID'
GO

comment on column "SELM_DELIVERY"."ST_CABINET_NO" is
'设备柜号'
GO

comment on column "SELM_DELIVERY"."ST_CERT_FLOW_NO" is
'取证号'
GO

comment on column "SELM_DELIVERY"."ST_RECEIVER_PHONE" is
'收件人手机号'
GO

comment on column "SELM_DELIVERY"."ST_RECEIVER_NAME" is
'收件人姓名'
GO

comment on column "SELM_DELIVERY"."ST_RECEIVER_IDCARD" is
'收件人身份证号'
GO

comment on column "SELM_DELIVERY"."ST_SENDER_ID" is
'投件人（用户ID）'
GO

comment on column "SELM_DELIVERY"."ST_SENDER_NAME" is
'投件人姓名'
GO

comment on column "SELM_DELIVERY"."ST_CERT_NAME" is
'证照名称'
GO

comment on column "SELM_DELIVERY"."NM_TYPE" is
'类型，0：企业；1：个人'
GO

comment on column "SELM_DELIVERY"."ST_APPLY_ID" is
'关联办件'
GO

comment on column "SELM_DELIVERY"."ST_NAME" is
'企业/个人 名称'
GO

comment on column "SELM_DELIVERY"."NM_STATUS" is
'状态；0：待存；1：待取；2：已取'
GO

comment on column "SELM_DELIVERY"."ST_RECEIVE_NUM" is
'取件码'
GO

comment on column "SELM_DELIVERY"."ST_DESC" is
'描述'
GO

comment on column "SELM_DELIVERY"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_DELIVERY"."DT_STORE" is
'投放时间'
GO

comment on column "SELM_DELIVERY"."DT_TAKE" is
'取走时间'
GO

comment on column "SELM_DELIVERY"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_DELIVERY"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 意见反馈表                                       */
/* Table Code: SELM_OPINION                                     */
/*==============================================================*/
create table "SELM_OPINION" (
   "ST_OPINION_ID"  nvarchar2(50) not null,   -- 意见表ID
   "ST_MACHINE_ID"  nvarchar2(50),   -- 设备ID
   "ST_UNAME"       nvarchar2(50),   -- 姓名
   "ST_PHONE"       nvarchar2(50),   -- 手机号
   "ST_UNIT"        nvarchar2(100),   -- 单位名称
   "ST_CONTENT"     nvarchar2(500),   -- 内容
   "NM_SATISFATION" decimal(1),   -- 满意度评价
   "DT_CREATE"      timestamp,   -- 创建时间
   "DT_UPDATE"      timestamp,   -- 修改时间
   "ST_EXT1"        nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"        nvarchar2(50),   -- 扩展字段2
   primary key ("ST_OPINION_ID")
)
GO

comment on column "SELM_OPINION"."ST_OPINION_ID" is
'意见表ID'
GO

comment on column "SELM_OPINION"."ST_MACHINE_ID" is
'设备ID'
GO

comment on column "SELM_OPINION"."ST_UNAME" is
'姓名'
GO

comment on column "SELM_OPINION"."ST_PHONE" is
'手机号'
GO

comment on column "SELM_OPINION"."ST_UNIT" is
'单位名称'
GO

comment on column "SELM_OPINION"."ST_CONTENT" is
'内容'
GO

comment on column "SELM_OPINION"."NM_SATISFATION" is
'满意度评价，1：非常满意；2：满意；3：一般；4：不满意'
GO

comment on column "SELM_OPINION"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_OPINION"."DT_UPDATE" is
'修改时间'
GO

comment on column "SELM_OPINION"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_OPINION"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 快递柜历史                                       */
/* Table Code: SELM_DELIVERY_HISTORY                            */
/*==============================================================*/
create table "SELM_DELIVERY_HISTORY" (
   "ST_DELIVERY_ID"     nvarchar2(50) not null,   -- 快递柜ID
   "ST_MACHINE_ID"      nvarchar2(50),   -- 设备ID
   "ST_CABINET_NO"      nvarchar2(50),   -- 设备柜号
   "ST_CERT_FLOW_NO"    nvarchar2(50),   -- 取证号
   "ST_RECEIVER_PHONE"  nvarchar2(50),   -- 收件人手机号
   "ST_RECEIVER_NAME"   nvarchar2(50),   -- 收件人姓名
   "ST_RECEIVER_IDCARD" nvarchar2(50),   -- 收件人身份证号
   "ST_SENDER_ID"       nvarchar2(50),   -- 投件人（用户ID）
   "ST_SENDER_NAME"     nvarchar2(50),   -- 投件人姓名
   "ST_CERT_NAME"       nvarchar2(50),   -- 证照名称
   "NM_TYPE"            decimal(1),   -- 类型
   "ST_APPLY_ID"        nvarchar2(50),   -- 关联办件
   "ST_NAME"            nvarchar2(100),   -- 企业/个人 名称
   "NM_STATUS"          decimal(1),   -- 状态
   "ST_RECEIVE_NUM"     nvarchar2(50),   -- 取件码
   "ST_DESC"            nvarchar2(200),   -- 描述
   "DT_CREATE"          timestamp,   -- 创建时间
   "DT_STORE"           timestamp,   -- 投放时间
   "DT_TAKE"            timestamp,   -- 取走时间
   "ST_EXT1"            nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"            nvarchar2(50),   -- 扩展字段2
   primary key ("ST_DELIVERY_ID")
)
GO

comment on column "SELM_DELIVERY_HISTORY"."ST_DELIVERY_ID" is
'快递柜ID'
GO

comment on column "SELM_DELIVERY_HISTORY"."ST_MACHINE_ID" is
'设备ID'
GO

comment on column "SELM_DELIVERY_HISTORY"."ST_CABINET_NO" is
'设备柜号'
GO

comment on column "SELM_DELIVERY_HISTORY"."ST_CERT_FLOW_NO" is
'取证号'
GO

comment on column "SELM_DELIVERY_HISTORY"."ST_RECEIVER_PHONE" is
'收件人手机号'
GO

comment on column "SELM_DELIVERY_HISTORY"."ST_RECEIVER_NAME" is
'收件人姓名'
GO

comment on column "SELM_DELIVERY_HISTORY"."ST_RECEIVER_IDCARD" is
'收件人身份证号'
GO

comment on column "SELM_DELIVERY_HISTORY"."ST_SENDER_ID" is
'投件人（用户ID）'
GO

comment on column "SELM_DELIVERY_HISTORY"."ST_SENDER_NAME" is
'投件人姓名'
GO

comment on column "SELM_DELIVERY_HISTORY"."ST_CERT_NAME" is
'证照名称'
GO

comment on column "SELM_DELIVERY_HISTORY"."NM_TYPE" is
'类型，0：企业；1：个人'
GO

comment on column "SELM_DELIVERY_HISTORY"."ST_APPLY_ID" is
'关联办件'
GO

comment on column "SELM_DELIVERY_HISTORY"."ST_NAME" is
'企业/个人 名称'
GO

comment on column "SELM_DELIVERY_HISTORY"."NM_STATUS" is
'状态；0：待存；1：待取；2：已取'
GO

comment on column "SELM_DELIVERY_HISTORY"."ST_RECEIVE_NUM" is
'取件码'
GO

comment on column "SELM_DELIVERY_HISTORY"."ST_DESC" is
'描述'
GO

comment on column "SELM_DELIVERY_HISTORY"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_DELIVERY_HISTORY"."DT_STORE" is
'投放时间'
GO

comment on column "SELM_DELIVERY_HISTORY"."DT_TAKE" is
'取走时间'
GO

comment on column "SELM_DELIVERY_HISTORY"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_DELIVERY_HISTORY"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 事项表                                           */
/* Table Code: SELM_ITEM                                        */
/*==============================================================*/
create table "SELM_ITEM" (
   "ST_ITEM_ID"       nvarchar2(50) not null,   -- 事项ID
   "ST_ITEM_NO"       nvarchar2(50),   -- 事项编码
   "ST_TEN_CODE"      nvarchar2(50),   -- 其他编码
   "ST_MAIN_NAME"     nvarchar2(200),   -- 主名称（主事项）
   "ST_ITEM_NAME"     nvarchar2(200),   -- 事项名称
   "NM_BELONG"        decimal(4),   -- 事项所属
   "ST_ITEM_TYPE"     nvarchar2(50),   -- 事项类型
   "ST_LEGAL_TIME"    nvarchar2(50),   -- 法定时限
   "ST_PROMISE_TIME"  nvarchar2(50),   -- 承诺时限
   "ST_ORGAN_ID"      nvarchar2(50),   -- 所属部门
   "ST_WORK_TYPE"     nvarchar2(50),   -- 事项分类
   "NM_SORT"          decimal(4),   -- 排序
   "ST_ITEM_GUIDE_ID" nvarchar2(50),   -- 事项办事指南
   "NM_TYPE"          decimal(1),   -- 类型
   "ST_PARENT_ID"     nvarchar2(50),   -- 父事项ID
   "NM_SHOW_TYPE"     decimal(1),   -- 显示类别
   "ST_WORK_URL"      nvarchar2(200),   -- 办理跳转链接
   "DT_CREATE"        timestamp,   -- 创建时间
   "DT_UPDATE"        timestamp,   -- 更新时间
   "ST_EXT1"          nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"          nvarchar2(50),   -- 扩展字段2
   "ST_EXT3"          nvarchar2(100),   -- 扩展字段3
   "ST_EXT4"          nvarchar2(200),   -- 扩展字段4
   primary key ("ST_ITEM_ID")
)
GO

comment on column "SELM_ITEM"."ST_ITEM_ID" is
'事项ID'
GO

comment on column "SELM_ITEM"."ST_ITEM_NO" is
'事项编码'
GO

comment on column "SELM_ITEM"."ST_TEN_CODE" is
'其他编码'
GO

comment on column "SELM_ITEM"."ST_MAIN_NAME" is
'主名称（主事项）'
GO

comment on column "SELM_ITEM"."ST_ITEM_NAME" is
'事项名称'
GO

comment on column "SELM_ITEM"."NM_BELONG" is
'法人或者个人'
GO

comment on column "SELM_ITEM"."ST_ITEM_TYPE" is
'审批还是服务事项'
GO

comment on column "SELM_ITEM"."ST_LEGAL_TIME" is
'法定时限'
GO

comment on column "SELM_ITEM"."ST_PROMISE_TIME" is
'承诺时限'
GO

comment on column "SELM_ITEM"."ST_ORGAN_ID" is
'所属部门'
GO

comment on column "SELM_ITEM"."ST_WORK_TYPE" is
'办理、查询、预约、其他'
GO

comment on column "SELM_ITEM"."NM_SORT" is
'排序'
GO

comment on column "SELM_ITEM"."ST_ITEM_GUIDE_ID" is
'事项办事指南，关联附件'
GO

comment on column "SELM_ITEM"."NM_TYPE" is
'类型，0：主事项；2：子事项；3：情形'
GO

comment on column "SELM_ITEM"."ST_PARENT_ID" is
'父事项ID'
GO

comment on column "SELM_ITEM"."NM_SHOW_TYPE" is
'显示类别，0：不展示；1：只做线上；2：只做线下；3：线上线下'
GO

comment on column "SELM_ITEM"."ST_WORK_URL" is
'办理跳转链接'
GO

comment on column "SELM_ITEM"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_ITEM"."DT_UPDATE" is
'更新时间'
GO

comment on column "SELM_ITEM"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_ITEM"."ST_EXT2" is
'扩展字段2'
GO

comment on column "SELM_ITEM"."ST_EXT3" is
'扩展字段2'
GO

comment on column "SELM_ITEM"."ST_EXT4" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 附件表                                           */
/* Table Code: SELM_ATTACH                                      */
/*==============================================================*/
create table "SELM_ATTACH" (
   "ST_ATTACH_ID"     nvarchar2(50) not null,   -- 附件ID
   "ST_LINK_TABLE"    nvarchar2(50),   -- 关联表名称
   "ST_LINK_ID"       nvarchar2(50),   -- 关联主键值
   "ST_ATTACH_TYPE"   nvarchar2(50),   -- 附件类型
   "ST_FILENAME"      nvarchar2(100),   -- 文件名
   "ST_FILE_SIZE"     nvarchar2(50),   -- 文件大小
   "CL_CONTENT"       clob,   -- 文本内容
   "BL_CONTENT"       blob,   -- 文件内容
   "BL_SMALL_CONTENT" blob,   -- 图片缩略图
   "ST_FILE_TYPE"     nvarchar2(10),   -- 文件类型
   "NM_ORDER"         decimal(4),   -- 排序
   "DT_CREATE"        timestamp,   -- 创建时间
   "DT_UPDATE"        timestamp,   -- 修改时间
   "ST_EXT1"          nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"          nvarchar2(50),   -- 扩展字段2
   primary key ("ST_ATTACH_ID")
)
GO

comment on column "SELM_ATTACH"."ST_ATTACH_ID" is
'附件ID'
GO

comment on column "SELM_ATTACH"."ST_LINK_TABLE" is
'关联表名称'
GO

comment on column "SELM_ATTACH"."ST_LINK_ID" is
'关联主键值'
GO

comment on column "SELM_ATTACH"."ST_ATTACH_TYPE" is
'附件类型'
GO

comment on column "SELM_ATTACH"."ST_FILENAME" is
'文件名'
GO

comment on column "SELM_ATTACH"."ST_FILE_SIZE" is
'文件大小'
GO

comment on column "SELM_ATTACH"."CL_CONTENT" is
'文本内容'
GO

comment on column "SELM_ATTACH"."BL_CONTENT" is
'文件内容'
GO

comment on column "SELM_ATTACH"."BL_SMALL_CONTENT" is
'图片缩略图'
GO

comment on column "SELM_ATTACH"."ST_FILE_TYPE" is
'文件类型'
GO

comment on column "SELM_ATTACH"."NM_ORDER" is
'排序'
GO

comment on column "SELM_ATTACH"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_ATTACH"."DT_UPDATE" is
'修改时间'
GO

comment on column "SELM_ATTACH"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_ATTACH"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 事项类别                                         */
/* Table Code: SELM_ITEM_TYPE                                   */
/*==============================================================*/
create table "SELM_ITEM_TYPE" (
   "ST_ITEM_TYPE_ID"   nvarchar2(50) not null,   -- 事项类别ID
   "ST_ITEM_TYPE_NAME" nvarchar2(50),   -- 事项类别名称
   "NM_SORT"           decimal(4),   -- 排序
   "ST_PARENT_ID"      nvarchar2(50),   -- 父事项ID
   "DT_CREATE"         timestamp,   -- 创建时间
   "DT_UPDATE"         timestamp,   -- 更新时间
   "ST_EXT1"           nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"           nvarchar2(50),   -- 扩展字段2
   primary key ("ST_ITEM_TYPE_ID")
)
GO

comment on column "SELM_ITEM_TYPE"."ST_ITEM_TYPE_ID" is
'事项类别ID'
GO

comment on column "SELM_ITEM_TYPE"."ST_ITEM_TYPE_NAME" is
'事项类别名称'
GO

comment on column "SELM_ITEM_TYPE"."NM_SORT" is
'排序'
GO

comment on column "SELM_ITEM_TYPE"."ST_PARENT_ID" is
'父事项ID'
GO

comment on column "SELM_ITEM_TYPE"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_ITEM_TYPE"."DT_UPDATE" is
'更新时间'
GO

comment on column "SELM_ITEM_TYPE"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_ITEM_TYPE"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 类别关联事项                                     */
/* Table Code: SELM_ITEM_LINK                                   */
/*==============================================================*/
create table "SELM_ITEM_LINK" (
   "ST_ITEM_ID"      nvarchar2(50) not null,   -- 事项ID
   "ST_ITEM_TYPE_ID" nvarchar2(50) not null,   -- 事项类别ID
   "NM_SORT"         decimal(4),   -- 排序
   primary key ("ST_ITEM_ID", "ST_ITEM_TYPE_ID")
)
GO

comment on column "SELM_ITEM_LINK"."ST_ITEM_ID" is
'事项ID'
GO

comment on column "SELM_ITEM_LINK"."ST_ITEM_TYPE_ID" is
'事项类别ID'
GO

comment on column "SELM_ITEM_LINK"."NM_SORT" is
'排序'
GO

/*==============================================================*/
/* Table Name: 个人档案表                                       */
/* Table Code: SELM_PERSONAL_DOCUMENT                           */
/*==============================================================*/
create table "SELM_PERSONAL_DOCUMENT" (
   "ST_PERSONAL_DOCUMENT" nvarchar2(50) not null,   -- 档案ID
   "ST_NAME"              nvarchar2(50),   -- 姓名
   "ST_SEX"               nvarchar2(50),   -- 性别
   "ST_NATION"            nvarchar2(50),   -- 民族
   "ST_BIRTH"             nvarchar2(50),   -- 出生年月
   "ST_HOME_ADDRESS"      nvarchar2(100),   -- 住址
   "ST_IDENTITY_NO"       nvarchar2(50),   -- 身份证号
   "ST_MOBILE"            nvarchar2(50),   -- 手机号
   "ST_HEAD_IMAGE_ID"     nvarchar2(50),   -- 身份证头像ID
   "ST_FRONT_IMAGE_ID"    nvarchar2(50),   -- 身份证正面ID
   "ST_BACK_IMAGE_ID"     nvarchar2(50),   -- 身份证反面ID
   "DT_CREATE"            timestamp,   -- 创建时间
   "DT_UPDATE"            timestamp,   -- 更新时间
   "ST_EXT1"              nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"              nvarchar2(50),   -- 扩展字段2
   primary key ("ST_PERSONAL_DOCUMENT")
)
GO

comment on column "SELM_PERSONAL_DOCUMENT"."ST_PERSONAL_DOCUMENT" is
'档案ID'
GO

comment on column "SELM_PERSONAL_DOCUMENT"."ST_NAME" is
'姓名'
GO

comment on column "SELM_PERSONAL_DOCUMENT"."ST_SEX" is
'性别'
GO

comment on column "SELM_PERSONAL_DOCUMENT"."ST_NATION" is
'民族'
GO

comment on column "SELM_PERSONAL_DOCUMENT"."ST_BIRTH" is
'出生年月'
GO

comment on column "SELM_PERSONAL_DOCUMENT"."ST_HOME_ADDRESS" is
'住址'
GO

comment on column "SELM_PERSONAL_DOCUMENT"."ST_IDENTITY_NO" is
'证件号'
GO

comment on column "SELM_PERSONAL_DOCUMENT"."ST_MOBILE" is
'手机号'
GO

comment on column "SELM_PERSONAL_DOCUMENT"."ST_HEAD_IMAGE_ID" is
'身份证头像ID，关联附件表'
GO

comment on column "SELM_PERSONAL_DOCUMENT"."ST_FRONT_IMAGE_ID" is
'身份证正面ID，关联附件表'
GO

comment on column "SELM_PERSONAL_DOCUMENT"."ST_BACK_IMAGE_ID" is
'身份证反面ID，关联附件表'
GO

comment on column "SELM_PERSONAL_DOCUMENT"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_PERSONAL_DOCUMENT"."DT_UPDATE" is
'更新时间'
GO

comment on column "SELM_PERSONAL_DOCUMENT"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_PERSONAL_DOCUMENT"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 查询历史                                         */
/* Table Code: SELM_QUERY_HIS                                   */
/*==============================================================*/
create table "SELM_QUERY_HIS" (
   "ST_QUERY_HIS_ID" nvarchar2(50) not null,   -- 历史ID
   "ST_MACHINE_ID"   nvarchar2(50),   -- 设备ID
   "ST_ASSIST_ID"    nvarchar2(50),   -- 辅助人ID
   "ST_MODULE_NAME"  nvarchar2(50),   -- 模块名称
   "ST_MODULE_OP"    nvarchar2(50),   -- 操作名称
   "ST_NAME"         nvarchar2(50),   -- 姓名
   "ST_IDENTITY_NO"  nvarchar2(50),   -- 身份证号
   "ST_MOBILE"       nvarchar2(50),   -- 手机号
   "DT_CREATE"       timestamp,   -- 创建时间
   "ST_ATTACH_ID1"   nvarchar2(50),   -- 附件ID1
   "ST_ATTACH_ID2"   nvarchar2(50),   -- 附件ID2
   "ST_ATTACH_ID3"   nvarchar2(50),   -- 附件ID3
   "ST_ATTACH_ID4"   nvarchar2(50),   -- 附件ID4
   "ST_EXT1"         nvarchar2(100),   -- 扩展字段1
   "ST_EXT2"         nvarchar2(100),   -- 扩展字段2
   "ST_EXT3"         nvarchar2(100),   -- 扩展字段3
   "ST_EXT4"         nvarchar2(200),   -- 扩展字段4
   "ST_EXT5"         nvarchar2(300),   -- 扩展字段5
   primary key ("ST_QUERY_HIS_ID")
)
GO

comment on column "SELM_QUERY_HIS"."ST_QUERY_HIS_ID" is
'历史ID'
GO

comment on column "SELM_QUERY_HIS"."ST_MACHINE_ID" is
'设备ID'
GO

comment on column "SELM_QUERY_HIS"."ST_ASSIST_ID" is
'辅助人ID'
GO

comment on column "SELM_QUERY_HIS"."ST_MODULE_NAME" is
'模块名称'
GO

comment on column "SELM_QUERY_HIS"."ST_MODULE_OP" is
'操作名称'
GO

comment on column "SELM_QUERY_HIS"."ST_NAME" is
'姓名'
GO

comment on column "SELM_QUERY_HIS"."ST_IDENTITY_NO" is
'证件号'
GO

comment on column "SELM_QUERY_HIS"."ST_MOBILE" is
'手机号'
GO

comment on column "SELM_QUERY_HIS"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_QUERY_HIS"."ST_ATTACH_ID1" is
'附件ID1'
GO

comment on column "SELM_QUERY_HIS"."ST_ATTACH_ID2" is
'附件ID2'
GO

comment on column "SELM_QUERY_HIS"."ST_ATTACH_ID3" is
'附件ID3'
GO

comment on column "SELM_QUERY_HIS"."ST_ATTACH_ID4" is
'附件ID4'
GO

comment on column "SELM_QUERY_HIS"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_QUERY_HIS"."ST_EXT2" is
'扩展字段2'
GO

comment on column "SELM_QUERY_HIS"."ST_EXT3" is
'扩展字段2'
GO

comment on column "SELM_QUERY_HIS"."ST_EXT4" is
'扩展字段2'
GO

comment on column "SELM_QUERY_HIS"."ST_EXT5" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 业务表                                           */
/* Table Code: SELM_STATISTICS                                  */
/*==============================================================*/
create table "SELM_STATISTICS" (
   "ST_STATISTICS_ID" nvarchar2(50) not null,   -- 统计ID
   "ST_NET_FLAG"      nvarchar2(50),   -- 业务标识
   "ST_NET_SUB_FLAG"  nvarchar2(50),   -- 业务子标识
   "ST_NAME"          nvarchar2(50),   -- 业务名称
   "NM_COUNT"         decimal(8),   -- 业务总数
   "NM_ODEVICE"       decimal(1),   -- 是否是外设
   "NM_SORT"          decimal(4),   -- 排序
   "ST_EXT1"          nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"          nvarchar2(50),   -- 扩展字段2
   primary key ("ST_STATISTICS_ID")
)
GO

comment on column "SELM_STATISTICS"."ST_STATISTICS_ID" is
'统计ID'
GO

comment on column "SELM_STATISTICS"."ST_NET_FLAG" is
'业务标识'
GO

comment on column "SELM_STATISTICS"."ST_NET_SUB_FLAG" is
'业务子标识'
GO

comment on column "SELM_STATISTICS"."ST_NAME" is
'业务名称'
GO

comment on column "SELM_STATISTICS"."NM_COUNT" is
'业务总数'
GO

comment on column "SELM_STATISTICS"."NM_ODEVICE" is
'是否是外设，0：否；1：是'
GO

comment on column "SELM_STATISTICS"."NM_SORT" is
'排序'
GO

comment on column "SELM_STATISTICS"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_STATISTICS"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 业务统计（按天）                                 */
/* Table Code: SELM_STATISTICS_DAY                              */
/*==============================================================*/
create table "SELM_STATISTICS_DAY" (
   "ST_STATISTICS_ID" nvarchar2(50) not null,   -- 统计ID
   "ST_DATE"          nvarchar2(50) not null,   -- 日期字符串
   "NM_COUNT"         decimal(8),   -- 业务总数
   "NM_QUERY"         decimal(8),   -- 业务查询
   "NM_SUCCESS"       decimal(8),   -- 业务成功
   "NM_FAILD"         decimal(8),   -- 业务失败
   "DT_TIME"          timestamp,   -- 日期
   "ST_EXT1"          nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"          nvarchar2(50),   -- 扩展字段2
   primary key ("ST_STATISTICS_ID", "ST_DATE")
)
GO

comment on column "SELM_STATISTICS_DAY"."ST_STATISTICS_ID" is
'统计ID'
GO

comment on column "SELM_STATISTICS_DAY"."ST_DATE" is
'日期字符串'
GO

comment on column "SELM_STATISTICS_DAY"."NM_COUNT" is
'业务总数'
GO

comment on column "SELM_STATISTICS_DAY"."NM_QUERY" is
'业务查询'
GO

comment on column "SELM_STATISTICS_DAY"."NM_SUCCESS" is
'业务成功'
GO

comment on column "SELM_STATISTICS_DAY"."NM_FAILD" is
'业务失败'
GO

comment on column "SELM_STATISTICS_DAY"."DT_TIME" is
'日期'
GO

comment on column "SELM_STATISTICS_DAY"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_STATISTICS_DAY"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 终端业务统计（按天）                             */
/* Table Code: SELM_CLIENT_STAT_DAY                             */
/*==============================================================*/
create table "SELM_CLIENT_STAT_DAY" (
   "ST_STATISTICS_ID" nvarchar2(50) not null,   -- 统计ID
   "ST_DATE"          nvarchar2(50) not null,   -- 日期字符串
   "ST_MACHINE_ID"    nvarchar2(50) not null,   -- 设备ID
   "NM_COUNT"         decimal(8),   -- 业务总数
   "NM_QUERY"         decimal(8),   -- 业务查询
   "NM_SUCCESS"       decimal(8),   -- 业务成功
   "NM_FAILD"         decimal(8),   -- 业务失败
   "DT_TIME"          timestamp,   -- 日期
   "ST_EXT1"          nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"          nvarchar2(50),   -- 扩展字段2
   primary key ("ST_STATISTICS_ID", "ST_DATE", "ST_MACHINE_ID")
)
GO

comment on column "SELM_CLIENT_STAT_DAY"."ST_STATISTICS_ID" is
'统计ID'
GO

comment on column "SELM_CLIENT_STAT_DAY"."ST_DATE" is
'日期字符串'
GO

comment on column "SELM_CLIENT_STAT_DAY"."ST_MACHINE_ID" is
'设备ID'
GO

comment on column "SELM_CLIENT_STAT_DAY"."NM_COUNT" is
'业务总数'
GO

comment on column "SELM_CLIENT_STAT_DAY"."NM_QUERY" is
'业务查询'
GO

comment on column "SELM_CLIENT_STAT_DAY"."NM_SUCCESS" is
'业务成功'
GO

comment on column "SELM_CLIENT_STAT_DAY"."NM_FAILD" is
'业务失败'
GO

comment on column "SELM_CLIENT_STAT_DAY"."DT_TIME" is
'日期'
GO

comment on column "SELM_CLIENT_STAT_DAY"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_CLIENT_STAT_DAY"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: OAUTH2认证客户端                                 */
/* Table Code: OAUTH2_CLIENT                                    */
/*==============================================================*/
create table "OAUTH2_CLIENT" (
   "ST_OAUTH2_ID"      nvarchar2(50) not null,   -- 认证客户端ID
   "ST_INTERFACE_USER" nvarchar2(50),   -- 接口用户名
   "ST_INTERFACE_PWD"  nvarchar2(50),   -- 接口密码
   "ST_CLIENT_NAME"    nvarchar2(50),   -- 客户端名称
   "ST_CLIENT_ID"      nvarchar2(50),   -- 客户端ID
   "ST_CLIENT_SECRET"  nvarchar2(50),   -- 客户端安全KEY
   "ST_DESC"           nvarchar2(100),   -- 备注
   primary key ("ST_OAUTH2_ID")
)
GO

comment on column "OAUTH2_CLIENT"."ST_OAUTH2_ID" is
'认证客户端ID'
GO

comment on column "OAUTH2_CLIENT"."ST_INTERFACE_USER" is
'接口用户名'
GO

comment on column "OAUTH2_CLIENT"."ST_INTERFACE_PWD" is
'接口密码'
GO

comment on column "OAUTH2_CLIENT"."ST_CLIENT_NAME" is
'客户端名称'
GO

comment on column "OAUTH2_CLIENT"."ST_CLIENT_ID" is
'客户端ID'
GO

comment on column "OAUTH2_CLIENT"."ST_CLIENT_SECRET" is
'客户端安全KEY'
GO

comment on column "OAUTH2_CLIENT"."ST_DESC" is
'备注'
GO

/*==============================================================*/
/* Table Name: 授权事项                                         */
/* Table Code: OAUTH2_CLIENT_ITEM                               */
/*==============================================================*/
create table "OAUTH2_CLIENT_ITEM" (
   "ST_OAUTH2_ID" nvarchar2(50) not null,   -- 认证客户端ID
   "ST_ITEM_ID"   nvarchar2(50) not null,   -- 事项ID
   "ST_DEVICE_ID" nvarchar2(50) not null,   -- 设备ID
   "NM_STATUS"    decimal(1),   -- 状态
   "NM_ORDER"     decimal(4),   -- 排序
   "DT_CREATE"    timestamp,   -- 创建时间
   "DT_UPDATE"    timestamp,   -- 更新时间
   primary key ("ST_OAUTH2_ID", "ST_ITEM_ID", "ST_DEVICE_ID")
)
GO

comment on column "OAUTH2_CLIENT_ITEM"."ST_OAUTH2_ID" is
'认证客户端ID'
GO

comment on column "OAUTH2_CLIENT_ITEM"."ST_ITEM_ID" is
'事项ID'
GO

comment on column "OAUTH2_CLIENT_ITEM"."ST_DEVICE_ID" is
'设备ID'
GO

comment on column "OAUTH2_CLIENT_ITEM"."NM_STATUS" is
'状态，0：删除；1：注册；2：正常；3：禁用；'
GO

comment on column "OAUTH2_CLIENT_ITEM"."NM_ORDER" is
'排序'
GO

comment on column "OAUTH2_CLIENT_ITEM"."DT_CREATE" is
'创建时间'
GO

comment on column "OAUTH2_CLIENT_ITEM"."DT_UPDATE" is
'更新时间'
GO

/*==============================================================*/
/* Table Name: 访问日志                                         */
/* Table Code: SELM_ITEM_LOG                                    */
/*==============================================================*/
create table "SELM_ITEM_LOG" (
   "ST_ITEM_LOG_ID" nvarchar2(50) not null,   -- 日志ID
   "ST_OAUTH2_ID"   nvarchar2(50),   -- 认证客户端ID
   "ST_ITEM_ID"     nvarchar2(50),   -- 事项ID
   "ST_ITEM_NAME"   nvarchar2(200),   -- 事项名称
   "ST_CLIENT_NAME" nvarchar2(50),   -- 客户端名称
   "DT_CREATE"      timestamp,   -- 创建时间
   "ST_EXT1"        nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"        nvarchar2(50),   -- 扩展字段2
   primary key ("ST_ITEM_LOG_ID")
)
GO

comment on column "SELM_ITEM_LOG"."ST_ITEM_LOG_ID" is
'日志ID'
GO

comment on column "SELM_ITEM_LOG"."ST_OAUTH2_ID" is
'认证客户端ID'
GO

comment on column "SELM_ITEM_LOG"."ST_ITEM_ID" is
'事项ID'
GO

comment on column "SELM_ITEM_LOG"."ST_ITEM_NAME" is
'事项名称'
GO

comment on column "SELM_ITEM_LOG"."ST_CLIENT_NAME" is
'客户端名称'
GO

comment on column "SELM_ITEM_LOG"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_ITEM_LOG"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_ITEM_LOG"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 客户端关联设备                                   */
/* Table Code: OAUTH2_CLIENT_DEVICE                             */
/*==============================================================*/
create table "OAUTH2_CLIENT_DEVICE" (
   "ST_OAUTH2_ID" nvarchar2(50) not null,   -- 认证客户端ID
   "ST_DEVICE_ID" nvarchar2(50) not null,   -- 设备ID
   "NM_ORDER"     decimal(4),   -- 排序
   "DT_CREATE"    timestamp,   -- 创建时间
   "DT_UPDATE"    timestamp,   -- 更新时间
   primary key ("ST_OAUTH2_ID", "ST_DEVICE_ID")
)
GO

comment on column "OAUTH2_CLIENT_DEVICE"."ST_OAUTH2_ID" is
'认证客户端ID'
GO

comment on column "OAUTH2_CLIENT_DEVICE"."ST_DEVICE_ID" is
'设备ID'
GO

comment on column "OAUTH2_CLIENT_DEVICE"."NM_ORDER" is
'排序'
GO

comment on column "OAUTH2_CLIENT_DEVICE"."DT_CREATE" is
'创建时间'
GO

comment on column "OAUTH2_CLIENT_DEVICE"."DT_UPDATE" is
'更新时间'
GO

/*==============================================================*/
/* Table Name: 设备关联事项                                     */
/* Table Code: SELM_DEVICE_ITEM                                 */
/*==============================================================*/
create table "SELM_DEVICE_ITEM" (
   "ST_ITEM_ID"   nvarchar2(50) not null,   -- 事项ID
   "ST_DEVICE_ID" nvarchar2(50) not null,   -- 设备ID
   "NM_STATUS"    decimal(1),   -- 状态
   "NM_ORDER"     decimal(4),   -- 排序
   "DT_CREATE"    timestamp,   -- 创建时间
   "DT_UPDATE"    timestamp,   -- 更新时间
   primary key ("ST_ITEM_ID", "ST_DEVICE_ID")
)
GO

comment on column "SELM_DEVICE_ITEM"."ST_ITEM_ID" is
'事项ID'
GO

comment on column "SELM_DEVICE_ITEM"."ST_DEVICE_ID" is
'设备ID'
GO

comment on column "SELM_DEVICE_ITEM"."NM_STATUS" is
'状态，0：删除；1：注册；2：正常；3：禁用；'
GO

comment on column "SELM_DEVICE_ITEM"."NM_ORDER" is
'排序'
GO

comment on column "SELM_DEVICE_ITEM"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_DEVICE_ITEM"."DT_UPDATE" is
'更新时间'
GO

/*==============================================================*/
/* Table Name: 接入申请                                         */
/* Table Code: SELM_ACCESS_APPLY                                */
/*==============================================================*/
create table "SELM_ACCESS_APPLY" (
   "ST_ACCESS_APPLY_ID" nvarchar2(50) not null,   -- 申请ID
   "ST_APPLY_TITLE"     nvarchar2(200),   -- 申请标题
   "ST_APPLY_CONTENT"   nvarchar2(2000),   -- 申请内容
   "ST_ATTACH_ID"       nvarchar2(50),   -- 附件ID
   "NM_STATUS"          decimal(1),   -- 状态
   "ST_APPLY_USER_ID"   nvarchar2(50),   -- 申请人ID
   "ST_APPLY_USER_NAME" nvarchar2(50),   -- 申请人姓名
   "DT_APPLY"           timestamp,   -- 申请时间
   "ST_AUDIT_USER_ID"   nvarchar2(50),   -- 审核人ID
   "ST_AUDIT_USER_NAME" nvarchar2(50),   -- 审核人姓名
   "DT_AUDIT"           timestamp,   -- 审核时间
   "DT_CREATE"          timestamp,   -- 创建时间
   "ST_DESC"            nvarchar2(100),   -- 备注
   "ST_EXT1"            nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"            nvarchar2(50),   -- 扩展字段2
   primary key ("ST_ACCESS_APPLY_ID")
)
GO

comment on column "SELM_ACCESS_APPLY"."ST_ACCESS_APPLY_ID" is
'申请ID'
GO

comment on column "SELM_ACCESS_APPLY"."ST_APPLY_TITLE" is
'申请标题'
GO

comment on column "SELM_ACCESS_APPLY"."ST_APPLY_CONTENT" is
'申请内容'
GO

comment on column "SELM_ACCESS_APPLY"."ST_ATTACH_ID" is
'附件ID，关联附件表'
GO

comment on column "SELM_ACCESS_APPLY"."NM_STATUS" is
'状态，0：已保存；1：已提交；2：已修改；3：审核通过；4：审核不通过；5：终止'
GO

comment on column "SELM_ACCESS_APPLY"."ST_APPLY_USER_ID" is
'申请人ID'
GO

comment on column "SELM_ACCESS_APPLY"."ST_APPLY_USER_NAME" is
'申请人姓名'
GO

comment on column "SELM_ACCESS_APPLY"."DT_APPLY" is
'申请时间'
GO

comment on column "SELM_ACCESS_APPLY"."ST_AUDIT_USER_ID" is
'审核人ID'
GO

comment on column "SELM_ACCESS_APPLY"."ST_AUDIT_USER_NAME" is
'审核人姓名'
GO

comment on column "SELM_ACCESS_APPLY"."DT_AUDIT" is
'审核时间'
GO

comment on column "SELM_ACCESS_APPLY"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_ACCESS_APPLY"."ST_DESC" is
'备注'
GO

comment on column "SELM_ACCESS_APPLY"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_ACCESS_APPLY"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 区域日办件统计表                                 */
/* Table Code: SELM_AREA_QUERY_DAY                              */
/*==============================================================*/
create table "SELM_AREA_QUERY_DAY" (
   "ST_AREA_ID"       nvarchar2(50) not null,   -- 区域标识
   "ST_DAY"           nvarchar2(50) not null,   -- 天
   "ST_AREA_NAME"     nvarchar2(50),   -- 区域名称
   "NM_GOV_NUMBER"    decimal(18),   -- 政务服务自助终端数量
   "NM_SOCIAL_NUMBER" decimal(18),   -- 社会化自助终端数量
   "NM_DAY"           decimal(18),   -- 日办件数量
   primary key ("ST_AREA_ID", "ST_DAY")
)
GO

comment on column "SELM_AREA_QUERY_DAY"."ST_AREA_ID" is
'区域标识'
GO

comment on column "SELM_AREA_QUERY_DAY"."ST_DAY" is
'天，yyyy-MM-dd'
GO

comment on column "SELM_AREA_QUERY_DAY"."ST_AREA_NAME" is
'区域名称'
GO

comment on column "SELM_AREA_QUERY_DAY"."NM_GOV_NUMBER" is
'政务服务自助终端数量'
GO

comment on column "SELM_AREA_QUERY_DAY"."NM_SOCIAL_NUMBER" is
'社会化自助终端数量'
GO

comment on column "SELM_AREA_QUERY_DAY"."NM_DAY" is
'日办件数量'
GO

/*==============================================================*/
/* Table Name: 服务开通申请                                     */
/* Table Code: SELM_SERVER_APPLY                                */
/*==============================================================*/
create table "SELM_SERVER_APPLY" (
   "ST_APPLY_ID"           nvarchar2(50) not null,   -- 申请ID
   "ST_APPLY_ORGAN_ID"     nvarchar2(50),   -- 申请单位ID
   "ST_APPLY_ORGAN_NAME"   nvarchar2(50),   -- 申请单位名称
   "NM_STATUS"             decimal(1),   -- 状态
   "ST_SERVER_USER_NAME"   nvarchar2(50),   -- 联系人
   "ST_SERVER_USER_PHONE"  nvarchar2(50),   -- 手机
   "ST_SERVER_USER_MOBILE" nvarchar2(50),   -- 固定电话
   "ST_SERVER_USER_EMAIL"  nvarchar2(50),   -- 电子邮箱
   "ST_SERVER_CONTENT"     nvarchar2(500),   -- 申请情况说明
   "DT_UP_CREATE"          timestamp,   -- 计划上线时间
   "DT_CREATE"             timestamp,   -- 创建时间
   "ST_SERVER_DESTRICT"    nvarchar2(50),   -- 所在区
   "ST_POINT_NAME"         nvarchar2(50),   -- 点位名称
   "ST_PUT_ADDRESS"        nvarchar2(100),   -- 摆放地址
   "ST_BUILD_COMPANY"      nvarchar2(50),   -- 承建厂商
   "ST_PUT_NUMBER"         nvarchar2(50),   -- 预计摆放台数
   "NM_NETWORK"            decimal(1),   -- 现场网络环境
   "ST_WATCH_OVER"         decimal(1),   -- 现场有无值守
   "ST_ATTACH_ID"          nvarchar2(50),   -- 附件ID
   "ST_RESULT"             nvarchar2(1000),   -- 事项审批结果
   "NM_UPDATE"             decimal(1),   -- 是否可修改
   "ST_EXT1"               nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"               nvarchar2(50),   -- 扩展字段2
   primary key ("ST_APPLY_ID")
)
GO

comment on column "SELM_SERVER_APPLY"."ST_APPLY_ID" is
'申请ID'
GO

comment on column "SELM_SERVER_APPLY"."ST_APPLY_ORGAN_ID" is
'申请单位ID'
GO

comment on column "SELM_SERVER_APPLY"."ST_APPLY_ORGAN_NAME" is
'申请单位名称'
GO

comment on column "SELM_SERVER_APPLY"."NM_STATUS" is
'状态，0：已保存；1：全部通过：2：部分通过；3：全部不通过'
GO

comment on column "SELM_SERVER_APPLY"."ST_SERVER_USER_NAME" is
'联系人'
GO

comment on column "SELM_SERVER_APPLY"."ST_SERVER_USER_PHONE" is
'手机'
GO

comment on column "SELM_SERVER_APPLY"."ST_SERVER_USER_MOBILE" is
'固定电话'
GO

comment on column "SELM_SERVER_APPLY"."ST_SERVER_USER_EMAIL" is
'电子邮箱'
GO

comment on column "SELM_SERVER_APPLY"."ST_SERVER_CONTENT" is
'申请情况说明'
GO

comment on column "SELM_SERVER_APPLY"."DT_UP_CREATE" is
'计划上线时间'
GO

comment on column "SELM_SERVER_APPLY"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_SERVER_APPLY"."ST_SERVER_DESTRICT" is
'所在区'
GO

comment on column "SELM_SERVER_APPLY"."ST_POINT_NAME" is
'点位名称'
GO

comment on column "SELM_SERVER_APPLY"."ST_PUT_ADDRESS" is
'摆放地址'
GO

comment on column "SELM_SERVER_APPLY"."ST_BUILD_COMPANY" is
'承建厂商'
GO

comment on column "SELM_SERVER_APPLY"."ST_PUT_NUMBER" is
'预计摆放台数'
GO

comment on column "SELM_SERVER_APPLY"."NM_NETWORK" is
'现场网络环境 0：政务外网 1：互联网'
GO

comment on column "SELM_SERVER_APPLY"."ST_WATCH_OVER" is
'现场有无值守0：有 1：无'
GO

comment on column "SELM_SERVER_APPLY"."ST_ATTACH_ID" is
'附件ID，关联附件表'
GO

comment on column "SELM_SERVER_APPLY"."ST_RESULT" is
'事项审批结果'
GO

comment on column "SELM_SERVER_APPLY"."NM_UPDATE" is
'是否可修改，0：否；1：是'
GO

comment on column "SELM_SERVER_APPLY"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_SERVER_APPLY"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 服务（设备）关联事项                             */
/* Table Code: SELM_SERVER_ITEM                                 */
/*==============================================================*/
create table "SELM_SERVER_ITEM" (
   "ST_LINKS_ID"        nvarchar2(50) not null,   -- 关联ID
   "ST_APPLY_ID"        nvarchar2(50),   -- 申请ID
   "ST_DEVICE_ID"       nvarchar2(50),   -- 设备ID
   "ST_ITEM_TYPE_ID"    nvarchar2(50),   -- 事项类别ID
   "ST_ITEM_ID"         nvarchar2(50),   -- 事项ID
   "ST_ITEM_NO"         nvarchar2(50),   -- 事项编码
   "ST_ITEM_NAME"       nvarchar2(200),   -- 事项名称
   "ST_ORGAN_ID"        nvarchar2(50),   -- 所属部门
   "NM_PASS"            decimal(1),   -- 是否通过
   "NM_STATUS"          decimal(1),   -- 状态
   "ST_REASON"          nvarchar2(100),   -- 批注原因
   "NM_TYPE"            decimal(1),   -- 关联类别
   "ST_AUDIT_USER_ID"   nvarchar2(50),   -- 审核人ID
   "ST_AUDIT_USER_NAME" nvarchar2(50),   -- 审核人姓名
   "DT_AUDIT"           timestamp,   -- 审核时间
   primary key ("ST_LINKS_ID")
)
GO

comment on column "SELM_SERVER_ITEM"."ST_LINKS_ID" is
'关联ID'
GO

comment on column "SELM_SERVER_ITEM"."ST_APPLY_ID" is
'申请ID'
GO

comment on column "SELM_SERVER_ITEM"."ST_DEVICE_ID" is
'设备ID'
GO

comment on column "SELM_SERVER_ITEM"."ST_ITEM_TYPE_ID" is
'事项类别ID'
GO

comment on column "SELM_SERVER_ITEM"."ST_ITEM_ID" is
'事项ID'
GO

comment on column "SELM_SERVER_ITEM"."ST_ITEM_NO" is
'事项编码'
GO

comment on column "SELM_SERVER_ITEM"."ST_ITEM_NAME" is
'事项名称'
GO

comment on column "SELM_SERVER_ITEM"."ST_ORGAN_ID" is
'所属部门'
GO

comment on column "SELM_SERVER_ITEM"."NM_PASS" is
'是否通过，0：否；1：是'
GO

comment on column "SELM_SERVER_ITEM"."NM_STATUS" is
'状态，0：已保存；1：已提交；2：已修改；3：审核通过；4：审核不通过；5：终止'
GO

comment on column "SELM_SERVER_ITEM"."ST_REASON" is
'批注原因'
GO

comment on column "SELM_SERVER_ITEM"."NM_TYPE" is
'关联类别，0：事项；1：组别；2：设备事项'
GO

comment on column "SELM_SERVER_ITEM"."ST_AUDIT_USER_ID" is
'审核人ID'
GO

comment on column "SELM_SERVER_ITEM"."ST_AUDIT_USER_NAME" is
'审核人姓名'
GO

comment on column "SELM_SERVER_ITEM"."DT_AUDIT" is
'审核时间'
GO

/*==============================================================*/
/* Table Name: 大屏统计缓存表                                   */
/* Table Code: SELM_BIGSCREEN_CACHE                             */
/*==============================================================*/
create table "SELM_BIGSCREEN_CACHE" (
   "ST_BIGSCREEN_CACHE_ID" nvarchar2(50) not null,   -- 缓存ID
   "ST_FRAME"              nvarchar2(50),   -- 框架标识
   "ST_FCODE"              nvarchar2(50),   -- 一级标识
   "ST_SCODE"              nvarchar2(50),   -- 二级标识
   "ST_TCODE"              nvarchar2(50),   -- 三级标识
   "ST_JSON"               nvarchar2(5000),   -- JSON数据
   "ST_CLOB_ID"            nvarchar2(50),   -- 超大数据
   "NM_ORDER"              decimal(4),   -- 排序
   "DT_CREATE"             timestamp,   -- 创建时间
   "DT_UPDATE"             timestamp,   -- 修改时间
   "ST_EXT1"               nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"               nvarchar2(50),   -- 扩展字段2
   primary key ("ST_BIGSCREEN_CACHE_ID")
)
GO

comment on column "SELM_BIGSCREEN_CACHE"."ST_BIGSCREEN_CACHE_ID" is
'缓存ID'
GO

comment on column "SELM_BIGSCREEN_CACHE"."ST_FRAME" is
'框架标识'
GO

comment on column "SELM_BIGSCREEN_CACHE"."ST_FCODE" is
'一级标识，页面标识'
GO

comment on column "SELM_BIGSCREEN_CACHE"."ST_SCODE" is
'二级标识，页面功能模块'
GO

comment on column "SELM_BIGSCREEN_CACHE"."ST_TCODE" is
'三级标识，页面子功能模块'
GO

comment on column "SELM_BIGSCREEN_CACHE"."ST_JSON" is
'JSON数据'
GO

comment on column "SELM_BIGSCREEN_CACHE"."ST_CLOB_ID" is
'超大数据，关联附件表'
GO

comment on column "SELM_BIGSCREEN_CACHE"."NM_ORDER" is
'排序'
GO

comment on column "SELM_BIGSCREEN_CACHE"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_BIGSCREEN_CACHE"."DT_UPDATE" is
'修改时间'
GO

comment on column "SELM_BIGSCREEN_CACHE"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_BIGSCREEN_CACHE"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 设备辅助人员                                     */
/* Table Code: SELM_ASSIST                                      */
/*==============================================================*/
create table "SELM_ASSIST" (
   "ST_ASSIST_ID"     nvarchar2(50) not null,   -- 辅助人ID
   "ST_ASSIST_NAME"   nvarchar2(50),   -- 辅助人姓名
   "ST_ASSIST_PHONE"  nvarchar2(50),   -- 辅助人手机号
   "ST_ASSIST_IDCARD" nvarchar2(50),   -- 辅助人身份证号
   "NM_ORDER"         decimal(1),   -- 排序
   "DT_CREATE"        timestamp,   -- 创建时间
   "DT_UPADTE"        timestamp,   -- 修改时间
   "ST_EXT1"          nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"          nvarchar2(50),   -- 扩展字段2
   primary key ("ST_ASSIST_ID")
)
GO

comment on column "SELM_ASSIST"."ST_ASSIST_ID" is
'辅助人ID'
GO

comment on column "SELM_ASSIST"."ST_ASSIST_NAME" is
'辅助人姓名'
GO

comment on column "SELM_ASSIST"."ST_ASSIST_PHONE" is
'辅助人手机号'
GO

comment on column "SELM_ASSIST"."ST_ASSIST_IDCARD" is
'辅助人身份证号'
GO

comment on column "SELM_ASSIST"."NM_ORDER" is
'排序'
GO

comment on column "SELM_ASSIST"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_ASSIST"."DT_UPADTE" is
'修改时间'
GO

comment on column "SELM_ASSIST"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_ASSIST"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 设备关联人员                                     */
/* Table Code: SELM_DEVICE_ASSIST                               */
/*==============================================================*/
create table "SELM_DEVICE_ASSIST" (
   "ST_ASSIST_ID" nvarchar2(50) not null,   -- 辅助人ID
   "ST_DEVICE_ID" nvarchar2(50) not null,   -- 设备ID
   "NM_ORDER"     decimal(8),   -- 排序
   primary key ("ST_ASSIST_ID", "ST_DEVICE_ID")
)
GO

comment on column "SELM_DEVICE_ASSIST"."ST_ASSIST_ID" is
'辅助人ID'
GO

comment on column "SELM_DEVICE_ASSIST"."ST_DEVICE_ID" is
'设备ID'
GO

comment on column "SELM_DEVICE_ASSIST"."NM_ORDER" is
'排序'
GO

/*==============================================================*/
/* Table Name: 设备接入申请                                     */
/* Table Code: SELM_DEVICE_APPLY                                */
/*==============================================================*/
create table "SELM_DEVICE_APPLY" (
   "ST_DEVICE_APPLY_ID"   nvarchar2(50) not null,   -- 申请ID
   "ST_DAPPLY_NO"         nvarchar2(50),   -- 申请单号
   "ST_APPLY_ORGAN_ID"    nvarchar2(50),   -- 申请单位ID
   "ST_APPLY_ORGAN_NAME"  nvarchar2(50),   -- 申请单位名称
   "ST_MAIN_ORG_ID"       nvarchar2(50),   -- 保障部门ID
   "ST_MAIN_ORG_NAME"     nvarchar2(50),   -- 保障部门名称
   "NM_STATUS"            decimal(1),   -- 状态
   "ST_APPLY_USER_NAME"   nvarchar2(50),   -- 联系人
   "ST_APPLY_USER_PHONE"  nvarchar2(50),   -- 手机
   "ST_APPLY_USER_MOBILE" nvarchar2(50),   -- 固定电话
   "ST_APPLY_USER_EMAIL"  nvarchar2(50),   -- 电子邮箱
   "ST_DESC"              nvarchar2(500),   -- 情况说明
   "DT_PLAN_CREATE"       timestamp,   -- 计划接入时间
   "ST_APPLY_USER_ID"     nvarchar2(50),   -- 申请人ID
   "ST_APPLY_USER_NAME2"  nvarchar2(50),   -- 申请人姓名
   "DT_CREATE"            timestamp,   -- 创建时间
   "ST_EXT1"              nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"              nvarchar2(50),   -- 扩展字段2
   primary key ("ST_DEVICE_APPLY_ID")
)
GO

comment on column "SELM_DEVICE_APPLY"."ST_DEVICE_APPLY_ID" is
'申请ID'
GO

comment on column "SELM_DEVICE_APPLY"."ST_DAPPLY_NO" is
'申请单号'
GO

comment on column "SELM_DEVICE_APPLY"."ST_APPLY_ORGAN_ID" is
'申请单位ID'
GO

comment on column "SELM_DEVICE_APPLY"."ST_APPLY_ORGAN_NAME" is
'申请单位名称'
GO

comment on column "SELM_DEVICE_APPLY"."ST_MAIN_ORG_ID" is
'保障部门ID'
GO

comment on column "SELM_DEVICE_APPLY"."ST_MAIN_ORG_NAME" is
'保障部门名称'
GO

comment on column "SELM_DEVICE_APPLY"."NM_STATUS" is
'状态，0：已保存；1：全部通过：2：部分通过；3：全部不通过'
GO

comment on column "SELM_DEVICE_APPLY"."ST_APPLY_USER_NAME" is
'联系人'
GO

comment on column "SELM_DEVICE_APPLY"."ST_APPLY_USER_PHONE" is
'手机'
GO

comment on column "SELM_DEVICE_APPLY"."ST_APPLY_USER_MOBILE" is
'固定电话'
GO

comment on column "SELM_DEVICE_APPLY"."ST_APPLY_USER_EMAIL" is
'电子邮箱'
GO

comment on column "SELM_DEVICE_APPLY"."ST_DESC" is
'情况说明'
GO

comment on column "SELM_DEVICE_APPLY"."DT_PLAN_CREATE" is
'计划接入时间'
GO

comment on column "SELM_DEVICE_APPLY"."ST_APPLY_USER_ID" is
'申请人ID'
GO

comment on column "SELM_DEVICE_APPLY"."ST_APPLY_USER_NAME2" is
'申请人姓名'
GO

comment on column "SELM_DEVICE_APPLY"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_DEVICE_APPLY"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_DEVICE_APPLY"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 接入申请关联设备                                 */
/* Table Code: SELM_DEVICE_ALINK                                */
/*==============================================================*/
create table "SELM_DEVICE_ALINK" (
   "ST_DEVICE_APPLY_ID" nvarchar2(50) not null,   -- 申请ID
   "ST_MACHINE_ID"      nvarchar2(50) not null,   -- 设备ID
   "ST_DEVICE_NAME"     nvarchar2(100),   -- 设备名称
   "ST_DEVICE_CODE"     nvarchar2(50),   -- 设备编号
   "ST_DEVICE_IP"       nvarchar2(50),   -- 设备IP
   "ST_DEVICE_MAC"      nvarchar2(50),   -- 设备MAC
   "ST_DEVICE_ADDRESS"  nvarchar2(100),   -- 设备详细地址
   "ST_AREA_ID"         nvarchar2(50),   -- 区域ID
   "ST_USER_ID"         nvarchar2(50),   -- 用户ID
   "ST_ADDRESS_ID"      nvarchar2(50),   -- 地址ID
   "ST_ORGAN_ID"        nvarchar2(50),   -- 组织机构ID
   "ST_CERT_KEY"        nvarchar2(50),   -- 证书唯一标识
   "ST_TYPE_ID"         nvarchar2(50),   -- 类型ID
   "NM_IS_HOST"         decimal(1),   -- 是否是主机
   "NM_YBZC"            decimal(1),   -- 是否有医保制册机
   "NM_GPY"             decimal(1),   -- 是否有高拍仪
   "NM_JZZQZ"           decimal(1),   -- 是否有居住证签注机
   "NM_JZZZK"           decimal(1),   -- 是否有居住证制卡机
   "ST_NETWORK"         nvarchar2(50),   -- 网络情况
   "NM_DUTY"            decimal(1),   -- 是否有人员值守
   "NM_STATUS"          decimal(1),   -- 状态
   "ST_REASON"          nvarchar2(100),   -- 批注原因
   "ST_AUDIT_USER_ID"   nvarchar2(50),   -- 审核人ID
   "ST_AUDIT_USER_NAME" nvarchar2(50),   -- 审核人姓名
   "DT_AUDIT"           timestamp,   -- 审核时间
   "DT_CREATE"          timestamp,   -- 创建时间
   "ST_DESC"            nvarchar2(100),   -- 备注
   "ST_EXT1"            nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"            nvarchar2(50),   -- 扩展字段2
   primary key ("ST_DEVICE_APPLY_ID", "ST_MACHINE_ID")
)
GO

comment on column "SELM_DEVICE_ALINK"."ST_DEVICE_APPLY_ID" is
'申请ID'
GO

comment on column "SELM_DEVICE_ALINK"."ST_MACHINE_ID" is
'设备ID'
GO

comment on column "SELM_DEVICE_ALINK"."ST_DEVICE_NAME" is
'设备名称'
GO

comment on column "SELM_DEVICE_ALINK"."ST_DEVICE_CODE" is
'设备编号'
GO

comment on column "SELM_DEVICE_ALINK"."ST_DEVICE_IP" is
'设备IP'
GO

comment on column "SELM_DEVICE_ALINK"."ST_DEVICE_MAC" is
'设备MAC'
GO

comment on column "SELM_DEVICE_ALINK"."ST_DEVICE_ADDRESS" is
'设备详细地址'
GO

comment on column "SELM_DEVICE_ALINK"."ST_AREA_ID" is
'区域ID'
GO

comment on column "SELM_DEVICE_ALINK"."ST_USER_ID" is
'用户ID'
GO

comment on column "SELM_DEVICE_ALINK"."ST_ADDRESS_ID" is
'地址ID'
GO

comment on column "SELM_DEVICE_ALINK"."ST_ORGAN_ID" is
'组织机构ID'
GO

comment on column "SELM_DEVICE_ALINK"."ST_CERT_KEY" is
'证书唯一标识'
GO

comment on column "SELM_DEVICE_ALINK"."ST_TYPE_ID" is
'类型ID'
GO

comment on column "SELM_DEVICE_ALINK"."NM_IS_HOST" is
'是否是主机'
GO

comment on column "SELM_DEVICE_ALINK"."NM_YBZC" is
'是否有医保制册机，0：否；1：是'
GO

comment on column "SELM_DEVICE_ALINK"."NM_GPY" is
'是否有高拍仪，0：否；1：是'
GO

comment on column "SELM_DEVICE_ALINK"."NM_JZZQZ" is
'是否有居住证签注机，0：否；1：是'
GO

comment on column "SELM_DEVICE_ALINK"."NM_JZZZK" is
'是否有居住证制卡机，0：否；1：是'
GO

comment on column "SELM_DEVICE_ALINK"."ST_NETWORK" is
'网络情况，互联网  政务网 '
GO

comment on column "SELM_DEVICE_ALINK"."NM_DUTY" is
'是否有人员值守'
GO

comment on column "SELM_DEVICE_ALINK"."NM_STATUS" is
'状态，0：已保存；1：已提交；2：已修改；3：审核通过；4：审核不通过；5：终止'
GO

comment on column "SELM_DEVICE_ALINK"."ST_REASON" is
'批注原因'
GO

comment on column "SELM_DEVICE_ALINK"."ST_AUDIT_USER_ID" is
'审核人ID'
GO

comment on column "SELM_DEVICE_ALINK"."ST_AUDIT_USER_NAME" is
'审核人姓名'
GO

comment on column "SELM_DEVICE_ALINK"."DT_AUDIT" is
'审核时间'
GO

comment on column "SELM_DEVICE_ALINK"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_DEVICE_ALINK"."ST_DESC" is
'备注'
GO

comment on column "SELM_DEVICE_ALINK"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_DEVICE_ALINK"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 服务关联设备                                     */
/* Table Code: SELM_SERVER_DLINK                                */
/*==============================================================*/
create table "SELM_SERVER_DLINK" (
   "ST_APPLY_ID"        nvarchar2(50) not null,   -- 申请ID
   "ST_MACHINE_ID"      nvarchar2(50) not null,   -- 设备ID
   "NM_STATUS"          decimal(1),   -- 状态
   "ST_REASON"          nvarchar2(100),   -- 批注原因
   "ST_AUDIT_USER_ID"   nvarchar2(50),   -- 审核人ID
   "ST_AUDIT_USER_NAME" nvarchar2(50),   -- 审核人姓名
   "DT_AUDIT"           timestamp,   -- 审核时间
   "DT_CREATE"          timestamp,   -- 创建时间
   "ST_DESC"            nvarchar2(100),   -- 备注
   "ST_EXT1"            nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"            nvarchar2(50),   -- 扩展字段2
   primary key ("ST_APPLY_ID", "ST_MACHINE_ID")
)
GO

comment on column "SELM_SERVER_DLINK"."ST_APPLY_ID" is
'申请ID'
GO

comment on column "SELM_SERVER_DLINK"."ST_MACHINE_ID" is
'设备ID'
GO

comment on column "SELM_SERVER_DLINK"."NM_STATUS" is
'状态，0：已保存；1：已提交；2：已修改；3：审核通过；4：审核不通过；5：终止'
GO

comment on column "SELM_SERVER_DLINK"."ST_REASON" is
'批注原因'
GO

comment on column "SELM_SERVER_DLINK"."ST_AUDIT_USER_ID" is
'审核人ID'
GO

comment on column "SELM_SERVER_DLINK"."ST_AUDIT_USER_NAME" is
'审核人姓名'
GO

comment on column "SELM_SERVER_DLINK"."DT_AUDIT" is
'审核时间'
GO

comment on column "SELM_SERVER_DLINK"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_SERVER_DLINK"."ST_DESC" is
'备注'
GO

comment on column "SELM_SERVER_DLINK"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_SERVER_DLINK"."ST_EXT2" is
'扩展字段2'
GO

/*==============================================================*/
/* Table Name: 健康码出入表                                     */
/* Table Code: SELM_HC_ACCESS                                   */
/*==============================================================*/
create table "SELM_HC_ACCESS" (
   "ST_ACCESS_ID"      nvarchar2(50) not null,   -- 访客ID
   "ST_AREA_ID"        nvarchar2(50),   -- 区域ID
   "ST_ADDRESS_ID"     nvarchar2(50),   -- 地址ID
   "ST_ORGAN_ID"       nvarchar2(50),   -- 组织机构ID
   "ST_VUID"           nvarchar2(50),   -- 访客用户ID
   "ST_DEVICE_ADDRESS" nvarchar2(100),   -- 设备详细地址
   "ST_VISITOR_NAME"   nvarchar2(50),   -- 访客姓名
   "ST_VISITOR_PHONE"  nvarchar2(50),   -- 访客手机号
   "ST_VISITOR_IDCARD" nvarchar2(50),   -- 访客身份证号
   "ST_CARD_ID"        nvarchar2(50),   -- 身份证照片
   "ST_PHOTO_ID"       nvarchar2(50),   -- 访客照片
   "DT_INTER"          timestamp,   -- 进入时间
   "DT_OUTER"          timestamp,   -- 出去时间
   "ST_HSE"            nvarchar2(50),   -- 健康信息
   "ST_TP"             nvarchar2(50),   -- 体温
   "NM_TYPE"           decimal(1),   -- 类型
   "ST_CREATOR"        nvarchar2(50),   -- 创建人
   "ST_CREATOR_NAME"   nvarchar2(50),   -- 创建人姓名
   "DT_CREATE"         timestamp,   -- 创建时间
   "ST_MODIFIER"       nvarchar2(50),   -- 最后修改人
   "ST_MODIFIER_NAME"  nvarchar2(50),   -- 最后修改人姓名
   "DT_MODIFIE"        timestamp,   -- 最后修改时间
   "ST_EXT1"           nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"           nvarchar2(50),   -- 扩展字段2
   primary key ("ST_ACCESS_ID")
)
GO

comment on column "SELM_HC_ACCESS"."ST_ACCESS_ID" is
'访客ID'
GO

comment on column "SELM_HC_ACCESS"."ST_AREA_ID" is
'区域ID'
GO

comment on column "SELM_HC_ACCESS"."ST_ADDRESS_ID" is
'地址ID'
GO

comment on column "SELM_HC_ACCESS"."ST_ORGAN_ID" is
'组织机构ID'
GO

comment on column "SELM_HC_ACCESS"."ST_VUID" is
'访客用户ID'
GO

comment on column "SELM_HC_ACCESS"."ST_DEVICE_ADDRESS" is
'设备详细地址'
GO

comment on column "SELM_HC_ACCESS"."ST_VISITOR_NAME" is
'访客姓名'
GO

comment on column "SELM_HC_ACCESS"."ST_VISITOR_PHONE" is
'访客手机号'
GO

comment on column "SELM_HC_ACCESS"."ST_VISITOR_IDCARD" is
'访客身份证号'
GO

comment on column "SELM_HC_ACCESS"."ST_CARD_ID" is
'身份证照片'
GO

comment on column "SELM_HC_ACCESS"."ST_PHOTO_ID" is
'访客照片'
GO

comment on column "SELM_HC_ACCESS"."DT_INTER" is
'进入时间'
GO

comment on column "SELM_HC_ACCESS"."DT_OUTER" is
'出去时间'
GO

comment on column "SELM_HC_ACCESS"."ST_HSE" is
'健康信息'
GO

comment on column "SELM_HC_ACCESS"."ST_TP" is
'体温'
GO

comment on column "SELM_HC_ACCESS"."NM_TYPE" is
'类型，0：离线码；1：健康码'
GO

comment on column "SELM_HC_ACCESS"."ST_CREATOR" is
'创建人ID'
GO

comment on column "SELM_HC_ACCESS"."ST_CREATOR_NAME" is
'中文名'
GO

comment on column "SELM_HC_ACCESS"."DT_CREATE" is
'创建时间'
GO

comment on column "SELM_HC_ACCESS"."ST_MODIFIER" is
'最后修改人ID'
GO

comment on column "SELM_HC_ACCESS"."ST_MODIFIER_NAME" is
'中文名'
GO

comment on column "SELM_HC_ACCESS"."DT_MODIFIE" is
'最后修改时间'
GO

comment on column "SELM_HC_ACCESS"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_HC_ACCESS"."ST_EXT2" is
'扩展字段2'
GO

