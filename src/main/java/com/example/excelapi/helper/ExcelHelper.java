package com.example.excelapi.helper;

import com.example.excelapi.entities.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelHelper {

    public static boolean checkExcelFormat(MultipartFile file){
        String contentType = file.getContentType();
        if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
            return true;
        }else{
            return false;
        }
    }
    //in this method we use apache poi for iterator between cells and extract data
    public static List<Student> convertExcelDB(InputStream inputStream){
        List<Student> list = new ArrayList<>();
        try{
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Planilha1");
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();
            while(iterator.hasNext()){
                Row row = iterator.next();
                if(rowNumber==0){
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                int cid = 0;
                Student student = new Student();
                while(cells.hasNext()){
                    Cell cell = cells.next();
                    switch (cid){
                        case 0:
                            student.setId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            student.setName(cell.getStringCellValue());
                            break;
                        case 2:
                            student.setGender(cell.getStringCellValue());
                            break;
                        case 3:
                            //date conversion to extract from cell and insert into database
                            String dateExcel = cell.getDateCellValue().toString();
                            DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
                            java.util.Date date = dateFormat.parse(dateExcel);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            String formattedDate = calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH))+"-"+(calendar.get(Calendar.DATE));
                            student.setBirthDate(Date.valueOf(formattedDate));
                            break;
                        case 4:
                            student.setFirstGrade((short) cell.getNumericCellValue());
                            break;
                        case 5:
                            student.setSecondGrade((short) cell.getNumericCellValue());
                            break;
                        case 6:
                            student.setThirdGrade((short) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                list.add(student);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
