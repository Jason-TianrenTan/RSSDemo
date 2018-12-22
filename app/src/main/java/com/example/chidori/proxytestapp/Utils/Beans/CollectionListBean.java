package com.example.chidori.proxytestapp.Utils.Beans;

import java.util.List;

public class CollectionListBean {


    /**
     * resId : b5774d61-6100-41fd-a014-574c3c1255b3
     * reqId : null
     * reqType : null
     * resResult : {"curData":[{"collectionId":"02d71687-5415-406e-9c5d-7269327eb08c","name":"JustForTest","description":"Nothing to say","publicStatus":2,"userId":"061583de-12be-4116-ac58-e7343aa7f024","createTime":"12/21/18 6:27 PM","updateTime":"12/21/18 6:27 PM","remark":null},{"collectionId":"28084a85-6d10-4693-bd27-d031b2478bcf","name":"S-D-F","description":"Nothing to say","publicStatus":2,"userId":"061583de-12be-4116-ac58-e7343aa7f024","createTime":"12/21/18 8:09 PM","updateTime":"12/21/18 8:09 PM","remark":null},{"collectionId":"3fcd0876-aa3a-4e6b-be1b-c18ef4013374","name":"Testing","description":"Description","publicStatus":2,"userId":"061583de-12be-4116-ac58-e7343aa7f024","createTime":"12/22/18 2:50 PM","updateTime":"12/22/18 2:50 PM","remark":null},{"collectionId":"a6c04078-7da9-44e3-8817-0fb216cf830a","name":"test","description":"test","publicStatus":1,"userId":"061583de-12be-4116-ac58-e7343aa7f024","createTime":"12/21/18 7:31 PM","updateTime":"12/21/18 7:31 PM","remark":null}],"isSuccess":true,"message":"请求成功"}
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
         * curData : [{"collectionId":"02d71687-5415-406e-9c5d-7269327eb08c","name":"JustForTest","description":"Nothing to say","publicStatus":2,"userId":"061583de-12be-4116-ac58-e7343aa7f024","createTime":"12/21/18 6:27 PM","updateTime":"12/21/18 6:27 PM","remark":null},{"collectionId":"28084a85-6d10-4693-bd27-d031b2478bcf","name":"S-D-F","description":"Nothing to say","publicStatus":2,"userId":"061583de-12be-4116-ac58-e7343aa7f024","createTime":"12/21/18 8:09 PM","updateTime":"12/21/18 8:09 PM","remark":null},{"collectionId":"3fcd0876-aa3a-4e6b-be1b-c18ef4013374","name":"Testing","description":"Description","publicStatus":2,"userId":"061583de-12be-4116-ac58-e7343aa7f024","createTime":"12/22/18 2:50 PM","updateTime":"12/22/18 2:50 PM","remark":null},{"collectionId":"a6c04078-7da9-44e3-8817-0fb216cf830a","name":"test","description":"test","publicStatus":1,"userId":"061583de-12be-4116-ac58-e7343aa7f024","createTime":"12/21/18 7:31 PM","updateTime":"12/21/18 7:31 PM","remark":null}]
         * isSuccess : true
         * message : 请求成功
         */

        private boolean isSuccess;
        private String message;
        private List<CurDataBean> curData;

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

        public List<CurDataBean> getCurData() {
            return curData;
        }

        public void setCurData(List<CurDataBean> curData) {
            this.curData = curData;
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
