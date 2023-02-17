declare @CurrentUser sysname
select @CurrentUser = user_name()

/****************************************************************/
/**************************** Drops *****************************/
/****************************************************************/

/*==============================================================*/
/* Table Code: INFOPUB_DEVICE_INFO                              */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_DEVICE_INFO]') and type = 'U') 
   drop table [INFOPUB_DEVICE_INFO];

/*==============================================================*/
/* Table Code: INFOPUB_DEVICE_TYPE                              */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_DEVICE_TYPE]') and type = 'U') 
   drop table [INFOPUB_DEVICE_TYPE];

/*==============================================================*/
/* Table Code: INFOPUB_AREA                                     */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_AREA]') and type = 'U') 
   drop table [INFOPUB_AREA];

/*==============================================================*/
/* Table Code: INFOPUB_ATTACHMENT                               */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_ATTACHMENT]') and type = 'U') 
   drop table [INFOPUB_ATTACHMENT];

/*==============================================================*/
/* Table Code: INFOPUB_ODEVICE_RESULT                           */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_ODEVICE_RESULT]') and type = 'U') 
   drop table [INFOPUB_ODEVICE_RESULT];

/*==============================================================*/
/* Table Code: INFOPUB_DEVICE_RESULT                            */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_DEVICE_RESULT]') and type = 'U') 
   drop table [INFOPUB_DEVICE_RESULT];

/*==============================================================*/
/* Table Code: INFOPUB_DEVICE_LOG                               */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_DEVICE_LOG]') and type = 'U') 
   drop table [INFOPUB_DEVICE_LOG];

/*==============================================================*/
/* Table Code: INFOPUB_IRESOURCE                                */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_IRESOURCE]') and type = 'U') 
   drop table [INFOPUB_IRESOURCE];

/*==============================================================*/
/* Table Code: INFOPUB_GROUP                                    */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_GROUP]') and type = 'U') 
   drop table [INFOPUB_GROUP];

/*==============================================================*/
/* Table Code: INFOPUB_GROUP_DEVICE                             */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_GROUP_DEVICE]') and type = 'U') 
   drop table [INFOPUB_GROUP_DEVICE];

/*==============================================================*/
/* Table Code: INFOPUB_PUBLISH                                  */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_PUBLISH]') and type = 'U') 
   drop table [INFOPUB_PUBLISH];

/*==============================================================*/
/* Table Code: INFOPUB_PSOURCE                                  */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_PSOURCE]') and type = 'U') 
   drop table [INFOPUB_PSOURCE];

/*==============================================================*/
/* Table Code: INFOPUB_ONOFF                                    */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_ONOFF]') and type = 'U') 
   drop table [INFOPUB_ONOFF];

/*==============================================================*/
/* Table Code: INFOPUB_ODEVICE_STATUS                           */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_ODEVICE_STATUS]') and type = 'U') 
   drop table [INFOPUB_ODEVICE_STATUS];

/*==============================================================*/
/* Table Code: INFOPUB_DEVICE_RESULT_HIS                        */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_DEVICE_RESULT_HIS]') and type = 'U') 
   drop table [INFOPUB_DEVICE_RESULT_HIS];

/*==============================================================*/
/* Table Code: INFOPUB_PIC_TYPE                                 */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_PIC_TYPE]') and type = 'U') 
   drop table [INFOPUB_PIC_TYPE];

/*==============================================================*/
/* Table Code: INFOPUB_PIC                                      */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_PIC]') and type = 'U') 
   drop table [INFOPUB_PIC];

/*==============================================================*/
/* Table Code: INFOPUB_FUNC                                     */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_FUNC]') and type = 'U') 
   drop table [INFOPUB_FUNC];

/*==============================================================*/
/* Table Code: INFOPUB_FUNC_TYPE                                */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_FUNC_TYPE]') and type = 'U') 
   drop table [INFOPUB_FUNC_TYPE];

/*==============================================================*/
/* Table Code: INFOPUB_APP                                      */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_APP]') and type = 'U') 
   drop table [INFOPUB_APP];

/*==============================================================*/
/* Table Code: INFOPUB_APP_PIC                                  */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_APP_PIC]') and type = 'U') 
   drop table [INFOPUB_APP_PIC];

/*==============================================================*/
/* Table Code: INFOPUB_APP_FUNC                                 */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_APP_FUNC]') and type = 'U') 
   drop table [INFOPUB_APP_FUNC];

/*==============================================================*/
/* Table Code: INFOPUB_COMPANY                                  */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_COMPANY]') and type = 'U') 
   drop table [INFOPUB_COMPANY];

/*==============================================================*/
/* Table Code: INFOPUB_ADDRESS                                  */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[INFOPUB_ADDRESS]') and type = 'U') 
   drop table [INFOPUB_ADDRESS];

/****************************************************************/
/************************ Create Tables *************************/
/****************************************************************/


/*==============================================================*/
/* Table Name: 设备信息                                         */
/* Table Code: INFOPUB_DEVICE_INFO                              */
/*==============================================================*/
create table [INFOPUB_DEVICE_INFO] (
   [ST_DEVICE_ID]      nvarchar(50) not null,   -- 设备ID
   [ST_DEVICE_NAME]    nvarchar(100),   -- 设备名称
   [ST_DEVICE_CODE]    nvarchar(50),   -- 设备编号
   [ST_DEVICE_IP]      nvarchar(50),   -- 设备IP
   [ST_DEVICE_MAC]     nvarchar(50),   -- 设备MAC
   [ST_DEVICE_ADDRESS] nvarchar(100),   -- 设备详细地址
   [ST_TYPE_ID]        nvarchar(50) not null,   -- 类型ID
   [NM_IS_HOST]        numeric(1),   -- 是否是主机
   [NM_ORDER]          numeric(8),   -- 排序
   [NM_INTERVAL]       numeric(10),   -- 监控间隔
   [NM_RECOVER]        numeric(1),   -- 是否恢复
   [NM_DOWN_TRY]       numeric(4),   -- 故障尝试次数
   [NM_NOTIFICATION]   numeric(1),   -- 是否通知
   [NM_LNG]            numeric(18,15),   -- 经度
   [NM_LAT]            numeric(18,15),   -- 纬度
   [NM_ONLINE]         numeric(1),   -- 是否在线
   [ST_CHANNEL]        nvarchar(50),   -- 消息通道
   [ST_CONFIG_ID]      nvarchar(50),   -- 终端配置
   [NM_SDTYPE]         numeric(1),   -- 设备类型（子类型）
   [ST_USER_ID]        nvarchar(50),   -- 用户ID
   [ST_AREA_ID]        nvarchar(50),   -- 区域ID
   [ST_ADDRESS_ID]     nvarchar(50),   -- 地址ID
   [ST_ORGAN_ID]       nvarchar(50),   -- 组织机构ID
   [ST_CERT_KEY]       nvarchar(50),   -- 证书唯一标识
   [DT_CREATE]         datetime,   -- 创建时间
   [DT_UPDATE]         datetime,   -- 更新时间
   [ST_DESC]           nvarchar(100),   -- 备注
   primary key ([ST_DEVICE_ID])
);

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'ST_DEVICE_ID'

execute sp_addextendedproperty 'MS_Description','设备名称', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'ST_DEVICE_NAME'

execute sp_addextendedproperty 'MS_Description','设备编号', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'ST_DEVICE_CODE'

execute sp_addextendedproperty 'MS_Description','设备IP', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'ST_DEVICE_IP'

execute sp_addextendedproperty 'MS_Description','设备MAC', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'ST_DEVICE_MAC'

execute sp_addextendedproperty 'MS_Description','设备详细地址', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'ST_DEVICE_ADDRESS'

execute sp_addextendedproperty 'MS_Description','类型ID', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'ST_TYPE_ID'

execute sp_addextendedproperty 'MS_Description','是否是主机', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'NM_IS_HOST'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','单位为秒', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'NM_INTERVAL'

execute sp_addextendedproperty 'MS_Description','是否恢复，0：未恢复（异常），1：已恢复（正常）', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'NM_RECOVER'

execute sp_addextendedproperty 'MS_Description','故障几次算异常', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'NM_DOWN_TRY'

execute sp_addextendedproperty 'MS_Description','是否通知', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'NM_NOTIFICATION'

execute sp_addextendedproperty 'MS_Description','经度', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'NM_LNG'

execute sp_addextendedproperty 'MS_Description','纬度', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'NM_LAT'

execute sp_addextendedproperty 'MS_Description','是否在线，0：不在线；1：在线；', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'NM_ONLINE'

execute sp_addextendedproperty 'MS_Description','消息通道', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'ST_CHANNEL'

execute sp_addextendedproperty 'MS_Description','终端配置，关联附件表', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'ST_CONFIG_ID'

execute sp_addextendedproperty 'MS_Description','设备类型，0：中心；1：延伸', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'NM_SDTYPE'

execute sp_addextendedproperty 'MS_Description','用户ID', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','区域ID', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'ST_AREA_ID'

execute sp_addextendedproperty 'MS_Description','地址ID', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'ST_ADDRESS_ID'

execute sp_addextendedproperty 'MS_Description','组织机构ID', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'ST_ORGAN_ID'

execute sp_addextendedproperty 'MS_Description','证书唯一标识', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'ST_CERT_KEY'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','备注', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_INFO', 'column', 'ST_DESC'

/*==============================================================*/
/* Table Name: 设备（外设）分类                                 */
/* Table Code: INFOPUB_DEVICE_TYPE                              */
/*==============================================================*/
create table [INFOPUB_DEVICE_TYPE] (
   [ST_TYPE_ID]        nvarchar(50) not null,   -- 类型ID
   [ST_TYPE_NAME]      nvarchar(50),   -- 分类名称
   [ST_TYPE_CODE]      nvarchar(50),   -- 分类代码
   [ST_ICON]           nvarchar(50),   -- 设备图标
   [ST_CLASS]          nvarchar(50),   -- 设备样式
   [ST_COMPANY_ID]     nvarchar(50),   -- 厂商ID
   [NM_DTYPE]          numeric(1),   -- 设备类型
   [ST_PARENT_TYPE_ID] nvarchar(50),   -- 父类型
   [NM_ORDER]          numeric(8),   -- 排序
   [DT_CREATE]         datetime,   -- 创建时间
   [DT_UPDATE]         datetime,   -- 更新时间
   [ST_DESC]           nvarchar(100),   -- 备注
   primary key ([ST_TYPE_ID])
);

execute sp_addextendedproperty 'MS_Description','类型ID', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_TYPE', 'column', 'ST_TYPE_ID'

execute sp_addextendedproperty 'MS_Description','分类名称', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_TYPE', 'column', 'ST_TYPE_NAME'

execute sp_addextendedproperty 'MS_Description','分类代码', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_TYPE', 'column', 'ST_TYPE_CODE'

execute sp_addextendedproperty 'MS_Description','设备图标', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_TYPE', 'column', 'ST_ICON'

execute sp_addextendedproperty 'MS_Description','设备样式', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_TYPE', 'column', 'ST_CLASS'

execute sp_addextendedproperty 'MS_Description','厂商ID', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_TYPE', 'column', 'ST_COMPANY_ID'

execute sp_addextendedproperty 'MS_Description','设备类型，0：政务终端；1：社会化终端', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_TYPE', 'column', 'NM_DTYPE'

execute sp_addextendedproperty 'MS_Description','父类型，子类型均为外设', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_TYPE', 'column', 'ST_PARENT_TYPE_ID'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_TYPE', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_TYPE', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_TYPE', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','备注', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_TYPE', 'column', 'ST_DESC'

/*==============================================================*/
/* Table Name: 区域                                             */
/* Table Code: INFOPUB_AREA                                     */
/*==============================================================*/
create table [INFOPUB_AREA] (
   [ST_AREA_ID]        nvarchar(50) not null,   -- 区域ID
   [ST_AREA_NAME]      nvarchar(50),   -- 区域名称
   [ST_AREA_CODE]      nvarchar(50),   -- 区域代码
   [ST_USER_ID]        nvarchar(50),   -- 管理员
   [ST_USER_NAME]      nvarchar(50),   -- 管理员姓名
   [ST_PHONE]          nvarchar(50),   -- 联系方式
   [NM_ORDER]          numeric(8),   -- 排序
   [DT_CREATE]         datetime,   -- 创建时间
   [DT_UPDATE]         datetime,   -- 更新时间
   [ST_PARENT_AREA_ID] nvarchar(50),   -- 父ID
   [ST_DESC]           nvarchar(100),   -- 备注
   primary key ([ST_AREA_ID])
);

execute sp_addextendedproperty 'MS_Description','区域ID', 'user', @CurrentUser, 'table', 'INFOPUB_AREA', 'column', 'ST_AREA_ID'

execute sp_addextendedproperty 'MS_Description','区域名称', 'user', @CurrentUser, 'table', 'INFOPUB_AREA', 'column', 'ST_AREA_NAME'

execute sp_addextendedproperty 'MS_Description','区域代码', 'user', @CurrentUser, 'table', 'INFOPUB_AREA', 'column', 'ST_AREA_CODE'

execute sp_addextendedproperty 'MS_Description','管理员', 'user', @CurrentUser, 'table', 'INFOPUB_AREA', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','管理员姓名', 'user', @CurrentUser, 'table', 'INFOPUB_AREA', 'column', 'ST_USER_NAME'

execute sp_addextendedproperty 'MS_Description','联系方式', 'user', @CurrentUser, 'table', 'INFOPUB_AREA', 'column', 'ST_PHONE'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'INFOPUB_AREA', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'INFOPUB_AREA', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'INFOPUB_AREA', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','父ID', 'user', @CurrentUser, 'table', 'INFOPUB_AREA', 'column', 'ST_PARENT_AREA_ID'

execute sp_addextendedproperty 'MS_Description','备注', 'user', @CurrentUser, 'table', 'INFOPUB_AREA', 'column', 'ST_DESC'

/*==============================================================*/
/* Table Name: 附件表                                           */
/* Table Code: INFOPUB_ATTACHMENT                               */
/*==============================================================*/
create table [INFOPUB_ATTACHMENT] (
   [ST_ATTACH_ID]     nvarchar(50) not null,   -- 主键
   [ST_LINK_TABLE]    nvarchar(50),   -- 关联表名称
   [ST_LINK_ID]       nvarchar(50),   -- 关联主键值
   [ST_ATTACH_TYPE]   nvarchar(50),   -- 附件类型
   [ST_FILENAME]      nvarchar(100),   -- 文件名
   [ST_FILE_SIZE]     nvarchar(50),   -- 文件大小
   [BL_CONTENT]       image,   -- 文件内容
   [CL_CONTENT]       text,   -- 文本内容
   [BL_SMALL_CONTENT] image,   -- 图片缩略图
   [ST_FILE_TYPE]     nvarchar(10),   -- 文件类型
   [DT_CREATE]        datetime,   -- 创建时间
   [DT_UPDATE]        datetime,   -- 修改时间
   primary key ([ST_ATTACH_ID])
);

execute sp_addextendedproperty 'MS_Description','主键', 'user', @CurrentUser, 'table', 'INFOPUB_ATTACHMENT', 'column', 'ST_ATTACH_ID'

execute sp_addextendedproperty 'MS_Description','关联表名称', 'user', @CurrentUser, 'table', 'INFOPUB_ATTACHMENT', 'column', 'ST_LINK_TABLE'

execute sp_addextendedproperty 'MS_Description','关联主键值', 'user', @CurrentUser, 'table', 'INFOPUB_ATTACHMENT', 'column', 'ST_LINK_ID'

execute sp_addextendedproperty 'MS_Description','附件类型', 'user', @CurrentUser, 'table', 'INFOPUB_ATTACHMENT', 'column', 'ST_ATTACH_TYPE'

execute sp_addextendedproperty 'MS_Description','文件名', 'user', @CurrentUser, 'table', 'INFOPUB_ATTACHMENT', 'column', 'ST_FILENAME'

execute sp_addextendedproperty 'MS_Description','文件大小', 'user', @CurrentUser, 'table', 'INFOPUB_ATTACHMENT', 'column', 'ST_FILE_SIZE'

execute sp_addextendedproperty 'MS_Description','文件内容', 'user', @CurrentUser, 'table', 'INFOPUB_ATTACHMENT', 'column', 'BL_CONTENT'

execute sp_addextendedproperty 'MS_Description','文本内容', 'user', @CurrentUser, 'table', 'INFOPUB_ATTACHMENT', 'column', 'CL_CONTENT'

execute sp_addextendedproperty 'MS_Description','图片缩略图', 'user', @CurrentUser, 'table', 'INFOPUB_ATTACHMENT', 'column', 'BL_SMALL_CONTENT'

execute sp_addextendedproperty 'MS_Description','文件类型', 'user', @CurrentUser, 'table', 'INFOPUB_ATTACHMENT', 'column', 'ST_FILE_TYPE'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'INFOPUB_ATTACHMENT', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','修改时间', 'user', @CurrentUser, 'table', 'INFOPUB_ATTACHMENT', 'column', 'DT_UPDATE'

/*==============================================================*/
/* Table Name: 外设状态结果信息                                 */
/* Table Code: INFOPUB_ODEVICE_RESULT                           */
/*==============================================================*/
create table [INFOPUB_ODEVICE_RESULT] (
   [ST_OUT_DEVICE_RESULT_ID] nvarchar(50) not null,   -- 外设状态结果ID
   [ST_DEVICE_ID]            nvarchar(50),   -- 设备ID
   [ST_OUT_DEVICE_CODE]      nvarchar(50),   -- 外设标识
   [NM_EXCEPTION]            numeric(1),   -- 是否异常
   [ST_CAUSE]                nvarchar(200),   -- 异常原因
   [NM_NOTICE]               numeric(1),   -- 是否已经通知
   [NM_TOTAL]                numeric(8),   -- 总量
   [NM_REMAIN]               numeric(8),   -- 剩余量
   [DT_UPDATE]               datetime,   -- 更新日期
   [ST_EXT1]                 nvarchar(50),   -- 扩展字段1
   [ST_EXT2]                 nvarchar(50),   -- 扩展字段2
   primary key ([ST_OUT_DEVICE_RESULT_ID])
);

execute sp_addextendedproperty 'MS_Description','外设状态结果ID', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_RESULT', 'column', 'ST_OUT_DEVICE_RESULT_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_RESULT', 'column', 'ST_DEVICE_ID'

execute sp_addextendedproperty 'MS_Description','外设标识', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_RESULT', 'column', 'ST_OUT_DEVICE_CODE'

execute sp_addextendedproperty 'MS_Description','是否异常，0：正常；1：异常', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_RESULT', 'column', 'NM_EXCEPTION'

execute sp_addextendedproperty 'MS_Description','异常原因', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_RESULT', 'column', 'ST_CAUSE'

execute sp_addextendedproperty 'MS_Description','是否已经通知，0：未通知；1：已通知', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_RESULT', 'column', 'NM_NOTICE'

execute sp_addextendedproperty 'MS_Description','总量', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_RESULT', 'column', 'NM_TOTAL'

execute sp_addextendedproperty 'MS_Description','剩余量', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_RESULT', 'column', 'NM_REMAIN'

execute sp_addextendedproperty 'MS_Description','更新日期', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_RESULT', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_RESULT', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_RESULT', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 设备状态结果信息                                 */
/* Table Code: INFOPUB_DEVICE_RESULT                            */
/*==============================================================*/
create table [INFOPUB_DEVICE_RESULT] (
   [ST_DEVICE_RESULT_ID] nvarchar(50) not null,   -- 设备状态结果ID
   [ST_DEVICE_ID]        nvarchar(50),   -- 设备ID
   [NM_MEM_USED]         numeric(5,2),   -- 内存使用率
   [NM_CPU_USED]         numeric(5,2),   -- CPU使用率
   [CL_HD_USED]          text,   -- 磁盘使用情况
   [CL_NET_USED]         text,   -- 网络使用情况
   [CL_SERVICE_USED]     text,   -- 服务使用情况
   [DT_CREATE]           datetime,   -- 创建日期
   [ST_EXT1]             nvarchar(50),   -- 扩展字段1
   [ST_EXT2]             nvarchar(50),   -- 扩展字段2
   primary key ([ST_DEVICE_RESULT_ID])
);

execute sp_addextendedproperty 'MS_Description','设备状态结果ID', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT', 'column', 'ST_DEVICE_RESULT_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT', 'column', 'ST_DEVICE_ID'

execute sp_addextendedproperty 'MS_Description','内存使用率', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT', 'column', 'NM_MEM_USED'

execute sp_addextendedproperty 'MS_Description','CPU使用率', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT', 'column', 'NM_CPU_USED'

execute sp_addextendedproperty 'MS_Description','磁盘使用情况', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT', 'column', 'CL_HD_USED'

execute sp_addextendedproperty 'MS_Description','网络使用情况', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT', 'column', 'CL_NET_USED'

execute sp_addextendedproperty 'MS_Description','服务使用情况', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT', 'column', 'CL_SERVICE_USED'

execute sp_addextendedproperty 'MS_Description','创建日期', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 设备日志记录                                     */
/* Table Code: INFOPUB_DEVICE_LOG                               */
/*==============================================================*/
create table [INFOPUB_DEVICE_LOG] (
   [ST_DEVICE_LOG_ID] nvarchar(50) not null,   -- 设备日志ID
   [ST_DEVICE_ID]     nvarchar(50),   -- 设备ID
   [ST_THREAD]        nvarchar(50),   -- 线程号
   [ST_LEVEL]         nvarchar(50),   -- 日志级别
   [ST_LOGGER]        nvarchar(100),   -- 日志记录类名称
   [ST_OPERATOR]      nvarchar(50),   -- 操作者
   [ST_OPERAND]       nvarchar(50),   -- 操作对象
   [ST_ACTION]        nvarchar(50),   -- 动作类型
   [ST_LOCATION]      nvarchar(100),   -- 记录位置
   [ST_LINE]          nvarchar(50),   -- 行号
   [ST_METHOD]        nvarchar(50),   -- 方法
   [ST_MSG]           nvarchar(1000),   -- 日志消息
   [ST_EXCEPTION]     nvarchar(1000),   -- 异常信息
   [DT_CREATE]        datetime,   -- 创建日期
   [ST_EXT1]          nvarchar(50),   -- 扩展字段1
   [ST_EXT2]          nvarchar(50),   -- 扩展字段2
   primary key ([ST_DEVICE_LOG_ID])
);

execute sp_addextendedproperty 'MS_Description','设备日志ID', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_LOG', 'column', 'ST_DEVICE_LOG_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_LOG', 'column', 'ST_DEVICE_ID'

execute sp_addextendedproperty 'MS_Description','线程号', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_LOG', 'column', 'ST_THREAD'

execute sp_addextendedproperty 'MS_Description','日志级别', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_LOG', 'column', 'ST_LEVEL'

execute sp_addextendedproperty 'MS_Description','日志记录类名称', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_LOG', 'column', 'ST_LOGGER'

execute sp_addextendedproperty 'MS_Description','操作者', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_LOG', 'column', 'ST_OPERATOR'

execute sp_addextendedproperty 'MS_Description','操作对象', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_LOG', 'column', 'ST_OPERAND'

execute sp_addextendedproperty 'MS_Description','动作类型', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_LOG', 'column', 'ST_ACTION'

execute sp_addextendedproperty 'MS_Description','记录位置', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_LOG', 'column', 'ST_LOCATION'

execute sp_addextendedproperty 'MS_Description','行号', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_LOG', 'column', 'ST_LINE'

execute sp_addextendedproperty 'MS_Description','方法', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_LOG', 'column', 'ST_METHOD'

execute sp_addextendedproperty 'MS_Description','日志消息', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_LOG', 'column', 'ST_MSG'

execute sp_addextendedproperty 'MS_Description','异常信息', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_LOG', 'column', 'ST_EXCEPTION'

execute sp_addextendedproperty 'MS_Description','创建日期', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_LOG', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_LOG', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_LOG', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 信息资源                                         */
/* Table Code: INFOPUB_IRESOURCE                                */
/*==============================================================*/
create table [INFOPUB_IRESOURCE] (
   [ST_IRESOURCE_ID]   nvarchar(50) not null,   -- 资源ID
   [ST_IRESOURCE_NAME] nvarchar(100),   -- 资源名称
   [ST_IRESOURCE_TYPE] nvarchar(50),   -- 资源类型
   [ST_USER_ID]        nvarchar(50),   -- 用户ID
   [DT_CREATE]         datetime,   -- 创建时间
   [DT_UPDATE]         datetime,   -- 更新时间
   [ST_DESC]           nvarchar(100),   -- 备注
   primary key ([ST_IRESOURCE_ID])
);

execute sp_addextendedproperty 'MS_Description','资源ID', 'user', @CurrentUser, 'table', 'INFOPUB_IRESOURCE', 'column', 'ST_IRESOURCE_ID'

execute sp_addextendedproperty 'MS_Description','资源名称', 'user', @CurrentUser, 'table', 'INFOPUB_IRESOURCE', 'column', 'ST_IRESOURCE_NAME'

execute sp_addextendedproperty 'MS_Description','资源类型', 'user', @CurrentUser, 'table', 'INFOPUB_IRESOURCE', 'column', 'ST_IRESOURCE_TYPE'

execute sp_addextendedproperty 'MS_Description','用户ID', 'user', @CurrentUser, 'table', 'INFOPUB_IRESOURCE', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'INFOPUB_IRESOURCE', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'INFOPUB_IRESOURCE', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','备注', 'user', @CurrentUser, 'table', 'INFOPUB_IRESOURCE', 'column', 'ST_DESC'

/*==============================================================*/
/* Table Name: 设备分组                                         */
/* Table Code: INFOPUB_GROUP                                    */
/*==============================================================*/
create table [INFOPUB_GROUP] (
   [ST_GROUP_ID]   nvarchar(50) not null,   -- 分组ID
   [ST_GROUP_NAME] nvarchar(50),   -- 设备组名称
   [ST_USER_ID]    nvarchar(50),   -- 用户ID
   [DT_CREATE]     datetime,   -- 创建时间
   [DT_UPDATE]     datetime,   -- 更新时间
   [ST_DESC]       nvarchar(100),   -- 备注
   [ST_AREA_ID]    nvarchar(50),   -- 区域ID
   primary key ([ST_GROUP_ID])
);

execute sp_addextendedproperty 'MS_Description','分组ID', 'user', @CurrentUser, 'table', 'INFOPUB_GROUP', 'column', 'ST_GROUP_ID'

execute sp_addextendedproperty 'MS_Description','设备组名称', 'user', @CurrentUser, 'table', 'INFOPUB_GROUP', 'column', 'ST_GROUP_NAME'

execute sp_addextendedproperty 'MS_Description','用户ID', 'user', @CurrentUser, 'table', 'INFOPUB_GROUP', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'INFOPUB_GROUP', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'INFOPUB_GROUP', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','备注', 'user', @CurrentUser, 'table', 'INFOPUB_GROUP', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','区域ID', 'user', @CurrentUser, 'table', 'INFOPUB_GROUP', 'column', 'ST_AREA_ID'

/*==============================================================*/
/* Table Name: 设备组关联                                       */
/* Table Code: INFOPUB_GROUP_DEVICE                             */
/*==============================================================*/
create table [INFOPUB_GROUP_DEVICE] (
   [ST_GROUP_ID]  nvarchar(50) not null,   -- 分组ID
   [ST_DEVICE_ID] nvarchar(50) not null,   -- 设备ID
   [NM_ORDER]     numeric(8),   -- 排序
   primary key ([ST_GROUP_ID], [ST_DEVICE_ID])
);

execute sp_addextendedproperty 'MS_Description','分组ID', 'user', @CurrentUser, 'table', 'INFOPUB_GROUP_DEVICE', 'column', 'ST_GROUP_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'INFOPUB_GROUP_DEVICE', 'column', 'ST_DEVICE_ID'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'INFOPUB_GROUP_DEVICE', 'column', 'NM_ORDER'

/*==============================================================*/
/* Table Name: 设备发布                                         */
/* Table Code: INFOPUB_PUBLISH                                  */
/*==============================================================*/
create table [INFOPUB_PUBLISH] (
   [ST_PUBLISH_ID]   nvarchar(50) not null,   -- 发布ID
   [ST_DEVICE_ID]    nvarchar(50),   -- 设备ID
   [ST_PSOURCE_ID]   nvarchar(50),   -- 发布源ID
   [ST_PUBLISH_NAME] nvarchar(50),   -- 发布名称
   [NM_PRIORITY]     numeric(4),   -- 优先级
   [ST_PTYPE]        nvarchar(50),   -- 类型
   [DT_PSTART]       datetime,   -- 发布开始时间
   [DT_PEND]         datetime,   -- 发布结束时间
   [ST_PERIOD]       nvarchar(50),   -- 发布周期
   [DT_PUBLISH]      datetime,   -- 发布日期
   [DT_CREATE]       datetime,   -- 创建时间
   [DT_UPDATE]       datetime,   -- 更新时间
   [ST_DESC]         nvarchar(100),   -- 备注
   primary key ([ST_PUBLISH_ID])
);

execute sp_addextendedproperty 'MS_Description','发布ID', 'user', @CurrentUser, 'table', 'INFOPUB_PUBLISH', 'column', 'ST_PUBLISH_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'INFOPUB_PUBLISH', 'column', 'ST_DEVICE_ID'

execute sp_addextendedproperty 'MS_Description','发布源ID', 'user', @CurrentUser, 'table', 'INFOPUB_PUBLISH', 'column', 'ST_PSOURCE_ID'

execute sp_addextendedproperty 'MS_Description','发布名称', 'user', @CurrentUser, 'table', 'INFOPUB_PUBLISH', 'column', 'ST_PUBLISH_NAME'

execute sp_addextendedproperty 'MS_Description','优先级', 'user', @CurrentUser, 'table', 'INFOPUB_PUBLISH', 'column', 'NM_PRIORITY'

execute sp_addextendedproperty 'MS_Description','类型，WEEK：按周；DAY：按天', 'user', @CurrentUser, 'table', 'INFOPUB_PUBLISH', 'column', 'ST_PTYPE'

execute sp_addextendedproperty 'MS_Description','发布开始时间', 'user', @CurrentUser, 'table', 'INFOPUB_PUBLISH', 'column', 'DT_PSTART'

execute sp_addextendedproperty 'MS_Description','发布结束时间', 'user', @CurrentUser, 'table', 'INFOPUB_PUBLISH', 'column', 'DT_PEND'

execute sp_addextendedproperty 'MS_Description','发布周期，固定7个数字，0代表不发布，1代表发布', 'user', @CurrentUser, 'table', 'INFOPUB_PUBLISH', 'column', 'ST_PERIOD'

execute sp_addextendedproperty 'MS_Description','发布日期', 'user', @CurrentUser, 'table', 'INFOPUB_PUBLISH', 'column', 'DT_PUBLISH'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'INFOPUB_PUBLISH', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'INFOPUB_PUBLISH', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','备注', 'user', @CurrentUser, 'table', 'INFOPUB_PUBLISH', 'column', 'ST_DESC'

/*==============================================================*/
/* Table Name: 发布源                                           */
/* Table Code: INFOPUB_PSOURCE                                  */
/*==============================================================*/
create table [INFOPUB_PSOURCE] (
   [ST_PSOURCE_ID]   nvarchar(50) not null,   -- 发布源ID
   [ST_PSOURCE_NAME] nvarchar(50),   -- 发布源名称
   [NM_OFFLINE]      numeric(1),   -- 是否离线
   [ST_USER_ID]      nvarchar(50),   -- 用户ID
   [DT_CREATE]       datetime,   -- 创建时间
   [DT_UPDATE]       datetime,   -- 更新时间
   [ST_DESC]         nvarchar(100),   -- 备注
   primary key ([ST_PSOURCE_ID])
);

execute sp_addextendedproperty 'MS_Description','发布源ID', 'user', @CurrentUser, 'table', 'INFOPUB_PSOURCE', 'column', 'ST_PSOURCE_ID'

execute sp_addextendedproperty 'MS_Description','发布源名称', 'user', @CurrentUser, 'table', 'INFOPUB_PSOURCE', 'column', 'ST_PSOURCE_NAME'

execute sp_addextendedproperty 'MS_Description','是否离线，0：否；1：是', 'user', @CurrentUser, 'table', 'INFOPUB_PSOURCE', 'column', 'NM_OFFLINE'

execute sp_addextendedproperty 'MS_Description','用户ID', 'user', @CurrentUser, 'table', 'INFOPUB_PSOURCE', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'INFOPUB_PSOURCE', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'INFOPUB_PSOURCE', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','备注', 'user', @CurrentUser, 'table', 'INFOPUB_PSOURCE', 'column', 'ST_DESC'

/*==============================================================*/
/* Table Name: 设备开关机                                       */
/* Table Code: INFOPUB_ONOFF                                    */
/*==============================================================*/
create table [INFOPUB_ONOFF] (
   [ST_ONOFF_ID]  nvarchar(50) not null,   -- 设备开关机ID
   [ST_DEVICE_ID] nvarchar(50),   -- 设备ID
   [ST_PTYPE]     nvarchar(50),   -- 类型
   [ST_ON_TIME]   nvarchar(50),   -- 开机时间
   [ST_OFF_TIME]  nvarchar(50),   -- 关机时间
   [ST_PERIOD]    nvarchar(50),   -- 发布周期
   [DT_ONOFF]     datetime,   -- 定时日期
   [DT_CREATE]    datetime,   -- 创建时间
   [DT_UPDATE]    datetime,   -- 更新时间
   [ST_DESC]      nvarchar(100),   -- 备注
   primary key ([ST_ONOFF_ID])
);

execute sp_addextendedproperty 'MS_Description','设备开关机ID', 'user', @CurrentUser, 'table', 'INFOPUB_ONOFF', 'column', 'ST_ONOFF_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'INFOPUB_ONOFF', 'column', 'ST_DEVICE_ID'

execute sp_addextendedproperty 'MS_Description','类型，WEEK：按周；DAY：按天', 'user', @CurrentUser, 'table', 'INFOPUB_ONOFF', 'column', 'ST_PTYPE'

execute sp_addextendedproperty 'MS_Description','开机时间', 'user', @CurrentUser, 'table', 'INFOPUB_ONOFF', 'column', 'ST_ON_TIME'

execute sp_addextendedproperty 'MS_Description','关机时间', 'user', @CurrentUser, 'table', 'INFOPUB_ONOFF', 'column', 'ST_OFF_TIME'

execute sp_addextendedproperty 'MS_Description','发布周期，固定7个数字，0代表不发布，1代表发布', 'user', @CurrentUser, 'table', 'INFOPUB_ONOFF', 'column', 'ST_PERIOD'

execute sp_addextendedproperty 'MS_Description','定时日期', 'user', @CurrentUser, 'table', 'INFOPUB_ONOFF', 'column', 'DT_ONOFF'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'INFOPUB_ONOFF', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'INFOPUB_ONOFF', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','备注', 'user', @CurrentUser, 'table', 'INFOPUB_ONOFF', 'column', 'ST_DESC'

/*==============================================================*/
/* Table Name: 外设状态                                         */
/* Table Code: INFOPUB_ODEVICE_STATUS                           */
/*==============================================================*/
create table [INFOPUB_ODEVICE_STATUS] (
   [ST_OUT_DEVICE_RESULT_ID] nvarchar(50) not null,   -- 外设状态结果ID
   [ST_DEVICE_ID]            nvarchar(50),   -- 设备ID
   [ST_OUT_DEVICE_CODE]      nvarchar(50),   -- 外设标识
   [NM_EXCEPTION]            numeric(1),   -- 是否异常
   [ST_CAUSE]                nvarchar(200),   -- 异常原因
   [NM_NOTICE]               numeric(1),   -- 是否已经通知
   [NM_TOTAL]                numeric(8),   -- 总量
   [NM_REMAIN]               numeric(8),   -- 剩余量
   [NM_HIS_TOTAL]            numeric(12),   -- 历史总量
   [NM_HIS_STOTAL]           numeric(12),   -- 成功总次数
   [NM_HIS_FTOTAL]           numeric(12),   -- 失败总次数
   [DT_UPDATE]               datetime,   -- 更新日期
   [ST_EXT1]                 nvarchar(50),   -- 扩展字段1
   [ST_EXT2]                 nvarchar(50),   -- 扩展字段2
   primary key ([ST_OUT_DEVICE_RESULT_ID])
);

execute sp_addextendedproperty 'MS_Description','外设状态结果ID', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_STATUS', 'column', 'ST_OUT_DEVICE_RESULT_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_STATUS', 'column', 'ST_DEVICE_ID'

execute sp_addextendedproperty 'MS_Description','外设标识', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_STATUS', 'column', 'ST_OUT_DEVICE_CODE'

execute sp_addextendedproperty 'MS_Description','是否异常，0：正常；1：异常', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_STATUS', 'column', 'NM_EXCEPTION'

execute sp_addextendedproperty 'MS_Description','异常原因', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_STATUS', 'column', 'ST_CAUSE'

execute sp_addextendedproperty 'MS_Description','是否已经通知，0：未通知；1：已通知', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_STATUS', 'column', 'NM_NOTICE'

execute sp_addextendedproperty 'MS_Description','总量', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_STATUS', 'column', 'NM_TOTAL'

execute sp_addextendedproperty 'MS_Description','剩余量', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_STATUS', 'column', 'NM_REMAIN'

execute sp_addextendedproperty 'MS_Description','历史总量', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_STATUS', 'column', 'NM_HIS_TOTAL'

execute sp_addextendedproperty 'MS_Description','成功总次数', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_STATUS', 'column', 'NM_HIS_STOTAL'

execute sp_addextendedproperty 'MS_Description','失败总次数', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_STATUS', 'column', 'NM_HIS_FTOTAL'

execute sp_addextendedproperty 'MS_Description','更新日期', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_STATUS', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_STATUS', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'INFOPUB_ODEVICE_STATUS', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 设备状态结果历史                                 */
/* Table Code: INFOPUB_DEVICE_RESULT_HIS                        */
/*==============================================================*/
create table [INFOPUB_DEVICE_RESULT_HIS] (
   [ST_DEVICE_RESULT_ID] nvarchar(50) not null,   -- 设备状态结果ID
   [ST_DEVICE_ID]        nvarchar(50),   -- 设备ID
   [NM_MEM_USED]         numeric(5,2),   -- 内存使用率
   [NM_CPU_USED]         numeric(5,2),   -- CPU使用率
   [CL_HD_USED]          text,   -- 磁盘使用情况
   [CL_NET_USED]         text,   -- 网络使用情况
   [CL_SERVICE_USED]     text,   -- 服务使用情况
   [DT_CREATE]           datetime,   -- 创建日期
   [ST_EXT1]             nvarchar(50),   -- 扩展字段1
   [ST_EXT2]             nvarchar(50),   -- 扩展字段2
   primary key ([ST_DEVICE_RESULT_ID])
);

execute sp_addextendedproperty 'MS_Description','设备状态结果ID', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT_HIS', 'column', 'ST_DEVICE_RESULT_ID'

execute sp_addextendedproperty 'MS_Description','设备ID', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT_HIS', 'column', 'ST_DEVICE_ID'

execute sp_addextendedproperty 'MS_Description','内存使用率', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT_HIS', 'column', 'NM_MEM_USED'

execute sp_addextendedproperty 'MS_Description','CPU使用率', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT_HIS', 'column', 'NM_CPU_USED'

execute sp_addextendedproperty 'MS_Description','磁盘使用情况', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT_HIS', 'column', 'CL_HD_USED'

execute sp_addextendedproperty 'MS_Description','网络使用情况', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT_HIS', 'column', 'CL_NET_USED'

execute sp_addextendedproperty 'MS_Description','服务使用情况', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT_HIS', 'column', 'CL_SERVICE_USED'

execute sp_addextendedproperty 'MS_Description','创建日期', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT_HIS', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT_HIS', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'INFOPUB_DEVICE_RESULT_HIS', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 图片集类型                                       */
/* Table Code: INFOPUB_PIC_TYPE                                 */
/*==============================================================*/
create table [INFOPUB_PIC_TYPE] (
   [ST_PIC_TYPE_ID] nvarchar(50) not null,   -- 图片集类型ID
   [ST_TYPE_CODE]   nvarchar(50),   -- 类型代码
   [ST_TYPE_NAME]   nvarchar(50),   -- 类型名称
   [DT_CREATE]      datetime,   -- 创建时间
   [DT_UPDATE]      datetime,   -- 更新时间
   [ST_DESC]        nvarchar(50),   -- 备注
   primary key ([ST_PIC_TYPE_ID])
);

execute sp_addextendedproperty 'MS_Description','图片集类型ID', 'user', @CurrentUser, 'table', 'INFOPUB_PIC_TYPE', 'column', 'ST_PIC_TYPE_ID'

execute sp_addextendedproperty 'MS_Description','类型代码', 'user', @CurrentUser, 'table', 'INFOPUB_PIC_TYPE', 'column', 'ST_TYPE_CODE'

execute sp_addextendedproperty 'MS_Description','类型名称', 'user', @CurrentUser, 'table', 'INFOPUB_PIC_TYPE', 'column', 'ST_TYPE_NAME'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'INFOPUB_PIC_TYPE', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'INFOPUB_PIC_TYPE', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','备注', 'user', @CurrentUser, 'table', 'INFOPUB_PIC_TYPE', 'column', 'ST_DESC'

/*==============================================================*/
/* Table Name: 图片集                                           */
/* Table Code: INFOPUB_PIC                                      */
/*==============================================================*/
create table [INFOPUB_PIC] (
   [ST_PIC_ID]      nvarchar(50) not null,   -- 图片集ID
   [ST_PIC_TYPE_ID] nvarchar(50),   -- 图片集类型ID
   [ST_ATTACH_ID]   nvarchar(50),   -- 附件ID
   [ST_PIC_NAME]    nvarchar(50),   -- 图片名称
   [ST_PIC_URL]     nvarchar(200),   -- 图片链接
   [ST_SKIP_URL]    nvarchar(200),   -- 跳转链接
   [ST_SKIP_MODULE] nvarchar(50),   -- 跳转模块
   [NM_ORDER]       numeric(4),   -- 图片排序
   [ST_DESC]        nvarchar(200),   -- 图片描述
   [DT_CREATE]      datetime,   -- 创建时间
   [NM_STATUS]      numeric(1),   -- 状态
   [ST_CREATOR]     nvarchar(50),   -- 创建人
   [DT_UPDATE]      datetime,   -- 更新时间
   [ST_AUDITOR]     nvarchar(50),   -- 审核人
   [DT_AUDIT]       datetime,   -- 审核时间
   primary key ([ST_PIC_ID])
);

execute sp_addextendedproperty 'MS_Description','图片集ID', 'user', @CurrentUser, 'table', 'INFOPUB_PIC', 'column', 'ST_PIC_ID'

execute sp_addextendedproperty 'MS_Description','图片集类型ID', 'user', @CurrentUser, 'table', 'INFOPUB_PIC', 'column', 'ST_PIC_TYPE_ID'

execute sp_addextendedproperty 'MS_Description','附件ID', 'user', @CurrentUser, 'table', 'INFOPUB_PIC', 'column', 'ST_ATTACH_ID'

execute sp_addextendedproperty 'MS_Description','图片名称', 'user', @CurrentUser, 'table', 'INFOPUB_PIC', 'column', 'ST_PIC_NAME'

execute sp_addextendedproperty 'MS_Description','图片链接，可能该图片链接其他系统', 'user', @CurrentUser, 'table', 'INFOPUB_PIC', 'column', 'ST_PIC_URL'

execute sp_addextendedproperty 'MS_Description','跳转链接', 'user', @CurrentUser, 'table', 'INFOPUB_PIC', 'column', 'ST_SKIP_URL'

execute sp_addextendedproperty 'MS_Description','跳转模块', 'user', @CurrentUser, 'table', 'INFOPUB_PIC', 'column', 'ST_SKIP_MODULE'

execute sp_addextendedproperty 'MS_Description','图片排序', 'user', @CurrentUser, 'table', 'INFOPUB_PIC', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','图片描述', 'user', @CurrentUser, 'table', 'INFOPUB_PIC', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'INFOPUB_PIC', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','状态，0：未审核；1：审核通过；2：已发布；3：已禁用；9：已删除', 'user', @CurrentUser, 'table', 'INFOPUB_PIC', 'column', 'NM_STATUS'

execute sp_addextendedproperty 'MS_Description','创建人', 'user', @CurrentUser, 'table', 'INFOPUB_PIC', 'column', 'ST_CREATOR'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'INFOPUB_PIC', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','审核人', 'user', @CurrentUser, 'table', 'INFOPUB_PIC', 'column', 'ST_AUDITOR'

execute sp_addextendedproperty 'MS_Description','审核时间', 'user', @CurrentUser, 'table', 'INFOPUB_PIC', 'column', 'DT_AUDIT'

/*==============================================================*/
/* Table Name: 功能模块                                         */
/* Table Code: INFOPUB_FUNC                                     */
/*==============================================================*/
create table [INFOPUB_FUNC] (
   [ST_FUNC_ID]      nvarchar(50) not null,   -- 功能模块ID
   [ST_FUNC_TYPE_ID] nvarchar(50),   -- 功能模块类型ID
   [ST_FUNC_CODE]    nvarchar(50),   -- 模块代码
   [ST_FUNC_NAME]    nvarchar(50),   -- 模块名称
   [ST_TITLE_ONE]    nvarchar(50),   -- 一级标题
   [ST_TITLE_TWO]    nvarchar(50),   -- 二级标题
   [ST_SKIP_URL]     nvarchar(200),   -- 跳转链接
   [ST_SKIP_MODULE]  nvarchar(50),   -- 跳转模块
   [ST_ICON]         nvarchar(50),   -- 小图标
   [NM_EURL]         numeric(1),   -- 是否是第三方url
   [ST_PARENT_ID]    nvarchar(50),   -- 父级ID
   [NM_ORDER]        numeric(4),   -- 排序
   [DT_CREATE]       datetime,   -- 创建时间
   [NM_STATUS]       numeric(1),   -- 状态
   [ST_CREATOR]      nvarchar(50),   -- 创建人
   [DT_UPDATE]       datetime,   -- 更新时间
   [ST_AUDITOR]      nvarchar(50),   -- 审核人
   [DT_AUDIT]        datetime,   -- 审核时间
   [ST_DESC]         nvarchar(200),   -- 功能简介
   [ST_PIC_PATH]     nvarchar(50),   -- 图片路径
   primary key ([ST_FUNC_ID])
);

execute sp_addextendedproperty 'MS_Description','功能模块ID', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'ST_FUNC_ID'

execute sp_addextendedproperty 'MS_Description','功能模块类型ID', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'ST_FUNC_TYPE_ID'

execute sp_addextendedproperty 'MS_Description','模块代码', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'ST_FUNC_CODE'

execute sp_addextendedproperty 'MS_Description','模块名称', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'ST_FUNC_NAME'

execute sp_addextendedproperty 'MS_Description','一级标题', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'ST_TITLE_ONE'

execute sp_addextendedproperty 'MS_Description','二级标题', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'ST_TITLE_TWO'

execute sp_addextendedproperty 'MS_Description','跳转链接', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'ST_SKIP_URL'

execute sp_addextendedproperty 'MS_Description','跳转模块', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'ST_SKIP_MODULE'

execute sp_addextendedproperty 'MS_Description','小图标，可以是url或者css样式等', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'ST_ICON'

execute sp_addextendedproperty 'MS_Description','是否是第三方url，0：否；1：是', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'NM_EURL'

execute sp_addextendedproperty 'MS_Description','父级ID', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'ST_PARENT_ID'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','状态，0：未审核；1：审核通过；2：已发布；3：已禁用；9：已删除', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'NM_STATUS'

execute sp_addextendedproperty 'MS_Description','创建人', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'ST_CREATOR'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','审核人', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'ST_AUDITOR'

execute sp_addextendedproperty 'MS_Description','审核时间', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'DT_AUDIT'

execute sp_addextendedproperty 'MS_Description','功能简介', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','图片路径', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC', 'column', 'ST_PIC_PATH'

/*==============================================================*/
/* Table Name: 功能模块类型                                     */
/* Table Code: INFOPUB_FUNC_TYPE                                */
/*==============================================================*/
create table [INFOPUB_FUNC_TYPE] (
   [ST_FUNC_TYPE_ID] nvarchar(50) not null,   -- 功能模块类型ID
   [ST_TYPE_CODE]    nvarchar(50),   -- 类型代码
   [ST_TYPE_NAME]    nvarchar(50),   -- 类型名称
   [DT_CREATE]       datetime,   -- 创建时间
   [DT_UPDATE]       datetime,   -- 更新时间
   [ST_DESC]         nvarchar(50),   -- 备注
   primary key ([ST_FUNC_TYPE_ID])
);

execute sp_addextendedproperty 'MS_Description','功能模块类型ID', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC_TYPE', 'column', 'ST_FUNC_TYPE_ID'

execute sp_addextendedproperty 'MS_Description','类型代码', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC_TYPE', 'column', 'ST_TYPE_CODE'

execute sp_addextendedproperty 'MS_Description','类型名称', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC_TYPE', 'column', 'ST_TYPE_NAME'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC_TYPE', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC_TYPE', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','备注', 'user', @CurrentUser, 'table', 'INFOPUB_FUNC_TYPE', 'column', 'ST_DESC'

/*==============================================================*/
/* Table Name: 应用                                             */
/* Table Code: INFOPUB_APP                                      */
/*==============================================================*/
create table [INFOPUB_APP] (
   [ST_APP_ID]   nvarchar(50) not null,   -- 应用ID
   [ST_APP_NAME] nvarchar(100),   -- 应用名称
   [ST_APP_CODE] nvarchar(50),   -- 应用代码
   [ST_APP_URL]  nvarchar(50),   -- 应用URL
   [DT_CREATE]   datetime,   -- 创建时间
   [NM_STATUS]   numeric(1),   -- 状态
   [ST_CREATOR]  nvarchar(50),   -- 创建人
   [DT_UPDATE]   datetime,   -- 更新时间
   [ST_AUDITOR]  nvarchar(50),   -- 审核人
   [DT_AUDIT]    datetime,   -- 审核时间
   [ST_DESC]     nvarchar(200),   -- 功能简介
   [ST_PIC_PATH] nvarchar(50),   -- 图片路径
   primary key ([ST_APP_ID])
);

execute sp_addextendedproperty 'MS_Description','应用ID', 'user', @CurrentUser, 'table', 'INFOPUB_APP', 'column', 'ST_APP_ID'

execute sp_addextendedproperty 'MS_Description','应用名称', 'user', @CurrentUser, 'table', 'INFOPUB_APP', 'column', 'ST_APP_NAME'

execute sp_addextendedproperty 'MS_Description','应用代码', 'user', @CurrentUser, 'table', 'INFOPUB_APP', 'column', 'ST_APP_CODE'

execute sp_addextendedproperty 'MS_Description','应用URL', 'user', @CurrentUser, 'table', 'INFOPUB_APP', 'column', 'ST_APP_URL'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'INFOPUB_APP', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','状态，0：未审核；1：审核通过；2：已发布；3：已禁用；9：已删除', 'user', @CurrentUser, 'table', 'INFOPUB_APP', 'column', 'NM_STATUS'

execute sp_addextendedproperty 'MS_Description','创建人', 'user', @CurrentUser, 'table', 'INFOPUB_APP', 'column', 'ST_CREATOR'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'INFOPUB_APP', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','审核人', 'user', @CurrentUser, 'table', 'INFOPUB_APP', 'column', 'ST_AUDITOR'

execute sp_addextendedproperty 'MS_Description','审核时间', 'user', @CurrentUser, 'table', 'INFOPUB_APP', 'column', 'DT_AUDIT'

execute sp_addextendedproperty 'MS_Description','功能简介', 'user', @CurrentUser, 'table', 'INFOPUB_APP', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','图片路径', 'user', @CurrentUser, 'table', 'INFOPUB_APP', 'column', 'ST_PIC_PATH'

/*==============================================================*/
/* Table Name: 应用关联图片                                     */
/* Table Code: INFOPUB_APP_PIC                                  */
/*==============================================================*/
create table [INFOPUB_APP_PIC] (
   [ST_APP_ID] nvarchar(50) not null,   -- 应用ID
   [ST_PIC_ID] nvarchar(50) not null,   -- 图片集ID
   [NM_ORDER]  numeric(4),   -- 排序
   primary key ([ST_APP_ID], [ST_PIC_ID])
);

execute sp_addextendedproperty 'MS_Description','应用ID', 'user', @CurrentUser, 'table', 'INFOPUB_APP_PIC', 'column', 'ST_APP_ID'

execute sp_addextendedproperty 'MS_Description','图片集ID', 'user', @CurrentUser, 'table', 'INFOPUB_APP_PIC', 'column', 'ST_PIC_ID'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'INFOPUB_APP_PIC', 'column', 'NM_ORDER'

/*==============================================================*/
/* Table Name: 应用关联模块                                     */
/* Table Code: INFOPUB_APP_FUNC                                 */
/*==============================================================*/
create table [INFOPUB_APP_FUNC] (
   [ST_APP_ID]  nvarchar(50) not null,   -- 应用ID
   [ST_FUNC_ID] nvarchar(50) not null,   -- 功能模块ID
   [NM_ORDER]   numeric(4),   -- 排序
   primary key ([ST_APP_ID], [ST_FUNC_ID])
);

execute sp_addextendedproperty 'MS_Description','应用ID', 'user', @CurrentUser, 'table', 'INFOPUB_APP_FUNC', 'column', 'ST_APP_ID'

execute sp_addextendedproperty 'MS_Description','功能模块ID', 'user', @CurrentUser, 'table', 'INFOPUB_APP_FUNC', 'column', 'ST_FUNC_ID'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'INFOPUB_APP_FUNC', 'column', 'NM_ORDER'

/*==============================================================*/
/* Table Name: 设备厂商                                         */
/* Table Code: INFOPUB_COMPANY                                  */
/*==============================================================*/
create table [INFOPUB_COMPANY] (
   [ST_COMPANY_ID]   nvarchar(50) not null,   -- 厂商ID
   [ST_COMPANY_CODE] nvarchar(50),   -- 厂商编码
   [ST_COMPANY_NAME] nvarchar(50),   -- 厂商名称
   [ST_CONTACT_NAME] nvarchar(50),   -- 厂商负责人姓名
   [ST_CONTACT_TEL]  nvarchar(50),   -- 厂商负责人联系方式
   [NM_ORDER]        numeric(1),   -- 排序
   [DT_CREATE]       datetime,   -- 创建时间
   [DT_UPDATE]       datetime,   -- 更新时间
   primary key ([ST_COMPANY_ID])
);

execute sp_addextendedproperty 'MS_Description','厂商ID', 'user', @CurrentUser, 'table', 'INFOPUB_COMPANY', 'column', 'ST_COMPANY_ID'

execute sp_addextendedproperty 'MS_Description','厂商编码', 'user', @CurrentUser, 'table', 'INFOPUB_COMPANY', 'column', 'ST_COMPANY_CODE'

execute sp_addextendedproperty 'MS_Description','厂商名称', 'user', @CurrentUser, 'table', 'INFOPUB_COMPANY', 'column', 'ST_COMPANY_NAME'

execute sp_addextendedproperty 'MS_Description','厂商负责人姓名', 'user', @CurrentUser, 'table', 'INFOPUB_COMPANY', 'column', 'ST_CONTACT_NAME'

execute sp_addextendedproperty 'MS_Description','厂商负责人联系方式', 'user', @CurrentUser, 'table', 'INFOPUB_COMPANY', 'column', 'ST_CONTACT_TEL'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'INFOPUB_COMPANY', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'INFOPUB_COMPANY', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'INFOPUB_COMPANY', 'column', 'DT_UPDATE'

/*==============================================================*/
/* Table Name: 地址表（办理点）                                 */
/* Table Code: INFOPUB_ADDRESS                                  */
/*==============================================================*/
create table [INFOPUB_ADDRESS] (
   [ST_ADDRESS_ID] nvarchar(50) not null,   -- 地址ID
   [ST_ADDRESS]    nvarchar(200),   -- 地址名称
   [ST_LABEL]      nvarchar(200),   -- 地址别名
   [NM_LNG]        numeric(18,15),   -- 经度
   [NM_LAT]        numeric(18,15),   -- 纬度
   [ST_CITY]       nvarchar(50),   -- 市
   [ST_DISTRICT]   nvarchar(50),   -- 区
   [ST_STREET]     nvarchar(50),   -- 街道
   [DT_CREATE]     datetime,   -- 创建时间
   [DT_UPDATE]     datetime,   -- 更新时间
   primary key ([ST_ADDRESS_ID])
);

execute sp_addextendedproperty 'MS_Description','地址ID', 'user', @CurrentUser, 'table', 'INFOPUB_ADDRESS', 'column', 'ST_ADDRESS_ID'

execute sp_addextendedproperty 'MS_Description','地址名称', 'user', @CurrentUser, 'table', 'INFOPUB_ADDRESS', 'column', 'ST_ADDRESS'

execute sp_addextendedproperty 'MS_Description','地址别名', 'user', @CurrentUser, 'table', 'INFOPUB_ADDRESS', 'column', 'ST_LABEL'

execute sp_addextendedproperty 'MS_Description','经度', 'user', @CurrentUser, 'table', 'INFOPUB_ADDRESS', 'column', 'NM_LNG'

execute sp_addextendedproperty 'MS_Description','纬度', 'user', @CurrentUser, 'table', 'INFOPUB_ADDRESS', 'column', 'NM_LAT'

execute sp_addextendedproperty 'MS_Description','市', 'user', @CurrentUser, 'table', 'INFOPUB_ADDRESS', 'column', 'ST_CITY'

execute sp_addextendedproperty 'MS_Description','区', 'user', @CurrentUser, 'table', 'INFOPUB_ADDRESS', 'column', 'ST_DISTRICT'

execute sp_addextendedproperty 'MS_Description','街道', 'user', @CurrentUser, 'table', 'INFOPUB_ADDRESS', 'column', 'ST_STREET'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'INFOPUB_ADDRESS', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'INFOPUB_ADDRESS', 'column', 'DT_UPDATE'

