package com.wonder.service;


import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.wonder.entity.DepositAccountLog;
import com.wonder.mapper.TestMapper;
import com.wonder.utils.ExportColumn;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ReportService {

    @Autowired
    private TestMapper testMapper;

    private static Integer PAGE_SIZE = 200000;

    public  void export(HttpServletResponse response) throws IOException {
        long start = System.currentTimeMillis();
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        SXSSFSheet sheet = wb.createSheet();
        sheet.setRandomAccessWindowSize(-1);
        SXSSFRow headRow = sheet.createRow(0);
        //头栏样式设置
        CellStyle headRowStyle = wb.createCellStyle();
//        headRowStyle.setFillBackgroundColor();
//        headRowStyle.setFillForegroundColor();
        Font fontStyle = wb.createFont();
        fontStyle.setFontHeightInPoints((short) 14); //设置字体高度
        headRowStyle.setFont(fontStyle);
        headRow.setHeight((short) 350);

        //内容栏样式设置
        CellStyle cellStyle = wb.createCellStyle();
        Font cellFont = wb.createFont();
        cellFont.setFontHeightInPoints((short) 10); //设置字体高度
        cellStyle.setFont(cellFont);

        int cellIndex = 0;//为了防止个别列隐藏的情况
        String lie = "accountId,accountName,region,transactionDate,logId,category,subCategory,transactionAmount,operatorName,operateDate,comments";
        String ss = "账号id,代理商名称,区域,交易日期,日志id,交易类型,交易方式,交易金额,操作人,操作日期,备注";
        List<String> columns = Lists.newArrayList(lie.split(","));
        List<String> titles = Lists.newArrayList(ss.split(","));

        for (int k = 0; k < columns.size(); k++) {
            String col = titles.get(k);
            int width = 100;
            sheet.setColumnWidth(cellIndex, width * 50);
            SXSSFCell cell = headRow.createCell(cellIndex);
            cell.setCellValue(col);
            cell.setCellStyle(cellStyle);
            cellIndex++;
        }

        long num = testMapper.depositAccountLogCount();
        log.info("num {}",num);
        List<DepositAccountLog> list = testMapper.depositAccountLogQuery();
        createSheet(sheet,cellStyle,list,columns);

        long end = System.currentTimeMillis();
        log.info("处理时间 ，{}",(end-start)/1000);

        wb.write(response.getOutputStream());
    }


    private void createSheet(SXSSFSheet sheet,CellStyle cellStyle, List<?> exportData, List<String> columns) {
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
                SXSSFCell cell = row.createCell(cellIndex);
                cell.setCellValue(celVal);
                cell.setCellStyle(cellStyle);
                cellIndex++;
            }
        }
    }
}
