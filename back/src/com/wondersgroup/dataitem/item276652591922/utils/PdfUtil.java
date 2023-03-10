package com.wondersgroup.dataitem.item276652591922.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.wondersgroup.common.utils.FileUtil;
import com.wondersgroup.dataitem.item276652591922.web.CivilServiceController;

public class PdfUtil {

	public static void fillData(PdfStamper ps,AcroFields fields, Map<String, Object[]> data) throws IOException, DocumentException {
		for (String key : data.keySet()) {
			Object[] arr = (Object[]) data.get(key);
//			Font f = null;
			String value = "";
			if (arr.length > 1 && arr[1] != null) {
				BaseFont bf = (BaseFont) arr[1];
				fields.setFieldProperty(key, "textfont", bf, null);
//				f = (Font) arr[1];
			}
			if (arr.length > 0 && arr[0] != null) {
				value = (String) arr[0];
//				if("mobilePhoneService".equals(key) 
//						|| "speech".equals(key) 
//						|| "broadbandService".equals(key) ){
//					
//				} else {
//					fields.setField(key, value);
//				}
				fields.setField(key, value, true);
			}
			
//			List<AcroFields.FieldPosition> multiLinePosition = fields.getFieldPositions(key);
//			int page = multiLinePosition.get(0).page;
//			Rectangle rectangle = multiLinePosition.get(0).position;
//			float left = rectangle.getLeft();
//			float right = rectangle.getRight();
//			float top = rectangle.getTop();
//			float bottom = rectangle.getBottom();
//			PdfContentByte pdfContentByte = ps.getOverContent(page);
//			ColumnText columnText = new ColumnText(pdfContentByte);
//			
//			Rectangle r = new Rectangle(left, bottom, right, top);
//			columnText.setSimpleColumn(r);
//			Chunk chunk = new Chunk(value);
//			Paragraph paragraph = new Paragraph(10, chunk);
//			paragraph.setAlignment(1);
//			paragraph.setAlignment(Paragraph.ALIGN_CENTER);
//			paragraph.setAlignment(PdfCell.ALIGN_CENTER);??????????????????????????????????????????????????????????????????padding
//			columnText.addText(paragraph);
//			paragraph.setFont(f);
//			columnText.addElement(paragraph);
//			columnText.go();
		}
	}
	
	/**
	 * 
	 * @param fileName ??????????????????(???fillImg???????????????????????????)
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static byte[] fillData(String fileName, Map<String, Object[]> data) throws IOException, DocumentException {
				
		PdfReader reader = new PdfReader(fileName);
		ByteArrayOutputStream bos = null;
		byte[] btReturn = null;
		PdfStamper ps = null;
		try {
			bos = new ByteArrayOutputStream();
			
			/* ?????????????????????PDF???????????? */
			ps = new PdfStamper(reader, bos);
			
			/* ???????????????????????????????????? */
			AcroFields fields = ps.getAcroFields();
			
			fillData(ps,fields, data);
			
			ps.setFormFlattening(true);
			ps.close();
			btReturn = bos.toByteArray();
		}
		finally {
			reader.close();
			if (bos != null) {
				bos.close();
			}
		}
		return btReturn;
	}
	
	/**
	 * 
	 * @param templatePath ??????????????????
	 * @param targetPath ??????????????????
	 * @param fieldName pdf??????
	 * @param buf ?????????????????????
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void fillImg(byte[] blContent,String targetPath,String fieldName,byte[] buf) throws IOException, DocumentException {
        
		String imagePath = CivilServiceController.class.getResource("").getPath()+"/template/temp.png";
		FileUtil.getFileFromBytes(buf, imagePath);
        PdfReader reader = new PdfReader(blContent);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(targetPath));
        // ??????pdf????????????
        AcroFields form = stamper.getAcroFields();
        form.addSubstitutionFont(BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));

        // ?????????????????????????????????????????????????????????
        int pageNo = form.getFieldPositions(fieldName).get(0).page;
        Rectangle signRect = form.getFieldPositions(fieldName).get(0).position;
        float x = signRect.getLeft();
        float y = signRect.getBottom();
        //???????????? ???
        float height=signRect.getHeight();
        float width=signRect.getWidth();
        // ?????????
        Image image = Image.getInstance(imagePath);
        // ?????????????????????
        PdfContentByte under = stamper.getOverContent(pageNo);
        // ??????????????????????????????
        //image.scaleToFit(signRect.getWidth(), signRect.getHeight());
        //image.scaleAbsolute(90f,122f);
        //????????? ???
        float imgHeight=image.getHeight();
        float imgWidth=image.getWidth();
        image.scalePercent((height/imgHeight)*100);
        
        //???????????????
        x+=(width-((height/imgHeight)*imgWidth))/2;
        // ????????????
        image.setAbsolutePosition(x, y);
        under.addImage(image);
        
//        under.closePath();
        stamper.close();
        reader.close();
	}
	
    public static void byte2File(byte[] buf) {
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;
        String filePath = PdfUtil.class.getResource("").getPath();
        try  
        {  
            File dir = new File(filePath);  
            if (!dir.exists() && dir.isDirectory())
            {  
                dir.mkdirs(); 
            }
            file = new File(filePath + File.separator + "temp.png");
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(buf);  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
        finally  
        {  
            if (bos != null)  
            {  
                try  
                {  
                    bos.close();  
                }  
                catch (IOException e)  
                {  
                    e.printStackTrace();  
                }  
            }  
            if (fos != null)  
            {  
                try  
                {  
                    fos.close();  
                }  
                catch (IOException e)  
                {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }
    
    public static byte[] File2byte(String filePath) {  
        byte[] buffer = null;  
        try  
        {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream();  
            byte[] b = new byte[1024];  
            int n;  
            while ((n = fis.read(b)) != -1)  
            {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        }  
        catch (FileNotFoundException e)  
        {  
            e.printStackTrace();  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        return buffer;  
    } 
	
	public static void main(String[] args) throws Exception {
		
//		String fileName = 
//				pdfUtil.getClass().getClassLoader().getResource(
//						"coral/certificate/tessdata/sample/sample.pdf").getPath();
		String fileName = "C:\\Users\\wanda\\Desktop\\???????????????????????????????????????????????????.pdf";
		String fontName = "C:\\Users\\wanda\\Desktop\\template\\MSYH.TTF";
		System.out.println(fileName);
		
		String pngPath = "C:\\Users\\wanda\\Desktop\\test\\?????????\\??????.png";
		//String pngPath = "C:/Users/wanda/Desktop/?????????/quyu.png";
		byte[] bty = File2byte(pngPath);
		byte[] blContent = File2byte(fileName);
		fillImg(blContent,"e:/zsResult.pdf","img",bty);
		
		/* ?????????????????? */
		BaseFont bf = BaseFont.createFont(fontName, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); 

		Map<String, Object[]> data = new HashMap<String, Object[]>();
//		data.put("stDistributeDepart", new Object[]{"??????????????????", bf});
//		data.put("dtValidStart", new Object[]{"2007.1.11", bf});
//		data.put("dtValidEnd", new Object[]{"2017.1.11", bf});
//		data.put("stName", new Object[]{"??????", bf});
//		data.put("stGender", new Object[]{"???", bf});
//		data.put("stNation", new Object[]{"???", bf});
//		data.put("stYear", new Object[]{"1993", bf});
//		data.put("stMonth", new Object[]{"12", bf});
//		data.put("stDay", new Object[]{"12", bf});
//		data.put("stIdentityNo", new Object[]{"330123199312120812", bf});
//		data.put("stAddress", new Object[]{"????????????????????????????????????2048???601???", bf});
		data.put("name", new Object[]{"??????", bf});
		data.put("sex", new Object[]{"??????", bf});
		
		byte[] btContent = fillData("e:/zsResult.pdf", data);
		OutputStream fos = null;
		try {
			fos = new FileOutputStream("e:/zsResult1.pdf");
			if (btContent != null && btContent.length > 0) {
				fos.write(btContent);
			}
		}
		finally {
			if (fos != null) {
				fos.flush();
				fos.close();
			}
		}
	}
}
