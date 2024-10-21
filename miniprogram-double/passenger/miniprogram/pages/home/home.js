var app = getApp()
var socket = null
Page({
  data: {
    data: [],
    imgUrls: [
      'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/17220675/O1CN01aBz0rL1GrC8WW6OsM_!!0-saturn_solar.jpg_360x360Q90.jpg_.webp',
      '	https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i2/111647195/O1CN016kO3Gz231MUevdOdd_!!0-saturn_solar.jpg_360x360Q90.jpg_.webp',
      'https://picasso.alicdn.com/imgextra/O1CNA1dd814p1wk3py0KMJt_!!185586345-0-psf.jpg_360x360Q90.jpg_.webp'
    ],
    indicatorDots: true,
    autoplay: true,
    interval: 3000,
    duration: 1000,
    navItems: [{
        name: '商品1',
        url: 'dishes'
      },
      {
        name: '商品2',
        url: 'take',
        isSplot: true
      },
      {
        name: '商品3',
        url: 'out'
      },
      {
        name: '商品4',
        url: 'bill'
      },
      {
        name: '商品5',
        url: 'bill',
        isSplot: true
      },
      {
        name: '商品6',
        url: 'bill'
      }
    ]
  },
  onLoad: function (e) {
    /*
    var object = {
      a: "aaa",
      b: "bbb"
    }
    console.log(Object.keys(object))
    */
    var _ = this
    socket = wx.connectSocket({
      url: 'wss://www.dream-y.top:8888/websocket/'+e.content,
    })
    socket.onMessage(res => {
      if (res.data != null){
        wx.redirectTo({
          url: '../finish/finish',
        })
        wx.closeSocket()
      }
    })
    wx.request({
      url: 'https://www.dream-y.top:8888/user/selectGoods/owner_id&'+getApp().globalData.owner_id,
      method: "POST",
      header: {
        'content-type': 'application/x-www-form-urlencoded',
      },
      success: res => {
        var itemList = res.data
        for (var i in itemList) {
          if (itemList[i].goodsName.length>6) {
            itemList[i].goodsName = itemList[i].goodsName.substring(0,5)+"..."
          }
        }
        _.setData({
          data: itemList
        })
      }
    })
  },
})