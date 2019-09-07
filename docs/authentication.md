# 用户认证

此接口执行用户登录操作。登录成功后，服务器将发送给你一个用来唯一标识当前登录状态的Cookie

请求方所有后续的请求都将继承此登录状态。

## 登录

此接口用于登录

### HTTP 请求

`POST /api/v1/login`

### 请求参数

| 参数     | 类型   | 必填 | 默认值 | 描述   |
| -------- | ------ | ---- | ------ | ------ |
| username | String | True | -      | 用户名 |
| password | String | True | -      | 密码   |

### 响应参数

无

!!! note "提示"

    登陆成功: 返回HTTP状态码200
    登录失败: 返回其他状态码

<!-- Page last revised on: {{ git_revision_date }} -->
