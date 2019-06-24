package com.github.webshop.util;

import com.github.webshop.conf.ReleaseUriConfig;
import com.github.webshop.pojo.PrdtImage;
import com.github.webshop.pojo.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyUtil {
    /**
     * 最大线程数
     */
    public static Integer MAXTHREADS = 10;

    /**
     * 获取时间戳
     *
     * @return
     */
    public static String getDateId() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获得session中用户id
     */
    public static Integer getUserId(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        return user.getUserId();
    }

    /**
     * 获取格式化的时间字符串
     *
     * @return
     */
    public static String getFormatDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 子串查询
     *
     * @param fileName
     * @param regex
     * @return
     */
    public static boolean isIndexImg(String fileName, String regex) {
        if (fileName.contains(regex)) {
            return true;
        }
        return false;
    }

    /**
     * 获取UUID唯一识别码
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 判断操作是否成功，返回相依的信息
     *
     * @param flag
     * @return
     */
    public static Map successOrFailed(int flag) {
        if (flag > 0) {
            return HashMapUtil.getFormatMap("success");
        } else {
            return HashMapUtil.getFormatMap("failed");
        }
    }

    /**
     * 删除list中记录路径下的实际文件
     * 用定长线程池来处理
     *
     * @param list
     */
    public static void delUploadFile(List list) {
        ExecutorService threadPool = MyUtil.getFixedThreadPool(MyUtil.MAXTHREADS);
        if (list != null) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < list.size(); i++) {
                        PrdtImage prdtImage = (PrdtImage) list.get(i);
                        File file = new File(prdtImage.getImgUrl());
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                }
            });
        }
    }

    /**
     * 创建并返回一个定长线程池
     *
     * @param maxThreads
     * @return
     */
    public static ExecutorService getFixedThreadPool(Integer maxThreads) {
        return Executors.newFixedThreadPool(maxThreads);
    }

    /**
     * 检查用户是否记住登陆，有则登陆
     *
     * @param request
     * @return
     */
    public static boolean loginFromCookie(HttpServletRequest request) {

        Cookie cookie = CookieUtil.cookie_getOne(request, ReleaseUriConfig.FLAG_KEEPING_LOGIN);

        if (cookie != null) {
            String[] parm_cookie = CookieUtil.cookie_changeToStringArray(cookie);
            HttpSession session = request.getSession();
            session.setAttribute("currentUserId", parm_cookie[0]);
            session.setAttribute("currentUserName", parm_cookie[1]);
            session.setAttribute("currentUserEmail", parm_cookie[2]);
            return true;
        }
        return false;
    }
}
