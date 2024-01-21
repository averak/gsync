// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: resource/player_storage.proto

// Protobuf Java Version: 3.25.2
package net.averak.gsync.schema.protobuf.resource.player_storage;

/**
 * Protobuf type {@code gsync.resource.player_storage.Criteria}
 */
public final class Criteria extends com.google.protobuf.GeneratedMessageV3
		implements
			// @@protoc_insertion_point(message_implements:gsync.resource.player_storage.Criteria)
			CriteriaOrBuilder {
	private static final long serialVersionUID = 0L;
	// Use Criteria.newBuilder() to construct.
	private Criteria(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
		super(builder);
	}
	private Criteria() {
		pattern_ = "";
		matchingType_ = 0;
	}

	@java.lang.Override
	@SuppressWarnings({"unused"})
	protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
		return new Criteria();
	}

	public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
		return net.averak.gsync.schema.protobuf.resource.player_storage.PlayerStorage.internal_static_gsync_resource_player_storage_Criteria_descriptor;
	}

	@java.lang.Override
	protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
		return net.averak.gsync.schema.protobuf.resource.player_storage.PlayerStorage.internal_static_gsync_resource_player_storage_Criteria_fieldAccessorTable
				.ensureFieldAccessorsInitialized(
						net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.class,
						net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.Builder.class);
	}

	/**
	 * Protobuf enum {@code gsync.resource.player_storage.Criteria.MatchingType}
	 */
	public enum MatchingType implements com.google.protobuf.ProtocolMessageEnum {
		/**
		 * <code>MATCHING_TYPE_UNSPECIFIED = 0;</code>
		 */
		MATCHING_TYPE_UNSPECIFIED(0),
		/**
		 * <code>MATCHING_TYPE_EXACT_MATCH = 1;</code>
		 */
		MATCHING_TYPE_EXACT_MATCH(1),
		/**
		 * <code>MATCHING_TYPE_FORWARD_MATCH = 2;</code>
		 */
		MATCHING_TYPE_FORWARD_MATCH(2), UNRECOGNIZED(-1),;

		/**
		 * <code>MATCHING_TYPE_UNSPECIFIED = 0;</code>
		 */
		public static final int MATCHING_TYPE_UNSPECIFIED_VALUE = 0;
		/**
		 * <code>MATCHING_TYPE_EXACT_MATCH = 1;</code>
		 */
		public static final int MATCHING_TYPE_EXACT_MATCH_VALUE = 1;
		/**
		 * <code>MATCHING_TYPE_FORWARD_MATCH = 2;</code>
		 */
		public static final int MATCHING_TYPE_FORWARD_MATCH_VALUE = 2;

		public final int getNumber() {
			if (this == UNRECOGNIZED) {
				throw new java.lang.IllegalArgumentException("Can't get the number of an unknown enum value.");
			}
			return value;
		}

		/**
		 * @param value
		 *            The numeric wire value of the corresponding enum entry.
		 * @return The enum associated with the given numeric wire value.
		 * @deprecated Use {@link #forNumber(int)} instead.
		 */
		@java.lang.Deprecated
		public static MatchingType valueOf(int value) {
			return forNumber(value);
		}

		/**
		 * @param value
		 *            The numeric wire value of the corresponding enum entry.
		 * @return The enum associated with the given numeric wire value.
		 */
		public static MatchingType forNumber(int value) {
			switch (value) {
				case 0 :
					return MATCHING_TYPE_UNSPECIFIED;
				case 1 :
					return MATCHING_TYPE_EXACT_MATCH;
				case 2 :
					return MATCHING_TYPE_FORWARD_MATCH;
				default :
					return null;
			}
		}

		public static com.google.protobuf.Internal.EnumLiteMap<MatchingType> internalGetValueMap() {
			return internalValueMap;
		}
		private static final com.google.protobuf.Internal.EnumLiteMap<MatchingType> internalValueMap = new com.google.protobuf.Internal.EnumLiteMap<MatchingType>() {
			public MatchingType findValueByNumber(int number) {
				return MatchingType.forNumber(number);
			}
		};

		public final com.google.protobuf.Descriptors.EnumValueDescriptor getValueDescriptor() {
			if (this == UNRECOGNIZED) {
				throw new java.lang.IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
			}
			return getDescriptor().getValues().get(ordinal());
		}
		public final com.google.protobuf.Descriptors.EnumDescriptor getDescriptorForType() {
			return getDescriptor();
		}
		public static final com.google.protobuf.Descriptors.EnumDescriptor getDescriptor() {
			return net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.getDescriptor().getEnumTypes()
					.get(0);
		}

		private static final MatchingType[] VALUES = values();

		public static MatchingType valueOf(com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
			if (desc.getType() != getDescriptor()) {
				throw new java.lang.IllegalArgumentException("EnumValueDescriptor is not for this type.");
			}
			if (desc.getIndex() == -1) {
				return UNRECOGNIZED;
			}
			return VALUES[desc.getIndex()];
		}

		private final int value;

		private MatchingType(int value) {
			this.value = value;
		}

		// @@protoc_insertion_point(enum_scope:gsync.resource.player_storage.Criteria.MatchingType)
	}

	public static final int PATTERN_FIELD_NUMBER = 1;
	@SuppressWarnings("serial")
	private volatile java.lang.Object pattern_ = "";
	/**
	 * <code>string pattern = 1;</code>
	 * 
	 * @return The pattern.
	 */
	@java.lang.Override
	public java.lang.String getPattern() {
		java.lang.Object ref = pattern_;
		if (ref instanceof java.lang.String) {
			return (java.lang.String) ref;
		} else {
			com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
			java.lang.String s = bs.toStringUtf8();
			pattern_ = s;
			return s;
		}
	}
	/**
	 * <code>string pattern = 1;</code>
	 * 
	 * @return The bytes for pattern.
	 */
	@java.lang.Override
	public com.google.protobuf.ByteString getPatternBytes() {
		java.lang.Object ref = pattern_;
		if (ref instanceof java.lang.String) {
			com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
			pattern_ = b;
			return b;
		} else {
			return (com.google.protobuf.ByteString) ref;
		}
	}

	public static final int MATCHING_TYPE_FIELD_NUMBER = 2;
	private int matchingType_ = 0;
	/**
	 * <code>.gsync.resource.player_storage.Criteria.MatchingType matching_type = 2;</code>
	 * 
	 * @return The enum numeric value on the wire for matchingType.
	 */
	@java.lang.Override
	public int getMatchingTypeValue() {
		return matchingType_;
	}
	/**
	 * <code>.gsync.resource.player_storage.Criteria.MatchingType matching_type = 2;</code>
	 * 
	 * @return The matchingType.
	 */
	@java.lang.Override
	public net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.MatchingType getMatchingType() {
		net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.MatchingType result = net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.MatchingType
				.forNumber(matchingType_);
		return result == null
				? net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.MatchingType.UNRECOGNIZED
				: result;
	}

	private byte memoizedIsInitialized = -1;
	@java.lang.Override
	public final boolean isInitialized() {
		byte isInitialized = memoizedIsInitialized;
		if (isInitialized == 1)
			return true;
		if (isInitialized == 0)
			return false;

		memoizedIsInitialized = 1;
		return true;
	}

	@java.lang.Override
	public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
		if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(pattern_)) {
			com.google.protobuf.GeneratedMessageV3.writeString(output, 1, pattern_);
		}
		if (matchingType_ != net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.MatchingType.MATCHING_TYPE_UNSPECIFIED
				.getNumber()) {
			output.writeEnum(2, matchingType_);
		}
		getUnknownFields().writeTo(output);
	}

	@java.lang.Override
	public int getSerializedSize() {
		int size = memoizedSize;
		if (size != -1)
			return size;

		size = 0;
		if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(pattern_)) {
			size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, pattern_);
		}
		if (matchingType_ != net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.MatchingType.MATCHING_TYPE_UNSPECIFIED
				.getNumber()) {
			size += com.google.protobuf.CodedOutputStream.computeEnumSize(2, matchingType_);
		}
		size += getUnknownFields().getSerializedSize();
		memoizedSize = size;
		return size;
	}

	@java.lang.Override
	public boolean equals(final java.lang.Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof net.averak.gsync.schema.protobuf.resource.player_storage.Criteria)) {
			return super.equals(obj);
		}
		net.averak.gsync.schema.protobuf.resource.player_storage.Criteria other = (net.averak.gsync.schema.protobuf.resource.player_storage.Criteria) obj;

		if (!getPattern().equals(other.getPattern()))
			return false;
		if (matchingType_ != other.matchingType_)
			return false;
		if (!getUnknownFields().equals(other.getUnknownFields()))
			return false;
		return true;
	}

	@java.lang.Override
	public int hashCode() {
		if (memoizedHashCode != 0) {
			return memoizedHashCode;
		}
		int hash = 41;
		hash = (19 * hash) + getDescriptor().hashCode();
		hash = (37 * hash) + PATTERN_FIELD_NUMBER;
		hash = (53 * hash) + getPattern().hashCode();
		hash = (37 * hash) + MATCHING_TYPE_FIELD_NUMBER;
		hash = (53 * hash) + matchingType_;
		hash = (29 * hash) + getUnknownFields().hashCode();
		memoizedHashCode = hash;
		return hash;
	}

	public static net.averak.gsync.schema.protobuf.resource.player_storage.Criteria parseFrom(java.nio.ByteBuffer data)
			throws com.google.protobuf.InvalidProtocolBufferException {
		return PARSER.parseFrom(data);
	}
	public static net.averak.gsync.schema.protobuf.resource.player_storage.Criteria parseFrom(java.nio.ByteBuffer data,
			com.google.protobuf.ExtensionRegistryLite extensionRegistry)
			throws com.google.protobuf.InvalidProtocolBufferException {
		return PARSER.parseFrom(data, extensionRegistry);
	}
	public static net.averak.gsync.schema.protobuf.resource.player_storage.Criteria parseFrom(
			com.google.protobuf.ByteString data) throws com.google.protobuf.InvalidProtocolBufferException {
		return PARSER.parseFrom(data);
	}
	public static net.averak.gsync.schema.protobuf.resource.player_storage.Criteria parseFrom(
			com.google.protobuf.ByteString data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
			throws com.google.protobuf.InvalidProtocolBufferException {
		return PARSER.parseFrom(data, extensionRegistry);
	}
	public static net.averak.gsync.schema.protobuf.resource.player_storage.Criteria parseFrom(byte[] data)
			throws com.google.protobuf.InvalidProtocolBufferException {
		return PARSER.parseFrom(data);
	}
	public static net.averak.gsync.schema.protobuf.resource.player_storage.Criteria parseFrom(byte[] data,
			com.google.protobuf.ExtensionRegistryLite extensionRegistry)
			throws com.google.protobuf.InvalidProtocolBufferException {
		return PARSER.parseFrom(data, extensionRegistry);
	}
	public static net.averak.gsync.schema.protobuf.resource.player_storage.Criteria parseFrom(java.io.InputStream input)
			throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
	}
	public static net.averak.gsync.schema.protobuf.resource.player_storage.Criteria parseFrom(java.io.InputStream input,
			com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
	}

	public static net.averak.gsync.schema.protobuf.resource.player_storage.Criteria parseDelimitedFrom(
			java.io.InputStream input) throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
	}

	public static net.averak.gsync.schema.protobuf.resource.player_storage.Criteria parseDelimitedFrom(
			java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
			throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
	}
	public static net.averak.gsync.schema.protobuf.resource.player_storage.Criteria parseFrom(
			com.google.protobuf.CodedInputStream input) throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
	}
	public static net.averak.gsync.schema.protobuf.resource.player_storage.Criteria parseFrom(
			com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
			throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
	}

	@java.lang.Override
	public Builder newBuilderForType() {
		return newBuilder();
	}
	public static Builder newBuilder() {
		return DEFAULT_INSTANCE.toBuilder();
	}
	public static Builder newBuilder(net.averak.gsync.schema.protobuf.resource.player_storage.Criteria prototype) {
		return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
	}
	@java.lang.Override
	public Builder toBuilder() {
		return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
	}

	@java.lang.Override
	protected Builder newBuilderForType(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
		Builder builder = new Builder(parent);
		return builder;
	}
	/**
	 * Protobuf type {@code gsync.resource.player_storage.Criteria}
	 */
	public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder>
			implements
				// @@protoc_insertion_point(builder_implements:gsync.resource.player_storage.Criteria)
				net.averak.gsync.schema.protobuf.resource.player_storage.CriteriaOrBuilder {
		public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
			return net.averak.gsync.schema.protobuf.resource.player_storage.PlayerStorage.internal_static_gsync_resource_player_storage_Criteria_descriptor;
		}

		@java.lang.Override
		protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
			return net.averak.gsync.schema.protobuf.resource.player_storage.PlayerStorage.internal_static_gsync_resource_player_storage_Criteria_fieldAccessorTable
					.ensureFieldAccessorsInitialized(
							net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.class,
							net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.Builder.class);
		}

		// Construct using
		// net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.newBuilder()
		private Builder() {

		}

		private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
			super(parent);

		}
		@java.lang.Override
		public Builder clear() {
			super.clear();
			bitField0_ = 0;
			pattern_ = "";
			matchingType_ = 0;
			return this;
		}

		@java.lang.Override
		public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
			return net.averak.gsync.schema.protobuf.resource.player_storage.PlayerStorage.internal_static_gsync_resource_player_storage_Criteria_descriptor;
		}

		@java.lang.Override
		public net.averak.gsync.schema.protobuf.resource.player_storage.Criteria getDefaultInstanceForType() {
			return net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.getDefaultInstance();
		}

		@java.lang.Override
		public net.averak.gsync.schema.protobuf.resource.player_storage.Criteria build() {
			net.averak.gsync.schema.protobuf.resource.player_storage.Criteria result = buildPartial();
			if (!result.isInitialized()) {
				throw newUninitializedMessageException(result);
			}
			return result;
		}

		@java.lang.Override
		public net.averak.gsync.schema.protobuf.resource.player_storage.Criteria buildPartial() {
			net.averak.gsync.schema.protobuf.resource.player_storage.Criteria result = new net.averak.gsync.schema.protobuf.resource.player_storage.Criteria(
					this);
			if (bitField0_ != 0) {
				buildPartial0(result);
			}
			onBuilt();
			return result;
		}

		private void buildPartial0(net.averak.gsync.schema.protobuf.resource.player_storage.Criteria result) {
			int from_bitField0_ = bitField0_;
			if (((from_bitField0_ & 0x00000001) != 0)) {
				result.pattern_ = pattern_;
			}
			if (((from_bitField0_ & 0x00000002) != 0)) {
				result.matchingType_ = matchingType_;
			}
		}

		@java.lang.Override
		public Builder clone() {
			return super.clone();
		}
		@java.lang.Override
		public Builder setField(com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
			return super.setField(field, value);
		}
		@java.lang.Override
		public Builder clearField(com.google.protobuf.Descriptors.FieldDescriptor field) {
			return super.clearField(field);
		}
		@java.lang.Override
		public Builder clearOneof(com.google.protobuf.Descriptors.OneofDescriptor oneof) {
			return super.clearOneof(oneof);
		}
		@java.lang.Override
		public Builder setRepeatedField(com.google.protobuf.Descriptors.FieldDescriptor field, int index,
				java.lang.Object value) {
			return super.setRepeatedField(field, index, value);
		}
		@java.lang.Override
		public Builder addRepeatedField(com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
			return super.addRepeatedField(field, value);
		}
		@java.lang.Override
		public Builder mergeFrom(com.google.protobuf.Message other) {
			if (other instanceof net.averak.gsync.schema.protobuf.resource.player_storage.Criteria) {
				return mergeFrom((net.averak.gsync.schema.protobuf.resource.player_storage.Criteria) other);
			} else {
				super.mergeFrom(other);
				return this;
			}
		}

		public Builder mergeFrom(net.averak.gsync.schema.protobuf.resource.player_storage.Criteria other) {
			if (other == net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.getDefaultInstance())
				return this;
			if (!other.getPattern().isEmpty()) {
				pattern_ = other.pattern_;
				bitField0_ |= 0x00000001;
				onChanged();
			}
			if (other.matchingType_ != 0) {
				setMatchingTypeValue(other.getMatchingTypeValue());
			}
			this.mergeUnknownFields(other.getUnknownFields());
			onChanged();
			return this;
		}

		@java.lang.Override
		public final boolean isInitialized() {
			return true;
		}

		@java.lang.Override
		public Builder mergeFrom(com.google.protobuf.CodedInputStream input,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
			if (extensionRegistry == null) {
				throw new java.lang.NullPointerException();
			}
			try {
				boolean done = false;
				while (!done) {
					int tag = input.readTag();
					switch (tag) {
						case 0 :
							done = true;
							break;
						case 10 : {
							pattern_ = input.readStringRequireUtf8();
							bitField0_ |= 0x00000001;
							break;
						} // case 10
						case 16 : {
							matchingType_ = input.readEnum();
							bitField0_ |= 0x00000002;
							break;
						} // case 16
						default : {
							if (!super.parseUnknownField(input, extensionRegistry, tag)) {
								done = true; // was an endgroup tag
							}
							break;
						} // default:
					} // switch (tag)
				} // while (!done)
			} catch (com.google.protobuf.InvalidProtocolBufferException e) {
				throw e.unwrapIOException();
			} finally {
				onChanged();
			} // finally
			return this;
		}
		private int bitField0_;

		private java.lang.Object pattern_ = "";
		/**
		 * <code>string pattern = 1;</code>
		 * 
		 * @return The pattern.
		 */
		public java.lang.String getPattern() {
			java.lang.Object ref = pattern_;
			if (!(ref instanceof java.lang.String)) {
				com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
				java.lang.String s = bs.toStringUtf8();
				pattern_ = s;
				return s;
			} else {
				return (java.lang.String) ref;
			}
		}
		/**
		 * <code>string pattern = 1;</code>
		 * 
		 * @return The bytes for pattern.
		 */
		public com.google.protobuf.ByteString getPatternBytes() {
			java.lang.Object ref = pattern_;
			if (ref instanceof String) {
				com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
				pattern_ = b;
				return b;
			} else {
				return (com.google.protobuf.ByteString) ref;
			}
		}
		/**
		 * <code>string pattern = 1;</code>
		 * 
		 * @param value
		 *            The pattern to set.
		 * @return This builder for chaining.
		 */
		public Builder setPattern(java.lang.String value) {
			if (value == null) {
				throw new NullPointerException();
			}
			pattern_ = value;
			bitField0_ |= 0x00000001;
			onChanged();
			return this;
		}
		/**
		 * <code>string pattern = 1;</code>
		 * 
		 * @return This builder for chaining.
		 */
		public Builder clearPattern() {
			pattern_ = getDefaultInstance().getPattern();
			bitField0_ = (bitField0_ & ~0x00000001);
			onChanged();
			return this;
		}
		/**
		 * <code>string pattern = 1;</code>
		 * 
		 * @param value
		 *            The bytes for pattern to set.
		 * @return This builder for chaining.
		 */
		public Builder setPatternBytes(com.google.protobuf.ByteString value) {
			if (value == null) {
				throw new NullPointerException();
			}
			checkByteStringIsUtf8(value);
			pattern_ = value;
			bitField0_ |= 0x00000001;
			onChanged();
			return this;
		}

		private int matchingType_ = 0;
		/**
		 * <code>.gsync.resource.player_storage.Criteria.MatchingType matching_type = 2;</code>
		 * 
		 * @return The enum numeric value on the wire for matchingType.
		 */
		@java.lang.Override
		public int getMatchingTypeValue() {
			return matchingType_;
		}
		/**
		 * <code>.gsync.resource.player_storage.Criteria.MatchingType matching_type = 2;</code>
		 * 
		 * @param value
		 *            The enum numeric value on the wire for matchingType to set.
		 * @return This builder for chaining.
		 */
		public Builder setMatchingTypeValue(int value) {
			matchingType_ = value;
			bitField0_ |= 0x00000002;
			onChanged();
			return this;
		}
		/**
		 * <code>.gsync.resource.player_storage.Criteria.MatchingType matching_type = 2;</code>
		 * 
		 * @return The matchingType.
		 */
		@java.lang.Override
		public net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.MatchingType getMatchingType() {
			net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.MatchingType result = net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.MatchingType
					.forNumber(matchingType_);
			return result == null
					? net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.MatchingType.UNRECOGNIZED
					: result;
		}
		/**
		 * <code>.gsync.resource.player_storage.Criteria.MatchingType matching_type = 2;</code>
		 * 
		 * @param value
		 *            The matchingType to set.
		 * @return This builder for chaining.
		 */
		public Builder setMatchingType(
				net.averak.gsync.schema.protobuf.resource.player_storage.Criteria.MatchingType value) {
			if (value == null) {
				throw new NullPointerException();
			}
			bitField0_ |= 0x00000002;
			matchingType_ = value.getNumber();
			onChanged();
			return this;
		}
		/**
		 * <code>.gsync.resource.player_storage.Criteria.MatchingType matching_type = 2;</code>
		 * 
		 * @return This builder for chaining.
		 */
		public Builder clearMatchingType() {
			bitField0_ = (bitField0_ & ~0x00000002);
			matchingType_ = 0;
			onChanged();
			return this;
		}
		@java.lang.Override
		public final Builder setUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
			return super.setUnknownFields(unknownFields);
		}

		@java.lang.Override
		public final Builder mergeUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
			return super.mergeUnknownFields(unknownFields);
		}

		// @@protoc_insertion_point(builder_scope:gsync.resource.player_storage.Criteria)
	}

	// @@protoc_insertion_point(class_scope:gsync.resource.player_storage.Criteria)
	private static final net.averak.gsync.schema.protobuf.resource.player_storage.Criteria DEFAULT_INSTANCE;
	static {
		DEFAULT_INSTANCE = new net.averak.gsync.schema.protobuf.resource.player_storage.Criteria();
	}

	public static net.averak.gsync.schema.protobuf.resource.player_storage.Criteria getDefaultInstance() {
		return DEFAULT_INSTANCE;
	}

	private static final com.google.protobuf.Parser<Criteria> PARSER = new com.google.protobuf.AbstractParser<Criteria>() {
		@java.lang.Override
		public Criteria parsePartialFrom(com.google.protobuf.CodedInputStream input,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws com.google.protobuf.InvalidProtocolBufferException {
			Builder builder = newBuilder();
			try {
				builder.mergeFrom(input, extensionRegistry);
			} catch (com.google.protobuf.InvalidProtocolBufferException e) {
				throw e.setUnfinishedMessage(builder.buildPartial());
			} catch (com.google.protobuf.UninitializedMessageException e) {
				throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
			} catch (java.io.IOException e) {
				throw new com.google.protobuf.InvalidProtocolBufferException(e)
						.setUnfinishedMessage(builder.buildPartial());
			}
			return builder.buildPartial();
		}
	};

	public static com.google.protobuf.Parser<Criteria> parser() {
		return PARSER;
	}

	@java.lang.Override
	public com.google.protobuf.Parser<Criteria> getParserForType() {
		return PARSER;
	}

	@java.lang.Override
	public net.averak.gsync.schema.protobuf.resource.player_storage.Criteria getDefaultInstanceForType() {
		return DEFAULT_INSTANCE;
	}

}
