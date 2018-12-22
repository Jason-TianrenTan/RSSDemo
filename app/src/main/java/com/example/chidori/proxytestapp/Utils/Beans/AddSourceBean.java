package com.example.chidori.proxytestapp.Utils.Beans;

public class AddSourceBean {

    /**
     * resId : a346ec1e-f11a-437b-b638-f9539cf4b243
     * reqId : null
     * reqType : null
     * resResult : {"curData":{"sourceId":"6ef9bbab-398d-4633-9d5a-505eca502964","name":"test","description":"testdesc","userId":"061583de-12be-4116-ac58-e7343aa7f024","link":"https://www.iteye.com/rss/news","file":null,"type":1,"preset":null,"createTime":"12/22/18 3:44 PM","updateTime":"12/22/18 3:44 PM","remark":null},"isSuccess":true,"message":"请求成功"}
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
         * curData : {"sourceId":"6ef9bbab-398d-4633-9d5a-505eca502964","name":"test","description":"testdesc","userId":"061583de-12be-4116-ac58-e7343aa7f024","link":"https://www.iteye.com/rss/news","file":null,"type":1,"preset":null,"createTime":"12/22/18 3:44 PM","updateTime":"12/22/18 3:44 PM","remark":null}
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
             * sourceId : 6ef9bbab-398d-4633-9d5a-505eca502964
             * name : test
             * description : testdesc
             * userId : 061583de-12be-4116-ac58-e7343aa7f024
             * link : https://www.iteye.com/rss/news
             * file : null
             * type : 1
             * preset : null
             * createTime : 12/22/18 3:44 PM
             * updateTime : 12/22/18 3:44 PM
             * remark : null
             */

            private String sourceId;
            private String name;
            private String description;
            private String userId;
            private String link;
            private Object file;
            private int type;
            private Object preset;
            private String createTime;
            private String updateTime;
            private Object remark;

            public String getSourceId() {
                return sourceId;
            }

            public void setSourceId(String sourceId) {
                this.sourceId = sourceId;
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

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public Object getFile() {
                return file;
            }

            public void setFile(Object file) {
                this.file = file;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public Object getPreset() {
                return preset;
            }

            public void setPreset(Object preset) {
                this.preset = preset;
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
