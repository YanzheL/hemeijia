// pages/function/wardrobePackage/packageDetail/index.js
var config = require("../../../../utils/config")
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  btn() {
    // wx.navigateTo({
    //   url: '../../packageGeneration/index?type=0101',
    //   success: function (res) { },
    //   fail: function (res) { },
    //   complete: function (res) { },
    // })
    var cookie = wx.getStorageSync('cookie')[0]
    var coupPacks = [{
      "name": "衣柜收纳",
      "quantity": 4
    }]
    wx.request({
      url: `${config.host}/packages`,
      data: {
        name: '衣柜收纳4次',
        price: '2396',
        coupPacks: coupPacks

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
          wx.showToast({
            title: '下单失败',
          })
        } else {
          wx.showToast({
            title: '下单成功',
          })
        }
      },
      fail: function (res) {
        console.log(res);
      },
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

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