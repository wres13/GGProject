package com.dongdian.jj.gorgeous.dto;

import java.util.List;

/**
 * Created by maoyuan he on 2018/4/16.
 * Email:1021661582@qq.com
 * des: 请求图片实体
 * version: 1.0.0
 */

public class ImageData {

    /**
     * error : false
     * results : [{"_id":"5b456f5d421aa92fc4eebe48","createdAt":"2018-07-11T10:45:49.246Z","desc":"2018-07-11","publishedAt":"2018-07-11T00:00:00.0Z","source":"web","type":"福利","url":"https://ww1.sinaimg.cn/large/0065oQSqly1ft5q7ys128j30sg10gnk5.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b441f06421aa92fccb520a2","createdAt":"2018-07-10T10:50:46.379Z","desc":"2018-07-10","publishedAt":"2018-07-10T00:00:00.0Z","source":"web","type":"福利","url":"https://ww1.sinaimg.cn/large/0065oQSqgy1ft4kqrmb9bj30sg10fdzq.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b42d1aa421aa92d1cba2918","createdAt":"2018-07-09T11:08:26.162Z","desc":"2018-07-09","publishedAt":"2018-07-09T00:00:00.0Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1ft3fna1ef9j30s210skgd.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b3ed2d5421aa91cfe803e35","createdAt":"2018-07-06T10:24:21.907Z","desc":"2018-07-06","publishedAt":"2018-07-06T00:00:00.0Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1fszxi9lmmzj30f00jdadv.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b3d883f421aa906e5b3c6f1","createdAt":"2018-07-05T10:53:51.361Z","desc":"2018-07-05","publishedAt":"2018-07-05T00:00:00.0Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1fsysqszneoj30hi0pvqb7.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b3ae394421aa906e7db029b","createdAt":"2018-07-03T10:46:44.112Z","desc":"2018-07-03","publishedAt":"2018-07-03T00:00:00.0Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1fswhaqvnobj30sg14hka0.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b398cf8421aa95570db5491","createdAt":"2018-07-02T10:24:56.546Z","desc":"2018-07-02","publishedAt":"2018-07-02T00:00:00.0Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1fsvb1xduvaj30u013175p.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b33ccf2421aa95570db5478","createdAt":"2018-06-28T01:44:18.488Z","desc":"2018-06-28","publishedAt":"2018-06-28T00:00:00.0Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1fsq9iq8ttrj30k80q9wi4.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b32807e421aa95570db5471","createdAt":"2018-06-27T02:05:50.227Z","desc":"2018-06-27","publishedAt":"2018-06-27T00:00:00.0Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1fsp4iok6o4j30j60optbl.jpg","used":true,"who":"lijinshanmx"},{"_id":"5b31aa33421aa9556d2cc4a7","createdAt":"2018-06-26T10:51:31.60Z","desc":"2018-06-26","publishedAt":"2018-06-26T00:00:00.0Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1fsoe3k2gkkj30g50niwla.jpg","used":true,"who":"lijinshanmx"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5b456f5d421aa92fc4eebe48
         * createdAt : 2018-07-11T10:45:49.246Z
         * desc : 2018-07-11
         * publishedAt : 2018-07-11T00:00:00.0Z
         * source : web
         * type : 福利
         * url : https://ww1.sinaimg.cn/large/0065oQSqly1ft5q7ys128j30sg10gnk5.jpg
         * used : true
         * who : lijinshanmx
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
