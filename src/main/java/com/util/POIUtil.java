package com.util;

import com.model.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class POIUtil {
    public static List<User> importExcel(InputStream file,String filename) throws Exception {
//        将Excel文件行变成对象，将对象集合返回
        String prefix = filename.substring(filename.lastIndexOf("."));
        boolean isExcel2003 = prefix.toLowerCase().endsWith("xls")?true:false;
//        工作簿
        Workbook workbook = null;
        if(isExcel2003){
            workbook = new HSSFWorkbook(file);
        }else{
            workbook = new XSSFWorkbook(file);
        }
//        工作表
        Sheet sheet = null;
//        存放对象的集合
        List<User> userList = new ArrayList<User>();
//        外层循环是找表
        for(int i = 0;i < workbook.getNumberOfSheets();i++){
            sheet = workbook.getSheetAt(i);
//            内层循环是找有效行数（带数据）
            for(int j = 1;j < sheet.getPhysicalNumberOfRows();j++){
//                表格行
                Row row = sheet.getRow(j);
                if(row != null){
                    User user = new User();
//                    从第一列到最后一列依次取值，给对象赋值
                    for(int k = 0;k < row.getLastCellNum();k++){
//                        判断单元格是否为空
                        if(row.getCell(k)!=null && !row.getCell(k).equals("")){
                            if (k == 0) {
                                Cell cell = row.getCell(0);
                                user.setUsername(cell.getStringCellValue());
                            }else if (k == 1) {
                                Cell cell = row.getCell(1);
                                user.setEmail(cell.getStringCellValue());
                            }else if (k == 2) {
                                Cell cell = row.getCell(2);
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                user.setPassword(cell.getStringCellValue());
                            }else if (k == 3) {
                                Cell cell = row.getCell(3);
                                user.setName(cell.getStringCellValue());
                            }else if (k == 4) {
                                Cell cell = row.getCell(4);
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                user.setPhone(cell.getStringCellValue());
                            }else if (k == 5) {
                                Cell cell = row.getCell(5);
                                user.setAddress(cell.getStringCellValue());
                            }
                        }
                    }
                    userList.add(user);
                }
            }
        }
        System.out.println("读表完成");
        return userList;
    }
}
