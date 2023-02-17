/****************************************************************/
/**************************** Drops *****************************/
/****************************************************************/

/*==============================================================*/
/* Table Code: SELM_MACHINE                                     */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SELM_MACHINE';
   if obj_count > 0 then
      execute immediate 'drop table "SELM_MACHINE"';
   end if;
end;
GO

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

/****************************************************************/
/************************ Create Tables *************************/
/****************************************************************/

/*==============================================================*/
/* Table Name: 自助设备                                         */
/* Table Code: SELM_MACHINE                                     */
/*==============================================================*/
create table "SELM_MACHINE" (
   "ST_MACHINE_ID"      nvarchar2(50) not null,   -- 设备ID
   "ST_MACHINE_NAME"    nvarchar2(50),   -- 设备名称
   "ST_MACHINE_UNIQUE"  nvarchar2(50),   -- 设备唯一标识
   "ST_MACHINE_ADDRESS" nvarchar2(100),   -- 设备位置
   "ST_DESC"            nvarchar2(100),   -- 设备描述
   "NM_TYPE"            decimal(1),   -- 设备类型
   "ST_VERSION"         nvarchar2(50),   -- 版本
   "ST_FORM_CAT_ID"     nvarchar2(50),   -- 取表目录ID
   "NM_LNG"             decimal(18,15),   -- 经度
   "NM_LAT"             decimal(18,15),   -- 纬度
   "NM_ONLINE"          decimal(1),   -- 是否在线
   "ST_EXT1"            nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"            nvarchar2(50),   -- 扩展字段2
   primary key ("ST_MACHINE_ID")
)
GO

comment on column "SELM_MACHINE"."ST_MACHINE_ID" is
'设备ID'
GO

comment on column "SELM_MACHINE"."ST_MACHINE_NAME" is
'设备名称'
GO

comment on column "SELM_MACHINE"."ST_MACHINE_UNIQUE" is
'设备唯一标识'
GO

comment on column "SELM_MACHINE"."ST_MACHINE_ADDRESS" is
'设备位置'
GO

comment on column "SELM_MACHINE"."ST_DESC" is
'设备描述'
GO

comment on column "SELM_MACHINE"."NM_TYPE" is
'设备类型，0：自助机；2：工作台'
GO

comment on column "SELM_MACHINE"."ST_VERSION" is
'版本，V1.0'
GO

comment on column "SELM_MACHINE"."ST_FORM_CAT_ID" is
'取表目录ID'
GO

comment on column "SELM_MACHINE"."NM_LNG" is
'经度'
GO

comment on column "SELM_MACHINE"."NM_LAT" is
'纬度'
GO

comment on column "SELM_MACHINE"."NM_ONLINE" is
'是否在线，0：不在线；1：在线'
GO

comment on column "SELM_MACHINE"."ST_EXT1" is
'扩展字段1'
GO

comment on column "SELM_MACHINE"."ST_EXT2" is
'扩展字段2'
GO

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

