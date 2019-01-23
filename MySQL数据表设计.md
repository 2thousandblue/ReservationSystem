# 订票系统

创建数据库：
``CREATE DATABASE reservation;``

## 用户信息表
创建用户信息表：
```
CREATE TABLE user (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT KEY COMMENT 'Uid',
	loginname VARCHAR(16) UNIQUE NOT NULL COMMENT '用户名',
	password VARCHAR(16) NOT NULL COMMENT '密码',
	username VARCHAR(20) NOT NULL COMMENT '姓名',
	identity VARCHAR(18) NOT NULL COMMENT '身份证号',
	sex VARCHAR(4) NOT NULL COMMENT '性别',
	phone VARCHAR(11) NOT NULL COMMENT '手机',
	email VARCHAR(20) COMMENT '电邮',
	address VARCHAR(20) COMMENT '地址'
);
```

添加用户测试数据：
```
INSERT INTO user 
(loginname, password, username, identity, sex, phone, email, address)
VALUES
('user1', '123', '张三', '1241123413412195', '男', '13012349744', 'aewez@gmail.com', '北京市中关村'),
('user2', '123', '李四', '1741468313412131', '女', '13572009756', 'qewsu@qq.com', '华西村'),
('user3', '123', '王二', '1241461234412131', '男', '13210349755', 'ghewsk@gmail.com', '地球村'),
('user4', '123', '美丽', '4567468313412131', '女', '13715349751', 'pewsz@foxmail.com', '新疆'),
('user5', '123', '狗蛋', '7410943313412131', '男', '15912309752', 'wewtz@163.com', '雄安新区');
```

## 管理信息表
创建管理员表：
```
CREATE TABLE admin (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT KEY COMMENT 'id',
	loginname VARCHAR(16) UNIQUE NOT NULL COMMENT '用户名',
	password VARCHAR(16) NOT NULL COMMENT '密码',
	username VARCHAR(20) NOT NULL COMMENT '姓名',
	identity VARCHAR(18) NOT NULL COMMENT '身份证号',
	phone VARCHAR(11) NOT NULL COMMENT '手机'
);
```

添加管理员测试数据：
```
INSERT INTO admin 
(loginname, password, username, identity, phone)
VALUES
('admin', 'admin', '管理员', '6451123413412195', '13088881111');
```

## 航班信息表
创建航班信息表：
```
CREATE TABLE flight (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT KEY COMMENT 'id',
	takeoff_time DATETIME NOT NULL COMMENT '出发时间',
	flying_time VARCHAR(20) NOT NULL COMMENT '飞行时间',
	start_place VARCHAR(20) NOT NULL COMMENT '出发地',
	end_place VARCHAR(20) NOT NULL COMMENT '目的地',
	ticket INT UNSIGNED NOT NULL COMMENT '余票',
	price DECIMAL(18,2) UNSIGNED NOT NULL COMMENT '票价'
);
```

添加航班信息测试数据：
```
INSERT INTO flight 
(takeoff_time, flying_time, start_place, end_place, ticket, price)
VALUES
('2019-01-25 10:05', '90分钟', '北京', '纽约', 50, 500.0),
('2019-01-24 11:05', '80分钟', '北京', '华盛顿', 50, 500.0),
('2019-01-24 10:05', '30分钟', '南京', '北京', 50, 200.0),
('2019-01-28 10:05', '60分钟', '重庆', '合肥', 50, 300.0),
('2019-01-29 10:05', '60分钟', '重庆', '合肥', 50, 300.0),
('2019-01-30 10:05', '60分钟', '重庆', '合肥', 50, 300.0),
('2019-01-26 10:05', '60分钟', '重庆', '合肥', 50, 300.0),
('2019-01-18 10:05', '60分钟', '北京', '纽约', 50, 500.0)
;
```

## 购票信息表
创建购票信息表：
```
CREATE TABLE order_ticket (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT KEY COMMENT '订单号',
	flight_id INT NOT NULL COMMENT '航班号',
	takeoff_time DATETIME NOT NULL COMMENT '出发时间',
	start_place VARCHAR(20) NOT NULL COMMENT '出发地',
	end_place VARCHAR(20) NOT NULL COMMENT '目的地',
	price DECIMAL(18,2) UNSIGNED NOT NULL COMMENT '票价',
	loginname VARCHAR(16) NOT NULL COMMENT '用户名',
	username VARCHAR(20) NOT NULL COMMENT '姓名',
	identity VARCHAR(18) NOT NULL COMMENT '身份证号'
);
```

添加购票信息测试数据：
```
INSERT INTO order_ticket 
(flight_id, takeoff_time, start_place, end_place, price, username, identity, loginname)
VALUES
(1, '2019-01-18 10:05', '北京', '纽约', 500.0, '张三', '1241123413412195', 'user1');
```
