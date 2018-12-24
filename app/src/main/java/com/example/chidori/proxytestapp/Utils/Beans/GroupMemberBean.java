package com.example.chidori.proxytestapp.Utils.Beans;

import java.util.List;

public class GroupMemberBean {


    /**
     * resId : c34cc1c3-3bbe-4617-a2cc-e91fc9dcce38
     * reqId : null
     * reqType : null
     * resResult : {"curData":[{"userId":"061583de-12be-4116-ac58-e7343aa7f024","username":"abcd","sex":0,"phone":"13","email":"111@qq.com","avatar":null}],"isSuccess":true,"message":"请求成功"}
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
         * curData : [{"userId":"061583de-12be-4116-ac58-e7343aa7f024","username":"abcd","sex":0,"phone":"13","email":"111@qq.com","avatar":null}]
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
             * userId : 061583de-12be-4116-ac58-e7343aa7f024
             * username : abcd
             * sex : 0
             * phone : 13
             * email : 111@qq.com
             * avatar : null
             */

            private String userId;
            private String username;
            private int sex;
            private String phone;
            private String email;
            private Object avatar;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public Object getAvatar() {
                return avatar;
            }

            public void setAvatar(Object avatar) {
                this.avatar = avatar;
            }
        }
    }
}
