// pages/MainInterface/order/index.js
var config = require("../../../utils/config")
var change = 1; //change用来控制显示报修页面 or 显示已报修页面
var color1, color2; //选中或者不选中的颜色
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderList: [],
    packageList:[],
    change: 1
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    that.change1()

  },

  getAllOrder:function(e){
    console.log("232")
    var that=this
    var cookie=wx.getStorageSync('cookie')[0]
    console.log(cookie)
    wx.request({
      url: `${config.host}/orders?nopkg=true`,
      data: {
      },
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'cookie':cookie
      },
      success: function (res) {
        console.log(res);
        if (res.data.success == false) {
          console.log("no data!");
          wx.showModal({
            title: '提示',
            content: '获取异常'
          })
        } else {
          that.setData({
            orderList: res.data.data
          })
        }
      },
      fail: function (res) {
        console.log(res);
        wx.showModal({
          title: '提示',
          content: '获取异常',
        })
      },
    })
  },
  getPackage: function () {
    var that = this
    var cookie = wx.getStorageSync('cookie')[0]

    wx.request({
      url: `${config.host}/packages`,
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'cookie': cookie
      },
      success: function (res) {
        console.log(res);
        if (res.data.success == false) {
          console.log("no data!");
          wx.showModal({
            title: '提示',
            content: '获取异常'
          })
        } else {
          that.setData({
            packageList: res.data.data
          })
          // var time = res.data.data[0].createdAd
          // console.log(time)
          

        }
      },
      fail: function (res) {
        console.log(res);
        wx.showModal({
          title: '提示',
          content: '获取异常',
        })
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

  },
  change1: function (e) {
    let that=this
    this.setData({
      change: 1,
      color1: '#2287e3',
      color2: '#CFCFCF',
    })
    var cookie = wx.getStorageSync('cookie')
    if (cookie == "" || cookie == undefined) {
      wx.showModal({
        title: '提示',
        content: '请先注册哦',
      })
    } else {
      that.getAllOrder()
    }
    // this.onShow();
  },

  change2: function (e) {
    var that = this
    this.setData({
      change: 2,
      color2: '#2287e3',
      color1: '#CFCFCF',
    })
    that.getPackage()
  }
})