// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: player_api/player_storage.proto

// Protobuf Java Version: 3.25.1
package net.averak.gsync.schema.protobuf.player_api;

public final class PlayerStorageOuterClass {
	private PlayerStorageOuterClass() {
	}
	public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
	}

	public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
		registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
	}
	static final com.google.protobuf.Descriptors.Descriptor internal_static_gsync_player_api_PlayerStorageSearchV1_descriptor;
	static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_gsync_player_api_PlayerStorageSearchV1_fieldAccessorTable;
	static final com.google.protobuf.Descriptors.Descriptor internal_static_gsync_player_api_PlayerStorageSearchV1_Request_descriptor;
	static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_gsync_player_api_PlayerStorageSearchV1_Request_fieldAccessorTable;
	static final com.google.protobuf.Descriptors.Descriptor internal_static_gsync_player_api_PlayerStorageSearchV1_Response_descriptor;
	static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_gsync_player_api_PlayerStorageSearchV1_Response_fieldAccessorTable;
	static final com.google.protobuf.Descriptors.Descriptor internal_static_gsync_player_api_PlayerStorageSetV1_descriptor;
	static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_gsync_player_api_PlayerStorageSetV1_fieldAccessorTable;
	static final com.google.protobuf.Descriptors.Descriptor internal_static_gsync_player_api_PlayerStorageSetV1_Request_descriptor;
	static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_gsync_player_api_PlayerStorageSetV1_Request_fieldAccessorTable;
	static final com.google.protobuf.Descriptors.Descriptor internal_static_gsync_player_api_PlayerStorageSetV1_Response_descriptor;
	static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_gsync_player_api_PlayerStorageSetV1_Response_fieldAccessorTable;
	static final com.google.protobuf.Descriptors.Descriptor internal_static_gsync_player_api_PlayerStorageClearV1_descriptor;
	static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_gsync_player_api_PlayerStorageClearV1_fieldAccessorTable;
	static final com.google.protobuf.Descriptors.Descriptor internal_static_gsync_player_api_PlayerStorageClearV1_Request_descriptor;
	static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_gsync_player_api_PlayerStorageClearV1_Request_fieldAccessorTable;
	static final com.google.protobuf.Descriptors.Descriptor internal_static_gsync_player_api_PlayerStorageClearV1_Response_descriptor;
	static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_gsync_player_api_PlayerStorageClearV1_Response_fieldAccessorTable;

	public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
		return descriptor;
	}
	private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
	static {
		java.lang.String[] descriptorData = {"\n\037player_api/player_storage.proto\022\020gsync"
				+ ".player_api\032\035resource/player_storage.pro"
				+ "to\"\240\001\n\025PlayerStorageSearchV1\032D\n\007Request\022"
				+ "9\n\010criteria\030\001 \001(\0132\'.gsync.resource.playe"
				+ "r_storage.Criteria\032A\n\010Response\0225\n\007entrie"
				+ "s\030\001 \003(\0132$.gsync.resource.player_storage."
				+ "Entry\"\307\001\n\022PlayerStorageSetV1\032Y\n\007Request\022"
				+ "3\n\005entry\030\001 \001(\0132$.gsync.resource.player_s"
				+ "torage.Entry\022\031\n\021previous_revision\030\002 \001(\t\032"
				+ "V\n\010Response\0223\n\005entry\030\001 \001(\0132$.gsync.resou"
				+ "rce.player_storage.Entry\022\025\n\rnext_revisio"
				+ "n\030\002 \001(\t\"\232\001\n\024PlayerStorageClearV1\032_\n\007Requ"
				+ "est\0229\n\010criteria\030\001 \001(\0132\'.gsync.resource.p"
				+ "layer_storage.Criteria\022\031\n\021previous_revis"
				+ "ion\030\002 \001(\t\032!\n\010Response\022\025\n\rnext_revision\030\001"
				+ " \001(\t2\320\002\n\rPlayerStorage\022m\n\010SearchV1\022/.gsy"
				+ "nc.player_api.PlayerStorageSearchV1.Requ" + "est\0320.gsync.player_api.PlayerStorageSear"
				+ "chV1.Response\022d\n\005SetV1\022,.gsync.player_ap" + "i.PlayerStorageSetV1.Request\032-.gsync.pla"
				+ "yer_api.PlayerStorageSetV1.Response\022j\n\007C" + "learV1\022..gsync.player_api.PlayerStorageC"
				+ "learV1.Request\032/.gsync.player_api.Player" + "StorageClearV1.ResponseB/\n+net.averak.gs"
				+ "ync.schema.protobuf.player_apiP\001b\006proto3"};
		descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData,
				new com.google.protobuf.Descriptors.FileDescriptor[]{
						net.averak.gsync.schema.protobuf.resource.player_storage.PlayerStorage.getDescriptor(),});
		internal_static_gsync_player_api_PlayerStorageSearchV1_descriptor = getDescriptor().getMessageTypes().get(0);
		internal_static_gsync_player_api_PlayerStorageSearchV1_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
				internal_static_gsync_player_api_PlayerStorageSearchV1_descriptor, new java.lang.String[]{});
		internal_static_gsync_player_api_PlayerStorageSearchV1_Request_descriptor = internal_static_gsync_player_api_PlayerStorageSearchV1_descriptor
				.getNestedTypes().get(0);
		internal_static_gsync_player_api_PlayerStorageSearchV1_Request_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
				internal_static_gsync_player_api_PlayerStorageSearchV1_Request_descriptor,
				new java.lang.String[]{"Criteria",});
		internal_static_gsync_player_api_PlayerStorageSearchV1_Response_descriptor = internal_static_gsync_player_api_PlayerStorageSearchV1_descriptor
				.getNestedTypes().get(1);
		internal_static_gsync_player_api_PlayerStorageSearchV1_Response_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
				internal_static_gsync_player_api_PlayerStorageSearchV1_Response_descriptor,
				new java.lang.String[]{"Entries",});
		internal_static_gsync_player_api_PlayerStorageSetV1_descriptor = getDescriptor().getMessageTypes().get(1);
		internal_static_gsync_player_api_PlayerStorageSetV1_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
				internal_static_gsync_player_api_PlayerStorageSetV1_descriptor, new java.lang.String[]{});
		internal_static_gsync_player_api_PlayerStorageSetV1_Request_descriptor = internal_static_gsync_player_api_PlayerStorageSetV1_descriptor
				.getNestedTypes().get(0);
		internal_static_gsync_player_api_PlayerStorageSetV1_Request_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
				internal_static_gsync_player_api_PlayerStorageSetV1_Request_descriptor,
				new java.lang.String[]{"Entry", "PreviousRevision",});
		internal_static_gsync_player_api_PlayerStorageSetV1_Response_descriptor = internal_static_gsync_player_api_PlayerStorageSetV1_descriptor
				.getNestedTypes().get(1);
		internal_static_gsync_player_api_PlayerStorageSetV1_Response_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
				internal_static_gsync_player_api_PlayerStorageSetV1_Response_descriptor,
				new java.lang.String[]{"Entry", "NextRevision",});
		internal_static_gsync_player_api_PlayerStorageClearV1_descriptor = getDescriptor().getMessageTypes().get(2);
		internal_static_gsync_player_api_PlayerStorageClearV1_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
				internal_static_gsync_player_api_PlayerStorageClearV1_descriptor, new java.lang.String[]{});
		internal_static_gsync_player_api_PlayerStorageClearV1_Request_descriptor = internal_static_gsync_player_api_PlayerStorageClearV1_descriptor
				.getNestedTypes().get(0);
		internal_static_gsync_player_api_PlayerStorageClearV1_Request_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
				internal_static_gsync_player_api_PlayerStorageClearV1_Request_descriptor,
				new java.lang.String[]{"Criteria", "PreviousRevision",});
		internal_static_gsync_player_api_PlayerStorageClearV1_Response_descriptor = internal_static_gsync_player_api_PlayerStorageClearV1_descriptor
				.getNestedTypes().get(1);
		internal_static_gsync_player_api_PlayerStorageClearV1_Response_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
				internal_static_gsync_player_api_PlayerStorageClearV1_Response_descriptor,
				new java.lang.String[]{"NextRevision",});
		net.averak.gsync.schema.protobuf.resource.player_storage.PlayerStorage.getDescriptor();
	}

	// @@protoc_insertion_point(outer_class_scope)
}
