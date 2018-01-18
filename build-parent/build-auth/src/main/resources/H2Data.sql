-- 用户表
drop table auth_user ;
CREATE TABLE `auth_user` (
  `id` varchar(64) NOT NULL COMMENT '唯一主键',
  `user_name` varchar(255) NOT NULL COMMENT '授权用户名',
  `user_password` varchar(1024) NOT NULL COMMENT '授权用户密码',
  `user_email` varchar(128) DEFAULT NULL COMMENT '授权用户电子邮箱',
  `user_mobile` varchar(13) DEFAULT NULL COMMENT '授权用户手机号',
  `real_name` varchar(128) DEFAULT NULL COMMENT '授权用户真实姓名',
  `user_message` text COMMENT '授权用户信息',
  `user_status` varchar(10) NOT NULL COMMENT '授权用户状态',
  `freeze_time` timestamp NULL DEFAULT NULL COMMENT '用户冻结时间',
  `register_time` timestamp NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '用户注册时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '用户修改时间',
  PRIMARY KEY (`id`)
) ;

-- 角色表
drop table AUTH_ROLE ;
CREATE TABLE `auth_role` (
  `id` VARCHAR(64) NOT NULL COMMENT '授权用户角色',
  `role_type` VARCHAR(45) NULL COMMENT '角色类型',
  `role_name` VARCHAR(64) NOT NULL COMMENT '角色名称',
  `role_auth` VARCHAR(2048) NOT NULL COMMENT '角色 授权信息',
  `status` VARCHAR(10) NOT NULL COMMENT '角色状态',
  `bak` TEXT NULL COMMENT '扩展字段',
  PRIMARY KEY (`id`));

-- 客户端表
drop table auth_client ;
CREATE TABLE `auth_client` (
  `id` VARCHAR(64) NOT NULL COMMENT '唯一主键',
  `client_id` VARCHAR(255) NOT NULL COMMENT '客户端ID-区别主键ID',
  `client_name` VARCHAR(255) NULL COMMENT '客户端名称',
  `client_password` VARCHAR(512) NOT NULL COMMENT '客户端密码',
  `auth_type` VARCHAR(1024) NOT NULL COMMENT '鉴权类型-\"authorization_code\",\"password\",\"implicit\",\"client_credentials\"',
  `auth_scope` VARCHAR(1024) NOT NULL COMMENT '授权动作',
  `expire_time` INT NOT NULL COMMENT '过期时间',
  `register_time` TIMESTAMP NOT NULL COMMENT '注册时间',
  `update_time` TIMESTAMP NULL COMMENT '修改时间',
  `freeze_time` TIMESTAMP NULL COMMENT '冻结时间',
  `bak` VARCHAR(45) NULL COMMENT '扩展字段',
  PRIMARY KEY (`id`));

-- 资源表
CREATE TABLE `auth_resource` (
  `id` VARCHAR(64) NOT NULL,
  `resource_id` VARCHAR(255) NOT NULL COMMENT '资源ID',
  `resource_name` VARCHAR(255) NULL COMMENT '资源名称',
  `resource_url` VARCHAR(1024) NULL COMMENT '资源地址',
  `resource_type` VARCHAR(45) NULL COMMENT '资源类型',
  `resource_pre_name` VARCHAR(64) NULL COMMENT '资源简称',
  `bak` TEXT NULL COMMENT '扩展字段',
  PRIMARY KEY (`id`));

-- 授权用户角色关联表
CREATE TABLE `auth_user_role` (
  `id` VARCHAR(64) NOT NULL,
  `user_id` VARCHAR(64) NULL COMMENT '授权用户ID',
  `role_id` VARCHAR(64) NULL COMMENT '授权角色ID',
  `role_name` VARCHAR(64) NULL COMMENT '授权角色名称',
  `role_type` VARCHAR(45) NULL COMMENT '授权角色类型',
  `create_time` TIMESTAMP NULL COMMENT '创建时间',
  PRIMARY KEY (`id`));

-- 客户端 资源 关系表
CREATE TABLE `auth_client_resource` (
  `id` VARCHAR(64) NOT NULL,
  `client_id` VARCHAR(255) NULL COMMENT '客户端ID',
  `resource_id` VARCHAR(255) NULL COMMENT '资源ID',
  PRIMARY KEY (`id`));

-- 角色 资源关系表
CREATE TABLE `auth_role_resource` (
  `id` VARCHAR(64) NOT NULL,
  `role_id` VARCHAR(64) NOT NULL COMMENT '角色ID',
  `role_type` VARCHAR(45) NOT NULL COMMENT '角色类型',
  `resource_id` VARCHAR(255) NOT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`));

