家政订单
*******

Order类结构
==========

属性
----

=============== ========= ====================================================================
Parameter       Type      Description
=============== ========= ====================================================================
customer        String    所属客户的用户名
mark            String    备注
createdAt       Date      创建时间
updatedAt       Date      更新时间
service         Service   下单的服务
status          String    订单状态 ["已下单", "已接单", "已出发", "使用中", "已完结", "已取消"]
address         String    服务地址
comment         String    服务评价
rate            Float     服务评分
=============== ========= ====================================================================

JSON格式示例
-----------

.. code:: json

   {
     "customer": "xxxxx",
     "mark": "不要按门铃",
     "createdAt": "2019-10-1 3:00 PM GMT+1:00",
     "updatedAt": "2019-10-1 3:00 PM GMT+1:00",
     "service": {
       "customer": "xxxxx",
       "name": "全屋打扫",
       "createdAt": "2019-10-1 3:00 PM GMT+1:00",
       "updatedAt": "2019-10-1 3:00 PM GMT+1:00",
       "usedAt": "2019-10-1 3:00 PM GMT+1:00",
       "price": 288.0,
       "valid": true
     },
     "status": "已下单",
     "address": "青州街男子职业技术学院",
     "comment": "非常满意",
     "rate": 4.8
   }


下单(使用服务)
============

此接口用于下单(使用已有的服务)

HTTP 请求
------------

``POST /api/v1/orders``

请求参数
-------

============ ========== ======== ========= ================
Parameter    Type       Required Default   Description
============ ========== ======== ========= ================
name         String     False    null      服务名
serviceId    Long       False    null      服务ID
mark         String     False    null      备注
address      String     True     -         服务地址
============ ========== ======== ========= ================

.. Attention::
   name和serviceId只能取其一，不能同时为空。如果serviceId为空, 则后台会根据name查找可用的服务。若找到, 则后台会把对应服务的valid设为false,usedAt设为当前时间, 返回成功创建的订单; 若未找到, 则会返回错误.

响应参数
-------
=========== ======== ================
Parameter   Type     Description
=========== ======== ================
data        Order    创建的Order对象
=========== ======== ================

查询订单
=======

此接口用于查询当前用户符合匹配条件的订单

HTTP 请求
------------

``GET /api/v1/orders``

请求参数
-------

暂无

响应参数
-------
=========== ========= =====================
Parameter   Type      Description
=========== ========= =====================
data        Order[]   匹配的Order对象列表
=========== ========= =====================
