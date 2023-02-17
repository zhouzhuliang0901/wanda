package reindeer.base.netwarnitem.until;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.netwarnitem.bean.CompanyInfo;
import reindeer.base.netwarnitem.dao.NetWarnIngItemDao;
import reindeer.base.utils.AciJsonHelper;

import wfc.service.config.Config;

@Controller
public class InitializationUtil {

	public static List<CompanyInfo> companyInfoList;

	@Autowired
	private NetWarnIngItemDao netWarnItemDao;

	/**
	 * 更新企业名称数据的缓存
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/initializationUtil.do")
	public void getHallDetails(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		companyInfoList = netWarnItemDao.getCompanyInfoList();
		AciJsonHelper.writeJsonPResponse(req, res, "成功");
	}

	/**
	 * 查询存储在项目缓存中的企业名称的数量
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/selectCompanyInfo.do")
	public void selectCompanyInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		List<CompanyInfo> companyInfoList = InitializationUtil.companyInfoList;
		AciJsonHelper.writeJsonPResponse(req, res, companyInfoList.size() + "");
	}

	/**
	 * 初始化企业名称数据信息（加载缓存到项目中）
	 */
	@PostConstruct
	public void init() {
		if ("pudong".equals(Config.get("server.SmsService.module"))) {
			System.out
					.println("---------------初始化企业名称表中的企业关键字开始----------------");
			companyInfoList = netWarnItemDao.getCompanyInfoList();
			System.out
					.println("---------------初始化企业名称表中的企业关键字结束---------------");
		}
	}

}
