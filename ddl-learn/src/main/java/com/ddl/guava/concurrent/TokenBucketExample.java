package com.ddl.guava.concurrent;


public class TokenBucketExample {

    public static void main(String[] args) {
        final TokenBucket tokenBucket = new TokenBucket();
        for (int i = 0; i < 500; i++) {
            new Thread(tokenBucket::buy)
                    .start();
        }
    }
}
