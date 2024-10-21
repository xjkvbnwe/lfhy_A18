//云开发实现支付
const cloud = require('wx-server-sdk')
cloud.init( {env: cloud.DYNAMIC_CURRENT_ENV})
 
//1，引入支付的三方依赖
const tenpay = require('tenpay');
//2，配置支付信息
const config = {
  appid: 'wx34df4762c2b9d324', 
  mchid: '1637003853',
  partnerKey: 'lingfenghongyang1234567812345678', 
  notify_url: 'http://www.baidu.com', 
  spbill_create_ip: '127.0.0.1' //这里填这个就可以
};
 
exports.main = async(event, context) => {
  const wxContext = cloud.getWXContext()
  let {
    orderid,
    money
  } = event;
  //3，初始化支付
  const api = tenpay.init(config);
 
  let result = await api.getPayParams({
    out_trade_no: orderid,
    body: '随手买乘客下单',
    total_fee: money, //订单金额(分),
    openid: wxContext.OPENID //付款用户的openid
  });
  return result;
}