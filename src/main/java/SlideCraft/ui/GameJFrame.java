package SlideCraft.ui;

// ============ import：把要用到的类引进来 ============
import java.awt.Color;                  // Color = 颜色
import java.awt.Font;                   // Font  = 字体
import java.awt.Image;                  // Image = 图片（缩放时用）
import java.awt.event.ActionEvent;      // ActionEvent = "动作事件"（点按钮/按回车触发）
import java.awt.event.ActionListener;   // ActionListener = "动作监听器"接口（要求实现 actionPerformed 方法）
import java.awt.event.KeyEvent;         // KeyEvent = "键盘事件"（按键时触发）
import java.awt.event.KeyListener;      // KeyListener = 键盘监听器接口
import java.awt.event.MouseEvent;       // MouseEvent = "鼠标事件"（点击时触发）
import java.awt.event.MouseListener;    // MouseListener = 鼠标监听器接口
import java.util.ArrayList;             // ArrayList = 可变数组（能随时往里加东西的列表）
import java.util.Arrays;                // Arrays = 数组工具类（fill 等方法）
import java.util.Collections;           // Collections = 集合工具类（shuffle 打乱等）
import java.util.List;                  // List = "列表"接口（ArrayList 的"身份证"类型）
import java.util.Random;                // Random = 随机数生成器

import javax.swing.ImageIcon;           // ImageIcon = 图片（把图片文件装进来）
import javax.swing.JButton;             // JButton = 按钮
import javax.swing.JDialog;             // JDialog = 弹窗（小窗口）
import javax.swing.JFrame;              // JFrame = 窗口
import javax.swing.JLabel;              // JLabel = 标签（放文字或图片）
import javax.swing.JMenu;               // JMenu = 菜单（菜单栏里的一个下拉项）
import javax.swing.JMenuBar;            // JMenuBar = 菜单栏（窗口顶部的横条）
import javax.swing.JMenuItem;           // JMenuItem = 菜单项（菜单里的一条）
import javax.swing.JPanel;              // JPanel = 面板（一块空白区域，可以装东西）
import javax.swing.border.BevelBorder;  // BevelBorder = 立体边框（凹下去/凸起来的效果）

/**
 * 拼图游戏主窗口（游戏本体）。
 *
 * ★ 游戏怎么"记"的（数据模型，最重要的一行注释）：
 *   用一个 int[9] 数组 data 记录 9 个格子的状态：
 *     - data[位置] = 图片编号（1~8），0 表示"空格"
 *     - 完成态：{1,2,3,4,5,6,7,8,0}（按顺序排好，最后一个是空格）
 *   界面怎么画：每次状态变了，就把 9 个格子重新摆一遍（paintPuzzle）
 *
 * 交互：
 *   - 鼠标点击格子：如果它旁边是空格，就和空格交换（移动）
 *   - W 键：弹出"完整图预览"（看拼好的样子）
 *   - A 键：作弊，直接恢复完成态
 */
public class GameJFrame extends JFrame
        implements MouseListener, KeyListener, ActionListener {
    // 继承 + 实现接口：
    //   extends JFrame               = GameJFrame 是一种窗口
    //   implements MouseListener 等  = 承诺"我会处理鼠标/键盘/动作事件"，
    //                                   所以必须实现接口里的方法（下面有 @Override 的那些）
    // 注意：界面和逻辑都在这一个类里，所以这个类既有"窗口"，又有"游戏逻辑"

    // ============ 常量 ============
    private static final int SIZE = 105;       // 每格边长 105 像素
    private static final int COLS = 3;         // 3 列
    private static final int ROWS = 3;         // 3 行
    private static final int IMG_COUNT = ROWS * COLS;   // 格子总数 = 9（3x3）
    private static final String IMG_DIR =
            "C:\\study\\java\\coding-diary\\src\\main\\java\\SlideCraft\\异环娜娜莉\\images\\";
    // 图片文件夹路径。注意 Java 字符串里 \\ 表示一个真正的反斜杠

    // ============ 状态（游戏数据） ============
    /** data[i] = "位置 i 上的图片编号"，0 表示空格 */
    private final int[] data = new int[IMG_COUNT];
    // new int[9] = 造一个能装 9 个整数的数组，每个位置默认是 0

    /** icons[图片编号] = 对应图片，下标 0 留空（0 代表空格，没图） */
    private final ImageIcon[] icons = new ImageIcon[IMG_COUNT + 1];
    // 下标 0~9（10 个），只用 1~8

    /** cells[位置] = 摆到界面上的 9 个 JLabel（每个格子一个） */
    private final JLabel[] cells = new JLabel[IMG_COUNT];

    /** 走了多少步 */
    private int stepCount = 0;

    /** 显示"步数：X"的那个标签（在顶部条里，每次步数变了就改它的文字） */
    private JLabel stepLabel;

    // 菜单命令的代号（字符串常量）。点菜单项时，用它来判断"点的是哪一项"
    private static final String CMD_REPLAY   = "replay";    // 重新游戏
    private static final String CMD_RELOGIN  = "relogin";   // 重新登入
    private static final String CMD_CLOSE    = "close";     // 关闭游戏
    private static final String CMD_ACCOUNT  = "account";   // 公众号

    // 构造器：new GameJFrame() 时按顺序执行
    public GameJFrame() {
        initImages();       // ① 把 8 张图片读进内存（先读好，以后只换编号不重读文件）
        resetData();        // ② 数据恢复成完成态 {1,2,3,4,5,6,7,8,0}
        initJFrame();       // ③ 设置窗口
        initJMenuBar();     // ④ 顶部菜单栏
        initTopBar();       // ⑤ 顶部信息条（标题 + 步数）
        paintPuzzle();      // ⑥ 把 9 个格子画到界面上
        this.setVisible(true);  // ⑦ 显示窗口
    }

    // =============================================================
    // 1. 窗口与菜单初始化
    // =============================================================
    public void initJFrame() {
        this.setSize(603, 680);                 // 窗口大小
        this.setTitle("拼图游戏 V1.0");         // 标题
        this.setAlwaysOnTop(true);              // 窗口永远在最上面
        this.setLocationRelativeTo(null);       // 屏幕居中
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);   // 点 X 退出程序
        this.getContentPane().setLayout(null);
        // 嵌套：先拿内容区，再关掉自动排版（手动摆放，跟登录窗口同一个坑）
        this.getContentPane().setBackground(new Color(245, 238, 220));   // 米黄背景
        this.addKeyListener(this);
        // 给窗口挂键盘监听。this = 监听器就是本类自己（本类实现了 KeyListener）
        // 人话：窗口收到按键 → 调用本类的 keyPressed 方法（在下面）
    }

    public void initJMenuBar() {
        // 菜单栏结构：菜单栏(JMenuBar) > 菜单(JMenu) > 菜单项(JMenuItem)
        JMenuBar menuBar = new JMenuBar();      // 造菜单栏
        JMenu functionJMenu = new JMenu("功能");    // 造菜单"功能"
        JMenu aboutJMenu   = new JMenu("关于我们"); // 造菜单"关于我们"

        JMenuItem replayItem  = new JMenuItem("重新游戏");  // 菜单项
        JMenuItem reloginItem = new JMenuItem("重新登入");
        JMenuItem closeItem   = new JMenuItem("关闭游戏");
        JMenuItem accountItem = new JMenuItem("公众号");

        // 给每个菜单项发一个"代号"，事件分发时靠代号区分点了哪项
        replayItem.setActionCommand(CMD_REPLAY);
        reloginItem.setActionCommand(CMD_RELOGIN);
        closeItem.setActionCommand(CMD_CLOSE);
        accountItem.setActionCommand(CMD_ACCOUNT);
        // 每个菜单项都挂上动作监听（点了 → 执行本类的 actionPerformed 方法）
        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        // 组装：菜单项放进菜单，菜单放进菜单栏
        functionJMenu.add(replayItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);

        menuBar.add(functionJMenu);
        menuBar.add(aboutJMenu);
        this.setJMenuBar(menuBar);   // 把菜单栏装到窗口顶部
    }

    /**
     * 顶部信息条：左侧标题，右侧步数。
     * 用一个 JPanel（面板）当容器，里面放两个标签，再把面板放进窗口。
     * （这就是"容器套组件"的嵌套：窗口 > 面板 > 标签）
     */
    private void initTopBar() {
        JPanel topBar = new JPanel();           // 造一块面板
        topBar.setLayout(null);                 // 面板里也手动摆放
        topBar.setBounds(0, 0, 603, 40);        // 面板占窗口最顶部一条，高 40
        topBar.setBackground(new Color(220, 200, 170));   // 面板背景色（深一点的米黄）

        JLabel title = new JLabel("SlideCraft 拼图 · 异环娜娜莉");
        title.setFont(new Font("微软雅黑", Font.BOLD, 16));
        title.setBounds(10, 8, 300, 24);        // 面板内坐标
        topBar.add(title);                      // 标签放进面板

        stepLabel = new JLabel("步数：0");      // 步数标签（存进字段，之后要改文字）
        stepLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        stepLabel.setBounds(470, 8, 120, 24);
        topBar.add(stepLabel);

        this.getContentPane().add(topBar);      // 面板放进窗口内容区
    }

    // =============================================================
    // 2. 图片加载
    // =============================================================
    private void initImages() {
        // 只加载 1~8 号图，第 9 格是空格（没有图）
        for (int n = 1; n <= 8; n++) {
            // for 循环：n 从 1 到 8，每次 +1，循环体执行 8 次
            String path = IMG_DIR + String.format("异环娜娜莉_%02d.png", n);
            // 嵌套拆解：String.format("...%02d...", n) = 把 n 补成两位数字的文本
            //           （n=1 时得到"异环娜娜莉_01.png"），再和文件夹路径拼接成完整路径
            icons[n] = scaleIcon(new ImageIcon(path), SIZE, SIZE);
            // 嵌套拆解（从里往外读）：
            //   1. new ImageIcon(path)  把图片文件读进来
            //   2. scaleIcon(图片, 105, 105)  把图缩放到 105x105 以内
            //   3. icons[n] = ...  存进图标数组（下标 n）
        }
        icons[0] = null;   // 下标 0 是"空格"，没有图（null = 什么都没有）
    }

    /**
     * 把图片按比例缩小/放大到 targetW x targetH 之内（contain 模式）：
     * 整张图完整可见、不变形，长边贴满目标，短边留白。
     */
    private ImageIcon scaleIcon(ImageIcon icon, int targetW, int targetH) {
        int w = icon.getIconWidth();    // 图片原本的宽
        int h = icon.getIconHeight();   // 图片原本的高
        // 缩放比例 = 目标尺寸 ÷ 原始尺寸，宽和高中"更需要缩小的那个"为准（Math.min 取较小值）
        double ratio = Math.min(targetW / (double) w, targetH / (double) h);
        // (double) w 把 w 转成小数再除，避免整数除法（105/83 在整数除法下会得 1）
        int newW = Math.max(1, (int) (w * ratio));   // 新宽度（至少 1 像素）
        int newH = Math.max(1, (int) (h * ratio));   // 新高度
        // (int) 是"强制转换"：把小数值截断成整数
        Image scaled = icon.getImage().getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        // 嵌套拆解：icon.getImage() 取出图片本身 → 调用 getScaledInstance(宽,高,算法)
        //           按新尺寸缩放。SCALE_SMOOTH = 平滑算法（放大不糊）
        return new ImageIcon(scaled);   // 把缩放好的图片重新包成 ImageIcon 返回
        // return = 把这个结果交还给调用者（initImages 里接住它存进数组）
    }

    // =============================================================
    // 3. 数据与打乱
    // =============================================================
    /** 把 data 重置为完成态 {1,2,3,4,5,6,7,8,0} */
    private void resetData() {
        for (int i = 0; i < IMG_COUNT; i++) {   // i 从 0 到 8
            // 最后一个位置(i=8)放 0（空格），其他位置放 i+1（图片编号）
            data[i] = (i == IMG_COUNT - 1) ? 0 : (i + 1);
            // ? : 是"三目运算"，人话：如果 i 是最后一个 → 存 0，否则 → 存 i+1
        }
        stepCount = 0;                          // 步数清零
        if (stepLabel != null) stepLabel.setText("步数：0");   // 顶部标签也刷新
    }

    /**
     * 打乱图片。
     * 不用"直接随机排"——那样有 50% 概率生成无解的局面（数学上的奇偶性问题），
     * 而是"从完成态开始，让空格随机走 60~80 步"，这样打乱的结果保证能拼回来。
     */
    private void shuffleData() {
        resetData();                    // 先回到完成态
        Random rnd = new Random();      // 造一个随机数生成器
        int blank = IMG_COUNT - 1;      // 空格现在的位置（完成态在最后：下标 8）
        int lastMoved = -1;             // 记录上一次移动的数字，避免来回抖
        int steps = 60 + rnd.nextInt(21);
        // rnd.nextInt(21) = 随机 0~20，+60 后是 60~80。要走这么多步

        // 四个移动方向：{行变化, 列变化}
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // 二维数组，人话：上(行-1)、下(行+1)、左(列-1)、右(列+1)

        for (int i = 0; i < steps; i++) {   // 循环 steps 次，每次让空格走一格
            List<int[]> candidates = new ArrayList<>();
            // 造一个"候选位置"列表，用来装"空格这次能往哪走"
            int br = blank / COLS, bc = blank % COLS;
            // ★ 数组下标 → 网格坐标的换算：
            //   blank / 3 = 第几行（整除），blank % 3 = 第几列（取余）
            //   比如 blank=8：8/3=2（第2行），8%3=2（第2列）

            for (int[] d : dirs) {      // 遍历 4 个方向（"foreach"写法：每个方向 d）
                int nr = br + d[0], nc = bc + d[1];   // 目标位置的行和列
                if (nr < 0 || nr >= ROWS || nc < 0 || nc >= COLS) continue;
                // 检查目标位置有没有超出 3x3 网格，超了就跳过这个方向（continue = 跳过本次循环）
                int nIdx = nr * COLS + nc;      // 行列 → 数组下标（换算回来）
                if (nIdx == lastMoved) continue;   // 不往回走（防抖）
                candidates.add(new int[]{nIdx});   // 这个方向可行，加进候选列表
            }
            if (candidates.isEmpty()) { i--; continue; }
            // 一个方向都走不了（全被堵住）？这次不算数，重新来

            int pick = candidates.get(rnd.nextInt(candidates.size()))[0];
            // 嵌套拆解：rnd.nextInt(列表大小) 随机一个下标 → get(下标) 取出那个候选位置
            // 人话：从可行方向里随机挑一个

            // 交换：空格位置 ← 被选中位置上的数字；被选中位置 ← 0（变成新空格）
            data[blank] = data[pick];
            data[pick] = 0;
            lastMoved = blank;   // 记下"刚被移动的数字原来在哪"，防止下一步又把它移回去
            blank = pick;        // 空格挪到了新位置
        }
        stepCount = 0;                          // 步数清零
        if (stepLabel != null) stepLabel.setText("步数：0");
    }

    // =============================================================
    // 4. 渲染拼图界面
    // =============================================================
    /**
     * 根据 data 把 9 个 JLabel 摆到对应坐标并设图片。
     * 每次打乱/移动后调用。做法：先把旧的 9 个格子从界面上删掉，再重新放 9 个新的。
     * （简单粗暴，但逻辑稳）
     */
    private void paintPuzzle() {
        JPanel content = (JPanel) this.getContentPane();
        // (JPanel) 是强制类型转换：getContentPane() 返回的类型是 Container（大类型），
        // 我们知道它实际是个 JPanel，所以转一下才能调用 JPanel 的方法
        for (JLabel cell : cells) {      // 遍历之前存的 9 个格子
            if (cell != null) content.remove(cell);   // 从内容区删掉旧格子
        }
        Arrays.fill(cells, null);        // 把数组清空（全填 null）

        // 网格整体尺寸：宽 = 3x105 = 315，高 = 315
        int gridW = COLS * SIZE;
        int gridH = ROWS * SIZE;

        // 让网格在窗口里居中：水平居中；垂直方向要避开顶部条(40px)
        int panelW = this.getContentPane().getWidth();   // 内容区实际宽
        int panelH = this.getContentPane().getHeight();  // 内容区实际高
        if (panelW <= 0) panelW = 603;      // 保险：万一还没布局好拿到 0，用窗口尺寸兜底
        if (panelH <= 0) panelH = 680 - 75;
        int startX = (panelW - gridW) / 2;               // 网格左上角 X = 居中公式
        int startY = 40 + (panelH - 40 - gridH) / 2;     // Y：从顶部条下面开始算居中

        // ★ 核心循环：把 9 个位置一个一个画出来
        for (int i = 0; i < IMG_COUNT; i++) {   // i = 位置编号 0~8
            int imgNo = data[i];                // 这个位置上放的图片编号（0=空格）
            JLabel cell = new JLabel();         // 造一个新的格子
            // 算这个格子的坐标：第 i%3 列、第 i/3 行
            cell.setBounds(startX + (i % COLS) * SIZE,      // X = 起点 + 列号 x 105
                           startY + (i / COLS) * SIZE,      // Y = 起点 + 行号 x 105
                           SIZE, SIZE);                      // 宽高 105x105
            cell.setBorder(new BevelBorder(BevelBorder.LOWERED));
            // 造一个"凹下去"的立体边框（LOWERED = 凹，像槽位）
            cell.setOpaque(true);               // 允许格子有自己的背景色
            cell.setBackground(new Color(255, 248, 230));   // 浅米色底
            cell.putClientProperty("idx", i);
            // ★ 技巧：把"自己是第几个位置(i)"存进格子的口袋里
            //   之后点击事件拿到格子时，从口袋里掏出 i 就知道点的是哪格
            cell.addMouseListener(this);        // 格子挂鼠标监听（点击 → mouseClicked 方法）

            if (imgNo != 0) {                   // 这个位置有图片
                ImageIcon icon = icons[imgNo];  // 从图标数组取出对应图片
                int x = (SIZE - icon.getIconWidth()) / 2;   // 图片比格子小时，居中偏移
                int y = (SIZE - icon.getIconHeight()) / 2;
                cell.setIcon(icon);             // 把图片放进格子
                cell.setHorizontalAlignment(JLabel.CENTER); // 水平居中
                cell.setVerticalAlignment(JLabel.CENTER);   // 垂直居中
                // 微调：让格子的实际大小跟图片一样，图片正好嵌入
                cell.setBounds(cell.getX() + x, cell.getY() + y,
                               icon.getIconWidth(), icon.getIconHeight());
                cell.putClientProperty("hasIcon", Boolean.TRUE);   // 口袋里记"有图"
            } else {
                // 空格：颜色深一点表示"这里空着"
                cell.setBackground(new Color(230, 220, 200));
                cell.putClientProperty("hasIcon", Boolean.FALSE);  // 口袋里记"没图"
            }
            cells[i] = cell;        // 存进格子数组（下次重画时能找到它）
            content.add(cell);      // 放进内容区
        }
        content.revalidate();       // 让容器重新计算布局
        content.repaint();          // 重画一遍（不调的话界面可能不刷新）
    }

    // =============================================================
    // 5. 鼠标点击 → 移动图片
    // =============================================================
    // @Override = 告诉编译器"我在实现接口里声明过的方法"（写错了会报错，防手滑）
    @Override
    public void mouseClicked(MouseEvent e) {    // 鼠标点击时自动调用
        Object src = e.getSource();
        // e.getSource() = 这次点击是"谁"触发的（哪个组件）
        if (!(src instanceof JLabel)) return;
        // instanceof = "是不是某种类型"。人话：如果点击的不是格子(JLabel)，直接结束
        JLabel clicked = (JLabel) src;          // 转成 JLabel（强制转换）
        Integer idxObj = (Integer) clicked.getClientProperty("idx");
        // 嵌套拆解：从格子的口袋里掏出之前存的"位置编号"（Integer = int 的包装类型）
        if (idxObj == null) return;             // 口袋里没有编号？结束
        int idx = idxObj;                       // 包装类型 → 基本类型（自动拆箱）

        if (data[idx] == 0) return;             // 点的是空格本身？没意义，结束

        // 在数组里找空格现在的位置
        int blank = -1;                         // -1 = "还没找到"的哨兵值
        for (int i = 0; i < IMG_COUNT; i++) {
            if (data[i] == 0) { blank = i; break; }   // 找到就记下位置，break 跳出循环
        }
        if (blank == -1) return;                // 没找到空格（不可能），结束

        if (!isAdjacent(idx, blank)) return;    // 点的格子不在空格旁边？不能动，结束

        // 走到这里 = 合法移动：把点的格子和空格交换
        data[blank] = data[idx];    // 空格位置 ← 图片编号
        data[idx]   = 0;            // 原位置变成空格
        stepCount++;                // 步数 +1
        if (stepLabel != null) stepLabel.setText("步数：" + stepCount);   // 刷新步数显示

        paintPuzzle();              // 重新画界面

        if (isWin()) {              // 移动后检查：拼好了吗？
            showWinDialog();        // 拼好了 → 弹胜利窗口
        }
    }

    /** 判断两个格子下标在网格里是否上下左右相邻 */
    private boolean isAdjacent(int a, int b) {
        int ar = a / COLS, ac = a % COLS;   // a 的行列
        int br = b / COLS, bc = b % COLS;   // b 的行列
        // 相邻 = 同一行且列差 1，或同一列且行差 1
        return (ar == br && Math.abs(ac - bc) == 1)
            || (ac == bc && Math.abs(ar - br) == 1);
        // Math.abs = 取绝对值。&& = 并且，|| = 或者
    }

    /** 当前 data 是不是完成态 */
    private boolean isWin() {
        // 前 8 个位置必须依次是 1,2,3,4,5,6,7,8
        for (int i = 0; i < IMG_COUNT - 1; i++) {
            if (data[i] != i + 1) return false;   // 只要有一个不对，就不是完成态
        }
        return data[IMG_COUNT - 1] == 0;    // 最后一位必须是空格(0)
    }

    /** 弹胜利窗口 */
    private void showWinDialog() {
        JDialog dialog = new JDialog(this, "胜利！", true);
        // 造一个弹窗：父窗口是 this，标题"胜利！"，true = 模态（不关掉它不能点主窗口）
        dialog.setSize(280, 160);
        dialog.setLocationRelativeTo(this);     // 弹窗出现在主窗口中间
        dialog.setLayout(null);

        JLabel msg = new JLabel("恭喜通关！共用了 " + stepCount + " 步", JLabel.CENTER);
        // 字符串拼接：把数字 stepCount 拼进一句话里（+ 号连接）
        msg.setFont(new Font("微软雅黑", Font.BOLD, 16));
        msg.setBounds(10, 30, 260, 30);
        dialog.add(msg);

        JButton ok = new JButton("再来一局");
        ok.setBounds(90, 80, 100, 30);
        ok.addActionListener(ev -> {
            // 回调：点"再来一局" → 执行括号里这三句
            dialog.dispose();       // 关掉胜利弹窗
            shuffleData();          // 重新打乱
            paintPuzzle();          // 重画
        });
        dialog.add(ok);
        dialog.setVisible(true);    // 显示弹窗（模态窗口会在这里"卡住"，直到被关掉）
    }

    // =============================================================
    // 6. 完整图预览 + 作弊码
    // =============================================================
    /** 弹窗展示按正确顺序排好的 8 张图（预览拼好的样子） */
    private void showFullPreview() {
        JDialog dialog = new JDialog(this, "完整图预览", true);
        dialog.setSize(SIZE * COLS + 30, SIZE * ROWS + 60);   // 弹窗大小按网格算
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);

        JLabel info = new JLabel("这就是拼图完成的样子", JLabel.CENTER);
        info.setBounds(0, 5, dialog.getWidth(), 25);
        dialog.add(info);

        // 9 个格子的固定坐标（行,列）
        int[][] dirs = {{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};
        for (int n = 1; n <= 8; n++) {      // 图 1~8
            JLabel cell = new JLabel(icons[n]);   // 造格子并直接放上第 n 张图
            cell.setHorizontalAlignment(JLabel.CENTER);
            cell.setBorder(new BevelBorder(BevelBorder.LOWERED));
            int r = (n - 1) / COLS, c = (n - 1) % COLS;   // 第 n 张图放在第几行第几列
            cell.setBounds(15 + c * SIZE, 30 + r * SIZE, SIZE, SIZE);
            dialog.add(cell);
        }
        dialog.setVisible(true);
    }

    // 键盘按键时自动调用（实现 KeyListener 接口的方法）
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();   // 拿到按的是哪个键（VK_W = 字母 W 的编号）
        if (key == KeyEvent.VK_W) {
            // W 键：查看完整图
            showFullPreview();
        } else if (key == KeyEvent.VK_A) {
            // A 键：作弊，直接把数据改成完成态
            data[0] = 1; data[1] = 2; data[2] = 3;
            data[3] = 4; data[4] = 5; data[5] = 6;
            data[6] = 7; data[7] = 8; data[8] = 0;
            paintPuzzle();      // 重画成完成态
            showWinDialog();    // 弹胜利窗口
        }
    }

    // =============================================================
    // 7. 菜单事件：重新游戏 / 重新登入 / 关闭游戏 / 公众号
    // =============================================================
    // 点任何菜单项时自动调用（实现 ActionListener 接口的方法）
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();   // 拿到"点的是哪个菜单项"的代号
        switch (cmd) {       // switch = 按代号分派，执行对应的分支
            case CMD_REPLAY:                // 点"重新游戏"
                shuffleData();              // 重新打乱
                paintPuzzle();              // 重画
                break;                      // break = 这个分支做完，跳出 switch
            case CMD_RELOGIN:               // 点"重新登入"
                // 真正的"重新登入"：关掉游戏窗口，回到登录界面
                this.dispose();             // 销毁游戏窗口（dispose 不触发 EXIT_ON_CLOSE，程序不会退出）
                new LoginJFrame();          // 打开登录窗口
                break;
            case CMD_CLOSE:                 // 点"关闭游戏"
                System.exit(0);             // 结束整个程序（0 = 正常退出）
                break;
            case CMD_ACCOUNT:               // 点"公众号"
                showAccountDialog();        // 弹公众号窗口
                break;
        }
    }

    /** "公众号"项：弹个窗口展示感谢语（占位，避免菜单项是死的） */
    private void showAccountDialog() {
        JDialog dialog = new JDialog(this, "关注公众号", true);
        dialog.setSize(300, 220);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);

        // <html>...</html> 是让 JLabel 支持多行文字的小技巧
        JLabel msg = new JLabel("<html><div style='text-align:center;'>"
                + "感谢使用 SlideCraft<br/>"
                + "关注公众号【SlideCraft 开发笔记】<br/>"
                + "获取更多 Java / Go 学习内容"
                + "</div></html>", JLabel.CENTER);
        msg.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        msg.setBounds(10, 20, 280, 100);
        dialog.add(msg);

        JButton ok = new JButton("知道了");
        ok.setBounds(100, 140, 100, 30);
        ok.addActionListener(ev -> dialog.dispose());   // 回调：点按钮 → 关弹窗
        dialog.add(ok);
        dialog.setVisible(true);
    }

    // =============================================================
    // 其余鼠标/键盘事件：本项目用不到，空实现占位（接口要求的方法必须写出来）
    // =============================================================
    @Override public void mousePressed(MouseEvent e) {}    // 鼠标按下
    @Override public void mouseReleased(MouseEvent e) {}   // 鼠标松开
    @Override public void mouseEntered(MouseEvent e) {}    // 鼠标移进来
    @Override public void mouseExited(MouseEvent e) {}     // 鼠标移出去
    @Override public void keyTyped(KeyEvent e) {}          // 键盘打字（这里不用）
    @Override public void keyReleased(KeyEvent e) {}       // 键盘松开
}
