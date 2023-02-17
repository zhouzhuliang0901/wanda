declare @CurrentUser sysname
select @CurrentUser = user_name()

/****************************************************************/
/**************************** Drops *****************************/
/****************************************************************/

/*==============================================================*/
/* Table Code: SMS_USER                                         */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SMS_USER]') and type = 'U') 
   drop table [SMS_USER];

/*==============================================================*/
/* Table Code: SMS_ORGAN                                        */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SMS_ORGAN]') and type = 'U') 
   drop table [SMS_ORGAN];

/*==============================================================*/
/* Table Code: SMS_ROLE                                         */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SMS_ROLE]') and type = 'U') 
   drop table [SMS_ROLE];

/*==============================================================*/
/* Table Code: SMS_GROUP                                        */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SMS_GROUP]') and type = 'U') 
   drop table [SMS_GROUP];

/*==============================================================*/
/* Table Code: SMS_MENU                                         */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SMS_MENU]') and type = 'U') 
   drop table [SMS_MENU];

/*==============================================================*/
/* Table Code: SMS_USER_ROLE                                    */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SMS_USER_ROLE]') and type = 'U') 
   drop table [SMS_USER_ROLE];

/*==============================================================*/
/* Table Code: SMS_USER_GROUP                                   */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SMS_USER_GROUP]') and type = 'U') 
   drop table [SMS_USER_GROUP];

/*==============================================================*/
/* Table Code: SMS_ROLE_MENU                                    */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SMS_ROLE_MENU]') and type = 'U') 
   drop table [SMS_ROLE_MENU];

/*==============================================================*/
/* Table Code: SMS_USER_MENU                                    */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SMS_USER_MENU]') and type = 'U') 
   drop table [SMS_USER_MENU];

/*==============================================================*/
/* Table Code: SMS_GROUP_MENU                                   */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SMS_GROUP_MENU]') and type = 'U') 
   drop table [SMS_GROUP_MENU];

/*==============================================================*/
/* Table Code: SMS_ATTACHMENT                                   */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SMS_ATTACHMENT]') and type = 'U') 
   drop table [SMS_ATTACHMENT];

/*==============================================================*/
/* Table Code: SMS_RESOURCE_ACCESS_LIST                         */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[SMS_RESOURCE_ACCESS_LIST]') and type = 'U') 
   drop table [SMS_RESOURCE_ACCESS_LIST];

/****************************************************************/
/************************ Create Tables *************************/
/****************************************************************/


/*==============================================================*/
/* Table Name: 用户表                                           */
/* Table Code: SMS_USER                                         */
/*==============================================================*/
create table [SMS_USER] (
   [ST_USER_ID]       nvarchar(50) not null,   -- 用户ID
   [ST_LOGIN_NAME]    nvarchar(50),   -- 登录名
   [ST_USER_CODE]     nvarchar(50),   -- 工号
   [ST_USER_NAME]     nvarchar(50),   -- 姓名
   [ST_PASSWORD]      nvarchar(50),   -- 密码
   [ST_PINYIN]        nvarchar(50),   -- 拼音
   [ST_ORGAN_ID]      nvarchar(50),   -- 所属部门
   [ST_AREA_ID]       nvarchar(50),   -- 区域ID
   [ST_EMAIL]         nvarchar(50),   -- 邮箱
   [ST_MOBILE]        nvarchar(50),   -- 手机
   [NM_RECEIVE_EMAIL] numeric(1),   -- 是否接收系统邮件
   [ST_THEME_NAME]    nvarchar(50),   -- 界面主题
   [NM_LOCKED]        numeric(1),   -- 账号是否被锁定
   [ST_SALT]          nvarchar(50),   -- 加密盐
   [ST_EXT_ID]        nvarchar(50),   -- 关联拓展用户表
   [DT_CREATE]        datetime,   -- 创建时间
   [DT_UPDATE]        datetime,   -- 修改时间
   [ST_EXT1]          nvarchar(50),   -- 扩展字段1
   [ST_EXT2]          nvarchar(50),   -- 扩展字段2
   primary key ([ST_USER_ID])
);

execute sp_addextendedproperty 'MS_Description','用户管理体系（SMS）', 'user', @CurrentUser, 'table', 'SMS_USER'

execute sp_addextendedproperty 'MS_Description','用户ID', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','登录名', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_LOGIN_NAME'

execute sp_addextendedproperty 'MS_Description','工号', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_USER_CODE'

execute sp_addextendedproperty 'MS_Description','姓名', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_USER_NAME'

execute sp_addextendedproperty 'MS_Description','密码', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_PASSWORD'

execute sp_addextendedproperty 'MS_Description','拼音', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_PINYIN'

execute sp_addextendedproperty 'MS_Description','所属部门', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_ORGAN_ID'

execute sp_addextendedproperty 'MS_Description','区域ID', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_AREA_ID'

execute sp_addextendedproperty 'MS_Description','邮箱', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_EMAIL'

execute sp_addextendedproperty 'MS_Description','手机', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_MOBILE'

execute sp_addextendedproperty 'MS_Description','是否接收系统邮件；0：不接收，1：接收', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'NM_RECEIVE_EMAIL'

execute sp_addextendedproperty 'MS_Description','界面主题', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_THEME_NAME'

execute sp_addextendedproperty 'MS_Description','账号是否被锁定；0：否，1：是', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'NM_LOCKED'

execute sp_addextendedproperty 'MS_Description','加密盐', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_SALT'

execute sp_addextendedproperty 'MS_Description','关联拓展用户', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_EXT_ID'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','修改时间', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 组织机构表                                       */
/* Table Code: SMS_ORGAN                                        */
/*==============================================================*/
create table [SMS_ORGAN] (
   [ST_ORGAN_ID]   nvarchar(50) not null,   -- 组织机构ID
   [ST_PARENT_ID]  nvarchar(50),   -- 父ID
   [ST_ORGAN_CODE] nvarchar(50),   -- 机构代码
   [ST_ORGAN_NAME] nvarchar(50),   -- 机构名称
   [NM_ORDER]      numeric(5),   -- 排序字段
   [DT_CREATE]     datetime,   -- 创建时间
   [DT_UPDATE]     datetime,   -- 修改时间
   [ST_DESC]       nvarchar(50),   -- 组织描述
   [ST_EXT1]       nvarchar(50),   -- 扩展字段1
   [ST_EXT2]       nvarchar(50),   -- 扩展字段2
   primary key ([ST_ORGAN_ID])
);

execute sp_addextendedproperty 'MS_Description','用户管理体系（SMS）', 'user', @CurrentUser, 'table', 'SMS_ORGAN'

execute sp_addextendedproperty 'MS_Description','组织机构ID', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'ST_ORGAN_ID'

execute sp_addextendedproperty 'MS_Description','父ID', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'ST_PARENT_ID'

execute sp_addextendedproperty 'MS_Description','机构代码', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'ST_ORGAN_CODE'

execute sp_addextendedproperty 'MS_Description','机构名称', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'ST_ORGAN_NAME'

execute sp_addextendedproperty 'MS_Description','排序字段', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','修改时间', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','组织描述', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 角色表                                           */
/* Table Code: SMS_ROLE                                         */
/*==============================================================*/
create table [SMS_ROLE] (
   [ST_ROLE_ID]   nvarchar(50) not null,   -- 角色ID
   [ST_ROLE_CODE] nvarchar(50),   -- 角色代码
   [ST_ROLE_NAME] nvarchar(50),   -- 角色名称
   [NM_ORDER]     numeric(5),   -- 排序字段
   [DT_CREATE]    datetime,   -- 创建时间
   [DT_UPDATE]    datetime,   -- 修改时间
   [ST_DESC]      nvarchar(50),   -- 角色描述
   [ST_EXT1]      nvarchar(50),   -- 扩展字段1
   [ST_EXT2]      nvarchar(50),   -- 扩展字段2
   primary key ([ST_ROLE_ID])
);

execute sp_addextendedproperty 'MS_Description','用户管理体系（SMS）', 'user', @CurrentUser, 'table', 'SMS_ROLE'

execute sp_addextendedproperty 'MS_Description','角色ID', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'ST_ROLE_ID'

execute sp_addextendedproperty 'MS_Description','角色代码', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'ST_ROLE_CODE'

execute sp_addextendedproperty 'MS_Description','角色名称', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'ST_ROLE_NAME'

execute sp_addextendedproperty 'MS_Description','排序字段', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','修改时间', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','角色描述', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 用户组                                           */
/* Table Code: SMS_GROUP                                        */
/*==============================================================*/
create table [SMS_GROUP] (
   [ST_GROUP_ID]   nvarchar(50) not null,   -- 用户组ID
   [ST_GROUP_CODE] nvarchar(50),   -- 用户组编码
   [ST_GROUP_NAME] nvarchar(50),   -- 用户组名称
   [NM_ORDER]      numeric(5),   -- 排序字段
   [DT_CREATE]     datetime,   -- 创建时间
   [DT_UPDATE]     datetime,   -- 修改时间
   [ST_DESC]       nvarchar(50),   -- 组描述
   [ST_EXT1]       nvarchar(50),   -- 扩展字段1
   [ST_EXT2]       nvarchar(50),   -- 扩展字段2
   primary key ([ST_GROUP_ID])
);

execute sp_addextendedproperty 'MS_Description','用户组ID', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'ST_GROUP_ID'

execute sp_addextendedproperty 'MS_Description','用户组编码', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'ST_GROUP_CODE'

execute sp_addextendedproperty 'MS_Description','用户组名称', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'ST_GROUP_NAME'

execute sp_addextendedproperty 'MS_Description','排序字段', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','修改时间', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','组描述', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 系统菜单表                                       */
/* Table Code: SMS_MENU                                         */
/*==============================================================*/
create table [SMS_MENU] (
   [ST_MENU_ID]   nvarchar(50) not null,   -- 菜单ID
   [ST_MENU_CODE] nvarchar(50),   -- 资源编号
   [ST_MENU_NAME] nvarchar(50),   -- 资源名称
   [ST_PARENT_ID] nvarchar(50),   -- 父ID
   [ST_URL]       nvarchar(100),   -- URL
   [ST_IMAGE]     nvarchar(50),   -- 图标
   [ST_TARGET]    nvarchar(50),   -- 目标
   [NM_ORDER]     numeric(10),   -- 排序号
   [DT_CREATE]    datetime,   -- 创建时间
   [DT_UPDATE]    datetime,   -- 修改时间
   [ST_DESC]      nvarchar(50),   -- 菜单描述
   [ST_EXT1]      nvarchar(50),   -- 扩展字段1
   [ST_EXT2]      nvarchar(50),   -- 扩展字段2
   primary key ([ST_MENU_ID])
);

execute sp_addextendedproperty 'MS_Description','菜单ID', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_MENU_ID'

execute sp_addextendedproperty 'MS_Description','资源编号', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_MENU_CODE'

execute sp_addextendedproperty 'MS_Description','资源名称', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_MENU_NAME'

execute sp_addextendedproperty 'MS_Description','父ID', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_PARENT_ID'

execute sp_addextendedproperty 'MS_Description','URL', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_URL'

execute sp_addextendedproperty 'MS_Description','图标', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_IMAGE'

execute sp_addextendedproperty 'MS_Description','目标', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_TARGET'

execute sp_addextendedproperty 'MS_Description','排序号', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','修改时间', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','菜单描述', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 用户角色表                                       */
/* Table Code: SMS_USER_ROLE                                    */
/*==============================================================*/
create table [SMS_USER_ROLE] (
   [ST_ROLE_ID] nvarchar(50) not null,   -- 角色ID
   [ST_USER_ID] nvarchar(50) not null,   -- 用户ID
   [NM_ORDER]   numeric(10),   -- 排序号
   [ST_EXT1]    nvarchar(50),   -- 扩展字段1
   [ST_EXT2]    nvarchar(50),   -- 扩展字段2
   primary key ([ST_ROLE_ID], [ST_USER_ID])
);

execute sp_addextendedproperty 'MS_Description','角色ID', 'user', @CurrentUser, 'table', 'SMS_USER_ROLE', 'column', 'ST_ROLE_ID'

execute sp_addextendedproperty 'MS_Description','用户ID', 'user', @CurrentUser, 'table', 'SMS_USER_ROLE', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','排序号', 'user', @CurrentUser, 'table', 'SMS_USER_ROLE', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SMS_USER_ROLE', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SMS_USER_ROLE', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 用户关联组                                       */
/* Table Code: SMS_USER_GROUP                                   */
/*==============================================================*/
create table [SMS_USER_GROUP] (
   [ST_GROUP_ID] nvarchar(50) not null,   -- 用户组ID
   [ST_USER_ID]  nvarchar(50) not null,   -- 用户ID
   [NM_ORDER]    numeric(10),   -- 排序号
   [ST_EXT1]     nvarchar(50),   -- 扩展字段1
   [ST_EXT2]     nvarchar(50),   -- 扩展字段2
   primary key ([ST_GROUP_ID], [ST_USER_ID])
);

execute sp_addextendedproperty 'MS_Description','用户组ID', 'user', @CurrentUser, 'table', 'SMS_USER_GROUP', 'column', 'ST_GROUP_ID'

execute sp_addextendedproperty 'MS_Description','用户ID', 'user', @CurrentUser, 'table', 'SMS_USER_GROUP', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','排序号', 'user', @CurrentUser, 'table', 'SMS_USER_GROUP', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SMS_USER_GROUP', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SMS_USER_GROUP', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 角色菜单                                         */
/* Table Code: SMS_ROLE_MENU                                    */
/*==============================================================*/
create table [SMS_ROLE_MENU] (
   [ST_ROLE_ID] nvarchar(50) not null,   -- 角色ID
   [ST_MENU_ID] nvarchar(50) not null,   -- 菜单ID
   [NM_ORDER]   numeric(10),   -- 排序号
   [ST_EXT1]    nvarchar(50),   -- 扩展字段1
   [ST_EXT2]    nvarchar(50),   -- 扩展字段2
   primary key ([ST_ROLE_ID], [ST_MENU_ID])
);

execute sp_addextendedproperty 'MS_Description','角色ID', 'user', @CurrentUser, 'table', 'SMS_ROLE_MENU', 'column', 'ST_ROLE_ID'

execute sp_addextendedproperty 'MS_Description','菜单ID', 'user', @CurrentUser, 'table', 'SMS_ROLE_MENU', 'column', 'ST_MENU_ID'

execute sp_addextendedproperty 'MS_Description','排序号', 'user', @CurrentUser, 'table', 'SMS_ROLE_MENU', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SMS_ROLE_MENU', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SMS_ROLE_MENU', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 用户菜单                                         */
/* Table Code: SMS_USER_MENU                                    */
/*==============================================================*/
create table [SMS_USER_MENU] (
   [ST_USER_ID] nvarchar(50) not null,   -- 用户ID
   [ST_MENU_ID] nvarchar(50) not null,   -- 菜单ID
   [NM_ORDER]   numeric(10),   -- 排序号
   [ST_EXT1]    nvarchar(50),   -- 扩展字段1
   [ST_EXT2]    nvarchar(50),   -- 扩展字段2
   primary key ([ST_USER_ID], [ST_MENU_ID])
);

execute sp_addextendedproperty 'MS_Description','用户ID', 'user', @CurrentUser, 'table', 'SMS_USER_MENU', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','菜单ID', 'user', @CurrentUser, 'table', 'SMS_USER_MENU', 'column', 'ST_MENU_ID'

execute sp_addextendedproperty 'MS_Description','排序号', 'user', @CurrentUser, 'table', 'SMS_USER_MENU', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SMS_USER_MENU', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SMS_USER_MENU', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 组菜单                                           */
/* Table Code: SMS_GROUP_MENU                                   */
/*==============================================================*/
create table [SMS_GROUP_MENU] (
   [ST_GROUP_ID] nvarchar(50) not null,   -- 用户组ID
   [ST_MENU_ID]  nvarchar(50) not null,   -- 菜单ID
   [NM_ORDER]    numeric(10),   -- 排序号
   [ST_EXT1]     nvarchar(50),   -- 扩展字段1
   [ST_EXT2]     nvarchar(50),   -- 扩展字段2
   primary key ([ST_GROUP_ID], [ST_MENU_ID])
);

execute sp_addextendedproperty 'MS_Description','用户组ID', 'user', @CurrentUser, 'table', 'SMS_GROUP_MENU', 'column', 'ST_GROUP_ID'

execute sp_addextendedproperty 'MS_Description','菜单ID', 'user', @CurrentUser, 'table', 'SMS_GROUP_MENU', 'column', 'ST_MENU_ID'

execute sp_addextendedproperty 'MS_Description','排序号', 'user', @CurrentUser, 'table', 'SMS_GROUP_MENU', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SMS_GROUP_MENU', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SMS_GROUP_MENU', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 附件表                                           */
/* Table Code: SMS_ATTACHMENT                                   */
/*==============================================================*/
create table [SMS_ATTACHMENT] (
   [ST_ATTACH_ID]     nvarchar(50) not null,   -- 主键
   [ST_LINK_TABLE]    nvarchar(50),   -- 关联表名称
   [ST_LINK_ID]       nvarchar(50),   -- 关联主键值
   [ST_ATTACH_TYPE]   nvarchar(50),   -- 附件类型
   [ST_FILE_NAME]     nvarchar(100),   -- 文件名
   [ST_FILE_SIZE]     nvarchar(50),   -- 文件大小
   [CL_CONTENT]       text,   -- 文本内容
   [BL_CONTENT]       image,   -- 文件内容
   [BL_SMALL_CONTENT] image,   -- 图片缩略图
   [ST_FILE_TYPE]     nvarchar(10),   -- 文件类型
   [DT_CREATE]        datetime,   -- 创建时间
   [DT_UPDATE]        datetime,   -- 修改时间
   [ST_EXT1]          nvarchar(50),   -- 扩展字段1
   [ST_EXT2]          nvarchar(50),   -- 扩展字段2
   primary key ([ST_ATTACH_ID])
);

execute sp_addextendedproperty 'MS_Description','主键', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_ATTACH_ID'

execute sp_addextendedproperty 'MS_Description','关联表名称', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_LINK_TABLE'

execute sp_addextendedproperty 'MS_Description','关联主键值', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_LINK_ID'

execute sp_addextendedproperty 'MS_Description','附件类型', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_ATTACH_TYPE'

execute sp_addextendedproperty 'MS_Description','文件名', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_FILE_NAME'

execute sp_addextendedproperty 'MS_Description','文件大小', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_FILE_SIZE'

execute sp_addextendedproperty 'MS_Description','文本内容', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'CL_CONTENT'

execute sp_addextendedproperty 'MS_Description','文件内容', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'BL_CONTENT'

execute sp_addextendedproperty 'MS_Description','图片缩略图', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'BL_SMALL_CONTENT'

execute sp_addextendedproperty 'MS_Description','文件类型', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_FILE_TYPE'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','修改时间', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 资源权限访问列表                                 */
/* Table Code: SMS_RESOURCE_ACCESS_LIST                         */
/*==============================================================*/
create table [SMS_RESOURCE_ACCESS_LIST] (
   [ST_USER_ID]          nvarchar(50) not null,   -- 用户ID
   [ST_RESOURCE_ID]      nvarchar(50) not null,   -- 资源ID
   [ST_RESOURCE_TYPE_ID] nvarchar(50) not null,   -- 资源类型ID
   [ST_UNIQUE_VALUE]     nvarchar(100),   -- 资源唯一值
   primary key ([ST_USER_ID], [ST_RESOURCE_ID], [ST_RESOURCE_TYPE_ID])
);

execute sp_addextendedproperty 'MS_Description','用户ID', 'user', @CurrentUser, 'table', 'SMS_RESOURCE_ACCESS_LIST', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','资源ID', 'user', @CurrentUser, 'table', 'SMS_RESOURCE_ACCESS_LIST', 'column', 'ST_RESOURCE_ID'

execute sp_addextendedproperty 'MS_Description','资源类型ID', 'user', @CurrentUser, 'table', 'SMS_RESOURCE_ACCESS_LIST', 'column', 'ST_RESOURCE_TYPE_ID'

execute sp_addextendedproperty 'MS_Description','资源唯一值', 'user', @CurrentUser, 'table', 'SMS_RESOURCE_ACCESS_LIST', 'column', 'ST_UNIQUE_VALUE'

