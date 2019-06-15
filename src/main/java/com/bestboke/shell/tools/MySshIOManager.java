package com.bestboke.shell.tools;


import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main entry of this POC.
 * <p>
 * The whole idea is:
 * <p/>
 * Make our own input-stream and output-stream. And manage them by this class.
 * MyOutputStream will read the response from SSH shell channel, if found an
 * 'expected' response, it will notify the MyInputStream through the
 * MySshIoManager, then the SSH shell channel can read from MyInputStream. And
 * MyInputStream will handle the "password" input part.
 *
 * @author: clarioneswang
 * @version: 1.0, Dec 31, 2012
 */
public class MySshIOManager {
    private MyInputStream mInputStream;
    private MyOutputStream mOutputStream;
    private List<MyCommand> mCommands = new LinkedList<MyCommand>();
    private int mCmdIdx;
    private boolean canSend;


    public InputStream getInputStream() {
        return mInputStream;
    }


    public OutputStream getOutputStream() {
        return mOutputStream;
    }


    // Load the command from file.
    public void loadCommands(String pIns) {
        mInputStream = new MyInputStream();
        mOutputStream = new MyOutputStream();
        mInputStream.setManager(this);
        mOutputStream.setManager(this);
        mCmdIdx = 0;
        canSend = false;

        String [] cmds = pIns.split(";");
        String line;
        for(int i = 0; i < cmds.length; i ++){
            line = cmds[i].trim();
            if(line.isEmpty()){
                continue;
            }
            addCommandLine(line);
        }
        mCmdIdx = 0;
    }

    static Pattern cmdptn = Pattern.compile("\\[([^\\s\\]]*)\\]([^\\s\\[]*)\\[([^\\]]*)\\]");


    private void addCommandLine(String pLine) {
        Matcher m = cmdptn.matcher(pLine);
        if (m.find()) {
            System.out.println("load command: " + pLine);
            addCommand(m.group(2), m.group(1), m.group(3));
        }
    }


    private void addCommand(String pCmd, String pEnds, String pContent) {
        mCommands.add(new MyCommand(pCmd, pEnds, pContent, mCmdIdx));
        mCmdIdx++;
    }


    public MyCommand getNextCommand() {
        if (mCmdIdx >= mCommands.size()) {
            return null;
        }
        synchronized (mCommands) {
            try {
                mCommands.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return mCommands.get(mCmdIdx++);
        }
    }


    public MyCommand getCommand() {
        if (mCmdIdx >= mCommands.size()) {
            return null;
        }
        synchronized (mCommands) {
            return mCommands.get(mCmdIdx);
        }
    }


    public void enableSend() {
        synchronized (mCommands) {
            mCommands.notify();
        }
    }
}
