/****************************************************************/
/**************************** Drops *****************************/
/****************************************************************/

/*==============================================================*/
/* Table Code: SMS_USER                                         */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SMS_USER';
   if obj_count > 0 then
      execute immediate 'drop table "SMS_USER"';
   end if;
end;
/

/*==============================================================*/
/* Table Code: SMS_ORGAN                                        */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SMS_ORGAN';
   if obj_count > 0 then
      execute immediate 'drop table "SMS_ORGAN"';
   end if;
end;
/

/*==============================================================*/
/* Table Code: SMS_ROLE                                         */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SMS_ROLE';
   if obj_count > 0 then
      execute immediate 'drop table "SMS_ROLE"';
   end if;
end;
/

/*==============================================================*/
/* Table Code: SMS_GROUP                                        */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SMS_GROUP';
   if obj_count > 0 then
      execute immediate 'drop table "SMS_GROUP"';
   end if;
end;
/

/*==============================================================*/
/* Table Code: SMS_MENU                                         */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SMS_MENU';
   if obj_count > 0 then
      execute immediate 'drop table "SMS_MENU"';
   end if;
end;
/

/*==============================================================*/
/* Table Code: SMS_USER_ROLE                                    */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SMS_USER_ROLE';
   if obj_count > 0 then
      execute immediate 'drop table "SMS_USER_ROLE"';
   end if;
end;
/

/*==============================================================*/
/* Table Code: SMS_USER_GROUP                                   */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SMS_USER_GROUP';
   if obj_count > 0 then
      execute immediate 'drop table "SMS_USER_GROUP"';
   end if;
end;
/

/*==============================================================*/
/* Table Code: SMS_ROLE_MENU                                    */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SMS_ROLE_MENU';
   if obj_count > 0 then
      execute immediate 'drop table "SMS_ROLE_MENU"';
   end if;
end;
/

/*==============================================================*/
/* Table Code: SMS_USER_MENU                                    */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SMS_USER_MENU';
   if obj_count > 0 then
      execute immediate 'drop table "SMS_USER_MENU"';
   end if;
end;
/

/*==============================================================*/
/* Table Code: SMS_GROUP_MENU                                   */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SMS_GROUP_MENU';
   if obj_count > 0 then
      execute immediate 'drop table "SMS_GROUP_MENU"';
   end if;
end;
/

/*==============================================================*/
/* Table Code: SMS_ATTACHMENT                                   */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SMS_ATTACHMENT';
   if obj_count > 0 then
      execute immediate 'drop table "SMS_ATTACHMENT"';
   end if;
end;
/

/*==============================================================*/
/* Table Code: SMS_RESOURCE_ACCESS_LIST                         */
/*==============================================================*/
declare obj_count int;
begin
   select count(*) into obj_count from user_tables where table_name = 'SMS_RESOURCE_ACCESS_LIST';
   if obj_count > 0 then
      execute immediate 'drop table "SMS_RESOURCE_ACCESS_LIST"';
   end if;
end;
/

/****************************************************************/
/************************ Create Tables *************************/
/****************************************************************/

/*==============================================================*/
/* Table Name: 用户表                                           */
/* Table Code: SMS_USER                                         */
/*==============================================================*/
create table "SMS_USER" (
   "ST_USER_ID"       nvarchar2(50) not null,   -- 用户ID
   "ST_LOGIN_NAME"    nvarchar2(50),   -- 登录名
   "ST_USER_CODE"     nvarchar2(50),   -- 工号
   "ST_USER_NAME"     nvarchar2(50),   -- 姓名
   "ST_PASSWORD"      nvarchar2(50),   -- 密码
   "ST_PINYIN"        nvarchar2(50),   -- 拼音
   "ST_ORGAN_ID"      nvarchar2(50),   -- 所属部门
   "ST_AREA_ID"       nvarchar2(50),   -- 区域ID
   "ST_EMAIL"         nvarchar2(50),   -- 邮箱
   "ST_MOBILE"        nvarchar2(50),   -- 手机
   "NM_RECEIVE_EMAIL" decimal(1),   -- 是否接收系统邮件
   "ST_THEME_NAME"    nvarchar2(50),   -- 界面主题
   "NM_LOCKED"        decimal(1),   -- 账号是否被锁定
   "ST_SALT"          nvarchar2(50),   -- 加密盐
   "ST_EXT_ID"        nvarchar2(50),   -- 关联拓展用户表
   "DT_CREATE"        timestamp,   -- 创建时间
   "DT_UPDATE"        timestamp,   -- 修改时间
   "ST_EXT1"          nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"          nvarchar2(50),   -- 扩展字段2
   primary key ("ST_USER_ID")
)
/

comment on table "SMS_USER" is
'用户管理体系（SMS）'
/

comment on column "SMS_USER"."ST_USER_ID" is
'用户ID'
/

comment on column "SMS_USER"."ST_LOGIN_NAME" is
'登录名'
/

comment on column "SMS_USER"."ST_USER_CODE" is
'工号'
/

comment on column "SMS_USER"."ST_USER_NAME" is
'姓名'
/

comment on column "SMS_USER"."ST_PASSWORD" is
'密码'
/

comment on column "SMS_USER"."ST_PINYIN" is
'拼音'
/

comment on column "SMS_USER"."ST_ORGAN_ID" is
'所属部门'
/

comment on column "SMS_USER"."ST_AREA_ID" is
'区域ID'
/

comment on column "SMS_USER"."ST_EMAIL" is
'邮箱'
/

comment on column "SMS_USER"."ST_MOBILE" is
'手机'
/

comment on column "SMS_USER"."NM_RECEIVE_EMAIL" is
'是否接收系统邮件；0：不接收，1：接收'
/

comment on column "SMS_USER"."ST_THEME_NAME" is
'界面主题'
/

comment on column "SMS_USER"."NM_LOCKED" is
'账号是否被锁定；0：否，1：是'
/

comment on column "SMS_USER"."ST_SALT" is
'加密盐'
/

comment on column "SMS_USER"."ST_EXT_ID" is
'关联拓展用户'
/

comment on column "SMS_USER"."DT_CREATE" is
'创建时间'
/

comment on column "SMS_USER"."DT_UPDATE" is
'修改时间'
/

comment on column "SMS_USER"."ST_EXT1" is
'扩展字段1'
/

comment on column "SMS_USER"."ST_EXT2" is
'扩展字段2'
/

/*==============================================================*/
/* Table Name: 组织机构表                                       */
/* Table Code: SMS_ORGAN                                        */
/*==============================================================*/
create table "SMS_ORGAN" (
   "ST_ORGAN_ID"   nvarchar2(50) not null,   -- 组织机构ID
   "ST_PARENT_ID"  nvarchar2(50),   -- 父ID
   "ST_ORGAN_CODE" nvarchar2(50),   -- 机构代码
   "ST_ORGAN_NAME" nvarchar2(50),   -- 机构名称
   "NM_ORDER"      decimal(5),   -- 排序字段
   "DT_CREATE"     timestamp,   -- 创建时间
   "DT_UPDATE"     timestamp,   -- 修改时间
   "ST_DESC"       nvarchar2(50),   -- 组织描述
   "ST_EXT1"       nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"       nvarchar2(50),   -- 扩展字段2
   primary key ("ST_ORGAN_ID")
)
/

comment on table "SMS_ORGAN" is
'用户管理体系（SMS）'
/

comment on column "SMS_ORGAN"."ST_ORGAN_ID" is
'组织机构ID'
/

comment on column "SMS_ORGAN"."ST_PARENT_ID" is
'父ID'
/

comment on column "SMS_ORGAN"."ST_ORGAN_CODE" is
'机构代码'
/

comment on column "SMS_ORGAN"."ST_ORGAN_NAME" is
'机构名称'
/

comment on column "SMS_ORGAN"."NM_ORDER" is
'排序字段'
/

comment on column "SMS_ORGAN"."DT_CREATE" is
'创建时间'
/

comment on column "SMS_ORGAN"."DT_UPDATE" is
'修改时间'
/

comment on column "SMS_ORGAN"."ST_DESC" is
'组织描述'
/

comment on column "SMS_ORGAN"."ST_EXT1" is
'扩展字段1'
/

comment on column "SMS_ORGAN"."ST_EXT2" is
'扩展字段2'
/

/*==============================================================*/
/* Table Name: 角色表                                           */
/* Table Code: SMS_ROLE                                         */
/*==============================================================*/
create table "SMS_ROLE" (
   "ST_ROLE_ID"   nvarchar2(50) not null,   -- 角色ID
   "ST_ROLE_CODE" nvarchar2(50),   -- 角色代码
   "ST_ROLE_NAME" nvarchar2(50),   -- 角色名称
   "NM_ORDER"     decimal(5),   -- 排序字段
   "DT_CREATE"    timestamp,   -- 创建时间
   "DT_UPDATE"    timestamp,   -- 修改时间
   "ST_DESC"      nvarchar2(50),   -- 角色描述
   "ST_EXT1"      nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"      nvarchar2(50),   -- 扩展字段2
   primary key ("ST_ROLE_ID")
)
/

comment on table "SMS_ROLE" is
'用户管理体系（SMS）'
/

comment on column "SMS_ROLE"."ST_ROLE_ID" is
'角色ID'
/

comment on column "SMS_ROLE"."ST_ROLE_CODE" is
'角色代码'
/

comment on column "SMS_ROLE"."ST_ROLE_NAME" is
'角色名称'
/

comment on column "SMS_ROLE"."NM_ORDER" is
'排序字段'
/

comment on column "SMS_ROLE"."DT_CREATE" is
'创建时间'
/

comment on column "SMS_ROLE"."DT_UPDATE" is
'修改时间'
/

comment on column "SMS_ROLE"."ST_DESC" is
'角色描述'
/

comment on column "SMS_ROLE"."ST_EXT1" is
'扩展字段1'
/

comment on column "SMS_ROLE"."ST_EXT2" is
'扩展字段2'
/

/*==============================================================*/
/* Table Name: 用户组                                           */
/* Table Code: SMS_GROUP                                        */
/*==============================================================*/
create table "SMS_GROUP" (
   "ST_GROUP_ID"   nvarchar2(50) not null,   -- 用户组ID
   "ST_GROUP_CODE" nvarchar2(50),   -- 用户组编码
   "ST_GROUP_NAME" nvarchar2(50),   -- 用户组名称
   "NM_ORDER"      decimal(5),   -- 排序字段
   "DT_CREATE"     timestamp,   -- 创建时间
   "DT_UPDATE"     timestamp,   -- 修改时间
   "ST_DESC"       nvarchar2(50),   -- 组描述
   "ST_EXT1"       nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"       nvarchar2(50),   -- 扩展字段2
   primary key ("ST_GROUP_ID")
)
/

comment on column "SMS_GROUP"."ST_GROUP_ID" is
'用户组ID'
/

comment on column "SMS_GROUP"."ST_GROUP_CODE" is
'用户组编码'
/

comment on column "SMS_GROUP"."ST_GROUP_NAME" is
'用户组名称'
/

comment on column "SMS_GROUP"."NM_ORDER" is
'排序字段'
/

comment on column "SMS_GROUP"."DT_CREATE" is
'创建时间'
/

comment on column "SMS_GROUP"."DT_UPDATE" is
'修改时间'
/

comment on column "SMS_GROUP"."ST_DESC" is
'组描述'
/

comment on column "SMS_GROUP"."ST_EXT1" is
'扩展字段1'
/

comment on column "SMS_GROUP"."ST_EXT2" is
'扩展字段2'
/

/*==============================================================*/
/* Table Name: 系统菜单表                                       */
/* Table Code: SMS_MENU                                         */
/*==============================================================*/
create table "SMS_MENU" (
   "ST_MENU_ID"   nvarchar2(50) not null,   -- 菜单ID
   "ST_MENU_CODE" nvarchar2(50),   -- 资源编号
   "ST_MENU_NAME" nvarchar2(50),   -- 资源名称
   "ST_PARENT_ID" nvarchar2(50),   -- 父ID
   "ST_URL"       nvarchar2(100),   -- URL
   "ST_IMAGE"     nvarchar2(50),   -- 图标
   "ST_TARGET"    nvarchar2(50),   -- 目标
   "NM_ORDER"     decimal(10),   -- 排序号
   "DT_CREATE"    timestamp,   -- 创建时间
   "DT_UPDATE"    timestamp,   -- 修改时间
   "ST_DESC"      nvarchar2(50),   -- 菜单描述
   "ST_EXT1"      nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"      nvarchar2(50),   -- 扩展字段2
   primary key ("ST_MENU_ID")
)
/

comment on column "SMS_MENU"."ST_MENU_ID" is
'菜单ID'
/

comment on column "SMS_MENU"."ST_MENU_CODE" is
'资源编号'
/

comment on column "SMS_MENU"."ST_MENU_NAME" is
'资源名称'
/

comment on column "SMS_MENU"."ST_PARENT_ID" is
'父ID'
/

comment on column "SMS_MENU"."ST_URL" is
'URL'
/

comment on column "SMS_MENU"."ST_IMAGE" is
'图标'
/

comment on column "SMS_MENU"."ST_TARGET" is
'目标'
/

comment on column "SMS_MENU"."NM_ORDER" is
'排序号'
/

comment on column "SMS_MENU"."DT_CREATE" is
'创建时间'
/

comment on column "SMS_MENU"."DT_UPDATE" is
'修改时间'
/

comment on column "SMS_MENU"."ST_DESC" is
'菜单描述'
/

comment on column "SMS_MENU"."ST_EXT1" is
'扩展字段1'
/

comment on column "SMS_MENU"."ST_EXT2" is
'扩展字段2'
/

/*==============================================================*/
/* Table Name: 用户角色表                                       */
/* Table Code: SMS_USER_ROLE                                    */
/*==============================================================*/
create table "SMS_USER_ROLE" (
   "ST_ROLE_ID" nvarchar2(50) not null,   -- 角色ID
   "ST_USER_ID" nvarchar2(50) not null,   -- 用户ID
   "NM_ORDER"   decimal(10),   -- 排序号
   "ST_EXT1"    nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"    nvarchar2(50),   -- 扩展字段2
   primary key ("ST_ROLE_ID", "ST_USER_ID")
)
/

comment on column "SMS_USER_ROLE"."ST_ROLE_ID" is
'角色ID'
/

comment on column "SMS_USER_ROLE"."ST_USER_ID" is
'用户ID'
/

comment on column "SMS_USER_ROLE"."NM_ORDER" is
'排序号'
/

comment on column "SMS_USER_ROLE"."ST_EXT1" is
'扩展字段1'
/

comment on column "SMS_USER_ROLE"."ST_EXT2" is
'扩展字段2'
/

/*==============================================================*/
/* Table Name: 用户关联组                                       */
/* Table Code: SMS_USER_GROUP                                   */
/*==============================================================*/
create table "SMS_USER_GROUP" (
   "ST_GROUP_ID" nvarchar2(50) not null,   -- 用户组ID
   "ST_USER_ID"  nvarchar2(50) not null,   -- 用户ID
   "NM_ORDER"    decimal(10),   -- 排序号
   "ST_EXT1"     nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"     nvarchar2(50),   -- 扩展字段2
   primary key ("ST_GROUP_ID", "ST_USER_ID")
)
/

comment on column "SMS_USER_GROUP"."ST_GROUP_ID" is
'用户组ID'
/

comment on column "SMS_USER_GROUP"."ST_USER_ID" is
'用户ID'
/

comment on column "SMS_USER_GROUP"."NM_ORDER" is
'排序号'
/

comment on column "SMS_USER_GROUP"."ST_EXT1" is
'扩展字段1'
/

comment on column "SMS_USER_GROUP"."ST_EXT2" is
'扩展字段2'
/

/*==============================================================*/
/* Table Name: 角色菜单                                         */
/* Table Code: SMS_ROLE_MENU                                    */
/*==============================================================*/
create table "SMS_ROLE_MENU" (
   "ST_ROLE_ID" nvarchar2(50) not null,   -- 角色ID
   "ST_MENU_ID" nvarchar2(50) not null,   -- 菜单ID
   "NM_ORDER"   decimal(10),   -- 排序号
   "ST_EXT1"    nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"    nvarchar2(50),   -- 扩展字段2
   primary key ("ST_ROLE_ID", "ST_MENU_ID")
)
/

comment on column "SMS_ROLE_MENU"."ST_ROLE_ID" is
'角色ID'
/

comment on column "SMS_ROLE_MENU"."ST_MENU_ID" is
'菜单ID'
/

comment on column "SMS_ROLE_MENU"."NM_ORDER" is
'排序号'
/

comment on column "SMS_ROLE_MENU"."ST_EXT1" is
'扩展字段1'
/

comment on column "SMS_ROLE_MENU"."ST_EXT2" is
'扩展字段2'
/

/*==============================================================*/
/* Table Name: 用户菜单                                         */
/* Table Code: SMS_USER_MENU                                    */
/*==============================================================*/
create table "SMS_USER_MENU" (
   "ST_USER_ID" nvarchar2(50) not null,   -- 用户ID
   "ST_MENU_ID" nvarchar2(50) not null,   -- 菜单ID
   "NM_ORDER"   decimal(10),   -- 排序号
   "ST_EXT1"    nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"    nvarchar2(50),   -- 扩展字段2
   primary key ("ST_USER_ID", "ST_MENU_ID")
)
/

comment on column "SMS_USER_MENU"."ST_USER_ID" is
'用户ID'
/

comment on column "SMS_USER_MENU"."ST_MENU_ID" is
'菜单ID'
/

comment on column "SMS_USER_MENU"."NM_ORDER" is
'排序号'
/

comment on column "SMS_USER_MENU"."ST_EXT1" is
'扩展字段1'
/

comment on column "SMS_USER_MENU"."ST_EXT2" is
'扩展字段2'
/

/*==============================================================*/
/* Table Name: 组菜单                                           */
/* Table Code: SMS_GROUP_MENU                                   */
/*==============================================================*/
create table "SMS_GROUP_MENU" (
   "ST_GROUP_ID" nvarchar2(50) not null,   -- 用户组ID
   "ST_MENU_ID"  nvarchar2(50) not null,   -- 菜单ID
   "NM_ORDER"    decimal(10),   -- 排序号
   "ST_EXT1"     nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"     nvarchar2(50),   -- 扩展字段2
   primary key ("ST_GROUP_ID", "ST_MENU_ID")
)
/

comment on column "SMS_GROUP_MENU"."ST_GROUP_ID" is
'用户组ID'
/

comment on column "SMS_GROUP_MENU"."ST_MENU_ID" is
'菜单ID'
/

comment on column "SMS_GROUP_MENU"."NM_ORDER" is
'排序号'
/

comment on column "SMS_GROUP_MENU"."ST_EXT1" is
'扩展字段1'
/

comment on column "SMS_GROUP_MENU"."ST_EXT2" is
'扩展字段2'
/

/*==============================================================*/
/* Table Name: 附件表                                           */
/* Table Code: SMS_ATTACHMENT                                   */
/*==============================================================*/
create table "SMS_ATTACHMENT" (
   "ST_ATTACH_ID"     nvarchar2(50) not null,   -- 主键
   "ST_LINK_TABLE"    nvarchar2(50),   -- 关联表名称
   "ST_LINK_ID"       nvarchar2(50),   -- 关联主键值
   "ST_ATTACH_TYPE"   nvarchar2(50),   -- 附件类型
   "ST_FILE_NAME"     nvarchar2(100),   -- 文件名
   "ST_FILE_SIZE"     nvarchar2(50),   -- 文件大小
   "CL_CONTENT"       clob,   -- 文本内容
   "BL_CONTENT"       blob,   -- 文件内容
   "BL_SMALL_CONTENT" blob,   -- 图片缩略图
   "ST_FILE_TYPE"     nvarchar2(10),   -- 文件类型
   "DT_CREATE"        timestamp,   -- 创建时间
   "DT_UPDATE"        timestamp,   -- 修改时间
   "ST_EXT1"          nvarchar2(50),   -- 扩展字段1
   "ST_EXT2"          nvarchar2(50),   -- 扩展字段2
   primary key ("ST_ATTACH_ID")
)
/

comment on column "SMS_ATTACHMENT"."ST_ATTACH_ID" is
'主键'
/

comment on column "SMS_ATTACHMENT"."ST_LINK_TABLE" is
'关联表名称'
/

comment on column "SMS_ATTACHMENT"."ST_LINK_ID" is
'关联主键值'
/

comment on column "SMS_ATTACHMENT"."ST_ATTACH_TYPE" is
'附件类型'
/

comment on column "SMS_ATTACHMENT"."ST_FILE_NAME" is
'文件名'
/

comment on column "SMS_ATTACHMENT"."ST_FILE_SIZE" is
'文件大小'
/

comment on column "SMS_ATTACHMENT"."CL_CONTENT" is
'文本内容'
/

comment on column "SMS_ATTACHMENT"."BL_CONTENT" is
'文件内容'
/

comment on column "SMS_ATTACHMENT"."BL_SMALL_CONTENT" is
'图片缩略图'
/

comment on column "SMS_ATTACHMENT"."ST_FILE_TYPE" is
'文件类型'
/

comment on column "SMS_ATTACHMENT"."DT_CREATE" is
'创建时间'
/

comment on column "SMS_ATTACHMENT"."DT_UPDATE" is
'修改时间'
/

comment on column "SMS_ATTACHMENT"."ST_EXT1" is
'扩展字段1'
/

comment on column "SMS_ATTACHMENT"."ST_EXT2" is
'扩展字段2'
/

/*==============================================================*/
/* Table Name: 资源权限访问列表                                 */
/* Table Code: SMS_RESOURCE_ACCESS_LIST                         */
/*==============================================================*/
create table "SMS_RESOURCE_ACCESS_LIST" (
   "ST_USER_ID"          nvarchar2(50) not null,   -- 用户ID
   "ST_RESOURCE_ID"      nvarchar2(50) not null,   -- 资源ID
   "ST_RESOURCE_TYPE_ID" nvarchar2(50) not null,   -- 资源类型ID
   "ST_UNIQUE_VALUE"     nvarchar2(100),   -- 资源唯一值
   primary key ("ST_USER_ID", "ST_RESOURCE_ID", "ST_RESOURCE_TYPE_ID")
)
/

comment on column "SMS_RESOURCE_ACCESS_LIST"."ST_USER_ID" is
'用户ID'
/

comment on column "SMS_RESOURCE_ACCESS_LIST"."ST_RESOURCE_ID" is
'资源ID'
/

comment on column "SMS_RESOURCE_ACCESS_LIST"."ST_RESOURCE_TYPE_ID" is
'资源类型ID'
/

comment on column "SMS_RESOURCE_ACCESS_LIST"."ST_UNIQUE_VALUE" is
'资源唯一值'
/

