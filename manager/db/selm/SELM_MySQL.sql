DELIMITER;;

/****************************************************************/
/**************************** Drops *****************************/
/****************************************************************/

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

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

/*==============================================================*/
/* Table Code: SELM_QUERY_HIS                                   */
/*==============================================================*/
drop table if exists `SELM_QUERY_HIS`;

/*==============================================================*/
/* Table Code: SELM_STATISTICS                                  */
/*==============================================================*/
drop table if exists `SELM_STATISTICS`;

/*==============================================================*/
/* Table Code: SELM_STATISTICS_DAY                              */
/*==============================================================*/
drop table if exists `SELM_STATISTICS_DAY`;

/*==============================================================*/
/* Table Code: SELM_CLIENT_STAT_DAY                             */
/*==============================================================*/
drop table if exists `SELM_CLIENT_STAT_DAY`;

/*==============================================================*/
/* Table Code: OAUTH2_CLIENT                                    */
/*==============================================================*/
drop table if exists `OAUTH2_CLIENT`;

/*==============================================================*/
/* Table Code: OAUTH2_CLIENT_ITEM                               */
/*==============================================================*/
drop table if exists `OAUTH2_CLIENT_ITEM`;

/*==============================================================*/
/* Table Code: SELM_ITEM_LOG                                    */
/*==============================================================*/
drop table if exists `SELM_ITEM_LOG`;

/*==============================================================*/
/* Table Code: OAUTH2_CLIENT_DEVICE                             */
/*==============================================================*/
drop table if exists `OAUTH2_CLIENT_DEVICE`;

/*==============================================================*/
/* Table Code: SELM_DEVICE_ITEM                                 */
/*==============================================================*/
drop table if exists `SELM_DEVICE_ITEM`;

/*==============================================================*/
/* Table Code: SELM_ACCESS_APPLY                                */
/*==============================================================*/
drop table if exists `SELM_ACCESS_APPLY`;

/*==============================================================*/
/* Table Code: SELM_AREA_QUERY_DAY                              */
/*==============================================================*/
drop table if exists `SELM_AREA_QUERY_DAY`;

/*==============================================================*/
/* Table Code: SELM_SERVER_APPLY                                */
/*==============================================================*/
drop table if exists `SELM_SERVER_APPLY`;

/*==============================================================*/
/* Table Code: SELM_SERVER_ITEM                                 */
/*==============================================================*/
drop table if exists `SELM_SERVER_ITEM`;

/*==============================================================*/
/* Table Code: SELM_BIGSCREEN_CACHE                             */
/*==============================================================*/
drop table if exists `SELM_BIGSCREEN_CACHE`;

/*==============================================================*/
/* Table Code: SELM_ASSIST                                      */
/*==============================================================*/
drop table if exists `SELM_ASSIST`;

/*==============================================================*/
/* Table Code: SELM_DEVICE_ASSIST                               */
/*==============================================================*/
drop table if exists `SELM_DEVICE_ASSIST`;

/*==============================================================*/
/* Table Code: SELM_DEVICE_APPLY                                */
/*==============================================================*/
drop table if exists `SELM_DEVICE_APPLY`;

/*==============================================================*/
/* Table Code: SELM_DEVICE_ALINK                                */
/*==============================================================*/
drop table if exists `SELM_DEVICE_ALINK`;

/*==============================================================*/
/* Table Code: SELM_SERVER_DLINK                                */
/*==============================================================*/
drop table if exists `SELM_SERVER_DLINK`;

/*==============================================================*/
/* Table Code: SELM_HC_ACCESS                                   */
/*==============================================================*/
drop table if exists `SELM_HC_ACCESS`;

set FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

/****************************************************************/
/************************ Create Tables *************************/
/****************************************************************/

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
   `ST_ORGAN_ID`      varchar(50) comment '所属部门',   -- 所属部门
   `ST_WORK_TYPE`     varchar(50) comment '办理、查询、预约、其他',   -- 事项分类
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

/*==============================================================*/
/* Table Name: 查询历史                                         */
/* Table Code: SELM_QUERY_HIS                                   */
/*==============================================================*/
create table `SELM_QUERY_HIS` (
   `ST_QUERY_HIS_ID` varchar(50) not null comment '历史ID',   -- 历史ID
   `ST_MACHINE_ID`   varchar(50) comment '设备ID',   -- 设备ID
   `ST_ASSIST_ID`    varchar(50) comment '辅助人ID',   -- 辅助人ID
   `ST_MODULE_NAME`  varchar(50) comment '模块名称',   -- 模块名称
   `ST_MODULE_OP`    varchar(50) comment '操作名称',   -- 操作名称
   `ST_NAME`         varchar(50) comment '姓名',   -- 姓名
   `ST_IDENTITY_NO`  varchar(50) comment '证件号',   -- 身份证号
   `ST_MOBILE`       varchar(50) comment '手机号',   -- 手机号
   `DT_CREATE`       datetime comment '创建时间',   -- 创建时间
   `ST_ATTACH_ID1`   varchar(50) comment '附件ID1',   -- 附件ID1
   `ST_ATTACH_ID2`   varchar(50) comment '附件ID2',   -- 附件ID2
   `ST_ATTACH_ID3`   varchar(50) comment '附件ID3',   -- 附件ID3
   `ST_ATTACH_ID4`   varchar(50) comment '附件ID4',   -- 附件ID4
   `ST_EXT1`         varchar(100) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`         varchar(100) comment '扩展字段2',   -- 扩展字段2
   `ST_EXT3`         varchar(100) comment '扩展字段2',   -- 扩展字段3
   `ST_EXT4`         varchar(200) comment '扩展字段2',   -- 扩展字段4
   `ST_EXT5`         varchar(300) comment '扩展字段2',   -- 扩展字段5
   primary key (`ST_QUERY_HIS_ID`)
);

/*==============================================================*/
/* Table Name: 业务表                                           */
/* Table Code: SELM_STATISTICS                                  */
/*==============================================================*/
create table `SELM_STATISTICS` (
   `ST_STATISTICS_ID` varchar(50) not null comment '统计ID',   -- 统计ID
   `ST_NET_FLAG`      varchar(50) comment '业务标识',   -- 业务标识
   `ST_NET_SUB_FLAG`  varchar(50) comment '业务子标识',   -- 业务子标识
   `ST_NAME`          varchar(50) comment '业务名称',   -- 业务名称
   `NM_COUNT`         decimal(8) comment '业务总数',   -- 业务总数
   `NM_ODEVICE`       decimal(1) comment '是否是外设，0：否；1：是',   -- 是否是外设
   `NM_SORT`          decimal(4) comment '排序',   -- 排序
   `ST_EXT1`          varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`          varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_STATISTICS_ID`)
);

/*==============================================================*/
/* Table Name: 业务统计（按天）                                 */
/* Table Code: SELM_STATISTICS_DAY                              */
/*==============================================================*/
create table `SELM_STATISTICS_DAY` (
   `ST_STATISTICS_ID` varchar(50) not null comment '统计ID',   -- 统计ID
   `ST_DATE`          varchar(50) not null comment '日期字符串',   -- 日期字符串
   `NM_COUNT`         decimal(8) comment '业务总数',   -- 业务总数
   `NM_QUERY`         decimal(8) comment '业务查询',   -- 业务查询
   `NM_SUCCESS`       decimal(8) comment '业务成功',   -- 业务成功
   `NM_FAILD`         decimal(8) comment '业务失败',   -- 业务失败
   `DT_TIME`          datetime comment '日期',   -- 日期
   `ST_EXT1`          varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`          varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_STATISTICS_ID`, `ST_DATE`)
);

/*==============================================================*/
/* Table Name: 终端业务统计（按天）                             */
/* Table Code: SELM_CLIENT_STAT_DAY                             */
/*==============================================================*/
create table `SELM_CLIENT_STAT_DAY` (
   `ST_STATISTICS_ID` varchar(50) not null comment '统计ID',   -- 统计ID
   `ST_DATE`          varchar(50) not null comment '日期字符串',   -- 日期字符串
   `ST_MACHINE_ID`    varchar(50) not null comment '设备ID',   -- 设备ID
   `NM_COUNT`         decimal(8) comment '业务总数',   -- 业务总数
   `NM_QUERY`         decimal(8) comment '业务查询',   -- 业务查询
   `NM_SUCCESS`       decimal(8) comment '业务成功',   -- 业务成功
   `NM_FAILD`         decimal(8) comment '业务失败',   -- 业务失败
   `DT_TIME`          datetime comment '日期',   -- 日期
   `ST_EXT1`          varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`          varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_STATISTICS_ID`, `ST_DATE`, `ST_MACHINE_ID`)
);

/*==============================================================*/
/* Table Name: OAUTH2认证客户端                                 */
/* Table Code: OAUTH2_CLIENT                                    */
/*==============================================================*/
create table `OAUTH2_CLIENT` (
   `ST_OAUTH2_ID`      varchar(50) not null comment '认证客户端ID',   -- 认证客户端ID
   `ST_INTERFACE_USER` varchar(50) comment '接口用户名',   -- 接口用户名
   `ST_INTERFACE_PWD`  varchar(50) comment '接口密码',   -- 接口密码
   `ST_CLIENT_NAME`    varchar(50) comment '客户端名称',   -- 客户端名称
   `ST_CLIENT_ID`      varchar(50) comment '客户端ID',   -- 客户端ID
   `ST_CLIENT_SECRET`  varchar(50) comment '客户端安全KEY',   -- 客户端安全KEY
   `ST_DESC`           varchar(100) comment '备注',   -- 备注
   primary key (`ST_OAUTH2_ID`)
);

/*==============================================================*/
/* Table Name: 授权事项                                         */
/* Table Code: OAUTH2_CLIENT_ITEM                               */
/*==============================================================*/
create table `OAUTH2_CLIENT_ITEM` (
   `ST_OAUTH2_ID` varchar(50) not null comment '认证客户端ID',   -- 认证客户端ID
   `ST_ITEM_ID`   varchar(50) not null comment '事项ID',   -- 事项ID
   `ST_DEVICE_ID` varchar(50) not null comment '设备ID',   -- 设备ID
   `NM_STATUS`    decimal(1) comment '状态，0：删除；1：注册；2：正常；3：禁用；',   -- 状态
   `NM_ORDER`     decimal(4) comment '排序',   -- 排序
   `DT_CREATE`    datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`    datetime comment '更新时间',   -- 更新时间
   primary key (`ST_OAUTH2_ID`, `ST_ITEM_ID`, `ST_DEVICE_ID`)
);

/*==============================================================*/
/* Table Name: 访问日志                                         */
/* Table Code: SELM_ITEM_LOG                                    */
/*==============================================================*/
create table `SELM_ITEM_LOG` (
   `ST_ITEM_LOG_ID` varchar(50) not null comment '日志ID',   -- 日志ID
   `ST_OAUTH2_ID`   varchar(50) comment '认证客户端ID',   -- 认证客户端ID
   `ST_ITEM_ID`     varchar(50) comment '事项ID',   -- 事项ID
   `ST_ITEM_NAME`   varchar(200) comment '事项名称',   -- 事项名称
   `ST_CLIENT_NAME` varchar(50) comment '客户端名称',   -- 客户端名称
   `DT_CREATE`      datetime comment '创建时间',   -- 创建时间
   `ST_EXT1`        varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`        varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_ITEM_LOG_ID`)
);

/*==============================================================*/
/* Table Name: 客户端关联设备                                   */
/* Table Code: OAUTH2_CLIENT_DEVICE                             */
/*==============================================================*/
create table `OAUTH2_CLIENT_DEVICE` (
   `ST_OAUTH2_ID` varchar(50) not null comment '认证客户端ID',   -- 认证客户端ID
   `ST_DEVICE_ID` varchar(50) not null comment '设备ID',   -- 设备ID
   `NM_ORDER`     decimal(4) comment '排序',   -- 排序
   `DT_CREATE`    datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`    datetime comment '更新时间',   -- 更新时间
   primary key (`ST_OAUTH2_ID`, `ST_DEVICE_ID`)
);

/*==============================================================*/
/* Table Name: 设备关联事项                                     */
/* Table Code: SELM_DEVICE_ITEM                                 */
/*==============================================================*/
create table `SELM_DEVICE_ITEM` (
   `ST_ITEM_ID`   varchar(50) not null comment '事项ID',   -- 事项ID
   `ST_DEVICE_ID` varchar(50) not null comment '设备ID',   -- 设备ID
   `NM_STATUS`    decimal(1) comment '状态，0：删除；1：注册；2：正常；3：禁用；',   -- 状态
   `NM_ORDER`     decimal(4) comment '排序',   -- 排序
   `DT_CREATE`    datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`    datetime comment '更新时间',   -- 更新时间
   primary key (`ST_ITEM_ID`, `ST_DEVICE_ID`)
);

/*==============================================================*/
/* Table Name: 接入申请                                         */
/* Table Code: SELM_ACCESS_APPLY                                */
/*==============================================================*/
create table `SELM_ACCESS_APPLY` (
   `ST_ACCESS_APPLY_ID` varchar(50) not null comment '申请ID',   -- 申请ID
   `ST_APPLY_TITLE`     varchar(200) comment '申请标题',   -- 申请标题
   `ST_APPLY_CONTENT`   varchar(2000) comment '申请内容',   -- 申请内容
   `ST_ATTACH_ID`       varchar(50) comment '附件ID，关联附件表',   -- 附件ID
   `NM_STATUS`          decimal(1) comment '状态，0：已保存；1：已提交；2：已修改；3：审核通过；4：审核不通过；5：终止',   -- 状态
   `ST_APPLY_USER_ID`   varchar(50) comment '申请人ID',   -- 申请人ID
   `ST_APPLY_USER_NAME` varchar(50) comment '申请人姓名',   -- 申请人姓名
   `DT_APPLY`           datetime comment '申请时间',   -- 申请时间
   `ST_AUDIT_USER_ID`   varchar(50) comment '审核人ID',   -- 审核人ID
   `ST_AUDIT_USER_NAME` varchar(50) comment '审核人姓名',   -- 审核人姓名
   `DT_AUDIT`           datetime comment '审核时间',   -- 审核时间
   `DT_CREATE`          datetime comment '创建时间',   -- 创建时间
   `ST_DESC`            varchar(100) comment '备注',   -- 备注
   `ST_EXT1`            varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`            varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_ACCESS_APPLY_ID`)
);

/*==============================================================*/
/* Table Name: 区域日办件统计表                                 */
/* Table Code: SELM_AREA_QUERY_DAY                              */
/*==============================================================*/
create table `SELM_AREA_QUERY_DAY` (
   `ST_AREA_ID`       varchar(50) not null comment '区域标识',   -- 区域标识
   `ST_DAY`           varchar(50) not null comment '天，yyyy-MM-dd',   -- 天
   `ST_AREA_NAME`     varchar(50) comment '区域名称',   -- 区域名称
   `NM_GOV_NUMBER`    decimal(18) comment '政务服务自助终端数量',   -- 政务服务自助终端数量
   `NM_SOCIAL_NUMBER` decimal(18) comment '社会化自助终端数量',   -- 社会化自助终端数量
   `NM_DAY`           decimal(18) comment '日办件数量',   -- 日办件数量
   primary key (`ST_AREA_ID`, `ST_DAY`)
);

/*==============================================================*/
/* Table Name: 服务开通申请                                     */
/* Table Code: SELM_SERVER_APPLY                                */
/*==============================================================*/
create table `SELM_SERVER_APPLY` (
   `ST_APPLY_ID`           varchar(50) not null comment '申请ID',   -- 申请ID
   `ST_APPLY_ORGAN_ID`     varchar(50) comment '申请单位ID',   -- 申请单位ID
   `ST_APPLY_ORGAN_NAME`   varchar(50) comment '申请单位名称',   -- 申请单位名称
   `NM_STATUS`             decimal(1) comment '状态，0：已保存；1：全部通过：2：部分通过；3：全部不通过',   -- 状态
   `ST_SERVER_USER_NAME`   varchar(50) comment '联系人',   -- 联系人
   `ST_SERVER_USER_PHONE`  varchar(50) comment '手机',   -- 手机
   `ST_SERVER_USER_MOBILE` varchar(50) comment '固定电话',   -- 固定电话
   `ST_SERVER_USER_EMAIL`  varchar(50) comment '电子邮箱',   -- 电子邮箱
   `ST_SERVER_CONTENT`     varchar(500) comment '申请情况说明',   -- 申请情况说明
   `DT_UP_CREATE`          datetime comment '计划上线时间',   -- 计划上线时间
   `DT_CREATE`             datetime comment '创建时间',   -- 创建时间
   `ST_SERVER_DESTRICT`    varchar(50) comment '所在区',   -- 所在区
   `ST_POINT_NAME`         varchar(50) comment '点位名称',   -- 点位名称
   `ST_PUT_ADDRESS`        varchar(100) comment '摆放地址',   -- 摆放地址
   `ST_BUILD_COMPANY`      varchar(50) comment '承建厂商',   -- 承建厂商
   `ST_PUT_NUMBER`         varchar(50) comment '预计摆放台数',   -- 预计摆放台数
   `NM_NETWORK`            decimal(1) comment '现场网络环境 0：政务外网 1：互联网',   -- 现场网络环境
   `ST_WATCH_OVER`         decimal(1) comment '现场有无值守0：有 1：无',   -- 现场有无值守
   `ST_ATTACH_ID`          varchar(50) comment '附件ID，关联附件表',   -- 附件ID
   `ST_RESULT`             varchar(1000) comment '事项审批结果',   -- 事项审批结果
   `NM_UPDATE`             decimal(1) comment '是否可修改，0：否；1：是',   -- 是否可修改
   `ST_EXT1`               varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`               varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_APPLY_ID`)
);

/*==============================================================*/
/* Table Name: 服务（设备）关联事项                             */
/* Table Code: SELM_SERVER_ITEM                                 */
/*==============================================================*/
create table `SELM_SERVER_ITEM` (
   `ST_LINKS_ID`        varchar(50) not null comment '关联ID',   -- 关联ID
   `ST_APPLY_ID`        varchar(50) comment '申请ID',   -- 申请ID
   `ST_DEVICE_ID`       varchar(50) comment '设备ID',   -- 设备ID
   `ST_ITEM_TYPE_ID`    varchar(50) comment '事项类别ID',   -- 事项类别ID
   `ST_ITEM_ID`         varchar(50) comment '事项ID',   -- 事项ID
   `ST_ITEM_NO`         varchar(50) comment '事项编码',   -- 事项编码
   `ST_ITEM_NAME`       varchar(200) comment '事项名称',   -- 事项名称
   `ST_ORGAN_ID`        varchar(50) comment '所属部门',   -- 所属部门
   `NM_PASS`            decimal(1) comment '是否通过，0：否；1：是',   -- 是否通过
   `NM_STATUS`          decimal(1) comment '状态，0：已保存；1：已提交；2：已修改；3：审核通过；4：审核不通过；5：终止',   -- 状态
   `ST_REASON`          varchar(100) comment '批注原因',   -- 批注原因
   `NM_TYPE`            decimal(1) comment '关联类别，0：事项；1：组别；2：设备事项',   -- 关联类别
   `ST_AUDIT_USER_ID`   varchar(50) comment '审核人ID',   -- 审核人ID
   `ST_AUDIT_USER_NAME` varchar(50) comment '审核人姓名',   -- 审核人姓名
   `DT_AUDIT`           datetime comment '审核时间',   -- 审核时间
   primary key (`ST_LINKS_ID`)
);

/*==============================================================*/
/* Table Name: 大屏统计缓存表                                   */
/* Table Code: SELM_BIGSCREEN_CACHE                             */
/*==============================================================*/
create table `SELM_BIGSCREEN_CACHE` (
   `ST_BIGSCREEN_CACHE_ID` varchar(50) not null comment '缓存ID',   -- 缓存ID
   `ST_FRAME`              varchar(50) comment '框架标识',   -- 框架标识
   `ST_FCODE`              varchar(50) comment '一级标识，页面标识',   -- 一级标识
   `ST_SCODE`              varchar(50) comment '二级标识，页面功能模块',   -- 二级标识
   `ST_TCODE`              varchar(50) comment '三级标识，页面子功能模块',   -- 三级标识
   `ST_JSON`               varchar(5000) comment 'JSON数据',   -- JSON数据
   `ST_CLOB_ID`            varchar(50) comment '超大数据，关联附件表',   -- 超大数据
   `NM_ORDER`              decimal(4) comment '排序',   -- 排序
   `DT_CREATE`             datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`             datetime comment '修改时间',   -- 修改时间
   `ST_EXT1`               varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`               varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_BIGSCREEN_CACHE_ID`)
);

/*==============================================================*/
/* Table Name: 设备辅助人员                                     */
/* Table Code: SELM_ASSIST                                      */
/*==============================================================*/
create table `SELM_ASSIST` (
   `ST_ASSIST_ID`     varchar(50) not null comment '辅助人ID',   -- 辅助人ID
   `ST_ASSIST_NAME`   varchar(50) comment '辅助人姓名',   -- 辅助人姓名
   `ST_ASSIST_PHONE`  varchar(50) comment '辅助人手机号',   -- 辅助人手机号
   `ST_ASSIST_IDCARD` varchar(50) comment '辅助人身份证号',   -- 辅助人身份证号
   `NM_ORDER`         decimal(1) comment '排序',   -- 排序
   `DT_CREATE`        datetime comment '创建时间',   -- 创建时间
   `DT_UPADTE`        datetime comment '修改时间',   -- 修改时间
   `ST_EXT1`          varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`          varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_ASSIST_ID`)
);

/*==============================================================*/
/* Table Name: 设备关联人员                                     */
/* Table Code: SELM_DEVICE_ASSIST                               */
/*==============================================================*/
create table `SELM_DEVICE_ASSIST` (
   `ST_ASSIST_ID` varchar(50) not null comment '辅助人ID',   -- 辅助人ID
   `ST_DEVICE_ID` varchar(50) not null comment '设备ID',   -- 设备ID
   `NM_ORDER`     decimal(8) comment '排序',   -- 排序
   primary key (`ST_ASSIST_ID`, `ST_DEVICE_ID`)
);

/*==============================================================*/
/* Table Name: 设备接入申请                                     */
/* Table Code: SELM_DEVICE_APPLY                                */
/*==============================================================*/
create table `SELM_DEVICE_APPLY` (
   `ST_DEVICE_APPLY_ID`   varchar(50) not null comment '申请ID',   -- 申请ID
   `ST_DAPPLY_NO`         varchar(50) comment '申请单号',   -- 申请单号
   `ST_APPLY_ORGAN_ID`    varchar(50) comment '申请单位ID',   -- 申请单位ID
   `ST_APPLY_ORGAN_NAME`  varchar(50) comment '申请单位名称',   -- 申请单位名称
   `ST_MAIN_ORG_ID`       varchar(50) comment '保障部门ID',   -- 保障部门ID
   `ST_MAIN_ORG_NAME`     varchar(50) comment '保障部门名称',   -- 保障部门名称
   `NM_STATUS`            decimal(1) comment '状态，0：已保存；1：全部通过：2：部分通过；3：全部不通过',   -- 状态
   `ST_APPLY_USER_NAME`   varchar(50) comment '联系人',   -- 联系人
   `ST_APPLY_USER_PHONE`  varchar(50) comment '手机',   -- 手机
   `ST_APPLY_USER_MOBILE` varchar(50) comment '固定电话',   -- 固定电话
   `ST_APPLY_USER_EMAIL`  varchar(50) comment '电子邮箱',   -- 电子邮箱
   `ST_DESC`              varchar(500) comment '情况说明',   -- 情况说明
   `DT_PLAN_CREATE`       datetime comment '计划接入时间',   -- 计划接入时间
   `ST_APPLY_USER_ID`     varchar(50) comment '申请人ID',   -- 申请人ID
   `ST_APPLY_USER_NAME2`  varchar(50) comment '申请人姓名',   -- 申请人姓名
   `DT_CREATE`            datetime comment '创建时间',   -- 创建时间
   `ST_EXT1`              varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`              varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_DEVICE_APPLY_ID`)
);

/*==============================================================*/
/* Table Name: 接入申请关联设备                                 */
/* Table Code: SELM_DEVICE_ALINK                                */
/*==============================================================*/
create table `SELM_DEVICE_ALINK` (
   `ST_DEVICE_APPLY_ID` varchar(50) not null comment '申请ID',   -- 申请ID
   `ST_MACHINE_ID`      varchar(50) not null comment '设备ID',   -- 设备ID
   `ST_DEVICE_NAME`     varchar(100) comment '设备名称',   -- 设备名称
   `ST_DEVICE_CODE`     varchar(50) comment '设备编号',   -- 设备编号
   `ST_DEVICE_IP`       varchar(50) comment '设备IP',   -- 设备IP
   `ST_DEVICE_MAC`      varchar(50) comment '设备MAC',   -- 设备MAC
   `ST_DEVICE_ADDRESS`  varchar(100) comment '设备详细地址',   -- 设备详细地址
   `ST_AREA_ID`         varchar(50) comment '区域ID',   -- 区域ID
   `ST_USER_ID`         varchar(50) comment '用户ID',   -- 用户ID
   `ST_ADDRESS_ID`      varchar(50) comment '地址ID',   -- 地址ID
   `ST_ORGAN_ID`        varchar(50) comment '组织机构ID',   -- 组织机构ID
   `ST_CERT_KEY`        varchar(50) comment '证书唯一标识',   -- 证书唯一标识
   `ST_TYPE_ID`         varchar(50) comment '类型ID',   -- 类型ID
   `NM_IS_HOST`         decimal(1) comment '是否是主机',   -- 是否是主机
   `NM_YBZC`            decimal(1) comment '是否有医保制册机，0：否；1：是',   -- 是否有医保制册机
   `NM_GPY`             decimal(1) comment '是否有高拍仪，0：否；1：是',   -- 是否有高拍仪
   `NM_JZZQZ`           decimal(1) comment '是否有居住证签注机，0：否；1：是',   -- 是否有居住证签注机
   `NM_JZZZK`           decimal(1) comment '是否有居住证制卡机，0：否；1：是',   -- 是否有居住证制卡机
   `ST_NETWORK`         varchar(50) comment '网络情况，互联网  政务网 ',   -- 网络情况
   `NM_DUTY`            decimal(1) comment '是否有人员值守',   -- 是否有人员值守
   `NM_STATUS`          decimal(1) comment '状态，0：已保存；1：已提交；2：已修改；3：审核通过；4：审核不通过；5：终止',   -- 状态
   `ST_REASON`          varchar(100) comment '批注原因',   -- 批注原因
   `ST_AUDIT_USER_ID`   varchar(50) comment '审核人ID',   -- 审核人ID
   `ST_AUDIT_USER_NAME` varchar(50) comment '审核人姓名',   -- 审核人姓名
   `DT_AUDIT`           datetime comment '审核时间',   -- 审核时间
   `DT_CREATE`          datetime comment '创建时间',   -- 创建时间
   `ST_DESC`            varchar(100) comment '备注',   -- 备注
   `ST_EXT1`            varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`            varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_DEVICE_APPLY_ID`, `ST_MACHINE_ID`)
);

/*==============================================================*/
/* Table Name: 服务关联设备                                     */
/* Table Code: SELM_SERVER_DLINK                                */
/*==============================================================*/
create table `SELM_SERVER_DLINK` (
   `ST_APPLY_ID`        varchar(50) not null comment '申请ID',   -- 申请ID
   `ST_MACHINE_ID`      varchar(50) not null comment '设备ID',   -- 设备ID
   `NM_STATUS`          decimal(1) comment '状态，0：已保存；1：已提交；2：已修改；3：审核通过；4：审核不通过；5：终止',   -- 状态
   `ST_REASON`          varchar(100) comment '批注原因',   -- 批注原因
   `ST_AUDIT_USER_ID`   varchar(50) comment '审核人ID',   -- 审核人ID
   `ST_AUDIT_USER_NAME` varchar(50) comment '审核人姓名',   -- 审核人姓名
   `DT_AUDIT`           datetime comment '审核时间',   -- 审核时间
   `DT_CREATE`          datetime comment '创建时间',   -- 创建时间
   `ST_DESC`            varchar(100) comment '备注',   -- 备注
   `ST_EXT1`            varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`            varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_APPLY_ID`, `ST_MACHINE_ID`)
);

/*==============================================================*/
/* Table Name: 健康码出入表                                     */
/* Table Code: SELM_HC_ACCESS                                   */
/*==============================================================*/
create table `SELM_HC_ACCESS` (
   `ST_ACCESS_ID`      varchar(50) not null comment '访客ID',   -- 访客ID
   `ST_AREA_ID`        varchar(50) comment '区域ID',   -- 区域ID
   `ST_ADDRESS_ID`     varchar(50) comment '地址ID',   -- 地址ID
   `ST_ORGAN_ID`       varchar(50) comment '组织机构ID',   -- 组织机构ID
   `ST_VUID`           varchar(50) comment '访客用户ID',   -- 访客用户ID
   `ST_DEVICE_ADDRESS` varchar(100) comment '设备详细地址',   -- 设备详细地址
   `ST_VISITOR_NAME`   varchar(50) comment '访客姓名',   -- 访客姓名
   `ST_VISITOR_PHONE`  varchar(50) comment '访客手机号',   -- 访客手机号
   `ST_VISITOR_IDCARD` varchar(50) comment '访客身份证号',   -- 访客身份证号
   `ST_CARD_ID`        varchar(50) comment '身份证照片',   -- 身份证照片
   `ST_PHOTO_ID`       varchar(50) comment '访客照片',   -- 访客照片
   `DT_INTER`          datetime comment '进入时间',   -- 进入时间
   `DT_OUTER`          datetime comment '出去时间',   -- 出去时间
   `ST_HSE`            varchar(50) comment '健康信息',   -- 健康信息
   `ST_TP`             varchar(50) comment '体温',   -- 体温
   `NM_TYPE`           decimal(1) comment '类型，0：离线码；1：健康码',   -- 类型
   `ST_CREATOR`        varchar(50) comment '创建人ID',   -- 创建人
   `ST_CREATOR_NAME`   varchar(50) comment '中文名',   -- 创建人姓名
   `DT_CREATE`         datetime comment '创建时间',   -- 创建时间
   `ST_MODIFIER`       varchar(50) comment '最后修改人ID',   -- 最后修改人
   `ST_MODIFIER_NAME`  varchar(50) comment '中文名',   -- 最后修改人姓名
   `DT_MODIFIE`        datetime comment '最后修改时间',   -- 最后修改时间
   `ST_EXT1`           varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`           varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_ACCESS_ID`)
);

;;
DELIMITER;