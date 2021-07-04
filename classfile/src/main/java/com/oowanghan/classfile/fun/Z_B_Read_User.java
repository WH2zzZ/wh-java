package com.oowanghan.classfile.fun;

import com.oowanghan.classfile.lsieun.utils.ByteDashboard;
import com.oowanghan.classfile.lsieun.utils.FileUtils;

public class Z_B_Read_User {
    public static void main(String[] args) {
        String relative_path = "com/oowanghan/classfile/fun/user_info.bin";
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes = FileUtils.readBytes(filepath);

        ByteDashboard bd = new ByteDashboard(bytes);
        User user = FunUtils.parseUser(bd);
        System.out.println(user);
    }
}
