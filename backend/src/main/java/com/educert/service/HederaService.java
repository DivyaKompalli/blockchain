package com.educert.service;

import com.hedera.hashgraph.sdk.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class HederaService {

    @Value("${hedera.accountId}")
    private String accountId;

    @Value("${hedera.privateKey}")
    private String privateKey;

    private Client client;
    private TopicId mainTopicId;

    @PostConstruct
    public void init() {
        try {
            if ("YOUR_ACCOUNT_ID".equals(accountId)) {
                System.out.println("Warning: Hedera keys not configured. Blockchain features will fail until keys are set in application.properties.");
                return;
            }
            client = Client.forTestnet();
            client.setOperator(AccountId.fromString(accountId), PrivateKey.fromString(privateKey));
            
            TopicCreateTransaction transaction = new TopicCreateTransaction();
            TransactionResponse response = transaction.execute(client);
            TransactionReceipt receipt = response.getReceipt(client);
            mainTopicId = receipt.topicId;
            System.out.println("Created Main EduCert Hedera Topic: " + mainTopicId);
            
        } catch (Exception e) {
            System.err.println("Failed to initialize Hedera Client: " + e.getMessage());
        }
    }

    public String storeHash(String hash) throws Exception {
        if (client == null || mainTopicId == null) {
             throw new RuntimeException("Hedera Client is not initialized.");
        }
        
        TopicMessageSubmitTransaction submitTransaction = new TopicMessageSubmitTransaction()
            .setTopicId(mainTopicId)
            .setMessage(hash);

        TransactionResponse response = submitTransaction.execute(client);
        
        return mainTopicId.toString() + "@" + response.transactionId.toString();
    }
}
