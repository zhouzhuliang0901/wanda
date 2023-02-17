var myDocApp = null; //定义一个全局的变量
function word_onclick() {
	myDocApp = new ActiveXObject("word.Application"); //创建word对象
	myDocApp.Documents.Open("d://cdb91b95-9f68-4c02-877c-3c481ac2331b.doc"); //打开word
	myDocApp.Application.Visible = false; //设置word打开后不可见

	//word的标签
	var bookMarks = ["certificateNo", "clientName", "applianceName", "mode", "outNo", "manuFactory", "approverImage", "supervisorImage", "verificateurImage"];

	//替换对应标签的值
	var bookMarksValue = ["0123456789", "we前往额", "水杯", "250ml", "cert-0010", "威尔厂", "d://402881942d082b8c012d0833bfa200c0.bmp", "d://402881942d082b8c012d0833bfa200c0.bmp", "d://402881942d082b8c012d0833bfa200c0.bmp"];

	//对打开的word中的标签进行遍历替换
	for(var i = 0; i < bookMarks.length; i++) {
		var booktype = "string"; //标签的类型，默认是string，还有一种是pic，说明是需要插入图片的标签
		if((bookMarks[i] == "approverImage") || (bookMarks[i] == "verificateurImage") || (bookMarks[i] == "supervisorImage")) {
			booktype = "pic"
		}

		//替换标签并赋值
		insertvaluetobookmarks(bookMarks[i], bookMarksValue[i], booktype);
	}

	//打开另外一个word并复制其全部内容粘贴到当前打开word的指定位置
	inserTable("insertTable", "d://4aac5f0d-16a2-457a-9258-4c260fa15b85.doc");

	fillTable();
	try {
		myDocApp.ActiveDocument.SaveAs("d:\\JaveToWord.doc");
	} catch(exception) {
		alert("浏览器安全设置过高,保存文件到本地失败");
		myDocApp.Documents.close();
		myDocApp.Application.quit();
		myDocApp = null;
		window.close();
	}
	myDocApp.Documents.close();
	myDocApp.Application.quit();
	myDocApp = null;
}
word_onclick();
//替换标签并赋值
function insertvaluetobookmarks(bookMarksName, insertValue, booktype) {
	if(myDocApp.ActiveDocument.BookMarks.Exists(bookMarksName)) {
		if(booktype != null && booktype == "pic") { //图片
			var objDoc = myDocApp.ActiveDocument.BookMarks(bookMarksName).Range.Select();
			var objSelection = myDocApp.Selection;
			//objSelection.TypeParagraph();
			//alert(getRootPath()+content);
			var objShape = objSelection.InlineShapes.AddPicture(insertValue);
		} else {
			myDocApp.ActiveDocument.BookMarks(bookMarksName).Range.Select();
			myDocApp.Application.selection.Text = insertValue;
		}
	} else {

		alert("输入的标签不存在");
		return false;
	}
}

function inserTable(bookMarksName, filepath) {
	//从另外一个word中拷贝，粘贴到当前word指定位置
	//begin
	if(myDocApp.ActiveDocument.BookMarks.Exists(bookMarksName)) {
		var tableDocApp = new ActiveXObject("word.Application");
		tableDocApp.Documents.Open(filepath);
		tableDocApp.Application.Visible = false;

		tableDocApp.Selection.WholeStory();
		tableDocApp.Selection.Copy();
		myDocApp.ActiveDocument.BookMarks(bookMarksName).Range.Select();
		myDocApp.Application.selection.Paste();

		tableDocApp.Documents.close();
		tableDocApp.Application.quit();
		tableDocApp = null;
	} else {
		alert(bookMarksName);
		return false;
	}
	//end
}

//动态的给指定表格增加行

function fillTable() {
	var tableCount = myDocApp.ActiveDocument.Tables.Count;
	alert(tableCount);
	var table = myDocApp.ActiveDocument.Tables(tableCount);
	for(var i = 0; i < 5; i++) {

		table.Rows(i + 2).Range.Select();
		myDocApp.Selection.Copy();

		myDocApp.Selection.MoveDown();
		myDocApp.Selection.Paste();

	}

	var rows = table.Rows; //选中table的行对象

	var columns = table.Columns; //选中table的列对象
	var rowcount = rows.Count;
	var columnscount = columns.Count;
	for(var i = 0; i < rowcount - 1; i++) {
		for(var j = 0; j < columnscount; j++) {

			//table.Cell(i+2,j+1).Range.Text="第"+i+"行，第"+j+"列";
			table.Cell(i + 2, j + 1).Range.Text = j; //遍历选中table的单元格并对其赋值

		}
	}

	table.Columns(5).Select(); //选中table的第5列
	table.Columns(5).Delete(); //删除table的第5列
}