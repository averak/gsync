package net.averak.gsync.schema.protobuf.player_api;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.61.0)", comments = "Source: player_api/friend.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FriendGrpc {

	private FriendGrpc() {
	}

	public static final java.lang.String SERVICE_NAME = "gsync.player_api.Friend";

	// Static method descriptors that strictly reflect the proto.
	private static volatile io.grpc.MethodDescriptor<net.averak.gsync.schema.protobuf.player_api.FriendListV1.Request, net.averak.gsync.schema.protobuf.player_api.FriendListV1.Response> getListV1Method;

	@io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
			+ "ListV1", requestType = net.averak.gsync.schema.protobuf.player_api.FriendListV1.Request.class, responseType = net.averak.gsync.schema.protobuf.player_api.FriendListV1.Response.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
	public static io.grpc.MethodDescriptor<net.averak.gsync.schema.protobuf.player_api.FriendListV1.Request, net.averak.gsync.schema.protobuf.player_api.FriendListV1.Response> getListV1Method() {
		io.grpc.MethodDescriptor<net.averak.gsync.schema.protobuf.player_api.FriendListV1.Request, net.averak.gsync.schema.protobuf.player_api.FriendListV1.Response> getListV1Method;
		if ((getListV1Method = FriendGrpc.getListV1Method) == null) {
			synchronized (FriendGrpc.class) {
				if ((getListV1Method = FriendGrpc.getListV1Method) == null) {
					FriendGrpc.getListV1Method = getListV1Method = io.grpc.MethodDescriptor.<net.averak.gsync.schema.protobuf.player_api.FriendListV1.Request, net.averak.gsync.schema.protobuf.player_api.FriendListV1.Response>newBuilder()
							.setType(io.grpc.MethodDescriptor.MethodType.UNARY)
							.setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListV1"))
							.setSampledToLocalTracing(true)
							.setRequestMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(net.averak.gsync.schema.protobuf.player_api.FriendListV1.Request
											.getDefaultInstance()))
							.setResponseMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(net.averak.gsync.schema.protobuf.player_api.FriendListV1.Response
											.getDefaultInstance()))
							.setSchemaDescriptor(new FriendMethodDescriptorSupplier("ListV1")).build();
				}
			}
		}
		return getListV1Method;
	}

	/**
	 * Creates a new async stub that supports all call types for the service
	 */
	public static FriendStub newStub(io.grpc.Channel channel) {
		io.grpc.stub.AbstractStub.StubFactory<FriendStub> factory = new io.grpc.stub.AbstractStub.StubFactory<FriendStub>() {
			@java.lang.Override
			public FriendStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
				return new FriendStub(channel, callOptions);
			}
		};
		return FriendStub.newStub(factory, channel);
	}

	/**
	 * Creates a new blocking-style stub that supports unary and streaming output
	 * calls on the service
	 */
	public static FriendBlockingStub newBlockingStub(io.grpc.Channel channel) {
		io.grpc.stub.AbstractStub.StubFactory<FriendBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<FriendBlockingStub>() {
			@java.lang.Override
			public FriendBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
				return new FriendBlockingStub(channel, callOptions);
			}
		};
		return FriendBlockingStub.newStub(factory, channel);
	}

	/**
	 * Creates a new ListenableFuture-style stub that supports unary calls on the
	 * service
	 */
	public static FriendFutureStub newFutureStub(io.grpc.Channel channel) {
		io.grpc.stub.AbstractStub.StubFactory<FriendFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<FriendFutureStub>() {
			@java.lang.Override
			public FriendFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
				return new FriendFutureStub(channel, callOptions);
			}
		};
		return FriendFutureStub.newStub(factory, channel);
	}

	/**
	 */
	public interface AsyncService {

		/**
		 */
		default void listV1(net.averak.gsync.schema.protobuf.player_api.FriendListV1.Request request,
				io.grpc.stub.StreamObserver<net.averak.gsync.schema.protobuf.player_api.FriendListV1.Response> responseObserver) {
			io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListV1Method(), responseObserver);
		}
	}

	/**
	 * Base class for the server implementation of the service Friend.
	 */
	public static abstract class FriendImplBase implements io.grpc.BindableService, AsyncService {

		@java.lang.Override
		public final io.grpc.ServerServiceDefinition bindService() {
			return FriendGrpc.bindService(this);
		}
	}

	/**
	 * A stub to allow clients to do asynchronous rpc calls to service Friend.
	 */
	public static final class FriendStub extends io.grpc.stub.AbstractAsyncStub<FriendStub> {
		private FriendStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			super(channel, callOptions);
		}

		@java.lang.Override
		protected FriendStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			return new FriendStub(channel, callOptions);
		}

		/**
		 */
		public void listV1(net.averak.gsync.schema.protobuf.player_api.FriendListV1.Request request,
				io.grpc.stub.StreamObserver<net.averak.gsync.schema.protobuf.player_api.FriendListV1.Response> responseObserver) {
			io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getListV1Method(), getCallOptions()), request,
					responseObserver);
		}
	}

	/**
	 * A stub to allow clients to do synchronous rpc calls to service Friend.
	 */
	public static final class FriendBlockingStub extends io.grpc.stub.AbstractBlockingStub<FriendBlockingStub> {
		private FriendBlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			super(channel, callOptions);
		}

		@java.lang.Override
		protected FriendBlockingStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			return new FriendBlockingStub(channel, callOptions);
		}

		/**
		 */
		public net.averak.gsync.schema.protobuf.player_api.FriendListV1.Response listV1(
				net.averak.gsync.schema.protobuf.player_api.FriendListV1.Request request) {
			return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getListV1Method(), getCallOptions(),
					request);
		}
	}

	/**
	 * A stub to allow clients to do ListenableFuture-style rpc calls to service
	 * Friend.
	 */
	public static final class FriendFutureStub extends io.grpc.stub.AbstractFutureStub<FriendFutureStub> {
		private FriendFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			super(channel, callOptions);
		}

		@java.lang.Override
		protected FriendFutureStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			return new FriendFutureStub(channel, callOptions);
		}

		/**
		 */
		public com.google.common.util.concurrent.ListenableFuture<net.averak.gsync.schema.protobuf.player_api.FriendListV1.Response> listV1(
				net.averak.gsync.schema.protobuf.player_api.FriendListV1.Request request) {
			return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getListV1Method(), getCallOptions()),
					request);
		}
	}

	private static final int METHODID_LIST_V1 = 0;

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
				case METHODID_LIST_V1 :
					serviceImpl.listV1((net.averak.gsync.schema.protobuf.player_api.FriendListV1.Request) request,
							(io.grpc.stub.StreamObserver<net.averak.gsync.schema.protobuf.player_api.FriendListV1.Response>) responseObserver);
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
				.addMethod(getListV1Method(), io.grpc.stub.ServerCalls.asyncUnaryCall(
						new MethodHandlers<net.averak.gsync.schema.protobuf.player_api.FriendListV1.Request, net.averak.gsync.schema.protobuf.player_api.FriendListV1.Response>(
								service, METHODID_LIST_V1)))
				.build();
	}

	private static abstract class FriendBaseDescriptorSupplier
			implements
				io.grpc.protobuf.ProtoFileDescriptorSupplier,
				io.grpc.protobuf.ProtoServiceDescriptorSupplier {
		FriendBaseDescriptorSupplier() {
		}

		@java.lang.Override
		public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
			return net.averak.gsync.schema.protobuf.player_api.FriendOuterClass.getDescriptor();
		}

		@java.lang.Override
		public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
			return getFileDescriptor().findServiceByName("Friend");
		}
	}

	private static final class FriendFileDescriptorSupplier extends FriendBaseDescriptorSupplier {
		FriendFileDescriptorSupplier() {
		}
	}

	private static final class FriendMethodDescriptorSupplier extends FriendBaseDescriptorSupplier
			implements
				io.grpc.protobuf.ProtoMethodDescriptorSupplier {
		private final java.lang.String methodName;

		FriendMethodDescriptorSupplier(java.lang.String methodName) {
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
			synchronized (FriendGrpc.class) {
				result = serviceDescriptor;
				if (result == null) {
					serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
							.setSchemaDescriptor(new FriendFileDescriptorSupplier()).addMethod(getListV1Method())
							.build();
				}
			}
		}
		return result;
	}
}
