package startup;

import service.ConfigService;
import util.DBUtil;

import java.io.*;

public class MysqlUtil {

    public static void backup(String mysqlPath, String backupFile) throws IOException {
        String commandFormat = "\"%s/bin/mysqldump.exe\" -u%s -p%s -hlocalhost -P%d %s -r \"%s\"";
        String command = String.format(commandFormat, mysqlPath, DBUtil.loginName,
                DBUtil.password, DBUtil.port, DBUtil.database, backupFile);
        Runtime.getRuntime().exec(command);
    }

    public static void recover(String mysqlPath, String recoverFile) throws IOException {
        String commandFormat = "\"%s/bin/mysql.exe\" -u%s -p%s %s";
        String command = String.format(commandFormat, mysqlPath,
                DBUtil.loginName, DBUtil.password, DBUtil.database);
        Process p = Runtime.getRuntime().exec(command);
        OutputStream out = p.getOutputStream();
        String line;
        StringBuffer sb = new StringBuffer("");
        String outStr;
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(recoverFile), "utf8"));
        while((line = br.readLine()) != null){
            sb.append(line+"\r\n");
        }
        outStr = sb.toString();

        OutputStreamWriter osw = new OutputStreamWriter(out, "utf8");
        osw.write(outStr);
        osw.flush();

        out.close();
        br.close();
        osw.flush();

    }




}
