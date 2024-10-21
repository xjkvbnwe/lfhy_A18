// pages/mine/mine.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    phone: "19999999999",
    img:"https://636c-cloud1-0grfnb0p89309523-1312953239.tcb.qcloud.la/%E5%B0%81%E9%9D%A2_%E5%89%AF%E6%9C%AC.png?sign=88084eab8783d85ea2be438852fc4e00&t=1673610923",
  },
  userEdit() {
      wx.navigateTo({
        url: './edit/edit',
      })
  },
  agreement() {
    wx.navigateTo({
      url: './agreement/agreement',
    })
  },

  showAction() {
    wx.showActionSheet({
      itemList: [this.data.phone, '呼叫'],
      success: (res) => {
        switch (res.tapIndex) {
          case 0:
            wx.setClipboardData({
              data: this.data.phone,
              success: function (res) {
                wx.showToast({
                  title: '复制成功',
                  icon: "success"
                });
              }
            })
            break;
          case 1:
            wx.makePhoneCall({
              phoneNumber: this.data.phone,
            })
            break;
        }
      }
    })
  },

  myCollection() {
    wx.navigateTo({
      url: './userlog/userlog?type=myCollection',
    })
  },
  myLike() {
    getApp().globalData.chooseId = getApp().globalData.userData._id
    wx.navigateTo({
      url: './userlog/userlog?type=userInfo',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})