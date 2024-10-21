// 云函数入口文件
const cloud = require('wx-server-sdk')

cloud.init({ env: cloud.DYNAMIC_CURRENT_ENV }) // 使用当前云环境

// 云函数入口函数
exports.main = async (event, context) => {
  const wxContext = cloud.getWXContext()

  try {
    const result = await cloud.openapi.subscribeMessage.send({
      touser: wxContext.OPENID,
      page: '',
      data: {
        character_string1: {
          value: event.order
        },
        amount4: {
          value: event.amount
        },
        phrase10: {
          value: "已支付"
        },
        time6: {
          value: event.date
        },
        thing11: {
          value: "支付成功，欢迎您下次光临！"
        }
      },
      templateId: "I-avoj7cO00Ds4ZmmuM1-9I8OHCDiM42JTuEHlU7nKs"
    })
    return result
  } catch (err) {
    return err
  }
}