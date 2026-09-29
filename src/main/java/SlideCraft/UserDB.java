package SlideCraft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 极简用户存储（练习版，不做加密，不做数据库）。
 *
 * 账号保存在项目根目录的 users.properties 文件里：
 *   - 每行一个账号：用户名=密码
 *   - 注册成功即写入文件，重启程序后依然有效
 * 预置了一个演示账号：admin / 123456
 */
public class UserDB {

    /** 账号文件（相对运行时工作目录，一般在项目根目录） */
    private static final File FILE = new File("users.properties");

    private static final Properties USERS = new Properties();

    static {
        // 启动时读取已有的账号文件
        if (FILE.exists()) {
            try (InputStream in = new FileInputStream(FILE)) {
                USERS.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 预置演示账号（仅当文件里没有 admin 时才写入，避免覆盖用户改过的密码）
        if (!USERS.containsKey("admin")) {
            USERS.setProperty("admin", "123456");
            save();
        }
    }

    private static void save() {
        try (OutputStream out = new FileOutputStream(FILE)) {
            USERS.store(out, "SlideCraft 用户数据（练习项目，明文存储）");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册：用户名不能为空、不能重复。
     * @return true 注册成功
     */
    public static boolean register(String name, String pwd) {
        if (name == null || name.isBlank()) return false;
        if (pwd == null || pwd.isBlank()) return false;
        if (USERS.containsKey(name)) return false;
        USERS.setProperty(name, pwd);
        save();
        return true;
    }

    /**
     * 登录校验。
     * @return true 用户名存在且密码正确
     */
    public static boolean login(String name, String pwd) {
        if (name == null || pwd == null) return false;
        return pwd.equals(USERS.getProperty(name));
    }
}
