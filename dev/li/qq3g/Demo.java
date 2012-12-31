package li.qq3g;

public class Demo {
    static QQ3G qq3g = new QQ3G();

    // static String sid = "Aecis1w3y-qVlvrp3IamYi8p";// 1055515958

    static String sid = "AQWj965jLyk49Pnuhukopwks";// 416133823

    // 1406678816
    public static void main(String[] args) throws Exception {
        // System.out.println(qq3g.login("416133823", "buxiaode"));
        // System.out.println(qq3g.refresh(sid, "416133823"));
        // System.out.println(qq3g.send(sid, "你好，这是一条测试消息，来自 黎明伟-3GQQ-JAVA", "416133823"));

        // sendMsgsToQQ(sid, "1406678816", new String[] { "床前明月光", "疑是地上霜", "举头望明月", "低头思故乡" });

        // System.out.println(qq3g.online(sid));
        // System.out.println(qq3g.groups(sid));

        // System.out.println(qq3g.group(sid, "我会的", "4", "4", "99"));
    }

    public static void sendMsgToQQs(String msg, String[] qqs) throws Exception {
        for (String qq : qqs) {
            // System.out.println(qq3g.send(sid, "你好,这是一条测试消息,收到请回答,来自 黎明伟-3GQQ-JAVA", qq));
            System.out.println(qq3g.send(sid, msg, qq));
            Thread.sleep(1000);
        }
    }

    public static void sendMsgsToQQ(String sid, String to, String[] msgs) throws Exception {
        for (String msg : msgs) {
            System.out.println(qq3g.send(sid, msg, to));
            Thread.sleep(1000);
        }
    }
}