package com.bestboke.shell.constant;

import com.bestboke.shell.pojo.ServicesInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddConstant {

    public List<ServicesInfo> getInfo(String ip){
        List<ServicesInfo> servicesInfos = new ArrayList<>();
        String command1 = "[$]send[su -];[Password:]password[passwd];[#]out[sh /root/nginx_ipblock_ctrl.sh -a "+ ip +"];[#]send[exit];[$]send[exit]";
        servicesInfos.add(new ServicesInfo("lfs", "vmuser", "wsxqazedc", command1));
        return servicesInfos;
    }

}
