// pages/function/japanClean/japanDetail/index.js
var service = require("../../utils/service.js")
Page({
  /**
   * 页面的初始数据
   */
  data: {
    type: "日式保洁",
    introduction: "日式保洁-4小时”是荷美家创立的居家常住房环境下的专业保洁业务，由一位经过培训的专职员工，统一着装、围裙，使用专用的清洁工具，提供有检验标准的家庭保洁服务",
    serviceList: [],
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    console.log(options)
    console.log(service)
    var serviceType = service["service" + options.id]
    console.log(serviceType)
    this.setData({
      serviceList: serviceType
    })

    // 修改顶部栏，保留待修改
    // wx.setNavigationBarTitle({
    //   title: this.data.serviceType.name
    // })
  },
  btn: function(e) {
    var input = this.data.serviceList[0].name
    for (var i = 1; i < this.data.serviceList.length; i++) {
      input += "###" + this.data.serviceList[i].name
    }
    console.log(input)
    wx.navigateTo({
      url: '/pages/function/orderGeneration/orderGeneration?input=' + input,
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})