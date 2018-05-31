package TezaParser;

public class setter {
    private static int id_send;
    private static String name;
    private static String url;
    private static int p_id;

    public static int getCompare() {
        return compare;
    }

    public static void setCompare(int compare) {
        setter.compare = compare;
    }

    private static int compare;
    private static String tag;
    public static void ReturnId(int id){
        setter.id_send = id;
    }
    public static void ReturnName(String name){
        setter.name = name;
    }
    public static void ReturnUrl(String url){
        setter.url = url;
    }

    public static String getName() {
        return name;
    }

    public static String getUrl() {
        return url;
    }

    public static int getId_send() {
        return id_send;
    }

    public static int getP_id() {
        return p_id;
    }

    public static void setP_id(int p_id) {
        setter.p_id = p_id;
    }

    public static String getTag() {
        return tag;
    }

    public static void setTag(String tag) {
        setter.tag = tag;
    }
}
