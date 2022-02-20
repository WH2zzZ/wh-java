package com.wanghan.java8.zhulin;

/**
 * @Author WangHan
 * @Create 2022/2/8 5:00 下午
 */
public class Demo01 {

    public static void main(String[] args) {
        // 0201
        int a = 524;
        // 0102
        int b = 549;
        // 0202
        int c = 526;

        // 三个点坐标 固定c点为z轴， a点为y轴， b为x轴
        // A点坐标
        int pointA_x = 0;
        int pointA_y = 20;
        int pointA_z = a;
        //B点坐标
        int pointB_x = 20;
        int pointB_y = 0;
        int pointB_z = b;
        //C点坐标
        int pointC_x = 0;
        int pointC_y = 0;
        int pointC_z = c;

        // 求法向量N1
        int vectorAC_x = 0;
        int vectorAC_y = pointC_y - pointA_y;
        int vectorAC_z = pointC_z - pointA_z;
        double vectorN1_y = -(double) vectorAC_z / vectorAC_y;

        int vectorBC_x = pointC_x - pointB_x;
        int vectorBC_y = 0;
        int vectorBC_z = pointC_z - pointB_z;
        double vectorN1_x = -(double) vectorBC_z / vectorBC_x;
        double vectorN1_z = 1;

        // 设置法向量N2的坐标
        double vectorN2_x = 0;
        double vectorN2_y = 0;
        double vectorN2_z = 1;


        //向量的点乘
        double vector = vectorN1_x * vectorN2_x + vectorN1_y * vectorN2_y + vectorN1_z * vectorN2_z;

        //向量的模乘
        double sqrt = Math.sqrt(vectorN1_x * vectorN1_x + vectorN1_y * vectorN1_y + vectorN1_z * vectorN1_z) *
                Math.sqrt(vectorN2_x * vectorN2_x + vectorN2_y * vectorN2_y + vectorN2_z * vectorN2_z);
        System.out.println("向量的点乘：" + vector);
        System.out.println("向量的模乘" + sqrt);

        double radian = Math.acos(vector / sqrt);
        double degree= (180 * radian / Math.PI);
        System.out.println("面角度：" + degree);
    }
}
