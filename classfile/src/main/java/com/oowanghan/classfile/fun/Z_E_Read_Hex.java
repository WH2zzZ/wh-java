package com.oowanghan.classfile.fun;

import com.oowanghan.classfile.lsieun.utils.FileUtils;
import com.oowanghan.classfile.lsieun.utils.HexFormat;
import com.oowanghan.classfile.lsieun.utils.HexUtils;

public class Z_E_Read_Hex {
    public static void main(String[] args) {
        String relative_path = "com/oowanghan/classfile/fun/user_info.bin";
        String filepath = FileUtils.getFilePath(relative_path);

        byte[] bytes = FileUtils.readBytes(filepath);
        String hex_str = HexUtils.format(bytes, HexFormat.FORMAT_FF_SPACE_FF_32);
        System.out.println(hex_str);
    }
}
