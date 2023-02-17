DELIMITER;;

/****************************************************************/
/**************************** Drops *****************************/
/****************************************************************/

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

/*==============================================================*/
/* Table Code: APIDOC_PROJECT                                   */
/*==============================================================*/
drop table if exists `APIDOC_PROJECT`;

/*==============================================================*/
/* Table Code: APIDOC_MODULE                                    */
/*==============================================================*/
drop table if exists `APIDOC_MODULE`;

/*==============================================================*/
/* Table Code: APIDOC_INTERFACE                                 */
/*==============================================================*/
drop table if exists `APIDOC_INTERFACE`;

/*==============================================================*/
/* Table Code: APIDOC_MOD_INTER                                 */
/*==============================================================*/
drop table if exists `APIDOC_MOD_INTER`;

set FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

/****************************************************************/
/************************ Create Tables *************************/
/****************************************************************/

/*==============================================================*/
/* Table Name: 项目                                             */
/* Table Code: APIDOC_PROJECT                                   */
/*==============================================================*/
create table `APIDOC_PROJECT` (
   `ST_PROJECT_ID`   varchar(50) not null comment '项目ID',   -- 项目ID
   `ST_USER_ID`      varchar(50) comment '用户ID',   -- 用户ID
   `ST_PROJECT_NAME` varchar(50) comment '项目名称',   -- 项目名称
   `NM_ORDER`        decimal(4) comment '排序',   -- 排序号
   `DT_CREATE`       datetime comment '创建时间',   -- 创建时间
   `ST_REMARK`       varchar(100) comment '备注',   -- 备注
   `ST_EXT1`         varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`         varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_PROJECT_ID`)
);

/*==============================================================*/
/* Table Name: 模块                                             */
/* Table Code: APIDOC_MODULE                                    */
/*==============================================================*/
create table `APIDOC_MODULE` (
   `ST_MODULE_ID`   varchar(50) not null comment '模块ID',   -- 模块ID
   `ST_MODULE_NAME` varchar(50) comment '模块名称',   -- 模块名称
   `ST_REMARK`      varchar(500) comment '模块说明',   -- 模块说明
   `ST_PROJECT_ID`  varchar(50) comment '项目ID',   -- 项目ID
   `NM_ORDER`       decimal(4) comment '排序',   -- 排序号
   `ST_PARENT_ID`   varchar(50) comment '父模块ID',   -- 父模块ID
   `DT_CREATE`      datetime comment '创建时间',   -- 创建时间
   `ST_EXT1`        varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`        varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_MODULE_ID`)
);

/*==============================================================*/
/* Table Name: 接口                                             */
/* Table Code: APIDOC_INTERFACE                                 */
/*==============================================================*/
create table `APIDOC_INTERFACE` (
   `ST_INTERFACE_ID`   varchar(50) not null comment '接口ID',   -- 接口ID
   `ST_INTERFACE_NAME` varchar(50) comment '接口名',   -- 接口名
   `ST_URL`            varchar(200) comment 'api链接',   -- 接口链接
   `CL_REMARK`         longtext comment '接口说明',   -- 接口说明
   `ST_METHOD`         varchar(50) comment ' 请求方式',   -- 请求方式
   `CL_REQUEST_RARAM`  longtext comment '请求参数备注',   -- 请求参数说明
   `CL_REQUEST_EXAM`   longtext comment '请求示例',   -- 请求示例
   `CL_RESPONSE_PARAM` longtext comment '返回参数说明',   -- 返回参数说明
   `CL_RESPONSE_EXAM`  longtext comment '返回示例',   -- 返回示例
   `ST_MODULE_ID`      varchar(50) comment '所属模块ID',   -- 所属模块ID
   `NM_STATUS`         decimal(1) comment '是否可用，0：不可用；1：可用；2： 删除',   -- 是否可用
   `NM_ORDER`          decimal(4) comment '排序',   -- 排序号
   `DT_CREATE`         datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`         datetime comment '更新时间',   -- 更新时间
   `NM_VERSION`        decimal(18) comment '版本号',   -- 版本号
   `ST_EXT1`           varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`           varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_INTERFACE_ID`)
);

/*==============================================================*/
/* Table Name: 模块关联接口                                     */
/* Table Code: APIDOC_MOD_INTER                                 */
/*==============================================================*/
create table `APIDOC_MOD_INTER` (
   `ST_MODULE_ID`    varchar(50) not null comment '模块ID',   -- 模块ID
   `ST_INTERFACE_ID` varchar(50) not null comment '接口ID',   -- 接口ID
   `NM_ORDER`        decimal(4) comment '排序',   -- 排序号
   primary key (`ST_MODULE_ID`, `ST_INTERFACE_ID`)
);

;;
DELIMITER;