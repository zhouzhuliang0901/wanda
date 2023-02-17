--**************************************************************
--************* 请将“语句终止符”设置为字符“/” **************
--**************************************************************

--**************************************************************
--*************************** Drops ****************************
--**************************************************************

--==============================================================
-- Procedure Code: TW_QUIET_DROP_PROC                           
-- 工具：用于弥补DB2没有类似 DROP IF EXISTS 的功能。            
--==============================================================
create or replace procedure TW_QUIET_DROP_PROC ( dropSql varchar(1000) )
language sql
begin
   execute immediate dropSql;
end
/

--==============================================================
-- Table Code: APIDOC_PROJECT                                   
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'APIDOC_PROJECT' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "APIDOC_PROJECT"');
   end if;
end
/

--==============================================================
-- Table Code: APIDOC_MODULE                                    
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'APIDOC_MODULE' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "APIDOC_MODULE"');
   end if;
end
/

--==============================================================
-- Table Code: APIDOC_INTERFACE                                 
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'APIDOC_INTERFACE' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "APIDOC_INTERFACE"');
   end if;
end
/

--==============================================================
-- Table Code: APIDOC_MOD_INTER                                 
--==============================================================
begin atomic
   if exists (
      select 1 from sysibm.systables where name = 'APIDOC_MOD_INTER' and type = 'T'
   ) then call TW_QUIET_DROP_PROC('drop table "APIDOC_MOD_INTER"');
   end if;
end
/

--==============================================================
-- Procedure Code: TW_QUIET_DROP_PROC                           
-- 工具：用于弥补DB2没有类似 DROP IF EXISTS 的功能。            
--==============================================================
drop procedure TW_QUIET_DROP_PROC
/

--**************************************************************
--*********************** Create Tables ************************
--**************************************************************

--==============================================================
-- Table Name: 项目                                             
-- Table Code: APIDOC_PROJECT                                   
--==============================================================
create table "APIDOC_PROJECT" (
   "ST_PROJECT_ID"   varchar(50) not null,   -- 项目ID
   "ST_USER_ID"      varchar(50),   -- 用户ID
   "ST_PROJECT_NAME" varchar(50),   -- 项目名称
   "NM_ORDER"        decimal(4),   -- 排序号
   "DT_CREATE"       timestamp,   -- 创建时间
   "ST_REMARK"       varchar(100),   -- 备注
   "ST_EXT1"         varchar(50),   -- 扩展字段1
   "ST_EXT2"         varchar(50),   -- 扩展字段2
   primary key ("ST_PROJECT_ID")
)
/

comment on column "APIDOC_PROJECT"."ST_PROJECT_ID" is
'项目ID'
/

comment on column "APIDOC_PROJECT"."ST_USER_ID" is
'用户ID'
/

comment on column "APIDOC_PROJECT"."ST_PROJECT_NAME" is
'项目名称'
/

comment on column "APIDOC_PROJECT"."NM_ORDER" is
'排序'
/

comment on column "APIDOC_PROJECT"."DT_CREATE" is
'创建时间'
/

comment on column "APIDOC_PROJECT"."ST_REMARK" is
'备注'
/

comment on column "APIDOC_PROJECT"."ST_EXT1" is
'扩展字段1'
/

comment on column "APIDOC_PROJECT"."ST_EXT2" is
'扩展字段2'
/

--==============================================================
-- Table Name: 模块                                             
-- Table Code: APIDOC_MODULE                                    
--==============================================================
create table "APIDOC_MODULE" (
   "ST_MODULE_ID"   varchar(50) not null,   -- 模块ID
   "ST_MODULE_NAME" varchar(50),   -- 模块名称
   "ST_REMARK"      varchar(500),   -- 模块说明
   "ST_PROJECT_ID"  varchar(50),   -- 项目ID
   "NM_ORDER"       decimal(4),   -- 排序号
   "ST_PARENT_ID"   varchar(50),   -- 父模块ID
   "DT_CREATE"      timestamp,   -- 创建时间
   "ST_EXT1"        varchar(50),   -- 扩展字段1
   "ST_EXT2"        varchar(50),   -- 扩展字段2
   primary key ("ST_MODULE_ID")
)
/

comment on column "APIDOC_MODULE"."ST_MODULE_ID" is
'模块ID'
/

comment on column "APIDOC_MODULE"."ST_MODULE_NAME" is
'模块名称'
/

comment on column "APIDOC_MODULE"."ST_REMARK" is
'模块说明'
/

comment on column "APIDOC_MODULE"."ST_PROJECT_ID" is
'项目ID'
/

comment on column "APIDOC_MODULE"."NM_ORDER" is
'排序'
/

comment on column "APIDOC_MODULE"."ST_PARENT_ID" is
'父模块ID'
/

comment on column "APIDOC_MODULE"."DT_CREATE" is
'创建时间'
/

comment on column "APIDOC_MODULE"."ST_EXT1" is
'扩展字段1'
/

comment on column "APIDOC_MODULE"."ST_EXT2" is
'扩展字段2'
/

--==============================================================
-- Table Name: 接口                                             
-- Table Code: APIDOC_INTERFACE                                 
--==============================================================
create table "APIDOC_INTERFACE" (
   "ST_INTERFACE_ID"   varchar(50) not null,   -- 接口ID
   "ST_INTERFACE_NAME" varchar(50),   -- 接口名
   "ST_URL"            varchar(200),   -- 接口链接
   "CL_REMARK"         clob(10M) not compact not logged,   -- 接口说明
   "ST_METHOD"         varchar(50),   -- 请求方式
   "CL_REQUEST_RARAM"  clob(10M) not compact not logged,   -- 请求参数说明
   "CL_REQUEST_EXAM"   clob(10M) not compact not logged,   -- 请求示例
   "CL_RESPONSE_PARAM" clob(10M) not compact not logged,   -- 返回参数说明
   "CL_RESPONSE_EXAM"  clob(10M) not compact not logged,   -- 返回示例
   "ST_MODULE_ID"      varchar(50),   -- 所属模块ID
   "NM_STATUS"         decimal(1),   -- 是否可用
   "NM_ORDER"          decimal(4),   -- 排序号
   "DT_CREATE"         timestamp,   -- 创建时间
   "DT_UPDATE"         timestamp,   -- 更新时间
   "NM_VERSION"        decimal(18),   -- 版本号
   "ST_EXT1"           varchar(50),   -- 扩展字段1
   "ST_EXT2"           varchar(50),   -- 扩展字段2
   primary key ("ST_INTERFACE_ID")
)
/

comment on column "APIDOC_INTERFACE"."ST_INTERFACE_ID" is
'接口ID'
/

comment on column "APIDOC_INTERFACE"."ST_INTERFACE_NAME" is
'接口名'
/

comment on column "APIDOC_INTERFACE"."ST_URL" is
'api链接'
/

comment on column "APIDOC_INTERFACE"."CL_REMARK" is
'接口说明'
/

comment on column "APIDOC_INTERFACE"."ST_METHOD" is
' 请求方式'
/

comment on column "APIDOC_INTERFACE"."CL_REQUEST_RARAM" is
'请求参数备注'
/

comment on column "APIDOC_INTERFACE"."CL_REQUEST_EXAM" is
'请求示例'
/

comment on column "APIDOC_INTERFACE"."CL_RESPONSE_PARAM" is
'返回参数说明'
/

comment on column "APIDOC_INTERFACE"."CL_RESPONSE_EXAM" is
'返回示例'
/

comment on column "APIDOC_INTERFACE"."ST_MODULE_ID" is
'所属模块ID'
/

comment on column "APIDOC_INTERFACE"."NM_STATUS" is
'是否可用，0：不可用；1：可用；2： 删除'
/

comment on column "APIDOC_INTERFACE"."NM_ORDER" is
'排序'
/

comment on column "APIDOC_INTERFACE"."DT_CREATE" is
'创建时间'
/

comment on column "APIDOC_INTERFACE"."DT_UPDATE" is
'更新时间'
/

comment on column "APIDOC_INTERFACE"."NM_VERSION" is
'版本号'
/

comment on column "APIDOC_INTERFACE"."ST_EXT1" is
'扩展字段1'
/

comment on column "APIDOC_INTERFACE"."ST_EXT2" is
'扩展字段2'
/

--==============================================================
-- Table Name: 模块关联接口                                     
-- Table Code: APIDOC_MOD_INTER                                 
--==============================================================
create table "APIDOC_MOD_INTER" (
   "ST_MODULE_ID"    varchar(50) not null,   -- 模块ID
   "ST_INTERFACE_ID" varchar(50) not null,   -- 接口ID
   "NM_ORDER"        decimal(4),   -- 排序号
   primary key ("ST_MODULE_ID", "ST_INTERFACE_ID")
)
/

comment on column "APIDOC_MOD_INTER"."ST_MODULE_ID" is
'模块ID'
/

comment on column "APIDOC_MOD_INTER"."ST_INTERFACE_ID" is
'接口ID'
/

comment on column "APIDOC_MOD_INTER"."NM_ORDER" is
'排序'
/

