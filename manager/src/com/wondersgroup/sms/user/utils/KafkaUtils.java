package com.wondersgroup.sms.user.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

import org.apache.activemq.util.Suspendable;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import tw.ecosystem.reindeer.config.RdConfig;

import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.dao.SmsUserDao;

import coral.base.app.AppContext;
import coral.base.quartz.Task;
import coral.base.quartz.TaskScheduled;

/**
 * CRON表达式    含义  "0 0 12 * * ?"    每天中午十二点触发  "0 15 10 ? * *"    每天早上10：15触发 
 * "0 15 10 * * ?"    每天早上10：15触发  "0 15 10 * * ? *"    每天早上10：15触发 
 * "0 15 10 * * ? 2005"    2005年的每天早上10：15触发  "0 * 14 * * ?"   
 * 每天从下午2点开始到2点59分每分钟一次触发  "0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发 
 * "0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发  "0 0-5 14 * * ?" 
 *   每天14:00至14:05每分钟一次触发  "0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发 
 * "0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发 0 0 10,14,16 * * ?
 * 每天上午10点，下午2点，4点 0 0/30 9-17 * * ?   朝九晚五工作时间内每半小时 0 0 12 ? * WED
 * 表示每个星期三中午12点  "0 0 12 * * ?" 每天中午12点触发  "0 15 10 ? * *" 每天上午10:15触发 
 * "0 15 10 * * ?" 每天上午10:15触发  "0 15 10 * * ? *" 每天上午10:15触发 
 * "0 15 10 * * ? 2005" 2005年的每天上午10:15触发  "0 * 14 * * ?"
 * 在每天下午2点到下午2:59期间的每1分钟触发  "0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发 
 * "0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发  "0 0-5 14 * * ?"
 * 在每天下午2点到下午2:05期间的每1分钟触发  "0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发 
 * "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发  "0 15 10 15 * ?" 每月15日上午10:15触发 
 * "0 15 10 L * ?" 每月最后一日的上午10:15触发  "0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发 
 * "0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发  "0 15 10 ? * 6#3"
 * 每月的第三个星期五上午10:15触发  0 0 0 3 3 ? 2016
 */
/**
 * 每次更新服务器的时候要修改@TaskScheduled参数
 * */

//@Component
//@TaskScheduled(cron = "0 30 18 * * ?")
public class KafkaUtils implements Task {
	private SmsUserDao smsUserDao;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		smsUserDao = (SmsUserDao) AppContext.getBean("smsUserDao");
		System.out.println("开始执行方法kafka方法");
		Properties properties = new Properties();
		// 正式 10.83.66.72:9092 // 测试117.184.226.150:9092
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"10.83.66.72:9092");// 连接服务器地址
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "ZZYX");// 根据自己部门设置不同的id，需要提前约定
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");// 数据序列化方式
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");// 数据序列化方式
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(
				properties);
		System.out.println("consumer:"+consumer);
		consumer.subscribe(Arrays.asList("DSJ"));
		while (true) {
			//System.out.println(1);
			ConsumerRecords<String, String> records = consumer.poll(1000);
			//System.out.println(2);
			//System.out.println(records);
			for (ConsumerRecord<String, String> record : records) {
				//System.out.println(3);
				System.out.printf("offset = %d, value = %s", record.offset(), record.value());
				System.out.println("记录内容："+record.value());
				if (!"".equals(StringUtils.trimToEmpty(record.value()))) {
					String result = record.value();
					JSONObject jsobject = JSONObject.parseObject(result);
					System.out.println("jsobject:"+jsobject);
					String app = jsobject.getString("applicationName");
					if(app.contains("ZZYX")){  //只获取ZZYX部门的账号
						SmsUser userName = smsUserDao.getUserName(jsobject.getString("loginname"));
						if (userName == null) {
							SmsUser smsUser = new SmsUser();
							smsUser.setStUserId(UUID.randomUUID().toString());
							smsUser.setStLoginName(jsobject.getString("loginname"));
							String name = new String(jsobject.getString("name"));
							smsUser.setStUserName(name);
							smsUser.setStPassword("d93ae65992caf6a8751e334d0a716ad8");// 123456
							String org_name = new String(jsobject.getString("org_name"));
							smsUser.setStOrgName(org_name);
							if (!"".equals(jsobject.getString("sex"))
									&& jsobject.getString("sex") != null) {
								smsUser.setStSex(new BigDecimal(jsobject
										.getString("sex"))); // 0：女 1 男
							}
							if (!"".equals(jsobject.getString("idcard"))
									&& jsobject.getString("idcard") != null) {
								smsUser.setStIdcard(jsobject.getString("idcard"));
							}
							if (!"".equals(jsobject.getString("email"))
									&& jsobject.getString("email") != null) {
								smsUser.setStEmail(jsobject.getString("email"));	
							}
							/*if(StringUtils.isNotEmpty(jsobject.getString("email"))){
								   byte[] email = AESUtil.encrypt(jsobject.getString("email"), RdConfig.get("reindeer.servlet.aes.key"));
								   smsUser.setStEmail(HexUtil.bytes2Hex(email));
							}*/
							
							smsUser.setStMobile(jsobject.getString("mobile"));
							smsUser.setNmLocked(new BigDecimal("1"));
							smsUser.setNmReceiveEmail(new BigDecimal("1"));
							smsUser.setDtCreate(new Timestamp(System
									.currentTimeMillis()));
							smsUserDao.add(smsUser);
						}else{
							SmsUser uUser = new SmsUser();
							uUser.setStUserId(userName.getStUserId());
							uUser.setStLoginName(jsobject.getString("loginname"));
							String name = new String(jsobject.getString("name"));
							uUser.setStUserName(name);
							uUser.setStPassword("d93ae65992caf6a8751e334d0a716ad8");// 123456
							String org_name = new String(jsobject.getString("org_name"));
							uUser.setStOrgName(org_name);
							if (!"".equals(jsobject.getString("sex"))
									&& jsobject.getString("sex") != null) {
								uUser.setStSex(new BigDecimal(jsobject
										.getString("sex"))); // 0：女 1 男
							}
							if (!"".equals(jsobject.getString("idcard"))
									&& jsobject.getString("idcard") != null) {
								uUser.setStIdcard(jsobject.getString("idcard"));
							}
							if (!"".equals(jsobject.getString("email"))
									&& jsobject.getString("email") != null) {
								uUser.setStEmail(jsobject.getString("email"));	
							}
							/*if(StringUtils.isNotEmpty(jsobject.getString("email"))){
								   byte[] email = AESUtil.encrypt(jsobject.getString("email"), RdConfig.get("reindeer.servlet.aes.key"));
								   smsUser.setStEmail(HexUtil.bytes2Hex(email));
							}*/
							
							uUser.setStMobile(jsobject.getString("mobile"));
							uUser.setNmLocked(new BigDecimal("1"));
							uUser.setNmReceiveEmail(new BigDecimal("1"));
							uUser.setDtUpdate(new Timestamp(System.currentTimeMillis()));
							smsUserDao.update(uUser);
						}
					}
						
				}
			}
		}
	}
	
	

}
