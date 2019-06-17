package com.bestboke.shell.tools;

import com.alibaba.fastjson.JSONArray;
import com.bestboke.shell.pojo.ServicesInfo;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Properties;

@Service
public class ShellMain {

    public JSONArray doShell(ServicesInfo servicesInfo) throws Exception{

        JSch jsch = new JSch();

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");

        Session session = jsch.getSession(servicesInfo.getUserName(), servicesInfo.getHost());
        session.setConfig(config);
        session.setPort(servicesInfo.getPort());
        session.setPassword(servicesInfo.getPasswd());
        session.connect();

        ChannelShell channel = (ChannelShell) session.openChannel("shell");
        MySshIOManager mySshIo = new MySshIOManager();
        mySshIo.loadCommands(servicesInfo.getCommand());

        channel.setInputStream(mySshIo.getInputStream());
        channel.setOutputStream(mySshIo.getOutputStream());
        InputStream is = channel.getExtInputStream();
        channel.setPtyType("MO80x25");
        channel.connect();

        while(!channel.isClosed()){
            Thread.sleep(1000);
        }
        System.out.println(mySshIo.getOut());
        channel.disconnect();
        session.disconnect();
        return mySshIo.getOut();

    }

}
