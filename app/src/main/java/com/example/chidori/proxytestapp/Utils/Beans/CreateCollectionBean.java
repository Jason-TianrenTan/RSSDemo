package com.example.chidori.proxytestapp.Utils.Beans;

public class CreateCollectionBean {

    /**
     * resId : 38dc5dbf-5271-4834-9d20-a3966f7c8b7b
     * reqId : null
     * reqType : null
     * resResult : {"curData":{"collectionId":"02d71687-5415-406e-9c5d-7269327eb08c","name":"JustForTest","description":"Nothing to say","publicStatus":2,"userId":"061583de-12be-4116-ac58-e7343aa7f024","createTime":"12/21/18 6:27 PM","updateTime":"12/21/18 6:27 PM","remark":null},"isSuccess":true,"message":"请求成功"}
     * resRefData : null
     */

    private String resId;
    private Object reqId;
    private Object reqType;
    private ResResultBean resResult;
    private Object resRefData;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public Object getReqId() {
        return reqId;
    }

    public void setReqId(Object reqId) {
        this.reqId = reqId;
    }

    public Object getReqType() {
        return reqType;
    }

    public void setReqType(Object reqType) {
        this.reqType = reqType;
    }

    public ResResultBean getResResult() {
        return resResult;
    }

    public void setResResult(ResResultBean resResult) {
        this.resResult = resResult;
    }

    public Object getResRefData() {
        return resRefData;
    }

    public void setResRefData(Object resRefData) {
        this.resRefData = resRefData;
    }

    public static class ResResultBean {
        /**
         * curData : {"collectionId":"02d71687-5415-406e-9c5d-7269327eb08c","name":"JustForTest","description":"Nothing to say","publicStatus":2,"userId":"061583de-12be-4116-ac58-e7343aa7f024","createTime":"12/21/18 6:27 PM","updateTime":"12/21/18 6:27 PM","remark":null}
         * isSuccess : true
         * message : 请求成功
         */

        private CurDataBean curData;
        private boolean isSuccess;
        private String message;

        public CurDataBean getCurData() {
            return curData;
        }

        public void setCurData(CurDataBean curData) {
            this.curData = curData;
        }

        public boolean isIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public static class CurDataBean {
            /**
             * collectionId : 02d71687-5415-406e-9c5d-7269327eb08c
             * name : JustForTest
             * description : Nothing to say
             * publicStatus : 2
             * userId : 061583de-12be-4116-ac58-e7343aa7f024
             * createTime : 12/21/18 6:27 PM
             * updateTime : 12/21/18 6:27 PM
             * remark : null
             */

            private String collectionId;
            private String name;
            private String description;
            private int publicStatus;
            private String userId;
            private String createTime;
            private String updateTime;
            private Object remark;

            public String getCollectionId() {
                return collectionId;
            }

            public void setCollectionId(String collectionId) {
                this.collectionId = collectionId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getPublicStatus() {
                return publicStatus;
            }

            public void setPublicStatus(int publicStatus) {
                this.publicStatus = publicStatus;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }
        }
    }
}
