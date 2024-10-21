// 云函数入口文件
const cloud = require('wx-server-sdk')
 
cloud.init({env: cloud.DYNAMIC_CURRENT_ENV})
 
const db = cloud.database()
const _ = db.command
 
// 云函数入口函数
exports.main = async (event, context) => {
  try {
    return await db.collection(event.databaseName).doc(event.id).update({
      // data 传入需要局部更新的数据
      data: event.data
    })
  } catch (e) {
    console.error(e)
  }
}