package reindeer.workday.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.workday.service.WorkdayConfigService;

import reindeer.workday.dao.Workday;


/**
 * WOA_WORKDAY web层控制器
 * 
 * @author 邬本春、虞越
 * 
 */
@Controller
public class WorkdayController {

	@Autowired
	private WorkdayConfigService workdayConfigService;

	@RequestMapping("/workday/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ParseException {
		String result = "";
		Workday workday = new Workday();
		workday.setStWorkdayId(UUID.randomUUID().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		workday.setDtWorkday(new Timestamp(sdf.parse(req.getParameter("day")).getTime()));
		boolean ok = workdayConfigService.save(workday);
		if (ok) {
			result = "Y";
		} else {
			result = "N";
		}
		res.setContentType("text/plain; charset=UTF-8");
		res.getWriter().write(result);
	}

	@RequestMapping("/workday/find.do")
	public void findAll(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		JSONArray result = JSONArray.fromObject(workdayConfigService.findAll());
		res.setContentType("application/json; charset=UTF-8");
		res.getWriter().write(result.toString());
	}

}
