package com.oowanghan.classfile.run;

import com.oowanghan.classfile.lsieun.utils.FileUtils;
import com.oowanghan.classfile.lsieun.utils.HexUtils;
import com.oowanghan.classfile.lsieun.utils.ReadUtils;

public class C_Change_MagicNumber {
    public static void main(String[] args) {
        // 第一步，输入参数
        String relative_path = "com/oowanghan/classfile/sample/HelloWorld.class";

        // 第二步，读取数据
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes = ReadUtils.readByPath(filepath);

        // 第三步，处理数据
        String magic_str = "CAFEBABF"; // 注意：正确的值应该是CAFEBABE，
        byte[] magic_bytes = HexUtils.parse(magic_str);
        for (int i = 0; i < magic_bytes.length; i++) {
            bytes[i] = magic_bytes[i];
        }
        FileUtils.writeBytes(filepath, bytes);
    }
}
