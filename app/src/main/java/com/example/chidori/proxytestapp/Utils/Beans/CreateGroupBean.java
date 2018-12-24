package com.example.chidori.proxytestapp.Utils.Beans;

public class CreateGroupBean {

    /**
     * resId : db58ddd6-073e-4e4b-826a-be97bd28f29d
     * reqId : null
     * reqType : null
     * resResult : {"curData":{"groupId":"03eda6fc-10d3-435d-9a82-bc1fd7a8714f","name":"group for Android","groupNum":"5b349518","description":"AndroidTest","userId":"061583de-12be-4116-ac58-e7343aa7f024","createTime":"12/24/18 12:19 PM","updateTime":"12/24/18 12:19 PM","remark":null},"isSuccess":true,"message":"请求成功"}
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
         * curData : {"groupId":"03eda6fc-10d3-435d-9a82-bc1fd7a8714f","name":"group for Android","groupNum":"5b349518","description":"AndroidTest","userId":"061583de-12be-4116-ac58-e7343aa7f024","createTime":"12/24/18 12:19 PM","updateTime":"12/24/18 12:19 PM","remark":null}
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
             * groupId : 03eda6fc-10d3-435d-9a82-bc1fd7a8714f
             * name : group for Android
             * groupNum : 5b349518
             * description : AndroidTest
             * userId : 061583de-12be-4116-ac58-e7343aa7f024
             * createTime : 12/24/18 12:19 PM
             * updateTime : 12/24/18 12:19 PM
             * remark : null
             */

            private String groupId;
            private String name;
            private String groupNum;
            private String description;
            private String userId;
            private String createTime;
            private String updateTime;
            private Object remark;

            public String getGroupId() {
                return groupId;
            }

            public void setGroupId(String groupId) {
                this.groupId = groupId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getGroupNum() {
                return groupNum;
            }

            public void setGroupNum(String groupNum) {
                this.groupNum = groupNum;
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
