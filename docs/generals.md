# 通用

## 标准响应格式

所有响应包具有统一的数据结构

### 响应参数

| 参数       | 类型    | 默认值 | 描述         |
| ---------- | ------- | ------ | ------------ |
| responseId | String  | -      | 响应ID       |
| timestamp  | Date    | -      | 时间戳       |
| success    | Boolean | -      | 成功标志     |
| data       | Any     | null   | 业务响应数据 |
| error      | Error   | null   | 错误类型     |

### JSON格式示例

成功

```json
{
    "responseId": "XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX",
    "timestamp":"2019-04-21T12:00:56.375+0000",
    "sucess": true,
    "data": null,
    "error": null
}
```

失败

```json
{
    "responseId": "XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX",
    "timestamp":"2019-04-21T12:00:56.375+0000",
    "sucess": false,
    "data": null,
    "error": {
        "type": "API.SECURITY.ACCESS_DENIED",
        "message": "Access Denied"
    }
}
```

### Error类结构

| 参数    | 类型   | 必填 | 默认值 | 描述               |
| ------- | ------ | ---- | ------ | ------------------ |
| type    | String | True | -      | 错误类型           |
| message | String | True | -      | 业务相关的错误描述 |

### 通用异常

| HTTP状态码 | 异常类型                   | 描述                 |
| ---------- | -------------------------- | -------------------- |
| 400        | API.INVALID_PARAMETER      | 请求参数错误 |
| 403        | API.SECURITY.ACCESS_DENIED | 未授权访问           |

<!-- Page last revised on: {{ git_revision_date }} -->
