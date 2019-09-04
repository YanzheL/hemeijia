通用
****

标准响应格式
============

所有响应包都具有统一的数据结构

响应参数
--------

============ ======== ======== ========= ===========
Parameter    Type     Required Default   Description
============ ======== ======== ========= ===========
responseId   String   True     -         响应ID
timestamp    Date     True     -         时间戳
success      Boolean  True     -         成功标志
data         Any      True     null      业务数据
error        Error    True     null      报错信息
============ ======== ======== ========= ===========

JSON格式示例
------------

.. code:: json

   {
     "responseId": "XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX",
     "timestamp":"2019-04-21T12:00:56.375+0000",
     "sucess": true,
     "data": null,
     "error": null
   }

.. code:: json

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

标准错误消息格式
================

Error类属性
-----------

============ ======== ======== ========= ===========
Parameter    Type     Required Default   Description
============ ======== ======== ========= ===========
type         String   True     -         错误类型
message      String   True     -         描述
============ ======== ======== ========= =========

JSON格式示例
------------

.. code:: json

   {
     "type": "API.SECURITY.ACCESS_DENIED",
     "message": "Access Denied"
   }

HTTP 状态码
===========

成功
----

+-----------+-----------------------------------------------------------+
| Success   | Meaning                                                   |
| Code      |                                                           |
+===========+===========================================================+
| 200       | OK – Standard response for successful HTTP requests       |
+-----------+-----------------------------------------------------------+
| 201       | Created – The request has been fulfilled, resulting in    |
|           | the creation of a new resource.                           |
+-----------+-----------------------------------------------------------+
| 202       | Accepted — The request has been accepted for processing,  |
|           | but the processing has not been completed.                |
+-----------+-----------------------------------------------------------+

错误
----

+-----------------------------------------+----------------------------+
| Error Code                              | Meaning                    |
+=========================================+============================+
| 400                                     | Bad Request – Your request |
|                                         | is invalid.                |
+-----------------------------------------+----------------------------+
| 401                                     | Unauthorized – Your are    |
|                                         | unauthorized.              |
+-----------------------------------------+----------------------------+
| 403                                     | Forbidden — You do not     |
|                                         | have privilege to perform  |
|                                         | this request.              |
+-----------------------------------------+----------------------------+
| 404                                     | Not Found – The specified  |
|                                         | kitten could not be found. |
+-----------------------------------------+----------------------------+
| 405                                     | Method Not Allowed – You   |
|                                         | tried to access a kitten   |
|                                         | with an invalid method.    |
+-----------------------------------------+----------------------------+
| 406                                     | Not Acceptable – You       |
|                                         | requested a format that    |
|                                         | isn’t json.                |
+-----------------------------------------+----------------------------+
| 410                                     | Gone – The object          |
|                                         | requested has been removed |
|                                         | from our servers.          |
+-----------------------------------------+----------------------------+
| 429                                     | Too Many Requests – You’re |
|                                         | requesting too many        |
|                                         | kittens! Slow down!        |
+-----------------------------------------+----------------------------+
| 500                                     | Internal Server Error – We |
|                                         | had a problem with our     |
|                                         | server. Try again later.   |
+-----------------------------------------+----------------------------+
| 503                                     | Service Unavailable –      |
|                                         | We’re temporarily offline  |
|                                         | for maintenance. Please    |
|                                         | try again later.           |
+-----------------------------------------+----------------------------+
