package com.bestboke.shell.pojo;

/**
 * 服务器信息实体类
 */
public class ServicesInfo {

    private String host;
    private String userName;
    private String passwd;
    private String command;
    private Integer port;

    public ServicesInfo(String host, String userName, String passwd, String command, Integer port) {
        this.host = host;
        this.userName = userName;
        this.passwd = passwd;
        this.command = command;
        this.port = port;
    }

    public ServicesInfo(String host, String userName, String passwd, String command) {
        this.host = host;
        this.userName = userName;
        this.passwd = passwd;
        this.command = command;
        this.port = 22;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
