package reindeer.oauth2.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.oauth2.bean.Oauth2Client;
import reindeer.oauth2.service.ClientService;
import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;
import tw.tool.helper.PageJsonHelper;

@Controller
public class Oauth2ClientController {

	@Autowired
	private ClientService clientService;

	@RequestMapping("/oauth2/addClient.do")
	public void addClient(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		Result result = Result.getResult();
		result.setSuccess(false);
		result.setMsg("创建客户端失败");
		try {
			String clientId = httpReqRes
					.getParameter(Oauth2Client.ST_CLIENT_ID);
			Oauth2Client oauth2Client = clientService.findByClientId(clientId);
			if (oauth2Client != null) {
				result.setMsg("当前客户端已存在");
			} else {
				oauth2Client = clientService.add(httpReqRes);
				if (oauth2Client != null) {
					result.setData(PageJsonHelper.toJson(oauth2Client));
					result.success().setMsg("创建客户端成功");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

}
