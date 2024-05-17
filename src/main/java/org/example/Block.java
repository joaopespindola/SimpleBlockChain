package org.example;
import java.util.Date;
import java.security.MessageDigest;

public class Block {

    public String hash;
    public String prevHash;
    private String data;
    private long timeStamp;

    public Block(String data, long timeStamp, String prevHash) {
        this.data = data;
        this.timeStamp = timeStamp;
        this.prevHash = prevHash;
    }

    public String calculateHash() {
        String calculatedHash = StringUtil.applySha256(
                prevHash + Long.toString(timeStamp) + data
        );
        return calculatedHash;
    }
}