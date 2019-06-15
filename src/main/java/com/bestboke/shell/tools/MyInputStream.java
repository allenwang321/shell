package com.bestboke.shell.tools;

import java.io.*;

public class MyInputStream extends InputStream {

    private MySshIOManager mManager;

    @Override
    public int read(byte[] pB, int pOff, int pLen) {
        try {
            MyCommand command = mManager.getNextCommand();
            // If all commands are executed, return -1 to disconnect
            String cmd;
            if (command == null) {
                return -1;
            }

            //execute the command
            if (command.isSend()) {
                // If it's a 'send', then read from list
                cmd = command.getContent() + "\n";
            } else {
                // If it's a 'password', need read from console
                Console cs = System.console();
                if (cs != null) {
                    cmd = new String(cs.readPassword()) + "\n";
                } else {
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    cmd = br.readLine() + "\n";
                }
            }

            // send the data to SSH channel
            byte[] result = cmd.getBytes();
            System.arraycopy(result, 0, pB, pOff, result.length);
            return result.length;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    public void setManager(MySshIOManager pMySshIO) {
        mManager = pMySshIO;
    }


    @Override
    public int read() throws IOException {
        // This function is not needed for this POC. Just return 0.
        return 0;
    }

}
