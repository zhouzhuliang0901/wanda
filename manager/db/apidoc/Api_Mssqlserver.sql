declare @CurrentUser sysname
select @CurrentUser = user_name()

/****************************************************************/
/**************************** Drops *****************************/
/****************************************************************/

/*==============================================================*/
/* Table Code: APIDOC_PROJECT                                   */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[APIDOC_PROJECT]') and type = 'U') 
   drop table [APIDOC_PROJECT];

/*==============================================================*/
/* Table Code: APIDOC_MODULE                                    */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[APIDOC_MODULE]') and type = 'U') 
   drop table [APIDOC_MODULE];

/*==============================================================*/
/* Table Code: APIDOC_INTERFACE                                 */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[APIDOC_INTERFACE]') and type = 'U') 
   drop table [APIDOC_INTERFACE];

/*==============================================================*/
/* Table Code: APIDOC_MOD_INTER                                 */
/*==============================================================*/
if exists (select 1 from sysobjects where id = object_id('[APIDOC_MOD_INTER]') and type = 'U') 
   drop table [APIDOC_MOD_INTER];

/****************************************************************/
/************************ Create Tables *************************/
/****************************************************************/


/*==============================================================*/
/* Table Name: 项目                                             */
/* Table Code: APIDOC_PROJECT                                   */
/*==============================================================*/
create table [APIDOC_PROJECT] (
   [ST_PROJECT_ID]   nvarchar(50) not null,   -- 项目ID
   [ST_USER_ID]      nvarchar(50),   -- 用户ID
   [ST_PROJECT_NAME] nvarchar(50),   -- 项目名称
   [NM_ORDER]        numeric(4),   -- 排序号
   [DT_CREATE]       datetime,   -- 创建时间
   [ST_REMARK]       nvarchar(100),   -- 备注
   [ST_EXT1]         nvarchar(50),   -- 扩展字段1
   [ST_EXT2]         nvarchar(50),   -- 扩展字段2
   primary key ([ST_PROJECT_ID])
);

execute sp_addextendedproperty 'MS_Description','项目ID', 'user', @CurrentUser, 'table', 'APIDOC_PROJECT', 'column', 'ST_PROJECT_ID'

execute sp_addextendedproperty 'MS_Description','用户ID', 'user', @CurrentUser, 'table', 'APIDOC_PROJECT', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','项目名称', 'user', @CurrentUser, 'table', 'APIDOC_PROJECT', 'column', 'ST_PROJECT_NAME'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'APIDOC_PROJECT', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'APIDOC_PROJECT', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','备注', 'user', @CurrentUser, 'table', 'APIDOC_PROJECT', 'column', 'ST_REMARK'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'APIDOC_PROJECT', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'APIDOC_PROJECT', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 模块                                             */
/* Table Code: APIDOC_MODULE                                    */
/*==============================================================*/
create table [APIDOC_MODULE] (
   [ST_MODULE_ID]   nvarchar(50) not null,   -- 模块ID
   [ST_MODULE_NAME] nvarchar(50),   -- 模块名称
   [ST_REMARK]      nvarchar(500),   -- 模块说明
   [ST_PROJECT_ID]  nvarchar(50),   -- 项目ID
   [NM_ORDER]       numeric(4),   -- 排序号
   [ST_PARENT_ID]   nvarchar(50),   -- 父模块ID
   [DT_CREATE]      datetime,   -- 创建时间
   [ST_EXT1]        nvarchar(50),   -- 扩展字段1
   [ST_EXT2]        nvarchar(50),   -- 扩展字段2
   primary key ([ST_MODULE_ID])
);

execute sp_addextendedproperty 'MS_Description','模块ID', 'user', @CurrentUser, 'table', 'APIDOC_MODULE', 'column', 'ST_MODULE_ID'

execute sp_addextendedproperty 'MS_Description','模块名称', 'user', @CurrentUser, 'table', 'APIDOC_MODULE', 'column', 'ST_MODULE_NAME'

execute sp_addextendedproperty 'MS_Description','模块说明', 'user', @CurrentUser, 'table', 'APIDOC_MODULE', 'column', 'ST_REMARK'

execute sp_addextendedproperty 'MS_Description','项目ID', 'user', @CurrentUser, 'table', 'APIDOC_MODULE', 'column', 'ST_PROJECT_ID'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'APIDOC_MODULE', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','父模块ID', 'user', @CurrentUser, 'table', 'APIDOC_MODULE', 'column', 'ST_PARENT_ID'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'APIDOC_MODULE', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'APIDOC_MODULE', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'APIDOC_MODULE', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 接口                                             */
/* Table Code: APIDOC_INTERFACE                                 */
/*==============================================================*/
create table [APIDOC_INTERFACE] (
   [ST_INTERFACE_ID]   nvarchar(50) not null,   -- 接口ID
   [ST_INTERFACE_NAME] nvarchar(50),   -- 接口名
   [ST_URL]            nvarchar(200),   -- 接口链接
   [CL_REMARK]         text,   -- 接口说明
   [ST_METHOD]         nvarchar(50),   -- 请求方式
   [CL_REQUEST_RARAM]  text,   -- 请求参数说明
   [CL_REQUEST_EXAM]   text,   -- 请求示例
   [CL_RESPONSE_PARAM] text,   -- 返回参数说明
   [CL_RESPONSE_EXAM]  text,   -- 返回示例
   [ST_MODULE_ID]      nvarchar(50),   -- 所属模块ID
   [NM_STATUS]         numeric(1),   -- 是否可用
   [NM_ORDER]          numeric(4),   -- 排序号
   [DT_CREATE]         datetime,   -- 创建时间
   [DT_UPDATE]         datetime,   -- 更新时间
   [NM_VERSION]        numeric(18),   -- 版本号
   [ST_EXT1]           nvarchar(50),   -- 扩展字段1
   [ST_EXT2]           nvarchar(50),   -- 扩展字段2
   primary key ([ST_INTERFACE_ID])
);

execute sp_addextendedproperty 'MS_Description','接口ID', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'ST_INTERFACE_ID'

execute sp_addextendedproperty 'MS_Description','接口名', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'ST_INTERFACE_NAME'

execute sp_addextendedproperty 'MS_Description','api链接', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'ST_URL'

execute sp_addextendedproperty 'MS_Description','接口说明', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'CL_REMARK'

execute sp_addextendedproperty 'MS_Description',' 请求方式', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'ST_METHOD'

execute sp_addextendedproperty 'MS_Description','请求参数备注', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'CL_REQUEST_RARAM'

execute sp_addextendedproperty 'MS_Description','请求示例', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'CL_REQUEST_EXAM'

execute sp_addextendedproperty 'MS_Description','返回参数说明', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'CL_RESPONSE_PARAM'

execute sp_addextendedproperty 'MS_Description','返回示例', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'CL_RESPONSE_EXAM'

execute sp_addextendedproperty 'MS_Description','所属模块ID', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'ST_MODULE_ID'

execute sp_addextendedproperty 'MS_Description','是否可用，0：不可用；1：可用；2： 删除', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'NM_STATUS'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','创建时间', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','更新时间', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','版本号', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'NM_VERSION'

execute sp_addextendedproperty 'MS_Description','扩展字段1', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','扩展字段2', 'user', @CurrentUser, 'table', 'APIDOC_INTERFACE', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: 模块关联接口                                     */
/* Table Code: APIDOC_MOD_INTER                                 */
/*==============================================================*/
create table [APIDOC_MOD_INTER] (
   [ST_MODULE_ID]    nvarchar(50) not null,   -- 模块ID
   [ST_INTERFACE_ID] nvarchar(50) not null,   -- 接口ID
   [NM_ORDER]        numeric(4),   -- 排序号
   primary key ([ST_MODULE_ID], [ST_INTERFACE_ID])
);

execute sp_addextendedproperty 'MS_Description','模块ID', 'user', @CurrentUser, 'table', 'APIDOC_MOD_INTER', 'column', 'ST_MODULE_ID'

execute sp_addextendedproperty 'MS_Description','接口ID', 'user', @CurrentUser, 'table', 'APIDOC_MOD_INTER', 'column', 'ST_INTERFACE_ID'

execute sp_addextendedproperty 'MS_Description','排序', 'user', @CurrentUser, 'table', 'APIDOC_MOD_INTER', 'column', 'NM_ORDER'

