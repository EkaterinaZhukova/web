package model;

public class CommandURL {
    public static final String home = "/servlet?command=home";
    public static final String blockList = "/servlet?command=blockList";
    public static final String unblockList = "/servlet?command=unblockList";
    public static final String registration = "/servlet?command=registration";
    public static final String logout = "/";
    public static final String services = "";

    public static String pay (String name, String surname) {
        return "/servlet?command=yourServices&name="+name+"&"+"surname="+surname;
    }

}
