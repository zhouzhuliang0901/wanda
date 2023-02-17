package com.wondersgroup.infopub.service;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

import com.wondersgroup.infopub.bean.AreaView;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.bean.InfopubAttachment;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubDeviceInfoExt;
import com.wondersgroup.infopub.bean.InfopubDeviceInfoHis;
import com.wondersgroup.infopub.bean.InfopubDeviceLog;
import com.wondersgroup.infopub.bean.InfopubDeviceResult;
import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.infopub.bean.InfopubGroup;
import com.wondersgroup.infopub.bean.InfopubGroupDevice;
import com.wondersgroup.infopub.bean.InfopubOdeviceStatus;
import com.wondersgroup.infopub.bean.InfopubOnoff;
import com.wondersgroup.infopub.bean.InfopubPsource;
import com.wondersgroup.infopub.bean.InfopubPsourceExt;
import com.wondersgroup.infopub.bean.InfopubPublish;
import com.wondersgroup.infopub.bean.InfopubWorkspace;
import com.wondersgroup.sms.resource.bean.SmsResourceAccessList;

import coral.base.util.RequestWrapper;
@Service
public interface InfoPubService {

	/**
	 * 设备信息列表
	 * 
	 * @param httpReqRes
	 * @return
	 */
	JSONObject deviceInfoList(HttpReqRes httpReqRes);

	/**
	 * 街道设备信息列表
	 * 
	 * @param httpReqRes
	 * @return
	 */
	JSONObject streetDeviceList(HttpReqRes httpReqRes);
	/**
	 * 保存和更新设备信息
	 * 
	 * @param httpReqRes
	 * @return
	 */
	InfopubDeviceInfo saveOrUpdateDeviceInfo(HttpReqRes httpReqRes);

	/**
	 * 展示设备信息
	 * 
	 * @param httpReqRes
	 * @return
	 */
	InfopubDeviceInfo getDeviceInfo(HttpReqRes httpReqRes);

	/**
	 * 设备信息删除
	 * 
	 * @param httpReqRes
	 */
	void deviceRemove(HttpReqRes httpReqRes);

	/**
	 * 设备信息逻辑删除
	 *
	 * @param httpReqRes
	 */
	void deviceLogicRemove(HttpReqRes httpReqRes);
	/**
	 * 设备开关机信息
	 * 
	 * @param httpReqRes
	 * @return
	 */
	JSONObject deviceOnOffList(HttpReqRes httpReqRes);

	/**
	 * 获取设备开关信息
	 * 
	 * @param httpReqRes
	 * @return
	 */
	InfopubOnoff getDeviceOnOffInfo(HttpReqRes httpReqRes);

	/**
	 * 设备开关机时间设定
	 * 
	 * @param httpReqRes
	 * @param deviceId 
	 * @return
	 */
	InfopubOnoff saveOrUpdateDeviceOnOff(HttpReqRes httpReqRes, String deviceId);

	/**
	 * 开关机删除
	 * 
	 * @param httpReqRes
	 */
	void deviceOnOffRemove(HttpReqRes httpReqRes);

	/**
	 * 设备日志记录
	 * 
	 * @param httpReqRes
	 * @return
	 */
	JSONObject deviceLogList(HttpReqRes httpReqRes);

	/**
	 * 设备操作
	 * 
	 * @param httpReqRes
	 */
	//void deviceOperate(HttpReqRes httpReqRes, String stDeviceId);
	void deviceOperate(HttpReqRes httpReqRes, String deviceId);

	/**
	 * 开关机设置信息删除
	 * 
	 * @param httpReqRes
	 */
	void deviceLogRemove(HttpReqRes httpReqRes);

	/**
	 * 查看日志信息
	 * 
	 * @param httpReqRes
	 * @return
	 */
	InfopubDeviceLog getDeviceLogInfo(HttpReqRes httpReqRes);

	/**
	 * 获取截屏图片
	 * 
	 * @param httpReqRes
	 * @return
	 */
	List<String> getAttachIds(HttpReqRes httpReqRes);

	/**
	 * 信息发布用户空间列表
	 * 
	 * @param httpReqRes
	 * @return
	 */
	JSONObject workSpaceList(HttpReqRes httpReqRes);

	/**
	 * 信息发布用户空间保存
	 * 
	 * @param httpReqRes
	 * @r
	 */
	InfopubWorkspace workSpaceSave(HttpReqRes httpReqRes);

	/**
	 * 获取用户空间
	 * 
	 * @param httpReqRes
	 * @return
	 */
	InfopubWorkspace getWorkSpaceInfo(HttpReqRes httpReqRes);

	/**
	 * 删除用户空间
	 * 
	 * @param httpReqRes
	 */
	void workSpaceRemove(HttpReqRes httpReqRes);

	/**
	 * 获取空间用户信息
	 * 
	 * @param httpReqRes
	 * @return
	 */
	List<SmsResourceAccessList> checkUserSelect(HttpReqRes httpReqRes);

	/**
	 * 设备分组列表
	 * 
	 * @param httpReqRes
	 * @return
	 */
	JSONObject deviceGroupList(HttpReqRes httpReqRes);

	/**
	 * 更新或者保存设备分组
	 * 
	 * @param httpReqRes
	 * @return
	 */
	InfopubGroup deviceGroupSaveOrUpate(HttpReqRes httpReqRes);

	/**
	 * 查看设备分组
	 * 
	 * @param httpReqRes
	 * @return
	 */
	InfopubGroup getDeviceGroupInfo(HttpReqRes httpReqRes);

	/**
	 * 删除设备分组
	 * 
	 * @param httpReqRes
	 */
	void deviceGroupRemove(HttpReqRes httpReqRes);

	/**
	 * 组设备信息选择
	 * 
	 * @param httpReqRes
	 * @return
	 */
	List<InfopubGroupDevice> deviceGroupSelect(HttpReqRes httpReqRes);

	/**
	 * 设备分组中的设备
	 * @param httpReqRes
	 * @return
	 */
	List<InfopubDeviceInfo> groupDeviceSelect(HttpReqRes httpReqRes);
	
	
	/**
	 * 删除附件
	 * 
	 * @param httpReqRes
	 */
	void deviceAttachmentRemove(HttpReqRes httpReqRes);

	/**
	 * 发布源信息列表
	 * 
	 * @param httpReqRes
	 * @return
	 */
	JSONObject psrouceList(HttpReqRes httpReqRes);

	/**
	 * 更新或者保存发布源
	 * 
	 * @param httpReqRes
	 * @return
	 */
	InfopubPsource saveOrUpdatePsrouce(HttpReqRes httpReqRes);

	/**
	 * 查看发布源
	 * 
	 * @param httpReqRes
	 * @return
	 */
	InfopubPsourceExt getPsrouceInfo(HttpReqRes httpReqRes);

	/**
	 * 发布源删除
	 * 
	 * @param httpReqRes
	 */
	void psrouceRemove(HttpReqRes httpReqRes);

	/**
	 * 查看附件
	 * 
	 * @param httpReqRes
	 * @return
	 */
	InfopubAttachment getInfopubAttachment(HttpReqRes httpReqRes, String stAttachmentId);

	/**
	 * 保存修改附件
	 * 
	 * @param httpReqRes
	 * @param linkTable
	 * @param linkId
	 * @return
	 */
	InfopubAttachment saveOrUpdateAttachment(HttpReqRes httpReqRes,
			String linkTable, String linkId);

	/**
	 * 设备发布列表
	 * 
	 * @param httpReqRes
	 * @return
	 */
	JSONObject publishList(HttpReqRes httpReqRes);

	/**
	 * 更新或者保存设备发布
	 * 
	 * @param httpReqRes
	 * @return
	 */
	InfopubPublish saveOrUpdatePublish(HttpReqRes httpReqRes, String deviceId);

	/**
	 * 设备发布编辑
	 * 
	 * @param httpReqRes
	 * @return
	 */
	InfopubPublish getPublish(HttpReqRes httpReqRes);

	/**
	 * 设备发布删除
	 * 
	 * @param httpReqRes
	 */
	void publishRemove(HttpReqRes httpReqRes);

	/**
	 * 删除截图
	 * 
	 * @param httpReqRes
	 */
	void deviceAttachmentRemoveShot(HttpReqRes httpReqRes);

	/**
	 * 设备信息类别列表
	 * 
	 * @param httpReqRes
	 * @return
	 */
	JSONObject deviceInfoTypeList(HttpReqRes httpReqRes);

	/**
	 * 获取设备附属信息
	 * 
	 * @param httpReqRes
	 * @return
	 */
	InfopubDeviceInfo getDeviceInfoExt(HttpReqRes httpReqRes);
	
	
	
	/**
	 * 更新或者保存设备分类
	 * 
	 * @param httpReqRes
	 * @return
	 */
	InfopubDeviceType deviceInfoTypeSaveOrUpate(HttpReqRes httpReqRes);

	/**
	 * 查看设备分类
	 * 
	 * @param httpReqRes
	 * @return
	 */
	InfopubDeviceType getDeviceInfoType(HttpReqRes httpReqRes);

	/**
	 * 删除设备分类
	 * 
	 * @param httpReqRes
	 */
	void deviceInfoTypeRemove(HttpReqRes httpReqRes);
	
	/**
	 * 检查分类编码
	 * 
	 * @param wrapper
	 * @return
	 */
	public boolean checkTypeCode(RequestWrapper wrapper);
	
	
	
	/**
	 * 更新或者保存外设状态
	 * 
	 * @param httpReqRes
	 * @return
	 */
	public InfopubOdeviceStatus odeviceStatusSaveOrUpate(HttpReqRes httpReqRes);

	/**
	 * 查看外设状态(通过外设状态id)
	 * 
	 * @param httpReqRes
	 * @return
	 */
	public InfopubOdeviceStatus getOdeviceStatus(HttpReqRes httpReqRes);
	

	/**
	 * 获取外设状态(通过设备id和外设标识)
	 * @param httpReqRes
	 * @return
	 */
	InfopubOdeviceStatus getOdevice(HttpReqRes httpReqRes);

	
	
	/**
	 * 外设状态列表
	 * 
	 * @param httpReqRes
	 * @return
	 */
	JSONObject getOdeviceStatusList(HttpReqRes httpReqRes);

	/**
	 * 删除外设状态
	 * 
	 * @param httpReqRes
	 */
	public void odeviceStatusRemove(HttpReqRes httpReqRes);
	
	
	/**
	 * 方法描述：截图保存
	 * @param httpReqRes
	 * @return
	 */
	public InfopubAttachment saveImage(HttpReqRes httpReqRes);
	
	/**
	 * 方法描述：是否在线
	 * @param httpReqRes
	 * @return
	 */
	InfopubDeviceInfo isOnline(HttpReqRes httpReqRes);

	
	/**
	 * 方法描述：建行是否在线
	 * @param httpReqRes
	 * @return
	 */
	public JSONObject jhisonline(HttpReqRes httpReqRes);
	

	/**
	 * 方法描述：得到所有设备的所有信息 
	 * @return
	 */
	JSONObject getAlldeviceInfo(HttpReqRes httpReqRes);
	
	
	
	
	public List<AreaView> getSystemAllAreaTree();
	
	/**
	 * 根据主键 {@link INFOPUB_AREA#ST_AREA_ID}获取组织机构表
	 * 
	 * @param stAreaId
	 *            区域表主键 {@link INFOPUB_AREA#ST_AREA_ID}
	 * @return 区域表实例
	 */
	InfopubArea get(String stAreaId);

	/**
	 * 查询区域信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 区域表列表
	 */
	PaginationArrayList<InfopubArea> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link INFOPUB_AREA#ST_AREA_ID}删除组织机构表
	 * 
	 * @param stAreaId
	 *            区域表主键 {@link INFOPUB_AREA#ST_AREA_ID}
	 */
	void remove(String[] areaIdList);

	/**
	 * 保存或更新区域表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 区域表实例
	 */
	InfopubArea saveOrUpdate(RequestWrapper wrapper);

	/**
	 * 检查区域编码
	 * 
	 * @param wrapper
	 * @return
	 */
	public boolean checkAreaCode(RequestWrapper wrapper);

	/**
	 * 区域列表
	 * @param httpReqRes
	 * @return
	 */
	JSONObject areaList(HttpReqRes httpReqRes);

	/**
	 * 设备信息子类别列表
	 * @param httpReqRes
	 * @return
	 */
	JSONObject subDeviceInfoTypeList(HttpReqRes httpReqRes);

	/**
	 * 保存修改信息，发送修改xml操作指令，
	 * @param httpReqRes
	 * @return
	 */
	String modifyOperate(HttpReqRes httpReqRes);

	/**
	 * 修改xml文件
	 * @param httpReqRes
	 * @return
	 */
	String modifyXML(HttpReqRes httpReqRes);

	/**
	 * 保存设备的资源使用情况
	 * @param httpReqRes
	 */
	InfopubDeviceResult addOrUpdate(HttpReqRes httpReqRes) throws Exception;

	

	
	/**
	 * 外设调用,外设的状态保存
	 * @param httpReqRes
	 * @return
	 */
	InfopubOdeviceStatus outDeviceStatusSave(HttpReqRes httpReqRes);

	
	/**
	 * 设备报警下的外设报警状态列表
	 * @param httpReqRes
	 * @return
	 */
	JSONObject getOdevicewarnList(HttpReqRes httpReqRes);

	/**
	 * 获取外设状态(通过设备id和外设标识)
	 * @param httpReqRes
	 * @return
	 */
	InfopubOdeviceStatus getOutdeviceStatus(HttpReqRes httpReqRes);

	/**
	 * 设备MAC唯一性检查
	 * @param wrapper
	 * @return
	 */
	boolean checkDeviceMac(RequestWrapper wrapper);

	JSONObject getOdeviceStatusInfoListCount(HttpReqRes httpReqRes);

	JSONObject getOdeviceStatusInfoListdate(HttpReqRes httpReqRes);

	Object appDeviceInfoList(HttpReqRes httpReqRes);

	JSONObject deviceInfoListMac(HttpReqRes httpReqRes);

	InfopubDeviceInfo getDeviceId(String stDeviceId);

	InfopubDeviceInfo getCode(String stCode);

	InfopubDeviceType getDeviceType(String stTypeId);

	InfopubDeviceInfo deviceImport(HttpReqRes httpReqRes);

	InfopubDeviceInfo getMac(String stCode);

	InfopubDeviceInfo CertKeySaveOrUpdate(HttpReqRes httpReqRes);

	JSONObject leading(HttpReqRes httpReqRes);

	JSONObject loadingXML(HttpReqRes httpReqRes);

	InfopubAttachment getById(String stAttachId);

	JSONObject deviceDistrict(HttpReqRes httpReqRes);

	JSONObject deviceStreet(HttpReqRes httpReqRes);

	InfopubAttachment uploadDebug(String stAttachId, String fileName,
			String fileType, byte[] file, int len, String applytitle,
			String applycontent);

	void downLoad(HttpReqRes httpReqRes);

	JSONObject itemDeviceList(HttpReqRes httpReqRes);

	InfopubDeviceInfo KeyEncryptionSaveOrUpdate(HttpReqRes httpReqRes);

	JSONObject getCertKey(HttpReqRes httpReqRes);

	JSONObject infopubMapList(HttpReqRes httpReqRes);

	JSONObject itemLinkDevice(HttpReqRes httpReqRes);

	JSONObject deviceTypeOptionList(HttpReqRes httpReqRes);

	JSONObject odeviceInfoList(HttpReqRes httpReqRes);

	void odeviceInfoRemove(HttpReqRes httpReqRes);

	InfopubDeviceType odeviceInfoSaveOrUpate(HttpReqRes httpReqRes);

	JSONObject deviceWarnInfoList(HttpReqRes httpReqRes);

	JSONObject deviceWarnInfoCount(HttpReqRes httpReqRes);

	JSONObject deviceInfoTypeInit(HttpReqRes httpReqRes);

	JSONObject wGetDeviceByTime(HttpReqRes httpReqRes);

	JSONObject wGetItemByDevice(HttpReqRes httpReqRes);

	InfopubDeviceInfoHis saveOrUpdateDeviceInfoHis(InfopubDeviceInfo info,String state);

	JSONObject isonlineofmap(HttpReqRes httpReqRes);

	JSONObject batchisonlineofmap(HttpReqRes httpReqRes);

	JSONObject mGetDevInfo(HttpReqRes httpReqRes);

	JSONObject deviceAddressInit(HttpReqRes httpReqRes);

	JSONObject InitDeviceType(HttpReqRes httpReqRes);

	JSONObject mGetDeviceByTime(HttpReqRes httpReqRes);

	JSONObject mGetItemByTime(HttpReqRes httpReqRes);

	JSONObject mGetItemByDevice(HttpReqRes httpReqRes);

	



}
