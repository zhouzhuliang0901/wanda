package coral.base.barcode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.krysalis.barcode4j.servlet.BarcodeServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BarCodeController extends BarcodeServlet {

	private static final long serialVersionUID = 1L;

	@RequestMapping("/barcode/barcode.do")
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// msg为内容
		// type为条码类型，默认为code128
		super.doGet(request, response);
	}
}
