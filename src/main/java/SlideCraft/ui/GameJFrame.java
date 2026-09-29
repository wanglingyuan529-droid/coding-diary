package SlideCraft.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * 拼图游戏主窗口。
 *
 * 数据模型：int[9] data，1~8 是图片编号，0 代表空格。
 * 初始为完成态 data = {1,2,3,4,5,6,7,8,0}，
 * 通过"从完成态随机向空格滑动 N 步"的方式打乱，保证永远可解。
 *
 * 交互：
 *   - 鼠标点击非空格格子：若与空格相邻则交换；否则忽略。
 *   - 空格不绑定点击事件，避免歧义。
 *   - W 键：查看完整图片（弹窗预览按正确顺序排好的 9 张图）。
 *   - A 键：作弊码，直接恢复完成态。
 */
public class GameJFrame extends JFrame implements MouseListener, KeyListener, ActionListener {

    // ============ 常量 ============
    private static final int SIZE = 105;      // 每格像素
    private static final int COLS = 3;
    private static final int ROWS = 3;
    private static final int IMG_COUNT = ROWS * COLS;       // 9
    private static final String IMG_DIR =
            "C:\\study\\java\\coding-diary\\src\\main\\java\\SlideCraft\\异环娜娜莉\\images\\";

    // ============ 状态 ============
    /** 9 个格子的图片编号；data[i] 表示"位置 i 上的图片编号"，0 为空 */
    private final int[] data = new int[IMG_COUNT];
    /** 缓存已经加载的 9 张图片，按编号 1~9 索引（下标 1~8，0 留空给空格） */
    private final ImageIcon[] icons = new ImageIcon[IMG_COUNT + 1];
    /** 已经摆到界面上的 9 个 JLabel，按 data 下标对应位置 */
    private final JLabel[] cells = new JLabel[IMG_COUNT];
    /** 记录步数 */
    private int stepCount = 0;
    /** 步数显示标签 */
    private JLabel stepLabel;

    // 复用常量，避免到处写字面量
    private static final String CMD_REPLAY   = "replay";
    private static final String CMD_RELOGIN  = "relogin";
    private static final String CMD_CLOSE    = "close";
    private static final String CMD_ACCOUNT  = "account";

    public GameJFrame() {
        // 先把所有图片加载到内存（打乱后只换 data，不重读图）
        initImages();
        // 初始化数据为完成态 {1,2,3,4,5,6,7,8,0}
        resetData();
        // 装配窗口
        initJFrame();
        initJMenuBar();
        initTopBar();        // 顶部步数条
        paintPuzzle();       // 把 9 个 JLabel 摆到界面上
        this.setVisible(true);
    }

    // =============================================================
    // 1. 窗口与菜单初始化
    // =============================================================
    public void initJFrame() {
        this.setSize(603, 680);
        this.setTitle("拼图游戏 V1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        // 关掉一个窗口就退出整个程序
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // 注意：组件是加在 contentPane 上的，布局也要在 contentPane 上设置才生效
        this.getContentPane().setLayout(null);
        // 米黄色背景，更耐看
        this.getContentPane().setBackground(new Color(245, 238, 220));
        // 绑定键盘监听（W/A 等作弊键）
        this.addKeyListener(this);
    }

    public void initJMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu   = new JMenu("关于我们");

        JMenuItem replayItem  = new JMenuItem("重新游戏");
        JMenuItem reloginItem = new JMenuItem("重新登入");
        JMenuItem closeItem   = new JMenuItem("关闭游戏");
        JMenuItem accountItem = new JMenuItem("公众号");

        // 给菜单项绑定 ActionCommand，事件分发时区分是哪个按钮
        replayItem.setActionCommand(CMD_REPLAY);
        reloginItem.setActionCommand(CMD_RELOGIN);
        closeItem.setActionCommand(CMD_CLOSE);
        accountItem.setActionCommand(CMD_ACCOUNT);
        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        functionJMenu.add(replayItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);

        menuBar.add(functionJMenu);
        menuBar.add(aboutJMenu);
        this.setJMenuBar(menuBar);
    }

    /**
     * 顶部信息条：左侧标题，右侧步数。
     * 用一个 JPanel 当容器，再放到 contentPane 上。
     */
    private void initTopBar() {
        JPanel topBar = new JPanel();
        topBar.setLayout(null);
        topBar.setBounds(0, 0, 603, 40);
        topBar.setBackground(new Color(220, 200, 170));

        JLabel title = new JLabel("SlideCraft 拼图 · 异环娜娜莉");
        title.setFont(new Font("微软雅黑", Font.BOLD, 16));
        title.setBounds(10, 8, 300, 24);
        topBar.add(title);

        stepLabel = new JLabel("步数：0");
        stepLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        stepLabel.setBounds(470, 8, 120, 24);
        topBar.add(stepLabel);

        this.getContentPane().add(topBar);
    }

    // =============================================================
    // 2. 图片加载
    // =============================================================
    private void initImages() {
        for (int n = 1; n <= 8; n++) {  // 只加载 1~8，第 9 格是空白
            String path = IMG_DIR + String.format("异环娜娜莉_%02d.png", n);
            icons[n] = scaleIcon(new ImageIcon(path), SIZE, SIZE);
        }
        // 索引 0 留 null，对应"空格"
        icons[0] = null;
    }

    /**
     * 等比缩放到 targetW x targetH 之内（contain），不变形。
     */
    private ImageIcon scaleIcon(ImageIcon icon, int targetW, int targetH) {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        double ratio = Math.min(targetW / (double) w, targetH / (double) h);
        int newW = Math.max(1, (int) (w * ratio));
        int newH = Math.max(1, (int) (h * ratio));
        Image scaled = icon.getImage().getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    // =============================================================
    // 3. 数据与打乱
    // =============================================================
    /** 把 data 重置为完成态 */
    private void resetData() {
        for (int i = 0; i < IMG_COUNT; i++) {
            data[i] = (i == IMG_COUNT - 1) ? 0 : (i + 1);  // 最后一位是 0
        }
        stepCount = 0;
        if (stepLabel != null) stepLabel.setText("步数：0");
    }

    /**
     * 打乱图片。
     * 用"从完成态随机走 N 步"代替直接随机排列 —— 后者有 50% 概率生成无解局面（奇偶性问题）。
     * N 选 50~80 步，够乱又不会太慢。
     */
    private void shuffleData() {
        resetData();
        Random rnd = new Random();
        int blank = IMG_COUNT - 1;       // 空格当前位置（完成态在末尾）
        int lastMoved = -1;              // 记录上一步移动的数字，避免来回抖
        int steps = 60 + rnd.nextInt(21);
        // 上下左右 4 个方向
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < steps; i++) {
            List<int[]> candidates = new ArrayList<>();
            int br = blank / COLS, bc = blank % COLS;
            for (int[] d : dirs) {
                int nr = br + d[0], nc = bc + d[1];
                if (nr < 0 || nr >= ROWS || nc < 0 || nc >= COLS) continue;
                int nIdx = nr * COLS + nc;
                if (nIdx == lastMoved) continue;  // 防止撤销上一步
                candidates.add(new int[]{nIdx});
            }
            if (candidates.isEmpty()) { i--; continue; }
            int pick = candidates.get(rnd.nextInt(candidates.size()))[0];
            // 把空格和 pick 位置互换
            data[blank] = data[pick];
            data[pick] = 0;
            lastMoved = blank;
            blank = pick;
        }
        stepCount = 0;
        if (stepLabel != null) stepLabel.setText("步数：0");
    }

    // =============================================================
    // 4. 渲染拼图界面
    // =============================================================
    /**
     * 根据 data 把 9 个 JLabel 摆到对应坐标，并设图标。
     * 每次打乱或移动后调用一次（先清空再重画，简单粗暴但稳）。
     */
    private void paintPuzzle() {
        JPanel content = (JPanel) this.getContentPane();
        // 移除旧的 9 个格子（避免重复添加）
        for (JLabel cell : cells) {
            if (cell != null) content.remove(cell);
        }
        Arrays.fill(cells, null);

        // 网格整体尺寸
        int gridW = COLS * SIZE;
        int gridH = ROWS * SIZE;
        // 让网格在内容区水平居中；垂直方向预留顶部条 40px
        int panelW = this.getContentPane().getWidth();
        int panelH = this.getContentPane().getHeight();
        if (panelW <= 0) panelW = 603;
        if (panelH <= 0) panelH = 680 - 75;
        int startX = (panelW - gridW) / 2;
        int startY = 40 + (panelH - 40 - gridH) / 2;

        for (int i = 0; i < IMG_COUNT; i++) {
            int imgNo = data[i];   // 该位置上的图片编号，0 表示空
            JLabel cell = new JLabel();
            cell.setBounds(startX + (i % COLS) * SIZE,
                           startY + (i / COLS) * SIZE,
                           SIZE, SIZE);
            // 凹槽边框：让格子有"槽位"的感觉
            cell.setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
            cell.setOpaque(true);
            cell.setBackground(new Color(255, 248, 230));
            // 把"自己在 data 里的下标 i"存到 label 的 clientProperty 里，
            // 点击事件里就能直接知道点的是哪个位置 —— 比用组件名字或遍历位置更靠谱。
            cell.putClientProperty("idx", i);
            cell.addMouseListener(this);

            if (imgNo != 0) {
                // 缩放后的图可能小于格子，居中
                ImageIcon icon = icons[imgNo];
                int x = (SIZE - icon.getIconWidth()) / 2;
                int y = (SIZE - icon.getIconHeight()) / 2;
                cell.setIcon(icon);
                cell.setHorizontalAlignment(JLabel.CENTER);
                cell.setVerticalAlignment(JLabel.CENTER);
                // 微调内边距让图正好嵌入
                cell.setBounds(cell.getX() + x, cell.getY() + y,
                               icon.getIconWidth(), icon.getIconHeight());
                cell.putClientProperty("hasIcon", Boolean.TRUE);
            } else {
                // 空格：浅色底表示"这里空着"
                cell.setBackground(new Color(230, 220, 200));
                cell.putClientProperty("hasIcon", Boolean.FALSE);
            }
            cells[i] = cell;
            content.add(cell);
        }
        // 重画
        content.revalidate();
        content.repaint();
    }

    // =============================================================
    // 5. 鼠标点击 → 移动图片
    // =============================================================
    @Override
    public void mouseClicked(MouseEvent e) {
        // 找到点中的 JLabel
        Object src = e.getSource();
        if (!(src instanceof JLabel)) return;
        JLabel clicked = (JLabel) src;
        Integer idxObj = (Integer) clicked.getClientProperty("idx");
        if (idxObj == null) return;
        int idx = idxObj;
        // 空格本身被点无效
        if (data[idx] == 0) return;
        // 找空格位置
        int blank = -1;
        for (int i = 0; i < IMG_COUNT; i++) {
            if (data[i] == 0) { blank = i; break; }
        }
        if (blank == -1) return;

        // 只允许"上下左右紧邻空格"的格子被移动
        if (!isAdjacent(idx, blank)) return;

        // 交换
        data[blank] = data[idx];
        data[idx]   = 0;
        stepCount++;
        if (stepLabel != null) stepLabel.setText("步数：" + stepCount);

        paintPuzzle();

        // 移动后判定是否胜利
        if (isWin()) {
            showWinDialog();
        }
    }

    /** 判断两个格子下标是否在网格中相邻（上下左右） */
    private boolean isAdjacent(int a, int b) {
        int ar = a / COLS, ac = a % COLS;
        int br = b / COLS, bc = b % COLS;
        return (ar == br && Math.abs(ac - bc) == 1)
            || (ac == bc && Math.abs(ar - br) == 1);
    }

    /** 当前 data 是否为完成态 */
    private boolean isWin() {
        for (int i = 0; i < IMG_COUNT - 1; i++) {
            if (data[i] != i + 1) return false;
        }
        return data[IMG_COUNT - 1] == 0;
    }

    private void showWinDialog() {
        JDialog dialog = new JDialog(this, "胜利！", true);
        dialog.setSize(280, 160);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);

        JLabel msg = new JLabel("恭喜通关！共用了 " + stepCount + " 步", JLabel.CENTER);
        msg.setFont(new Font("微软雅黑", Font.BOLD, 16));
        msg.setBounds(10, 30, 260, 30);
        dialog.add(msg);

        JButton ok = new JButton("再来一局");
        ok.setBounds(90, 80, 100, 30);
        ok.addActionListener(ev -> {
            dialog.dispose();
            shuffleData();
            paintPuzzle();
        });
        dialog.add(ok);
        dialog.setVisible(true);
    }

    // =============================================================
    // 6. 完整图预览 + 作弊码
    // =============================================================
    /** 弹窗展示按正确顺序排好的 9 张图（预览效果） */
    private void showFullPreview() {
        JDialog dialog = new JDialog(this, "完整图预览", true);
        dialog.setSize(SIZE * COLS + 30, SIZE * ROWS + 60);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);

        JLabel info = new JLabel("这就是拼图完成的样子", JLabel.CENTER);
        info.setBounds(0, 5, dialog.getWidth(), 25);
        dialog.add(info);

        for (int n = 1; n <= 8; n++) {
            JLabel cell = new JLabel(icons[n]);
            cell.setHorizontalAlignment(JLabel.CENTER);
            cell.setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
            int r = (n - 1) / COLS, c = (n - 1) % COLS;
            cell.setBounds(15 + c * SIZE, 30 + r * SIZE, SIZE, SIZE);
            dialog.add(cell);
        }
        dialog.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            // W：查看完整图
            showFullPreview();
        } else if (key == KeyEvent.VK_A) {
            // A：作弊直接通关
            data[0] = 1; data[1] = 2; data[2] = 3;
            data[3] = 4; data[4] = 5; data[5] = 6;
            data[6] = 7; data[7] = 8; data[8] = 0;
            paintPuzzle();
            showWinDialog();
        }
    }

    // =============================================================
    // 7. 菜单事件：重新游戏 / 重新登入 / 关闭游戏 / 公众号
    // =============================================================
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case CMD_REPLAY:
                shuffleData();
                paintPuzzle();
                break;
            case CMD_RELOGIN:
                // 练习版：不真的做登录页，直接重置游戏即可
                shuffleData();
                paintPuzzle();
                break;
            case CMD_CLOSE:
                System.exit(0);
                break;
            case CMD_ACCOUNT:
                showAccountDialog();
                break;
        }
    }

    /** "公众号"项：弹个静态图片占位，避免选项是哑的 */
    private void showAccountDialog() {
        JDialog dialog = new JDialog(this, "关注公众号", true);
        dialog.setSize(300, 220);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);

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
        ok.addActionListener(ev -> dialog.dispose());
        dialog.add(ok);
        dialog.setVisible(true);
    }

    // =============================================================
    // 其余鼠标/键盘事件：本项目只用 pressed/keyPressed，其他空实现即可
    // =============================================================
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
