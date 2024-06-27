package com.zeeyeh.mctoosimple.client;

/**
 * @author Leon_Keiran
 * @description 口渴值实体类
 * @date 2024/6/26 下午11:14
 * @github <a href="https://github.com/bmlingqi">https://github.com/bmlingqi</a>
 */
public class ThirstData {
    private static int thirst;

    public static void setThirst(int thirst) {
        ThirstData.thirst = thirst;
    }

    public static int getThirst() {
        return thirst;
    }
}
