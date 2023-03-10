package com.wondersgroup.selfapi.service.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;
import org.fusesource.hawtbuf.ByteArrayInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reindeer.base.bean.WindowItemStatus;
import reindeer.base.bean.WindowItemStuff;
import reindeer.base.utils.RequestWrapper;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import wfc.service.database.BlobHelper;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import com.wondersgroup.selfapi.bean.CsOrgan;
import com.wondersgroup.selfapi.bean.WindowItemInfo;
import com.wondersgroup.selfapi.bean.WorkApplyExt;
import com.wondersgroup.selfapi.bean.WorkApplyInfo;
import com.wondersgroup.selfapi.bean.WorkApplyStuff;
import com.wondersgroup.selfapi.bean.WorkAttachment;
import com.wondersgroup.selfapi.bean.WorkUapplyAttach;
import com.wondersgroup.selfapi.dao.MaterialUpDao;
import com.wondersgroup.selfapi.dao.WorkApplyExtDao;
import com.wondersgroup.selfapi.dao.WorkApplyInfoDao;
import com.wondersgroup.selfapi.dao.WorkApplyStuffDao;
import com.wondersgroup.selfapi.dao.WorkAttachmentDao;

import com.wondersgroup.selfapi.service.MaterialUpService;
import com.wondersgroup.selfapi.util.ApplyNoUtils;

@SuppressWarnings("all")
@Service
public class MaterialUpServiceImpl implements MaterialUpService {

	@Autowired
	private MaterialUpDao materialUpDao;
	@Autowired
	private WorkApplyInfoDao workApplyInfoDao;
	@Autowired
	private WorkApplyExtDao workApplyExtDao;
	@Autowired
	private WorkApplyStuffDao workApplyStuffDao;
	@Autowired
	private WorkAttachmentDao workAttachmentDao;

	/**
	 * ??????????????????
	 */
	@Override
	public JSONObject organList(RequestWrapper wrapper) {
		JSONObject jso = new JSONObject();
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("REMOVED", Condition.OT_EQUAL, "0"));
		List<CsOrgan> cl = materialUpDao.queryOrgan(conds, "ORDER BY NAME ");
		try {
			if (!cl.isEmpty()) {
				net.sf.json.JSONArray jar = net.sf.json.JSONArray
						.fromObject(cl); // JSONArray?????????????????????????????????,??????????????????????????????
				jso.put("data", jar);
				jso.put("mag", "????????????!");
			} else {
				jso.put("data", "");
				jso.put("mag", "??????????????????!");
			}
		} catch (JSONException e) {
			Log.debug(e.getMessage());
		}
		return jso;
	}

	/**
	 * ????????????id???organId?????????????????????
	 */
	@Override
	public JSONObject itemByOrganId(RequestWrapper wrapper) {
		String organId = wrapper.getParameter("organId");
		JSONObject jso = new JSONObject();
		try {
			if (StringUtils.isNotBlank(organId)) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("a.NM_ORGAN_NODE_ID",
						Condition.OT_EQUAL, organId));
				List<WindowItemInfo> w1 = materialUpDao.queryItemByOrganId(
						conds, " ORDER BY a.ST_ITEM_NAME ");
				if (!w1.isEmpty()) {
					JSONArray jar = new JSONArray();
					JSONObject json = new JSONObject();
					for (WindowItemInfo item : w1) {// ??????????????????json
						json = new JSONObject();
						json.put("stItemId", item.getStItemId());
						json.put("stItemName", item.getStItemName());
						json.put("ogranId", item.getNmOrganNodeId());
						json.put("itemNo", item.getStItemNo());
						jar.put(json);
					}
					jso.put("data", jar);
					jso.put("msg", "???????????????");
				} else {
					jso.put("data", "");
					jso.put("msg", "?????????????????????");
				}
			} else {
				jso.put("data", "");
				jso.put("msg", "??????id???????????????");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jso;
	}

	/**
	 * ??????????????????(?????????????????????????????????itemName)
	 */
	@Override
	public JSONObject queryItem(RequestWrapper wrapper) {
		String itemName = wrapper.getParameter("itemName");
		JSONObject jso = new JSONObject();
		Conditions conds = Conditions.newAndConditions();
		List<WindowItemInfo> w1 = null;
		try {
			if (StringUtils.isNotBlank(itemName)) {
				try {
					itemName = URLDecoder.decode(itemName, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				conds = Conditions.newAndConditions();
				conds.add(new Condition(" ST_ITEM_NAME ", Condition.OT_LIKE,
						itemName));
				
				w1 = materialUpDao.queryItem(conds, " ORDER BY ST_ITEM_NAME ");
			} else {
				w1 = materialUpDao.queryItem(conds, " ORDER BY ST_ITEM_NAME ");
			}
			if (!w1.isEmpty()) {
				JSONArray jar = new JSONArray();
				JSONObject json = new JSONObject();
				for (WindowItemInfo item : w1) {
					json = new JSONObject();
					json.put("stItemId", item.getStItemId());
					json.put("itemNo", item.getStItemNo());
					json.put("stItemName", item.getStItemName());
					json.put("organId", item.getNmOrganNodeId());
					jar.put(json);
				}
				jso.put("data", jar);
				jso.put("msg", "???????????????");
			} else {
				jso.put("data", "");
				jso.put("msg", "?????????????????????");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jso;
	}

	/**
	 * ????????????ID??????????????????(itemId)
	 */
	@Override
	public JSONObject queryStatusList(RequestWrapper wrapper) {
		String itemId = wrapper.getParameter("itemId");
		JSONObject jso = new JSONObject();
		Conditions conds = Conditions.newAndConditions();
		try {
			if (StringUtils.isNotBlank(itemId)) {
				conds.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL,
						itemId));
				List<WindowItemStatus> sl = materialUpDao.queryStatusList(
						conds, " ORDER BY NM_ORDER ");
				if (!sl.isEmpty()) {
					JSONArray jar = new JSONArray();
					JSONObject json = new JSONObject();
					for (WindowItemStatus item : sl) {
						json = new JSONObject();
						json.put("stItemId", item.getStItemId());
						json.put("stStatusName", item.getStStatusName());
						json.put("stStatusId", item.getStStatusId());
						jar.put(json);
					}
					jso.put("data", jar);
					jso.put("msg", "???????????????");
				} else {
					jso.put("data", "");
					jso.put("msg", "?????????????????????");
				}
			} else {
				jso.put("data", "");
				jso.put("msg", "??????id???????????????");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jso;
	}

	/**
	 * ????????????ID??????????????????(statusId)
	 */
	@Override
	public JSONObject queryStuffList(RequestWrapper wrapper) {
		String statusId = wrapper.getParameter("statusId");
		JSONObject jso = new JSONObject();
		try {
			if (StringUtils.isNotBlank(statusId)) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_STATUS_ID", Condition.OT_EQUAL,
						statusId));
				List<WindowItemStuff> s1 = materialUpDao
						.getWindowItemApplyStuff(
								conds,
								" GROUP BY b.ST_STUFF_ID,b.ST_FORMAL_ID,"
										+ "b.ST_ITEM_ID,b.ST_STUFF_NAME,b.NM_ORIGINAL,b.NM_COPY,"
										+ "b.NM_SAMPLE,b.NM_MUST,b.NM_ORDER ORDER BY b.NM_ORDER");
				if (!s1.isEmpty()) {
					JSONArray jar = new JSONArray();
					JSONObject json = new JSONObject();
					for (WindowItemStuff item : s1) {
						json = new JSONObject();
						json.put("stStuffName", item.getStStuffName());
						json.put("stStuffId", item.getStStuffId());

						// ??????????????????
						conds = Conditions.newAndConditions();
						conds.add(new Condition("ST_LINK_ID",
								Condition.OT_EQUAL, "WINDOW_ITEM_STUFF"));
						List<WorkAttachment> waList = materialUpDao
								.queryWorkAttachment(conds,
										" ORDER BY DT_CREATE ");
						if (!waList.isEmpty()) {
							JSONArray jar1 = new JSONArray();
							JSONObject json1 = new JSONObject();
							for (WorkAttachment i : waList) {
								json1 = new JSONObject();
								conds = Conditions.newAndConditions();
								conds.add(new Condition("ST_ATTACH_ID",
										Condition.OT_EQUAL, i.getStAttachId()));
								try {
									byte[] buf = BlobHelper.getBlob(
											"WORK_ATTACHMENT", "BL_CONTENT",
											conds.toString(),
											conds.getObjectArray());
									// ???????????????base64??????
									BASE64Encoder encoder = new BASE64Encoder();
									// ??????base64??????????????????????????????
									json1.put(
											"imageBaseStr",
											"data:image/png;base64,"
													+ encoder.encode(buf));
								} catch (JSONException e) {
									Log.debug("+++++??????????????????+++++");
									json1.put("imageBaseStr", "");
								}
								jar1.put(json1);
							}
							json.put("imageArr", jar1);
						} else {
							json.put("imageArr", "");
						}
						jar.put(json);
					}
					jso.put("data", jar);
					jso.put("msg", "???????????????");
				} else {
					jso.put("data", "");
					jso.put("msg", "?????????????????????");
				}
			} else {
				jso.put("data", "");
				jso.put("msg", "??????id???????????????");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jso;
	}
	/**
	 * ??????????????????
	 */
	@Override
	public JSONObject save(RequestWrapper wrapper) {
		JSONObject json = new JSONObject();
		String username = wrapper.getParameter("username");
		
		String mobile = wrapper.getParameter("mobile");
		String stTarget = wrapper.getParameter("targetName");// ???????????????stTarget
		String identityNo = wrapper.getParameter("targetNo");
		String itemId = wrapper.getParameter("itemId");
		String stStatusId = wrapper.getParameter("stStatusId");

		try {
			stStatusId = URLDecoder.decode(stStatusId, "UTF-8");
			username = URLDecoder.decode(username, "UTF-8");
			mobile = URLDecoder.decode(mobile, "UTF-8");
			stTarget = URLDecoder.decode(stTarget, "UTF-8");
			identityNo = URLDecoder.decode(identityNo, "UTF-8");
			itemId = URLDecoder.decode(itemId, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// ??????????????????
		WorkApplyInfo workApplyInfo = new WorkApplyInfo();
		String applyId = UUID.randomUUID().toString();
		workApplyInfo.setStApplyId(applyId);
		workApplyInfo.setStUserName(username);
		workApplyInfo.setStMobile(mobile);
		workApplyInfo.setNmIdentityType(BigDecimal.ONE);
		workApplyInfo.setStIdentityNo(identityNo);
		workApplyInfo.setStItemId(itemId);
		workApplyInfo.setStUnit(stTarget);
		workApplyInfo.setDtStartTime(new Timestamp(System.currentTimeMillis()));
		workApplyInfoDao.add(workApplyInfo);

		WorkApplyExt workApplyExt = new WorkApplyExt();
		String extId = UUID.randomUUID().toString();
		workApplyExt.setStExtId(extId);
		workApplyExt.setStTable("WORK_APPLY_INFO");
		workApplyExt.setStEntityId(applyId);
		workApplyExt.setStInfoNameEn("data_stStatusId");
		workApplyExt.setStInfoName("??????ID");
		workApplyExt.setStInfoValue(stStatusId);
		workApplyExtDao.add(workApplyExt);
		System.out.println("??????workApplyInfo ????????????sql????????????");
		try {
			json.put("applyId", applyId);
			json.put("msg", "???????????????");
			System.out.println(applyId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * ???????????????????????????????????????base64?????????
	 */
	@Override
	public JSONObject uploadFile(RequestWrapper wrapper) {
		JSONObject json = new JSONObject();
		String applyId = wrapper.getParameter("applyId");
		String stuffId = wrapper.getParameter("stuffId");
		String itemId = wrapper.getParameter("itemId");
		String reset = wrapper.getParameter("reset");
		String stuffName = wrapper.getParameter("stuffName");
		String type = wrapper.getParameter("type");// ???????????? 1???base64 2????????????
		try {
			stuffName = URLDecoder.decode(stuffName, "utf-8");
			applyId = URLDecoder.decode(applyId, "UTF-8");
			itemId = URLDecoder.decode(itemId, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Conditions conds = Conditions.newAndConditions();
		try {
			if (StringUtils.isBlank(applyId) || StringUtils.isBlank(applyId)) { // ??????
				json.put("msg", "??????ID?????????ID????????????");
			} else {
				WorkApplyStuff workApplyStuff = getByExt1(applyId, stuffId);
				System.out.println("??????ID" + stuffId);
				if (workApplyStuff == null) {
					workApplyStuff = new WorkApplyStuff();
					workApplyStuff.setStApplyStuffId(UUID.randomUUID()
							.toString());
					workApplyStuff.setStApplyId(applyId);
					workApplyStuff.setStItemId(itemId);
					workApplyStuff.setStStuffName(stuffName);
					workApplyStuff.setStExt1(stuffId);
					workApplyStuff.setNmOriginal(BigDecimal.ONE);
					workApplyStuff.setNmCopy(BigDecimal.ZERO);
					workApplyStuffDao.add(workApplyStuff);
				}

				// ??????????????????????????????
				conds = Conditions.newAndConditions();
				conds.add(new Condition(WorkAttachment.ST_LINK_ID,
						Condition.OT_EQUAL, workApplyStuff.getStApplyId()));
				conds.add(new Condition(WorkAttachment.ST_LINK_TABLE,
						Condition.OT_EQUAL, WorkAttachment.WORK_ATTACHMENT));
				if ("reset".equals(reset)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put(WorkAttachment.ST_LINK_ID,
							"his-" + workApplyStuff.getStApplyStuffId());
					workAttachmentDao.update(map, conds);
				}
				WorkAttachment pa = new WorkAttachment();
				conds = Conditions.newAndConditions();
				if ("1".equals(type)) {
					String fileBase64 = wrapper.getParameter("fileInput");
					String fileName = wrapper.getParameter("fileName");
					System.out.println("??????????????????????????????fileBase64" + fileBase64);

					System.out.println("????????????????????????fileName" + fileName);
					try {
						fileName = URLDecoder.decode(fileName, "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					if (StringUtils.isNotBlank(fileBase64)) {
						System.out.println(fileBase64 + "??????fileBase64??????????????????");
						String[] arr = fileBase64.split(",");
						BASE64Decoder base = new BASE64Decoder();
						byte[] sssByte;
						try {
							sssByte = base.decodeBuffer(arr[1]);
							System.out.println(sssByte.length + "??????---------");
							if (sssByte.length != 0) {
								pa.setStAttachId(UUID.randomUUID().toString());
								pa.setStLinkTable(WorkAttachment.WORK_ATTACHMENT);
								pa.setStLinkId(workApplyStuff
										.getStApplyStuffId());
								int index = fileName.lastIndexOf("\\");
								int indexType = fileName.lastIndexOf(".");
								String lastString = fileName.substring(
										index + 1, fileName.length());
								String lastType = fileName.substring(
										indexType + 1, fileName.length());
								pa.setStFilename(lastString);
								pa.setStFileSize(sssByte.length + "");
								pa.setStFileType(lastType);
								pa.setDtUpdate(new Timestamp(System
										.currentTimeMillis()));
								System.out.println("????????????");
								pa.setDtCreate(new Timestamp(System
										.currentTimeMillis()));
								workAttachmentDao.add(pa);
								System.out.println("????????????????????????sql??????");
								conds = Conditions.newAndConditions();
								conds.add(new Condition(
										WorkUapplyAttach.ST_ATTACH_ID,
										Condition.OT_EQUAL, pa.getStAttachId()));
								System.out.println("1111111????????????????????????sql??????");
								BlobHelper.setBlob(
										WorkAttachment.WORK_ATTACHMENT,
										WorkAttachment.BL_CONTENT,
										conds.toString(), sssByte,
										conds.getObjectArray());
								System.out.println("22222222????????????????????????sql??????");
								InputStream input = new ByteArrayInputStream(
										sssByte);
								ByteArrayOutputStream output = new ByteArrayOutputStream();
								try {
									scale(input, output, 240, 300, false); // ?????????
								} catch (Exception e) {
									BlobHelper.setBlob(
											WorkAttachment.WORK_ATTACHMENT,
											WorkAttachment.BL_SMALL_CONTENT,
											conds.toString(), sssByte,
											conds.getObjectArray());
									System.out.println("33333????????????????????????sql??????");
								}
								sssByte = output.toByteArray();
								BlobHelper.setBlob(
										WorkAttachment.WORK_ATTACHMENT,
										WorkAttachment.BL_SMALL_CONTENT,
										conds.toString(), sssByte,
										conds.getObjectArray());
								System.out
										.println("?????????4444444444444????????????????????????sql??????");
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						json.put("msg", "???????????????????????????");
					} else {
						json.put("msg", "????????????????????????????????????");
					}
				} else if ("2".equals(type)) {
					// ????????????
					String fileName = wrapper.getParameter("fileName");
					try {
						fileName = URLDecoder.decode(fileName, "utf-8");
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
					FileItem fitem = wrapper.getFileItem("fileInput");
					if (fitem != null) {
						byte[] sssByte = fitem.get();
						if (sssByte.length != 0) {
							pa.setStAttachId(UUID.randomUUID().toString());
							pa.setStLinkTable(WorkAttachment.WORK_ATTACHMENT);
							pa.setStLinkId(workApplyStuff.getStApplyStuffId());
							int index = fileName.lastIndexOf("\\");
							int indexType = fileName.lastIndexOf(".");
							String lastString = fileName.substring(index + 1,
									fileName.length());
							String lastType = fileName.substring(indexType + 1,
									fileName.length());
							pa.setStFilename(lastString);
							pa.setStFileSize(fitem.getSize() + "");
							pa.setStFileType(lastType);
							pa.setDtUpdate(new Timestamp(System
									.currentTimeMillis()));
							pa.setDtCreate(new Timestamp(System
									.currentTimeMillis()));
							workAttachmentDao.add(pa);
							conds = Conditions.newAndConditions();
							conds.add(new Condition(
									WorkUapplyAttach.ST_ATTACH_ID,
									Condition.OT_EQUAL, pa.getStAttachId()));
							System.out.println("?????????11111111????????????????????????sql??????");
							BlobHelper.setBlob(WorkAttachment.WORK_ATTACHMENT,
									WorkAttachment.BL_CONTENT,
									conds.toString(), sssByte,
									conds.getObjectArray());
							System.out.println("?????????2222222????????????????????????sql??????");
							InputStream input = new ByteArrayInputStream(
									sssByte);
							ByteArrayOutputStream output = new ByteArrayOutputStream();
							try {
								scale(input, output, 240, 300, false);
							} catch (Exception e) {
								BlobHelper.setBlob(
										WorkAttachment.WORK_ATTACHMENT,
										WorkAttachment.BL_SMALL_CONTENT,
										conds.toString(), sssByte,
										conds.getObjectArray());
								System.out.println("?????????333333????????????????????????sql??????");
							}
							sssByte = output.toByteArray();
							BlobHelper.setBlob(WorkAttachment.WORK_ATTACHMENT,
									WorkAttachment.BL_SMALL_CONTENT,
									conds.toString(), sssByte,
									conds.getObjectArray());
							System.out.println("?????????4444444444444????????????????????????sql??????");
						}
						json.put("msg", "???????????????????????????");
					} else {
						json.put("msg", "????????????????????????????????????");
					}
				} else {
					json.put("msg", "????????????????????????");
				}

			}
		} catch (Exception e) {
			Log.debug(e.getMessage());
		}
		return json;
	}
	
	private WorkApplyStuff getByExt1(String stApplyId, String stStuffId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition(WorkApplyStuff.ST_APPLY_ID, Condition.OT_EQUAL,
				stApplyId));
		conds.add(new Condition(WorkApplyStuff.ST_EXT1, Condition.OT_EQUAL,
				stStuffId));
		List<WorkApplyStuff> list = workApplyStuffDao.query(conds, null);
		return list.size() > 0 ? list.get(0) : null;
	}
	// ??????????????????????????????
	private void scale(InputStream input, ByteArrayOutputStream output, int height,
			int width, boolean bb) throws Exception {
		double ratio = 0.0;//????????????
		BufferedImage bi = ImageIO.read(input);
		Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
		// ????????????
		if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
			if (bi.getHeight() > bi.getWidth()) {
				ratio = (Integer.valueOf(height)).doubleValue()
						/ bi.getHeight();
			} else {
				ratio = (Integer.valueOf(width)).doubleValue() / bi.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(
					AffineTransform.getScaleInstance(ratio, ratio), null);
			itemp = op.filter(bi, null);
		}
		if (bb) {// ??????
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0, width, height);
			if (width == itemp.getWidth(null)) {
				g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
						itemp.getWidth(null), itemp.getHeight(null),
						Color.white, null);
			} else {
				g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
						itemp.getWidth(null), itemp.getHeight(null),
						Color.white, null);
			}
			g.dispose();
			itemp = image;
		}
		ImageIO.write((BufferedImage) itemp, "JPEG", output);
	}
	
	/**
	 * ????????????
	 */
	@Override
	public JSONObject toSubmit(RequestWrapper wrapper) {
		JSONObject jso = new JSONObject();
		String stApplyId = wrapper.getParameter("applyId");
		String userName = wrapper.getParameter("userName");
		try {
			userName = URLDecoder.decode(userName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		WorkApplyInfo workApply = workApplyInfoDao.get(stApplyId);
		
		if(workApply == null){
			try {
				jso.put("applyNo", "");
				jso.put("msg", "???????????????????????????");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			workApply.setDtFinish(new Timestamp(System.currentTimeMillis()));
			workApply.setStApplyNo(
					ApplyNoUtils.genApplyNo(wrapper.getParameter("itemNo")));
		workApplyInfoDao.update(workApply);
		}
		try {
			jso.put("applyNo", workApply.getStApplyNo());
			
			jso.put("msg", "????????????");
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		return jso;
	}

}
