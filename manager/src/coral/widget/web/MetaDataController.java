package coral.widget.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import coral.base.data.DataPage;
import coral.base.data.MetadataService;
import coral.base.data.MetadataTable;
import coral.base.data.Transformer4Request;
import coral.widget.data.AjaxReturnMessage;
import coral.widget.data.DataSet;
import coral.widget.service.WidgetService;
import coral.widget.utils.ExtHelper;

/**
 * 元数据控制器
 * 
 * @author 龚云
 * 
 */
@Controller
public class MetaDataController {

	/**
	 * 通用表查询接口
	 * 
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	@RequestMapping("/widget/table_query.do")
	public void query(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String result = "";
		try {
			Transformer4Request tf4r = new Transformer4Request(req);
			ExtHelper.Page page = ExtHelper.getPage(req);
			String table = tf4r.getParameter(ExtHelper.TABLE_PARAM);
			MetadataTable metadataTable = metadataService
					.extractMetadataFromDatabase(table);
			DataPage dataPage = widgetService.query(metadataTable, tf4r, page);
			DataSet dataSet = DataSet.convert(dataPage);
			result = widgetService.toJson(dataSet).toString();
		} catch (Exception e) {
			result = AjaxReturnMessage.catchExceptionToString(e, "");
		}
		ExtHelper.writeString(res, result);
	}

	@Autowired
	private WidgetService widgetService;

	@Autowired
	private MetadataService metadataService;

}
