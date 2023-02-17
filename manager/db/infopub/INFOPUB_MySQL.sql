DELIMITER;;

/****************************************************************/
/**************************** Drops *****************************/
/****************************************************************/

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

/*==============================================================*/
/* Table Code: INFOPUB_DEVICE_INFO                              */
/*==============================================================*/
drop table if exists `INFOPUB_DEVICE_INFO`;

/*==============================================================*/
/* Table Code: INFOPUB_DEVICE_TYPE                              */
/*==============================================================*/
drop table if exists `INFOPUB_DEVICE_TYPE`;

/*==============================================================*/
/* Table Code: INFOPUB_AREA                                     */
/*==============================================================*/
drop table if exists `INFOPUB_AREA`;

/*==============================================================*/
/* Table Code: INFOPUB_ATTACHMENT                               */
/*==============================================================*/
drop table if exists `INFOPUB_ATTACHMENT`;

/*==============================================================*/
/* Table Code: INFOPUB_ODEVICE_RESULT                           */
/*==============================================================*/
drop table if exists `INFOPUB_ODEVICE_RESULT`;

/*==============================================================*/
/* Table Code: INFOPUB_DEVICE_RESULT                            */
/*==============================================================*/
drop table if exists `INFOPUB_DEVICE_RESULT`;

/*==============================================================*/
/* Table Code: INFOPUB_DEVICE_LOG                               */
/*==============================================================*/
drop table if exists `INFOPUB_DEVICE_LOG`;

/*==============================================================*/
/* Table Code: INFOPUB_IRESOURCE                                */
/*==============================================================*/
drop table if exists `INFOPUB_IRESOURCE`;

/*==============================================================*/
/* Table Code: INFOPUB_GROUP                                    */
/*==============================================================*/
drop table if exists `INFOPUB_GROUP`;

/*==============================================================*/
/* Table Code: INFOPUB_GROUP_DEVICE                             */
/*==============================================================*/
drop table if exists `INFOPUB_GROUP_DEVICE`;

/*==============================================================*/
/* Table Code: INFOPUB_PUBLISH                                  */
/*==============================================================*/
drop table if exists `INFOPUB_PUBLISH`;

/*==============================================================*/
/* Table Code: INFOPUB_PSOURCE                                  */
/*==============================================================*/
drop table if exists `INFOPUB_PSOURCE`;

/*==============================================================*/
/* Table Code: INFOPUB_ONOFF                                    */
/*==============================================================*/
drop table if exists `INFOPUB_ONOFF`;

/*==============================================================*/
/* Table Code: INFOPUB_ODEVICE_STATUS                           */
/*==============================================================*/
drop table if exists `INFOPUB_ODEVICE_STATUS`;

/*==============================================================*/
/* Table Code: INFOPUB_DEVICE_RESULT_HIS                        */
/*==============================================================*/
drop table if exists `INFOPUB_DEVICE_RESULT_HIS`;

/*==============================================================*/
/* Table Code: INFOPUB_PIC_TYPE                                 */
/*==============================================================*/
drop table if exists `INFOPUB_PIC_TYPE`;

/*==============================================================*/
/* Table Code: INFOPUB_PIC                                      */
/*==============================================================*/
drop table if exists `INFOPUB_PIC`;

/*==============================================================*/
/* Table Code: INFOPUB_FUNC                                     */
/*==============================================================*/
drop table if exists `INFOPUB_FUNC`;

/*==============================================================*/
/* Table Code: INFOPUB_FUNC_TYPE                                */
/*==============================================================*/
drop table if exists `INFOPUB_FUNC_TYPE`;

/*==============================================================*/
/* Table Code: INFOPUB_APP                                      */
/*==============================================================*/
drop table if exists `INFOPUB_APP`;

/*==============================================================*/
/* Table Code: INFOPUB_APP_PIC                                  */
/*==============================================================*/
drop table if exists `INFOPUB_APP_PIC`;

/*==============================================================*/
/* Table Code: INFOPUB_APP_FUNC                                 */
/*==============================================================*/
drop table if exists `INFOPUB_APP_FUNC`;

/*==============================================================*/
/* Table Code: INFOPUB_COMPANY                                  */
/*==============================================================*/
drop table if exists `INFOPUB_COMPANY`;

/*==============================================================*/
/* Table Code: INFOPUB_ADDRESS                                  */
/*==============================================================*/
drop table if exists `INFOPUB_ADDRESS`;

set FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

/****************************************************************/
/************************ Create Tables *************************/
/****************************************************************/

/*==============================================================*/
/* Table Name: 设备信息                                         */
/* Table Code: INFOPUB_DEVICE_INFO                              */
/*==============================================================*/
create table `INFOPUB_DEVICE_INFO` (
   `ST_DEVICE_ID`      varchar(50) not null comment '设备ID',   -- 设备ID
   `ST_DEVICE_NAME`    varchar(100) comment '设备名称',   -- 设备名称
   `ST_DEVICE_CODE`    varchar(50) comment '设备编号',   -- 设备编号
   `ST_DEVICE_IP`      varchar(50) comment '设备IP',   -- 设备IP
   `ST_DEVICE_MAC`     varchar(50) comment '设备MAC',   -- 设备MAC
   `ST_DEVICE_ADDRESS` varchar(100) comment '设备详细地址',   -- 设备详细地址
   `ST_TYPE_ID`        varchar(50) not null comment '类型ID',   -- 类型ID
   `NM_IS_HOST`        decimal(1) comment '是否是主机',   -- 是否是主机
   `NM_ORDER`          decimal(8) comment '排序',   -- 排序
   `NM_INTERVAL`       decimal(10) comment '单位为秒',   -- 监控间隔
   `NM_RECOVER`        decimal(1) comment '是否恢复，0：未恢复（异常），1：已恢复（正常）',   -- 是否恢复
   `NM_DOWN_TRY`       decimal(4) comment '故障几次算异常',   -- 故障尝试次数
   `NM_NOTIFICATION`   decimal(1) comment '是否通知',   -- 是否通知
   `NM_LNG`            decimal(18,15) comment '经度',   -- 经度
   `NM_LAT`            decimal(18,15) comment '纬度',   -- 纬度
   `NM_ONLINE`         decimal(1) comment '是否在线，0：不在线；1：在线；',   -- 是否在线
   `ST_CHANNEL`        varchar(50) comment '消息通道',   -- 消息通道
   `ST_CONFIG_ID`      varchar(50) comment '终端配置，关联附件表',   -- 终端配置
   `NM_SDTYPE`         decimal(1) comment '设备类型，0：中心；1：延伸',   -- 设备类型（子类型）
   `ST_USER_ID`        varchar(50) comment '用户ID',   -- 用户ID
   `ST_AREA_ID`        varchar(50) comment '区域ID',   -- 区域ID
   `ST_ADDRESS_ID`     varchar(50) comment '地址ID',   -- 地址ID
   `ST_ORGAN_ID`       varchar(50) comment '组织机构ID',   -- 组织机构ID
   `ST_CERT_KEY`       varchar(50) comment '证书唯一标识',   -- 证书唯一标识
   `DT_CREATE`         datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`         datetime comment '更新时间',   -- 更新时间
   `ST_DESC`           varchar(100) comment '备注',   -- 备注
   primary key (`ST_DEVICE_ID`)
);

/*==============================================================*/
/* Table Name: 设备（外设）分类                                 */
/* Table Code: INFOPUB_DEVICE_TYPE                              */
/*==============================================================*/
create table `INFOPUB_DEVICE_TYPE` (
   `ST_TYPE_ID`        varchar(50) not null comment '类型ID',   -- 类型ID
   `ST_TYPE_NAME`      varchar(50) comment '分类名称',   -- 分类名称
   `ST_TYPE_CODE`      varchar(50) comment '分类代码',   -- 分类代码
   `ST_ICON`           varchar(50) comment '设备图标',   -- 设备图标
   `ST_CLASS`          varchar(50) comment '设备样式',   -- 设备样式
   `ST_COMPANY_ID`     varchar(50) comment '厂商ID',   -- 厂商ID
   `NM_DTYPE`          decimal(1) comment '设备类型，0：政务终端；1：社会化终端',   -- 设备类型
   `ST_PARENT_TYPE_ID` varchar(50) comment '父类型，子类型均为外设',   -- 父类型
   `NM_ORDER`          decimal(8) comment '排序',   -- 排序
   `DT_CREATE`         datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`         datetime comment '更新时间',   -- 更新时间
   `ST_DESC`           varchar(100) comment '备注',   -- 备注
   primary key (`ST_TYPE_ID`)
);

/*==============================================================*/
/* Table Name: 区域                                             */
/* Table Code: INFOPUB_AREA                                     */
/*==============================================================*/
create table `INFOPUB_AREA` (
   `ST_AREA_ID`        varchar(50) not null comment '区域ID',   -- 区域ID
   `ST_AREA_NAME`      varchar(50) comment '区域名称',   -- 区域名称
   `ST_AREA_CODE`      varchar(50) comment '区域代码',   -- 区域代码
   `ST_USER_ID`        varchar(50) comment '管理员',   -- 管理员
   `ST_USER_NAME`      varchar(50) comment '管理员姓名',   -- 管理员姓名
   `ST_PHONE`          varchar(50) comment '联系方式',   -- 联系方式
   `NM_ORDER`          decimal(8) comment '排序',   -- 排序
   `DT_CREATE`         datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`         datetime comment '更新时间',   -- 更新时间
   `ST_PARENT_AREA_ID` varchar(50) comment '父ID',   -- 父ID
   `ST_DESC`           varchar(100) comment '备注',   -- 备注
   primary key (`ST_AREA_ID`)
);

/*==============================================================*/
/* Table Name: 附件表                                           */
/* Table Code: INFOPUB_ATTACHMENT                               */
/*==============================================================*/
create table `INFOPUB_ATTACHMENT` (
   `ST_ATTACH_ID`     varchar(50) not null comment '主键',   -- 主键
   `ST_LINK_TABLE`    varchar(50) comment '关联表名称',   -- 关联表名称
   `ST_LINK_ID`       varchar(50) comment '关联主键值',   -- 关联主键值
   `ST_ATTACH_TYPE`   varchar(50) comment '附件类型',   -- 附件类型
   `ST_FILENAME`      varchar(100) comment '文件名',   -- 文件名
   `ST_FILE_SIZE`     varchar(50) comment '文件大小',   -- 文件大小
   `BL_CONTENT`       longblob comment '文件内容',   -- 文件内容
   `CL_CONTENT`       longtext comment '文本内容',   -- 文本内容
   `BL_SMALL_CONTENT` longblob comment '图片缩略图',   -- 图片缩略图
   `ST_FILE_TYPE`     varchar(10) comment '文件类型',   -- 文件类型
   `DT_CREATE`        datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`        datetime comment '修改时间',   -- 修改时间
   primary key (`ST_ATTACH_ID`)
);

/*==============================================================*/
/* Table Name: 外设状态结果信息                                 */
/* Table Code: INFOPUB_ODEVICE_RESULT                           */
/*==============================================================*/
create table `INFOPUB_ODEVICE_RESULT` (
   `ST_OUT_DEVICE_RESULT_ID` varchar(50) not null comment '外设状态结果ID',   -- 外设状态结果ID
   `ST_DEVICE_ID`            varchar(50) comment '设备ID',   -- 设备ID
   `ST_OUT_DEVICE_CODE`      varchar(50) comment '外设标识',   -- 外设标识
   `NM_EXCEPTION`            decimal(1) comment '是否异常，0：正常；1：异常',   -- 是否异常
   `ST_CAUSE`                varchar(200) comment '异常原因',   -- 异常原因
   `NM_NOTICE`               decimal(1) comment '是否已经通知，0：未通知；1：已通知',   -- 是否已经通知
   `NM_TOTAL`                decimal(8) comment '总量',   -- 总量
   `NM_REMAIN`               decimal(8) comment '剩余量',   -- 剩余量
   `DT_UPDATE`               datetime comment '更新日期',   -- 更新日期
   `ST_EXT1`                 varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`                 varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_OUT_DEVICE_RESULT_ID`)
);

/*==============================================================*/
/* Table Name: 设备状态结果信息                                 */
/* Table Code: INFOPUB_DEVICE_RESULT                            */
/*==============================================================*/
create table `INFOPUB_DEVICE_RESULT` (
   `ST_DEVICE_RESULT_ID` varchar(50) not null comment '设备状态结果ID',   -- 设备状态结果ID
   `ST_DEVICE_ID`        varchar(50) comment '设备ID',   -- 设备ID
   `NM_MEM_USED`         decimal(5,2) comment '内存使用率',   -- 内存使用率
   `NM_CPU_USED`         decimal(5,2) comment 'CPU使用率',   -- CPU使用率
   `CL_HD_USED`          longtext comment '磁盘使用情况',   -- 磁盘使用情况
   `CL_NET_USED`         longtext comment '网络使用情况',   -- 网络使用情况
   `CL_SERVICE_USED`     longtext comment '服务使用情况',   -- 服务使用情况
   `DT_CREATE`           datetime comment '创建日期',   -- 创建日期
   `ST_EXT1`             varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`             varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_DEVICE_RESULT_ID`)
);

/*==============================================================*/
/* Table Name: 设备日志记录                                     */
/* Table Code: INFOPUB_DEVICE_LOG                               */
/*==============================================================*/
create table `INFOPUB_DEVICE_LOG` (
   `ST_DEVICE_LOG_ID` varchar(50) not null comment '设备日志ID',   -- 设备日志ID
   `ST_DEVICE_ID`     varchar(50) comment '设备ID',   -- 设备ID
   `ST_THREAD`        varchar(50) comment '线程号',   -- 线程号
   `ST_LEVEL`         varchar(50) comment '日志级别',   -- 日志级别
   `ST_LOGGER`        varchar(100) comment '日志记录类名称',   -- 日志记录类名称
   `ST_OPERATOR`      varchar(50) comment '操作者',   -- 操作者
   `ST_OPERAND`       varchar(50) comment '操作对象',   -- 操作对象
   `ST_ACTION`        varchar(50) comment '动作类型',   -- 动作类型
   `ST_LOCATION`      varchar(100) comment '记录位置',   -- 记录位置
   `ST_LINE`          varchar(50) comment '行号',   -- 行号
   `ST_METHOD`        varchar(50) comment '方法',   -- 方法
   `ST_MSG`           varchar(1000) comment '日志消息',   -- 日志消息
   `ST_EXCEPTION`     varchar(1000) comment '异常信息',   -- 异常信息
   `DT_CREATE`        datetime comment '创建日期',   -- 创建日期
   `ST_EXT1`          varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`          varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_DEVICE_LOG_ID`)
);

/*==============================================================*/
/* Table Name: 信息资源                                         */
/* Table Code: INFOPUB_IRESOURCE                                */
/*==============================================================*/
create table `INFOPUB_IRESOURCE` (
   `ST_IRESOURCE_ID`   varchar(50) not null comment '资源ID',   -- 资源ID
   `ST_IRESOURCE_NAME` varchar(100) comment '资源名称',   -- 资源名称
   `ST_IRESOURCE_TYPE` varchar(50) comment '资源类型',   -- 资源类型
   `ST_USER_ID`        varchar(50) comment '用户ID',   -- 用户ID
   `DT_CREATE`         datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`         datetime comment '更新时间',   -- 更新时间
   `ST_DESC`           varchar(100) comment '备注',   -- 备注
   primary key (`ST_IRESOURCE_ID`)
);

/*==============================================================*/
/* Table Name: 设备分组                                         */
/* Table Code: INFOPUB_GROUP                                    */
/*==============================================================*/
create table `INFOPUB_GROUP` (
   `ST_GROUP_ID`   varchar(50) not null comment '分组ID',   -- 分组ID
   `ST_GROUP_NAME` varchar(50) comment '设备组名称',   -- 设备组名称
   `ST_USER_ID`    varchar(50) comment '用户ID',   -- 用户ID
   `DT_CREATE`     datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`     datetime comment '更新时间',   -- 更新时间
   `ST_DESC`       varchar(100) comment '备注',   -- 备注
   `ST_AREA_ID`    varchar(50) comment '区域ID',   -- 区域ID
   primary key (`ST_GROUP_ID`)
);

/*==============================================================*/
/* Table Name: 设备组关联                                       */
/* Table Code: INFOPUB_GROUP_DEVICE                             */
/*==============================================================*/
create table `INFOPUB_GROUP_DEVICE` (
   `ST_GROUP_ID`  varchar(50) not null comment '分组ID',   -- 分组ID
   `ST_DEVICE_ID` varchar(50) not null comment '设备ID',   -- 设备ID
   `NM_ORDER`     decimal(8) comment '排序',   -- 排序
   primary key (`ST_GROUP_ID`, `ST_DEVICE_ID`)
);

/*==============================================================*/
/* Table Name: 设备发布                                         */
/* Table Code: INFOPUB_PUBLISH                                  */
/*==============================================================*/
create table `INFOPUB_PUBLISH` (
   `ST_PUBLISH_ID`   varchar(50) not null comment '发布ID',   -- 发布ID
   `ST_DEVICE_ID`    varchar(50) comment '设备ID',   -- 设备ID
   `ST_PSOURCE_ID`   varchar(50) comment '发布源ID',   -- 发布源ID
   `ST_PUBLISH_NAME` varchar(50) comment '发布名称',   -- 发布名称
   `NM_PRIORITY`     decimal(4) comment '优先级',   -- 优先级
   `ST_PTYPE`        varchar(50) comment '类型，WEEK：按周；DAY：按天',   -- 类型
   `DT_PSTART`       datetime comment '发布开始时间',   -- 发布开始时间
   `DT_PEND`         datetime comment '发布结束时间',   -- 发布结束时间
   `ST_PERIOD`       varchar(50) comment '发布周期，固定7个数字，0代表不发布，1代表发布',   -- 发布周期
   `DT_PUBLISH`      datetime comment '发布日期',   -- 发布日期
   `DT_CREATE`       datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`       datetime comment '更新时间',   -- 更新时间
   `ST_DESC`         varchar(100) comment '备注',   -- 备注
   primary key (`ST_PUBLISH_ID`)
);

/*==============================================================*/
/* Table Name: 发布源                                           */
/* Table Code: INFOPUB_PSOURCE                                  */
/*==============================================================*/
create table `INFOPUB_PSOURCE` (
   `ST_PSOURCE_ID`   varchar(50) not null comment '发布源ID',   -- 发布源ID
   `ST_PSOURCE_NAME` varchar(50) comment '发布源名称',   -- 发布源名称
   `NM_OFFLINE`      decimal(1) comment '是否离线，0：否；1：是',   -- 是否离线
   `ST_USER_ID`      varchar(50) comment '用户ID',   -- 用户ID
   `DT_CREATE`       datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`       datetime comment '更新时间',   -- 更新时间
   `ST_DESC`         varchar(100) comment '备注',   -- 备注
   primary key (`ST_PSOURCE_ID`)
);

/*==============================================================*/
/* Table Name: 设备开关机                                       */
/* Table Code: INFOPUB_ONOFF                                    */
/*==============================================================*/
create table `INFOPUB_ONOFF` (
   `ST_ONOFF_ID`  varchar(50) not null comment '设备开关机ID',   -- 设备开关机ID
   `ST_DEVICE_ID` varchar(50) comment '设备ID',   -- 设备ID
   `ST_PTYPE`     varchar(50) comment '类型，WEEK：按周；DAY：按天',   -- 类型
   `ST_ON_TIME`   varchar(50) comment '开机时间',   -- 开机时间
   `ST_OFF_TIME`  varchar(50) comment '关机时间',   -- 关机时间
   `ST_PERIOD`    varchar(50) comment '发布周期，固定7个数字，0代表不发布，1代表发布',   -- 发布周期
   `DT_ONOFF`     datetime comment '定时日期',   -- 定时日期
   `DT_CREATE`    datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`    datetime comment '更新时间',   -- 更新时间
   `ST_DESC`      varchar(100) comment '备注',   -- 备注
   primary key (`ST_ONOFF_ID`)
);

/*==============================================================*/
/* Table Name: 外设状态                                         */
/* Table Code: INFOPUB_ODEVICE_STATUS                           */
/*==============================================================*/
create table `INFOPUB_ODEVICE_STATUS` (
   `ST_OUT_DEVICE_RESULT_ID` varchar(50) not null comment '外设状态结果ID',   -- 外设状态结果ID
   `ST_DEVICE_ID`            varchar(50) comment '设备ID',   -- 设备ID
   `ST_OUT_DEVICE_CODE`      varchar(50) comment '外设标识',   -- 外设标识
   `NM_EXCEPTION`            decimal(1) comment '是否异常，0：正常；1：异常',   -- 是否异常
   `ST_CAUSE`                varchar(200) comment '异常原因',   -- 异常原因
   `NM_NOTICE`               decimal(1) comment '是否已经通知，0：未通知；1：已通知',   -- 是否已经通知
   `NM_TOTAL`                decimal(8) comment '总量',   -- 总量
   `NM_REMAIN`               decimal(8) comment '剩余量',   -- 剩余量
   `NM_HIS_TOTAL`            decimal(12) comment '历史总量',   -- 历史总量
   `NM_HIS_STOTAL`           decimal(12) comment '成功总次数',   -- 成功总次数
   `NM_HIS_FTOTAL`           decimal(12) comment '失败总次数',   -- 失败总次数
   `DT_UPDATE`               datetime comment '更新日期',   -- 更新日期
   `ST_EXT1`                 varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`                 varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_OUT_DEVICE_RESULT_ID`)
);

/*==============================================================*/
/* Table Name: 设备状态结果历史                                 */
/* Table Code: INFOPUB_DEVICE_RESULT_HIS                        */
/*==============================================================*/
create table `INFOPUB_DEVICE_RESULT_HIS` (
   `ST_DEVICE_RESULT_ID` varchar(50) not null comment '设备状态结果ID',   -- 设备状态结果ID
   `ST_DEVICE_ID`        varchar(50) comment '设备ID',   -- 设备ID
   `NM_MEM_USED`         decimal(5,2) comment '内存使用率',   -- 内存使用率
   `NM_CPU_USED`         decimal(5,2) comment 'CPU使用率',   -- CPU使用率
   `CL_HD_USED`          longtext comment '磁盘使用情况',   -- 磁盘使用情况
   `CL_NET_USED`         longtext comment '网络使用情况',   -- 网络使用情况
   `CL_SERVICE_USED`     longtext comment '服务使用情况',   -- 服务使用情况
   `DT_CREATE`           datetime comment '创建日期',   -- 创建日期
   `ST_EXT1`             varchar(50) comment '扩展字段1',   -- 扩展字段1
   `ST_EXT2`             varchar(50) comment '扩展字段2',   -- 扩展字段2
   primary key (`ST_DEVICE_RESULT_ID`)
);

/*==============================================================*/
/* Table Name: 图片集类型                                       */
/* Table Code: INFOPUB_PIC_TYPE                                 */
/*==============================================================*/
create table `INFOPUB_PIC_TYPE` (
   `ST_PIC_TYPE_ID` varchar(50) not null comment '图片集类型ID',   -- 图片集类型ID
   `ST_TYPE_CODE`   varchar(50) comment '类型代码',   -- 类型代码
   `ST_TYPE_NAME`   varchar(50) comment '类型名称',   -- 类型名称
   `DT_CREATE`      datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`      datetime comment '更新时间',   -- 更新时间
   `ST_DESC`        varchar(50) comment '备注',   -- 备注
   primary key (`ST_PIC_TYPE_ID`)
);

/*==============================================================*/
/* Table Name: 图片集                                           */
/* Table Code: INFOPUB_PIC                                      */
/*==============================================================*/
create table `INFOPUB_PIC` (
   `ST_PIC_ID`      varchar(50) not null comment '图片集ID',   -- 图片集ID
   `ST_PIC_TYPE_ID` varchar(50) comment '图片集类型ID',   -- 图片集类型ID
   `ST_ATTACH_ID`   varchar(50) comment '附件ID',   -- 附件ID
   `ST_PIC_NAME`    varchar(50) comment '图片名称',   -- 图片名称
   `ST_PIC_URL`     varchar(200) comment '图片链接，可能该图片链接其他系统',   -- 图片链接
   `ST_SKIP_URL`    varchar(200) comment '跳转链接',   -- 跳转链接
   `ST_SKIP_MODULE` varchar(50) comment '跳转模块',   -- 跳转模块
   `NM_ORDER`       decimal(4) comment '图片排序',   -- 图片排序
   `ST_DESC`        varchar(200) comment '图片描述',   -- 图片描述
   `DT_CREATE`      datetime comment '创建时间',   -- 创建时间
   `NM_STATUS`      decimal(1) comment '状态，0：未审核；1：审核通过；2：已发布；3：已禁用；9：已删除',   -- 状态
   `ST_CREATOR`     varchar(50) comment '创建人',   -- 创建人
   `DT_UPDATE`      datetime comment '更新时间',   -- 更新时间
   `ST_AUDITOR`     varchar(50) comment '审核人',   -- 审核人
   `DT_AUDIT`       datetime comment '审核时间',   -- 审核时间
   primary key (`ST_PIC_ID`)
);

/*==============================================================*/
/* Table Name: 功能模块                                         */
/* Table Code: INFOPUB_FUNC                                     */
/*==============================================================*/
create table `INFOPUB_FUNC` (
   `ST_FUNC_ID`      varchar(50) not null comment '功能模块ID',   -- 功能模块ID
   `ST_FUNC_TYPE_ID` varchar(50) comment '功能模块类型ID',   -- 功能模块类型ID
   `ST_FUNC_CODE`    varchar(50) comment '模块代码',   -- 模块代码
   `ST_FUNC_NAME`    varchar(50) comment '模块名称',   -- 模块名称
   `ST_TITLE_ONE`    varchar(50) comment '一级标题',   -- 一级标题
   `ST_TITLE_TWO`    varchar(50) comment '二级标题',   -- 二级标题
   `ST_SKIP_URL`     varchar(200) comment '跳转链接',   -- 跳转链接
   `ST_SKIP_MODULE`  varchar(50) comment '跳转模块',   -- 跳转模块
   `ST_ICON`         varchar(50) comment '小图标，可以是url或者css样式等',   -- 小图标
   `NM_EURL`         decimal(1) comment '是否是第三方url，0：否；1：是',   -- 是否是第三方url
   `ST_PARENT_ID`    varchar(50) comment '父级ID',   -- 父级ID
   `NM_ORDER`        decimal(4) comment '排序',   -- 排序
   `DT_CREATE`       datetime comment '创建时间',   -- 创建时间
   `NM_STATUS`       decimal(1) comment '状态，0：未审核；1：审核通过；2：已发布；3：已禁用；9：已删除',   -- 状态
   `ST_CREATOR`      varchar(50) comment '创建人',   -- 创建人
   `DT_UPDATE`       datetime comment '更新时间',   -- 更新时间
   `ST_AUDITOR`      varchar(50) comment '审核人',   -- 审核人
   `DT_AUDIT`        datetime comment '审核时间',   -- 审核时间
   `ST_DESC`         varchar(200) comment '功能简介',   -- 功能简介
   `ST_PIC_PATH`     varchar(50) comment '图片路径',   -- 图片路径
   primary key (`ST_FUNC_ID`)
);

/*==============================================================*/
/* Table Name: 功能模块类型                                     */
/* Table Code: INFOPUB_FUNC_TYPE                                */
/*==============================================================*/
create table `INFOPUB_FUNC_TYPE` (
   `ST_FUNC_TYPE_ID` varchar(50) not null comment '功能模块类型ID',   -- 功能模块类型ID
   `ST_TYPE_CODE`    varchar(50) comment '类型代码',   -- 类型代码
   `ST_TYPE_NAME`    varchar(50) comment '类型名称',   -- 类型名称
   `DT_CREATE`       datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`       datetime comment '更新时间',   -- 更新时间
   `ST_DESC`         varchar(50) comment '备注',   -- 备注
   primary key (`ST_FUNC_TYPE_ID`)
);

/*==============================================================*/
/* Table Name: 应用                                             */
/* Table Code: INFOPUB_APP                                      */
/*==============================================================*/
create table `INFOPUB_APP` (
   `ST_APP_ID`   varchar(50) not null comment '应用ID',   -- 应用ID
   `ST_APP_NAME` varchar(100) comment '应用名称',   -- 应用名称
   `ST_APP_CODE` varchar(50) comment '应用代码',   -- 应用代码
   `ST_APP_URL`  varchar(50) comment '应用URL',   -- 应用URL
   `DT_CREATE`   datetime comment '创建时间',   -- 创建时间
   `NM_STATUS`   decimal(1) comment '状态，0：未审核；1：审核通过；2：已发布；3：已禁用；9：已删除',   -- 状态
   `ST_CREATOR`  varchar(50) comment '创建人',   -- 创建人
   `DT_UPDATE`   datetime comment '更新时间',   -- 更新时间
   `ST_AUDITOR`  varchar(50) comment '审核人',   -- 审核人
   `DT_AUDIT`    datetime comment '审核时间',   -- 审核时间
   `ST_DESC`     varchar(200) comment '功能简介',   -- 功能简介
   `ST_PIC_PATH` varchar(50) comment '图片路径',   -- 图片路径
   primary key (`ST_APP_ID`)
);

/*==============================================================*/
/* Table Name: 应用关联图片                                     */
/* Table Code: INFOPUB_APP_PIC                                  */
/*==============================================================*/
create table `INFOPUB_APP_PIC` (
   `ST_APP_ID` varchar(50) not null comment '应用ID',   -- 应用ID
   `ST_PIC_ID` varchar(50) not null comment '图片集ID',   -- 图片集ID
   `NM_ORDER`  decimal(4) comment '排序',   -- 排序
   primary key (`ST_APP_ID`, `ST_PIC_ID`)
);

/*==============================================================*/
/* Table Name: 应用关联模块                                     */
/* Table Code: INFOPUB_APP_FUNC                                 */
/*==============================================================*/
create table `INFOPUB_APP_FUNC` (
   `ST_APP_ID`  varchar(50) not null comment '应用ID',   -- 应用ID
   `ST_FUNC_ID` varchar(50) not null comment '功能模块ID',   -- 功能模块ID
   `NM_ORDER`   decimal(4) comment '排序',   -- 排序
   primary key (`ST_APP_ID`, `ST_FUNC_ID`)
);

/*==============================================================*/
/* Table Name: 设备厂商                                         */
/* Table Code: INFOPUB_COMPANY                                  */
/*==============================================================*/
create table `INFOPUB_COMPANY` (
   `ST_COMPANY_ID`   varchar(50) not null comment '厂商ID',   -- 厂商ID
   `ST_COMPANY_CODE` varchar(50) comment '厂商编码',   -- 厂商编码
   `ST_COMPANY_NAME` varchar(50) comment '厂商名称',   -- 厂商名称
   `ST_CONTACT_NAME` varchar(50) comment '厂商负责人姓名',   -- 厂商负责人姓名
   `ST_CONTACT_TEL`  varchar(50) comment '厂商负责人联系方式',   -- 厂商负责人联系方式
   `NM_ORDER`        decimal(1) comment '排序',   -- 排序
   `DT_CREATE`       datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`       datetime comment '更新时间',   -- 更新时间
   primary key (`ST_COMPANY_ID`)
);

/*==============================================================*/
/* Table Name: 地址表（办理点）                                 */
/* Table Code: INFOPUB_ADDRESS                                  */
/*==============================================================*/
create table `INFOPUB_ADDRESS` (
   `ST_ADDRESS_ID` varchar(50) not null comment '地址ID',   -- 地址ID
   `ST_ADDRESS`    varchar(200) comment '地址名称',   -- 地址名称
   `ST_LABEL`      varchar(200) comment '地址别名',   -- 地址别名
   `NM_LNG`        decimal(18,15) comment '经度',   -- 经度
   `NM_LAT`        decimal(18,15) comment '纬度',   -- 纬度
   `ST_CITY`       varchar(50) comment '市',   -- 市
   `ST_DISTRICT`   varchar(50) comment '区',   -- 区
   `ST_STREET`     varchar(50) comment '街道',   -- 街道
   `DT_CREATE`     datetime comment '创建时间',   -- 创建时间
   `DT_UPDATE`     datetime comment '更新时间',   -- 更新时间
   primary key (`ST_ADDRESS_ID`)
);

;;
DELIMITER;