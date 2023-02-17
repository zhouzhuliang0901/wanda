DELIMITER;;

/****************************************************************/
/**************************** Drops *****************************/
/****************************************************************/

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

/*==============================================================*/
/* Table Code: SELM_MACHINE                                     */
/*==============================================================*/
drop table if exists `SELM_MACHINE`;

/*==============================================================*/
/* Table Code: SELM_DELIVERY                                    */
/*==============================================================*/
drop table if exists `SELM_DELIVERY`;

/*==============================================================*/
/* Table Code: SELM_OPINION                                     */
/*==============================================================*/
drop table if exists `SELM_OPINION`;

/*==============================================================*/
/* Table Code: SELM_DELIVERY_HISTORY                            */
/*==============================================================*/
drop table if exists `SELM_DELIVERY_HISTORY`;

/*==============================================================*/
/* Table Code: SELM_ITEM                                        */
/*==============================================================*/
drop table if exists `SELM_ITEM`;

/*==============================================================*/
/* Table Code: SELM_ATTACH                                      */
/*==============================================================*/
drop table if exists `SELM_ATTACH`;

/*==============================================================*/
/* Table Code: SELM_ITEM_TYPE                                   */
/*==============================================================*/
drop table if exists `SELM_ITEM_TYPE`;

/*==============================================================*/
/* Table Code: SELM_ITEM_LINK                                   */
/*==============================================================*/
drop table if exists `SELM_ITEM_LINK`;

/*==============================================================*/
/* Table Code: SELM_PERSONAL_DOCUMENT                           */
/*==============================================================*/
drop table if exists `SELM_PERSONAL_DOCUMENT`;

set FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

/****************************************************************/
/************************ Create Tables *************************/
/****************************************************************/

/*==============================================================*/
/* Table Name: 自助设备                                         */
/* Table Code: SELM_MACHINE                                     */
/*==============================================================*/
create table `SELM_MACHINE` (
   `ST_MACHINE_ID`      varchar(50) not null comment '设备ID',   -- 设备ID
   `ST_MACHINE_NAME`    varchar(50) comment '设备名称',   -- 设备名称
   `ST_MACHINE_UNIQUE`  varchar(50) comment '设备唯一标识',   -- 设备唯一标识
   `ST_MACHINE_ADDRESS` varchar(100) comment '设备位置',   -- 设备位置
   `ST_DESC`            varchar(100) comment '设备描述',   -- 设备描述
   `NM_TYPE`            decimal(1) comment '设备类型，0：自助机；2：工作台',   -- 设备类型
   `ST_VERSION`         varchar(50) comment '版本，V1.0',   -- 版本
   `ST_FORM_CAT_ID`     varchar(50) comment '取表目录ID',   -- 取表目录ID
   `NM_LNG`             decimal(18,15) comment '经度',   -- 经度
   `NM_LAT`             decimal(18,15) comment '纬度',   -- 纬度
   `NM_ONLINE`          decimal(1) comment '是否在线，0：不在线；1：在线',   -- 是否在线
   `ST_EXT1`            varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`            varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_MACHINE_ID`)
);

/*==============================================================*/
/* Table Name: 快递柜                                           */
/* Table Code: SELM_DELIVERY                                    */
/*==============================================================*/
create table `SELM_DELIVERY` (
   `ST_DELIVERY_ID`     varchar(50) not null comment '快递柜ID',   -- 快递柜ID
   `ST_MACHINE_ID`      varchar(50) comment '设备ID',   -- 设备ID
   `ST_CABINET_NO`      varchar(50) comment '设备柜号',   -- 设备柜号
   `ST_CERT_FLOW_NO`    varchar(50) comment '取证号',   -- 取证号
   `ST_RECEIVER_PHONE`  varchar(50) comment '收件人手机号',   -- 收件人手机号
   `ST_RECEIVER_NAME`   varchar(50) comment '收件人姓名',   -- 收件人姓名
   `ST_RECEIVER_IDCARD` varchar(50) comment '收件人身份证号',   -- 收件人身份证号
   `ST_SENDER_ID`       varchar(50) comment '投件人（用户ID）',   -- 投件人（用户ID）
   `ST_SENDER_NAME`     varchar(50) comment '投件人姓名',   -- 投件人姓名
   `ST_CERT_NAME`       varchar(50) comment '证照名称',   -- 证照名称
   `NM_TYPE`            decimal(1) comment '类型，0：企业；1：个人',   -- 类型
   `ST_APPLY_ID`        varchar(50) comment '关联办件',   -- 关联办件
   `ST_NAME`            varchar(100) comment '企业/个人 名称',   -- 企业/个人 名称
   `NM_STATUS`          decimal(1) comment '状态；0：待存；1：待取；2：已取',   -- 状态
   `ST_RECEIVE_NUM`     varchar(50) comment '取件码',   -- 取件码
   `ST_DESC`            varchar(200) comment '描述',   -- 描述
   `DT_CREATE`          datetime comment '创建时间',   -- 创建时间
   `DT_STORE`           datetime comment '投放时间',   -- 投放时间
   `DT_TAKE`            datetime comment '取走时间',   -- 取走时间
   `ST_EXT1`            varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`            varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_DELIVERY_ID`)
);

/*==============================================================*/
/* Table Name: 意见反馈表                                       */
/* Table Code: SELM_OPINION                                     */
/*==============================================================*/
create table `SELM_OPINION` (
   `ST_OPINION_ID`  varchar(50) not null comment '意见表ID',   -- 意见表ID
   `ST_MACHINE_ID`  varchar(50) comment '设备ID',   -- 设备ID
   `ST_UNAME`       varchar(50) comment '姓名',   -- 姓名
   `ST_PHONE`       varchar(50) comment '手机号',   -- 手机号
   `ST_UNIT`        varchar(100) comment '单位名称',   -- 单位名称
   `ST_CONTENT`     varchar(500) comment '内容',   -- 内容
   `NM_SATISFATION` decimal(1) comment '满意度评价，1：非常满意；2：满意；3：一般；4：不满意',   -- 满意度评价
   `DT_CREATE`      datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`      datetime comment '修改时间',   -- 修改时间
   `ST_EXT1`        varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`        varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_OPINION_ID`)
);

/*==============================================================*/
/* Table Name: 快递柜历史                                       */
/* Table Code: SELM_DELIVERY_HISTORY                            */
/*==============================================================*/
create table `SELM_DELIVERY_HISTORY` (
   `ST_DELIVERY_ID`     varchar(50) not null comment '快递柜ID',   -- 快递柜ID
   `ST_MACHINE_ID`      varchar(50) comment '设备ID',   -- 设备ID
   `ST_CABINET_NO`      varchar(50) comment '设备柜号',   -- 设备柜号
   `ST_CERT_FLOW_NO`    varchar(50) comment '取证号',   -- 取证号
   `ST_RECEIVER_PHONE`  varchar(50) comment '收件人手机号',   -- 收件人手机号
   `ST_RECEIVER_NAME`   varchar(50) comment '收件人姓名',   -- 收件人姓名
   `ST_RECEIVER_IDCARD` varchar(50) comment '收件人身份证号',   -- 收件人身份证号
   `ST_SENDER_ID`       varchar(50) comment '投件人（用户ID）',   -- 投件人（用户ID）
   `ST_SENDER_NAME`     varchar(50) comment '投件人姓名',   -- 投件人姓名
   `ST_CERT_NAME`       varchar(50) comment '证照名称',   -- 证照名称
   `NM_TYPE`            decimal(1) comment '类型，0：企业；1：个人',   -- 类型
   `ST_APPLY_ID`        varchar(50) comment '关联办件',   -- 关联办件
   `ST_NAME`            varchar(100) comment '企业/个人 名称',   -- 企业/个人 名称
   `NM_STATUS`          decimal(1) comment '状态；0：待存；1：待取；2：已取',   -- 状态
   `ST_RECEIVE_NUM`     varchar(50) comment '取件码',   -- 取件码
   `ST_DESC`            varchar(200) comment '描述',   -- 描述
   `DT_CREATE`          datetime comment '创建时间',   -- 创建时间
   `DT_STORE`           datetime comment '投放时间',   -- 投放时间
   `DT_TAKE`            datetime comment '取走时间',   -- 取走时间
   `ST_EXT1`            varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`            varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_DELIVERY_ID`)
);

/*==============================================================*/
/* Table Name: 事项表                                           */
/* Table Code: SELM_ITEM                                        */
/*==============================================================*/
create table `SELM_ITEM` (
   `ST_ITEM_ID`       varchar(50) not null comment '事项ID',   -- 事项ID
   `ST_ITEM_NO`       varchar(50) comment '事项编码',   -- 事项编码
   `ST_TEN_CODE`      varchar(50) comment '其他编码',   -- 其他编码
   `ST_MAIN_NAME`     varchar(200) comment '主名称（主事项）',   -- 主名称（主事项）
   `ST_ITEM_NAME`     varchar(200) comment '事项名称',   -- 事项名称
   `NM_BELONG`        decimal(4) comment '法人或者个人',   -- 事项所属
   `ST_ITEM_TYPE`     varchar(50) comment '审批还是服务事项',   -- 事项类型
   `ST_LEGAL_TIME`    varchar(50) comment '法定时限',   -- 法定时限
   `ST_PROMISE_TIME`  varchar(50) comment '承诺时限',   -- 承诺时限
   `NM_SORT`          decimal(4) comment '排序',   -- 排序
   `ST_ITEM_GUIDE_ID` varchar(50) comment '事项办事指南，关联附件',   -- 事项办事指南
   `NM_TYPE`          decimal(1) comment '类型，0：主事项；2：子事项；3：情形',   -- 类型
   `ST_PARENT_ID`     varchar(50) comment '父事项ID',   -- 父事项ID
   `NM_SHOW_TYPE`     decimal(1) comment '显示类别，0：不展示；1：只做线上；2：只做线下；3：线上线下',   -- 显示类别
   `ST_WORK_URL`      varchar(200) comment '办理跳转链接',   -- 办理跳转链接
   `DT_CREATE`        datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`        datetime comment '更新时间',   -- 更新时间
   `ST_EXT1`          varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`          varchar(50) comment '扩展字段2',   -- 扩展字段2
   `ST_EXT3`          varchar(100) comment '扩展字段2',   -- 扩展字段3
   `ST_EXT4`          varchar(200) comment '扩展字段2',   -- 扩展字段4
   primary key (`ST_ITEM_ID`)
);

/*==============================================================*/
/* Table Name: 附件表                                           */
/* Table Code: SELM_ATTACH                                      */
/*==============================================================*/
create table `SELM_ATTACH` (
   `ST_ATTACH_ID`     varchar(50) not null comment '附件ID',   -- 附件ID
   `ST_LINK_TABLE`    varchar(50) comment '关联表名称',   -- 关联表名称
   `ST_LINK_ID`       varchar(50) comment '关联主键值',   -- 关联主键值
   `ST_ATTACH_TYPE`   varchar(50) comment '附件类型',   -- 附件类型
   `ST_FILENAME`      varchar(100) comment '文件名',   -- 文件名
   `ST_FILE_SIZE`     varchar(50) comment '文件大小',   -- 文件大小
   `CL_CONTENT`       longtext comment '文本内容',   -- 文本内容
   `BL_CONTENT`       longblob comment '文件内容',   -- 文件内容
   `BL_SMALL_CONTENT` longblob comment '图片缩略图',   -- 图片缩略图
   `ST_FILE_TYPE`     varchar(10) comment '文件类型',   -- 文件类型
   `NM_ORDER`         decimal(4) comment '排序',   -- 排序
   `DT_CREATE`        datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`        datetime comment '修改时间',   -- 修改时间
   `ST_EXT1`          varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`          varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_ATTACH_ID`)
);

/*==============================================================*/
/* Table Name: 事项类别                                         */
/* Table Code: SELM_ITEM_TYPE                                   */
/*==============================================================*/
create table `SELM_ITEM_TYPE` (
   `ST_ITEM_TYPE_ID`   varchar(50) not null comment '事项类别ID',   -- 事项类别ID
   `ST_ITEM_TYPE_NAME` varchar(50) comment '事项类别名称',   -- 事项类别名称
   `NM_SORT`           decimal(4) comment '排序',   -- 排序
   `ST_PARENT_ID`      varchar(50) comment '父事项ID',   -- 父事项ID
   `DT_CREATE`         datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`         datetime comment '更新时间',   -- 更新时间
   `ST_EXT1`           varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`           varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_ITEM_TYPE_ID`)
);

/*==============================================================*/
/* Table Name: 类别关联事项                                     */
/* Table Code: SELM_ITEM_LINK                                   */
/*==============================================================*/
create table `SELM_ITEM_LINK` (
   `ST_ITEM_ID`      varchar(50) not null comment '事项ID',   -- 事项ID
   `ST_ITEM_TYPE_ID` varchar(50) not null comment '事项类别ID',   -- 事项类别ID
   `NM_SORT`         decimal(4) comment '排序',   -- 排序
   primary key (`ST_ITEM_ID`, `ST_ITEM_TYPE_ID`)
);

/*==============================================================*/
/* Table Name: 个人档案表                                       */
/* Table Code: SELM_PERSONAL_DOCUMENT                           */
/*==============================================================*/
create table `SELM_PERSONAL_DOCUMENT` (
   `ST_PERSONAL_DOCUMENT` varchar(50) not null comment '档案ID',   -- 档案ID
   `ST_NAME`              varchar(50) comment '姓名',   -- 姓名
   `ST_SEX`               varchar(50) comment '性别',   -- 性别
   `ST_NATION`            varchar(50) comment '民族',   -- 民族
   `ST_BIRTH`             varchar(50) comment '出生年月',   -- 出生年月
   `ST_HOME_ADDRESS`      varchar(100) comment '住址',   -- 住址
   `ST_IDENTITY_NO`       varchar(50) comment '证件号',   -- 身份证号
   `ST_MOBILE`            varchar(50) comment '手机号',   -- 手机号
   `ST_HEAD_IMAGE_ID`     varchar(50) comment '身份证头像ID，关联附件表',   -- 身份证头像ID
   `ST_FRONT_IMAGE_ID`    varchar(50) comment '身份证正面ID，关联附件表',   -- 身份证正面ID
   `ST_BACK_IMAGE_ID`     varchar(50) comment '身份证反面ID，关联附件表',   -- 身份证反面ID
   `DT_CREATE`            datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`            datetime comment '更新时间',   -- 更新时间
   `ST_EXT1`              varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`              varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_PERSONAL_DOCUMENT`)
);

;;
DELIMITER;