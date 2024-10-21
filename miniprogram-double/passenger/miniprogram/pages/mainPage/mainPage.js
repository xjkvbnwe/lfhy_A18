Page({

  /**
   * 页面的初始数据
   */
  data: {
    img: "https://636c-cloud1-0grfnb0p89309523-1312953239.tcb.qcloud.la/%E5%9B%BE%E7%89%87.png?sign=b20542bcf4e069161bbf949861c3f5f0&t=1673495818",
    scanCode: '扫码',
    scanResult: "",
    showApplyReason: true,
    animationData: "",
    viewId: ""
  },

  unlock() {
    var result = this.data.scanResult.split(",")
    if (result[0].indexOf("Unlock") != -1) {
      var userId = getApp().globalData.userDataId
      var content = this.data.scanResult + "," + userId
      wx.redirectTo({
        url: '../home/home?content=' + content,
      })
    }
  },
  /**
   * 扫码事件
   */
  scanCodeEvent: function () {
    wx.scanCode({
      success: res => {
        this.setData({
          scanResult: res.result
        })
        var list = res.result.split(",")
        getApp().globalData.machineName = list[1];
        this.showModal()
      }
    })
  },
  closeShow() {
    // this.setData({
    //   showApplyReason: true
    // })
    this.hideModal()
  },

  showModal: function () {
    var that = this;
    that.setData({
      showApplyReason: false
    })
    // 创建动画实例
    var animation = wx.createAnimation({
      duration: 400, //动画的持续时间
      timingFunction: 'ease', //动画的效果 默认值是linear->匀速，ease->动画以低速开始，然后加快，在结束前变慢
    })
    this.animation = animation; //将animation变量赋值给当前动画
    var time1 = setTimeout(function () {
      that.slideIn(); //调用动画--滑入
      clearTimeout(time1);
      time1 = null;
    }, 20)
  },

  // 隐藏遮罩层
  hideModal: function () {
    var that = this;
    var animation = wx.createAnimation({
      duration: 400, //动画的持续时间 默认400ms
      timingFunction: 'ease', //动画的效果 默认值是linear
    })
    this.animation = animation
    that.slideDown(); //调用动画--滑出
    var time1 = setTimeout(function () {
      that.setData({
        showApplyReason: true
      })
      clearTimeout(time1);
      time1 = null;
    }, 240) //先执行下滑动画，再隐藏模块

  },

  slideIn: function () {
    this.animation.translateY(0).step() // 在y轴偏移，然后用step()完成一个动画
    this.setData({
      //动画实例的export方法导出动画数据传递给组件的animation属性
      animationData: this.animation.export()
    })
  },
  //动画 -- 滑出
  slideDown: function () {
    this.animation.translateY(500).step()
    this.setData({
      animationData: this.animation.export(),
    })
  },
})