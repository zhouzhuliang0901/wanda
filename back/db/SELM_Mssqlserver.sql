declare @CurrentUser sysname
select @CurrentUser = user_name()

/****************************************************************/
/**************************** Drops *****************************/
/****************************************************************/

/*==============================================================*/
/* Table Code: SELM_MACHINE                                     */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_MACHINE]') and type = 'U') 
   drop table [SELM_MACHINE];

/*==============================================================*/
/* Table Code: SELM_DELIVERY                                    */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_DELIVERY]') and type = 'U') 
   drop table [SELM_DELIVERY];

/*==============================================================*/
/* Table Code: SELM_OPINION                                     */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_OPINION]') and type = 'U') 
   drop table [SELM_OPINION];

/*==============================================================*/
/* Table Code: SELM_DELIVERY_HISTORY                            */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_DELIVERY_HISTORY]') and type = 'U') 
   drop table [SELM_DELIVERY_HISTORY];

/*==============================================================*/
/* Table Code: SELM_ITEM                                        */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_ITEM]') and type = 'U') 
   drop table [SELM_ITEM];

/*==============================================================*/
/* Table Code: SELM_ATTACH                                      */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_ATTACH]') and type = 'U') 
   drop table [SELM_ATTACH];

/*==============================================================*/
/* Table Code: SELM_ITEM_TYPE                                   */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_ITEM_TYPE]') and type = 'U') 
   drop table [SELM_ITEM_TYPE];

/*==============================================================*/
/* Table Code: SELM_ITEM_LINK                                   */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_ITEM_LINK]') and type = 'U') 
   drop table [SELM_ITEM_LINK];

/*==============================================================*/
/* Table Code: SELM_PERSONAL_DOCUMENT                           */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_PERSONAL_DOCUMENT]') and type = 'U') 
   drop table [SELM_PERSONAL_DOCUMENT];

/****************************************************************/
/************************ Create Tables *************************/
/****************************************************************/


/*==============================================================*/
/* Table Name: 自助设备                                         */
/* Table Code: SELM_MACHINE                                     */
/*==============================================================*/
create table [SELM_MACHINE] (
   [ST_MACHINE_ID]      nvarchar(50) not null,   -- 设备ID
   [ST_MACHINE_NAME]    nvarchar(50),   -- 设备名称
   [ST_MACHINE_UNIQUE]  nvarchar(50),   -- 设备唯一标识
   [ST_MACHINE_ADDRESS] nvarchar(100),   -- 设备位置
   [ST_DESC]            nvarchar(100),   -- 设备描述
   [NM_TYPE]            numeric(1),   -- 设备类型
   [ST_VERSION]         nvarchar(50),   -- 版本
   [ST_FORM_CAT_ID]     nvarchar(50),   -- 取表目录ID
   [NM_LNG]             numeric(18,15),   -- 经度
   [NM_LAT]             numeric(18,15),   -- 纬度
   [NM_ONLINE]          numeric(1),   -- 是否在线
   [ST_EXT1]            nvarchar(50),   -- 扩展字段1
   [ST_EXT2]            nvarchar(50),   -- 扩展字段2
   primary key ([ST_MACHINE_ID])
);

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'SELM_MACHINE', 'column', 'ST_MACHINE_ID'

execute sp_addextendedproperty 'MS_Description','设备名称', 'user', @CurrentUser, 'table', 'SELM_MACHINE', 'column', 'ST_MACHINE_NAME'

execute sp_addextendedproperty 'MS_Description','设备唯一标识', 'user', @CurrentUser, 'table', 'SELM_MACHINE', 'column', 'ST_MACHINE_UNIQUE'

execute sp_addextendedproperty 'MS_Description','设备位置', 'user', @CurrentUser, 'table', 'SELM_MACHINE', 'column', 'ST_MACHINE_ADDRESS'

execute sp_addextendedproperty 'MS_Description','设备描述', 'user', @CurrentUser, 'table', 'SELM_MACHINE', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','设备类型，0：自助机；2：工作台', 'user', @CurrentUser, 'table', 'SELM_MACHINE', 'column', 'NM_TYPE'

execute sp_addextendedproperty 'MS_Description','版本，V1.0', 'user', @CurrentUser, 'table', 'SELM_MACHINE', 'column', 'ST_VERSION'

execute sp_addextendedproperty 'MS_Description','取表目录ID', 'user', @CurrentUser, 'table', 'SELM_MACHINE', 'column', 'ST_FORM_CAT_ID'

execute sp_addextendedproperty 'MS_Description','经度', 'user', @CurrentUser, 'table', 'SELM_MACHINE', 'column', 'NM_LNG'

execute sp_addextendedproperty 'MS_Description','纬度', 'user', @CurrentUser, 'table', 'SELM_MACHINE', 'column', 'NM_LAT'

execute sp_addextendedproperty 'MS_Description','是否在线，0：不在线；1：在线', 'user', @CurrentUser, 'table', 'SELM_MACHINE', 'column', 'NM_ONLINE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_MACHINE', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_MACHINE', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 快递柜                                           */
/* Table Code: SELM_DELIVERY                                    */
/*==============================================================*/
create table [SELM_DELIVERY] (
   [ST_DELIVERY_ID]     nvarchar(50) not null,   -- 快递柜ID
   [ST_MACHINE_ID]      nvarchar(50),   -- 设备ID
   [ST_CABINET_NO]      nvarchar(50),   -- 设备柜号
   [ST_CERT_FLOW_NO]    nvarchar(50),   -- 取证号
   [ST_RECEIVER_PHONE]  nvarchar(50),   -- 收件人手机号
   [ST_RECEIVER_NAME]   nvarchar(50),   -- 收件人姓名
   [ST_RECEIVER_IDCARD] nvarchar(50),   -- 收件人身份证号
   [ST_SENDER_ID]       nvarchar(50),   -- 投件人（用户ID）
   [ST_SENDER_NAME]     nvarchar(50),   -- 投件人姓名
   [ST_CERT_NAME]       nvarchar(50),   -- 证照名称
   [NM_TYPE]            numeric(1),   -- 类型
   [ST_APPLY_ID]        nvarchar(50),   -- 关联办件
   [ST_NAME]            nvarchar(100),   -- 企业/个人 名称
   [NM_STATUS]          numeric(1),   -- 状态
   [ST_RECEIVE_NUM]     nvarchar(50),   -- 取件码
   [ST_DESC]            nvarchar(200),   -- 描述
   [DT_CREATE]          datetime,   -- 创建时间
   [DT_STORE]           datetime,   -- 投放时间
   [DT_TAKE]            datetime,   -- 取走时间
   [ST_EXT1]            nvarchar(50),   -- 扩展字段1
   [ST_EXT2]            nvarchar(50),   -- 扩展字段2
   primary key ([ST_DELIVERY_ID])
);

execute sp_addextendedproperty 'MS_Description','快递柜ID', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'ST_DELIVERY_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'ST_MACHINE_ID'

execute sp_addextendedproperty 'MS_Description','设备柜号', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'ST_CABINET_NO'

execute sp_addextendedproperty 'MS_Description','取证号', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'ST_CERT_FLOW_NO'

execute sp_addextendedproperty 'MS_Description','收件人手机号', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'ST_RECEIVER_PHONE'

execute sp_addextendedproperty 'MS_Description','收件人姓名', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'ST_RECEIVER_NAME'

execute sp_addextendedproperty 'MS_Description','收件人身份证号', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'ST_RECEIVER_IDCARD'

execute sp_addextendedproperty 'MS_Description','投件人（用户ID）', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'ST_SENDER_ID'

execute sp_addextendedproperty 'MS_Description','投件人姓名', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'ST_SENDER_NAME'

execute sp_addextendedproperty 'MS_Description','证照名称', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'ST_CERT_NAME'

execute sp_addextendedproperty 'MS_Description','类型，0：企业；1：个人', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'NM_TYPE'

execute sp_addextendedproperty 'MS_Description','关联办件', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'ST_APPLY_ID'

execute sp_addextendedproperty 'MS_Description','企业/个人 名称', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'ST_NAME'

execute sp_addextendedproperty 'MS_Description','状态；0：待存；1：待取；2：已取', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'NM_STATUS'

execute sp_addextendedproperty 'MS_Description','取件码', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'ST_RECEIVE_NUM'

execute sp_addextendedproperty 'MS_Description','描述', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','投放时间', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'DT_STORE'

execute sp_addextendedproperty 'MS_Description','取走时间', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'DT_TAKE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_DELIVERY', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 意见反馈表                                       */
/* Table Code: SELM_OPINION                                     */
/*==============================================================*/
create table [SELM_OPINION] (
   [ST_OPINION_ID]  nvarchar(50) not null,   -- 意见表ID
   [ST_MACHINE_ID]  nvarchar(50),   -- 设备ID
   [ST_UNAME]       nvarchar(50),   -- 姓名
   [ST_PHONE]       nvarchar(50),   -- 手机号
   [ST_UNIT]        nvarchar(100),   -- 单位名称
   [ST_CONTENT]     nvarchar(500),   -- 内容
   [NM_SATISFATION] numeric(1),   -- 满意度评价
   [DT_CREATE]      datetime,   -- 创建时间
   [DT_UPDATE]      datetime,   -- 修改时间
   [ST_EXT1]        nvarchar(50),   -- 扩展字段1
   [ST_EXT2]        nvarchar(50),   -- 扩展字段2
   primary key ([ST_OPINION_ID])
);

execute sp_addextendedproperty 'MS_Description','意见表ID', 'user', @CurrentUser, 'table', 'SELM_OPINION', 'column', 'ST_OPINION_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'SELM_OPINION', 'column', 'ST_MACHINE_ID'

execute sp_addextendedproperty 'MS_Description','姓名', 'user', @CurrentUser, 'table', 'SELM_OPINION', 'column', 'ST_UNAME'

execute sp_addextendedproperty 'MS_Description','手机号', 'user', @CurrentUser, 'table', 'SELM_OPINION', 'column', 'ST_PHONE'

execute sp_addextendedproperty 'MS_Description','单位名称', 'user', @CurrentUser, 'table', 'SELM_OPINION', 'column', 'ST_UNIT'

execute sp_addextendedproperty 'MS_Description','内容', 'user', @CurrentUser, 'table', 'SELM_OPINION', 'column', 'ST_CONTENT'

execute sp_addextendedproperty 'MS_Description','满意度评价，1：非常满意；2：满意；3：一般；4：不满意', 'user', @CurrentUser, 'table', 'SELM_OPINION', 'column', 'NM_SATISFATION'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_OPINION', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','修改时间', 'user', @CurrentUser, 'table', 'SELM_OPINION', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_OPINION', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_OPINION', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 快递柜历史                                       */
/* Table Code: SELM_DELIVERY_HISTORY                            */
/*==============================================================*/
create table [SELM_DELIVERY_HISTORY] (
   [ST_DELIVERY_ID]     nvarchar(50) not null,   -- 快递柜ID
   [ST_MACHINE_ID]      nvarchar(50),   -- 设备ID
   [ST_CABINET_NO]      nvarchar(50),   -- 设备柜号
   [ST_CERT_FLOW_NO]    nvarchar(50),   -- 取证号
   [ST_RECEIVER_PHONE]  nvarchar(50),   -- 收件人手机号
   [ST_RECEIVER_NAME]   nvarchar(50),   -- 收件人姓名
   [ST_RECEIVER_IDCARD] nvarchar(50),   -- 收件人身份证号
   [ST_SENDER_ID]       nvarchar(50),   -- 投件人（用户ID）
   [ST_SENDER_NAME]     nvarchar(50),   -- 投件人姓名
   [ST_CERT_NAME]       nvarchar(50),   -- 证照名称
   [NM_TYPE]            numeric(1),   -- 类型
   [ST_APPLY_ID]        nvarchar(50),   -- 关联办件
   [ST_NAME]            nvarchar(100),   -- 企业/个人 名称
   [NM_STATUS]          numeric(1),   -- 状态
   [ST_RECEIVE_NUM]     nvarchar(50),   -- 取件码
   [ST_DESC]            nvarchar(200),   -- 描述
   [DT_CREATE]          datetime,   -- 创建时间
   [DT_STORE]           datetime,   -- 投放时间
   [DT_TAKE]            datetime,   -- 取走时间
   [ST_EXT1]            nvarchar(50),   -- 扩展字段1
   [ST_EXT2]            nvarchar(50),   -- 扩展字段2
   primary key ([ST_DELIVERY_ID])
);

execute sp_addextendedproperty 'MS_Description','快递柜ID', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'ST_DELIVERY_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'ST_MACHINE_ID'

execute sp_addextendedproperty 'MS_Description','设备柜号', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'ST_CABINET_NO'

execute sp_addextendedproperty 'MS_Description','取证号', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'ST_CERT_FLOW_NO'

execute sp_addextendedproperty 'MS_Description','收件人手机号', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'ST_RECEIVER_PHONE'

execute sp_addextendedproperty 'MS_Description','收件人姓名', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'ST_RECEIVER_NAME'

execute sp_addextendedproperty 'MS_Description','收件人身份证号', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'ST_RECEIVER_IDCARD'

execute sp_addextendedproperty 'MS_Description','投件人（用户ID）', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'ST_SENDER_ID'

execute sp_addextendedproperty 'MS_Description','投件人姓名', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'ST_SENDER_NAME'

execute sp_addextendedproperty 'MS_Description','证照名称', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'ST_CERT_NAME'

execute sp_addextendedproperty 'MS_Description','类型，0：企业；1：个人', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'NM_TYPE'

execute sp_addextendedproperty 'MS_Description','关联办件', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'ST_APPLY_ID'

execute sp_addextendedproperty 'MS_Description','企业/个人 名称', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'ST_NAME'

execute sp_addextendedproperty 'MS_Description','状态；0：待存；1：待取；2：已取', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'NM_STATUS'

execute sp_addextendedproperty 'MS_Description','取件码', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'ST_RECEIVE_NUM'

execute sp_addextendedproperty 'MS_Description','描述', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','投放时间', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'DT_STORE'

execute sp_addextendedproperty 'MS_Description','取走时间', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'DT_TAKE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_DELIVERY_HISTORY', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 事项表                                           */
/* Table Code: SELM_ITEM                                        */
/*==============================================================*/
create table [SELM_ITEM] (
   [ST_ITEM_ID]       nvarchar(50) not null,   -- 事项ID
   [ST_ITEM_NO]       nvarchar(50),   -- 事项编码
   [ST_TEN_CODE]      nvarchar(50),   -- 其他编码
   [ST_MAIN_NAME]     nvarchar(200),   -- 主名称（主事项）
   [ST_ITEM_NAME]     nvarchar(200),   -- 事项名称
   [NM_BELONG]        numeric(4),   -- 事项所属
   [ST_ITEM_TYPE]     nvarchar(50),   -- 事项类型
   [ST_LEGAL_TIME]    nvarchar(50),   -- 法定时限
   [ST_PROMISE_TIME]  nvarchar(50),   -- 承诺时限
   [NM_SORT]          numeric(4),   -- 排序
   [ST_ITEM_GUIDE_ID] nvarchar(50),   -- 事项办事指南
   [NM_TYPE]          numeric(1),   -- 类型
   [ST_PARENT_ID]     nvarchar(50),   -- 父事项ID
   [NM_SHOW_TYPE]     numeric(1),   -- 显示类别
   [ST_WORK_URL]      nvarchar(200),   -- 办理跳转链接
   [DT_CREATE]        datetime,   -- 创建时间
   [DT_UPDATE]        datetime,   -- 更新时间
   [ST_EXT1]          nvarchar(50),   -- 扩展字段1
   [ST_EXT2]          nvarchar(50),   -- 扩展字段2
   [ST_EXT3]          nvarchar(100),   -- 扩展字段3
   [ST_EXT4]          nvarchar(200),   -- 扩展字段4
   primary key ([ST_ITEM_ID])
);

execute sp_addextendedproperty 'MS_Description','事项ID', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_ITEM_ID'

execute sp_addextendedproperty 'MS_Description','事项编码', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_ITEM_NO'

execute sp_addextendedproperty 'MS_Description','其他编码', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_TEN_CODE'

execute sp_addextendedproperty 'MS_Description','主名称（主事项）', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_MAIN_NAME'

execute sp_addextendedproperty 'MS_Description','事项名称', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_ITEM_NAME'

execute sp_addextendedproperty 'MS_Description','法人或者个人', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'NM_BELONG'

execute sp_addextendedproperty 'MS_Description','审批还是服务事项', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_ITEM_TYPE'

execute sp_addextendedproperty 'MS_Description','法定时限', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_LEGAL_TIME'

execute sp_addextendedproperty 'MS_Description','承诺时限', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_PROMISE_TIME'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'NM_SORT'

execute sp_addextendedproperty 'MS_Description','事项办事指南，关联附件', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_ITEM_GUIDE_ID'

execute sp_addextendedproperty 'MS_Description','类型，0：主事项；2：子事项；3：情形', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'NM_TYPE'

execute sp_addextendedproperty 'MS_Description','父事项ID', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_PARENT_ID'

execute sp_addextendedproperty 'MS_Description','显示类别，0：不展示；1：只做线上；2：只做线下；3：线上线下', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'NM_SHOW_TYPE'

execute sp_addextendedproperty 'MS_Description','办理跳转链接', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_WORK_URL'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_EXT2'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_EXT3'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_EXT4'

/*==============================================================*/
/* Table Name: 附件表                                           */
/* Table Code: SELM_ATTACH                                      */
/*==============================================================*/
create table [SELM_ATTACH] (
   [ST_ATTACH_ID]     nvarchar(50) not null,   -- 附件ID
   [ST_LINK_TABLE]    nvarchar(50),   -- 关联表名称
   [ST_LINK_ID]       nvarchar(50),   -- 关联主键值
   [ST_ATTACH_TYPE]   nvarchar(50),   -- 附件类型
   [ST_FILENAME]      nvarchar(100),   -- 文件名
   [ST_FILE_SIZE]     nvarchar(50),   -- 文件大小
   [CL_CONTENT]       text,   -- 文本内容
   [BL_CONTENT]       image,   -- 文件内容
   [BL_SMALL_CONTENT] image,   -- 图片缩略图
   [ST_FILE_TYPE]     nvarchar(10),   -- 文件类型
   [NM_ORDER]         numeric(4),   -- 排序
   [DT_CREATE]        datetime,   -- 创建时间
   [DT_UPDATE]        datetime,   -- 修改时间
   [ST_EXT1]          nvarchar(50),   -- 扩展字段1
   [ST_EXT2]          nvarchar(50),   -- 扩展字段2
   primary key ([ST_ATTACH_ID])
);

execute sp_addextendedproperty 'MS_Description','附件ID', 'user', @CurrentUser, 'table', 'SELM_ATTACH', 'column', 'ST_ATTACH_ID'

execute sp_addextendedproperty 'MS_Description','关联表名称', 'user', @CurrentUser, 'table', 'SELM_ATTACH', 'column', 'ST_LINK_TABLE'

execute sp_addextendedproperty 'MS_Description','关联主键值', 'user', @CurrentUser, 'table', 'SELM_ATTACH', 'column', 'ST_LINK_ID'

execute sp_addextendedproperty 'MS_Description','附件类型', 'user', @CurrentUser, 'table', 'SELM_ATTACH', 'column', 'ST_ATTACH_TYPE'

execute sp_addextendedproperty 'MS_Description','文件名', 'user', @CurrentUser, 'table', 'SELM_ATTACH', 'column', 'ST_FILENAME'

execute sp_addextendedproperty 'MS_Description','文件大小', 'user', @CurrentUser, 'table', 'SELM_ATTACH', 'column', 'ST_FILE_SIZE'

execute sp_addextendedproperty 'MS_Description','文本内容', 'user', @CurrentUser, 'table', 'SELM_ATTACH', 'column', 'CL_CONTENT'

execute sp_addextendedproperty 'MS_Description','文件内容', 'user', @CurrentUser, 'table', 'SELM_ATTACH', 'column', 'BL_CONTENT'

execute sp_addextendedproperty 'MS_Description','图片缩略图', 'user', @CurrentUser, 'table', 'SELM_ATTACH', 'column', 'BL_SMALL_CONTENT'

execute sp_addextendedproperty 'MS_Description','文件类型', 'user', @CurrentUser, 'table', 'SELM_ATTACH', 'column', 'ST_FILE_TYPE'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'SELM_ATTACH', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_ATTACH', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','修改时间', 'user', @CurrentUser, 'table', 'SELM_ATTACH', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_ATTACH', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_ATTACH', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 事项类别                                         */
/* Table Code: SELM_ITEM_TYPE                                   */
/*==============================================================*/
create table [SELM_ITEM_TYPE] (
   [ST_ITEM_TYPE_ID]   nvarchar(50) not null,   -- 事项类别ID
   [ST_ITEM_TYPE_NAME] nvarchar(50),   -- 事项类别名称
   [NM_SORT]           numeric(4),   -- 排序
   [ST_PARENT_ID]      nvarchar(50),   -- 父事项ID
   [DT_CREATE]         datetime,   -- 创建时间
   [DT_UPDATE]         datetime,   -- 更新时间
   [ST_EXT1]           nvarchar(50),   -- 扩展字段1
   [ST_EXT2]           nvarchar(50),   -- 扩展字段2
   primary key ([ST_ITEM_TYPE_ID])
);

execute sp_addextendedproperty 'MS_Description','事项类别ID', 'user', @CurrentUser, 'table', 'SELM_ITEM_TYPE', 'column', 'ST_ITEM_TYPE_ID'

execute sp_addextendedproperty 'MS_Description','事项类别名称', 'user', @CurrentUser, 'table', 'SELM_ITEM_TYPE', 'column', 'ST_ITEM_TYPE_NAME'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'SELM_ITEM_TYPE', 'column', 'NM_SORT'

execute sp_addextendedproperty 'MS_Description','父事项ID', 'user', @CurrentUser, 'table', 'SELM_ITEM_TYPE', 'column', 'ST_PARENT_ID'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_ITEM_TYPE', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'SELM_ITEM_TYPE', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_ITEM_TYPE', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_ITEM_TYPE', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 类别关联事项                                     */
/* Table Code: SELM_ITEM_LINK                                   */
/*==============================================================*/
create table [SELM_ITEM_LINK] (
   [ST_ITEM_ID]      nvarchar(50) not null,   -- 事项ID
   [ST_ITEM_TYPE_ID] nvarchar(50) not null,   -- 事项类别ID
   [NM_SORT]         numeric(4),   -- 排序
   primary key ([ST_ITEM_ID], [ST_ITEM_TYPE_ID])
);

execute sp_addextendedproperty 'MS_Description','事项ID', 'user', @CurrentUser, 'table', 'SELM_ITEM_LINK', 'column', 'ST_ITEM_ID'

execute sp_addextendedproperty 'MS_Description','事项类别ID', 'user', @CurrentUser, 'table', 'SELM_ITEM_LINK', 'column', 'ST_ITEM_TYPE_ID'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'SELM_ITEM_LINK', 'column', 'NM_SORT'

/*==============================================================*/
/* Table Name: 个人档案表                                       */
/* Table Code: SELM_PERSONAL_DOCUMENT                           */
/*==============================================================*/
create table [SELM_PERSONAL_DOCUMENT] (
   [ST_PERSONAL_DOCUMENT] nvarchar(50) not null,   -- 档案ID
   [ST_NAME]              nvarchar(50),   -- 姓名
   [ST_SEX]               nvarchar(50),   -- 性别
   [ST_NATION]            nvarchar(50),   -- 民族
   [ST_BIRTH]             nvarchar(50),   -- 出生年月
   [ST_HOME_ADDRESS]      nvarchar(100),   -- 住址
   [ST_IDENTITY_NO]       nvarchar(50),   -- 身份证号
   [ST_MOBILE]            nvarchar(50),   -- 手机号
   [ST_HEAD_IMAGE_ID]     nvarchar(50),   -- 身份证头像ID
   [ST_FRONT_IMAGE_ID]    nvarchar(50),   -- 身份证正面ID
   [ST_BACK_IMAGE_ID]     nvarchar(50),   -- 身份证反面ID
   [DT_CREATE]            datetime,   -- 创建时间
   [DT_UPDATE]            datetime,   -- 更新时间
   [ST_EXT1]              nvarchar(50),   -- 扩展字段1
   [ST_EXT2]              nvarchar(50),   -- 扩展字段2
   primary key ([ST_PERSONAL_DOCUMENT])
);

execute sp_addextendedproperty 'MS_Description','档案ID', 'user', @CurrentUser, 'table', 'SELM_PERSONAL_DOCUMENT', 'column', 'ST_PERSONAL_DOCUMENT'

execute sp_addextendedproperty 'MS_Description','姓名', 'user', @CurrentUser, 'table', 'SELM_PERSONAL_DOCUMENT', 'column', 'ST_NAME'

execute sp_addextendedproperty 'MS_Description','性别', 'user', @CurrentUser, 'table', 'SELM_PERSONAL_DOCUMENT', 'column', 'ST_SEX'

execute sp_addextendedproperty 'MS_Description','民族', 'user', @CurrentUser, 'table', 'SELM_PERSONAL_DOCUMENT', 'column', 'ST_NATION'

execute sp_addextendedproperty 'MS_Description','出生年月', 'user', @CurrentUser, 'table', 'SELM_PERSONAL_DOCUMENT', 'column', 'ST_BIRTH'

execute sp_addextendedproperty 'MS_Description','住址', 'user', @CurrentUser, 'table', 'SELM_PERSONAL_DOCUMENT', 'column', 'ST_HOME_ADDRESS'

execute sp_addextendedproperty 'MS_Description','证件号', 'user', @CurrentUser, 'table', 'SELM_PERSONAL_DOCUMENT', 'column', 'ST_IDENTITY_NO'

execute sp_addextendedproperty 'MS_Description','手机号', 'user', @CurrentUser, 'table', 'SELM_PERSONAL_DOCUMENT', 'column', 'ST_MOBILE'

execute sp_addextendedproperty 'MS_Description','身份证头像ID，关联附件表', 'user', @CurrentUser, 'table', 'SELM_PERSONAL_DOCUMENT', 'column', 'ST_HEAD_IMAGE_ID'

execute sp_addextendedproperty 'MS_Description','身份证正面ID，关联附件表', 'user', @CurrentUser, 'table', 'SELM_PERSONAL_DOCUMENT', 'column', 'ST_FRONT_IMAGE_ID'

execute sp_addextendedproperty 'MS_Description','身份证反面ID，关联附件表', 'user', @CurrentUser, 'table', 'SELM_PERSONAL_DOCUMENT', 'column', 'ST_BACK_IMAGE_ID'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_PERSONAL_DOCUMENT', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'SELM_PERSONAL_DOCUMENT', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_PERSONAL_DOCUMENT', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_PERSONAL_DOCUMENT', 'column', 'ST_EXT2'

