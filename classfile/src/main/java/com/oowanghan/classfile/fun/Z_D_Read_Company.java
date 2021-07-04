package com.oowanghan.classfile.fun;

import com.oowanghan.classfile.lsieun.utils.ByteDashboard;
import com.oowanghan.classfile.lsieun.utils.FileUtils;

public class Z_D_Read_Company {
    public static void main(String[] args) {
        String relative_path = "com/oowanghan/classfile/fun/company_info.bin";
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes = FileUtils.readBytes(filepath);

        ByteDashboard bd = new ByteDashboard(bytes);
        Company company = FunUtils.parseCompany(bd);
        System.out.println(company);
    }
}
