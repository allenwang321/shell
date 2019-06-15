package com.bestboke.shell.tools;

import java.io.OutputStream;

public class MyOutputStream extends OutputStream {

    private StringBuffer sb = new StringBuffer();
    private MySshIOManager mManager;


    @Override
    public void write(int pB) {
        try {
            // Each time has respond character(s), need check if it's the
            // expected content for next command.
            MyCommand command = mManager.getCommand();
            sb.append(Character.toChars(pB));

            // If all command were executed, then just show the respond
            // character(s) in the system.out
            if (command == null) {
                System.out.print(sb.toString());
                sb.delete(0, sb.length());
                return;
            }

            // To clear the screen, this time I just print the response only when next command can execute.
            // You can change the code as your wish. For example, once get a character, just show it.
            if (sb.toString().trim().endsWith(command.getEnds())) {
                System.out.print(sb.toString());
                sb.delete(0, sb.length());

                // Once a 'expected' response found, notify the manager to let the "inputStream' to work.
                mManager.enableSend();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setManager(MySshIOManager pMySshIO) {
        mManager = pMySshIO;
    }

}
