# 服务套餐

## Package类结构

| 属性      | 类型     | 描述                       |
| --------- | -------- | -------------------------- |
| customer  | String   | 所属客户的用户名           |
| name      | String   | 套餐名                     |
| coupons   | Coupon[] | 所包含的兑换券列表         |
| createdAt | Date     | 创建时间                   |
| updatedAt | Date     | 更新时间                   |
| price     | Float    | 价格                       |
| paid      | Boolean  | 是否已付款. 目前默认为True |

### JSON格式示例

```json
{
    "customer": "xxxxx",
    "name": "全屋打扫套餐",
    "createdAt": "2019-10-1 3:00 PM GMT+1:00",
    "updatedAt": "2019-10-1 3:00 PM GMT+1:00",
    "price": 288.0,
    "coupons": [
        {
          "id": 1,
          "name": "全屋打扫",
          "valid": true
        },
        {
          "id": 2,
          "name": "全屋打扫",
          "valid": true
        },
        {
          "id": 3,
          "name": "管道疏通",
          "valid": true
        }
    ]
}
```

## 购买套餐

此接口用于购买套餐

### HTTP 请求

`POST /api/v1/packages`

!!! attention "注意"

    此接口登录后才可使用

### 请求参数

| 参数      | 类型       | 必填 | 默认值 | 描述               |
| --------- | ---------- | ---- | ------ | ------------------ |
| name      | String     | True | -      | 用户名(手机号)     |
| coupPacks | CoupPack[] | True | -      | 套餐所含服务包列表 |
| price     | Float      | True | -      | 价格               |

#### CoupPack类结构

| 参数     | 类型    | 必填 | 默认值 | 描述   |
| -------- | ------- | ---- | ------ | ------ |
| name     | String  | True |        | 服务名 |
| quantity | Integer | True |        | 数量   |

### 请求示例

```json
{
    "name": "全屋打扫套餐",
    "price": 288.0,
    "coupPacks": [
        {
          "name": "全屋打扫",
          "quantity": 2
        },
        {
          "name": "管道疏通",
          "quantity": 1
        }
    ]
}
```

### 响应参数

| 参数 | 类型   | 描述  |
| --------- | ------ | -------------- |
| data | Package | 创建的Package对象 |

### 异常

[通用异常](generals.md)

## 查询套餐

此接口用于查询当前用户符合匹配条件的套餐

### HTTP 请求

`GET /api/v1/packages`

!!! attention "注意"

    此接口登录后才可使用

### 请求参数

| 参数 | 类型   | 必填 | 默认值 | 描述    |
| --------- | ------ | -------- | ------- | -------------- |
| name | String | False | null   | 套餐名 |

### 响应参数

| 参数 | 类型   | 描述    |
| --------- | ------ | -------------- |
| data | Package[] | 匹配的Package对象列表 |

### 异常

[通用异常](generals.md)

## 获取指定套餐信息

此接口用于查询当前用户指定ID的套餐

### HTTP 请求

`GET /api/v1/packages/<ID>`

!!! attention "注意"

    此接口登录后才可使用

### URL参数

| 参数 | 类型 | 必填 | 默认值 | 描述    |
| --------- | ------ | -------- | ------- | -------------- |
| ID  | Integer | False    | null    | 套餐ID  |

### 响应参数

| 参数 | 类型 | 描述  |
| --------- | ------ | -------------- |
| data | Package | 匹配的Package对象 |

### 异常

| HTTP状态码 | 异常类型             | 描述               |
| ---------- | -------------------- | ------------------ |
| 404        | API.ENTITY_NOT_FOUND | 未找到指定ID的套餐 |

[通用异常](generals.md)

Page last revised on: {{ git_revision_date }}
