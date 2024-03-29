// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: resource/player.proto

// Protobuf Java Version: 3.25.2
package net.averak.gsync.schema.protobuf.resource.player;

/**
 * Protobuf type {@code gsync.resource.player.PlayerProfile}
 */
public final class PlayerProfile extends com.google.protobuf.GeneratedMessageV3
		implements
			// @@protoc_insertion_point(message_implements:gsync.resource.player.PlayerProfile)
			PlayerProfileOrBuilder {
	private static final long serialVersionUID = 0L;
	// Use PlayerProfile.newBuilder() to construct.
	private PlayerProfile(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
		super(builder);
	}
	private PlayerProfile() {
		nickname_ = "";
		iconId_ = "";
	}

	@java.lang.Override
	@SuppressWarnings({"unused"})
	protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
		return new PlayerProfile();
	}

	public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
		return net.averak.gsync.schema.protobuf.resource.player.Player.internal_static_gsync_resource_player_PlayerProfile_descriptor;
	}

	@java.lang.Override
	protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
		return net.averak.gsync.schema.protobuf.resource.player.Player.internal_static_gsync_resource_player_PlayerProfile_fieldAccessorTable
				.ensureFieldAccessorsInitialized(net.averak.gsync.schema.protobuf.resource.player.PlayerProfile.class,
						net.averak.gsync.schema.protobuf.resource.player.PlayerProfile.Builder.class);
	}

	public static final int NICKNAME_FIELD_NUMBER = 1;
	@SuppressWarnings("serial")
	private volatile java.lang.Object nickname_ = "";
	/**
	 * <code>string nickname = 1;</code>
	 * 
	 * @return The nickname.
	 */
	@java.lang.Override
	public java.lang.String getNickname() {
		java.lang.Object ref = nickname_;
		if (ref instanceof java.lang.String) {
			return (java.lang.String) ref;
		} else {
			com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
			java.lang.String s = bs.toStringUtf8();
			nickname_ = s;
			return s;
		}
	}
	/**
	 * <code>string nickname = 1;</code>
	 * 
	 * @return The bytes for nickname.
	 */
	@java.lang.Override
	public com.google.protobuf.ByteString getNicknameBytes() {
		java.lang.Object ref = nickname_;
		if (ref instanceof java.lang.String) {
			com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
			nickname_ = b;
			return b;
		} else {
			return (com.google.protobuf.ByteString) ref;
		}
	}

	public static final int ICON_ID_FIELD_NUMBER = 2;
	@SuppressWarnings("serial")
	private volatile java.lang.Object iconId_ = "";
	/**
	 * <code>string icon_id = 2;</code>
	 * 
	 * @return The iconId.
	 */
	@java.lang.Override
	public java.lang.String getIconId() {
		java.lang.Object ref = iconId_;
		if (ref instanceof java.lang.String) {
			return (java.lang.String) ref;
		} else {
			com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
			java.lang.String s = bs.toStringUtf8();
			iconId_ = s;
			return s;
		}
	}
	/**
	 * <code>string icon_id = 2;</code>
	 * 
	 * @return The bytes for iconId.
	 */
	@java.lang.Override
	public com.google.protobuf.ByteString getIconIdBytes() {
		java.lang.Object ref = iconId_;
		if (ref instanceof java.lang.String) {
			com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
			iconId_ = b;
			return b;
		} else {
			return (com.google.protobuf.ByteString) ref;
		}
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
		if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(nickname_)) {
			com.google.protobuf.GeneratedMessageV3.writeString(output, 1, nickname_);
		}
		if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(iconId_)) {
			com.google.protobuf.GeneratedMessageV3.writeString(output, 2, iconId_);
		}
		getUnknownFields().writeTo(output);
	}

	@java.lang.Override
	public int getSerializedSize() {
		int size = memoizedSize;
		if (size != -1)
			return size;

		size = 0;
		if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(nickname_)) {
			size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, nickname_);
		}
		if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(iconId_)) {
			size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, iconId_);
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
		if (!(obj instanceof net.averak.gsync.schema.protobuf.resource.player.PlayerProfile)) {
			return super.equals(obj);
		}
		net.averak.gsync.schema.protobuf.resource.player.PlayerProfile other = (net.averak.gsync.schema.protobuf.resource.player.PlayerProfile) obj;

		if (!getNickname().equals(other.getNickname()))
			return false;
		if (!getIconId().equals(other.getIconId()))
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
		hash = (37 * hash) + NICKNAME_FIELD_NUMBER;
		hash = (53 * hash) + getNickname().hashCode();
		hash = (37 * hash) + ICON_ID_FIELD_NUMBER;
		hash = (53 * hash) + getIconId().hashCode();
		hash = (29 * hash) + getUnknownFields().hashCode();
		memoizedHashCode = hash;
		return hash;
	}

	public static net.averak.gsync.schema.protobuf.resource.player.PlayerProfile parseFrom(java.nio.ByteBuffer data)
			throws com.google.protobuf.InvalidProtocolBufferException {
		return PARSER.parseFrom(data);
	}
	public static net.averak.gsync.schema.protobuf.resource.player.PlayerProfile parseFrom(java.nio.ByteBuffer data,
			com.google.protobuf.ExtensionRegistryLite extensionRegistry)
			throws com.google.protobuf.InvalidProtocolBufferException {
		return PARSER.parseFrom(data, extensionRegistry);
	}
	public static net.averak.gsync.schema.protobuf.resource.player.PlayerProfile parseFrom(
			com.google.protobuf.ByteString data) throws com.google.protobuf.InvalidProtocolBufferException {
		return PARSER.parseFrom(data);
	}
	public static net.averak.gsync.schema.protobuf.resource.player.PlayerProfile parseFrom(
			com.google.protobuf.ByteString data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
			throws com.google.protobuf.InvalidProtocolBufferException {
		return PARSER.parseFrom(data, extensionRegistry);
	}
	public static net.averak.gsync.schema.protobuf.resource.player.PlayerProfile parseFrom(byte[] data)
			throws com.google.protobuf.InvalidProtocolBufferException {
		return PARSER.parseFrom(data);
	}
	public static net.averak.gsync.schema.protobuf.resource.player.PlayerProfile parseFrom(byte[] data,
			com.google.protobuf.ExtensionRegistryLite extensionRegistry)
			throws com.google.protobuf.InvalidProtocolBufferException {
		return PARSER.parseFrom(data, extensionRegistry);
	}
	public static net.averak.gsync.schema.protobuf.resource.player.PlayerProfile parseFrom(java.io.InputStream input)
			throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
	}
	public static net.averak.gsync.schema.protobuf.resource.player.PlayerProfile parseFrom(java.io.InputStream input,
			com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
	}

	public static net.averak.gsync.schema.protobuf.resource.player.PlayerProfile parseDelimitedFrom(
			java.io.InputStream input) throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
	}

	public static net.averak.gsync.schema.protobuf.resource.player.PlayerProfile parseDelimitedFrom(
			java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
			throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
	}
	public static net.averak.gsync.schema.protobuf.resource.player.PlayerProfile parseFrom(
			com.google.protobuf.CodedInputStream input) throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
	}
	public static net.averak.gsync.schema.protobuf.resource.player.PlayerProfile parseFrom(
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
	public static Builder newBuilder(net.averak.gsync.schema.protobuf.resource.player.PlayerProfile prototype) {
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
	 * Protobuf type {@code gsync.resource.player.PlayerProfile}
	 */
	public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder>
			implements
				// @@protoc_insertion_point(builder_implements:gsync.resource.player.PlayerProfile)
				net.averak.gsync.schema.protobuf.resource.player.PlayerProfileOrBuilder {
		public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
			return net.averak.gsync.schema.protobuf.resource.player.Player.internal_static_gsync_resource_player_PlayerProfile_descriptor;
		}

		@java.lang.Override
		protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
			return net.averak.gsync.schema.protobuf.resource.player.Player.internal_static_gsync_resource_player_PlayerProfile_fieldAccessorTable
					.ensureFieldAccessorsInitialized(
							net.averak.gsync.schema.protobuf.resource.player.PlayerProfile.class,
							net.averak.gsync.schema.protobuf.resource.player.PlayerProfile.Builder.class);
		}

		// Construct using
		// net.averak.gsync.schema.protobuf.resource.player.PlayerProfile.newBuilder()
		private Builder() {

		}

		private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
			super(parent);

		}
		@java.lang.Override
		public Builder clear() {
			super.clear();
			bitField0_ = 0;
			nickname_ = "";
			iconId_ = "";
			return this;
		}

		@java.lang.Override
		public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
			return net.averak.gsync.schema.protobuf.resource.player.Player.internal_static_gsync_resource_player_PlayerProfile_descriptor;
		}

		@java.lang.Override
		public net.averak.gsync.schema.protobuf.resource.player.PlayerProfile getDefaultInstanceForType() {
			return net.averak.gsync.schema.protobuf.resource.player.PlayerProfile.getDefaultInstance();
		}

		@java.lang.Override
		public net.averak.gsync.schema.protobuf.resource.player.PlayerProfile build() {
			net.averak.gsync.schema.protobuf.resource.player.PlayerProfile result = buildPartial();
			if (!result.isInitialized()) {
				throw newUninitializedMessageException(result);
			}
			return result;
		}

		@java.lang.Override
		public net.averak.gsync.schema.protobuf.resource.player.PlayerProfile buildPartial() {
			net.averak.gsync.schema.protobuf.resource.player.PlayerProfile result = new net.averak.gsync.schema.protobuf.resource.player.PlayerProfile(
					this);
			if (bitField0_ != 0) {
				buildPartial0(result);
			}
			onBuilt();
			return result;
		}

		private void buildPartial0(net.averak.gsync.schema.protobuf.resource.player.PlayerProfile result) {
			int from_bitField0_ = bitField0_;
			if (((from_bitField0_ & 0x00000001) != 0)) {
				result.nickname_ = nickname_;
			}
			if (((from_bitField0_ & 0x00000002) != 0)) {
				result.iconId_ = iconId_;
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
			if (other instanceof net.averak.gsync.schema.protobuf.resource.player.PlayerProfile) {
				return mergeFrom((net.averak.gsync.schema.protobuf.resource.player.PlayerProfile) other);
			} else {
				super.mergeFrom(other);
				return this;
			}
		}

		public Builder mergeFrom(net.averak.gsync.schema.protobuf.resource.player.PlayerProfile other) {
			if (other == net.averak.gsync.schema.protobuf.resource.player.PlayerProfile.getDefaultInstance())
				return this;
			if (!other.getNickname().isEmpty()) {
				nickname_ = other.nickname_;
				bitField0_ |= 0x00000001;
				onChanged();
			}
			if (!other.getIconId().isEmpty()) {
				iconId_ = other.iconId_;
				bitField0_ |= 0x00000002;
				onChanged();
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
							nickname_ = input.readStringRequireUtf8();
							bitField0_ |= 0x00000001;
							break;
						} // case 10
						case 18 : {
							iconId_ = input.readStringRequireUtf8();
							bitField0_ |= 0x00000002;
							break;
						} // case 18
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

		private java.lang.Object nickname_ = "";
		/**
		 * <code>string nickname = 1;</code>
		 * 
		 * @return The nickname.
		 */
		public java.lang.String getNickname() {
			java.lang.Object ref = nickname_;
			if (!(ref instanceof java.lang.String)) {
				com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
				java.lang.String s = bs.toStringUtf8();
				nickname_ = s;
				return s;
			} else {
				return (java.lang.String) ref;
			}
		}
		/**
		 * <code>string nickname = 1;</code>
		 * 
		 * @return The bytes for nickname.
		 */
		public com.google.protobuf.ByteString getNicknameBytes() {
			java.lang.Object ref = nickname_;
			if (ref instanceof String) {
				com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
				nickname_ = b;
				return b;
			} else {
				return (com.google.protobuf.ByteString) ref;
			}
		}
		/**
		 * <code>string nickname = 1;</code>
		 * 
		 * @param value
		 *            The nickname to set.
		 * @return This builder for chaining.
		 */
		public Builder setNickname(java.lang.String value) {
			if (value == null) {
				throw new NullPointerException();
			}
			nickname_ = value;
			bitField0_ |= 0x00000001;
			onChanged();
			return this;
		}
		/**
		 * <code>string nickname = 1;</code>
		 * 
		 * @return This builder for chaining.
		 */
		public Builder clearNickname() {
			nickname_ = getDefaultInstance().getNickname();
			bitField0_ = (bitField0_ & ~0x00000001);
			onChanged();
			return this;
		}
		/**
		 * <code>string nickname = 1;</code>
		 * 
		 * @param value
		 *            The bytes for nickname to set.
		 * @return This builder for chaining.
		 */
		public Builder setNicknameBytes(com.google.protobuf.ByteString value) {
			if (value == null) {
				throw new NullPointerException();
			}
			checkByteStringIsUtf8(value);
			nickname_ = value;
			bitField0_ |= 0x00000001;
			onChanged();
			return this;
		}

		private java.lang.Object iconId_ = "";
		/**
		 * <code>string icon_id = 2;</code>
		 * 
		 * @return The iconId.
		 */
		public java.lang.String getIconId() {
			java.lang.Object ref = iconId_;
			if (!(ref instanceof java.lang.String)) {
				com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
				java.lang.String s = bs.toStringUtf8();
				iconId_ = s;
				return s;
			} else {
				return (java.lang.String) ref;
			}
		}
		/**
		 * <code>string icon_id = 2;</code>
		 * 
		 * @return The bytes for iconId.
		 */
		public com.google.protobuf.ByteString getIconIdBytes() {
			java.lang.Object ref = iconId_;
			if (ref instanceof String) {
				com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
				iconId_ = b;
				return b;
			} else {
				return (com.google.protobuf.ByteString) ref;
			}
		}
		/**
		 * <code>string icon_id = 2;</code>
		 * 
		 * @param value
		 *            The iconId to set.
		 * @return This builder for chaining.
		 */
		public Builder setIconId(java.lang.String value) {
			if (value == null) {
				throw new NullPointerException();
			}
			iconId_ = value;
			bitField0_ |= 0x00000002;
			onChanged();
			return this;
		}
		/**
		 * <code>string icon_id = 2;</code>
		 * 
		 * @return This builder for chaining.
		 */
		public Builder clearIconId() {
			iconId_ = getDefaultInstance().getIconId();
			bitField0_ = (bitField0_ & ~0x00000002);
			onChanged();
			return this;
		}
		/**
		 * <code>string icon_id = 2;</code>
		 * 
		 * @param value
		 *            The bytes for iconId to set.
		 * @return This builder for chaining.
		 */
		public Builder setIconIdBytes(com.google.protobuf.ByteString value) {
			if (value == null) {
				throw new NullPointerException();
			}
			checkByteStringIsUtf8(value);
			iconId_ = value;
			bitField0_ |= 0x00000002;
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

		// @@protoc_insertion_point(builder_scope:gsync.resource.player.PlayerProfile)
	}

	// @@protoc_insertion_point(class_scope:gsync.resource.player.PlayerProfile)
	private static final net.averak.gsync.schema.protobuf.resource.player.PlayerProfile DEFAULT_INSTANCE;
	static {
		DEFAULT_INSTANCE = new net.averak.gsync.schema.protobuf.resource.player.PlayerProfile();
	}

	public static net.averak.gsync.schema.protobuf.resource.player.PlayerProfile getDefaultInstance() {
		return DEFAULT_INSTANCE;
	}

	private static final com.google.protobuf.Parser<PlayerProfile> PARSER = new com.google.protobuf.AbstractParser<PlayerProfile>() {
		@java.lang.Override
		public PlayerProfile parsePartialFrom(com.google.protobuf.CodedInputStream input,
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

	public static com.google.protobuf.Parser<PlayerProfile> parser() {
		return PARSER;
	}

	@java.lang.Override
	public com.google.protobuf.Parser<PlayerProfile> getParserForType() {
		return PARSER;
	}

	@java.lang.Override
	public net.averak.gsync.schema.protobuf.resource.player.PlayerProfile getDefaultInstanceForType() {
		return DEFAULT_INSTANCE;
	}

}
