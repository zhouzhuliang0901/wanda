package com.wondersgroup.wdf.web;

import com.wondersgroup.wdf.dao.*;
import com.wondersgroup.wdf.service.UacUapplyAttachService;
import coral.base.util.RequestWrapper;
import coral.widget.data.ExtAjaxReturnMessage;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.BlobHelper;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * 综合材料附件 web层控制器
 *
 * @author scalffold
 */
@RestController
public class UacUapplyAttachController {

    @RequestMapping("/wdf/uacUapplyAttach/edit")
    public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        RequestWrapper wrapper = new RequestWrapper(req);
        // UacUapplyAttach.ST_ATTACH_ID
        String stAttachId = wrapper.getParameter(UacUapplyAttach.ST_ATTACH_ID);
        if (!StringUtils.trimToEmpty(stAttachId).isEmpty()) {
            UacUapplyAttach uacUapplyAttach = uacUapplyAttachService.get(stAttachId);
            req.setAttribute(UacUapplyAttach.UAC_UAPPLY_ATTACH, uacUapplyAttach);
        }
        return new ModelAndView("/uacUapplyAttach/edit.jsp");
    }

    @RequestMapping("/wdf/uacUapplyAttach/info")
    public WdfResult info(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        RequestWrapper wrapper = new RequestWrapper(req);
        // UacUapplyAttach.ST_ATTACH_ID
        String stAttachId = wrapper.getParameter(UacUapplyAttach.ST_ATTACH_ID);
        UacUapplyAttach uacUapplyAttach = uacUapplyAttachService.get(stAttachId);
        req.setAttribute(UacUapplyAttach.UAC_UAPPLY_ATTACH, uacUapplyAttach);
        return WdfResult.getResult().success().setData(JsonUtils.toJson(uacUapplyAttach));
    }

    @RequestMapping("/wdf/uacUapplyAttach/list")
    public WdfResult list(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        WdfResult result = WdfResult.getSuccessResult();
        try {
            RequestWrapper wrapper = new RequestWrapper(req);
            PaginationArrayList<UacUapplyAttach> list = uacUapplyAttachService.query(wrapper);
            result.setData(JsonUtils.toJson(list, UacUapplyAttach.class));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 删除多附件
     *
     * @param req
     * @param res
     * @return
     * @throws IOException
     */
    @RequestMapping("/wdf/uacUapplyAttach/remove")
    public WdfResult remove(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        WdfResult result = WdfResult.getSuccessResult();
        try {
            RequestWrapper wrapper = new RequestWrapper(req);
            String[] roleIdList = wrapper.getParameterValues(UacUapplyAttach.ST_ATTACH_ID);
            if (roleIdList.length == 0) {
                String roleId = wrapper.getParameter("stAttachId");
                if (!StringUtils.isBlank(roleId)) {
                    roleIdList = new String[1];
                    roleIdList[0] = roleId;
                } else {
                    throw new NullPointerException("附件ID不能为空");
                }
            }
            uacUapplyAttachService.remove(roleIdList);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.failed().setMsg(ex.getMessage());
        }
        return result;
    }

    @RequestMapping("/wdf/uacUapplyAttach/save")
    public WdfResult save(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        WdfResult result = WdfResult.getSuccessResult();
        try {
            RequestWrapper wrapper = new RequestWrapper(req);
            UacUapplyAttach uacUapplyAttach = uacUapplyAttachService.saveOrUpdate(wrapper);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.failed().setMsg(ex.getMessage());
        }
        return result;
    }

    /**
     * 获取子证证照
     *
     * @param req
     * @param res
     * @return
     * @throws IOException
     */
    @RequestMapping("/wdf/uacUapplyAttach/getCclist")
    public List<UacUapplyAttach> getCclist(HttpServletRequest req, HttpServletResponse res) throws IOException {
        WdfResult result = WdfResult.getSuccessResult();
        RequestWrapper wrapper = new RequestWrapper(req);
        try {
            String st_sub_no = wrapper.getParameter("ST_SUB_NO");
            Conditions conds = Conditions.newAndConditions();
            conds.add(new Condition(UacUapplyAttach.ST_LINK_ID, Condition.OT_EQUAL, st_sub_no));
            conds.add(new Condition(UacUapplyAttach.ST_LINK_TABLE, Condition.OT_EQUAL, "EXT_PD_CCLIST"));
            PaginationArrayList<UacUapplyAttach> Linklist = uacUapplyAttachDaoExt.query(conds, null, 5, 1);
            Linklist.forEach(item -> {
                String strConditon = "ST_ATTACH_ID = " + "'" + item.getStAttachId() + "'";
                byte[] blob = BlobHelper.getBlob("UAC_UAPPLY_ATTACH", "BL_CONTENT", strConditon);
                item.setContent(new String(blob, StandardCharsets.UTF_8));
            });
            result.setData(JsonUtils.toJson(Linklist, UacUapplyAttach.class));
            return Linklist;
        } catch (Exception e) {
            e.printStackTrace();
            result.failed().setMsg(e.getMessage());
        }
        return null;
    }

    /**
     * 上传图片
     *
     * @param multipartFiles
     * @param stApplyId
     * @throws IOException
     */
    @RequestMapping("/uacUapplyAttach/upBlob.do")
    public synchronized Object upBlob(MultipartFile multipartFiles, String stApplyId, String stStuffId)
            throws IOException {
        String result = "";

        try {
            Conditions conds = Conditions.newAndConditions();
            conds.add(new Condition(UacUapplyStuff.ST_APPLY_ID, Condition.OT_EQUAL, stApplyId));
            conds.add(new Condition(UacUapplyStuff.ST_CSTUFF_ID, Condition.OT_EQUAL, stStuffId));
            List<UacUapplyStuff> query = uacuapplystuffdao.query(conds, "");
            UacItemStuffTwo uacItemStuffTwo = uacItemStuffDao.getStuffId(stStuffId);

            //获取文件类型
//            String applicationType = multipartFiles.getContentType();
//
//            String contentType = applicationType.substring(applicationType.lastIndexOf("/")+1);

            String filename = multipartFiles.getOriginalFilename();
            String contentType = filename.substring(filename.lastIndexOf("."));
            String size = String.valueOf(multipartFiles.getSize());

            if (query.isEmpty()) {
                UacUapplyAttach uacUapplyAttach = new UacUapplyAttach();
                uacUapplyAttach.setStAttachId(UUID.randomUUID().toString());
                uacUapplyAttach.setStLinkTable("UAC_UNION_APPLY");
                uacUapplyAttach.setStLinkId(stApplyId);
                uacUapplyAttach.setStFileType(contentType);//材料类型
                uacUapplyAttach.setStFilename(filename);//文件名
                uacUapplyAttach.setStFileType(contentType);//文件类型
                uacUapplyAttach.setStFileSize(size);//文件大小
                uacUapplyAttach.setDtCreate(new Timestamp(System.currentTimeMillis()));
                uacUapplyAttachDao.add(uacUapplyAttach);
                byte[] read = multipartFiles.getBytes();
                Conditions conds2 = Conditions.newAndConditions();
                conds2.add(new Condition(UacUapplyAttach.ST_ATTACH_ID, Condition.OT_EQUAL, uacUapplyAttach.getStAttachId()));
                BlobHelper.setBlob(UacUapplyAttach.UAC_UAPPLY_ATTACH, UacUapplyAttach.BL_CONTENT, conds2.toString(), read, conds2.getObjectArray());

                UacUapplyStuff uacUapplyStuff = new UacUapplyStuff();
                uacUapplyStuff.setStEstuffId(UUID.randomUUID().toString());
                uacUapplyStuff.setStApplyId(stApplyId);
                uacUapplyStuff.setStCstuffId(stStuffId);
                uacUapplyStuff.setStEntityType("FILE");
                uacUapplyStuff.setStStuffType("APPLY");
                uacUapplyStuff.setNmCopy(uacItemStuffTwo.getNmCopy());
                uacUapplyStuff.setNmOriginal(uacItemStuffTwo.getNmOriginal());
                uacUapplyStuff.setStStuffUse("申请材料");
                uacUapplyStuff.setNmStatus(new BigDecimal("0"));//材料状态，0：正常；1：待补正；2：已补正；
                uacUapplyStuff.setNmOfflineSubmit(new BigDecimal("1"));//现场提交，0：否；1：是

                uacUapplyStuff.setDtCreate(new Timestamp(System.currentTimeMillis()));
                uacuapplystuffdao.add(uacUapplyStuff);

                UacAttachLink uacAttachLink = new UacAttachLink();
                uacAttachLink.setStAttachId(uacUapplyAttach.getStAttachId());
                uacAttachLink.setStEstuffId(uacUapplyStuff.getStEstuffId());
                uacAttachLink.setStFileName(filename);
                uacattachlinkDao.add(uacAttachLink);
                //调用上传文件方法
//                result = (String) uploadFile(multipartFiles, uacUapplyAttach.getStAttachId());

            } else {
                UacUapplyStuff uacUapplyStuff = query.get(0);
                String stEstuffId = uacUapplyStuff.getStEstuffId();
                String stAttachId = UUID.randomUUID().toString();

                UacUapplyAttach uacUapplyAttach = new UacUapplyAttach();
                uacUapplyAttach.setStAttachId(stAttachId);
                uacUapplyAttach.setDtCreate(new Timestamp(System.currentTimeMillis()));
                uacUapplyAttach.setStFileType(contentType);//材料类型
                uacUapplyAttach.setStFilename(filename);//文件名
                uacUapplyAttach.setStFileType(contentType);//文件类型
                uacUapplyAttach.setStFileSize(size);//文件大小
                uacUapplyAttach.setStLinkTable("UAC_UNION_APPLY");
                uacUapplyAttach.setStLinkId(stApplyId);
                uacUapplyAttachDao.add(uacUapplyAttach);

                byte[] read = multipartFiles.getBytes();
                Conditions conds2 = Conditions.newAndConditions();
                conds2.add(new Condition(UacUapplyAttach.ST_ATTACH_ID, Condition.OT_EQUAL, stAttachId));
                BlobHelper.setBlob(UacUapplyAttach.UAC_UAPPLY_ATTACH, UacUapplyAttach.BL_CONTENT, conds2.toString(), read, conds2.getObjectArray());

                UacAttachLink uacAttachLink = new UacAttachLink();
                uacAttachLink.setStAttachId(stAttachId);
                uacAttachLink.setStEstuffId(stEstuffId);
                uacAttachLink.setStFileName(filename);
                uacattachlinkDao.add(uacAttachLink);
            }
        } catch (Exception e) {
            System.out.println("上传失败，原因：" + e);
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] read(InputStream inputStream) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int num = inputStream.read(buffer);
            while (num != -1) {
                baos.write(buffer, 0, num);
                num = inputStream.read(buffer);
            }
            baos.flush();
            return baos.toByteArray();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

//    /**
//     * 获取图片
//     *
//     * @param stEstuffId
//     * @throws IOException
//     */
//    @RequestMapping("/uacUapplyAttach/getBlob.do")
//    public Object getBlob(HttpServletResponse response, String stEstuffId)
//            throws SQLException {
//        UacAttachLink uacAttachLink = uacattachlinkDao.getAth(stEstuffId);
//        String stAttachId = uacAttachLink.getStAttachId();
//
//        UacUapplyAttach uacUapplyAttach = uacUapplyAttachDao.get(stAttachId);
//        String stFileType = uacUapplyAttach.getStFileType();
//
//        return downloadFile(stAttachId, response, stFileType);
//    }

//    private final static String rootPath = "D:/SaveFile/";
//
//    //    @RequestMapping("/uacUapplyAttach/upBlob.do")
//    public Object uploadFile(MultipartFile multipartFiles, String stAttachId) {
//        String result = "失败";
//
//        File fileDir = new File(rootPath);
//        if (!fileDir.exists() && !fileDir.isDirectory()) {
//            fileDir.mkdirs();
//        }
//        try {
//            if (multipartFiles != null) {
//
//                String filename = multipartFiles.getOriginalFilename();
//                String suffixName = filename.substring(filename.lastIndexOf("."));
//
//                try {
//                    //以原来的名称命名,覆盖掉旧的，这里也可以使用UUID之类的方式命名，这里就没有处理了
////                    String storagePath = rootPath + multipartFiles.getOriginalFilename();
//                    String storagePath = rootPath + stAttachId + suffixName;
//
//                    System.out.println("上传的文件：" + multipartFiles.getName() + "," + multipartFiles.getContentType() + "," + multipartFiles.getOriginalFilename()
//                            + "，保存的路径为：" + storagePath);
//
//                    multipartFiles.transferTo(new File(storagePath));
//
//                    result = "成功";
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //前端可以通过状态码，判断文件是否上传成功
//        return result;
//    }

    /**
     * @param stAttachId,response
     * @param response
     * @return
     */
    @RequestMapping("/uacUapplyAttach/getBlob.do")
    public Object downloadFile(@RequestParam String stAttachId, HttpServletResponse response) {
        String result = ExtAjaxReturnMessage.toJsonErrorObj("文件下载成功。", "成功",
                null).toString();

//        for (String attachId : stAttachId) {
        UacUapplyAttach uacUapplyAttach = uacUapplyAttachDao.get(stAttachId);
//            String stFileType = uacUapplyAttach.getStFileType();

        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition(UacUapplyAttach.ST_ATTACH_ID, Condition.OT_EQUAL, stAttachId));

        String strConditon = "ST_ATTACH_ID = " + "'" + stAttachId + "'";
        byte[] blob = BlobHelper.getBlob("UAC_UAPPLY_ATTACH", "BL_CONTENT", strConditon);

        String fileName = uacUapplyAttach.getStFilename();

        OutputStream os = null;
        InputStream is = null;
        try {
            // 取得输出流
            os = response.getOutputStream();
//            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=utf-8");
//            //Content-Disposition中指定的类型是文件的扩展名，并且弹出的下载对话框中的文件类型图片是按照文件的扩展名显示的，点保存后，文件以filename的值命名，
//            // 保存类型以Content中设置的为准。注意：在设置Content-Disposition头字段之前，一定要设置Content-Type头字段。
//            //把文件名按UTF-8取出，并按ISO8859-1编码，保证弹出窗口中的文件名中文不乱码，中文不要太多，最多支持17个中文，因为header有150个字节限制。
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            is = new ByteArrayInputStream(blob);
            if (is == null) {
                System.out.println("下载附件失败，请检查文件“" + fileName + "”是否存在");
                return result = ExtAjaxReturnMessage.toJsonErrorObj("文件下载失败。", "失败",
                        null).toString();
            }
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            System.out.println("下载附件失败，IO异常");
            e.printStackTrace();
            return result = ExtAjaxReturnMessage.toJsonErrorObj("文件下载失败。", "失败",
                    null).toString();
        }
        //文件的关闭放在finally中
        finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //其实，这个返回什么都不重要
        System.out.println("下载附件成功“" + fileName);
//        }
        return result;
    }

    @RequestMapping("/uacUapplyAttach/getStuffList")
    public WdfResult downloadFile(@RequestParam String stEstuffId) {
        WdfResult result = WdfResult.getSuccessResult();
        try {
            Conditions conds = Conditions.newAndConditions();
            conds.add(new Condition(UacAttachLink.ST_ESTUFF_ID, Condition.OT_EQUAL, stEstuffId));
            List<UacAttachLink> query = uacattachlinkDao.query(conds, "");
            result.setData(query);
        } catch (Exception e) {
            e.printStackTrace();
            result.failed().setMsg(e.getMessage());
        }
        return result;
    }


    /**
     * @param stAttachId,response
     * @param response
     * @return
     */
    @RequestMapping("/uacUapplyAttach/previewFile.do")
    public void previewFile(@RequestParam String stAttachId, HttpServletResponse response) {
        String result = ExtAjaxReturnMessage.toJsonErrorObj("文件下载成功。", "成功",
                null).toString();

//        for (String attachId : stAttachId) {
        UacUapplyAttach uacUapplyAttach = uacUapplyAttachDao.get(stAttachId);
        String fileType = uacUapplyAttach.getStFileType();

        String strConditon = "ST_ATTACH_ID = " + "'" + stAttachId + "'";
        byte[] blob = BlobHelper.getBlob("UAC_UAPPLY_ATTACH", "BL_CONTENT", strConditon);

//        if (fileType.equals(".pdf")){
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            response.addHeader("Content-Type", fileType);
            os.write(blob);
            os.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//            return null;
//        }else {
//            return blob;
    }
//        Conditions conds = Conditions.newAndConditions();
//        conds.add(new Condition(UacUapplyAttach.ST_ATTACH_ID, Condition.OT_EQUAL, stAttachId));
//        return BlobToBytes(blob);

//        String fileName = uacUapplyAttach.getStFilename();
//
//        OutputStream os = null;
//        InputStream is = null;
//        try {
//            // 取得输出流
//            os = response.getOutputStream();
////            // 清空输出流
//            response.reset();
//            response.setContentType("application/x-download;charset=utf-8");
////            //Content-Disposition中指定的类型是文件的扩展名，并且弹出的下载对话框中的文件类型图片是按照文件的扩展名显示的，点保存后，文件以filename的值命名，
////            // 保存类型以Content中设置的为准。注意：在设置Content-Disposition头字段之前，一定要设置Content-Type头字段。
////            //把文件名按UTF-8取出，并按ISO8859-1编码，保证弹出窗口中的文件名中文不乱码，中文不要太多，最多支持17个中文，因为header有150个字节限制。
//            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
//            is = new ByteArrayInputStream(blob);
//            if (is == null) {
//                System.out.println("下载附件失败，请检查文件“" + fileName + "”是否存在");
//                return result = ExtAjaxReturnMessage.toJsonErrorObj("文件下载失败。", "失败",
//                        null).toString();
//            }
//            //复制
//            IOUtils.copy(is, response.getOutputStream());
//            response.getOutputStream().flush();
//        } catch (IOException e) {
//            System.out.println("下载附件失败，IO异常");
//            e.printStackTrace();
//            return result = ExtAjaxReturnMessage.toJsonErrorObj("文件下载失败。", "失败",
//                    null).toString();
//        }
//        //文件的关闭放在finally中
//        finally {
//            try {
//                if (is != null) {
//                    is.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (os != null) {
//                    os.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        //其实，这个返回什么都不重要
//        System.out.println("下载附件成功“" + fileName);
////        }
//        return result;
//    }


//    /**
//     * @param stEstuffId,response
//     * @param response
//     * @return
//     */
//    @RequestMapping("/uacUapplyAttach/getBlob.do")
//    public Object downloadFile(@RequestParam String stEstuffId, HttpServletResponse response) {
//        String result = ExtAjaxReturnMessage.toJsonErrorObj("文件下载成功。", "成功",
//                null).toString();
//
//        UacAttachLink uacAttachLink = uacattachlinkDao.getAth(stEstuffId);
//        String stAttachId = uacAttachLink.getStAttachId();
//
//        UacUapplyAttach uacUapplyAttach = uacUapplyAttachDao.get(stAttachId);
//        String stFileType = uacUapplyAttach.getStFileType();
//
//        String fileName = stAttachId + stFileType;
//
//        OutputStream os = null;
//        InputStream is = null;
//        try {
//            // 取得输出流
//            os = response.getOutputStream();
//            // 清空输出流
//


    @Autowired
    private UacUapplyAttachService uacUapplyAttachService;

    @Autowired
    private UacUapplyAttachDaoExt uacUapplyAttachDaoExt;

    @Autowired
    private UacUapplyAttachDao uacUapplyAttachDao;

    @Autowired
    private UacUapplyStuffDao uacuapplystuffdao;

    @Autowired
    private UacAttachLinkDao uacattachlinkDao;

    @Autowired
    private UacItemStuffTwoDao uacItemStuffDao;
}
