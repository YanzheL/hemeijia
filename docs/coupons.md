# 兑换券

## Coupon类结构

| 属性         | 类型    | 描述                       |
| ------------ | ------- | -------------------------- |
| customer     | String  | 所属客户的用户名           |
| pkg          | Long    | 所属的套餐ID(若有)         |
| name         | String  | 服务名                     |
| createdAt    | Date    | 创建时间                   |
| updatedAt    | Date    | 更新时间                   |
| usedAt       | Date    | 使用时间                   |
| price        | Float   | 价格                       |
| introduction | String  | 兑换券详情介绍             |
| valid        | Boolean | 是否未使用                 |
| paid         | Boolean | 是否已付款. 目前默认为True |

### JSON格式示例

```json
{
    "customer": "xxxxx",
    "name": "全屋打扫",
    "createdAt": "2019-10-1 3:00",
    "updatedAt": "2019-10-1 3:00",
    "usedAt": null,
    "price": 288.0,
    "valid": true
}
```

## 购买优惠券

此接口用于购买单个兑换券

### HTTP 请求

`POST /api/v1/coupons`

!!! attention

    此接口登录后才可使用

### 请求参数

| 参数  | 类型   | 必填 | 默认值 | 描述           |
| ----- | ------ | ---- | ------ | -------------- |
| name  | String | True | -      | 用户名(手机号) |
| price | Float  | True | -      | 价格           |

### 响应参数

| 参数 | 类型   | 描述  |
| --------- | ------ | -------------- |
| data | Coupon | 创建的Coupon对象 |

### 异常

[通用异常](generals.md)

## 查询兑换券

此接口用于查询当前用户符合匹配条件的兑换券

### HTTP 请求

`GET /api/v1/coupons`

!!! attention

    此接口登录后才可使用

### 请求参数

| 参数 | 类型   | 必填 | 默认值 | 描述    |
| --------- | ------ | -------- | ------- | -------------- |
| name      | String | False    | null    | 兑换券名        |
| nopkg | Boolean | False | False  | 是否只返回不属于套餐的兑换券 |

### 响应参数

| 参数 | 类型   | 描述    |
| --------- | ------ | -------------- |
| data | Coupon[] | 匹配的Coupon对象列表 |

### 异常

[通用异常](generals.md)

## 获取指定兑换券信息

此接口用于查询当前用户符合匹配条件的兑换券

### HTTP 请求

`GET /api/v1/coupons/<ID>`

!!! attention

    此接口登录后才可使用

### URL参数

| 参数 | 类型 | 必填 | 默认值 | 描述    |
| --------- | ------ | -------- | ------- | -------------- |
| ID  | Integer | False    | null    | 兑换券ID      |

### 响应参数

| 参数 | 类型 | 描述  |
| --------- | ------ | -------------- |
| data | Coupon | 匹配的Coupon对象列表 |

### 异常

| HTTP状态码 | 异常类型             | 描述                 |
| ---------- | -------------------- | -------------------- |
| 404        | API.ENTITY_NOT_FOUND | 未找到指定ID的兑换券 |

[通用异常](generals.md)

Page last revised on: {{ git_revision_date }}
