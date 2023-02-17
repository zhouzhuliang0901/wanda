package com.wondersgroup.outdevicestatus.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wondersgroup.outdevicestatus.bean.InfopubDeviceInfo;
import com.wondersgroup.outdevicestatus.bean.InfopubDeviceType;
import com.wondersgroup.outdevicestatus.bean.InfopubOdeviceStatus;
import com.wondersgroup.outdevicestatus.dao.InfopubDeviceInfoDao;
import com.wondersgroup.outdevicestatus.dao.InfopubDeviceTypeDao;
import com.wondersgroup.outdevicestatus.dao.InfopubOdeviceResultDao;
import com.wondersgroup.outdevicestatus.dao.InfopubOdeviceStatusDao;
import com.wondersgroup.outdevicestatus.service.OdeviceStatusService;
import com.wondersgroup.outdevicestatus.util.Decode;

import tw.ecosystem.reindeer.web.HttpReqRes;

@Service
@Transactional
public class OdeviceStatusServiceImpl implements OdeviceStatusService {
	
	@Autowired
	private InfopubOdeviceStatusDao infopubOdeviceStatusDao;
	@Autowired
	private InfopubOdeviceResultDao infopubOdeviceResultDao;
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	@Autowired
	private InfopubDeviceTypeDao infopubDeviceTypeDao;

	/**
	 * 添加或更新外设状态
	 */
	@SuppressWarnings("all")
	@Override
	public InfopubOdeviceStatus outDeviceStatusSave(HttpReqRes httpReqRes) {
		// 设备id
		String stDeviceId = httpReqRes.getParameter("stDeviceId");
		if (stDeviceId == null || StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
			try {
				Log.info("设备id为空");
			} catch (Exception e) {
				throw new NullPointerException("stDeviceId为null"); 
			}
			return null;
		}
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(stDeviceId);
		if (deviceInfo == null) {
			try {
				Log.info("设备不存在");
			} catch (Exception e) {
				throw new NullPointerException(); 
			}
			//throw new NullPointerException("设备不存在");
			return null;
		}
		String stTypeId = deviceInfo.getStTypeId();
		if (stTypeId == null || StringUtils.trimToEmpty(stTypeId).isEmpty()) {
			try {
				Log.info("该设备类型为空，没有关联外设");
			} catch (Exception e) {
				throw new NullPointerException(); 
			}
			//throw new NullPointerException("该设备类型为空，没有关联外设"); 
			return null;
		}
		
		List<InfopubDeviceType> list = infopubDeviceTypeDao.getByPrentId(stTypeId);
		// 外设标识
		String outDeviceCode = httpReqRes.getParameter("stOutDeviceCode");
		String stOutDeviceCode = Decode.decode(outDeviceCode, "utf-8");
		
		boolean flag = false;
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				InfopubDeviceType info = list.get(i);
				if (stOutDeviceCode.equals(info.getStTypeCode())) {
					flag = true;
				}
			}
		}
		
		if (flag==false) {
			try {
				Log.info("该设备没有" + outDeviceCode);
			} catch (Exception e) {
				throw new NullPointerException(); 
			}
			//throw new NullPointerException(); 
		}
		
		// 是否异常
		// Integer nmException = httpReqRes.getParameterInteger("nmException");
		BigDecimal nmException = httpReqRes
				.getParameterBigdecimal("nmException");
		if (nmException == null) {
			nmException = new BigDecimal("0");
			//return null;
		}
		// 异常原因
		String cause = httpReqRes.getParameter("stCause");
		if (cause == null || StringUtils.trimToEmpty(cause).isEmpty()) {
			cause=""; 
		}
		String stCause = Decode.decode(cause, "utf-8");
		// 是否通知
		// Integer nmNotice = httpReqRes.getParameterInteger("nmNotice");
		BigDecimal nmNotice = httpReqRes.getParameterBigdecimal("nmNotice");
		if (nmException == null) {
			nmException = new BigDecimal("0");
			//return null;
		}
		// 总量
		// Integer nmTotal = httpReqRes.getParameterInteger("nmTotal");
		BigDecimal nmTotal = httpReqRes.getParameterBigdecimal("nmTotal");
		if (nmTotal == null) {
			nmTotal = new BigDecimal("0");
			//return null;
		}
		// 剩余量
		// Integer nmRemain = httpReqRes.getParameterInteger("nmRemain");
		BigDecimal nmRemain = httpReqRes.getParameterBigdecimal("nmRemain");
		if (nmTotal == null) {
			nmTotal = new BigDecimal("0");
			//return null;
		}
		// int intnmRemain = nmRemain.intValue();
		// 拓展1
		String stExt1 = httpReqRes.getParameter("stExt1");
		//String stExt1 = Decode.decode(ext1, "utf-8");
		// 拓展2
		String stExt2 = httpReqRes.getParameter("stExt2");
		//String stExt2 = Decode.decode(ext2, "utf-8");
		// 存放使用量
		Integer count = httpReqRes.getParameterInteger("count");
		if (count == null) {
			count = 0;
			//return null;
		}
		BigDecimal useCount = new BigDecimal(count.toString());
		
		// 用来判断  居住证擦写设备(ResidenceErased)是否清零，type=0 不清0，照常调用，type=1,清0
		String type = httpReqRes.getParameter("type");
		if (type == null || StringUtils.trimToEmpty(type).isEmpty()) {
			type = "0"; 
		}
		
		if (stOutDeviceCode.equals("ResidenceErased")) {
			if (nmException.compareTo(new BigDecimal(1)) == 0) {
				InfopubOdeviceStatus infoException = new InfopubOdeviceStatus();
				infoException.setStOutDeviceCode(stOutDeviceCode);
				infoException.setStDeviceId(stDeviceId);
				infoException.setNmException(nmException);
				infoException.setNmNotice(nmNotice);
				infoException.setStCause(stCause);
				//infoException.setNmTotal(nmTotal);
				//infoException.setNmRemain(nmRemain);
				infoException.setStExt1(stExt1);
				infoException.setStExt2(stExt2);
				infopubOdeviceResultDao.add(infoException);
			}
			if (type.equals("0")) {
				InfopubOdeviceStatus oldInfopubOdeviceStatus = null;
				if (stDeviceId != null
						&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
					if (stOutDeviceCode != null
							&& !StringUtils.trimToEmpty(stOutDeviceCode).isEmpty()) {
						InfopubOdeviceStatus info = new InfopubOdeviceStatus();
						info.setStDeviceId(stDeviceId);
						info.setStOutDeviceCode(stOutDeviceCode);
						oldInfopubOdeviceStatus = infopubOdeviceStatusDao
								.getOdeviceStatus(info);
					}
				}
				if (oldInfopubOdeviceStatus != null) {
					// 更新外设状态
					// 没有异常，成功总次数+1,否则失败总次数+1
					if (nmException.compareTo(new BigDecimal(0)) == 0) {
						BigDecimal newNmHisStotal = oldInfopubOdeviceStatus.getNmHisStotal().add(new BigDecimal(1));
						oldInfopubOdeviceStatus.setNmHisStotal(newNmHisStotal);
						BigDecimal newNmTotal = oldInfopubOdeviceStatus.getNmTotal().add(new BigDecimal(1));
						oldInfopubOdeviceStatus.setNmTotal(newNmTotal);
					} else {
						BigDecimal newNmHisFtotal = oldInfopubOdeviceStatus.getNmHisFtotal().add(new BigDecimal(1));
						oldInfopubOdeviceStatus.setNmHisFtotal(newNmHisFtotal);
					}
					infopubOdeviceStatusDao.update(oldInfopubOdeviceStatus);
					return oldInfopubOdeviceStatus;
				}else {
					InfopubOdeviceStatus newInfopubOdeviceStatus = new InfopubOdeviceStatus();
					newInfopubOdeviceStatus.setStOutDeviceResultId(UUID.randomUUID()
							.toString());
					newInfopubOdeviceStatus.setStDeviceId(stDeviceId);
					newInfopubOdeviceStatus.setStOutDeviceCode(stOutDeviceCode);
					newInfopubOdeviceStatus.setNmException(nmException);
					newInfopubOdeviceStatus.setStCause(stCause);
					newInfopubOdeviceStatus.setNmNotice(nmNotice);
					newInfopubOdeviceStatus.setDtUpdate(new Timestamp(System
							.currentTimeMillis()));
					newInfopubOdeviceStatus.setStExt1(stExt1);
					newInfopubOdeviceStatus.setStExt2(stExt2);
					// 没有异常，成功总次数+1,否则失败总次数+1
					if (nmException.compareTo(new BigDecimal(0)) == 0) {
						// 没有异常，成功总次数+1,否则失败总次数+1
						newInfopubOdeviceStatus.setNmHisStotal(new BigDecimal(1));
						newInfopubOdeviceStatus.setNmHisFtotal(new BigDecimal(0));
						newInfopubOdeviceStatus.setNmTotal(new BigDecimal(1));
					} else {
						newInfopubOdeviceStatus.setNmHisStotal(new BigDecimal(0));
						newInfopubOdeviceStatus.setNmHisFtotal(new BigDecimal(1));
					}
					infopubOdeviceStatusDao.add(newInfopubOdeviceStatus);
					return newInfopubOdeviceStatus;
				}
			}else if (type.equals("1")) {
				InfopubOdeviceStatus oldInfopubOdeviceStatus = null;
				if (stDeviceId != null
						&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
					if (stOutDeviceCode != null
							&& !StringUtils.trimToEmpty(stOutDeviceCode).isEmpty()) {
						InfopubOdeviceStatus info = new InfopubOdeviceStatus();
						info.setStDeviceId(stDeviceId);
						info.setStOutDeviceCode(stOutDeviceCode);
						oldInfopubOdeviceStatus = infopubOdeviceStatusDao
								.getOdeviceStatus(info);
					}
				}
				if (oldInfopubOdeviceStatus != null) {
					// 更新外设状态
					// 没有异常，成功总次数+1,否则失败总次数+1
					if (nmException.compareTo(new BigDecimal(0)) == 0) {
						BigDecimal newNmHisStotal = oldInfopubOdeviceStatus.getNmHisStotal().add(new BigDecimal(1));
						oldInfopubOdeviceStatus.setNmHisStotal(newNmHisStotal);
						oldInfopubOdeviceStatus.setNmTotal(new BigDecimal(0));
					} else {
						BigDecimal newNmHisFtotal = oldInfopubOdeviceStatus.getNmHisFtotal().add(new BigDecimal(1));
						oldInfopubOdeviceStatus.setNmHisFtotal(newNmHisFtotal);
					}
					infopubOdeviceStatusDao.update(oldInfopubOdeviceStatus);
					return oldInfopubOdeviceStatus;
				}else {
					return null;
				}
			}
			
			return null;
			
		}else {
			//判断是否异常，异常加入历史
			if (nmException.compareTo(new BigDecimal(1)) == 0) {
				InfopubOdeviceStatus infoException = new InfopubOdeviceStatus();
				infoException.setStOutDeviceCode(stOutDeviceCode);
				infoException.setStDeviceId(stDeviceId);
				infoException.setNmException(nmException);
				infoException.setNmNotice(nmNotice);
				infoException.setStCause(stCause);
				infoException.setNmTotal(nmTotal);
				infoException.setNmRemain(nmRemain);
				infoException.setStExt1(stExt1);
				infoException.setStExt2(stExt2);
				infopubOdeviceResultDao.add(infoException);
			}
			
			InfopubOdeviceStatus oldInfopubOdeviceStatus = null;
			if (stDeviceId != null
					&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
				if (stOutDeviceCode != null
						&& !StringUtils.trimToEmpty(stOutDeviceCode).isEmpty()) {
					InfopubOdeviceStatus info = new InfopubOdeviceStatus();
					info.setStDeviceId(stDeviceId);
					info.setStOutDeviceCode(stOutDeviceCode);
					oldInfopubOdeviceStatus = infopubOdeviceStatusDao
							.getOdeviceStatus(info);
				}
			}
			if (oldInfopubOdeviceStatus != null) {
				// 打印机放纸或其他外设
				if (count == 0) {
					//判断外设状态是否异常，异常将oldInfopubOdeviceStatus存储到历史中
					/*if (nmException.compareTo(new BigDecimal(1)) == 0) {
					if (stCause != null && oldInfopubOdeviceStatus.getStCause() != null) {
						if (stCause.equals(oldInfopubOdeviceStatus.getStCause())) {
							if (nmNotice.compareTo(oldInfopubOdeviceStatus.getNmNotice()) == 0) {
								if (nmTotal.compareTo(oldInfopubOdeviceStatus.getNmTotal()) == 0) {
									System.out.println("外设状态未改变....");
								}else {
									infopubOdeviceResultDao.add(oldInfopubOdeviceStatus);
								}
							} else {
								infopubOdeviceResultDao.add(oldInfopubOdeviceStatus);
							}
						}else {
							infopubOdeviceResultDao.add(oldInfopubOdeviceStatus);
						}
					}else if (stCause == null && oldInfopubOdeviceStatus.getStCause() == null) {
						if (nmNotice.compareTo(oldInfopubOdeviceStatus.getNmNotice()) == 0) {
							if (nmTotal.compareTo(oldInfopubOdeviceStatus.getNmTotal()) == 0) {
								System.out.println("外设状态未改变....");
							}else {
								infopubOdeviceResultDao.add(oldInfopubOdeviceStatus);
							}
						} else {
							infopubOdeviceResultDao.add(oldInfopubOdeviceStatus);
						}
					}else {
						infopubOdeviceResultDao.add(oldInfopubOdeviceStatus);
					}
				}else {
					infopubOdeviceResultDao.add(oldInfopubOdeviceStatus);
				}*/
					// 判断nmException是否变化，发生变化，添加到历史中。
					/*if (nmException.compareTo(oldInfopubOdeviceStatus.getNmException()) != 0) {
						infopubOdeviceResultDao.add(oldInfopubOdeviceStatus);
					}*/
					// 更新外设状态
					// 没有异常，成功总次数+1,否则失败总次数+1
					if (nmException.compareTo(new BigDecimal(0)) == 0) {
						BigDecimal newNmHisStotal = oldInfopubOdeviceStatus.getNmHisStotal().add(new BigDecimal(1));
						oldInfopubOdeviceStatus.setNmHisStotal(newNmHisStotal);
					} else {
						BigDecimal newNmHisFtotal = oldInfopubOdeviceStatus.getNmHisFtotal().add(new BigDecimal(1));
						oldInfopubOdeviceStatus.setNmHisFtotal(newNmHisFtotal);
					}
					oldInfopubOdeviceStatus.setNmException(nmException);
					oldInfopubOdeviceStatus.setNmNotice(nmNotice);
					oldInfopubOdeviceStatus.setStCause(stCause);
					oldInfopubOdeviceStatus.setDtUpdate(new Timestamp(System
							.currentTimeMillis()));
					
					// 判断nmException是否为1，为1则异常，结束方法。
					if (nmException.compareTo(new BigDecimal(1)) == 0) {
						infopubOdeviceStatusDao.update(oldInfopubOdeviceStatus);
						return oldInfopubOdeviceStatus;
					}
					BigDecimal oldnmHisTotal = oldInfopubOdeviceStatus.getNmHisTotal();
					BigDecimal newnmHisTotal = oldnmHisTotal.add(nmTotal);
					oldInfopubOdeviceStatus.setNmHisTotal(newnmHisTotal);
					// 判断剩余量是大于零还是小于零
					BigDecimal oldnmRemain = oldInfopubOdeviceStatus.getNmRemain();
					// nmTotal = nmTotal + 剩余量
					oldInfopubOdeviceStatus.setNmTotal(nmTotal.add(oldnmRemain));
					oldInfopubOdeviceStatus.setNmRemain(nmTotal.add(oldnmRemain));
					
					oldInfopubOdeviceStatus.setStExt1(stExt1);
					oldInfopubOdeviceStatus.setStExt2(stExt2);
					
					infopubOdeviceStatusDao.update(oldInfopubOdeviceStatus);
					return oldInfopubOdeviceStatus;
				} else {
					// 打印机打印
					// 判断nmException是否变化，发生变化，添加到历史中。
					/*if (nmException.compareTo(oldInfopubOdeviceStatus.getNmException()) != 0) {
						infopubOdeviceResultDao.add(oldInfopubOdeviceStatus);
					}*/
					if (nmException.compareTo(new BigDecimal(0)) == 0) {
						// 没有异常，成功总次数+1,否则失败总次数+1
						BigDecimal newNmHisStotal = oldInfopubOdeviceStatus.getNmHisStotal().add(new BigDecimal(1));
						oldInfopubOdeviceStatus.setNmHisStotal(newNmHisStotal);
					} else {
						BigDecimal newNmHisFtotal = oldInfopubOdeviceStatus.getNmHisFtotal().add(new BigDecimal(1));
						oldInfopubOdeviceStatus.setNmHisFtotal(newNmHisFtotal);
					}
					oldInfopubOdeviceStatus.setNmException(nmException);
					oldInfopubOdeviceStatus.setNmNotice(nmNotice);
					oldInfopubOdeviceStatus.setStCause(stCause);
					oldInfopubOdeviceStatus.setDtUpdate(new Timestamp(System
							.currentTimeMillis()));
					
					oldInfopubOdeviceStatus.setStExt1(stExt1);
					oldInfopubOdeviceStatus.setStExt2(stExt2);
					
					// 判断nmException是否为1，为1则异常，结束方法。
					if (nmException.compareTo(new BigDecimal(1)) == 0) {
						infopubOdeviceStatusDao.update(oldInfopubOdeviceStatus);
						return oldInfopubOdeviceStatus;
					}
					// 查询到的剩余量
					BigDecimal oldnmRemain = oldInfopubOdeviceStatus.getNmRemain();
					// 减少后
					BigDecimal newnmRemain = oldnmRemain.subtract(useCount);
					if (newnmRemain.intValue() < 0) {
						newnmRemain = new BigDecimal(0);
					}
					oldInfopubOdeviceStatus.setNmRemain(newnmRemain);
					infopubOdeviceStatusDao.update(oldInfopubOdeviceStatus);
					return oldInfopubOdeviceStatus;
				}
			} else {
				if (count == 0) {
					InfopubOdeviceStatus newInfopubOdeviceStatus = new InfopubOdeviceStatus();
					newInfopubOdeviceStatus.setStOutDeviceResultId(UUID.randomUUID()
							.toString());
					newInfopubOdeviceStatus.setStDeviceId(stDeviceId);
					newInfopubOdeviceStatus.setStOutDeviceCode(stOutDeviceCode);
					newInfopubOdeviceStatus.setNmException(nmException);
					newInfopubOdeviceStatus.setStCause(stCause);
					newInfopubOdeviceStatus.setNmNotice(nmNotice);
					newInfopubOdeviceStatus.setDtUpdate(new Timestamp(System
							.currentTimeMillis()));
					newInfopubOdeviceStatus.setStExt1(stExt1);
					newInfopubOdeviceStatus.setStExt2(stExt2);
					/*
				// 判断nmException是否为1，为1则异常，保存到历史中。
				if (nmException.compareTo(new BigDecimal(1)) == 0) {
					infopubOdeviceResultDao.add(newInfopubOdeviceStatus);
				}
					 */
					// 没有异常，成功总次数+1,否则失败总次数+1
					if (nmException.compareTo(new BigDecimal(0)) == 0) {
						// 没有异常，成功总次数+1,否则失败总次数+1
						newInfopubOdeviceStatus.setNmHisStotal(new BigDecimal(1));
						newInfopubOdeviceStatus.setNmHisFtotal(new BigDecimal(0));
					} else {
						newInfopubOdeviceStatus.setNmHisStotal(new BigDecimal(0));
						newInfopubOdeviceStatus.setNmHisFtotal(new BigDecimal(1));
					}
					newInfopubOdeviceStatus.setNmHisTotal(nmTotal);
					newInfopubOdeviceStatus.setNmTotal(nmTotal);
					newInfopubOdeviceStatus.setNmRemain(nmRemain);
					infopubOdeviceStatusDao.add(newInfopubOdeviceStatus);
					return newInfopubOdeviceStatus;
				} else {
					InfopubOdeviceStatus newInfopubOdeviceStatus = new InfopubOdeviceStatus();
					newInfopubOdeviceStatus.setStOutDeviceResultId(UUID.randomUUID()
							.toString());
					newInfopubOdeviceStatus.setStDeviceId(stDeviceId);
					newInfopubOdeviceStatus.setStOutDeviceCode(stOutDeviceCode);
					newInfopubOdeviceStatus.setNmException(nmException);
					newInfopubOdeviceStatus.setStCause(stCause);
					newInfopubOdeviceStatus.setNmNotice(nmNotice);
					newInfopubOdeviceStatus.setDtUpdate(new Timestamp(System
							.currentTimeMillis()));
					newInfopubOdeviceStatus.setStExt1(stExt1);
					newInfopubOdeviceStatus.setStExt2(stExt2);
					/*
					// 判断nmException是否为1，为1则异常，保存到历史中。
					if (nmException.compareTo(new BigDecimal(1)) == 0) {
					infopubOdeviceResultDao.add(newInfopubOdeviceStatus);
					}
					 */
					// 没有异常，成功总次数+1,否则失败总次数+1
					if (nmException.compareTo(new BigDecimal(0)) == 0) {
						// 没有异常，成功总次数+1,否则失败总次数+1
						newInfopubOdeviceStatus.setNmHisStotal(new BigDecimal(1));
						newInfopubOdeviceStatus.setNmHisFtotal(new BigDecimal(0));
					} else {
						newInfopubOdeviceStatus.setNmHisStotal(new BigDecimal(0));
						newInfopubOdeviceStatus.setNmHisFtotal(new BigDecimal(1));
					}
					newInfopubOdeviceStatus.setNmTotal(nmTotal);
					BigDecimal newnmRemain = nmRemain.subtract(useCount);
					if (newnmRemain.intValue() < 0) {
						newnmRemain = new BigDecimal(0);
					}
					newInfopubOdeviceStatus.setNmRemain(newnmRemain);
					newInfopubOdeviceStatus.setNmHisTotal(nmTotal);
					infopubOdeviceStatusDao.add(newInfopubOdeviceStatus);
					return newInfopubOdeviceStatus;
				}
			}
		}

	}

	/**
	 * 获取外设状态(通过设备id和外设标识)
	 */
	@Override
	public InfopubOdeviceStatus getOdeviceStatus(HttpReqRes httpReqRes) {
		String stDeviceId = httpReqRes.getParameter("stDeviceId");
		String stOutDeviceCode = httpReqRes.getParameter("stOutDeviceCode");
		if (stDeviceId != null && !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
			if (stOutDeviceCode != null && !StringUtils.trimToEmpty(stOutDeviceCode).isEmpty()) {
				InfopubOdeviceStatus info = new InfopubOdeviceStatus();
				info.setStDeviceId(stDeviceId);
				info.setStOutDeviceCode(stOutDeviceCode);
				return infopubOdeviceStatusDao.getOdeviceStatus(info);
			}
		}
		return null;
	}
	
	/**
	 * 查看外设状态(通过设备id)
	 */
	@Override
	public List<InfopubOdeviceStatus> getOdeviceStatusByDeviceId(
			HttpReqRes httpReqRes) {
		String stDeviceId = httpReqRes.getParameter("stDeviceId");
		if (stDeviceId != null && !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
			return infopubOdeviceStatusDao.getByDeviceId(stDeviceId);
		}
	    throw new NullPointerException("设备ID不能为空");
	}
	

	/**
	 * 删除外设状态
	 */
	@Override
	public void odeviceStatusRemove(HttpReqRes httpReqRes) {
		String[] stOutDeviceResultIdList = httpReqRes.getRequest().getParameterValues(
				"stOutDeviceResultId[]");
		if (stOutDeviceResultIdList == null) {
			String stOutDeviceResultId = httpReqRes.getRequest()
					.getParameter("stOutDeviceResultId");
			if (stOutDeviceResultId != null
					&& !StringUtils.trimToEmpty(stOutDeviceResultId).isEmpty()) {
				infopubOdeviceStatusDao.delete(stOutDeviceResultId);
				return;
			} else {
				throw new NullPointerException("外设状态结果ID不能为空");
			}
		}
		infopubOdeviceStatusDao.delete(stOutDeviceResultIdList);
	}

	
	
}
