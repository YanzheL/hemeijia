# 家政预约

## Order类结构

| 属性      | 类型   | 描述                                                         |
| --------- | ------ | ------------------------------------------------------------ |
| customer  | String | 所属客户的用户名                                             |
| mark      | String | 所属的套餐ID(若有)                                           |
| createdAt | Date   | 创建时间                                                     |
| updatedAt | Date   | 更新时间                                                     |
| startAt   | Date   | 计划开始时间                                                 |
| endAt     | Date   | 计划结束时间                                                 |
| coupon    | Coupon | 使用的兑换券                                                 |
| status    | String | 订单状态 ["已下单", "已接单", "已出发", "使用中", "已完结", "已取消"] |
| address   | String | 服务地址                                                     |
| comment   | String | 是否已付款. 目前默认为True                                   |
| rate      | Float  | 服务评分                                                     |

### JSON格式示例

```json
{
    "customer": "xxxxx",
    "mark": "不要按门铃",
    "createdAt": "2019-10-1 3:00",
    "updatedAt": "2019-10-1 3:00",
    "startAt": "2019-10-1 3:00",
    "endAt": "2019-10-1 3:00",
    "coupon": {
        "customer": "xxxxx",
        "name": "全屋打扫",
        "createdAt": "2019-10-1 3:00",
        "updatedAt": "2019-10-1 3:00",
        "usedAt": "2019-10-1 3:00",
        "price": 288.0,
        "valid": true
    },
    "status": "已下单",
    "address": "青州街男子职业技术学院",
    "comment": "非常满意",
    "rate": 4.8
}
```

## 创建预约单

此接口用于使用已有的兑换券创建家政预约单

### HTTP 请求

`POST /api/v1/orders`

!!! attention

    此接口登录后才可使用

### 请求参数

| 参数     | 类型   | 必填  | 默认值 | 描述                                          |
| -------- | ------ | ----- | ------ | --------------------------------------------- |
| name     | String | False | null   | 用户名(手机号)                                |
| couponId | Long   | False | null   | 兑换券ID                                      |
| mark     | String | True  | -      | 备注                                          |
| address  | String | True  | -      | 服务地址                                      |
| startAt  | String | True  | -      | 计划开始时间, 字符串格式"2000-10-31 01:30:00" |
| endAt    | String | True  | -      | 计划结束时间, 字符串格式"2000-10-31 01:30:00" |

!!! attention

    name和couponId只能取其一，不能同时为空.
    
    如果couponId为空, 则后台会根据name查找可用的对应服务的兑换券.
    
    若找到, 则后台会把兑换券的valid设为false,usedAt设为当前时间, 返回成功创建的订单;
    
    若未找到, 则会返回错误.

### 响应参数

| 参数 | 类型   | 描述  |
| --------- | ------ | -------------- |
| data | Order | 创建的Order对象 |

### 异常

[通用异常](generals.md)

## 查询预约单

此接口用于查询当前用户符合匹配条件的预约单

### HTTP 请求

`GET /api/v1/orders`

!!! attention

    此接口登录后才可使用

### 请求参数

| 参数 | 类型   | 必填 | 默认值 | 描述    |
| --------- | ------ | -------- | ------- | -------------- |
| nopkg | Boolean | False | False  | 是否只返回单次直接购买的预约单 |

### 响应参数

| 参数 | 类型   | 描述    |
| --------- | ------ | -------------- |
| data | Order[] | 匹配的Order对象列表 |

### 异常

[通用异常](generals.md)

<!-- Page last revised on: {{ git_revision_date }} -->
