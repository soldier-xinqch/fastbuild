package com.fastbuild.email;

/**
 *  邮件枚举
 */
public interface EmailEnum {

    public enum MAIL_METHOD implements EmailEnum{
        /**
         *   纯文本
         */
        TEXT(1),
        /**
         *  内嵌图片和文本
         */
        IMAGE_TEXT(2),
        /**
         *  附件和文本
         */
        ATTACH_TEXT(3),
        /**
         *  附件和内嵌图片和文本
         */
        ATTACH_TEXT_IMAGE(4);

        private Integer value;
        MAIL_METHOD(Integer value){
            this.value = value;
        }
        public Integer getValue(){
            return value;
        }

    }
}
