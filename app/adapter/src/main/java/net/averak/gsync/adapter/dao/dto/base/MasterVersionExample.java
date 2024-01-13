package net.averak.gsync.adapter.dao.dto.base;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all"})
public class MasterVersionExample {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table gsync_master_version
	 *
	 * @mbg.generated
	 */
	protected String orderByClause;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table gsync_master_version
	 *
	 * @mbg.generated
	 */
	protected boolean distinct;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table gsync_master_version
	 *
	 * @mbg.generated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_master_version
	 *
	 * @mbg.generated
	 */
	public MasterVersionExample() {
		oredCriteria = new ArrayList<>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_master_version
	 *
	 * @mbg.generated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_master_version
	 *
	 * @mbg.generated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_master_version
	 *
	 * @mbg.generated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_master_version
	 *
	 * @mbg.generated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_master_version
	 *
	 * @mbg.generated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_master_version
	 *
	 * @mbg.generated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_master_version
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
	 * the database table gsync_master_version
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
	 * the database table gsync_master_version
	 *
	 * @mbg.generated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_master_version
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
	 * database table gsync_master_version
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

		public Criteria andVersionIsNull() {
			addCriterion("`version` is null");
			return (Criteria) this;
		}

		public Criteria andVersionIsNotNull() {
			addCriterion("`version` is not null");
			return (Criteria) this;
		}

		public Criteria andVersionEqualTo(String value) {
			addCriterion("`version` =", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionNotEqualTo(String value) {
			addCriterion("`version` <>", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionGreaterThan(String value) {
			addCriterion("`version` >", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionGreaterThanOrEqualTo(String value) {
			addCriterion("`version` >=", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionLessThan(String value) {
			addCriterion("`version` <", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionLessThanOrEqualTo(String value) {
			addCriterion("`version` <=", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionLike(String value) {
			addCriterion("`version` like", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionNotLike(String value) {
			addCriterion("`version` not like", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionIn(List<String> values) {
			addCriterion("`version` in", values, "version");
			return (Criteria) this;
		}

		public Criteria andVersionNotIn(List<String> values) {
			addCriterion("`version` not in", values, "version");
			return (Criteria) this;
		}

		public Criteria andVersionBetween(String value1, String value2) {
			addCriterion("`version` between", value1, value2, "version");
			return (Criteria) this;
		}

		public Criteria andVersionNotBetween(String value1, String value2) {
			addCriterion("`version` not between", value1, value2, "version");
			return (Criteria) this;
		}

		public Criteria andIsEnabledIsNull() {
			addCriterion("`is_enabled` is null");
			return (Criteria) this;
		}

		public Criteria andIsEnabledIsNotNull() {
			addCriterion("`is_enabled` is not null");
			return (Criteria) this;
		}

		public Criteria andIsEnabledEqualTo(Boolean value) {
			addCriterion("`is_enabled` =", value, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledNotEqualTo(Boolean value) {
			addCriterion("`is_enabled` <>", value, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledGreaterThan(Boolean value) {
			addCriterion("`is_enabled` >", value, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledGreaterThanOrEqualTo(Boolean value) {
			addCriterion("`is_enabled` >=", value, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledLessThan(Boolean value) {
			addCriterion("`is_enabled` <", value, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledLessThanOrEqualTo(Boolean value) {
			addCriterion("`is_enabled` <=", value, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledIn(List<Boolean> values) {
			addCriterion("`is_enabled` in", values, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledNotIn(List<Boolean> values) {
			addCriterion("`is_enabled` not in", values, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledBetween(Boolean value1, Boolean value2) {
			addCriterion("`is_enabled` between", value1, value2, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andIsEnabledNotBetween(Boolean value1, Boolean value2) {
			addCriterion("`is_enabled` not between", value1, value2, "isEnabled");
			return (Criteria) this;
		}

		public Criteria andCommentIsNull() {
			addCriterion("`comment` is null");
			return (Criteria) this;
		}

		public Criteria andCommentIsNotNull() {
			addCriterion("`comment` is not null");
			return (Criteria) this;
		}

		public Criteria andCommentEqualTo(String value) {
			addCriterion("`comment` =", value, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentNotEqualTo(String value) {
			addCriterion("`comment` <>", value, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentGreaterThan(String value) {
			addCriterion("`comment` >", value, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentGreaterThanOrEqualTo(String value) {
			addCriterion("`comment` >=", value, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentLessThan(String value) {
			addCriterion("`comment` <", value, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentLessThanOrEqualTo(String value) {
			addCriterion("`comment` <=", value, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentLike(String value) {
			addCriterion("`comment` like", value, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentNotLike(String value) {
			addCriterion("`comment` not like", value, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentIn(List<String> values) {
			addCriterion("`comment` in", values, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentNotIn(List<String> values) {
			addCriterion("`comment` not in", values, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentBetween(String value1, String value2) {
			addCriterion("`comment` between", value1, value2, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentNotBetween(String value1, String value2) {
			addCriterion("`comment` not between", value1, value2, "comment");
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
	 * database table gsync_master_version
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
	 * database table gsync_master_version
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