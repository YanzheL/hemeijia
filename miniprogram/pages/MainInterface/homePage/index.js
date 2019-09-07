// pages/MainInterface/homePage/index.js
var service = require("../../../utils/service.js")
const app = getApp()
var currentImage = ''
var user = require("../../../utils/userInfo")
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgUrls: [
      'http://media.jiazhengye.cn/phpgoKwPJ',
      'http://media.jiazhengye.cn/phpJhAZem',
      'http://media.jiazhengye.cn/phphQ3HyN'
    ],
    iconList: [{
      id: 1,
      icon: 'http://media.jiazhengye.cn/phptKsr7t',
      color: 'red',
      url: '../../function/japanClean/index',
      name: '日式保洁'
    }, {
      id: 2,
      icon: 'http://media.jiazhengye.cn/phpOOmNGj',
      color: 'orange',
      url: '../../function/dustClean/index',
      name: '除尘除螨'
    }, {
      id: 3,
      icon: 'http://media.jiazhengye.cn/phpFJUerl',
      color: 'yellow',
      url: '../../function/elecClean/index',
      name: '家电清洗'
    }, {
      id: 4,
      icon: 'http://media.jiazhengye.cn/phpx8bfzI',
      color: 'olive',
      url: '../../function/openupClean/index',
      name: '开荒保洁'
    }, {
      id: 5,
      icon: 'http://media.jiazhengye.cn/phpZsifgw',
      color: 'cyan',
      url: '../../function/tidyup/index',
      name: '整理收纳'
    }, {
      id: 6,
      icon: 'http://media.jiazhengye.cn/php0EMkBN',
      color: 'blue',
      url: '../../function/windowClean/index',
      name: '精细擦窗'
    }, {
      id: 7,
      icon: 'http://media.jiazhengye.cn/phpEYV6Ez',
      url: '../../function/deepClean/index',
      name: '深度保洁'
    }, {
      id: 8,
      icon: 'http://media.jiazhengye.cn/phpMUMb6c',
      url: '../../function/otherClean/index',
      name: '其他'
    }],
    iconList2: [{
      icon: 'http://media.jiazhengye.cn/phpJIlW0L'
    }, {
      icon: 'http://media.jiazhengye.cn/phpLegwb2'
    }],
    gridCol: 4,
    skin: false,
    grids: [0, 1]
  },
  swiperChange: function(event) {
    // console.log(event.detail.current)
    currentImage = event.detail.current
    //event.detail = { current, source }
  },
  swiperClick: function(event) {
    if (currentImage == 0) {
      wx.navigateTo({
        url: '/pages/function/detail/detail?id=0101',
      })
    } else if (currentImage == 1) {
      wx.navigateTo({
        url: '/pages/function/detail/detail?id=0701',
      })
    } else if (currentImage == 2) {
      wx.navigateTo({
        url: '/pages/function/detail/detail?id=0702',
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

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