package net.averak.gsync.schema.protobuf.player_api;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.61.0)", comments = "Source: player_api/player_storage.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class PlayerStorageGrpc {

	private PlayerStorageGrpc() {
	}

	public static final java.lang.String SERVICE_NAME = "gsync.player_api.PlayerStorage";

	// Static method descriptors that strictly reflect the proto.
	private static volatile io.grpc.MethodDescriptor<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Request, net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Response> getSearchV1Method;

	@io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
			+ "SearchV1", requestType = net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Request.class, responseType = net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Response.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
	public static io.grpc.MethodDescriptor<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Request, net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Response> getSearchV1Method() {
		io.grpc.MethodDescriptor<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Request, net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Response> getSearchV1Method;
		if ((getSearchV1Method = PlayerStorageGrpc.getSearchV1Method) == null) {
			synchronized (PlayerStorageGrpc.class) {
				if ((getSearchV1Method = PlayerStorageGrpc.getSearchV1Method) == null) {
					PlayerStorageGrpc.getSearchV1Method = getSearchV1Method = io.grpc.MethodDescriptor.<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Request, net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Response>newBuilder()
							.setType(io.grpc.MethodDescriptor.MethodType.UNARY)
							.setFullMethodName(generateFullMethodName(SERVICE_NAME, "SearchV1"))
							.setSampledToLocalTracing(true)
							.setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
									net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Request
											.getDefaultInstance()))
							.setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
									net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Response
											.getDefaultInstance()))
							.setSchemaDescriptor(new PlayerStorageMethodDescriptorSupplier("SearchV1")).build();
				}
			}
		}
		return getSearchV1Method;
	}

	private static volatile io.grpc.MethodDescriptor<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Request, net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Response> getSetV1Method;

	@io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
			+ "SetV1", requestType = net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Request.class, responseType = net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Response.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
	public static io.grpc.MethodDescriptor<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Request, net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Response> getSetV1Method() {
		io.grpc.MethodDescriptor<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Request, net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Response> getSetV1Method;
		if ((getSetV1Method = PlayerStorageGrpc.getSetV1Method) == null) {
			synchronized (PlayerStorageGrpc.class) {
				if ((getSetV1Method = PlayerStorageGrpc.getSetV1Method) == null) {
					PlayerStorageGrpc.getSetV1Method = getSetV1Method = io.grpc.MethodDescriptor.<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Request, net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Response>newBuilder()
							.setType(io.grpc.MethodDescriptor.MethodType.UNARY)
							.setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetV1"))
							.setSampledToLocalTracing(true)
							.setRequestMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Request
											.getDefaultInstance()))
							.setResponseMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Response
											.getDefaultInstance()))
							.setSchemaDescriptor(new PlayerStorageMethodDescriptorSupplier("SetV1")).build();
				}
			}
		}
		return getSetV1Method;
	}

	private static volatile io.grpc.MethodDescriptor<net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Request, net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Response> getClearV1Method;

	@io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
			+ "ClearV1", requestType = net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Request.class, responseType = net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Response.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
	public static io.grpc.MethodDescriptor<net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Request, net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Response> getClearV1Method() {
		io.grpc.MethodDescriptor<net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Request, net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Response> getClearV1Method;
		if ((getClearV1Method = PlayerStorageGrpc.getClearV1Method) == null) {
			synchronized (PlayerStorageGrpc.class) {
				if ((getClearV1Method = PlayerStorageGrpc.getClearV1Method) == null) {
					PlayerStorageGrpc.getClearV1Method = getClearV1Method = io.grpc.MethodDescriptor.<net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Request, net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Response>newBuilder()
							.setType(io.grpc.MethodDescriptor.MethodType.UNARY)
							.setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClearV1"))
							.setSampledToLocalTracing(true)
							.setRequestMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Request
											.getDefaultInstance()))
							.setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
									net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Response
											.getDefaultInstance()))
							.setSchemaDescriptor(new PlayerStorageMethodDescriptorSupplier("ClearV1")).build();
				}
			}
		}
		return getClearV1Method;
	}

	/**
	 * Creates a new async stub that supports all call types for the service
	 */
	public static PlayerStorageStub newStub(io.grpc.Channel channel) {
		io.grpc.stub.AbstractStub.StubFactory<PlayerStorageStub> factory = new io.grpc.stub.AbstractStub.StubFactory<PlayerStorageStub>() {
			@java.lang.Override
			public PlayerStorageStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
				return new PlayerStorageStub(channel, callOptions);
			}
		};
		return PlayerStorageStub.newStub(factory, channel);
	}

	/**
	 * Creates a new blocking-style stub that supports unary and streaming output
	 * calls on the service
	 */
	public static PlayerStorageBlockingStub newBlockingStub(io.grpc.Channel channel) {
		io.grpc.stub.AbstractStub.StubFactory<PlayerStorageBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<PlayerStorageBlockingStub>() {
			@java.lang.Override
			public PlayerStorageBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
				return new PlayerStorageBlockingStub(channel, callOptions);
			}
		};
		return PlayerStorageBlockingStub.newStub(factory, channel);
	}

	/**
	 * Creates a new ListenableFuture-style stub that supports unary calls on the
	 * service
	 */
	public static PlayerStorageFutureStub newFutureStub(io.grpc.Channel channel) {
		io.grpc.stub.AbstractStub.StubFactory<PlayerStorageFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<PlayerStorageFutureStub>() {
			@java.lang.Override
			public PlayerStorageFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
				return new PlayerStorageFutureStub(channel, callOptions);
			}
		};
		return PlayerStorageFutureStub.newStub(factory, channel);
	}

	/**
	 */
	public interface AsyncService {

		/**
		 */
		default void searchV1(net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Request request,
				io.grpc.stub.StreamObserver<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Response> responseObserver) {
			io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchV1Method(), responseObserver);
		}

		/**
		 */
		default void setV1(net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Request request,
				io.grpc.stub.StreamObserver<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Response> responseObserver) {
			io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetV1Method(), responseObserver);
		}

		/**
		 */
		default void clearV1(net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Request request,
				io.grpc.stub.StreamObserver<net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Response> responseObserver) {
			io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClearV1Method(), responseObserver);
		}
	}

	/**
	 * Base class for the server implementation of the service PlayerStorage.
	 */
	public static abstract class PlayerStorageImplBase implements io.grpc.BindableService, AsyncService {

		@java.lang.Override
		public final io.grpc.ServerServiceDefinition bindService() {
			return PlayerStorageGrpc.bindService(this);
		}
	}

	/**
	 * A stub to allow clients to do asynchronous rpc calls to service
	 * PlayerStorage.
	 */
	public static final class PlayerStorageStub extends io.grpc.stub.AbstractAsyncStub<PlayerStorageStub> {
		private PlayerStorageStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			super(channel, callOptions);
		}

		@java.lang.Override
		protected PlayerStorageStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			return new PlayerStorageStub(channel, callOptions);
		}

		/**
		 */
		public void searchV1(net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Request request,
				io.grpc.stub.StreamObserver<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Response> responseObserver) {
			io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getSearchV1Method(), getCallOptions()),
					request, responseObserver);
		}

		/**
		 */
		public void setV1(net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Request request,
				io.grpc.stub.StreamObserver<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Response> responseObserver) {
			io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getSetV1Method(), getCallOptions()), request,
					responseObserver);
		}

		/**
		 */
		public void clearV1(net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Request request,
				io.grpc.stub.StreamObserver<net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Response> responseObserver) {
			io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getClearV1Method(), getCallOptions()), request,
					responseObserver);
		}
	}

	/**
	 * A stub to allow clients to do synchronous rpc calls to service PlayerStorage.
	 */
	public static final class PlayerStorageBlockingStub
			extends
				io.grpc.stub.AbstractBlockingStub<PlayerStorageBlockingStub> {
		private PlayerStorageBlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			super(channel, callOptions);
		}

		@java.lang.Override
		protected PlayerStorageBlockingStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			return new PlayerStorageBlockingStub(channel, callOptions);
		}

		/**
		 */
		public net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Response searchV1(
				net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Request request) {
			return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getSearchV1Method(), getCallOptions(),
					request);
		}

		/**
		 */
		public net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Response setV1(
				net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Request request) {
			return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getSetV1Method(), getCallOptions(),
					request);
		}

		/**
		 */
		public net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Response clearV1(
				net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Request request) {
			return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getClearV1Method(), getCallOptions(),
					request);
		}
	}

	/**
	 * A stub to allow clients to do ListenableFuture-style rpc calls to service
	 * PlayerStorage.
	 */
	public static final class PlayerStorageFutureStub extends io.grpc.stub.AbstractFutureStub<PlayerStorageFutureStub> {
		private PlayerStorageFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			super(channel, callOptions);
		}

		@java.lang.Override
		protected PlayerStorageFutureStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			return new PlayerStorageFutureStub(channel, callOptions);
		}

		/**
		 */
		public com.google.common.util.concurrent.ListenableFuture<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Response> searchV1(
				net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Request request) {
			return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getSearchV1Method(), getCallOptions()),
					request);
		}

		/**
		 */
		public com.google.common.util.concurrent.ListenableFuture<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Response> setV1(
				net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Request request) {
			return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getSetV1Method(), getCallOptions()),
					request);
		}

		/**
		 */
		public com.google.common.util.concurrent.ListenableFuture<net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Response> clearV1(
				net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Request request) {
			return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getClearV1Method(), getCallOptions()),
					request);
		}
	}

	private static final int METHODID_SEARCH_V1 = 0;
	private static final int METHODID_SET_V1 = 1;
	private static final int METHODID_CLEAR_V1 = 2;

	private static final class MethodHandlers<Req, Resp>
			implements
				io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
				io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
				io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
				io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
		private final AsyncService serviceImpl;
		private final int methodId;

		MethodHandlers(AsyncService serviceImpl, int methodId) {
			this.serviceImpl = serviceImpl;
			this.methodId = methodId;
		}

		@java.lang.Override
		@java.lang.SuppressWarnings("unchecked")
		public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
			switch (methodId) {
				case METHODID_SEARCH_V1 :
					serviceImpl.searchV1(
							(net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Request) request,
							(io.grpc.stub.StreamObserver<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Response>) responseObserver);
					break;
				case METHODID_SET_V1 :
					serviceImpl.setV1((net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Request) request,
							(io.grpc.stub.StreamObserver<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Response>) responseObserver);
					break;
				case METHODID_CLEAR_V1 :
					serviceImpl.clearV1(
							(net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Request) request,
							(io.grpc.stub.StreamObserver<net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Response>) responseObserver);
					break;
				default :
					throw new AssertionError();
			}
		}

		@java.lang.Override
		@java.lang.SuppressWarnings("unchecked")
		public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
			switch (methodId) {
				default :
					throw new AssertionError();
			}
		}
	}

	public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
		return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
				.addMethod(getSearchV1Method(), io.grpc.stub.ServerCalls.asyncUnaryCall(
						new MethodHandlers<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Request, net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1.Response>(
								service, METHODID_SEARCH_V1)))
				.addMethod(getSetV1Method(), io.grpc.stub.ServerCalls.asyncUnaryCall(
						new MethodHandlers<net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Request, net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1.Response>(
								service, METHODID_SET_V1)))
				.addMethod(getClearV1Method(), io.grpc.stub.ServerCalls.asyncUnaryCall(
						new MethodHandlers<net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Request, net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1.Response>(
								service, METHODID_CLEAR_V1)))
				.build();
	}

	private static abstract class PlayerStorageBaseDescriptorSupplier
			implements
				io.grpc.protobuf.ProtoFileDescriptorSupplier,
				io.grpc.protobuf.ProtoServiceDescriptorSupplier {
		PlayerStorageBaseDescriptorSupplier() {
		}

		@java.lang.Override
		public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
			return net.averak.gsync.schema.protobuf.player_api.PlayerStorageOuterClass.getDescriptor();
		}

		@java.lang.Override
		public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
			return getFileDescriptor().findServiceByName("PlayerStorage");
		}
	}

	private static final class PlayerStorageFileDescriptorSupplier extends PlayerStorageBaseDescriptorSupplier {
		PlayerStorageFileDescriptorSupplier() {
		}
	}

	private static final class PlayerStorageMethodDescriptorSupplier extends PlayerStorageBaseDescriptorSupplier
			implements
				io.grpc.protobuf.ProtoMethodDescriptorSupplier {
		private final java.lang.String methodName;

		PlayerStorageMethodDescriptorSupplier(java.lang.String methodName) {
			this.methodName = methodName;
		}

		@java.lang.Override
		public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
			return getServiceDescriptor().findMethodByName(methodName);
		}
	}

	private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

	public static io.grpc.ServiceDescriptor getServiceDescriptor() {
		io.grpc.ServiceDescriptor result = serviceDescriptor;
		if (result == null) {
			synchronized (PlayerStorageGrpc.class) {
				result = serviceDescriptor;
				if (result == null) {
					serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
							.setSchemaDescriptor(new PlayerStorageFileDescriptorSupplier())
							.addMethod(getSearchV1Method()).addMethod(getSetV1Method()).addMethod(getClearV1Method())
							.build();
				}
			}
		}
		return result;
	}
}
