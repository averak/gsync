// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: custom_option.proto

// Protobuf Java Version: 3.25.2
package net.averak.gsync.schema.protobuf.custom_option;

public final class CustomOption {
	private CustomOption() {
	}
	public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
		registry.add(net.averak.gsync.schema.protobuf.custom_option.CustomOption.playerApiMethodOption);
	}

	public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
		registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
	}
	public static final int PLAYER_API_METHOD_OPTION_FIELD_NUMBER = 20001;
	/**
	 * <code>extend .google.protobuf.MethodOptions { ... }</code>
	 */
	public static final com.google.protobuf.GeneratedMessage.GeneratedExtension<com.google.protobuf.DescriptorProtos.MethodOptions, net.averak.gsync.schema.protobuf.custom_option.PlayerApiMethodOption> playerApiMethodOption = com.google.protobuf.GeneratedMessage
			.newFileScopedGeneratedExtension(net.averak.gsync.schema.protobuf.custom_option.PlayerApiMethodOption.class,
					net.averak.gsync.schema.protobuf.custom_option.PlayerApiMethodOption.getDefaultInstance());
	static final com.google.protobuf.Descriptors.Descriptor internal_static_gsync_custom_option_PlayerApiMethodOption_descriptor;
	static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_gsync_custom_option_PlayerApiMethodOption_fieldAccessorTable;
	static final com.google.protobuf.Descriptors.Descriptor internal_static_gsync_custom_option_PlayerApiMethodOption_MethodErrorDefinition_descriptor;
	static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_gsync_custom_option_PlayerApiMethodOption_MethodErrorDefinition_fieldAccessorTable;

	public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
		return descriptor;
	}
	private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
	static {
		java.lang.String[] descriptorData = {
				"\n\023custom_option.proto\022\023gsync.custom_opti" + "on\032!player_api/error/error_code.proto\032 g"
						+ "oogle/protobuf/descriptor.proto\"\335\001\n\025Play"
						+ "erApiMethodOption\022b\n\030method_error_defini"
						+ "tions\030\001 \003(\0132@.gsync.custom_option.Player"
						+ "ApiMethodOption.MethodErrorDefinition\032`\n"
						+ "\025MethodErrorDefinition\0226\n\004code\030\001 \001(\0162(.g"
						+ "sync.player_api.error.ErrorCode.Method\022\017"
						+ "\n\007message\030\002 \001(\t:n\n\030player_api_method_opt"
						+ "ion\022\036.google.protobuf.MethodOptions\030\241\234\001 "
						+ "\001(\0132*.gsync.custom_option.PlayerApiMetho" + "dOptionB2\n.net.averak.gsync.schema.proto"
						+ "buf.custom_optionP\001b\006proto3"};
		descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData,
				new com.google.protobuf.Descriptors.FileDescriptor[]{
						net.averak.gsync.schema.protobuf.player_api.error.ErrorCodeOuterClass.getDescriptor(),
						com.google.protobuf.DescriptorProtos.getDescriptor(),});
		internal_static_gsync_custom_option_PlayerApiMethodOption_descriptor = getDescriptor().getMessageTypes().get(0);
		internal_static_gsync_custom_option_PlayerApiMethodOption_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
				internal_static_gsync_custom_option_PlayerApiMethodOption_descriptor,
				new java.lang.String[]{"MethodErrorDefinitions",});
		internal_static_gsync_custom_option_PlayerApiMethodOption_MethodErrorDefinition_descriptor = internal_static_gsync_custom_option_PlayerApiMethodOption_descriptor
				.getNestedTypes().get(0);
		internal_static_gsync_custom_option_PlayerApiMethodOption_MethodErrorDefinition_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
				internal_static_gsync_custom_option_PlayerApiMethodOption_MethodErrorDefinition_descriptor,
				new java.lang.String[]{"Code", "Message",});
		playerApiMethodOption.internalInit(descriptor.getExtensions().get(0));
		net.averak.gsync.schema.protobuf.player_api.error.ErrorCodeOuterClass.getDescriptor();
		com.google.protobuf.DescriptorProtos.getDescriptor();
	}

	// @@protoc_insertion_point(outer_class_scope)
}
