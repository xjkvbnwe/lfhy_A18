// pages/youhui/youhui.js
function DateToStr(date) {
  var year = date.getFullYear(); //年
  var month = date.getMonth(); //月
  var day = date.getDate(); //日
  var hours = date.getHours(); //时
  var min = date.getMinutes(); //分
  var second = date.getSeconds(); //秒
  return year + "-" +
    ((month + 1) > 9 ? (month + 1) : "0" + (month + 1)) + "-" +
    (day > 9 ? day : ("0" + day)) + " " +
    (hours > 9 ? hours : ("0" + hours)) + ":" +
    (min > 9 ? min : ("0" + min)) + ":" +
    (second > 9 ? second : ("0" + second));
}
Page({

  /**
   * 页面的初始数据
   */
  data: {
    couponList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var _ = this
    const db = wx.cloud.database()
    db.collection("UserData")
      .where({
        open_id: getApp().globalData.open_id
      })
      .get()
      .then(res => {
        var couponList = res.data[0].coupon
        for (var i in couponList) {
          if (couponList[i].endDate >= DateToStr(new Date())) {
            couponList[i].time = "未过期"
            couponList[i].timeType = 1
          } else {
            couponList[i].time = "已过期"
            couponList[i].timeType = 0
          }
        }
        couponList.sort((a,b) => {
          return b.timeType-a.timeType
        })
        _.setData({
          couponList: couponList
        })
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