package com.example.chidori.proxytestapp.Utils.Beans;

public class SaveEntryBean {


    /**
     * resId : 4deb1233-3b9f-49cd-a0ab-7dcdc5a5295f
     * reqId : null
     * reqType : null
     * resResult : {"curData":{"entryId":"db1c567d-fac6-4a32-9931-1febf06f3284","title":"Testing CSDN","description":"hello world","entryLink":"https://blog.csdn.net/yelvgou9995/article/details/85124543","content":null,"collectionId":"28084a85-6d10-4693-bd27-d031b2478bcf","showType":null,"sourceId":"2085cb9a-5dd4-4908-b2e7-1b516e945a8e","sourceName":"csdn","sourceLink":"https://blog.csdn.net","collectionName":"Update name test","updateTime":"12/22/18 6:52 PM"},"isSuccess":true,"message":"请求成功"}
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
         * curData : {"entryId":"db1c567d-fac6-4a32-9931-1febf06f3284","title":"Testing CSDN","description":"hello world","entryLink":"https://blog.csdn.net/yelvgou9995/article/details/85124543","content":null,"collectionId":"28084a85-6d10-4693-bd27-d031b2478bcf","showType":null,"sourceId":"2085cb9a-5dd4-4908-b2e7-1b516e945a8e","sourceName":"csdn","sourceLink":"https://blog.csdn.net","collectionName":"Update name test","updateTime":"12/22/18 6:52 PM"}
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
             * entryId : db1c567d-fac6-4a32-9931-1febf06f3284
             * title : Testing CSDN
             * description : hello world
             * entryLink : https://blog.csdn.net/yelvgou9995/article/details/85124543
             * content : null
             * collectionId : 28084a85-6d10-4693-bd27-d031b2478bcf
             * showType : null
             * sourceId : 2085cb9a-5dd4-4908-b2e7-1b516e945a8e
             * sourceName : csdn
             * sourceLink : https://blog.csdn.net
             * collectionName : Update name test
             * updateTime : 12/22/18 6:52 PM
             */

            private String entryId;
            private String title;
            private String description;
            private String entryLink;
            private Object content;
            private String collectionId;
            private Object showType;
            private String sourceId;
            private String sourceName;
            private String sourceLink;
            private String collectionName;
            private String updateTime;

            public String getEntryId() {
                return entryId;
            }

            public void setEntryId(String entryId) {
                this.entryId = entryId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getEntryLink() {
                return entryLink;
            }

            public void setEntryLink(String entryLink) {
                this.entryLink = entryLink;
            }

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
                this.content = content;
            }

            public String getCollectionId() {
                return collectionId;
            }

            public void setCollectionId(String collectionId) {
                this.collectionId = collectionId;
            }

            public Object getShowType() {
                return showType;
            }

            public void setShowType(Object showType) {
                this.showType = showType;
            }

            public String getSourceId() {
                return sourceId;
            }

            public void setSourceId(String sourceId) {
                this.sourceId = sourceId;
            }

            public String getSourceName() {
                return sourceName;
            }

            public void setSourceName(String sourceName) {
                this.sourceName = sourceName;
            }

            public String getSourceLink() {
                return sourceLink;
            }

            public void setSourceLink(String sourceLink) {
                this.sourceLink = sourceLink;
            }

            public String getCollectionName() {
                return collectionName;
            }

            public void setCollectionName(String collectionName) {
                this.collectionName = collectionName;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
