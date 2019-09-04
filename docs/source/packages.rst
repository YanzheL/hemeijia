服务套餐
********

Package类结构
============

属性
----

=============== ========= ==================================
Parameter       Type      Description
=============== ========= ==================================
customer        String    所属客户的用户名
name            String    套餐名
coupons         Coupon[]  所包含的兑换券列表
createdAt       Date      创建时间
updatedAt       Date      更新时间
price           Float     价格
valid           Boolean   如果套餐内的服务全用完了, 则变为无效状态
paid            Boolean   是否已付款. 目前默认为True
=============== ========= ==================================

JSON格式示例
-----------

.. code:: json

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
     ],
     "valid": true
   }


购买套餐
=======

此接口用于购买套餐

HTTP 请求
------------

``POST /api/v1/packages``

请求参数
-------

============ ========== ======== ========= ================
Parameter    Type       Required Default   Description
============ ========== ======== ========= ================
name         String     True     -         套餐名
coupPacks    CoupPack[] True     -         套餐所含服务包列表
price        Float      True     -         价格
============ ========== ======== ========= ================

CoupPack类结构
-------------

============ ========== ======== ========= ===========
Parameter    Type       Required Default   Description
============ ========== ======== ========= ===========
name         String     True     -         服务名
quantity     Integer    True     -         数量
============ ========== ======== ========= ===========

请求示例
-----------

.. code:: json

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


响应参数
-------
=========== ======== ================
Parameter   Type     Description
=========== ======== ================
data        Package  创建的Package对象
=========== ======== ================

.. Note::
   用户首次购买套餐后会自动变成会员(User.member = true)

查询套餐
=======

此接口用于查询当前用户符合匹配条件的套餐

HTTP 请求
------------

``GET /api/v1/packages``

请求参数
-------

============ ========== ======== ========= ================
Parameter    Type       Required Default   Description
============ ========== ======== ========= ================
name         String     False    null      套餐名
============ ========== ======== ========= ================


响应参数
-------
=========== ========= =====================
Parameter   Type      Description
=========== ========= =====================
data        Package[] 匹配的Package对象列表
=========== ========= =====================
