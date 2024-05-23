package org.block;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class SimpleBlockChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int DIFFICULTY_VAR = 1;

    public static void main(String[] args) {
        blockchain.add(new Block("First Block", "0"));
        blockchain.get(0).mineBlock(DIFFICULTY_VAR);
        System.out.println("Trying mine block 1");
        blockchain.add(new Block("Second Block", blockchain.get(blockchain.size()-1).getHash()));
        blockchain.get(1).mineBlock(DIFFICULTY_VAR);
        System.out.println("Trying mine block 2");
        blockchain.add(new Block("Third Block", blockchain.get(blockchain.size()-1).getHash()));
        blockchain.get(2).mineBlock(DIFFICULTY_VAR);
        System.out.println("Trying mine block 3...");

        System.out.println("Blockhain valid? " + isChainValid() + "\n");

        System.out.println("-------THE BLOCKCHAIN-------");
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);

    }

    //Method to compare if the block is valid
    public static Boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[DIFFICULTY_VAR]).replace('\0', '0');

        for(int i = 1; i < blockchain.size(); i++){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            if(!currentBlock.getHash().equals(currentBlock.calculateHash())){
                System.out.println("Hash does not match");
                return false;
            }
            if(!previousBlock.getHash().equals(currentBlock.getPrevHash())){
                System.out.println("Previous hash does not match");
                return false;
            }
            if(!currentBlock.getHash().substring(0, DIFFICULTY_VAR).equals(hashTarget)){
                System.out.println("Block wasn't been mined");
                return false;
            }
        }
        return true;
    }
}
