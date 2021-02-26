package com.atguigu.crowd.entity.po;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdvertisementPOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAdvertisementDescIsNull() {
            addCriterion("advertisement_desc is null");
            return (Criteria) this;
        }

        public Criteria andAdvertisementDescIsNotNull() {
            addCriterion("advertisement_desc is not null");
            return (Criteria) this;
        }

        public Criteria andAdvertisementDescEqualTo(String value) {
            addCriterion("advertisement_desc =", value, "advertisementDesc");
            return (Criteria) this;
        }

        public Criteria andAdvertisementDescNotEqualTo(String value) {
            addCriterion("advertisement_desc <>", value, "advertisementDesc");
            return (Criteria) this;
        }

        public Criteria andAdvertisementDescGreaterThan(String value) {
            addCriterion("advertisement_desc >", value, "advertisementDesc");
            return (Criteria) this;
        }

        public Criteria andAdvertisementDescGreaterThanOrEqualTo(String value) {
            addCriterion("advertisement_desc >=", value, "advertisementDesc");
            return (Criteria) this;
        }

        public Criteria andAdvertisementDescLessThan(String value) {
            addCriterion("advertisement_desc <", value, "advertisementDesc");
            return (Criteria) this;
        }

        public Criteria andAdvertisementDescLessThanOrEqualTo(String value) {
            addCriterion("advertisement_desc <=", value, "advertisementDesc");
            return (Criteria) this;
        }

        public Criteria andAdvertisementDescLike(String value) {
            addCriterion("advertisement_desc like", value, "advertisementDesc");
            return (Criteria) this;
        }

        public Criteria andAdvertisementDescNotLike(String value) {
            addCriterion("advertisement_desc not like", value, "advertisementDesc");
            return (Criteria) this;
        }

        public Criteria andAdvertisementDescIn(List<String> values) {
            addCriterion("advertisement_desc in", values, "advertisementDesc");
            return (Criteria) this;
        }

        public Criteria andAdvertisementDescNotIn(List<String> values) {
            addCriterion("advertisement_desc not in", values, "advertisementDesc");
            return (Criteria) this;
        }

        public Criteria andAdvertisementDescBetween(String value1, String value2) {
            addCriterion("advertisement_desc between", value1, value2, "advertisementDesc");
            return (Criteria) this;
        }

        public Criteria andAdvertisementDescNotBetween(String value1, String value2) {
            addCriterion("advertisement_desc not between", value1, value2, "advertisementDesc");
            return (Criteria) this;
        }

        public Criteria andAdvertisementUrlIsNull() {
            addCriterion("advertisement_url is null");
            return (Criteria) this;
        }

        public Criteria andAdvertisementUrlIsNotNull() {
            addCriterion("advertisement_url is not null");
            return (Criteria) this;
        }

        public Criteria andAdvertisementUrlEqualTo(String value) {
            addCriterion("advertisement_url =", value, "advertisementUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertisementUrlNotEqualTo(String value) {
            addCriterion("advertisement_url <>", value, "advertisementUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertisementUrlGreaterThan(String value) {
            addCriterion("advertisement_url >", value, "advertisementUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertisementUrlGreaterThanOrEqualTo(String value) {
            addCriterion("advertisement_url >=", value, "advertisementUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertisementUrlLessThan(String value) {
            addCriterion("advertisement_url <", value, "advertisementUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertisementUrlLessThanOrEqualTo(String value) {
            addCriterion("advertisement_url <=", value, "advertisementUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertisementUrlLike(String value) {
            addCriterion("advertisement_url like", value, "advertisementUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertisementUrlNotLike(String value) {
            addCriterion("advertisement_url not like", value, "advertisementUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertisementUrlIn(List<String> values) {
            addCriterion("advertisement_url in", values, "advertisementUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertisementUrlNotIn(List<String> values) {
            addCriterion("advertisement_url not in", values, "advertisementUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertisementUrlBetween(String value1, String value2) {
            addCriterion("advertisement_url between", value1, value2, "advertisementUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertisementUrlNotBetween(String value1, String value2) {
            addCriterion("advertisement_url not between", value1, value2, "advertisementUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertisementPicIsNull() {
            addCriterion("advertisement_pic is null");
            return (Criteria) this;
        }

        public Criteria andAdvertisementPicIsNotNull() {
            addCriterion("advertisement_pic is not null");
            return (Criteria) this;
        }

        public Criteria andAdvertisementPicEqualTo(String value) {
            addCriterion("advertisement_pic =", value, "advertisementPic");
            return (Criteria) this;
        }

        public Criteria andAdvertisementPicNotEqualTo(String value) {
            addCriterion("advertisement_pic <>", value, "advertisementPic");
            return (Criteria) this;
        }

        public Criteria andAdvertisementPicGreaterThan(String value) {
            addCriterion("advertisement_pic >", value, "advertisementPic");
            return (Criteria) this;
        }

        public Criteria andAdvertisementPicGreaterThanOrEqualTo(String value) {
            addCriterion("advertisement_pic >=", value, "advertisementPic");
            return (Criteria) this;
        }

        public Criteria andAdvertisementPicLessThan(String value) {
            addCriterion("advertisement_pic <", value, "advertisementPic");
            return (Criteria) this;
        }

        public Criteria andAdvertisementPicLessThanOrEqualTo(String value) {
            addCriterion("advertisement_pic <=", value, "advertisementPic");
            return (Criteria) this;
        }

        public Criteria andAdvertisementPicLike(String value) {
            addCriterion("advertisement_pic like", value, "advertisementPic");
            return (Criteria) this;
        }

        public Criteria andAdvertisementPicNotLike(String value) {
            addCriterion("advertisement_pic not like", value, "advertisementPic");
            return (Criteria) this;
        }

        public Criteria andAdvertisementPicIn(List<String> values) {
            addCriterion("advertisement_pic in", values, "advertisementPic");
            return (Criteria) this;
        }

        public Criteria andAdvertisementPicNotIn(List<String> values) {
            addCriterion("advertisement_pic not in", values, "advertisementPic");
            return (Criteria) this;
        }

        public Criteria andAdvertisementPicBetween(String value1, String value2) {
            addCriterion("advertisement_pic between", value1, value2, "advertisementPic");
            return (Criteria) this;
        }

        public Criteria andAdvertisementPicNotBetween(String value1, String value2) {
            addCriterion("advertisement_pic not between", value1, value2, "advertisementPic");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}