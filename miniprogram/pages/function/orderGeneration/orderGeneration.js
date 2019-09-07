var serviceDetail = require("../../../utils/serviceDetail")
var config = require("../../../utils/config")
const app = getApp();
Page({
  data: {
    id: "",
    type: "",
    serviceDetail: {},
    phoneNumber: 17863110696,
    charge: 398,
    serviceList: [],
    selectid: 0,
    service: null,
    customerType: 0,
    time: '08:00',
    date: '2019-09-05',
    region: ['山东省', '威海', '环翠区'],
    regionDetail: "",
    message: '',
    flag: false
  },
  onLoad: function(options) {
    console.log(options.input)
    this.setData({
      id: this.options.id,
      serviceDetail: serviceDetail[this.options.id],
    })
    var cookie = wx.getStorageSync('cookie')[0]
    console.log(cookie)
    var that = this
    wx.request({
      url: app.globalData.requestUrl + "coupons",
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'cookie': cookie
      },
      data: {
        "name": this.data.serviceDetail.name,
        "nopkg": null,
      },
      success(res) {
        console.log("success")
        console.log(res)
        var data = res.data.data
        console.log(this.data.serviceDetail.name)
        for (var i = 0; i < data.length; i++) {
          if (data[i].name == that.data.serviceDetail.name) {
            that.setDta({
              flag: true
            })
          }
          break
        }
        console.log(this.data.flag)
      },
      fail: function(res) {
        console.log("fail")
        console.log(res)
      }
    })
  },
  // 选择标签
  selectTag: function(e) {
    console.log(e)
    var thispage = this
    thispage.setData({
      selectid: e.currentTarget.id,
      service: this.data.serviceList[e.currentTarget.id].name
    })
  },
  RegionChange: function(e) {
    this.setData({
      region: e.detail.value
    })
  },
  RegionDetailChange(e) {
    if (e.detail.value) {
      this.setData({
        RegionDetail: e.detail.value
      })
    }
  },
  TimeChange(e) {
    this.setData({
      time: e.detail.value
    })
  },
  DateChange(e) {
    this.setData({
      date: e.detail.value
    })
  },
  textareaInput(e) {
    this.setData({
      message: e.detail.value
    })
  },



  payfunction: function(e) {
    var that = this
    var cookie = wx.getStorageSync('cookie')[0]
    console.log(cookie)
    var startAt = new Date(this.data.date);
    // date.setHours(date.getHours(), date.getMinutes() - date.getTimezoneOffset());
    startAt.setHours(this.data.time.split(':')[0], this.data.time.split(':')[1] - startAt.getTimezoneOffset());
    var endAt = new Date(this.data.date);
    endAt.setHours(this.data.time.split(':')[0], this.data.time.split(':')[1] - endAt.getTimezoneOffset() + 240);
    console.log(endAt.toISOString())
    wx.showLoading({
      title: '支付中',
    })
    wx.request({
      url: app.globalData.requestUrl + "coupons",
      method: 'POST',
      header: {
        'content-type': 'application/json', // 默认值
        'cookie': cookie
      },
      data: {
        name: this.data.serviceDetail.name,
        price: parseFloat(this.data.serviceDetail.price)
      },
      success(res) {
        console.log("success_1")
        console.log(res)
        
        wx.request({
          url: app.globalData.requestUrl + "orders",
          method: 'POST',
          header: {
            'content-type': 'application/json', // 默认值
            'cookie': cookie
          },
          data: {
            name: that.data.serviceDetail.name,
            // couponld: null,
            mark: that.data.message,
            address: that.data.region.toString() + that.data.regionDetail,
            startAt: startAt.getFullYear() + '-' + (startAt.getMonth() + 1) + '-' + startAt.getDate() + ' ' + startAt.getHours() + ':' + startAt.getMinutes() + ':' + startAt.getSeconds(),
            // endAt: endAt.toISOString(),
            endAt: endAt.getFullYear() + '-' + (endAt.getMonth() + 1) + '-' + endAt.getDate() + ' ' + endAt.getHours() + ':' + endAt.getMinutes() + ':' + endAt.getSeconds(),
            name: that.data.serviceDetail.name,
            price: that.data.serviceDetail.price
          },
          success(res) {
            console.log("success_2")
            console.log(res)
            wx.hideLoading()
            wx.showToast({
              title: '支付成功',
              icon: 'success',
              duration: 1500
            })
            that.successNavigate()
            console.log("跳转成功")
          },
          fail: function(res) {
            console.log("fail_2")
            console.log(res)
            wx.hideLoading()
            wx.showToast({
              title: '支付失败',
              icon: 'none',
              duration: 2000,
            })
            wx.navigateTo({
              url: '../../MainInterface/orderPage/index',
            })
          }
        })
      },
      fail: function(res) {
        console.log("fail_1")
        console.log(res)
        wx.hideLoading()
        wx.showToast({
          title: '支付失败',
          icon: 'none',
          duration: 2000,
        })
        wx.navigateTo({
          url: '../../MainInterface/orderPage/index',
        })
      }
    })

  },
  successNavigate: function(){
    wx.switchTab({
      url: '../../MainInterface/orderPage/index',
    })
    console.log("successNavigate")
  }
})