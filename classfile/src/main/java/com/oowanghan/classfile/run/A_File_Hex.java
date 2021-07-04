package com.oowanghan.classfile.run;

import com.oowanghan.classfile.lsieun.utils.FileUtils;
import com.oowanghan.classfile.lsieun.utils.HexFormat;
import com.oowanghan.classfile.lsieun.utils.HexUtils;
import com.oowanghan.classfile.lsieun.utils.ReadUtils;

public class A_File_Hex {
    public static void main(String[] args) {
        // 第一步，输入参数
        String relative_path = "com/oowanghan/classfile/sample/HelloWorld.class";

        // 第二步，读取数据
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes = ReadUtils.readByPath(filepath);

        // 第三步，输出数据
        String hex_str = HexUtils.format(bytes, HexFormat.FORMAT_FF_SPACE_FF_32);
        System.out.println(hex_str);
    }
}
