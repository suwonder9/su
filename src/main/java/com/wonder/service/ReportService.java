package com.wonder.service;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


@Service
@Slf4j
public class ReportService {

    public  void export(String exportName,String columnStr,String titleStr,List<?> list,HttpServletResponse response){
        OutputStream os = null;
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);

        try {
            os = response.getOutputStream();
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode( exportName+".xlsx", "UTF-8"));

            long start = System.currentTimeMillis();
            SXSSFSheet sheet = wb.createSheet();

            List<String> columns = Lists.newArrayList(columnStr.split(","));
            List<String> titles = Lists.newArrayList(titleStr.split(","));

            setHeader(wb,sheet,columns,titles);
            createSheet(wb,sheet,list,columns);

            wb.write(os);

            long end = System.currentTimeMillis();
            log.info("处理时间 ，{}",(end-start)/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                wb.close();
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                log.error("report fail message{}",e.getMessage());
            } // 关闭输出流
        }
    }

    private  void setHeader(SXSSFWorkbook wb,SXSSFSheet sheet, List<String> columns,List<String> titles) {
        SXSSFRow headRow = sheet.createRow(0);
        headRow.setHeight((short) 350);
        //头栏样式设置
        CellStyle headRowStyle = wb.createCellStyle();
//        headRowStyle.setFillBackgroundColor();
//        headRowStyle.setFillForegroundColor();
        Font fontStyle = wb.createFont();
        fontStyle.setFontHeightInPoints((short) 14); //设置字体高度
        headRowStyle.setFont(fontStyle);


        int cellIndex = 0;//为了防止个别列隐藏的情况
        for (int k = 0; k < columns.size(); k++) {
            String col = titles.get(k);
            int width = 100;
            sheet.setColumnWidth(cellIndex, width * 50);
            SXSSFCell cell = headRow.createCell(cellIndex);
            cell.setCellValue(col);
            cell.setCellStyle(headRowStyle);
            cellIndex++;
        }
    }


    private  void createSheet(SXSSFWorkbook wb,SXSSFSheet sheet, List<?> exportData, List<String> columns) {
        //内容栏样式设置
        CellStyle cellStyle = wb.createCellStyle();
        Font cellFont = wb.createFont();
        cellFont.setFontHeightInPoints((short) 10); //设置字体高度
        cellStyle.setFont(cellFont);

        for (int i = 0; i < exportData.size(); i++) {
            Object obj = exportData.get(i);
            SXSSFRow row = sheet.createRow(i + 1);
            int cellIndex = 0;//为了防止个别列隐藏的情况
            for (int k = 0; k < columns.size(); k++) {
                String col = columns.get(k);
                String celVal = "";
                try {
                    if (StringUtils.isNotBlank(col)) {
                        Object celValObj = PropertyUtils.getProperty(obj, col);
                        if (celValObj != null) {
                            celVal = celValObj.toString();
                        }
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
                if ("itemStatus".equals(col)){
                    celVal = orderStatus(celVal);
                }
                SXSSFCell cell = row.createCell(cellIndex);
                cell.setCellValue(celVal);
                cell.setCellStyle(cellStyle);
                cellIndex++;
            }
        }
    }


    private String orderStatus(String val){
        if ("CREATED,MODIFY_UNPAID,CANCEL_UNPAID".matches("(.*)"+val+"(.*)")){
            return "未支付";
        }else if ("CREATE_REVIEW,MODIFY_REVIEW,CANCEL_REVIEW".matches("(.*)"+val+"(.*)")){
            return "待审核";
        }else if ("CONFIRMED,EXECUTING".matches("(.*)"+val+"(.*)")){
            return "已支付";
        }else if ("CANCELED,'CREATE_REJECTED".matches("(.*)"+val+"(.*)")){
            return "已取消";
        }else if ("COMPLETED".matches("(.*)"+val+"(.*)")){
            return "已完成";
        }else {
            return "未定义状态";
        }
    }
}
