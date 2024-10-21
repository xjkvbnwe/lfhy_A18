// app.js
App({
  onLaunch: function () {
    wx.getStorage({
      key: "app_openid",
      success: function (res) {
        getApp().globalData.open_id = res.data
        const db = wx.cloud.database()
        db.collection("UserData")
          .where({
            open_id: getApp().globalData.open_id
          })
          .get()
          .then(res => {
            if (res.data.length == 0) {
              console.log(res.data)
              wx.showModal({
                title: '信息获取提示',
                content: "第一次扫码前需要获取个人信息，点击确定获取",
                success: res => {
                  if (res.confirm) {
                    wx.getUserProfile({
                      desc: '我们需要获取您的用户信息',
                      lang: 'zh_CN',
                      success: res => {
                        getApp().globalData.userInfo = res
                        console.log(res)
                        wx.cloud.callFunction({
                            name: "addData",
                            data: {
                              databaseName: "UserData",
                              data: {
                                open_id: getApp().globalData.open_id,
                                session_key: getApp().globalData.session_key,
                                nickName: getApp().globalData.userInfo.userInfo.nickName,
                                avatarUrl: getApp().globalData.userInfo.userInfo.avatarUrl,
                                coupon: []
                              }
                            }
                          })
                          .then(res => {
                            getApp().globalData.userDataId = res.result._id
                          })

                        getApp().globalData.userData = res.userInfo
                      }
                    })
                  }
                }
              })
            } else {
              getApp().globalData.userDataId = res.data[0]._id
              getApp().globalData.userData = res.data[0]
            }
          })
      },
      fail: err => {
        wx.login({
          success: res => {
            // 发送 res.code 到后台换取 openId, sessionKey
            if (res.code) {
              wx.cloud.callFunction({
                  name: "userLogin",
                  data: {
                    code: res.code
                  }
                })
                .then(response => {
                  console.log(response)
                  wx.setStorageSync('app_openid', response.result.openid); //将openid存入本地缓存
                  getApp().globalData.open_id = response.result.openid
                  wx.setStorageSync('sessionKey', response.result.session_key) //将session_key 存入本地缓存命名为SessionKey
                  getApp().globalData.session_key = response.result.session_key

                  const db = wx.cloud.database()
                  db.collection("UserData")
                    .where({
                      open_id: getApp().globalData.open_id
                    })
                    .get()
                    .then(res => {
                      if (res.data.length == 0) {
                        wx.showModal({
                          title: '信息获取提示',
                          content: "实现部分功能需要获取个人信息，点击确定获取",
                          success: res => {
                            if (res.confirm) {
                              wx.getUserProfile({
                                desc: '我们需要获取您的用户信息',
                                lang: 'zh_CN',
                                success: res => {

                                  getApp().globalData.userInfo = res
                                  console.log(res)
                                  const db = wx.cloud.database()
                                  wx.cloud.callFunction({
                                      name: "addData",
                                      data: {
                                        databaseName: "UserData",
                                        data: {
                                          open_id: getApp().globalData.open_id,
                                          session_key: getApp().globalData.session_key,
                                          nickName: getApp().globalData.userInfo.userInfo.nickName,
                                          avatarUrl: getApp().globalData.userInfo.userInfo.avatarUrl,
                                          coupon: []
                                        }
                                      }
                                    })
                                    .then(res => {
                                      getApp().globalData.userDataId = res.result._id
                                    })
                                  getApp().globalData.userData = res.userInfo
                                }
                              })
                            }
                          }
                        })
                      } else {
                        getApp().globalData.userDataId = res.data[0]._id
                        getApp().globalData.userData = res.data[0]
                      }
                    })
                })
            } else {
              console.log("登陆失败");
            }
          }
        })
      }
    })

    if (!wx.cloud) {
      console.error('请使用 2.2.3 或以上的基础库以使用云能力');
    } else {
      wx.cloud.init({
        // env 参数说明：
        //   env 参数决定接下来小程序发起的云开发调用（wx.cloud.xxx）会默认请求到哪个云环境的资源
        //   此处请填入环境 ID, 环境 ID 可打开云控制台查看
        //   如不填则使用默认环境（第一个创建的环境）
        env: 'dream-y-9gkp6qa275763d33',
        traceUser: true,
      });
    }


    this.globalData = {
      owner_id: "3513bed163f8722d000c72d86de8af7f",
      ownerOpenId: "",
      userData: {},
      itemData: [],
      userDataId: "",
      open_id: "",
      session_key: "",
      machineName: "",
      userInfo: {},
      userItem: []
    };
  },

});