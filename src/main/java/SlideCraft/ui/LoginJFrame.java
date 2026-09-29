package SlideCraft.ui;

// ============ import：把别人写好的类"引进来"才能用 ============
// 大写单词 = 类名（类型）。记不住没关系，注释里都标了中文意思
import java.awt.Color;              // Color  = 颜色类（245,238,220 这种 RGB 数值）
import java.awt.Font;               // Font   = 字体类
import javax.swing.JButton;         // JButton = 按钮
import javax.swing.JFrame;          // JFrame = 窗口（我们的所有窗口都继承它）
import javax.swing.JLabel;          // JLabel = 文字/图片标签
import javax.swing.JOptionPane;     // JOptionPane = 弹窗工具（弹出提示框）
import javax.swing.JPasswordField;  // JPasswordField = 密码输入框（内容显示成圆点）
import javax.swing.JTextField;      // JTextField = 普通文本输入框

import SlideCraft.UserDB;           // 我们自己写的"用户存取"类（在同一项目的其他包里）

/**
 * 登录窗口。
 * 流程：输入用户名密码 → 点"登录" → 验证通过 → 关掉自己 → 打开拼图窗口 GameJFrame
 */
public class LoginJFrame extends JFrame {
    // extends = 继承。意思是：LoginJFrame 本身就是一种"窗口"，JFrame 里所有能力它都有
    // （比如 setSize、setTitle、setVisible 这些方法都是继承来的，不用自己写）

    // final = 值一旦定了就不能改；static final 合起来 = "常量"（全大写命名是 Java 惯例）
    private static final int W = 488;   // 窗口宽度（像素）
    private static final int H = 430;   // 窗口高度

    // ====== 字段（成员变量）：整个类里所有方法都能用 ======
    private JTextField userField;       // 装"用户名输入框"的变量（现在只是声明，还没造出来）
    private JPasswordField pwdField;    // 装"密码输入框"的变量

    // 构造器：执行 new LoginJFrame() 时自动运行的代码（只执行这一次）
    public LoginJFrame() {
        initFrame();            // ① 设置窗口本身（大小、标题、背景色）
        initWidgets();          // ② 往窗口里放控件（输入框、按钮）
        this.setVisible(true);  // ③ 把窗口显示出来（true = 显示；this = 当前这个窗口自己）
    }

    // 设置窗口本身
    private void initFrame() {
        this.setSize(W, H);                     // 窗口的大小 = 宽 W、高 H
        this.setTitle("登入");                  // 窗口的标题
        this.setLocationRelativeTo(null);       // 窗口出现在屏幕正中央（null 表示"相对屏幕"）
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // 上面这句：点窗口右上角 X 时的行为。EXIT_ON_CLOSE = 关闭窗口同时退出整个程序
        // （因为登录窗口是程序第一个窗口，关掉它程序就该结束）

        // 布局设在 contentPane 上才生效（和 GameJFrame 同样的坑）
        this.getContentPane().setLayout(null);
        // 嵌套拆解：先 this.getContentPane() 拿到"窗口的内容区"这个对象，
        //           再对这个内容区调用 setLayout(null)
        // setLayout(null) = 关掉自动排版，改由我们手动 setBounds 指定每个控件的位置

        this.getContentPane().setBackground(new Color(245, 238, 220));
        // 嵌套拆解：先 new Color(245,238,220) 造一个"米黄色"（三个数是红/绿/蓝，0~255），
        //           再把这个颜色设为内容区的背景色
    }

    // 往窗口里放控件
    private void initWidgets() {
        // ---------- 标题文字 ----------
        JLabel title = new JLabel("SlideCraft 拼图", JLabel.CENTER);
        // new JLabel(...) 造一个"文字标签"，JLabel.CENTER 表示文字水平居中
        title.setFont(new Font("微软雅黑", Font.BOLD, 26));
        // 嵌套拆解：new Font(字体名, 样式, 字号) 造一个字体对象，再设给标题。
        //           Font.BOLD = 加粗
        title.setBounds(0, 45, W, 40);
        // setBounds(x, y, 宽, 高)：手动指定控件位置。x,y 是左上角坐标（相对内容区）
        this.getContentPane().add(title);
        // 嵌套拆解：先拿到内容区，再把标题 add（放）进内容区

        // ---------- "用户名：" 三个字 ----------
        JLabel userLabel = new JLabel("用户名：");
        userLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));  // Font.PLAIN = 不加粗
        userLabel.setBounds(90, 140, 80, 30);                     // 放在 (90,140)，宽80高30
        this.getContentPane().add(userLabel);

        // ---------- 用户名输入框 ----------
        userField = new JTextField();
        // 注意：这里不是再声明一个变量，而是给上面已经声明好的 userField 赋值
        //       new JTextField() = 真正造一个输入框出来
        userField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        userField.setBounds(175, 140, 220, 32);   // 放在"用户名："右边
        this.getContentPane().add(userField);

        // ---------- "密码：" 三个字 ----------
        JLabel pwdLabel = new JLabel("密　码：");
        pwdLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        pwdLabel.setBounds(90, 195, 80, 30);
        this.getContentPane().add(pwdLabel);

        // ---------- 密码输入框（输入显示成圆点） ----------
        pwdField = new JPasswordField();
        pwdField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        pwdField.setBounds(175, 195, 220, 32);
        this.getContentPane().add(pwdField);

        // ---------- "登录" 按钮 ----------
        JButton loginBtn = new JButton("登　录");
        loginBtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        loginBtn.setBounds(110, 270, 120, 38);
        // ★ 重点：给按钮挂"点击后干嘛"的回调（这叫 监听器/事件）
        loginBtn.addActionListener(e -> doLogin());
        // 拆解这一行（函数嵌套函数的核心）：
        //   addActionListener( ... )  = "注册一个动作监听器"，意思是：这个按钮被点击时执行(...)
        //   e -> doLogin()            = 一种简写，代表"一小段要执行的代码"
        //   e                         = 事件信息（比如点了哪个按钮），这里没用到
        //   整句人话：按钮被点击时，就调用 doLogin() 这个方法（登录逻辑在下面）
        this.getContentPane().add(loginBtn);

        // ---------- "注册" 按钮 ----------
        JButton registerBtn = new JButton("注　册");
        registerBtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        registerBtn.setBounds(260, 270, 120, 38);
        registerBtn.addActionListener(e -> {
            // 这个回调里有两条语句，所以用 { } 包起来
            this.dispose();         // 先关掉登录窗口自己（dispose = 销毁窗口）
            new RegisterJFrame();   // 再打开注册窗口（new 一个窗口，构造器里会自动显示）
        });
        // 人话：点击"注册" = 关掉登录窗口 + 打开注册窗口（一前一后，顺序执行）
        this.getContentPane().add(registerBtn);

        // ---------- 输入框里按回车 = 触发登录 ----------
        userField.addActionListener(e -> doLogin());   // 在用户名框按回车 → 登录
        pwdField.addActionListener(e -> doLogin());    // 在密码框按回车 → 登录
        // 为什么输入框也能 addActionListener？因为 JTextField 里按回车会触发"动作事件"，
        // 跟按钮被点击触发的是同一种事件，所以都走 doLogin()
    }

    // 登录逻辑（doLogin = "做登录"）
    private void doLogin() {
        String name = userField.getText().trim();
        // 嵌套拆解：userField.getText() 拿输入框里用户敲的字，
        //           .trim() 去掉首尾空格（防止用户不小心多敲了空格）
        String pwd = new String(pwdField.getPassword());
        // 嵌套拆解：pwdField.getPassword() 拿密码框里的内容（出于安全它返回 char[] 数组），
        //           new String(数组) 把数组转成字符串，方便和存好的密码比较

        if (name.isEmpty() || pwd.isEmpty()) {
            // isEmpty() = 是否为空字符串；|| = 或者
            // 人话：用户名或密码有一个是空的 → 提示，不继续
            JOptionPane.showMessageDialog(this, "用户名和密码不能为空");
            // JOptionPane = 弹窗工具。showMessageDialog(窗口, 文字) = 弹一个小提示框
            return;   // return = 立刻结束 doLogin 这个方法，后面的代码不执行了
        }

        if (UserDB.login(name, pwd)) {
            // UserDB.login(...) = 去用户库里查：用户名和密码对不对
            // 它返回 boolean（true=对，false=错），所以能直接放进 if 的条件里
            // 人话：登录成功 →
            this.dispose();         // 关掉登录窗口
            new GameJFrame();       // 打开拼图游戏窗口
        } else {
            // 人话：登录失败 → 弹窗提示
            JOptionPane.showMessageDialog(this, "用户名或密码错误");
        }
    }
}
