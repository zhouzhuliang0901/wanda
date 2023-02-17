declare @CurrentUser sysname
select @CurrentUser = user_name()

/****************************************************************/
/**************************** Drops *****************************/
/****************************************************************/

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

/*==============================================================*/
/* Table Code: SELM_QUERY_HIS                                   */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_QUERY_HIS]') and type = 'U') 
   drop table [SELM_QUERY_HIS];

/*==============================================================*/
/* Table Code: SELM_STATISTICS                                  */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_STATISTICS]') and type = 'U') 
   drop table [SELM_STATISTICS];

/*==============================================================*/
/* Table Code: SELM_STATISTICS_DAY                              */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_STATISTICS_DAY]') and type = 'U') 
   drop table [SELM_STATISTICS_DAY];

/*==============================================================*/
/* Table Code: SELM_CLIENT_STAT_DAY                             */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_CLIENT_STAT_DAY]') and type = 'U') 
   drop table [SELM_CLIENT_STAT_DAY];

/*==============================================================*/
/* Table Code: OAUTH2_CLIENT                                    */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[OAUTH2_CLIENT]') and type = 'U') 
   drop table [OAUTH2_CLIENT];

/*==============================================================*/
/* Table Code: OAUTH2_CLIENT_ITEM                               */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[OAUTH2_CLIENT_ITEM]') and type = 'U') 
   drop table [OAUTH2_CLIENT_ITEM];

/*==============================================================*/
/* Table Code: SELM_ITEM_LOG                                    */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_ITEM_LOG]') and type = 'U') 
   drop table [SELM_ITEM_LOG];

/*==============================================================*/
/* Table Code: OAUTH2_CLIENT_DEVICE                             */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[OAUTH2_CLIENT_DEVICE]') and type = 'U') 
   drop table [OAUTH2_CLIENT_DEVICE];

/*==============================================================*/
/* Table Code: SELM_DEVICE_ITEM                                 */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_DEVICE_ITEM]') and type = 'U') 
   drop table [SELM_DEVICE_ITEM];

/*==============================================================*/
/* Table Code: SELM_ACCESS_APPLY                                */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_ACCESS_APPLY]') and type = 'U') 
   drop table [SELM_ACCESS_APPLY];

/*==============================================================*/
/* Table Code: SELM_AREA_QUERY_DAY                              */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_AREA_QUERY_DAY]') and type = 'U') 
   drop table [SELM_AREA_QUERY_DAY];

/*==============================================================*/
/* Table Code: SELM_SERVER_APPLY                                */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_SERVER_APPLY]') and type = 'U') 
   drop table [SELM_SERVER_APPLY];

/*==============================================================*/
/* Table Code: SELM_SERVER_ITEM                                 */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_SERVER_ITEM]') and type = 'U') 
   drop table [SELM_SERVER_ITEM];

/*==============================================================*/
/* Table Code: SELM_BIGSCREEN_CACHE                             */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_BIGSCREEN_CACHE]') and type = 'U') 
   drop table [SELM_BIGSCREEN_CACHE];

/*==============================================================*/
/* Table Code: SELM_ASSIST                                      */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_ASSIST]') and type = 'U') 
   drop table [SELM_ASSIST];

/*==============================================================*/
/* Table Code: SELM_DEVICE_ASSIST                               */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_DEVICE_ASSIST]') and type = 'U') 
   drop table [SELM_DEVICE_ASSIST];

/*==============================================================*/
/* Table Code: SELM_DEVICE_APPLY                                */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_DEVICE_APPLY]') and type = 'U') 
   drop table [SELM_DEVICE_APPLY];

/*==============================================================*/
/* Table Code: SELM_DEVICE_ALINK                                */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_DEVICE_ALINK]') and type = 'U') 
   drop table [SELM_DEVICE_ALINK];

/*==============================================================*/
/* Table Code: SELM_SERVER_DLINK                                */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_SERVER_DLINK]') and type = 'U') 
   drop table [SELM_SERVER_DLINK];

/*==============================================================*/
/* Table Code: SELM_HC_ACCESS                                   */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SELM_HC_ACCESS]') and type = 'U') 
   drop table [SELM_HC_ACCESS];

/****************************************************************/
/************************ Create Tables *************************/
/****************************************************************/


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
   [ST_ORGAN_ID]      nvarchar(50),   -- 所属部门
   [ST_WORK_TYPE]     nvarchar(50),   -- 事项分类
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

execute sp_addextendedproperty 'MS_Description','所属部门', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_ORGAN_ID'

execute sp_addextendedproperty 'MS_Description','办理、查询、预约、其他', 'user', @CurrentUser, 'table', 'SELM_ITEM', 'column', 'ST_WORK_TYPE'

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

/*==============================================================*/
/* Table Name: 查询历史                                         */
/* Table Code: SELM_QUERY_HIS                                   */
/*==============================================================*/
create table [SELM_QUERY_HIS] (
   [ST_QUERY_HIS_ID] nvarchar(50) not null,   -- 历史ID
   [ST_MACHINE_ID]   nvarchar(50),   -- 设备ID
   [ST_ASSIST_ID]    nvarchar(50),   -- 辅助人ID
   [ST_MODULE_NAME]  nvarchar(50),   -- 模块名称
   [ST_MODULE_OP]    nvarchar(50),   -- 操作名称
   [ST_NAME]         nvarchar(50),   -- 姓名
   [ST_IDENTITY_NO]  nvarchar(50),   -- 身份证号
   [ST_MOBILE]       nvarchar(50),   -- 手机号
   [DT_CREATE]       datetime,   -- 创建时间
   [ST_ATTACH_ID1]   nvarchar(50),   -- 附件ID1
   [ST_ATTACH_ID2]   nvarchar(50),   -- 附件ID2
   [ST_ATTACH_ID3]   nvarchar(50),   -- 附件ID3
   [ST_ATTACH_ID4]   nvarchar(50),   -- 附件ID4
   [ST_EXT1]         nvarchar(100),   -- 扩展字段1
   [ST_EXT2]         nvarchar(100),   -- 扩展字段2
   [ST_EXT3]         nvarchar(100),   -- 扩展字段3
   [ST_EXT4]         nvarchar(200),   -- 扩展字段4
   [ST_EXT5]         nvarchar(300),   -- 扩展字段5
   primary key ([ST_QUERY_HIS_ID])
);

execute sp_addextendedproperty 'MS_Description','历史ID', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_QUERY_HIS_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_MACHINE_ID'

execute sp_addextendedproperty 'MS_Description','辅助人ID', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_ASSIST_ID'

execute sp_addextendedproperty 'MS_Description','模块名称', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_MODULE_NAME'

execute sp_addextendedproperty 'MS_Description','操作名称', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_MODULE_OP'

execute sp_addextendedproperty 'MS_Description','姓名', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_NAME'

execute sp_addextendedproperty 'MS_Description','证件号', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_IDENTITY_NO'

execute sp_addextendedproperty 'MS_Description','手机号', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_MOBILE'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','附件ID1', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_ATTACH_ID1'

execute sp_addextendedproperty 'MS_Description','附件ID2', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_ATTACH_ID2'

execute sp_addextendedproperty 'MS_Description','附件ID3', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_ATTACH_ID3'

execute sp_addextendedproperty 'MS_Description','附件ID4', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_ATTACH_ID4'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_EXT2'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_EXT3'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_EXT4'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_QUERY_HIS', 'column', 'ST_EXT5'

/*==============================================================*/
/* Table Name: 业务表                                           */
/* Table Code: SELM_STATISTICS                                  */
/*==============================================================*/
create table [SELM_STATISTICS] (
   [ST_STATISTICS_ID] nvarchar(50) not null,   -- 统计ID
   [ST_NET_FLAG]      nvarchar(50),   -- 业务标识
   [ST_NET_SUB_FLAG]  nvarchar(50),   -- 业务子标识
   [ST_NAME]          nvarchar(50),   -- 业务名称
   [NM_COUNT]         numeric(8),   -- 业务总数
   [NM_ODEVICE]       numeric(1),   -- 是否是外设
   [NM_SORT]          numeric(4),   -- 排序
   [ST_EXT1]          nvarchar(50),   -- 扩展字段1
   [ST_EXT2]          nvarchar(50),   -- 扩展字段2
   primary key ([ST_STATISTICS_ID])
);

execute sp_addextendedproperty 'MS_Description','统计ID', 'user', @CurrentUser, 'table', 'SELM_STATISTICS', 'column', 'ST_STATISTICS_ID'

execute sp_addextendedproperty 'MS_Description','业务标识', 'user', @CurrentUser, 'table', 'SELM_STATISTICS', 'column', 'ST_NET_FLAG'

execute sp_addextendedproperty 'MS_Description','业务子标识', 'user', @CurrentUser, 'table', 'SELM_STATISTICS', 'column', 'ST_NET_SUB_FLAG'

execute sp_addextendedproperty 'MS_Description','业务名称', 'user', @CurrentUser, 'table', 'SELM_STATISTICS', 'column', 'ST_NAME'

execute sp_addextendedproperty 'MS_Description','业务总数', 'user', @CurrentUser, 'table', 'SELM_STATISTICS', 'column', 'NM_COUNT'

execute sp_addextendedproperty 'MS_Description','是否是外设，0：否；1：是', 'user', @CurrentUser, 'table', 'SELM_STATISTICS', 'column', 'NM_ODEVICE'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'SELM_STATISTICS', 'column', 'NM_SORT'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_STATISTICS', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_STATISTICS', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 业务统计（按天）                                 */
/* Table Code: SELM_STATISTICS_DAY                              */
/*==============================================================*/
create table [SELM_STATISTICS_DAY] (
   [ST_STATISTICS_ID] nvarchar(50) not null,   -- 统计ID
   [ST_DATE]          nvarchar(50) not null,   -- 日期字符串
   [NM_COUNT]         numeric(8),   -- 业务总数
   [NM_QUERY]         numeric(8),   -- 业务查询
   [NM_SUCCESS]       numeric(8),   -- 业务成功
   [NM_FAILD]         numeric(8),   -- 业务失败
   [DT_TIME]          datetime,   -- 日期
   [ST_EXT1]          nvarchar(50),   -- 扩展字段1
   [ST_EXT2]          nvarchar(50),   -- 扩展字段2
   primary key ([ST_STATISTICS_ID], [ST_DATE])
);

execute sp_addextendedproperty 'MS_Description','统计ID', 'user', @CurrentUser, 'table', 'SELM_STATISTICS_DAY', 'column', 'ST_STATISTICS_ID'

execute sp_addextendedproperty 'MS_Description','日期字符串', 'user', @CurrentUser, 'table', 'SELM_STATISTICS_DAY', 'column', 'ST_DATE'

execute sp_addextendedproperty 'MS_Description','业务总数', 'user', @CurrentUser, 'table', 'SELM_STATISTICS_DAY', 'column', 'NM_COUNT'

execute sp_addextendedproperty 'MS_Description','业务查询', 'user', @CurrentUser, 'table', 'SELM_STATISTICS_DAY', 'column', 'NM_QUERY'

execute sp_addextendedproperty 'MS_Description','业务成功', 'user', @CurrentUser, 'table', 'SELM_STATISTICS_DAY', 'column', 'NM_SUCCESS'

execute sp_addextendedproperty 'MS_Description','业务失败', 'user', @CurrentUser, 'table', 'SELM_STATISTICS_DAY', 'column', 'NM_FAILD'

execute sp_addextendedproperty 'MS_Description','日期', 'user', @CurrentUser, 'table', 'SELM_STATISTICS_DAY', 'column', 'DT_TIME'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_STATISTICS_DAY', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_STATISTICS_DAY', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 终端业务统计（按天）                             */
/* Table Code: SELM_CLIENT_STAT_DAY                             */
/*==============================================================*/
create table [SELM_CLIENT_STAT_DAY] (
   [ST_STATISTICS_ID] nvarchar(50) not null,   -- 统计ID
   [ST_DATE]          nvarchar(50) not null,   -- 日期字符串
   [ST_MACHINE_ID]    nvarchar(50) not null,   -- 设备ID
   [NM_COUNT]         numeric(8),   -- 业务总数
   [NM_QUERY]         numeric(8),   -- 业务查询
   [NM_SUCCESS]       numeric(8),   -- 业务成功
   [NM_FAILD]         numeric(8),   -- 业务失败
   [DT_TIME]          datetime,   -- 日期
   [ST_EXT1]          nvarchar(50),   -- 扩展字段1
   [ST_EXT2]          nvarchar(50),   -- 扩展字段2
   primary key ([ST_STATISTICS_ID], [ST_DATE], [ST_MACHINE_ID])
);

execute sp_addextendedproperty 'MS_Description','统计ID', 'user', @CurrentUser, 'table', 'SELM_CLIENT_STAT_DAY', 'column', 'ST_STATISTICS_ID'

execute sp_addextendedproperty 'MS_Description','日期字符串', 'user', @CurrentUser, 'table', 'SELM_CLIENT_STAT_DAY', 'column', 'ST_DATE'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'SELM_CLIENT_STAT_DAY', 'column', 'ST_MACHINE_ID'

execute sp_addextendedproperty 'MS_Description','业务总数', 'user', @CurrentUser, 'table', 'SELM_CLIENT_STAT_DAY', 'column', 'NM_COUNT'

execute sp_addextendedproperty 'MS_Description','业务查询', 'user', @CurrentUser, 'table', 'SELM_CLIENT_STAT_DAY', 'column', 'NM_QUERY'

execute sp_addextendedproperty 'MS_Description','业务成功', 'user', @CurrentUser, 'table', 'SELM_CLIENT_STAT_DAY', 'column', 'NM_SUCCESS'

execute sp_addextendedproperty 'MS_Description','业务失败', 'user', @CurrentUser, 'table', 'SELM_CLIENT_STAT_DAY', 'column', 'NM_FAILD'

execute sp_addextendedproperty 'MS_Description','日期', 'user', @CurrentUser, 'table', 'SELM_CLIENT_STAT_DAY', 'column', 'DT_TIME'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_CLIENT_STAT_DAY', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_CLIENT_STAT_DAY', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: OAUTH2认证客户端                                 */
/* Table Code: OAUTH2_CLIENT                                    */
/*==============================================================*/
create table [OAUTH2_CLIENT] (
   [ST_OAUTH2_ID]      nvarchar(50) not null,   -- 认证客户端ID
   [ST_INTERFACE_USER] nvarchar(50),   -- 接口用户名
   [ST_INTERFACE_PWD]  nvarchar(50),   -- 接口密码
   [ST_CLIENT_NAME]    nvarchar(50),   -- 客户端名称
   [ST_CLIENT_ID]      nvarchar(50),   -- 客户端ID
   [ST_CLIENT_SECRET]  nvarchar(50),   -- 客户端安全KEY
   [ST_DESC]           nvarchar(100),   -- 备注
   primary key ([ST_OAUTH2_ID])
);

execute sp_addextendedproperty 'MS_Description','认证客户端ID', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT', 'column', 'ST_OAUTH2_ID'

execute sp_addextendedproperty 'MS_Description','接口用户名', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT', 'column', 'ST_INTERFACE_USER'

execute sp_addextendedproperty 'MS_Description','接口密码', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT', 'column', 'ST_INTERFACE_PWD'

execute sp_addextendedproperty 'MS_Description','客户端名称', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT', 'column', 'ST_CLIENT_NAME'

execute sp_addextendedproperty 'MS_Description','客户端ID', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT', 'column', 'ST_CLIENT_ID'

execute sp_addextendedproperty 'MS_Description','客户端安全KEY', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT', 'column', 'ST_CLIENT_SECRET'

execute sp_addextendedproperty 'MS_Description','备注', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT', 'column', 'ST_DESC'

/*==============================================================*/
/* Table Name: 授权事项                                         */
/* Table Code: OAUTH2_CLIENT_ITEM                               */
/*==============================================================*/
create table [OAUTH2_CLIENT_ITEM] (
   [ST_OAUTH2_ID] nvarchar(50) not null,   -- 认证客户端ID
   [ST_ITEM_ID]   nvarchar(50) not null,   -- 事项ID
   [ST_DEVICE_ID] nvarchar(50) not null,   -- 设备ID
   [NM_STATUS]    numeric(1),   -- 状态
   [NM_ORDER]     numeric(4),   -- 排序
   [DT_CREATE]    datetime,   -- 创建时间
   [DT_UPDATE]    datetime,   -- 更新时间
   primary key ([ST_OAUTH2_ID], [ST_ITEM_ID], [ST_DEVICE_ID])
);

execute sp_addextendedproperty 'MS_Description','认证客户端ID', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT_ITEM', 'column', 'ST_OAUTH2_ID'

execute sp_addextendedproperty 'MS_Description','事项ID', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT_ITEM', 'column', 'ST_ITEM_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT_ITEM', 'column', 'ST_DEVICE_ID'

execute sp_addextendedproperty 'MS_Description','状态，0：删除；1：注册；2：正常；3：禁用；', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT_ITEM', 'column', 'NM_STATUS'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT_ITEM', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT_ITEM', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT_ITEM', 'column', 'DT_UPDATE'

/*==============================================================*/
/* Table Name: 访问日志                                         */
/* Table Code: SELM_ITEM_LOG                                    */
/*==============================================================*/
create table [SELM_ITEM_LOG] (
   [ST_ITEM_LOG_ID] nvarchar(50) not null,   -- 日志ID
   [ST_OAUTH2_ID]   nvarchar(50),   -- 认证客户端ID
   [ST_ITEM_ID]     nvarchar(50),   -- 事项ID
   [ST_ITEM_NAME]   nvarchar(200),   -- 事项名称
   [ST_CLIENT_NAME] nvarchar(50),   -- 客户端名称
   [DT_CREATE]      datetime,   -- 创建时间
   [ST_EXT1]        nvarchar(50),   -- 扩展字段1
   [ST_EXT2]        nvarchar(50),   -- 扩展字段2
   primary key ([ST_ITEM_LOG_ID])
);

execute sp_addextendedproperty 'MS_Description','日志ID', 'user', @CurrentUser, 'table', 'SELM_ITEM_LOG', 'column', 'ST_ITEM_LOG_ID'

execute sp_addextendedproperty 'MS_Description','认证客户端ID', 'user', @CurrentUser, 'table', 'SELM_ITEM_LOG', 'column', 'ST_OAUTH2_ID'

execute sp_addextendedproperty 'MS_Description','事项ID', 'user', @CurrentUser, 'table', 'SELM_ITEM_LOG', 'column', 'ST_ITEM_ID'

execute sp_addextendedproperty 'MS_Description','事项名称', 'user', @CurrentUser, 'table', 'SELM_ITEM_LOG', 'column', 'ST_ITEM_NAME'

execute sp_addextendedproperty 'MS_Description','客户端名称', 'user', @CurrentUser, 'table', 'SELM_ITEM_LOG', 'column', 'ST_CLIENT_NAME'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_ITEM_LOG', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_ITEM_LOG', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_ITEM_LOG', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 客户端关联设备                                   */
/* Table Code: OAUTH2_CLIENT_DEVICE                             */
/*==============================================================*/
create table [OAUTH2_CLIENT_DEVICE] (
   [ST_OAUTH2_ID] nvarchar(50) not null,   -- 认证客户端ID
   [ST_DEVICE_ID] nvarchar(50) not null,   -- 设备ID
   [NM_ORDER]     numeric(4),   -- 排序
   [DT_CREATE]    datetime,   -- 创建时间
   [DT_UPDATE]    datetime,   -- 更新时间
   primary key ([ST_OAUTH2_ID], [ST_DEVICE_ID])
);

execute sp_addextendedproperty 'MS_Description','认证客户端ID', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT_DEVICE', 'column', 'ST_OAUTH2_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT_DEVICE', 'column', 'ST_DEVICE_ID'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT_DEVICE', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT_DEVICE', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'OAUTH2_CLIENT_DEVICE', 'column', 'DT_UPDATE'

/*==============================================================*/
/* Table Name: 设备关联事项                                     */
/* Table Code: SELM_DEVICE_ITEM                                 */
/*==============================================================*/
create table [SELM_DEVICE_ITEM] (
   [ST_ITEM_ID]   nvarchar(50) not null,   -- 事项ID
   [ST_DEVICE_ID] nvarchar(50) not null,   -- 设备ID
   [NM_STATUS]    numeric(1),   -- 状态
   [NM_ORDER]     numeric(4),   -- 排序
   [DT_CREATE]    datetime,   -- 创建时间
   [DT_UPDATE]    datetime,   -- 更新时间
   primary key ([ST_ITEM_ID], [ST_DEVICE_ID])
);

execute sp_addextendedproperty 'MS_Description','事项ID', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ITEM', 'column', 'ST_ITEM_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ITEM', 'column', 'ST_DEVICE_ID'

execute sp_addextendedproperty 'MS_Description','状态，0：删除；1：注册；2：正常；3：禁用；', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ITEM', 'column', 'NM_STATUS'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ITEM', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ITEM', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ITEM', 'column', 'DT_UPDATE'

/*==============================================================*/
/* Table Name: 接入申请                                         */
/* Table Code: SELM_ACCESS_APPLY                                */
/*==============================================================*/
create table [SELM_ACCESS_APPLY] (
   [ST_ACCESS_APPLY_ID] nvarchar(50) not null,   -- 申请ID
   [ST_APPLY_TITLE]     nvarchar(200),   -- 申请标题
   [ST_APPLY_CONTENT]   nvarchar(2000),   -- 申请内容
   [ST_ATTACH_ID]       nvarchar(50),   -- 附件ID
   [NM_STATUS]          numeric(1),   -- 状态
   [ST_APPLY_USER_ID]   nvarchar(50),   -- 申请人ID
   [ST_APPLY_USER_NAME] nvarchar(50),   -- 申请人姓名
   [DT_APPLY]           datetime,   -- 申请时间
   [ST_AUDIT_USER_ID]   nvarchar(50),   -- 审核人ID
   [ST_AUDIT_USER_NAME] nvarchar(50),   -- 审核人姓名
   [DT_AUDIT]           datetime,   -- 审核时间
   [DT_CREATE]          datetime,   -- 创建时间
   [ST_DESC]            nvarchar(100),   -- 备注
   [ST_EXT1]            nvarchar(50),   -- 扩展字段1
   [ST_EXT2]            nvarchar(50),   -- 扩展字段2
   primary key ([ST_ACCESS_APPLY_ID])
);

execute sp_addextendedproperty 'MS_Description','申请ID', 'user', @CurrentUser, 'table', 'SELM_ACCESS_APPLY', 'column', 'ST_ACCESS_APPLY_ID'

execute sp_addextendedproperty 'MS_Description','申请标题', 'user', @CurrentUser, 'table', 'SELM_ACCESS_APPLY', 'column', 'ST_APPLY_TITLE'

execute sp_addextendedproperty 'MS_Description','申请内容', 'user', @CurrentUser, 'table', 'SELM_ACCESS_APPLY', 'column', 'ST_APPLY_CONTENT'

execute sp_addextendedproperty 'MS_Description','附件ID，关联附件表', 'user', @CurrentUser, 'table', 'SELM_ACCESS_APPLY', 'column', 'ST_ATTACH_ID'

execute sp_addextendedproperty 'MS_Description','状态，0：已保存；1：已提交；2：已修改；3：审核通过；4：审核不通过；5：终止', 'user', @CurrentUser, 'table', 'SELM_ACCESS_APPLY', 'column', 'NM_STATUS'

execute sp_addextendedproperty 'MS_Description','申请人ID', 'user', @CurrentUser, 'table', 'SELM_ACCESS_APPLY', 'column', 'ST_APPLY_USER_ID'

execute sp_addextendedproperty 'MS_Description','申请人姓名', 'user', @CurrentUser, 'table', 'SELM_ACCESS_APPLY', 'column', 'ST_APPLY_USER_NAME'

execute sp_addextendedproperty 'MS_Description','申请时间', 'user', @CurrentUser, 'table', 'SELM_ACCESS_APPLY', 'column', 'DT_APPLY'

execute sp_addextendedproperty 'MS_Description','审核人ID', 'user', @CurrentUser, 'table', 'SELM_ACCESS_APPLY', 'column', 'ST_AUDIT_USER_ID'

execute sp_addextendedproperty 'MS_Description','审核人姓名', 'user', @CurrentUser, 'table', 'SELM_ACCESS_APPLY', 'column', 'ST_AUDIT_USER_NAME'

execute sp_addextendedproperty 'MS_Description','审核时间', 'user', @CurrentUser, 'table', 'SELM_ACCESS_APPLY', 'column', 'DT_AUDIT'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_ACCESS_APPLY', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','备注', 'user', @CurrentUser, 'table', 'SELM_ACCESS_APPLY', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_ACCESS_APPLY', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_ACCESS_APPLY', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 区域日办件统计表                                 */
/* Table Code: SELM_AREA_QUERY_DAY                              */
/*==============================================================*/
create table [SELM_AREA_QUERY_DAY] (
   [ST_AREA_ID]       nvarchar(50) not null,   -- 区域标识
   [ST_DAY]           nvarchar(50) not null,   -- 天
   [ST_AREA_NAME]     nvarchar(50),   -- 区域名称
   [NM_GOV_NUMBER]    numeric(18),   -- 政务服务自助终端数量
   [NM_SOCIAL_NUMBER] numeric(18),   -- 社会化自助终端数量
   [NM_DAY]           numeric(18),   -- 日办件数量
   primary key ([ST_AREA_ID], [ST_DAY])
);

execute sp_addextendedproperty 'MS_Description','区域标识', 'user', @CurrentUser, 'table', 'SELM_AREA_QUERY_DAY', 'column', 'ST_AREA_ID'

execute sp_addextendedproperty 'MS_Description','天，yyyy-MM-dd', 'user', @CurrentUser, 'table', 'SELM_AREA_QUERY_DAY', 'column', 'ST_DAY'

execute sp_addextendedproperty 'MS_Description','区域名称', 'user', @CurrentUser, 'table', 'SELM_AREA_QUERY_DAY', 'column', 'ST_AREA_NAME'

execute sp_addextendedproperty 'MS_Description','政务服务自助终端数量', 'user', @CurrentUser, 'table', 'SELM_AREA_QUERY_DAY', 'column', 'NM_GOV_NUMBER'

execute sp_addextendedproperty 'MS_Description','社会化自助终端数量', 'user', @CurrentUser, 'table', 'SELM_AREA_QUERY_DAY', 'column', 'NM_SOCIAL_NUMBER'

execute sp_addextendedproperty 'MS_Description','日办件数量', 'user', @CurrentUser, 'table', 'SELM_AREA_QUERY_DAY', 'column', 'NM_DAY'

/*==============================================================*/
/* Table Name: 服务开通申请                                     */
/* Table Code: SELM_SERVER_APPLY                                */
/*==============================================================*/
create table [SELM_SERVER_APPLY] (
   [ST_APPLY_ID]           nvarchar(50) not null,   -- 申请ID
   [ST_APPLY_ORGAN_ID]     nvarchar(50),   -- 申请单位ID
   [ST_APPLY_ORGAN_NAME]   nvarchar(50),   -- 申请单位名称
   [NM_STATUS]             numeric(1),   -- 状态
   [ST_SERVER_USER_NAME]   nvarchar(50),   -- 联系人
   [ST_SERVER_USER_PHONE]  nvarchar(50),   -- 手机
   [ST_SERVER_USER_MOBILE] nvarchar(50),   -- 固定电话
   [ST_SERVER_USER_EMAIL]  nvarchar(50),   -- 电子邮箱
   [ST_SERVER_CONTENT]     nvarchar(500),   -- 申请情况说明
   [DT_UP_CREATE]          datetime,   -- 计划上线时间
   [DT_CREATE]             datetime,   -- 创建时间
   [ST_SERVER_DESTRICT]    nvarchar(50),   -- 所在区
   [ST_POINT_NAME]         nvarchar(50),   -- 点位名称
   [ST_PUT_ADDRESS]        nvarchar(100),   -- 摆放地址
   [ST_BUILD_COMPANY]      nvarchar(50),   -- 承建厂商
   [ST_PUT_NUMBER]         nvarchar(50),   -- 预计摆放台数
   [NM_NETWORK]            numeric(1),   -- 现场网络环境
   [ST_WATCH_OVER]         numeric(1),   -- 现场有无值守
   [ST_ATTACH_ID]          nvarchar(50),   -- 附件ID
   [ST_RESULT]             nvarchar(1000),   -- 事项审批结果
   [NM_UPDATE]             numeric(1),   -- 是否可修改
   [ST_EXT1]               nvarchar(50),   -- 扩展字段1
   [ST_EXT2]               nvarchar(50),   -- 扩展字段2
   primary key ([ST_APPLY_ID])
);

execute sp_addextendedproperty 'MS_Description','申请ID', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_APPLY_ID'

execute sp_addextendedproperty 'MS_Description','申请单位ID', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_APPLY_ORGAN_ID'

execute sp_addextendedproperty 'MS_Description','申请单位名称', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_APPLY_ORGAN_NAME'

execute sp_addextendedproperty 'MS_Description','状态，0：已保存；1：全部通过：2：部分通过；3：全部不通过', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'NM_STATUS'

execute sp_addextendedproperty 'MS_Description','联系人', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_SERVER_USER_NAME'

execute sp_addextendedproperty 'MS_Description','手机', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_SERVER_USER_PHONE'

execute sp_addextendedproperty 'MS_Description','固定电话', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_SERVER_USER_MOBILE'

execute sp_addextendedproperty 'MS_Description','电子邮箱', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_SERVER_USER_EMAIL'

execute sp_addextendedproperty 'MS_Description','申请情况说明', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_SERVER_CONTENT'

execute sp_addextendedproperty 'MS_Description','计划上线时间', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'DT_UP_CREATE'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','所在区', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_SERVER_DESTRICT'

execute sp_addextendedproperty 'MS_Description','点位名称', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_POINT_NAME'

execute sp_addextendedproperty 'MS_Description','摆放地址', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_PUT_ADDRESS'

execute sp_addextendedproperty 'MS_Description','承建厂商', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_BUILD_COMPANY'

execute sp_addextendedproperty 'MS_Description','预计摆放台数', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_PUT_NUMBER'

execute sp_addextendedproperty 'MS_Description','现场网络环境 0：政务外网 1：互联网', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'NM_NETWORK'

execute sp_addextendedproperty 'MS_Description','现场有无值守0：有 1：无', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_WATCH_OVER'

execute sp_addextendedproperty 'MS_Description','附件ID，关联附件表', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_ATTACH_ID'

execute sp_addextendedproperty 'MS_Description','事项审批结果', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_RESULT'

execute sp_addextendedproperty 'MS_Description','是否可修改，0：否；1：是', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'NM_UPDATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_SERVER_APPLY', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 服务（设备）关联事项                             */
/* Table Code: SELM_SERVER_ITEM                                 */
/*==============================================================*/
create table [SELM_SERVER_ITEM] (
   [ST_LINKS_ID]        nvarchar(50) not null,   -- 关联ID
   [ST_APPLY_ID]        nvarchar(50),   -- 申请ID
   [ST_DEVICE_ID]       nvarchar(50),   -- 设备ID
   [ST_ITEM_TYPE_ID]    nvarchar(50),   -- 事项类别ID
   [ST_ITEM_ID]         nvarchar(50),   -- 事项ID
   [ST_ITEM_NO]         nvarchar(50),   -- 事项编码
   [ST_ITEM_NAME]       nvarchar(200),   -- 事项名称
   [ST_ORGAN_ID]        nvarchar(50),   -- 所属部门
   [NM_PASS]            numeric(1),   -- 是否通过
   [NM_STATUS]          numeric(1),   -- 状态
   [ST_REASON]          nvarchar(100),   -- 批注原因
   [NM_TYPE]            numeric(1),   -- 关联类别
   [ST_AUDIT_USER_ID]   nvarchar(50),   -- 审核人ID
   [ST_AUDIT_USER_NAME] nvarchar(50),   -- 审核人姓名
   [DT_AUDIT]           datetime,   -- 审核时间
   primary key ([ST_LINKS_ID])
);

execute sp_addextendedproperty 'MS_Description','关联ID', 'user', @CurrentUser, 'table', 'SELM_SERVER_ITEM', 'column', 'ST_LINKS_ID'

execute sp_addextendedproperty 'MS_Description','申请ID', 'user', @CurrentUser, 'table', 'SELM_SERVER_ITEM', 'column', 'ST_APPLY_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'SELM_SERVER_ITEM', 'column', 'ST_DEVICE_ID'

execute sp_addextendedproperty 'MS_Description','事项类别ID', 'user', @CurrentUser, 'table', 'SELM_SERVER_ITEM', 'column', 'ST_ITEM_TYPE_ID'

execute sp_addextendedproperty 'MS_Description','事项ID', 'user', @CurrentUser, 'table', 'SELM_SERVER_ITEM', 'column', 'ST_ITEM_ID'

execute sp_addextendedproperty 'MS_Description','事项编码', 'user', @CurrentUser, 'table', 'SELM_SERVER_ITEM', 'column', 'ST_ITEM_NO'

execute sp_addextendedproperty 'MS_Description','事项名称', 'user', @CurrentUser, 'table', 'SELM_SERVER_ITEM', 'column', 'ST_ITEM_NAME'

execute sp_addextendedproperty 'MS_Description','所属部门', 'user', @CurrentUser, 'table', 'SELM_SERVER_ITEM', 'column', 'ST_ORGAN_ID'

execute sp_addextendedproperty 'MS_Description','是否通过，0：否；1：是', 'user', @CurrentUser, 'table', 'SELM_SERVER_ITEM', 'column', 'NM_PASS'

execute sp_addextendedproperty 'MS_Description','状态，0：已保存；1：已提交；2：已修改；3：审核通过；4：审核不通过；5：终止', 'user', @CurrentUser, 'table', 'SELM_SERVER_ITEM', 'column', 'NM_STATUS'

execute sp_addextendedproperty 'MS_Description','批注原因', 'user', @CurrentUser, 'table', 'SELM_SERVER_ITEM', 'column', 'ST_REASON'

execute sp_addextendedproperty 'MS_Description','关联类别，0：事项；1：组别；2：设备事项', 'user', @CurrentUser, 'table', 'SELM_SERVER_ITEM', 'column', 'NM_TYPE'

execute sp_addextendedproperty 'MS_Description','审核人ID', 'user', @CurrentUser, 'table', 'SELM_SERVER_ITEM', 'column', 'ST_AUDIT_USER_ID'

execute sp_addextendedproperty 'MS_Description','审核人姓名', 'user', @CurrentUser, 'table', 'SELM_SERVER_ITEM', 'column', 'ST_AUDIT_USER_NAME'

execute sp_addextendedproperty 'MS_Description','审核时间', 'user', @CurrentUser, 'table', 'SELM_SERVER_ITEM', 'column', 'DT_AUDIT'

/*==============================================================*/
/* Table Name: 大屏统计缓存表                                   */
/* Table Code: SELM_BIGSCREEN_CACHE                             */
/*==============================================================*/
create table [SELM_BIGSCREEN_CACHE] (
   [ST_BIGSCREEN_CACHE_ID] nvarchar(50) not null,   -- 缓存ID
   [ST_FRAME]              nvarchar(50),   -- 框架标识
   [ST_FCODE]              nvarchar(50),   -- 一级标识
   [ST_SCODE]              nvarchar(50),   -- 二级标识
   [ST_TCODE]              nvarchar(50),   -- 三级标识
   [ST_JSON]               nvarchar(5000),   -- JSON数据
   [ST_CLOB_ID]            nvarchar(50),   -- 超大数据
   [NM_ORDER]              numeric(4),   -- 排序
   [DT_CREATE]             datetime,   -- 创建时间
   [DT_UPDATE]             datetime,   -- 修改时间
   [ST_EXT1]               nvarchar(50),   -- 扩展字段1
   [ST_EXT2]               nvarchar(50),   -- 扩展字段2
   primary key ([ST_BIGSCREEN_CACHE_ID])
);

execute sp_addextendedproperty 'MS_Description','缓存ID', 'user', @CurrentUser, 'table', 'SELM_BIGSCREEN_CACHE', 'column', 'ST_BIGSCREEN_CACHE_ID'

execute sp_addextendedproperty 'MS_Description','框架标识', 'user', @CurrentUser, 'table', 'SELM_BIGSCREEN_CACHE', 'column', 'ST_FRAME'

execute sp_addextendedproperty 'MS_Description','一级标识，页面标识', 'user', @CurrentUser, 'table', 'SELM_BIGSCREEN_CACHE', 'column', 'ST_FCODE'

execute sp_addextendedproperty 'MS_Description','二级标识，页面功能模块', 'user', @CurrentUser, 'table', 'SELM_BIGSCREEN_CACHE', 'column', 'ST_SCODE'

execute sp_addextendedproperty 'MS_Description','三级标识，页面子功能模块', 'user', @CurrentUser, 'table', 'SELM_BIGSCREEN_CACHE', 'column', 'ST_TCODE'

execute sp_addextendedproperty 'MS_Description','JSON数据', 'user', @CurrentUser, 'table', 'SELM_BIGSCREEN_CACHE', 'column', 'ST_JSON'

execute sp_addextendedproperty 'MS_Description','超大数据，关联附件表', 'user', @CurrentUser, 'table', 'SELM_BIGSCREEN_CACHE', 'column', 'ST_CLOB_ID'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'SELM_BIGSCREEN_CACHE', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_BIGSCREEN_CACHE', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','修改时间', 'user', @CurrentUser, 'table', 'SELM_BIGSCREEN_CACHE', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_BIGSCREEN_CACHE', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_BIGSCREEN_CACHE', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 设备辅助人员                                     */
/* Table Code: SELM_ASSIST                                      */
/*==============================================================*/
create table [SELM_ASSIST] (
   [ST_ASSIST_ID]     nvarchar(50) not null,   -- 辅助人ID
   [ST_ASSIST_NAME]   nvarchar(50),   -- 辅助人姓名
   [ST_ASSIST_PHONE]  nvarchar(50),   -- 辅助人手机号
   [ST_ASSIST_IDCARD] nvarchar(50),   -- 辅助人身份证号
   [NM_ORDER]         numeric(1),   -- 排序
   [DT_CREATE]        datetime,   -- 创建时间
   [DT_UPADTE]        datetime,   -- 修改时间
   [ST_EXT1]          nvarchar(50),   -- 扩展字段1
   [ST_EXT2]          nvarchar(50),   -- 扩展字段2
   primary key ([ST_ASSIST_ID])
);

execute sp_addextendedproperty 'MS_Description','辅助人ID', 'user', @CurrentUser, 'table', 'SELM_ASSIST', 'column', 'ST_ASSIST_ID'

execute sp_addextendedproperty 'MS_Description','辅助人姓名', 'user', @CurrentUser, 'table', 'SELM_ASSIST', 'column', 'ST_ASSIST_NAME'

execute sp_addextendedproperty 'MS_Description','辅助人手机号', 'user', @CurrentUser, 'table', 'SELM_ASSIST', 'column', 'ST_ASSIST_PHONE'

execute sp_addextendedproperty 'MS_Description','辅助人身份证号', 'user', @CurrentUser, 'table', 'SELM_ASSIST', 'column', 'ST_ASSIST_IDCARD'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'SELM_ASSIST', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_ASSIST', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','修改时间', 'user', @CurrentUser, 'table', 'SELM_ASSIST', 'column', 'DT_UPADTE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_ASSIST', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_ASSIST', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 设备关联人员                                     */
/* Table Code: SELM_DEVICE_ASSIST                               */
/*==============================================================*/
create table [SELM_DEVICE_ASSIST] (
   [ST_ASSIST_ID] nvarchar(50) not null,   -- 辅助人ID
   [ST_DEVICE_ID] nvarchar(50) not null,   -- 设备ID
   [NM_ORDER]     numeric(8),   -- 排序
   primary key ([ST_ASSIST_ID], [ST_DEVICE_ID])
);

execute sp_addextendedproperty 'MS_Description','辅助人ID', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ASSIST', 'column', 'ST_ASSIST_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ASSIST', 'column', 'ST_DEVICE_ID'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ASSIST', 'column', 'NM_ORDER'

/*==============================================================*/
/* Table Name: 设备接入申请                                     */
/* Table Code: SELM_DEVICE_APPLY                                */
/*==============================================================*/
create table [SELM_DEVICE_APPLY] (
   [ST_DEVICE_APPLY_ID]   nvarchar(50) not null,   -- 申请ID
   [ST_DAPPLY_NO]         nvarchar(50),   -- 申请单号
   [ST_APPLY_ORGAN_ID]    nvarchar(50),   -- 申请单位ID
   [ST_APPLY_ORGAN_NAME]  nvarchar(50),   -- 申请单位名称
   [ST_MAIN_ORG_ID]       nvarchar(50),   -- 保障部门ID
   [ST_MAIN_ORG_NAME]     nvarchar(50),   -- 保障部门名称
   [NM_STATUS]            numeric(1),   -- 状态
   [ST_APPLY_USER_NAME]   nvarchar(50),   -- 联系人
   [ST_APPLY_USER_PHONE]  nvarchar(50),   -- 手机
   [ST_APPLY_USER_MOBILE] nvarchar(50),   -- 固定电话
   [ST_APPLY_USER_EMAIL]  nvarchar(50),   -- 电子邮箱
   [ST_DESC]              nvarchar(500),   -- 情况说明
   [DT_PLAN_CREATE]       datetime,   -- 计划接入时间
   [ST_APPLY_USER_ID]     nvarchar(50),   -- 申请人ID
   [ST_APPLY_USER_NAME2]  nvarchar(50),   -- 申请人姓名
   [DT_CREATE]            datetime,   -- 创建时间
   [ST_EXT1]              nvarchar(50),   -- 扩展字段1
   [ST_EXT2]              nvarchar(50),   -- 扩展字段2
   primary key ([ST_DEVICE_APPLY_ID])
);

execute sp_addextendedproperty 'MS_Description','申请ID', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'ST_DEVICE_APPLY_ID'

execute sp_addextendedproperty 'MS_Description','申请单号', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'ST_DAPPLY_NO'

execute sp_addextendedproperty 'MS_Description','申请单位ID', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'ST_APPLY_ORGAN_ID'

execute sp_addextendedproperty 'MS_Description','申请单位名称', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'ST_APPLY_ORGAN_NAME'

execute sp_addextendedproperty 'MS_Description','保障部门ID', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'ST_MAIN_ORG_ID'

execute sp_addextendedproperty 'MS_Description','保障部门名称', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'ST_MAIN_ORG_NAME'

execute sp_addextendedproperty 'MS_Description','状态，0：已保存；1：全部通过：2：部分通过；3：全部不通过', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'NM_STATUS'

execute sp_addextendedproperty 'MS_Description','联系人', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'ST_APPLY_USER_NAME'

execute sp_addextendedproperty 'MS_Description','手机', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'ST_APPLY_USER_PHONE'

execute sp_addextendedproperty 'MS_Description','固定电话', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'ST_APPLY_USER_MOBILE'

execute sp_addextendedproperty 'MS_Description','电子邮箱', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'ST_APPLY_USER_EMAIL'

execute sp_addextendedproperty 'MS_Description','情况说明', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','计划接入时间', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'DT_PLAN_CREATE'

execute sp_addextendedproperty 'MS_Description','申请人ID', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'ST_APPLY_USER_ID'

execute sp_addextendedproperty 'MS_Description','申请人姓名', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'ST_APPLY_USER_NAME2'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_DEVICE_APPLY', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 接入申请关联设备                                 */
/* Table Code: SELM_DEVICE_ALINK                                */
/*==============================================================*/
create table [SELM_DEVICE_ALINK] (
   [ST_DEVICE_APPLY_ID] nvarchar(50) not null,   -- 申请ID
   [ST_MACHINE_ID]      nvarchar(50) not null,   -- 设备ID
   [ST_DEVICE_NAME]     nvarchar(100),   -- 设备名称
   [ST_DEVICE_CODE]     nvarchar(50),   -- 设备编号
   [ST_DEVICE_IP]       nvarchar(50),   -- 设备IP
   [ST_DEVICE_MAC]      nvarchar(50),   -- 设备MAC
   [ST_DEVICE_ADDRESS]  nvarchar(100),   -- 设备详细地址
   [ST_AREA_ID]         nvarchar(50),   -- 区域ID
   [ST_USER_ID]         nvarchar(50),   -- 用户ID
   [ST_ADDRESS_ID]      nvarchar(50),   -- 地址ID
   [ST_ORGAN_ID]        nvarchar(50),   -- 组织机构ID
   [ST_CERT_KEY]        nvarchar(50),   -- 证书唯一标识
   [ST_TYPE_ID]         nvarchar(50),   -- 类型ID
   [NM_IS_HOST]         numeric(1),   -- 是否是主机
   [NM_YBZC]            numeric(1),   -- 是否有医保制册机
   [NM_GPY]             numeric(1),   -- 是否有高拍仪
   [NM_JZZQZ]           numeric(1),   -- 是否有居住证签注机
   [NM_JZZZK]           numeric(1),   -- 是否有居住证制卡机
   [ST_NETWORK]         nvarchar(50),   -- 网络情况
   [NM_DUTY]            numeric(1),   -- 是否有人员值守
   [NM_STATUS]          numeric(1),   -- 状态
   [ST_REASON]          nvarchar(100),   -- 批注原因
   [ST_AUDIT_USER_ID]   nvarchar(50),   -- 审核人ID
   [ST_AUDIT_USER_NAME] nvarchar(50),   -- 审核人姓名
   [DT_AUDIT]           datetime,   -- 审核时间
   [DT_CREATE]          datetime,   -- 创建时间
   [ST_DESC]            nvarchar(100),   -- 备注
   [ST_EXT1]            nvarchar(50),   -- 扩展字段1
   [ST_EXT2]            nvarchar(50),   -- 扩展字段2
   primary key ([ST_DEVICE_APPLY_ID], [ST_MACHINE_ID])
);

execute sp_addextendedproperty 'MS_Description','申请ID', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_DEVICE_APPLY_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_MACHINE_ID'

execute sp_addextendedproperty 'MS_Description','设备名称', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_DEVICE_NAME'

execute sp_addextendedproperty 'MS_Description','设备编号', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_DEVICE_CODE'

execute sp_addextendedproperty 'MS_Description','设备IP', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_DEVICE_IP'

execute sp_addextendedproperty 'MS_Description','设备MAC', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_DEVICE_MAC'

execute sp_addextendedproperty 'MS_Description','设备详细地址', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_DEVICE_ADDRESS'

execute sp_addextendedproperty 'MS_Description','区域ID', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_AREA_ID'

execute sp_addextendedproperty 'MS_Description','用户ID', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','地址ID', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_ADDRESS_ID'

execute sp_addextendedproperty 'MS_Description','组织机构ID', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_ORGAN_ID'

execute sp_addextendedproperty 'MS_Description','证书唯一标识', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_CERT_KEY'

execute sp_addextendedproperty 'MS_Description','类型ID', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_TYPE_ID'

execute sp_addextendedproperty 'MS_Description','是否是主机', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'NM_IS_HOST'

execute sp_addextendedproperty 'MS_Description','是否有医保制册机，0：否；1：是', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'NM_YBZC'

execute sp_addextendedproperty 'MS_Description','是否有高拍仪，0：否；1：是', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'NM_GPY'

execute sp_addextendedproperty 'MS_Description','是否有居住证签注机，0：否；1：是', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'NM_JZZQZ'

execute sp_addextendedproperty 'MS_Description','是否有居住证制卡机，0：否；1：是', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'NM_JZZZK'

execute sp_addextendedproperty 'MS_Description','网络情况，互联网  政务网 ', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_NETWORK'

execute sp_addextendedproperty 'MS_Description','是否有人员值守', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'NM_DUTY'

execute sp_addextendedproperty 'MS_Description','状态，0：已保存；1：已提交；2：已修改；3：审核通过；4：审核不通过；5：终止', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'NM_STATUS'

execute sp_addextendedproperty 'MS_Description','批注原因', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_REASON'

execute sp_addextendedproperty 'MS_Description','审核人ID', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_AUDIT_USER_ID'

execute sp_addextendedproperty 'MS_Description','审核人姓名', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_AUDIT_USER_NAME'

execute sp_addextendedproperty 'MS_Description','审核时间', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'DT_AUDIT'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','备注', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_DEVICE_ALINK', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 服务关联设备                                     */
/* Table Code: SELM_SERVER_DLINK                                */
/*==============================================================*/
create table [SELM_SERVER_DLINK] (
   [ST_APPLY_ID]        nvarchar(50) not null,   -- 申请ID
   [ST_MACHINE_ID]      nvarchar(50) not null,   -- 设备ID
   [NM_STATUS]          numeric(1),   -- 状态
   [ST_REASON]          nvarchar(100),   -- 批注原因
   [ST_AUDIT_USER_ID]   nvarchar(50),   -- 审核人ID
   [ST_AUDIT_USER_NAME] nvarchar(50),   -- 审核人姓名
   [DT_AUDIT]           datetime,   -- 审核时间
   [DT_CREATE]          datetime,   -- 创建时间
   [ST_DESC]            nvarchar(100),   -- 备注
   [ST_EXT1]            nvarchar(50),   -- 扩展字段1
   [ST_EXT2]            nvarchar(50),   -- 扩展字段2
   primary key ([ST_APPLY_ID], [ST_MACHINE_ID])
);

execute sp_addextendedproperty 'MS_Description','申请ID', 'user', @CurrentUser, 'table', 'SELM_SERVER_DLINK', 'column', 'ST_APPLY_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'SELM_SERVER_DLINK', 'column', 'ST_MACHINE_ID'

execute sp_addextendedproperty 'MS_Description','状态，0：已保存；1：已提交；2：已修改；3：审核通过；4：审核不通过；5：终止', 'user', @CurrentUser, 'table', 'SELM_SERVER_DLINK', 'column', 'NM_STATUS'

execute sp_addextendedproperty 'MS_Description','批注原因', 'user', @CurrentUser, 'table', 'SELM_SERVER_DLINK', 'column', 'ST_REASON'

execute sp_addextendedproperty 'MS_Description','审核人ID', 'user', @CurrentUser, 'table', 'SELM_SERVER_DLINK', 'column', 'ST_AUDIT_USER_ID'

execute sp_addextendedproperty 'MS_Description','审核人姓名', 'user', @CurrentUser, 'table', 'SELM_SERVER_DLINK', 'column', 'ST_AUDIT_USER_NAME'

execute sp_addextendedproperty 'MS_Description','审核时间', 'user', @CurrentUser, 'table', 'SELM_SERVER_DLINK', 'column', 'DT_AUDIT'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_SERVER_DLINK', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','备注', 'user', @CurrentUser, 'table', 'SELM_SERVER_DLINK', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_SERVER_DLINK', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_SERVER_DLINK', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 健康码出入表                                     */
/* Table Code: SELM_HC_ACCESS                                   */
/*==============================================================*/
create table [SELM_HC_ACCESS] (
   [ST_ACCESS_ID]      nvarchar(50) not null,   -- 访客ID
   [ST_AREA_ID]        nvarchar(50),   -- 区域ID
   [ST_ADDRESS_ID]     nvarchar(50),   -- 地址ID
   [ST_ORGAN_ID]       nvarchar(50),   -- 组织机构ID
   [ST_VUID]           nvarchar(50),   -- 访客用户ID
   [ST_DEVICE_ADDRESS] nvarchar(100),   -- 设备详细地址
   [ST_VISITOR_NAME]   nvarchar(50),   -- 访客姓名
   [ST_VISITOR_PHONE]  nvarchar(50),   -- 访客手机号
   [ST_VISITOR_IDCARD] nvarchar(50),   -- 访客身份证号
   [ST_CARD_ID]        nvarchar(50),   -- 身份证照片
   [ST_PHOTO_ID]       nvarchar(50),   -- 访客照片
   [DT_INTER]          datetime,   -- 进入时间
   [DT_OUTER]          datetime,   -- 出去时间
   [ST_HSE]            nvarchar(50),   -- 健康信息
   [ST_TP]             nvarchar(50),   -- 体温
   [NM_TYPE]           numeric(1),   -- 类型
   [ST_CREATOR]        nvarchar(50),   -- 创建人
   [ST_CREATOR_NAME]   nvarchar(50),   -- 创建人姓名
   [DT_CREATE]         datetime,   -- 创建时间
   [ST_MODIFIER]       nvarchar(50),   -- 最后修改人
   [ST_MODIFIER_NAME]  nvarchar(50),   -- 最后修改人姓名
   [DT_MODIFIE]        datetime,   -- 最后修改时间
   [ST_EXT1]           nvarchar(50),   -- 扩展字段1
   [ST_EXT2]           nvarchar(50),   -- 扩展字段2
   primary key ([ST_ACCESS_ID])
);

execute sp_addextendedproperty 'MS_Description','访客ID', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_ACCESS_ID'

execute sp_addextendedproperty 'MS_Description','区域ID', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_AREA_ID'

execute sp_addextendedproperty 'MS_Description','地址ID', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_ADDRESS_ID'

execute sp_addextendedproperty 'MS_Description','组织机构ID', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_ORGAN_ID'

execute sp_addextendedproperty 'MS_Description','访客用户ID', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_VUID'

execute sp_addextendedproperty 'MS_Description','设备详细地址', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_DEVICE_ADDRESS'

execute sp_addextendedproperty 'MS_Description','访客姓名', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_VISITOR_NAME'

execute sp_addextendedproperty 'MS_Description','访客手机号', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_VISITOR_PHONE'

execute sp_addextendedproperty 'MS_Description','访客身份证号', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_VISITOR_IDCARD'

execute sp_addextendedproperty 'MS_Description','身份证照片', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_CARD_ID'

execute sp_addextendedproperty 'MS_Description','访客照片', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_PHOTO_ID'

execute sp_addextendedproperty 'MS_Description','进入时间', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'DT_INTER'

execute sp_addextendedproperty 'MS_Description','出去时间', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'DT_OUTER'

execute sp_addextendedproperty 'MS_Description','健康信息', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_HSE'

execute sp_addextendedproperty 'MS_Description','体温', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_TP'

execute sp_addextendedproperty 'MS_Description','类型，0：离线码；1：健康码', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'NM_TYPE'

execute sp_addextendedproperty 'MS_Description','创建人ID', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_CREATOR'

execute sp_addextendedproperty 'MS_Description','中文名', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_CREATOR_NAME'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','最后修改人ID', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_MODIFIER'

execute sp_addextendedproperty 'MS_Description','中文名', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_MODIFIER_NAME'

execute sp_addextendedproperty 'MS_Description','最后修改时间', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'DT_MODIFIE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SELM_HC_ACCESS', 'column', 'ST_EXT2'

