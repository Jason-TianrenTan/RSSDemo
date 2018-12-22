package com.example.chidori.proxytestapp.Utils.Beans;

public class UpdateCollectionBean {

    /**
     * resId : ee2eb446-4e0d-4bef-a009-1970f58621fe
     * reqId : null
     * reqType : null
     * resResult : {"curData":{"name":"Update name test","description":"another test","publicStatus":2,"collectionId":"28084a85-6d10-4693-bd27-d031b2478bcf"},"isSuccess":true,"message":"请求成功"}
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
         * curData : {"name":"Update name test","description":"another test","publicStatus":2,"collectionId":"28084a85-6d10-4693-bd27-d031b2478bcf"}
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
             * name : Update name test
             * description : another test
             * publicStatus : 2
             * collectionId : 28084a85-6d10-4693-bd27-d031b2478bcf
             */

            private String name;
            private String description;
            private int publicStatus;
            private String collectionId;

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

            public String getCollectionId() {
                return collectionId;
            }

            public void setCollectionId(String collectionId) {
                this.collectionId = collectionId;
            }
        }
    }
}
