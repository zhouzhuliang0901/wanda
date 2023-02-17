package coral.base.qrcode;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import wfc.service.web.QRCodeServlet;

@Controller
public class QRCodeController {

	@RequestMapping("/qrcode/qrcode.do")
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		QRCodeServlet servlet = new QRCodeServlet();
		servlet.service(request, response);
	}
}
