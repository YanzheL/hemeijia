// pages/MainInterface/personalCenter/index.js
const app = getApp()
var config = require("../../../utils/config")
var code = ''
var change = 1; //change用来控制显示报修页面 or 显示已报修页面
var color1, color2; //选中或者不选中的颜色
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    showFlag: 1,
    name: '',
    phone: '',
    flag: 0,
    password: '',
    change: 1
  },

  getPassword: function(e) {
    console.log(e)
    this.setData({
      password: e.detail.value
    })
  },


  getName: function(e) {
    console.log(e)
    this.setData({
      name: e.detail.value
    })
  },
  getPhone: function(e) {
    console.log(e)
    this.setData({
      phone: e.detail.value
    })
  },
  handleClick2: function() {
    var phone = this.data.phone
    var name = this.data.name
    var password = this.data.password
    // console.log(this.data.userInfo)
    if (this.data.password == "" || this.data.name == "" || this.data.phone == "" || this.data.name == null || this.data.phone == null) {
      wx.showModal({
        title: '提示',
        content: '请填写完整信息',
      })
    } else {
      this.register()
    }
  },

  handleClick1: function() {
    var phone = this.data.phone
    var name = this.data.name
    var password = this.data.password
    // console.log(this.data.userInfo)
    if (this.data.password == "" || this.data.name == "" || this.data.phone == "" || this.data.name == null || this.data.phone == null) {
      wx.showModal({
        title: '提示',
        content: '请填写完整信息',
      })
    } else {
      this.login()
    }
  },

  // getCode: function() {
  //   wx.login({
  //     success: res => {
  //       code = res.code
  //       console.log(res.code) // 发送 res.code 到后台换取 openId, sessionKey, unionId
  //       this.register(code)
  //     },
  //     fail: res => {
  //       //console.log("code fail",res)
  //     }
  //   })
  // },
  register: function() {
    var phone = this.data.phone
    var name = this.data.name
    var password = this.data.password
    console.log("register", password)
    console.log(phone)
    var that = this
    if (this.data.flag == 0) {
      name = name + "先生"
    }
    console.log(name)

    wx.setStorageSync("userInfo", {
      phone: phone,
      password: password,
      name: name
    })

    wx.request({
      url: `${config.host}/users/register`,
      data: {
        username: phone,
        phoneNumber: phone,
        name: name,
        password: password
      },
      method: 'POST',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function(res) {
        console.log(res);
        if (res.data.success == false) {
          console.log("no data!");
          wx.showModal({
            title: '提示',
            content: '注册异常'
          })
        } else {
          that.onShow()
          // var user = {
          //   'name': res.data.name,
          //   'phoneNumber': res.data.phoneNumber
          // }
          // wx.setStorageSync("userInfo", user)
          console.log("success load");
          that.login()
        }
      },
      fail: function(res) {
        console.log(res);
        wx.showModal({
          title: '提示',
          content: '注册异常',
        })
      },
    })
  },
  login: function() {
    let that = this
    var phone = this.data.phone
    var name = this.data.name
    var password = this.data.password
    console.log("register", password)
    console.log(phone)
    if (this.data.flag == 0) {
      name = name + "先生"
    }
    console.log(name)

    wx.setStorageSync("userInfo", {
      phone: phone,
      password: password,
      name: name
    })
    wx.request({
      url: `${config.host}/login?username=` + phone + `&password=` + password,
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded' // 默认值
      },
      success: function(res) {
        console.log(res);
        if (res.statusCode == "200") {
          console.log("success load");
          that.setData({
            showFlag: 2
          })
          wx.setStorageSync('cookie', res.cookies)
        } else {}
      },
      fail: function(res) {
        console.log(res);
      },
    })
  },

  // 获取性别信息
  radioChange: function(e) {
    var sex = e.detail.value
    var name = ''
    if (sex == "male") {
      name = this.data.name + "先生"
    } else {
      name = this.data.name + "女士"
    }
    this.setData({
      name: name,
      flag: 1
    })
  },
  tapfunc2: function() {
    wx.showModal({
      title: '您的专属客服热线',
      content: '13863172789',
      showCancel: true, //是否显示取消按钮
      confirmText: "拨号", //默认是“确定”
      success: function(res) {
        if (res.cancel) {
          //点击取消,默认隐藏弹框
        } else {
          //点击确定
          wx.makePhoneCall({
            phoneNumber: '13863172789',
            success: function() {
              console.log("成功拨打电话")
            }
          })
        }
      },
    })
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  // test: function() {
  //   wx.login({
  //     success: res => {
  //       code = res.code
  //       //console.log(res.code)// 发送 res.code 到后台换取 openId, sessionKey, unionId
  //     }
  //   })
  //   wx.request({
  //     url: 'https://api.weixin.qq.com/sns/jscode2session?appid=wx787edf8fd2267fec&secret=0d9a56dd6547d8f22ec0d65d8197bcf9&js_code=' + code + '&grant_type=authorization_code',
  //     data: {},
  //     header: {
  //       'content-type': 'application/json'
  //     },
  //     success: function(res) {
  //       openid = res.data.openid //返回openid
  //       console.log(res.data.openid)
  //     }
  //   })
  // },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this
    that.change1()
    var cookie = wx.getStorageSync('cookie')
    console.log(cookie)
    // wx.login({
    //   success: res => {
    //     code = res.code
    //     console.log(res.code) // 发送 res.code 到后台换取 openId, sessionKey, unionId
    //     this.login(code, phone)
    //   },
    //   fail: res => {
    //     //console.log("code fail",res)
    //   }
    // })
    if (cookie == "" || cookie == undefined) {
      that.setData({
        showFlag: 1
      })
    } else {
      that.setData({
        showFlag: 2
      })
    }
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
    var code = ''
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

  },
  change1: function(e) {
    this.setData({
      change: 1,
      color1: '#2287e3',
      color2: '#CFCFCF',
    })
    this.onShow();
  },

  change2: function(e) {
    var that = this
    this.setData({
      change: 2,
      color2: '#2287e3',
      color1: '#CFCFCF',
    })
  }
})