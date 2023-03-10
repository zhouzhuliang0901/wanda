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
/* Table Name: ?????????                                           */
/* Table Code: SMS_USER                                         */
/*==============================================================*/
create table [SMS_USER] (
   [ST_USER_ID]       nvarchar(50) not null,   -- ??????ID
   [ST_LOGIN_NAME]    nvarchar(50),   -- ?????????
   [ST_USER_CODE]     nvarchar(50),   -- ??????
   [ST_USER_NAME]     nvarchar(50),   -- ??????
   [ST_PASSWORD]      nvarchar(50),   -- ??????
   [ST_PINYIN]        nvarchar(50),   -- ??????
   [ST_ORGAN_ID]      nvarchar(50),   -- ????????????
   [ST_AREA_ID]       nvarchar(50),   -- ??????ID
   [ST_EMAIL]         nvarchar(50),   -- ??????
   [ST_MOBILE]        nvarchar(50),   -- ??????
   [NM_RECEIVE_EMAIL] numeric(1),   -- ????????????????????????
   [ST_THEME_NAME]    nvarchar(50),   -- ????????????
   [NM_LOCKED]        numeric(1),   -- ?????????????????????
   [ST_SALT]          nvarchar(50),   -- ?????????
   [ST_EXT_ID]        nvarchar(50),   -- ?????????????????????
   [DT_CREATE]        datetime,   -- ????????????
   [DT_UPDATE]        datetime,   -- ????????????
   [ST_EXT1]          nvarchar(50),   -- ????????????1
   [ST_EXT2]          nvarchar(50),   -- ????????????2
   primary key ([ST_USER_ID])
);

execute sp_addextendedproperty 'MS_Description','?????????????????????SMS???', 'user', @CurrentUser, 'table', 'SMS_USER'

execute sp_addextendedproperty 'MS_Description','??????ID', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','?????????', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_LOGIN_NAME'

execute sp_addextendedproperty 'MS_Description','??????', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_USER_CODE'

execute sp_addextendedproperty 'MS_Description','??????', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_USER_NAME'

execute sp_addextendedproperty 'MS_Description','??????', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_PASSWORD'

execute sp_addextendedproperty 'MS_Description','??????', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_PINYIN'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_ORGAN_ID'

execute sp_addextendedproperty 'MS_Description','??????ID', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_AREA_ID'

execute sp_addextendedproperty 'MS_Description','??????', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_EMAIL'

execute sp_addextendedproperty 'MS_Description','??????', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_MOBILE'

execute sp_addextendedproperty 'MS_Description','???????????????????????????0???????????????1?????????', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'NM_RECEIVE_EMAIL'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_THEME_NAME'

execute sp_addextendedproperty 'MS_Description','????????????????????????0?????????1??????', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'NM_LOCKED'

execute sp_addextendedproperty 'MS_Description','?????????', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_SALT'

execute sp_addextendedproperty 'MS_Description','??????????????????', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_EXT_ID'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','????????????1', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','????????????2', 'user', @CurrentUser, 'table', 'SMS_USER', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: ???????????????                                       */
/* Table Code: SMS_ORGAN                                        */
/*==============================================================*/
create table [SMS_ORGAN] (
   [ST_ORGAN_ID]   nvarchar(50) not null,   -- ????????????ID
   [ST_PARENT_ID]  nvarchar(50),   -- ???ID
   [ST_ORGAN_CODE] nvarchar(50),   -- ????????????
   [ST_ORGAN_NAME] nvarchar(50),   -- ????????????
   [NM_ORDER]      numeric(5),   -- ????????????
   [DT_CREATE]     datetime,   -- ????????????
   [DT_UPDATE]     datetime,   -- ????????????
   [ST_DESC]       nvarchar(50),   -- ????????????
   [ST_EXT1]       nvarchar(50),   -- ????????????1
   [ST_EXT2]       nvarchar(50),   -- ????????????2
   primary key ([ST_ORGAN_ID])
);

execute sp_addextendedproperty 'MS_Description','?????????????????????SMS???', 'user', @CurrentUser, 'table', 'SMS_ORGAN'

execute sp_addextendedproperty 'MS_Description','????????????ID', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'ST_ORGAN_ID'

execute sp_addextendedproperty 'MS_Description','???ID', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'ST_PARENT_ID'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'ST_ORGAN_CODE'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'ST_ORGAN_NAME'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','????????????1', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','????????????2', 'user', @CurrentUser, 'table', 'SMS_ORGAN', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: ?????????                                           */
/* Table Code: SMS_ROLE                                         */
/*==============================================================*/
create table [SMS_ROLE] (
   [ST_ROLE_ID]   nvarchar(50) not null,   -- ??????ID
   [ST_ROLE_CODE] nvarchar(50),   -- ????????????
   [ST_ROLE_NAME] nvarchar(50),   -- ????????????
   [NM_ORDER]     numeric(5),   -- ????????????
   [DT_CREATE]    datetime,   -- ????????????
   [DT_UPDATE]    datetime,   -- ????????????
   [ST_DESC]      nvarchar(50),   -- ????????????
   [ST_EXT1]      nvarchar(50),   -- ????????????1
   [ST_EXT2]      nvarchar(50),   -- ????????????2
   primary key ([ST_ROLE_ID])
);

execute sp_addextendedproperty 'MS_Description','?????????????????????SMS???', 'user', @CurrentUser, 'table', 'SMS_ROLE'

execute sp_addextendedproperty 'MS_Description','??????ID', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'ST_ROLE_ID'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'ST_ROLE_CODE'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'ST_ROLE_NAME'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','????????????1', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','????????????2', 'user', @CurrentUser, 'table', 'SMS_ROLE', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: ?????????                                           */
/* Table Code: SMS_GROUP                                        */
/*==============================================================*/
create table [SMS_GROUP] (
   [ST_GROUP_ID]   nvarchar(50) not null,   -- ?????????ID
   [ST_GROUP_CODE] nvarchar(50),   -- ???????????????
   [ST_GROUP_NAME] nvarchar(50),   -- ???????????????
   [NM_ORDER]      numeric(5),   -- ????????????
   [DT_CREATE]     datetime,   -- ????????????
   [DT_UPDATE]     datetime,   -- ????????????
   [ST_DESC]       nvarchar(50),   -- ?????????
   [ST_EXT1]       nvarchar(50),   -- ????????????1
   [ST_EXT2]       nvarchar(50),   -- ????????????2
   primary key ([ST_GROUP_ID])
);

execute sp_addextendedproperty 'MS_Description','?????????ID', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'ST_GROUP_ID'

execute sp_addextendedproperty 'MS_Description','???????????????', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'ST_GROUP_CODE'

execute sp_addextendedproperty 'MS_Description','???????????????', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'ST_GROUP_NAME'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','?????????', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','????????????1', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','????????????2', 'user', @CurrentUser, 'table', 'SMS_GROUP', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: ???????????????                                       */
/* Table Code: SMS_MENU                                         */
/*==============================================================*/
create table [SMS_MENU] (
   [ST_MENU_ID]   nvarchar(50) not null,   -- ??????ID
   [ST_MENU_CODE] nvarchar(50),   -- ????????????
   [ST_MENU_NAME] nvarchar(50),   -- ????????????
   [ST_PARENT_ID] nvarchar(50),   -- ???ID
   [ST_URL]       nvarchar(100),   -- URL
   [ST_IMAGE]     nvarchar(50),   -- ??????
   [ST_TARGET]    nvarchar(50),   -- ??????
   [NM_ORDER]     numeric(10),   -- ?????????
   [DT_CREATE]    datetime,   -- ????????????
   [DT_UPDATE]    datetime,   -- ????????????
   [ST_DESC]      nvarchar(50),   -- ????????????
   [ST_EXT1]      nvarchar(50),   -- ????????????1
   [ST_EXT2]      nvarchar(50),   -- ????????????2
   primary key ([ST_MENU_ID])
);

execute sp_addextendedproperty 'MS_Description','??????ID', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_MENU_ID'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_MENU_CODE'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_MENU_NAME'

execute sp_addextendedproperty 'MS_Description','???ID', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_PARENT_ID'

execute sp_addextendedproperty 'MS_Description','URL', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_URL'

execute sp_addextendedproperty 'MS_Description','??????', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_IMAGE'

execute sp_addextendedproperty 'MS_Description','??????', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_TARGET'

execute sp_addextendedproperty 'MS_Description','?????????', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_DESC'

execute sp_addextendedproperty 'MS_Description','????????????1', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','????????????2', 'user', @CurrentUser, 'table', 'SMS_MENU', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: ???????????????                                       */
/* Table Code: SMS_USER_ROLE                                    */
/*==============================================================*/
create table [SMS_USER_ROLE] (
   [ST_ROLE_ID] nvarchar(50) not null,   -- ??????ID
   [ST_USER_ID] nvarchar(50) not null,   -- ??????ID
   [NM_ORDER]   numeric(10),   -- ?????????
   [ST_EXT1]    nvarchar(50),   -- ????????????1
   [ST_EXT2]    nvarchar(50),   -- ????????????2
   primary key ([ST_ROLE_ID], [ST_USER_ID])
);

execute sp_addextendedproperty 'MS_Description','??????ID', 'user', @CurrentUser, 'table', 'SMS_USER_ROLE', 'column', 'ST_ROLE_ID'

execute sp_addextendedproperty 'MS_Description','??????ID', 'user', @CurrentUser, 'table', 'SMS_USER_ROLE', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','?????????', 'user', @CurrentUser, 'table', 'SMS_USER_ROLE', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','????????????1', 'user', @CurrentUser, 'table', 'SMS_USER_ROLE', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','????????????2', 'user', @CurrentUser, 'table', 'SMS_USER_ROLE', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: ???????????????                                       */
/* Table Code: SMS_USER_GROUP                                   */
/*==============================================================*/
create table [SMS_USER_GROUP] (
   [ST_GROUP_ID] nvarchar(50) not null,   -- ?????????ID
   [ST_USER_ID]  nvarchar(50) not null,   -- ??????ID
   [NM_ORDER]    numeric(10),   -- ?????????
   [ST_EXT1]     nvarchar(50),   -- ????????????1
   [ST_EXT2]     nvarchar(50),   -- ????????????2
   primary key ([ST_GROUP_ID], [ST_USER_ID])
);

execute sp_addextendedproperty 'MS_Description','?????????ID', 'user', @CurrentUser, 'table', 'SMS_USER_GROUP', 'column', 'ST_GROUP_ID'

execute sp_addextendedproperty 'MS_Description','??????ID', 'user', @CurrentUser, 'table', 'SMS_USER_GROUP', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','?????????', 'user', @CurrentUser, 'table', 'SMS_USER_GROUP', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','????????????1', 'user', @CurrentUser, 'table', 'SMS_USER_GROUP', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','????????????2', 'user', @CurrentUser, 'table', 'SMS_USER_GROUP', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: ????????????                                         */
/* Table Code: SMS_ROLE_MENU                                    */
/*==============================================================*/
create table [SMS_ROLE_MENU] (
   [ST_ROLE_ID] nvarchar(50) not null,   -- ??????ID
   [ST_MENU_ID] nvarchar(50) not null,   -- ??????ID
   [NM_ORDER]   numeric(10),   -- ?????????
   [ST_EXT1]    nvarchar(50),   -- ????????????1
   [ST_EXT2]    nvarchar(50),   -- ????????????2
   primary key ([ST_ROLE_ID], [ST_MENU_ID])
);

execute sp_addextendedproperty 'MS_Description','??????ID', 'user', @CurrentUser, 'table', 'SMS_ROLE_MENU', 'column', 'ST_ROLE_ID'

execute sp_addextendedproperty 'MS_Description','??????ID', 'user', @CurrentUser, 'table', 'SMS_ROLE_MENU', 'column', 'ST_MENU_ID'

execute sp_addextendedproperty 'MS_Description','?????????', 'user', @CurrentUser, 'table', 'SMS_ROLE_MENU', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','????????????1', 'user', @CurrentUser, 'table', 'SMS_ROLE_MENU', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','????????????2', 'user', @CurrentUser, 'table', 'SMS_ROLE_MENU', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: ????????????                                         */
/* Table Code: SMS_USER_MENU                                    */
/*==============================================================*/
create table [SMS_USER_MENU] (
   [ST_USER_ID] nvarchar(50) not null,   -- ??????ID
   [ST_MENU_ID] nvarchar(50) not null,   -- ??????ID
   [NM_ORDER]   numeric(10),   -- ?????????
   [ST_EXT1]    nvarchar(50),   -- ????????????1
   [ST_EXT2]    nvarchar(50),   -- ????????????2
   primary key ([ST_USER_ID], [ST_MENU_ID])
);

execute sp_addextendedproperty 'MS_Description','??????ID', 'user', @CurrentUser, 'table', 'SMS_USER_MENU', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','??????ID', 'user', @CurrentUser, 'table', 'SMS_USER_MENU', 'column', 'ST_MENU_ID'

execute sp_addextendedproperty 'MS_Description','?????????', 'user', @CurrentUser, 'table', 'SMS_USER_MENU', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','????????????1', 'user', @CurrentUser, 'table', 'SMS_USER_MENU', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','????????????2', 'user', @CurrentUser, 'table', 'SMS_USER_MENU', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: ?????????                                           */
/* Table Code: SMS_GROUP_MENU                                   */
/*==============================================================*/
create table [SMS_GROUP_MENU] (
   [ST_GROUP_ID] nvarchar(50) not null,   -- ?????????ID
   [ST_MENU_ID]  nvarchar(50) not null,   -- ??????ID
   [NM_ORDER]    numeric(10),   -- ?????????
   [ST_EXT1]     nvarchar(50),   -- ????????????1
   [ST_EXT2]     nvarchar(50),   -- ????????????2
   primary key ([ST_GROUP_ID], [ST_MENU_ID])
);

execute sp_addextendedproperty 'MS_Description','?????????ID', 'user', @CurrentUser, 'table', 'SMS_GROUP_MENU', 'column', 'ST_GROUP_ID'

execute sp_addextendedproperty 'MS_Description','??????ID', 'user', @CurrentUser, 'table', 'SMS_GROUP_MENU', 'column', 'ST_MENU_ID'

execute sp_addextendedproperty 'MS_Description','?????????', 'user', @CurrentUser, 'table', 'SMS_GROUP_MENU', 'column', 'NM_ORDER'

execute sp_addextendedproperty 'MS_Description','????????????1', 'user', @CurrentUser, 'table', 'SMS_GROUP_MENU', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','????????????2', 'user', @CurrentUser, 'table', 'SMS_GROUP_MENU', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: ?????????                                           */
/* Table Code: SMS_ATTACHMENT                                   */
/*==============================================================*/
create table [SMS_ATTACHMENT] (
   [ST_ATTACH_ID]     nvarchar(50) not null,   -- ??????
   [ST_LINK_TABLE]    nvarchar(50),   -- ???????????????
   [ST_LINK_ID]       nvarchar(50),   -- ???????????????
   [ST_ATTACH_TYPE]   nvarchar(50),   -- ????????????
   [ST_FILE_NAME]     nvarchar(100),   -- ?????????
   [ST_FILE_SIZE]     nvarchar(50),   -- ????????????
   [CL_CONTENT]       text,   -- ????????????
   [BL_CONTENT]       image,   -- ????????????
   [BL_SMALL_CONTENT] image,   -- ???????????????
   [ST_FILE_TYPE]     nvarchar(10),   -- ????????????
   [DT_CREATE]        datetime,   -- ????????????
   [DT_UPDATE]        datetime,   -- ????????????
   [ST_EXT1]          nvarchar(50),   -- ????????????1
   [ST_EXT2]          nvarchar(50),   -- ????????????2
   primary key ([ST_ATTACH_ID])
);

execute sp_addextendedproperty 'MS_Description','??????', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_ATTACH_ID'

execute sp_addextendedproperty 'MS_Description','???????????????', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_LINK_TABLE'

execute sp_addextendedproperty 'MS_Description','???????????????', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_LINK_ID'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_ATTACH_TYPE'

execute sp_addextendedproperty 'MS_Description','?????????', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_FILE_NAME'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_FILE_SIZE'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'CL_CONTENT'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'BL_CONTENT'

execute sp_addextendedproperty 'MS_Description','???????????????', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'BL_SMALL_CONTENT'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_FILE_TYPE'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'DT_CREATE'

execute sp_addextendedproperty 'MS_Description','????????????', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'DT_UPDATE'

execute sp_addextendedproperty 'MS_Description','????????????1', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_EXT1'

execute sp_addextendedproperty 'MS_Description','????????????2', 'user', @CurrentUser, 'table', 'SMS_ATTACHMENT', 'column', 'ST_EXT2'

/*==============================================================*/
/* Table Name: ????????????????????????                                 */
/* Table Code: SMS_RESOURCE_ACCESS_LIST                         */
/*==============================================================*/
create table [SMS_RESOURCE_ACCESS_LIST] (
   [ST_USER_ID]          nvarchar(50) not null,   -- ??????ID
   [ST_RESOURCE_ID]      nvarchar(50) not null,   -- ??????ID
   [ST_RESOURCE_TYPE_ID] nvarchar(50) not null,   -- ????????????ID
   [ST_UNIQUE_VALUE]     nvarchar(100),   -- ???????????????
   primary key ([ST_USER_ID], [ST_RESOURCE_ID], [ST_RESOURCE_TYPE_ID])
);

execute sp_addextendedproperty 'MS_Description','??????ID', 'user', @CurrentUser, 'table', 'SMS_RESOURCE_ACCESS_LIST', 'column', 'ST_USER_ID'

execute sp_addextendedproperty 'MS_Description','??????ID', 'user', @CurrentUser, 'table', 'SMS_RESOURCE_ACCESS_LIST', 'column', 'ST_RESOURCE_ID'

execute sp_addextendedproperty 'MS_Description','????????????ID', 'user', @CurrentUser, 'table', 'SMS_RESOURCE_ACCESS_LIST', 'column', 'ST_RESOURCE_TYPE_ID'

execute sp_addextendedproperty 'MS_Description','???????????????', 'user', @CurrentUser, 'table', 'SMS_RESOURCE_ACCESS_LIST', 'column', 'ST_UNIQUE_VALUE'

