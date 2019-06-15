package com.bestboke.shell.tools;

public class MyCommand {

    private String mCmd;
    private String mEnds;
    private String mContent;
    private int mIdx;

    public MyCommand(String pCmd, String pEnds, String pContent, int pCmdIdx) {
        mCmd = pCmd;
        mEnds = pEnds;
        mContent = pContent;
        setIdx(pCmdIdx);
    }

    public String getCmd() {
        return mCmd;
    }

    public void setCmd(String pCmd) {
        mCmd = pCmd;
    }

    public String getEnds() {
        return mEnds;
    }

    public void setEnds(String pEnds) {
        mEnds = pEnds;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String pContent) {
        mContent = pContent;
    }


    public boolean isSend() {
        return !mCmd.equalsIgnoreCase("Password:");
    }

    public int getIdx() {
        return mIdx;
    }

    public void setIdx(int idx) {
        mIdx = idx;
    }

    public String toString() {
        return mIdx + ":[" + mEnds + "]" + mCmd + ":" + mContent;
    }
}
