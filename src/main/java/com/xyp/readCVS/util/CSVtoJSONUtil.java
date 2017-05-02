package com.xyp.readCVS.util;

/**
 * Created by DT288 on 2016/8/10.
 */
public class CSVtoJSONUtil {

    //,(?=([^"]*"[^"])*[^"]*$) 切割的正则
    private static final String CSV_REGEX = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    public static String CSVtoJSON(String output) {

        String[] lines = output.split("\n");
        StringBuilder builder = new StringBuilder();
        builder.append("{");

        //分页行的处理，并且对于第一行Title，有空格全部去掉，'-'字符变成'_'
        String firstLine = "";
        int firstIndex = 1;
        if (lines[0].split(",").length == 1) {
            firstLine = lines[1].replaceAll("\\s*", "").replace("-", "_");
            firstIndex = 2;
            builder.append(lines[0]);
        } else {
            firstLine = lines[0].replaceAll("\\s*", "").replace("-", "_");
            builder.append("NEXTROW:''");
        }
        builder.append(',');
        builder.append("result:[");
        String[] headers = firstLine.replaceAll("\"", "").split(",", -1);

        //CSV TO JSON
        for (int i = firstIndex; i < lines.length; i++) {
            String[] values = lines[i].split(CSV_REGEX, -1);
            builder.append('{');
//            if (values.length != headers.length) {
//                System.out.println("This line maybe error");
//            }
            for (int j = 0; j < values.length && j < headers.length; j++) {
                String value = values[j].replace("\"", "");
                String jsonvalue = "\"" + headers[j] + "\":\"" + value + "\"";
                if (j < headers.length - 1) { //if not last value of values...
                    jsonvalue += ',';
                }
                builder.append(jsonvalue);
            }
            builder.append('}');
            if (i < lines.length - 1) {
                builder.append(',');
            }

        }
        builder.append("]}");
        output = builder.toString();

        return output;
    }

}
