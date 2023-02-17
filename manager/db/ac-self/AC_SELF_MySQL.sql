DELIMITER;;

/****************************************************************/
/**************************** Drops *****************************/
/****************************************************************/

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

/*==============================================================*/
/* Table Code: SMS_USER                                         */
/*==============================================================*/
drop table if exists `SMS_USER`;

/*==============================================================*/
/* Table Code: SMS_ORGAN                                        */
/*==============================================================*/
drop table if exists `SMS_ORGAN`;

/*==============================================================*/
/* Table Code: SMS_ROLE                                         */
/*==============================================================*/
drop table if exists `SMS_ROLE`;

/*==============================================================*/
/* Table Code: SMS_GROUP                                        */
/*==============================================================*/
drop table if exists `SMS_GROUP`;

/*==============================================================*/
/* Table Code: SMS_MENU                                         */
/*==============================================================*/
drop table if exists `SMS_MENU`;

/*==============================================================*/
/* Table Code: SMS_USER_ROLE                                    */
/*==============================================================*/
drop table if exists `SMS_USER_ROLE`;

/*==============================================================*/
/* Table Code: SMS_USER_GROUP                                   */
/*==============================================================*/
drop table if exists `SMS_USER_GROUP`;

/*==============================================================*/
/* Table Code: SMS_ROLE_MENU                                    */
/*==============================================================*/
drop table if exists `SMS_ROLE_MENU`;

/*==============================================================*/
/* Table Code: SMS_USER_MENU                                    */
/*==============================================================*/
drop table if exists `SMS_USER_MENU`;

/*==============================================================*/
/* Table Code: SMS_GROUP_MENU                                   */
/*==============================================================*/
drop table if exists `SMS_GROUP_MENU`;

/*==============================================================*/
/* Table Code: SMS_ATTACHMENT                                   */
/*==============================================================*/
drop table if exists `SMS_ATTACHMENT`;

/*==============================================================*/
/* Table Code: SMS_RESOURCE_ACCESS_LIST                         */
/*==============================================================*/
drop table if exists `SMS_RESOURCE_ACCESS_LIST`;

set FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

/****************************************************************/
/************************ Create Tables *************************/
/****************************************************************/

/*==============================================================*/
/* Table Name: 用户表                                           */
/* Table Code: SMS_USER                                         */
/*==============================================================*/
create table `SMS_USER` (
   `ST_USER_ID`       varchar(50) not null comment '用户ID',   -- 用户ID
   `ST_LOGIN_NAME`    varchar(50) comment '登录名',   -- 登录名
   `ST_USER_CODE`     varchar(50) comment '工号',   -- 工号
   `ST_USER_NAME`     varchar(50) comment '姓名',   -- 姓名
   `ST_PASSWORD`      varchar(50) comment '密码',   -- 密码
   `ST_PINYIN`        varchar(50) comment '拼音',   -- 拼音
   `ST_ORGAN_ID`      varchar(50) comment '所属部门',   -- 所属部门
   `ST_AREA_ID`       varchar(50) comment '区域ID',   -- 区域ID
   `ST_EMAIL`         varchar(50) comment '邮箱',   -- 邮箱
   `ST_MOBILE`        varchar(50) comment '手机',   -- 手机
   `NM_RECEIVE_EMAIL` decimal(1) comment '是否接收系统邮件；0：不接收，1：接收',   -- 是否接收系统邮件
   `ST_THEME_NAME`    varchar(50) comment '界面主题',   -- 界面主题
   `NM_LOCKED`        decimal(1) comment '账号是否被锁定；0：否，1：是',   -- 账号是否被锁定
   `ST_SALT`          varchar(50) comment '加密盐',   -- 加密盐
   `ST_EXT_ID`        varchar(50) comment '关联拓展用户',   -- 关联拓展用户表
   `DT_CREATE`        datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`        datetime comment '修改时间',   -- 修改时间
   `ST_EXT1`          varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`          varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_USER_ID`)
);

alter table `SMS_USER` comment '用户管理体系（SMS）';

/*==============================================================*/
/* Table Name: 组织机构表                                       */
/* Table Code: SMS_ORGAN                                        */
/*==============================================================*/
create table `SMS_ORGAN` (
   `ST_ORGAN_ID`   varchar(50) not null comment '组织机构ID',   -- 组织机构ID
   `ST_PARENT_ID`  varchar(50) comment '父ID',   -- 父ID
   `ST_ORGAN_CODE` varchar(50) comment '机构代码',   -- 机构代码
   `ST_ORGAN_NAME` varchar(50) comment '机构名称',   -- 机构名称
   `NM_ORDER`      decimal(5) comment '排序字段',   -- 排序字段
   `DT_CREATE`     datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`     datetime comment '修改时间',   -- 修改时间
   `ST_DESC`       varchar(50) comment '组织描述',   -- 组织描述
   `ST_EXT1`       varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`       varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_ORGAN_ID`)
);

alter table `SMS_ORGAN` comment '用户管理体系（SMS）';

/*==============================================================*/
/* Table Name: 角色表                                           */
/* Table Code: SMS_ROLE                                         */
/*==============================================================*/
create table `SMS_ROLE` (
   `ST_ROLE_ID`   varchar(50) not null comment '角色ID',   -- 角色ID
   `ST_ROLE_CODE` varchar(50) comment '角色代码',   -- 角色代码
   `ST_ROLE_NAME` varchar(50) comment '角色名称',   -- 角色名称
   `NM_ORDER`     decimal(5) comment '排序字段',   -- 排序字段
   `DT_CREATE`    datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`    datetime comment '修改时间',   -- 修改时间
   `ST_DESC`      varchar(50) comment '角色描述',   -- 角色描述
   `ST_EXT1`      varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`      varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_ROLE_ID`)
);

alter table `SMS_ROLE` comment '用户管理体系（SMS）';

/*==============================================================*/
/* Table Name: 用户组                                           */
/* Table Code: SMS_GROUP                                        */
/*==============================================================*/
create table `SMS_GROUP` (
   `ST_GROUP_ID`   varchar(50) not null comment '用户组ID',   -- 用户组ID
   `ST_GROUP_CODE` varchar(50) comment '用户组编码',   -- 用户组编码
   `ST_GROUP_NAME` varchar(50) comment '用户组名称',   -- 用户组名称
   `NM_ORDER`      decimal(5) comment '排序字段',   -- 排序字段
   `DT_CREATE`     datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`     datetime comment '修改时间',   -- 修改时间
   `ST_DESC`       varchar(50) comment '组描述',   -- 组描述
   `ST_EXT1`       varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`       varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_GROUP_ID`)
);

/*==============================================================*/
/* Table Name: 系统菜单表                                       */
/* Table Code: SMS_MENU                                         */
/*==============================================================*/
create table `SMS_MENU` (
   `ST_MENU_ID`   varchar(50) not null comment '菜单ID',   -- 菜单ID
   `ST_MENU_CODE` varchar(50) comment '资源编号',   -- 资源编号
   `ST_MENU_NAME` varchar(50) comment '资源名称',   -- 资源名称
   `ST_PARENT_ID` varchar(50) comment '父ID',   -- 父ID
   `ST_URL`       varchar(100) comment 'URL',   -- URL
   `ST_IMAGE`     varchar(50) comment '图标',   -- 图标
   `ST_TARGET`    varchar(50) comment '目标',   -- 目标
   `NM_ORDER`     decimal(10) comment '排序号',   -- 排序号
   `DT_CREATE`    datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`    datetime comment '修改时间',   -- 修改时间
   `ST_DESC`      varchar(50) comment '菜单描述',   -- 菜单描述
   `ST_EXT1`      varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`      varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_MENU_ID`)
);

/*==============================================================*/
/* Table Name: 用户角色表                                       */
/* Table Code: SMS_USER_ROLE                                    */
/*==============================================================*/
create table `SMS_USER_ROLE` (
   `ST_ROLE_ID` varchar(50) not null comment '角色ID',   -- 角色ID
   `ST_USER_ID` varchar(50) not null comment '用户ID',   -- 用户ID
   `NM_ORDER`   decimal(10) comment '排序号',   -- 排序号
   `ST_EXT1`    varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`    varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_ROLE_ID`, `ST_USER_ID`)
);

/*==============================================================*/
/* Table Name: 用户关联组                                       */
/* Table Code: SMS_USER_GROUP                                   */
/*==============================================================*/
create table `SMS_USER_GROUP` (
   `ST_GROUP_ID` varchar(50) not null comment '用户组ID',   -- 用户组ID
   `ST_USER_ID`  varchar(50) not null comment '用户ID',   -- 用户ID
   `NM_ORDER`    decimal(10) comment '排序号',   -- 排序号
   `ST_EXT1`     varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`     varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_GROUP_ID`, `ST_USER_ID`)
);

/*==============================================================*/
/* Table Name: 角色菜单                                         */
/* Table Code: SMS_ROLE_MENU                                    */
/*==============================================================*/
create table `SMS_ROLE_MENU` (
   `ST_ROLE_ID` varchar(50) not null comment '角色ID',   -- 角色ID
   `ST_MENU_ID` varchar(50) not null comment '菜单ID',   -- 菜单ID
   `NM_ORDER`   decimal(10) comment '排序号',   -- 排序号
   `ST_EXT1`    varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`    varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_ROLE_ID`, `ST_MENU_ID`)
);

/*==============================================================*/
/* Table Name: 用户菜单                                         */
/* Table Code: SMS_USER_MENU                                    */
/*==============================================================*/
create table `SMS_USER_MENU` (
   `ST_USER_ID` varchar(50) not null comment '用户ID',   -- 用户ID
   `ST_MENU_ID` varchar(50) not null comment '菜单ID',   -- 菜单ID
   `NM_ORDER`   decimal(10) comment '排序号',   -- 排序号
   `ST_EXT1`    varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`    varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_USER_ID`, `ST_MENU_ID`)
);

/*==============================================================*/
/* Table Name: 组菜单                                           */
/* Table Code: SMS_GROUP_MENU                                   */
/*==============================================================*/
create table `SMS_GROUP_MENU` (
   `ST_GROUP_ID` varchar(50) not null comment '用户组ID',   -- 用户组ID
   `ST_MENU_ID`  varchar(50) not null comment '菜单ID',   -- 菜单ID
   `NM_ORDER`    decimal(10) comment '排序号',   -- 排序号
   `ST_EXT1`     varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`     varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_GROUP_ID`, `ST_MENU_ID`)
);

/*==============================================================*/
/* Table Name: 附件表                                           */
/* Table Code: SMS_ATTACHMENT                                   */
/*==============================================================*/
create table `SMS_ATTACHMENT` (
   `ST_ATTACH_ID`     varchar(50) not null comment '主键',   -- 主键
   `ST_LINK_TABLE`    varchar(50) comment '关联表名称',   -- 关联表名称
   `ST_LINK_ID`       varchar(50) comment '关联主键值',   -- 关联主键值
   `ST_ATTACH_TYPE`   varchar(50) comment '附件类型',   -- 附件类型
   `ST_FILE_NAME`     varchar(100) comment '文件名',   -- 文件名
   `ST_FILE_SIZE`     varchar(50) comment '文件大小',   -- 文件大小
   `CL_CONTENT`       longtext comment '文本内容',   -- 文本内容
   `BL_CONTENT`       longblob comment '文件内容',   -- 文件内容
   `BL_SMALL_CONTENT` longblob comment '图片缩略图',   -- 图片缩略图
   `ST_FILE_TYPE`     varchar(10) comment '文件类型',   -- 文件类型
   `DT_CREATE`        datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`        datetime comment '修改时间',   -- 修改时间
   `ST_EXT1`          varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`          varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_ATTACH_ID`)
);

/*==============================================================*/
/* Table Name: 资源权限访问列表                                 */
/* Table Code: SMS_RESOURCE_ACCESS_LIST                         */
/*==============================================================*/
create table `SMS_RESOURCE_ACCESS_LIST` (
   `ST_USER_ID`          varchar(50) not null comment '用户ID',   -- 用户ID
   `ST_RESOURCE_ID`      varchar(50) not null comment '资源ID',   -- 资源ID
   `ST_RESOURCE_TYPE_ID` varchar(50) not null comment '资源类型ID',   -- 资源类型ID
   `ST_UNIQUE_VALUE`     varchar(100) comment '资源唯一值',   -- 资源唯一值
   primary key (`ST_USER_ID`, `ST_RESOURCE_ID`, `ST_RESOURCE_TYPE_ID`)
);

;;
DELIMITER;