(function (w) {
	w.LODOP_PRINT = {};
	w.LODOP_PRINT.test = function () {
		// lodop.SET_PRINTER_INDEX('HP ColorLaserJet M253-M254 PCL 6');
		lodop.ADD_PRINT_TEXT(0, 0, 100, 20, "激光打印机");
		lodop.ADD_PRINT_TEXT(0, 0, 100, 120, "激光打印机");
		lodop.ADD_PRINT_TEXT(0, 0, 200, 220, "激光打印机");
		lodop.ADD_PRINT_TEXT(0, 0, 100, 320, "激光打印机");
		lodop.ADD_PRINT_TEXT(0, 0, 300, 420, "激光打印机");
		lodop.ADD_PRINT_TEXT(0, 0, 100, 520, "激光打印机");
		lodop.ADD_PRINT_TEXT(0, 0, 700, 620, "激光打印机");
		lodop.ADD_PRINT_TEXT(0, 0, 100, 720, "激光打印机");

		lodop.PREVIEW();
	}
	w.LODOP_PRINT.corporateLicense = function (img) {
		var lodop = $.device.printGetLodop('');
		lodop.ADD_PRINT_IMAGE(0, 0, 800, 1300, "<img border='0' src='" + img + "'>");
		lodop.SET_PRINT_STYLEA(0, "Stretch", 2); //按原图比例(不变形)缩放模式
		lodop.ADD_PRINT_TEXT(0, 0, 400, 500, "此证照由市电子证照库提供");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.SET_PRINT_STYLEA(0, "FontColor", "#ccc");
		lodop.SET_PRINT_STYLEA(0, "ItemType", 1);
		lodop.SET_PRINT_STYLEA(0, "Angle", 50);
		lodop.SET_PRINT_STYLEA(0, "Repeat", true);
		// lodop.PREVIEW();
		lodop.PRINT();
	};
	w.LODOP_PRINT.personLicense = function (img) {
		var lodop = $.device.printGetLodop('');
		lodop.ADD_PRINT_IMAGE(240, 220, 330, 800, "<img border='0' src='" + img + "'>");
		lodop.SET_PRINT_STYLEA(0, "Stretch", 2); //按原图比例(不变形)缩放模式
		lodop.ADD_PRINT_TEXT(0, 0, 400, 500, "此证照由市电子证照库提供");
		lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
		lodop.SET_PRINT_STYLEA(0, "FontColor", "#ccc");
		lodop.SET_PRINT_STYLEA(0, "ItemType", 1);
		lodop.SET_PRINT_STYLEA(0, "Angle", 50);
		lodop.SET_PRINT_STYLEA(0, "Repeat", true);
		// lodop.PREVIEW();
		lodop.PRINT();
	};
	w.LODOP_PRINT.idcardPrint = function (frontImg,backImg) {
		var lodop = $.device.printGetLodop('');
		lodop.ADD_PRINT_IMAGE(140, 220, 880, 1000, "<img border='0' style='width:320px;height:205px;' src='"+frontImg+"'>");
		lodop.ADD_PRINT_IMAGE(460, 220, 380, 1000, "<img border='0' style='width:320px;height:205px;' src='"+backImg+"'>");
		lodop.SET_PRINT_STYLEA(0, "Stretch", 2); //按原图比例(不变形)缩放模式
		lodop.PRINT();
		// lodop.PREVIEW();
	};
	w.LODOP_PRINT.materialPrint = function (img) {
		var lodop = $.device.printGetLodop('');
		lodop.ADD_PRINT_IMAGE(0, 0, 1080, 1200, "<img border='0' style='width:750px;height:1100px;'  src='"+img+"'>");
		lodop.SET_PRINT_STYLEA(0, "Stretch", 2); //按原图比例(不变形)缩放模式
		//lodop.SET_PRINT_STYLEA(0, "AngleOfPageInside", -90); //按原图比例(不变形)缩放模式
		lodop.PRINT();
	};
	w.LODOP_PRINT.faceRecognition = function (photograph, portrait, info) {
		var SerialNumber = function () {
			var ArrNum = '000000'.split('');
			if (localStorage.getItem('SerialTimer') == null) { //第一次打印注入时间戳
				localStorage.setItem('SerialTimer', Date.parse(new Date()))
			}
			if (localStorage.getItem('SerialTimer') != null && Date.parse(new Date()) - localStorage.getItem('SerialTimer') > 1000 * 3600 * 12) { //时间戳存在并已经过12个小时，则清空流水号以及时间戳
				localStorage.removeItem("SerialTimer");
				localStorage.removeItem("SerialNumber");
				localStorage.setItem('SerialTimer', Date.parse(new Date())) //更新时间戳
			}
			if (localStorage.getItem('SerialNumber') == null) { //流水号是否为空
				localStorage.setItem('SerialNumber', '000001');
			} else { //流水号保有原格式递增
				var num = parseInt(localStorage.getItem('SerialNumber')) + 1 + '';
				for (var index = 0; index < num.length; index++) {
					ArrNum.push(num[index]);
				}
				var temporary1 = ArrNum.reverse();
				temporary1.length = 6;
				ArrNum = temporary1.reverse();
				localStorage.setItem('SerialNumber', ArrNum.join(''))
			}
		}
		printBtn = function () {
			var D = new Date();
			var NowDate = D.getFullYear() + '' + (D.getMonth() + 1) + '' + D.getDate();
			var dataTime = D.getFullYear() + '年' + (D.getMonth() + 1) + '月' + D.getDate() + '日';
			SerialNumber(); //流水号		

			lodop.PRINT_INIT("打印插件功能演示_lodop功能_BASE64编码串打印图片");
			lodop.SET_PRINT_STYLE("FontSize", 18);
			lodop.ADD_PRINT_TEXT(130, 320, 200, 80, "人证核验结果单");
			lodop.SET_PRINT_STYLE("FontSize", 12);

			lodop.ADD_PRINT_TEXT(50, 500, 200, 80, "流水号:");
			lodop.ADD_PRINT_TEXT(50, 560, 200, 80, NowDate + localStorage.getItem('SerialNumber'));

			lodop.ADD_PRINT_TEXT(170, 100, 200, 80, "姓名:");
			lodop.ADD_PRINT_TEXT(170, 150, 200, 80, info.Name);

			lodop.ADD_PRINT_TEXT(200, 100, 200, 80, "性别：");
			lodop.ADD_PRINT_TEXT(200, 150, 200, 80, info.Sex);

			lodop.ADD_PRINT_TEXT(200, 300, 200, 80, "民族：");
			lodop.ADD_PRINT_TEXT(200, 350, 200, 80, info.People+"族");

			lodop.ADD_PRINT_TEXT(230, 100, 200, 80, "身份证号：");
			lodop.ADD_PRINT_TEXT(230, 180, 200, 80, info.Number);

			lodop.ADD_PRINT_TEXT(260, 100, 200, 80, "有效期限：");
			lodop.ADD_PRINT_TEXT(260, 180, 250, 80, info.ValidtermOfEnd);

			lodop.ADD_PRINT_TEXT(290, 100, 200, 80, "核验结果：");
			lodop.ADD_PRINT_TEXT(290, 180, 200, 80, "核验通过");

			lodop.ADD_PRINT_TEXT(320, 100, 200, 80, "核验时间：");
			lodop.ADD_PRINT_TEXT(320, 180, 200, 80, dataTime);

			lodop.ADD_PRINT_IMAGE(500, 100, "100%", "100%", "<img border='0' style='width:350px;height:256px;' src='" + photograph + "'>"); //核验照片
			lodop.ADD_PRINT_IMAGE(500, 500, "100%", "100%", portrait); //身份证头像
			//水印
			lodop.ADD_PRINT_TEXT(0, 0, 400, 500, "本结果只用于政务服务核验身份");
			lodop.SET_PRINT_STYLEA(0, "FontSize", 20);
			lodop.SET_PRINT_STYLEA(0, "FontColor", "#ccc");
			lodop.SET_PRINT_STYLEA(0, "ItemType", 1);
			lodop.SET_PRINT_STYLEA(0, "Angle", 50);
			lodop.SET_PRINT_STYLEA(0, "Repeat", true);
			lodop.PRINT();
		}
		printBtn();
	}
	// alert(JSON.stringify(w.LODOP_PRINT))
})(window)