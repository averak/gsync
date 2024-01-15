package net.averak.gsync.adapter.dao.dto.base;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all"})
public class EchoExample {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table gsync_echo
	 *
	 * @mbg.generated
	 */
	protected String orderByClause;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table gsync_echo
	 *
	 * @mbg.generated
	 */
	protected boolean distinct;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table gsync_echo
	 *
	 * @mbg.generated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	public EchoExample() {
		oredCriteria = new ArrayList<>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_echo
	 *
	 * @mbg.generated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the
	 * database table gsync_echo
	 *
	 * @mbg.generated
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<>();
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

		public Criteria andEchoIdIsNull() {
			addCriterion("`echo_id` is null");
			return (Criteria) this;
		}

		public Criteria andEchoIdIsNotNull() {
			addCriterion("`echo_id` is not null");
			return (Criteria) this;
		}

		public Criteria andEchoIdEqualTo(String value) {
			addCriterion("`echo_id` =", value, "echoId");
			return (Criteria) this;
		}

		public Criteria andEchoIdNotEqualTo(String value) {
			addCriterion("`echo_id` <>", value, "echoId");
			return (Criteria) this;
		}

		public Criteria andEchoIdGreaterThan(String value) {
			addCriterion("`echo_id` >", value, "echoId");
			return (Criteria) this;
		}

		public Criteria andEchoIdGreaterThanOrEqualTo(String value) {
			addCriterion("`echo_id` >=", value, "echoId");
			return (Criteria) this;
		}

		public Criteria andEchoIdLessThan(String value) {
			addCriterion("`echo_id` <", value, "echoId");
			return (Criteria) this;
		}

		public Criteria andEchoIdLessThanOrEqualTo(String value) {
			addCriterion("`echo_id` <=", value, "echoId");
			return (Criteria) this;
		}

		public Criteria andEchoIdLike(String value) {
			addCriterion("`echo_id` like", value, "echoId");
			return (Criteria) this;
		}

		public Criteria andEchoIdNotLike(String value) {
			addCriterion("`echo_id` not like", value, "echoId");
			return (Criteria) this;
		}

		public Criteria andEchoIdIn(List<String> values) {
			addCriterion("`echo_id` in", values, "echoId");
			return (Criteria) this;
		}

		public Criteria andEchoIdNotIn(List<String> values) {
			addCriterion("`echo_id` not in", values, "echoId");
			return (Criteria) this;
		}

		public Criteria andEchoIdBetween(String value1, String value2) {
			addCriterion("`echo_id` between", value1, value2, "echoId");
			return (Criteria) this;
		}

		public Criteria andEchoIdNotBetween(String value1, String value2) {
			addCriterion("`echo_id` not between", value1, value2, "echoId");
			return (Criteria) this;
		}

		public Criteria andMessageIsNull() {
			addCriterion("`message` is null");
			return (Criteria) this;
		}

		public Criteria andMessageIsNotNull() {
			addCriterion("`message` is not null");
			return (Criteria) this;
		}

		public Criteria andMessageEqualTo(String value) {
			addCriterion("`message` =", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageNotEqualTo(String value) {
			addCriterion("`message` <>", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageGreaterThan(String value) {
			addCriterion("`message` >", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageGreaterThanOrEqualTo(String value) {
			addCriterion("`message` >=", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageLessThan(String value) {
			addCriterion("`message` <", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageLessThanOrEqualTo(String value) {
			addCriterion("`message` <=", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageLike(String value) {
			addCriterion("`message` like", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageNotLike(String value) {
			addCriterion("`message` not like", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageIn(List<String> values) {
			addCriterion("`message` in", values, "message");
			return (Criteria) this;
		}

		public Criteria andMessageNotIn(List<String> values) {
			addCriterion("`message` not in", values, "message");
			return (Criteria) this;
		}

		public Criteria andMessageBetween(String value1, String value2) {
			addCriterion("`message` between", value1, value2, "message");
			return (Criteria) this;
		}

		public Criteria andMessageNotBetween(String value1, String value2) {
			addCriterion("`message` not between", value1, value2, "message");
			return (Criteria) this;
		}

		public Criteria andTimestampIsNull() {
			addCriterion("`timestamp` is null");
			return (Criteria) this;
		}

		public Criteria andTimestampIsNotNull() {
			addCriterion("`timestamp` is not null");
			return (Criteria) this;
		}

		public Criteria andTimestampEqualTo(LocalDateTime value) {
			addCriterion("`timestamp` =", value, "timestamp");
			return (Criteria) this;
		}

		public Criteria andTimestampNotEqualTo(LocalDateTime value) {
			addCriterion("`timestamp` <>", value, "timestamp");
			return (Criteria) this;
		}

		public Criteria andTimestampGreaterThan(LocalDateTime value) {
			addCriterion("`timestamp` >", value, "timestamp");
			return (Criteria) this;
		}

		public Criteria andTimestampGreaterThanOrEqualTo(LocalDateTime value) {
			addCriterion("`timestamp` >=", value, "timestamp");
			return (Criteria) this;
		}

		public Criteria andTimestampLessThan(LocalDateTime value) {
			addCriterion("`timestamp` <", value, "timestamp");
			return (Criteria) this;
		}

		public Criteria andTimestampLessThanOrEqualTo(LocalDateTime value) {
			addCriterion("`timestamp` <=", value, "timestamp");
			return (Criteria) this;
		}

		public Criteria andTimestampIn(List<LocalDateTime> values) {
			addCriterion("`timestamp` in", values, "timestamp");
			return (Criteria) this;
		}

		public Criteria andTimestampNotIn(List<LocalDateTime> values) {
			addCriterion("`timestamp` not in", values, "timestamp");
			return (Criteria) this;
		}

		public Criteria andTimestampBetween(LocalDateTime value1, LocalDateTime value2) {
			addCriterion("`timestamp` between", value1, value2, "timestamp");
			return (Criteria) this;
		}

		public Criteria andTimestampNotBetween(LocalDateTime value1, LocalDateTime value2) {
			addCriterion("`timestamp` not between", value1, value2, "timestamp");
			return (Criteria) this;
		}

		public Criteria andCreatedAtIsNull() {
			addCriterion("`created_at` is null");
			return (Criteria) this;
		}

		public Criteria andCreatedAtIsNotNull() {
			addCriterion("`created_at` is not null");
			return (Criteria) this;
		}

		public Criteria andCreatedAtEqualTo(LocalDateTime value) {
			addCriterion("`created_at` =", value, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtNotEqualTo(LocalDateTime value) {
			addCriterion("`created_at` <>", value, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtGreaterThan(LocalDateTime value) {
			addCriterion("`created_at` >", value, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtGreaterThanOrEqualTo(LocalDateTime value) {
			addCriterion("`created_at` >=", value, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtLessThan(LocalDateTime value) {
			addCriterion("`created_at` <", value, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtLessThanOrEqualTo(LocalDateTime value) {
			addCriterion("`created_at` <=", value, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtIn(List<LocalDateTime> values) {
			addCriterion("`created_at` in", values, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtNotIn(List<LocalDateTime> values) {
			addCriterion("`created_at` not in", values, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtBetween(LocalDateTime value1, LocalDateTime value2) {
			addCriterion("`created_at` between", value1, value2, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
			addCriterion("`created_at` not between", value1, value2, "createdAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtIsNull() {
			addCriterion("`updated_at` is null");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtIsNotNull() {
			addCriterion("`updated_at` is not null");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtEqualTo(LocalDateTime value) {
			addCriterion("`updated_at` =", value, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtNotEqualTo(LocalDateTime value) {
			addCriterion("`updated_at` <>", value, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtGreaterThan(LocalDateTime value) {
			addCriterion("`updated_at` >", value, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtGreaterThanOrEqualTo(LocalDateTime value) {
			addCriterion("`updated_at` >=", value, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtLessThan(LocalDateTime value) {
			addCriterion("`updated_at` <", value, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtLessThanOrEqualTo(LocalDateTime value) {
			addCriterion("`updated_at` <=", value, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtIn(List<LocalDateTime> values) {
			addCriterion("`updated_at` in", values, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtNotIn(List<LocalDateTime> values) {
			addCriterion("`updated_at` not in", values, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtBetween(LocalDateTime value1, LocalDateTime value2) {
			addCriterion("`updated_at` between", value1, value2, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
			addCriterion("`updated_at` not between", value1, value2, "updatedAt");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the
	 * database table gsync_echo
	 *
	 * @mbg.generated do_not_delete_during_merge
	 */
	public static class Criteria extends GeneratedCriteria {
		protected Criteria() {
			super();
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the
	 * database table gsync_echo
	 *
	 * @mbg.generated
	 */
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