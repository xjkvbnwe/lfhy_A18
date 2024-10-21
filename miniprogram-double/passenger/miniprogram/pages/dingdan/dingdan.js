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
    currtab: 0,
    swipertab: [{ name: '已完成', index: 0 }, { name: '待付款', index: 1 }, { name: '已取消', index: 2 }],
    dataFinishList: [],
    dataFinish: {},
    dataPayList: [],
    dataPay: {},
    dataCancelList: [],
    dataCancel: {}
  },

  clickTradeInfo(e) {
    var index = e.currentTarget.dataset.index
    var type = e.currentTarget.dataset.type
    if (type == 0) {
      wx.navigateTo({
        url: './orderInfo/orderInfo?item='+this.data.dataFinishList[index]+"&buyAmount="+this.data.dataFinish[index].buyAmount+"&buyDate="+this.data.dataFinish[index].buyDate
        +"&ownerId="+this.data.dataFinish[index].ownerId+"&state="+this.data.dataFinish[index].state,
      })
    } else if (type == 1) {
      wx.navigateTo({
        url: './orderInfo/orderInfo?item='+this.data.dataPayList[index]+"&buyAmount="+this.data.dataPay[index].buyAmount+"&buyDate="+this.data.dataPay[index].buyDate
        +"&ownerId="+this.data.dataPay[index].ownerId+"&state="+this.data.dataPay[index].state,
      })
    } else {
      wx.navigateTo({
        url: './orderInfo/orderInfo?item='+this.data.dataCancelList[index]+"&buyAmount="+this.data.dataCancel[index].buyAmount+"&buyDate="+this.data.dataCancel[index].buyDate
        +"&ownerId="+this.data.dataCancel[index].ownerId+"&state="+this.data.dataCancel[index].state,
      })
    }
  },
 
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var _ = this
    wx.request({
      url: 'https://www.dream-y.top:8888/user/selectTradeGoodsAll/'+getApp().globalData.userDataId+'&1',
      method: "POST",
      header: {
        'content-type': 'application/x-www-form-urlencoded',
      },
      success: res => {
        console.log(res.data)
        var itemAll = res.data
        var keys = Object.keys(res.data)
        var finishItems= [],payItems= [],cancelItems = []
        var finishList = [],payList = [],cancelList = []
        for (var key of keys) {
          var itemList = res.data[key].array
          for (var i in itemList) {
            if (itemList[i].goodsName.length>15) {
              itemList[i].goodsName = itemList[i].goodsName.substring(0,14)+"..."
            }
            itemList[i].goodsName = itemList[i].goodsName+" x"+itemList[i].buyTotal
            itemList[i].goodsPrice = itemList[i].goodsPrice*itemList[i].buyTotal
          }
          itemAll[key].array = itemList
          if (itemAll[key].state == "交易成功" || itemAll[key].state == "已提现") {
            finishItems.push(itemAll[key])
            finishList.push(key)
          } else if (itemAll[key].state == "待支付") {
            payItems.push(itemAll[key])
            payList.push(key)
          } else {
            cancelItems.push(itemAll[key])
            cancelList.push(key)
          }
        }
        payItems.sort((a,b) => {
          return new Date(b.buyDate) - new Date(a.buyDate)
        })
        finishItems.sort((a,b) => {
          return new Date(b.buyDate) - new Date(a.buyDate)
        })
        cancelItems.sort((a,b) => {
          return new Date(b.buyDate) - new Date(a.buyDate)
        })
        _.setData({
          dataFinish: finishItems,
          dataFinishList: finishList,
          dataPay: payItems,
          dataPayList: payList,
          dataCancel: cancelItems,
          dataCancelList: cancelList
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    // 页面渲染完成
    this.getDeviceInfo()
  },
 
  getDeviceInfo: function () {
    let that = this
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          deviceW: res.windowWidth,
          deviceH: res.windowHeight
        })
      }
    })
  },
 
  /**
  * @Explain：选项卡点击切换
  */
  tabSwitch: function (e) {
    var that = this
    if (this.data.currtab === e.target.dataset.current) {
      return false
    } else {
      that.setData({
        currtab: e.target.dataset.current
      })
    }
  },
 
  tabChange: function (e) {
    this.setData({ currtab: e.detail.current })
  },

  clickPay(e) {
    var _ = this
    var index = e.currentTarget.dataset.index
    var order = _.data.dataPayList[index]
    var amount = _.data.dataPay[index].buyAmount
    wx.cloud.callFunction({
      name: "pay",
      data: {
        orderid: order,
        money: amount
      },
    }).then(res => {
      console.log("提交成功")
      console.log(res.result)
      _.pay(res.result, order, amount)
    })
  },
  pay(payData, order, amount) {
    var _ = this
    wx.requestPayment({
      nonceStr: payData.nonceStr,
      package: payData.package,
      paySign: payData.paySign,
      timeStamp: payData.timeStamp,
      signType: "MD5",
      success(res) {
        wx.request({
          url: 'https://www.dream-y.top:8888/user/updateStatusByOneFilter/buy_tradeno'+"&"+order+"&"+"交易成功",
          method: "POST",
          header: {
            'content-type': 'application/x-www-form-urlencoded',
          },
          success: res => {
            wx.requestSubscribeMessage({
              tmplIds: ['I-avoj7cO00Ds4ZmmuM1-9I8OHCDiM42JTuEHlU7nKs'],
              success(res) {
                _.onLoad()
                wx.cloud.callFunction({
                  name: "pushMsg",
                  data: {
                    order: order,
                    amount: amount,
                    date: DateToStr(new Date())
                  }
                })

                wx.request({
                  url: 'https://www.dream-y.top:8888/Function/sendRestockedMessage/',
                  method: "POST",
                  header: {
                    'content-type': 'application/x-www-form-urlencoded',
                  },
                  data: {
                    functionName: "sendMsgToOwner",
                    ownerOpenId: getApp().globalData.userData.open_id,
                    ownerName: getApp().globalData.userData.authenticationInfo.ownerName,
                    machineName: "test",
                    goodsName: "巧克力",
                    goodsTotal: 10
                  },
                  success: res => {
                    console.log(res)
                  }
                })
              }
            })
          }
        })
      },
      fail(res) {
      },
      complete(res) {
        console.log("支付完成", res)
      }
    })
  },
})