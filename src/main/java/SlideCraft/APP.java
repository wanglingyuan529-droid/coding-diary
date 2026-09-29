package SlideCraft;

import SlideCraft.ui.LoginJFrame;

/**
 * 程序入口。
 * 从登录窗口开始：登录成功后才进入拼图主窗口 GameJFrame。
 */
public class APP {
    public static void main(String[] args) {
        new LoginJFrame();
    }
}
