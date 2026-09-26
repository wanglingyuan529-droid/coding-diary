package SlideCraft.ui;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GameJFrame extends javax.swing.JFrame {

    public GameJFrame() {
        initJMenuBar();

        initJFrame();

        // 让显示显示出来
        this.setVisible(true);
    }

    public void initJFrame() {
        // 设置界面的宽高
        this.setSize(603, 680);
        this.setVisible(true);
        // 设置界面的标题
        this.setTitle("拼图游戏 V1.0");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(3);
    }

    public void initJMenuBar() {
        // 初始化菜单
        javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        // 创建选项下面的条目对象
        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reloginItem = new JMenuItem("重新登入");
        JMenuItem closeItem = new JMenuItem("关闭游戏");
        JMenuItem accountItem = new JMenuItem("公众号");

        // 将每一个选项下面的条目添加到选项中
        functionJMenu.add(replayItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);

        // 将菜单添加到界面中
        menuBar.add(functionJMenu);
        menuBar.add(aboutJMenu);
        this.setJMenuBar(menuBar);

        // 给这个界面设置菜单
        this.setJMenuBar(menuBar);

    }
}
