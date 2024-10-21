// pages/dingdan/orderInfo/orderInfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    data: {},
    order: "",
    type: "微信支付",
    payDate: "",
    machine: "",
    state: "",
    ownerId: "",
    buyAmount: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var order = options.item
    var _ = this
    wx.request({
      url: 'https://www.dream-y.top:8888/user/selectTradeGoods/'+order,
      method: "POST",
      header: {
        'content-type': 'application/x-www-form-urlencoded',
      },
      success: res => {
        var itemList = res.data
          for (var i in itemList) {
            if (itemList[i].goodsName.length>15) {
              itemList[i].goodsName = itemList[i].goodsName.substring(0,14)+"..."
            }
            itemList[i].goodsName = itemList[i].goodsName+" x"+itemList[i].buyTotal
            itemList[i].goodsPrice = itemList[i].goodsPrice*itemList[i].buyTotal
          }
      
        _.setData({
          data: itemList,
          order: order,
          payDate: options.buyDate,
          machine: "Test",
          state: options.state,
          ownerId: options.ownerId,
          buyAmount: options.buyAmount
        })
      }
    })
  },

  clickCopy() {
    wx.setClipboardData({
      data: this.data.order,
      success: function(res) {
        wx.showToast({
          title: '复制成功',
          icon: "success"
        });
      }
    })
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