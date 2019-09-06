# 用户管理

## User类结构

| 属性        | 类型    | 描述       |
| ----------- | ------- | ---------- |
| username    | String  | 用户名     |
| name        | String  | 姓名       |
| phoneNumber | String  | 手机号     |
| createdAt   | Date    | 注册时间   |
| updatedAt   | Date    | 更新时间   |
| member      | Boolean | 是否为会员 |

### JSON格式示例

```json
{
    "username": "xxxxx",
    "name": "李四",
    "createdAt": "2019-10-1 3:00",
    "updatedAt": "2019-10-1 3:00",
    "phoneNumber": "18900000000",
    "member": true
}
```

## 注册

此接口用于创建新用户

### HTTP 请求

`POST /api/v1/users/register`

### 请求参数

| 参数        | 类型   | 必填 | 默认值 | 描述   |
| ----------- | ------ | ---- | ------ | ------ |
| username    | String | True | -      | 用户名 |
| password    | String | True | -      | 密码   |
| name        | String | True | -      | 姓名   |
| phoneNumber | String | True | -      | 手机号 |

### 响应参数

| 参数 | 类型   | 描述  |
| --------- | ------ | -------------- |
| data | User | 创建的User对象 |

### 异常

[通用异常](generals.md)

## 获取用户信息

此接口用于获取当前用户的信息

### HTTP 请求

`GET /api/v1/users`

!!! attention "注意"

    此接口登录后才可使用

### 请求参数

无

### 响应参数

| 参数 | 类型   | 描述  |
| --------- | ------ | -------------- |
| data | User | 当前User对象 |

### 异常

[通用异常](generals.md)

Page last revised on: {{ git_revision_date }}
