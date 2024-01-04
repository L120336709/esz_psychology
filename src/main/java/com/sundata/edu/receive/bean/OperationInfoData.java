package com.sundata.edu.receive.bean;

public class OperationInfoData<T> {
    public int operationType;
    public T data;
    private Long messageId;
    public OperationInfoData() {
    }
    public Long getMessageId() {
        return this.messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public int getOperationType() {
        return this.operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
