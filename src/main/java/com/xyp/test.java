package com.xyp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class test {

    private InputStreamReader fr = null;
    private BufferedReader br = null;

    public test(String f) throws IOException {
        fr = new InputStreamReader(new FileInputStream(f));
    }

    public static void main(String[] args) {
        try {
            readCSVFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析csv文件 到一个list中 每个单元个为一个String类型记录，每一行为一个list。 再将所有的行放到一个总list中
     */
    public static List<List<String>> readCSVFile() throws IOException {
//        br = new BufferedReader(fr);
        String rec = "HH,ADLHI,46007,Hilton Adelaide,OD53BR,Promo,OD53AR,Hot Deal,OD53LR,OD53SR,OD53RR,OD53TR,OD15YR,OD15YR,OD53TR,OD15YR,NULL,RPYNR1,2016/12/20,2017/1/20,2016/12/20,2017/12/31\n";// 一行
        String str;// 一个单元格
        List<List<String>> listFile = new ArrayList<List<String>>();
        try {
            // 读取一行
//            while ((rec = br.readLine()) != null) {
            Pattern pCells = Pattern
                    .compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*[,\n]");
            Matcher mCells = pCells.matcher(rec);
            List<String> cells = new ArrayList<String>();// 每行记录一个list
            // 读取每个单元格
            while (mCells.find()) {
                str = mCells.group();
                str = str.replaceAll(
                        "(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");
                str = str.replaceAll("(?sm)(\"(\"))", "$2");
                cells.add(str);
            }
            listFile.add(cells);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        } finally {
//            if (fr != null) {
//                fr.close();
//            }
//            if (br != null) {
//                br.close();
//            }
//        }
        return listFile;
    }
}