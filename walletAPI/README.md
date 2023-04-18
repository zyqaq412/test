# 数据库设计

## 用户表

~~~sql
CREATE TABLE user(
	id INT PRIMARY KEY COMMENT'用户id',
	name VARCHAR(20) COMMENT '用户姓名'
)
~~~

## 钱包表

~~~sql
CREATE TABLE wallet(
	user_id INT PRIMARY KEY COMMENT'用户id',
	balance DECIMAL(10,2) COMMENT'余额，保留两位小数'
)
~~~

## 交易记录表

~~~sql
CREATE TABLE wallet_record(
	id INT PRIMARY KEY,
  user_id INT,
  type INT COMMENT'1充值，2消费，3退款，4提现',
  amount DECIMAL(10,2),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT'交易时间，默认当前时间'
)
~~~

# 接口测试

## 查询余额

![image-20230418093553450](assets/image-20230418093553450.png)



## 用户消费100

![image-20230418093734739](assets/image-20230418093734739.png)

## 用户退款20



![image-20230418093808426](assets/image-20230418093808426.png)

![image-20230418093946036](assets/image-20230418093946036.png)



![image-20230418094012425](assets/image-20230418094012425.png)

![image-20230418095220307](assets/image-20230418095220307.png)

## 查询明细

![image-20230418095835772](assets/image-20230418095835772.png)

id应该用long
