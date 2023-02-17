package reindeer.ssologin;

import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.dao.SmsUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reindeer.utils.JwtUtil;
import tw.ecosystem.reindeer.config.RdConfig;
import tw.ecosystem.reindeer.web.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: whp
 * @Date: 2021/11/12/13:01
 * @Description:
 */
@RestController
public class SsoLoginController {

    @Autowired
    private SmsUserDao smsUserDao;

    @RequestMapping("/ssologin/toExamUrl")
    public void SsoLogin(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            String ssoDes = SsoHelper.getSafeUser(req);
            List<SmsUser> users = smsUserDao.queryByUsername(ssoDes);
            if (users.size() != 0){
                res.sendRedirect(RdConfig.get("wfc.service.ws.wdf.exam.loginIndexIp")+ "?Authorization="+ JwtUtil.sign(users.get(0)));
            }
        }catch (Exception e){
            throw new Exception("'ssols'参数不允许为空");
        }
    }

    @RequestMapping("/ssologin/test")
    public Result test(HttpServletRequest req, HttpServletResponse res)throws IOException {
        String key = req.getParameter("loginName");
        String dwwk = SsoHelper.getSafeSsoDes(key);
        return Result.getSuccessResult().setData(dwwk);
    }

    @RequestMapping("/ssologin/gotest")
    public void gotest(HttpServletRequest req, HttpServletResponse res)throws Exception {
        try {
            String key = req.getParameter("loginName");
            String dwwk = SsoHelper.getSafeSsoDes(key);
            String ssoDes = SsoHelper.getSafeUser(dwwk);
            List<SmsUser> users = smsUserDao.queryByUsername(ssoDes);
            if (users.size() != 0){
                String token = JwtUtil.sign(users.get(0));
                String url = RdConfig.get("wfc.service.ws.wdf.exam.loginIndexIp") + "?Authorization=" + token;
                res.sendRedirect(RdConfig.get("wfc.service.ws.wdf.exam.loginIndexIp")+ "?Authorization="+ JwtUtil.sign(users.get(0)));
            }
        }catch (Exception e){
            throw new Exception("'ssols'参数不允许为空");
        }
    }

}
