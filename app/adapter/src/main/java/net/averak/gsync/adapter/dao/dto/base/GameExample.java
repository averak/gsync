package net.averak.gsync.adapter.dao.dto.base;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all"})
public class GameExample {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table gsync_game
	 *
	 * @mbg.generated
	 */
	protected String orderByClause;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table gsync_game
	 *
	 * @mbg.generated
	 */
	protected boolean distinct;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database table gsync_game
	 *
	 * @mbg.generated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_game
	 *
	 * @mbg.generated
	 */
	public GameExample() {
		oredCriteria = new ArrayList<>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_game
	 *
	 * @mbg.generated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_game
	 *
	 * @mbg.generated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_game
	 *
	 * @mbg.generated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_game
	 *
	 * @mbg.generated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_game
	 *
	 * @mbg.generated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_game
	 *
	 * @mbg.generated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_game
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
	 * the database table gsync_game
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
	 * the database table gsync_game
	 *
	 * @mbg.generated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table gsync_game
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
	 * database table gsync_game
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

		public Criteria andGameIdIsNull() {
			addCriterion("`game_id` is null");
			return (Criteria) this;
		}

		public Criteria andGameIdIsNotNull() {
			addCriterion("`game_id` is not null");
			return (Criteria) this;
		}

		public Criteria andGameIdEqualTo(String value) {
			addCriterion("`game_id` =", value, "gameId");
			return (Criteria) this;
		}

		public Criteria andGameIdNotEqualTo(String value) {
			addCriterion("`game_id` <>", value, "gameId");
			return (Criteria) this;
		}

		public Criteria andGameIdGreaterThan(String value) {
			addCriterion("`game_id` >", value, "gameId");
			return (Criteria) this;
		}

		public Criteria andGameIdGreaterThanOrEqualTo(String value) {
			addCriterion("`game_id` >=", value, "gameId");
			return (Criteria) this;
		}

		public Criteria andGameIdLessThan(String value) {
			addCriterion("`game_id` <", value, "gameId");
			return (Criteria) this;
		}

		public Criteria andGameIdLessThanOrEqualTo(String value) {
			addCriterion("`game_id` <=", value, "gameId");
			return (Criteria) this;
		}

		public Criteria andGameIdLike(String value) {
			addCriterion("`game_id` like", value, "gameId");
			return (Criteria) this;
		}

		public Criteria andGameIdNotLike(String value) {
			addCriterion("`game_id` not like", value, "gameId");
			return (Criteria) this;
		}

		public Criteria andGameIdIn(List<String> values) {
			addCriterion("`game_id` in", values, "gameId");
			return (Criteria) this;
		}

		public Criteria andGameIdNotIn(List<String> values) {
			addCriterion("`game_id` not in", values, "gameId");
			return (Criteria) this;
		}

		public Criteria andGameIdBetween(String value1, String value2) {
			addCriterion("`game_id` between", value1, value2, "gameId");
			return (Criteria) this;
		}

		public Criteria andGameIdNotBetween(String value1, String value2) {
			addCriterion("`game_id` not between", value1, value2, "gameId");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("`name` is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("`name` is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("`name` =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("`name` <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("`name` >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("`name` >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("`name` <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("`name` <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("`name` like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("`name` not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("`name` in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("`name` not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("`name` between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("`name` not between", value1, value2, "name");
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
	 * database table gsync_game
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
	 * database table gsync_game
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