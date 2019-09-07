// pages/function/packageGeneration/index.js
var service = require("../../../utils/package.js")
var config = require("../../../utils/config")

Page({

  /**
   * 页面的初始数据
   */
  data: {
    serviceDetail: {},
    remark:''
  },
  getRemark:function(e){
    this.setData({
      remark:e.detail.data
    })

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var type=options.type

    console.log(type)
    console.log(service)
    this.setData({
      serviceDetail: service[type],
    })

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  payfunction() {
    var cookie = wx.getStorageSync('cookie')[0]
    wx.request({
      url: `${config.host}/coupons`,
      data: {
        name: this.data.serviceDetail.name,
        price: this.data.serviceDetail.price,
        remark:this.data.remark
      },
      method: 'POST',
      header: {
        'cookie': cookie,
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res);
        if (res.data.success == false) {
          console.log("success load");
        } else {
          console.log("no data!");
        }
      },
      fail: function (res) {
        console.log(res);
      },
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})