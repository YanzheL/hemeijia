兑换券
******

Coupon类结构
============

属性
----

=============== ========= =======================
Parameter       Type      Description
=============== ========= =======================
customer        String    所属客户的用户名
pkg             Long      所属的套餐ID(若有)
name            String    服务名
createdAt       Date      创建时间
updatedAt       Date      更新时间
usedAt          Date      使用时间
price           Float     价格
introduction    String    兑换券详情介绍
valid           Boolean   是否未使用
paid            Boolean   是否已付款. 目前默认为True
=============== ========= =======================

JSON格式示例
------------

.. code:: json

   {
     "customer": "xxxxx",
     "name": "全屋打扫",
     "createdAt": "2019-10-1 3:00 PM GMT+1:00",
     "updatedAt": "2019-10-1 3:00 PM GMT+1:00",
     "usedAt": null,
     "price": 288.0,
     "valid": true
   }


购买兑换券
==========

此接口用于购买单个服务

HTTP 请求
------------

``POST /api/v1/coupons``

请求参数
--------

============ ========== ======== ========= ================
Parameter    Type       Required Default   Description
============ ========== ======== ========= ================
name         String     True     -         服务名
price        Float      True     -         价格
============ ========== ======== ========= ================

响应参数
--------
=========== ======== ================
Parameter   Type     Description
=========== ======== ================
data        Coupon   创建的Coupon对象
=========== ======== ================

查询兑换券
==========

此接口用于查询当前用户符合匹配条件的服务

HTTP 请求
------------

``GET /api/v1/coupons``

请求参数
--------

============ ========== ======== ========= ================
Parameter    Type       Required Default   Description
============ ========== ======== ========= ================
name         String     False    null      服务名
============ ========== ======== ========= ================


响应参数
--------
=========== ========= =====================
Parameter   Type      Description
=========== ========= =====================
data        Coupon[]  匹配的Coupon对象列表
=========== ========= =====================
