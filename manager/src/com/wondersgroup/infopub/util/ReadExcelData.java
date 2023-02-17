package com.wondersgroup.infopub.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.app.bean.SelmDeviceItem;
import com.wondersgroup.app.bean.SelmItem;
import com.wondersgroup.app.bean.SelmItemLink;
import com.wondersgroup.app.dao.SelmDeviceItemDao;
import com.wondersgroup.app.dao.SelmItemDao;
import com.wondersgroup.app.dao.SelmItemLinkDao;
import com.wondersgroup.app.dao.SelmItemTypeDao;
import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.bean.InfopubCompany;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.infopub.dao.InfopubAddressDao;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.infopub.dao.InfopubCompanyDao;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.infopub.dao.InfopubDeviceTypeDao;

/**
 * 读取excel表格的工具类
 * 
 * @author biany
 * 
 */
public class ReadExcelData {

	/*
	 * 获取指定表格中的所有数据,并返回集合list
	 */
	/**
	 * @param file
	 * @return
	 */
	public static List<InfopubAddress> getDataByExcel(String file) {
		List<InfopubAddress> list = new ArrayList<InfopubAddress>();
		String district = null;
		String street = null;
		String addressLabel = null;
		String addressName = null;
		String lat = null; //纬度
		String lng = null; //经度
		try {
			File excel = new File(file);
			if (excel.isFile() && excel.exists()) { // 判断文件是否存在
				String[] split = excel.getName().split("\\."); // .是特殊字符，需要转义！！！！！
				Workbook wb;
				// 根据文件后缀（xls/xlsx）进行判断
				FileInputStream fis = new FileInputStream(excel); // 文件流对象
				if ("xls".equals(split[1])) {
					wb = new HSSFWorkbook(fis);
				} else if ("xlsx".equals(split[1])) {
					wb = new XSSFWorkbook(fis);
				} else {
					System.out.println("文件类型错误!");
					return null;
				}

				// 开始解析
				Sheet sheet = wb.getSheetAt(0); // 读取sheet 0

				int firstRowIndex = sheet.getFirstRowNum() + 1; // 第一行是列名，所以不读
				int lastRowIndex = sheet.getLastRowNum();
				System.out.println("firstRowIndex: " + firstRowIndex);
				System.out.println("lastRowIndex: " + lastRowIndex);

				for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) { // 遍历行
					System.out.println("rIndex: " + rIndex);
					Row row = sheet.getRow(rIndex);
					InfopubAddress pubAddress = new InfopubAddress();
					if (row != null) {
						int firstCellIndex = row.getFirstCellNum();
						int lastCellIndex = row.getLastCellNum();
						for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) { // 遍历列
							Cell cell = row.getCell(cIndex);
							// 将获得的单元格的值赋值给实体
							if (cIndex == 0) {
								district = cell.toString();// 获取区名称
								pubAddress.setStDistrict(district);
							} /*else if (cIndex == 1) {
								street = cell.toString();// 获取街道名称
								pubAddress.setStStreet(street);
							}*/ else if (cIndex == 1) {
								addressLabel = cell.toString();// 获取地址别名
								pubAddress.setStLabel(addressLabel);
							} else if (cIndex == 2) {
								addressName = cell.toString();// 获取地址名称
								pubAddress.setStAddress(addressName);
							} /*else if (cIndex == 4) {
								lng = cell.toString();// 
								pubAddress.setNmLng(new BigDecimal(lng));
							} else if (cIndex == 5) {
								lat = cell.toString();// 
								pubAddress.setNmLat(new BigDecimal(lat));
							}*/
						}
					}
					/*System.out.println("区名称:" + district + ";街道名称：" + street
							+ ";地址别名：" + addressLabel + ";地址名称：" + addressName);*/
					list.add(pubAddress);
				}
				return list;
			} else {
				System.out.println("找不到指定的文件");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static List<InfopubDeviceInfo> getDataByExcel(byte[] bfile,
			String fileName) {
		/**
		 * 读取前端传的文件并下载到本地
		 */
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		String filePath = "D:\\设备信息导入";
		// String fileName="infobup.xls";
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(filePath + "\\" + fileName);
			System.out.println(file);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		/**
		 * 读取本地文件并遍历出文件内容
		 */
		List<InfopubDeviceInfo> list = new ArrayList<InfopubDeviceInfo>();
		String stDeviceName = null;
		String stDeviceIp = null;
		String stDeviceMac = null;
		String stTypeId = null;
		String stAreaId = null;
		String stAddressId = null;
		String stStreet = null;
		String stAddress = null;
		try {
			File excel = new File("D:\\设备信息导入\\" + fileName);
			if (excel.isFile() && excel.exists()) { // 判断文件是否存在
				String[] split = excel.getName().split("\\."); // .是特殊字符，需要转义！！！！！
				Workbook wb;
				// 根据文件后缀（xls/xlsx）进行判断
				FileInputStream fis = new FileInputStream(excel); // 文件流对象
				if ("xls".equals(split[1])) {
					wb = new HSSFWorkbook(fis);
				} else if ("xlsx".equals(split[1])) {
					wb = new XSSFWorkbook(fis);
				} else {
					System.out.println("文件类型错误!");
					return null;
				}
				// 开始解析
				Sheet sheet = wb.getSheetAt(0); // 读取sheet 0
				int firstRowIndex = sheet.getFirstRowNum() + 1; // 第一行是列名，所以不读
				int lastRowIndex = sheet.getLastRowNum();
				for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) { // 遍历行
					Row row = sheet.getRow(rIndex);
					InfopubDeviceInfo infopubDeviceInfo = new InfopubDeviceInfo();
					if (row != null) {
						int firstCellIndex = row.getFirstCellNum();
						int lastCellIndex = row.getLastCellNum();
						for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) { // 遍历列
							Cell cell = row.getCell(cIndex);
							// 将获得的单元格的值赋值给实体
							if (cIndex == 0) {
								stDeviceName = cell.toString();// 设备名称
								infopubDeviceInfo.setStDeviceName(stDeviceName);
							} else if (cIndex == 1) {
								stDeviceIp = cell.toString();// 设备IP
								infopubDeviceInfo.setStDeviceIp("无");
							} else if (cIndex == 2) {
								stDeviceMac = cell.toString();// 设备MAC
								infopubDeviceInfo.setStDeviceMac(stDeviceMac);
							} else if (cIndex == 3) {
								stTypeId = cell.toString();// 设备类型（类型id）
								infopubDeviceInfo.setStTypeId("建设银行智慧柜员机");
							} else if (cIndex == 4) {
								stAddressId = cell.toString();// 设备地址（地址id）
								infopubDeviceInfo.setStAddressId("上海市");
							} else if (cIndex == 5) {
								stAreaId = cell.toString();// 区域（区域id）
								infopubDeviceInfo.setStAreaId(stAreaId);
							}/* else if(cIndex == 6){
								stStreet = cell.toString();// 街道setStDeviceName
								infopubDeviceInfo.setStDesc(stStreet);
							}*/else if(cIndex == 6){
								stAddress = cell.toString();// 详细地址
								infopubDeviceInfo.setStDeviceAddress(stAddress);
							}
						}
					}
					 //System.out.println(stDeviceName+stDeviceIp+stDeviceMac+stTypeId+stAreaId+stAddress);
					list.add(infopubDeviceInfo);
				}
				return list;
			} else {
				System.out.println("找不到指定的文件");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	
	/**
	 * 读取excel的地址信息并持久化到数据库
	 * @param url
	 */
	public static void readExcelAddressToDB(String url){
		
		File excel = new File(url);
		InfopubAddressDao addressDao = new InfopubAddressDao();
		
		//判断文件是否存在
		try {
			FileInputStream fis = new FileInputStream(excel);
			if(excel.isFile() && excel.exists()){
				String[] split = excel.getName().split("\\.");
				Workbook wb;
				//根据文件后缀(xls/xlsx)判断文件类型
				if("xls".equals(split[1])){
					wb = new HSSFWorkbook(fis);
				}else if("xlsx".equals(split[1])){
					wb = new XSSFWorkbook(fis);
				}else{
					System.out.println("文件类型错误");
					return;
				}
				
				//开始解析
				Sheet sheet = wb.getSheetAt(0);  //读取工作表0
				
				int firstRowIndex = sheet.getFirstRowNum()+1; //第一行是标题，不读取，读取第二行
				int lastRowIndex = sheet.getLastRowNum();
				System.out.println("第一行："+firstRowIndex+",最后一行："+lastRowIndex);
				
				for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++){
					System.out.println("读取到第"+(rIndex+1)+"行");
					Row row = sheet.getRow(rIndex);// 获取地rIndex行
					if(null != row){
						int firstCellIndex = row.getFirstCellNum();  //获取第一列
						int lastCellIndex = row.getLastCellNum();  //获取最后一列
						System.out.println(firstCellIndex+"--"+lastCellIndex);
						
						//地址
						InfopubAddress ia = null;
						boolean empt = false;
						String address = "";
						String district = "";
						String street = "";
						
						for(int cIndex = firstCellIndex; cIndex <= lastCellIndex; cIndex++){
							Cell cell = row.getCell(cIndex); //获取第rIndex行第cIndex列的单元格
							if(null != cell){
								//第一列：详细地址
								if(cIndex == 0){
									ia = addressDao.getByLikeSpec(cell.toString().trim());
									if(null == ia){
										empt = true;
										address = cell.toString().trim();
										ia = new InfopubAddress();
										ia.setStAddressId(UUID.randomUUID().toString());
										ia.setStAddress(cell.toString().trim());
										ia.setDtCreate(new Timestamp(System.currentTimeMillis()));
										ia.setStCity("上海市");
									}
								//第二列：网点名称
								}else if(cIndex == 1){
									if(empt){
										ia.setStLabel("浦发上海"+cell.toString().trim());
									}
									/*String allAddress = cell.toString().trim();
									district = allAddress.substring(allAddress.indexOf("市")+1, allAddress.indexOf("区")+1);
									int i = allAddress.indexOf("镇");
									if(i > 0){
										street = allAddress.substring(allAddress.indexOf("区")+1,allAddress.indexOf("镇")+1);
										address = allAddress.substring(allAddress.indexOf("镇")+1, allAddress.length());
									}else{
										address = allAddress.substring(allAddress.indexOf("区")+1, allAddress.length());
									}
									*/
									//System.out.println(district+"--"+street+"--"+address);
									
									/*ia = addressDao.getByLikeSpec(address);
									if(null == ia){
										empt = true;
										ia = new InfopubAddress();
										ia.setStAddressId(UUID.randomUUID().toString());
										ia.setStAddress(address);
										ia.setDtCreate(new Timestamp(System.currentTimeMillis()));
										ia.setStCity("上海市");
										ia.setStDistrict(district);
										ia.setStStreet(street);
										Map map = addressToLanAndLat(allAddress);
										ia.setNmLng((BigDecimal)map.get("lng"));
										ia.setNmLat((BigDecimal)map.get("lat"));
									}*/
								//第三列：区名称
								}else if(cIndex == 2){
									if(empt){
										district = cell.toString().trim();
										ia.setStDistrict(cell.toString().trim());
									}
								//第四列：街道名称
								}else if(cIndex == 3){
									if(empt){
										street = cell.toString().trim();
										ia.setStStreet(cell.toString().trim());
										String allAddress = "上海市"+district+street+address;
										System.out.println(allAddress);
										Map map = addressToLanAndLat(allAddress);
										ia.setNmLng((BigDecimal)map.get("lng"));
										ia.setNmLat((BigDecimal)map.get("lat"));
									}
									
									
								}else if(cIndex == 4){
									/*if(empt){
										Map map = addressToLanAndLat(cell.toString());
										ia.setNmLng((BigDecimal)map.get("lng"));
										ia.setNmLat((BigDecimal)map.get("lat"));
									}*/
									/*if(empt){
										ia.setStLabel("中国农业银行"+cell.toString().trim());
									}*/
								}else if(cIndex == 6){
									/*if(null != ia){
										ia.setStStreet(cell.toString());
									}*/
								}
								System.out.println(cIndex+"-"+cell.toString());
							}
						}
						
						if(empt){
							/*String add = "上海市"+district+street+address;
							Map map = addressToLanAndLat(add);
							ia.setNmLng((BigDecimal)map.get("lng"));
							ia.setNmLat((BigDecimal)map.get("lat"));*/
							//addressDao.add(ia);
						}
						System.out.println(ia);
					}
				}
			}else{
				System.out.println("找不到指定文件");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 读取excel的设备信息并持久化到数据库
	 * @param url
	 */
	public static void readExcelDeviceToDB(String url){
		
		List<String> list = new ArrayList<String>();
		File excel = new File(url);
		InfopubDeviceInfoDao deviceDao = new InfopubDeviceInfoDao();
		InfopubAddressDao addressDao = new InfopubAddressDao();
		InfopubAreaDao areaDao = new InfopubAreaDao();
		//判断文件是否存在
		try {
			FileInputStream fis = new FileInputStream(excel);
			if(excel.isFile() && excel.exists()){
				String[] split = excel.getName().split("\\.");
				Workbook wb;
				//根据文件后缀(xls/xlsx)判断文件类型
				if("xls".equals(split[1])){
					wb = new HSSFWorkbook(fis);
				}else if("xlsx".equals(split[1])){
					wb = new XSSFWorkbook(fis);
				}else{
					System.out.println("文件类型错误");
					return;
				}
				
				//开始解析
				Sheet sheet = wb.getSheetAt(0);  //读取工作表0
				
				int firstRowIndex = sheet.getFirstRowNum(); //第一行是标题，不读取，读取第二行,从0开始读，表的第一行下标为0
				int lastRowIndex = sheet.getLastRowNum();
				System.out.println("第一行："+firstRowIndex+",最后一行："+lastRowIndex);
				
				List<String> macList = new ArrayList<String>();
				for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++){
					System.out.println("读取到第"+(rIndex+1)+"行");
					Row row = sheet.getRow(rIndex);// 获取地rIndex行
					if(null != row){
						int firstCellIndex = row.getFirstCellNum();  //获取第一列
						int lastCellIndex = row.getLastCellNum();  //获取最后一列
						
						//设备
						InfopubDeviceInfo idi = null;
						boolean empt = false;
						//地址
						InfopubAddress ia = null;
						InfopubArea iaa = null;
						
						String district = "";
						String street = "";
						String address = "";
						
						
						for(int cIndex = firstCellIndex; cIndex <= lastCellIndex; cIndex++){
							Cell cell = row.getCell(cIndex); //获取第rIndex行第cIndex列的单元格
							
							if(null != cell){
								//第一列：MAC
								if(cIndex == 0){
									idi = deviceDao.getMac(cell.toString().trim());
									if(null == idi){
										empt = true;
										idi = new InfopubDeviceInfo();
										idi.setStDeviceId(UUID.randomUUID().toString());
										idi.setStDeviceMac(cell.toString().trim());
										idi.setStChannel("/client/"+cell.toString().trim());
										idi.setStTypeId("18c3bcf9-7aed-4e41-a00d-69bfd4169b64");
										idi.setStUserId("admin");
										idi.setDtCreate(new Timestamp(System.currentTimeMillis()));
										idi.setDtUpdate(new Timestamp(System.currentTimeMillis()));
									}
								//第二列：网点
								}else if(cIndex == 1){
									if(empt){
										idi.setStDeviceName("工商银行上海"+cell.toString().trim()+"智慧柜员机");
									}
								//第三列：详细地址
								}else if(cIndex == 2){
									if(empt){
										address = cell.toString().trim();
										ia = addressDao.getByLikeSpec(address);
										if(null != ia){
											idi.setStAddressId(ia.getStAddressId());
											idi.setStDeviceAddress("上海市"+ia.getStDistrict()+ia.getStStreet()+ia.getStAddress());
											idi.setNmLng(ia.getNmLng());
											idi.setNmLat(ia.getNmLat());
											iaa = areaDao.getName(ia.getStDistrict());
											if(null != iaa){
												idi.setStAreaId(iaa.getStAreaId());
												idi.setStDeviceCode(getDeviceCode("18c3bcf9-7aed-4e41-a00d-69bfd4169b64",iaa.getStAreaId()));
											}
										}
									}
								//第四列：IP
								}else if(cIndex == 3){
									if(empt){
										idi.setStDeviceIp(cell.toString().trim());
									}
									//idi.setStDeviceName(ia.getStLabel()+"智慧柜员机");
								}else if(cIndex == 4){
									/*if(empt){
										idi.setStDeviceName("农行上海"+cell.toString().trim()+"智慧柜员机");
									}*/
									/*idi = deviceDao.getMac(cell.toString().trim());
									if(null != idi){
										empt = true;
									}*/
									/*iaa = areaDao.getName(cell.toString().trim());
									idi.setStAreaId(iaa.getStAreaId());
									idi.setStDeviceCode(getDeviceCode("ba1b9a00-1655-4cf4-8cb4-5a363ad9d307",iaa.getStAreaId()));*/
								}else if(cIndex == 5){
									
									//idi.setStDeviceIp(cell.toString().trim());
									//idi.setDtUpdate(new Timestamp(System.currentTimeMillis()));
									/*iaa = areaDao.getName(cell.toString());
									idi.setStAreaId(iaa.getStAreaId());*/
									/*idi = deviceDao.getMac(cell.toString().trim());
									if(null != idi){
										empt = true;
										idi.setStOrganId("69dd2cdd-c86a-4811-864c-184d24587800");
									}else{
										macList.add(cell.toString().trim());
									}*/
								}else if(cIndex == 6){
									/*if(empt){
										idi.setStDeviceMac(cell.toString().trim());
									}*/
								}else if(cIndex == 7){
									//idi.setStDeviceIp(cell.toString());
								}else if(cIndex == 8){
									/*idi.setStDeviceMac(cell.toString());
									idi.setStChannel("/client/"+cell.toString());	*/
								}
				
								System.out.println(cIndex+"-"+cell.toString());
							}
						}
						
						
						
						
						if(empt){
							deviceDao.add(idi);
							//deviceDao.update(idi);
						}
						
						System.out.println(idi);
					}
					
				}
				System.out.println(list.size());
				int i = 0;
				for(String temp : list){
					i+=1;
					System.out.println(temp);
				}
				System.out.println("未添加的设备：");
				for(String temp : macList){
					i+=1;
					System.out.println(temp);
				}
			}else{
				System.out.println("找不到指定文件");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public static String getDeviceCode(String stTypeId,String stAreaId){
		
		InfopubDeviceInfoDao infopubDeviceInfoDao = new InfopubDeviceInfoDao();
		InfopubCompanyDao infopubCompanyDao = new InfopubCompanyDao();
		InfopubDeviceTypeDao infopubDeviceTypeDao = new InfopubDeviceTypeDao();
		InfopubAreaDao infopubAreaDao = new InfopubAreaDao();
		
		List<InfopubDeviceInfo> list = infopubDeviceInfoDao.query(null,
				null);
		List arrayListCount = new ArrayList();
		for (InfopubDeviceInfo infopubDeviceInfo : list) {
			String aCode = infopubDeviceInfo.getStDeviceCode();
			String str = aCode.substring(aCode.length() - 4, aCode.length());
			arrayListCount.add(str);
		}
		//stTypeCode
		InfopubDeviceType infopubDeviceType = infopubDeviceTypeDao.get(stTypeId);
		String stTypeCode = infopubDeviceType.getStTypeCode();
		//company
		InfopubCompany infopubCompany = infopubCompanyDao.get(infopubDeviceType.getStCompanyId());
		String company = infopubCompany.getStCompanyCode();
		//s
		String num = Collections.max(arrayListCount);
		int cout = Integer.decode(num) + 1;
		DecimalFormat mFormat = new DecimalFormat("0000");// 确定格式，把1转换为01
		String s = mFormat.format(cout);
		//stAreaCode
		InfopubArea infopubArea = infopubAreaDao.get(stAreaId);
		String stAreaCode = infopubArea.getStAreaCode();
		
		String code = stAreaCode+company+stTypeCode+s;
		return code;
	}
	
	/**
	 * 通过地址转换经纬度
	 * @param address
	 * @return
	 */
	public static Map addressToLanAndLat(String address){
		Map map = new HashMap();
		String url="https://api.map.baidu.com/geocoding/v3/?address=" + address + "&output=json&ak=2e14nq3anz0xrooGORuPnFtkFsm4IdX7";
		String json = loadJSON(url);
		JSONObject obj = JSONObject.fromObject(json);
		System.out.println(obj.toString());
		if(obj.get("status").toString().equals("0")){
			double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
			double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
			System.out.println(address+"   经度："+lng+"纬度："+lat);
			map.put("lng",new BigDecimal(lng).setScale(7, BigDecimal.ROUND_HALF_UP));
			map.put("lat",new BigDecimal(lat).setScale(7, BigDecimal.ROUND_HALF_UP));
		}else{
			map.put("lng",new BigDecimal(0));
			map.put("lat",new BigDecimal(0));
			
            System.out.println("未找到相匹配的经纬度！");
		}
		
		return map;
	}

	private static String loadJSON(String url) {
		StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            System.out.println("____"+json);
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString(); 
	}
	
	
	
	
	public static void readExcelItemToDB(String url){
		SelmItemLinkDao selmItemLinkDao = new SelmItemLinkDao();
		SelmItemDao selmItemDao = new SelmItemDao();
		File excel = new File(url);
		//判断文件是否存在
		try {
			FileInputStream fis = new FileInputStream(excel);
			if(excel.isFile() && excel.exists()){
				String[] split = excel.getName().split("\\.");
				Workbook wb;
				//根据文件后缀(xls/xlsx)判断文件类型
				if("xls".equals(split[1])){
					wb = new HSSFWorkbook(fis);
				}else if("xlsx".equals(split[1])){
					wb = new XSSFWorkbook(fis);
				}else{
					System.out.println("文件类型错误");
					return;
				}
				
				//开始解析
				//Sheet sheet = wb.getSheetAt(0);  //读取工作表0
				Sheet sheet = wb.getSheet("工行事项");  //按名字读取工作表
				
				int firstRowIndex = sheet.getFirstRowNum(); //第一行是标题，不读取，读取第二行
				int lastRowIndex = sheet.getLastRowNum();
				System.out.println("第一行："+firstRowIndex+",最后一行："+lastRowIndex);
				
				for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++){
					System.out.println("读取到第"+(rIndex+1)+"行");
					Row row = sheet.getRow(rIndex);// 获取地rIndex行
					if(null != row){
						int firstCellIndex = row.getFirstCellNum();  //获取第一列
						int lastCellIndex = row.getLastCellNum();  //获取最后一列
						System.out.println("第一列："+firstCellIndex+",最后一列："+lastCellIndex);
						
						//地址
						SelmItemLink sil = null;
						SelmItem si = null;
						String itemName = null;
						
						
						for(int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++){
							Cell cell = row.getCell(cIndex); //获取第rIndex行第cIndex列的单元格
							if(null != cell){
								//第一列：详细地址
								if(cIndex == 0){
									itemName = cell.toString().trim();
									si = selmItemDao.getByName(itemName);
									if(null != si){
										sil = new SelmItemLink();
										sil.setStItemId(si.getStItemId());
										sil.setStItemTypeId("c015086d-caa6-4acf-a805-cd11d111bb60");
									}
									SelmItemLink sil2 = selmItemLinkDao.get(si.getStItemId(), "c015086d-caa6-4acf-a805-cd11d111bb60");
									if(null == sil2){
										selmItemLinkDao.add(sil);
									}
									
								}
									
							}
							System.out.println(cIndex+"-"+cell.toString());
							System.out.println(sil);
						}
					}
				}
			}else{
				System.out.println("找不到指定文件");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void linkItemAndDevice(String sheetStr,String bankDeviceName,String url){
		SelmItemLinkDao selmItemLinkDao = new SelmItemLinkDao();
		SelmItemDao selmItemDao = new SelmItemDao();
		SelmDeviceItemDao selmDeviceItemDao = new SelmDeviceItemDao();
		SelmItemTypeDao SelmItemTypeDao = new SelmItemTypeDao();
		InfopubDeviceInfoDao infopubDeviceInfoDao = new InfopubDeviceInfoDao();
		InfopubDeviceTypeDao infopubDeviceTypeDao = new InfopubDeviceTypeDao();
		
		/*String bankItemName = null;
		//事项分组ID
		String stItemTypeId = SelmItemTypeDao.getByName(bankItemName).getStItemTypeId();
		
		//一个分组的所有事项
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_ITEM_TYPE_ID",Condition.OT_EQUAL,stItemTypeId));
		ArrayList<SelmItemLink> itemList = (ArrayList<SelmItemLink>) selmItemLinkDao.query(conds,null);
		*/
		
		Conditions conds = Conditions.newAndConditions();
		conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_TYPE_NAME",Condition.OT_EQUAL,bankDeviceName));
		//设备类型
		String stDeviceTypeId = infopubDeviceTypeDao.getByName(bankDeviceName).getStTypeId();
		//一个银行的所有设备
		conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_TYPE_ID",Condition.OT_EQUAL,stDeviceTypeId));
		ArrayList<InfopubDeviceInfo> idiList = (ArrayList<InfopubDeviceInfo>) infopubDeviceInfoDao.query(conds, null);
		
		File excel = new File(url);
		//判断文件是否存在
		try {
			FileInputStream fis = new FileInputStream(excel);
			if(excel.isFile() && excel.exists()){
				String[] split = excel.getName().split("\\.");
				Workbook wb;
				//根据文件后缀(xls/xlsx)判断文件类型
				if("xls".equals(split[1])){
					wb = new HSSFWorkbook(fis);
				}else if("xlsx".equals(split[1])){
					wb = new XSSFWorkbook(fis);
				}else{
					System.out.println("文件类型错误");
					return;
				}
				
				//开始解析
				//Sheet sheet = wb.getSheetAt(0);  //读取工作表0
				Sheet sheet = wb.getSheet(sheetStr);  //按名字读取工作表
				
				int firstRowIndex = sheet.getFirstRowNum(); //第一行是标题，不读取，读取第二行
				int lastRowIndex = sheet.getLastRowNum();
				System.out.println("第一行："+firstRowIndex+",最后一行："+lastRowIndex);
				
				for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++){
					System.out.println("读取到第"+(rIndex+1)+"行");
					Row row = sheet.getRow(rIndex);// 获取地rIndex行
					if(null != row){
						int firstCellIndex = row.getFirstCellNum();  //获取第一列
						int lastCellIndex = row.getLastCellNum();  //获取最后一列
						System.out.println("第一列："+firstCellIndex+",最后一列："+lastCellIndex);
						
						//地址
						SelmItemLink sil = null;
						SelmItem si = null;
						String itemName = null;
						
						
						for(int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++){
							Cell cell = row.getCell(cIndex); //获取第rIndex行第cIndex列的单元格
							if(null != cell){
								//第一列：详细地址
								if(cIndex == 0){
									itemName = cell.toString().trim();
									si = selmItemDao.getByName(itemName);
									if(null != si){
										for(InfopubDeviceInfo d : idiList){
												SelmDeviceItem sdi = null;
												SelmDeviceItem sdi2 = null;
												sdi2 = new SelmDeviceItem();
												sdi2.setStItemId(si.getStItemId());
												sdi2.setStDeviceId(d.getStDeviceId());
												sdi = selmDeviceItemDao.get(si.getStItemId(), d.getStDeviceId());
												if(null == sdi){
													selmDeviceItemDao.add(sdi2);
												}
												System.out.println(sdi2);
												System.out.println(cIndex+"-"+cell.toString());
										}
									}
								}
									
							}
							
						}
					}
				}
			}else{
				System.out.println("找不到指定文件");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
		
	public static void addItemCode(String url){
		SelmItemDao selmItemDao = new SelmItemDao();
		
		Conditions conds = Conditions.newAndConditions();
		conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_TYPE_NAME",Condition.OT_EQUAL,""));
	
		
		File excel = new File(url);
		//判断文件是否存在
		try {
			FileInputStream fis = new FileInputStream(excel);
			if(excel.isFile() && excel.exists()){
				String[] split = excel.getName().split("\\.");
				Workbook wb;
				//根据文件后缀(xls/xlsx)判断文件类型
				if("xls".equals(split[1])){
					wb = new HSSFWorkbook(fis);
				}else if("xlsx".equals(split[1])){
					wb = new XSSFWorkbook(fis);
				}else{
					System.out.println("文件类型错误");
					return;
				}
				
				//开始解析
				Sheet sheet = wb.getSheetAt(0);  //读取工作表0
				//Sheet sheet = wb.getSheet(sheetStr);  //按名字读取工作表
				
				int firstRowIndex = sheet.getFirstRowNum(); //第一行是标题，不读取，读取第二行
				int lastRowIndex = sheet.getLastRowNum();
				System.out.println("第一行："+firstRowIndex+",最后一行："+lastRowIndex);
				
				for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++){
					System.out.println("读取到第"+(rIndex+1)+"行");
					Row row = sheet.getRow(rIndex);// 获取地rIndex行
					if(null != row){
						int firstCellIndex = row.getFirstCellNum();  //获取第一列
						int lastCellIndex = row.getLastCellNum();  //获取最后一列
						System.out.println("第一列："+firstCellIndex+",最后一列："+lastCellIndex);
						
						SelmItem si = null;
						
						
						for(int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++){
							Cell cell = row.getCell(cIndex); //获取第rIndex行第cIndex列的单元格
							if(null != cell){
								//第一列：详细地址
								if(cIndex == 0){
									String name = cell.toString().trim();
									
								}else if(cIndex == 1){
									String code1 = cell.toString().trim();
									
								}else if(cIndex == 2){
									String code2 = cell.toString().trim();
								}
									
							}
							System.out.println(cell.toString().trim());
						}
					}
				}
			}else{
				System.out.println("找不到指定文件");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
