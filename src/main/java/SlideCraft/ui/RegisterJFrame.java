package SlideCraft.ui;

// ============ import：引进来才能用 ============
import java.awt.Color;              // Color = 颜色
import java.awt.Font;               // Font  = 字体
import javax.swing.JButton;         // JButton = 按钮
import javax.swing.JFrame;          // JFrame = 窗口
import javax.swing.JLabel;          // JLabel = 文字标签
import javax.swing.JOptionPane;     // JOptionPane = 弹窗
import javax.swing.JPasswordField;  // JPasswordField = 密码输入框
import javax.swing.JTextField;      // JTextField = 文本输入框

import SlideCraft.UserDB;           // 我们的"用户存取"类

/**
 * 注册窗口。
 * 流程：输入用户名/密码/确认密码 → 点"注册" → 校验通过 → 账号存进 UserDB → 回到登录窗口
 */
public class RegisterJFrame extends JFrame {   // 继承：RegisterJFrame 也是一种窗口

    private static final int W = 488;   // 窗口宽
    private static final int H = 500;   // 窗口高

    // 三个输入框的变量（先声明，构造器里再 new 赋值）
    private JTextField userField;       // 用户名输入框
    private JPasswordField pwdField;    // 密码输入框
    private JPasswordField confirmField;// 确认密码输入框

    // 构造器：new RegisterJFrame() 时自动执行
    public RegisterJFrame() {
        initFrame();            // ① 设置窗口
        initWidgets();          // ② 放控件
        this.setVisible(true);  // ③ 显示窗口
    }

    // 设置窗口本身
    private void initFrame() {
        this.setSize(W, H);                             // 窗口大小
        this.setTitle("注册");                          // 窗口标题
        this.setLocationRelativeTo(null);               // 屏幕居中
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);   // 点 X = 退出程序
        this.getContentPane().setLayout(null);
        // 嵌套：先拿内容区，再关掉自动排版（手动 setBounds 摆放）
        this.getContentPane().setBackground(new Color(245, 238, 220));
        // 嵌套：先造一个米黄色，再设为背景色（跟登录窗口一个颜色，风格统一）
    }

    // 往窗口里放控件
    private void initWidgets() {
        // ---------- 标题 ----------
        JLabel title = new JLabel("注册新账号", JLabel.CENTER);   // 居中显示"注册新账号"
        title.setFont(new Font("微软雅黑", Font.BOLD, 26));       // 微软雅黑、加粗、26号
        title.setBounds(0, 45, W, 40);
        this.getContentPane().add(title);

        // ---------- 用户名 ----------
        JLabel userLabel = new JLabel("用户名：");
        userLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        userLabel.setBounds(90, 135, 80, 30);
        this.getContentPane().add(userLabel);

        userField = new JTextField();                  // 造出输入框，赋给字段变量
        userField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        userField.setBounds(180, 135, 210, 32);
        this.getContentPane().add(userField);

        // ---------- 密码 ----------
        JLabel pwdLabel = new JLabel("密　码：");
        pwdLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        pwdLabel.setBounds(90, 190, 80, 30);
        this.getContentPane().add(pwdLabel);

        pwdField = new JPasswordField();
        pwdField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        pwdField.setBounds(180, 190, 210, 32);
        this.getContentPane().add(pwdField);

        // ---------- 确认密码 ----------
        JLabel confirmLabel = new JLabel("确认密码：");
        confirmLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        confirmLabel.setBounds(90, 245, 80, 30);
        this.getContentPane().add(confirmLabel);

        confirmField = new JPasswordField();
        confirmField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        confirmField.setBounds(180, 245, 210, 32);
        this.getContentPane().add(confirmField);

        // ---------- "注册" 按钮 ----------
        JButton registerBtn = new JButton("注　册");
        registerBtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        registerBtn.setBounds(110, 315, 120, 38);
        registerBtn.addActionListener(e -> doRegister());
        // 回调：按钮被点击 → 执行 doRegister()（注册逻辑在下面）
        this.getContentPane().add(registerBtn);

        // ---------- "返回登录" 按钮 ----------
        JButton backBtn = new JButton("返回登录");
        backBtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        backBtn.setBounds(260, 315, 120, 38);
        backBtn.addActionListener(e -> {
            // 回调里两条语句，用 { } 包起来
            this.dispose();         // 关掉注册窗口
            new LoginJFrame();      // 重新打开登录窗口
        });
        this.getContentPane().add(backBtn);

        // 在"确认密码"框里按回车 = 触发注册
        confirmField.addActionListener(e -> doRegister());
    }

    // 注册逻辑（doRegister = "做注册"）
    private void doRegister() {
        String name = userField.getText().trim();             // 读用户名，去首尾空格
        String pwd = new String(pwdField.getPassword());      // 读密码（char[] 转字符串）
        String confirm = new String(confirmField.getPassword()); // 读确认密码

        // 第一道检查：有没有空着的（三个里有一个为空就提示并停止）
        if (name.isEmpty() || pwd.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名和密码不能为空");
            return;   // return = 结束本方法，下面的代码不执行
        }

        // 第二道检查：两次输入的密码一不一致
        if (!pwd.equals(confirm)) {
            // !  = 取反（"不是"）；equals = 两个字符串内容是否相同
            // 人话：如果密码和确认密码不一样 → 提示
            JOptionPane.showMessageDialog(this, "两次输入的密码不一致");
            return;
        }

        // 第三道检查：用户名是否被占用了
        if (!UserDB.register(name, pwd)) {
            // UserDB.register(用户名, 密码) = 尝试存账号
            // 返回 false 说明"存不进去"（用户名重复或为空），所以前面加 ! 取反判断
            JOptionPane.showMessageDialog(this, "用户名已存在，换一个试试");
            return;
        }

        // 三道检查全过 = 注册成功
        JOptionPane.showMessageDialog(this, "注册成功，请登录");
        this.dispose();         // 关掉注册窗口
        new LoginJFrame();      // 回登录窗口
    }
}
